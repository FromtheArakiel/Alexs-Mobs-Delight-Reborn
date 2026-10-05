package dev.arakiel.alexsmobsdelight.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

public class WaterPlaceableBlockItem extends BlockItem {
    public WaterPlaceableBlockItem(Block block, Properties props) {
        super(block, props);
    }

    @Override
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        if (level.getFluidState(pos).is(Fluids.WATER) && level.getBlockState(pos.above()).canBeReplaced(ctx)) {
            return BlockPlaceContext.at(ctx, pos.above(), ctx.getClickedFace());
        }
        return super.updatePlacementContext(ctx);
    }
}