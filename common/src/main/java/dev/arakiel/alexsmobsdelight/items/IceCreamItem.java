package dev.arakiel.alexsmobsdelight.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

/** Ice cream, chills the eater. */
public class IceCreamItem extends ConsumableItem {
    public IceCreamItem(Properties properties) {
        super(properties);
    }

    public IceCreamItem(Properties properties, boolean effectTooltip) {
        super(properties, effectTooltip);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        consumer.setTicksFrozen(consumer.getTicksFrozen() + 200);
        return super.finishUsingItem(stack, level, consumer);
    }
}
