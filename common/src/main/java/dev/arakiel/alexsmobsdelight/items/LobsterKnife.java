package dev.arakiel.alexsmobsdelight.items;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import vectorwing.farmersdelight.common.item.KnifeItem;

/**
 * Lobster knife: hold right click to dash forward; hitting something during the dash launches the
 * player upwards.
 *
 * <p>The 1.20.1 sources stored the dash flags in the item NBT. 1.21 keeps item NBT in a data
 * component, which is what {@link #flag}/{@link #setFlag} wrap.</p>
 */
public class LobsterKnife extends KnifeItem {
    private static final String JUMP = "AMDShrimpJump";
    private static final String DASH = "AMDShrimpDash";

    public LobsterKnife(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    private static boolean flag(ItemStack stack, String key) {
        return stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean(key);
    }

    private static void setFlag(ItemStack stack, String key, boolean value) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        tag.putBoolean(key, value);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        int used = this.getUseDuration(stack, entity) - timeLeft;
        if (entity instanceof Player player && used > 20) {
            setFlag(stack, JUMP, false);
            setFlag(stack, DASH, true);
            player.setDeltaMovement(player.getDeltaMovement().add(player.getViewVector(0.7F)));
            player.hurtMarked = true;
            player.getCooldowns().addCooldown(this, 10);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (level.isClientSide || !(entity instanceof Player player)) {
            return;
        }
        if (!flag(stack, JUMP) && flag(stack, DASH)) {
            Vec3 ahead = player.getBoundingBox().getCenter().add(player.getViewVector(1));
            AABB box = new AABB(ahead, ahead).inflate(0.1);
            List<Entity> hits = level.getEntities(player, box, target -> target instanceof LivingEntity && target != player);
            if (!hits.isEmpty() && hits.get(0) instanceof LivingEntity target
                    && target.hurt(player.damageSources().playerAttack(player), 4) && target.isAlive()) {
                setFlag(stack, JUMP, true);
                setFlag(stack, DASH, false);
            }
        }
        if (!player.getCooldowns().isOnCooldown(this) && player.onGround()) {
            if (flag(stack, JUMP)) {
                player.setDeltaMovement(new Vec3(0, 1.2, 0));
                player.hurtMarked = true;
            }
            setFlag(stack, JUMP, false);
            setFlag(stack, DASH, false);
        }
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
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.lobster_knife").withStyle(ChatFormatting.GREEN));
    }
}
