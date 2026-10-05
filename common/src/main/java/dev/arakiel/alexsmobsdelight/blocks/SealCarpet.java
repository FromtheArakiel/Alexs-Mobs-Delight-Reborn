package dev.arakiel.alexsmobsdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class SealCarpet extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape MODEL_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    public SealCarpet(BlockBehaviour.Properties p_152915_) {
        super(p_152915_);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    public BlockState getStateForPlacement(BlockPlaceContext p_221508_) {
        FluidState $$1 = p_221508_.getLevel().getFluidState(p_221508_.getClickedPos());
        boolean $$2 = $$1.getType() == Fluids.WATER;
        return (BlockState)super.getStateForPlacement(p_221508_).setValue(WATERLOGGED, $$2);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56388_) {
        p_56388_.add(WATERLOGGED);
    }

    public VoxelShape getCollisionShape(BlockState p_56702_, BlockGetter p_56703_, BlockPos p_56704_, CollisionContext p_56705_) {
        return SHAPE;
    }

    public VoxelShape getShape(BlockState p_56684_, BlockGetter p_56685_, BlockPos p_56686_, CollisionContext p_56687_) {
        return MODEL_SHAPE;
    }

    public BlockState updateShape(BlockState p_152926_, Direction p_152927_, BlockState p_152928_, LevelAccessor p_152929_, BlockPos p_152930_, BlockPos p_152931_) {
        if ((Boolean)p_152926_.getValue(WATERLOGGED)) {
            p_152929_.scheduleTick(p_152930_, Fluids.WATER, Fluids.WATER.getTickDelay(p_152929_));
        }
        return !p_152926_.canSurvive(p_152929_, p_152930_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_152926_, p_152927_, p_152928_, p_152929_, p_152930_, p_152931_);
    }

    public boolean canSurvive(BlockState p_152922_, LevelReader p_152923_, BlockPos p_152924_) {
        return !p_152923_.isEmptyBlock(p_152924_.below());
    }

    public FluidState getFluidState(BlockState p_56397_) {
        return (Boolean)p_56397_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56397_);
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }
}
