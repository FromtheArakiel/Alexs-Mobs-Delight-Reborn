package dev.arakiel.alexsmobsdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class MooseSausageWithSalmonBlock extends FeastBlock {
    protected static final VoxelShape CONTAINER_SHAPE = Block.box(2.0, 0.0, 0.0, 14.0, 8, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_A = Block.box(0.0, 0.0, 2.0, 16.0, 8, 14.0);

    public MooseSausageWithSalmonBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction d = state.getValue(FACING);
        return (d == Direction.WEST || d == Direction.EAST) ? CONTAINER_SHAPE_A : CONTAINER_SHAPE;
    }

    @Override
    public int getMaxServings() {
        return 3;
    }
}