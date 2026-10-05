package dev.arakiel.alexsmobsdelight.integration.jade;

import dev.arakiel.alexsmobsdelight.blocks.KiviakBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

/** Shows the fermentation progress of a kiviak and what to do when it is done. */
public enum KiviakProvider implements IBlockComponentProvider {
    INSTANCE;

    private static final ResourceLocation UID =
            ResourceLocation.fromNamespaceAndPath("alexsmobsdelight", "kiviak");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        BlockState state = accessor.getBlockState();
        if (!(state.getBlock() instanceof KiviakBlock kiviak)) {
            return;
        }
        int stage = state.getValue(KiviakBlock.COMPOSTING);
        tooltip.add(Component.translatable("jade.alexsmobsdelight.ripening", stage,
                kiviak.getMaxCompostingStage()));
        if (stage >= kiviak.getMaxCompostingStage()) {
            tooltip.add(Component.translatable(state.getValue(KiviakBlock.OPENED)
                    ? "jade.alexsmobsdelight.ready"
                    : "jade.alexsmobsdelight.needs_knife"));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}
