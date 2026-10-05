package dev.arakiel.alexsmobsdelight.items;

import dev.arakiel.alexsmobsdelight.config.Config;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/** Food that is never consumed but goes on a cooldown instead. */
public class EternalFoodItem extends Item {
    public EternalFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (stack.has(DataComponents.FOOD)) {
            entity.eat(level, stack.copy());
            if (entity instanceof Player player) {
                player.getCooldowns().addCooldown(stack.getItem(), Config.ETERNAL_SEAGULL_COOLDOWN.get());
            }
        }
        return stack;
    }
}
