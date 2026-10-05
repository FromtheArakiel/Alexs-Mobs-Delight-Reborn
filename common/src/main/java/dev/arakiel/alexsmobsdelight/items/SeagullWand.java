package dev.arakiel.alexsmobsdelight.items;

import java.util.ArrayList;
import java.util.List;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import dev.arakiel.alexsmobsdelight.helper.ItemHelper;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

/** Wand that steals food from players, villagers and mobs, exactly like a seagull. */
public class SeagullWand extends HoeItem {
    public SeagullWand(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        int used = this.getUseDuration(stack, entity) - timeLeft;
        if (!(entity instanceof Player player) || used <= 20) {
            return;
        }
        AABB box = new AABB(player.getBoundingBox().getCenter().add(player.getViewVector(1.0F)),
                player.getBoundingBox().getCenter().add(player.getViewVector(1.0F))).inflate(1.0);
        List<Entity> hits = level.getEntities(player, box, target -> target instanceof LivingEntity && target != player);
        if (hits.isEmpty() || !(hits.get(0) instanceof LivingEntity target)) {
            return;
        }

        List<ItemStack> candidates = new ArrayList<>();
        if (target instanceof Player targetPlayer) {
            for (int slot = 0; slot < 9; ++slot) {
                ItemStack carried = targetPlayer.getInventory().items.get(slot);
                if (isStealableFood(carried)) {
                    candidates.add(carried);
                }
            }
        } else if (target instanceof AbstractVillager villager) {
            MerchantOffers originalOffers = villager.getOffers();
            MerchantOffers offers = new MerchantOffers();
            if (!originalOffers.isEmpty()) {
                for (MerchantOffer offer : originalOffers) {
                    if (!offer.isOutOfStock() && (isEdible(offer.getResult()) || isEdible(offer.getBaseCostA()))) {
                        offers.add(offer);
                    }
                }
                if (!offers.isEmpty()) {
                    MerchantOffer offer = offers.get(offers.size() == 1 ? 0
                            : player.getRandom().nextInt(offers.size() - 1));
                    ItemStack stolen = offer.getResult().getItem() == Items.EMERALD
                            ? offer.getBaseCostA() : offer.getResult();
                    if (!stolen.isEmpty() && !offer.isOutOfStock()) {
                        for (int use = 1; use <= offer.getMaxUses(); use++) {
                            offer.increaseUses();
                        }
                        ItemStack copy = stolen.copy();
                        copy.setCount(1);
                        candidates.add(copy);
                    }
                }
            }
        } else {
            if (isStealableFood(target.getItemInHand(InteractionHand.MAIN_HAND))) {
                candidates.add(target.getItemInHand(InteractionHand.MAIN_HAND));
            }
            if (isStealableFood(target.getItemInHand(InteractionHand.OFF_HAND)) && candidates.isEmpty()) {
                candidates.add(target.getItemInHand(InteractionHand.OFF_HAND));
            }
        }

        if (candidates.isEmpty()) {
            return;
        }
        ItemStack food = candidates.get(candidates.size() == 1 ? 0
                : player.getRandom().nextInt(candidates.size() - 1));
        if (food.isEmpty()) {
            return;
        }
        ItemStack copy = food.copy();
        copy.setCount(1);
        food.shrink(1);
        if (!player.getInventory().add(copy)) {
            player.drop(copy, false);
        }
        if (target instanceof ServerPlayer serverPlayer) {
            AMAdvancementTriggerRegistry.SEAGULL_STEAL.trigger(serverPlayer);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.success(stack);
    }

    public boolean isStealableFood(ItemStack stack) {
        ResourceLocation id = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (id != null) {
            for (String blacklisted : AMConfig.seagullStealingBlacklist) {
                if (id.toString().equals(blacklisted)) {
                    return false;
                }
            }
        }
        return isEdible(stack) && !stack.is(ItemRegistry.KIVIAK.get());
    }

    private static boolean isEdible(ItemStack stack) {
        return stack.has(DataComponents.FOOD);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(ItemHelper.customColor(Component.translatable("tooltip.alexsmobsdelight.seagull_wand"), 251, 198, 69));
        tooltip.add(ItemHelper.customColor(Component.translatable("tooltip.alexsmobsdelight.seagull_wand_1"), 251, 198, 69));
    }
}
