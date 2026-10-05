package dev.arakiel.alexsmobsdelight.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

/** Cheese clears every harmful effect, like milk. */
public class CheeseItem extends ConsumableItem {
    public CheeseItem(Properties properties) {
        super(properties);
    }

    public CheeseItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public CheeseItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        for (MobEffectInstance instance : new ArrayList<>(entity.getActiveEffects())) {
            MobEffectCategory category = instance.getEffect().value().getCategory();
            if (category == MobEffectCategory.HARMFUL) {
                entity.removeEffect(instance.getEffect());
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
