package dev.arakiel.alexsmobsdelight.items;

import net.minecraft.world.item.Item;

/**
 * Dimensional food.
 *
 * <p>The 1.20.1 sources rendered it with a Forge client extension point. The custom item renderer
 * is registered by each platform's client code instead (see the fabric and neoforge client
 * entry points), so the item itself is a plain item.</p>
 */
public class DimensionalFoods extends Item {
    public DimensionalFoods(Properties properties) {
        super(properties);
    }
}
