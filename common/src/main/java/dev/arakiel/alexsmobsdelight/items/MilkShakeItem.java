package dev.arakiel.alexsmobsdelight.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import vectorwing.farmersdelight.common.item.ConsumableItem;

/** Milkshake: always drunk, never eaten. */
public class MilkShakeItem extends ConsumableItem {
    public MilkShakeItem(Properties properties) {
        super(properties);
    }

    public MilkShakeItem(Properties properties, boolean effectTooltip) {
        super(properties, effectTooltip);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
