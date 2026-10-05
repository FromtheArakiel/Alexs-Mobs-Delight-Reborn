package dev.arakiel.alexsmobsdelight.integration.jei;

import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;

@SuppressWarnings("removal")
public class TooltipCallback implements IRecipeSlotTooltipCallback {

    @Override
    public void onTooltip(IRecipeSlotView slotView, List<Component> tooltip) {
        tooltip.add(Component.translatable("alexsmobsdelight.jei.kiviak_decomposition.tips").withStyle(ChatFormatting.YELLOW));
    }
}