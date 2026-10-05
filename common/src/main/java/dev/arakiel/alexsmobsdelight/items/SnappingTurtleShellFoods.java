package dev.arakiel.alexsmobsdelight.items;

import vectorwing.farmersdelight.common.item.ConsumableItem;

/**
 * Alligator snapping turtle shell food.
 *
 * <p>Like {@link TurtleShellFoods} the shell remainder is a data component in 1.21 and is set by
 * {@code ItemRegistry} through {@code Item.Properties#craftRemainder}.</p>
 */
public class SnappingTurtleShellFoods extends ConsumableItem {
    public SnappingTurtleShellFoods(Properties properties) {
        super(properties);
    }

    public SnappingTurtleShellFoods(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public SnappingTurtleShellFoods(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }
}
