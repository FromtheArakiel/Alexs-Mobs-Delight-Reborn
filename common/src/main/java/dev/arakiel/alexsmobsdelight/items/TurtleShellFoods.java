package dev.arakiel.alexsmobsdelight.items;

import vectorwing.farmersdelight.common.item.ConsumableItem;

/**
 * Terrapin shell food.
 *
 * <p>1.21 turned crafting remainders into the {@code minecraft:craft_remainder} data component, so
 * the shell is now set through {@code Item.Properties#craftRemainder} at registration time (see
 * {@code ItemRegistry}).</p>
 */
public class TurtleShellFoods extends ConsumableItem {
    public TurtleShellFoods(Properties properties) {
        super(properties);
    }

    public TurtleShellFoods(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public TurtleShellFoods(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }
}
