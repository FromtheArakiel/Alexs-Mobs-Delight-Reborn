package dev.arakiel.alexsmobsdelight.integration.jade;

import dev.arakiel.alexsmobsdelight.blocks.SteamedStuffedCrocodileBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vectorwing.farmersdelight.common.block.FeastBlock;

/** Shows how many servings are left on a feast style block. */
public enum FeastServingsProvider implements IBlockComponentProvider {
    INSTANCE;

    private static final ResourceLocation UID =
            ResourceLocation.fromNamespaceAndPath("alexsmobsdelight", "feast_servings");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        BlockState state = accessor.getBlockState();
        if (state.getBlock() instanceof FeastBlock feast) {
            tooltip.add(Component.translatable("jade.alexsmobsdelight.servings",
                    state.getValue(feast.getServingsProperty()), feast.getMaxServings()));
        } else if (state.getBlock() instanceof SteamedStuffedCrocodileBlock crocodile) {
            tooltip.add(Component.translatable("jade.alexsmobsdelight.servings",
                    state.getValue(crocodile.getServingsProperty()), crocodile.riceRollServings.size()));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}
