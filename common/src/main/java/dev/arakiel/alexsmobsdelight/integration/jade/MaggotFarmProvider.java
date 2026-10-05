package dev.arakiel.alexsmobsdelight.integration.jade;

import dev.arakiel.alexsmobsdelight.blocks.MaggotFarmBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

/** Shows the compost level of the maggot farm, which mirrors the composter block. */
public enum MaggotFarmProvider implements IBlockComponentProvider {
    INSTANCE;

    private static final ResourceLocation UID =
            ResourceLocation.fromNamespaceAndPath("alexsmobsdelight", "maggot_farm");
    private static final int MAX_LEVEL = 8;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        BlockState state = accessor.getBlockState();
        if (!(state.getBlock() instanceof MaggotFarmBlock)) {
            return;
        }
        int level = state.getValue(MaggotFarmBlock.LEVEL);
        tooltip.add(Component.translatable("jade.alexsmobsdelight.compost", level, MAX_LEVEL));
        if (level >= MAX_LEVEL) {
            tooltip.add(Component.translatable("jade.alexsmobsdelight.ready"));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}
