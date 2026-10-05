package dev.arakiel.alexsmobsdelight.events;

import java.util.List;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import dev.architectury.event.events.client.ClientTooltipEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

/**
 * Extra tooltips for Alex's Mobs items.
 *
 * <p>The 1.20.1 sources used Forge's {@code ItemTooltipEvent}; the Architectury client tooltip event
 * is the cross platform equivalent. Only registered from the client entry points.</p>
 */
public final class TooltipEvents {
    private TooltipEvents() {
    }

    public static void register() {
        ClientTooltipEvent.ITEM.register((stack, lines, context, flag) -> addTooltips(stack, lines));
    }

    private static void addTooltips(ItemStack stack, List<Component> lines) {
        if (stack.is(AMItemRegistry.BANANA.get())) {
            lines.add(Component.translatable("tooltip.alexsmobsdelight.banana").withStyle(ChatFormatting.BLUE));
        }
        if (stack.is(AMItemRegistry.ACACIA_BLOSSOM.get())) {
            lines.add(Component.translatable("tooltip.alexsmobsdelight.acacia_blossom")
                    .withStyle(ChatFormatting.BLUE));
        }
    }
}
