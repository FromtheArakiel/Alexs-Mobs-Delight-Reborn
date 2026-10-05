package dev.arakiel.alexsmobsdelight.items;

import java.util.function.Predicate;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import dev.arakiel.alexsmobsdelight.entities.ThrownBananaEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

/**
 * Bow that shoots bananas.
 *
 * <p>1.21 removed the Forge arrow hooks, the item side enchantment hooks and
 * {@code setSecondsOnFire}; enchantability now comes from the
 * {@code minecraft:enchantable/bow} tag this mod adds its bow to.</p>
 */
public class BananaBowItem extends BowItem {
    public BananaBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) {
            return;
        }
        boolean infinite = player.getAbilities().instabuild
                || EnchantmentHelper.getItemEnchantmentLevel(enchantment(level, Enchantments.INFINITY), stack) > 0;
        ItemStack projectile = player.getProjectile(stack);
        int charge = this.getUseDuration(stack, entity) - timeLeft;
        if (charge < 0) {
            return;
        }
        if (projectile.isEmpty() && !infinite) {
            return;
        }
        if (projectile.isEmpty()) {
            projectile = new ItemStack(AMItemRegistry.BANANA.get());
        }
        float power = getPowerForTime(charge);
        if (power < 0.1D) {
            return;
        }
        if (!level.isClientSide) {
            ThrownBananaEntity banana = new ThrownBananaEntity(level, player);
            banana.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            if (EnchantmentHelper.getItemEnchantmentLevel(enchantment(level, Enchantments.FLAME), stack) > 0) {
                banana.igniteForSeconds(5.0F);
            }
            stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
            level.addFreshEntity(banana);
        }
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);
        if (!infinite && !player.getAbilities().instabuild) {
            projectile.shrink(1);
            if (projectile.isEmpty()) {
                player.getInventory().removeItem(projectile);
            }
        }
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    private static Holder<Enchantment> enchantment(Level level,
                                                   net.minecraft.resources.ResourceKey<Enchantment> key) {
        return level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(key);
    }

    public static float getPowerForTime(int charge) {
        float power = (float) charge / 20.0F;
        power = (power * power + power * 2.0F) / 3.0F;
        return power > 1.0F ? 1.0F : power;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean hasProjectile = !player.getProjectile(stack).isEmpty();
        if (!player.getAbilities().instabuild && !hasProjectile) {
            return InteractionResultHolder.fail(stack);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(AMItemRegistry.BANANA.get());
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }
}
