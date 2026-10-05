package dev.arakiel.alexsmobsdelight.items;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

/**
 * Crocodile scute sword: a vanilla sword that also makes the holder swim faster.
 *
 * <p>The old sources injected a Forge swim speed attribute through
 * {@code getAttributeModifiers}. 1.21 builds attribute modifiers as data on the item instead; the
 * vanilla water movement efficiency attribute keeps the same behaviour on both loaders.</p>
 */
public class CrocodileSword extends SwordItem {
    private static final double SWIM_BONUS = 0.5D;

    public CrocodileSword(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(withSwimSpeed(
                SwordItem.createAttributes(tier, attackDamage, attackSpeed))));
    }

    private static ItemAttributeModifiers withSwimSpeed(ItemAttributeModifiers modifiers) {
        return modifiers.withModifierAdded(Attributes.WATER_MOVEMENT_EFFICIENCY,
                new AttributeModifier(AlexsMobsDelight.id("crocodile_sword_swim_speed"), SWIM_BONUS,
                        AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
    }
}
