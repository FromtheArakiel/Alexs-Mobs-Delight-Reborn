package dev.arakiel.alexsmobsdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.tag.ModTags;

public class BeggarsEmuInTheMudBlock extends Block {
    public static final DirectionProperty FACING;
    protected static final VoxelShape CONTAINER_SHAPE = Block.box(2.0, 0.0, 0.0, 14.0, 14, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_A = Block.box(0.0, 0.0, 2.0, 16.0, 14, 14.0);

    public static BooleanProperty COOKED = BooleanProperty.create("cooked");

    public BeggarsEmuInTheMudBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(COOKED,false));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(COOKED);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            if(level.getRandom().nextFloat() < 0.2 && level.getBlockState(pos.below()).is(ModTags.Blocks.HEAT_SOURCES) && !state.getValue(COOKED)){
                level.setBlock(pos, state.setValue(COOKED, true), Block.UPDATE_ALL);
            }
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(COOKED,false);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, COOKED);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction d = state.getValue(FACING);
        return (d == Direction.WEST || d == Direction.EAST) ? CONTAINER_SHAPE_A : CONTAINER_SHAPE;
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
    }
}
