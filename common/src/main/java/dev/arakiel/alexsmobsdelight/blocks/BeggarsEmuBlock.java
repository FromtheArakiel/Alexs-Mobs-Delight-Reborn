package dev.arakiel.alexsmobsdelight.blocks;

import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BeggarsEmuBlock extends FeastBlock {
    protected static final VoxelShape CONTAINER_SHAPE = Block.box(3.0, 0.0, 1.0, 13.0, 12, 15.0);
    protected static final VoxelShape CONTAINER_SHAPE_A = Block.box(1.0, 0.0, 3.0, 15.0, 12, 13.0);

    public static final IntegerProperty ROLL_SERVINGS = IntegerProperty.create("servings", 0, 7);
    public final List<Supplier<Item>> riceRollServings;

    public BeggarsEmuBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
        this.riceRollServings = Arrays.asList(ItemRegistry.PLATE_OF_BEGGARS_EMU, ItemRegistry.PLATE_OF_BEGGARS_EMU, ItemRegistry.PLATE_OF_BEGGARS_EMU, ItemRegistry.PLATE_OF_BEGGARS_EMU, ItemRegistry.PLATE_OF_BEGGARS_EMU, ItemRegistry.PLATE_OF_BEGGARS_EMU, ItemRegistry.PLATE_OF_BEGGARS_EMU);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction d = state.getValue(FACING);
        return (d == Direction.WEST || d == Direction.EAST) ? CONTAINER_SHAPE_A : CONTAINER_SHAPE;
    }

    public IntegerProperty getServingsProperty() {
        return ROLL_SERVINGS;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack((ItemLike)((Supplier)this.riceRollServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROLL_SERVINGS);
    }

    @Override
    public int getMaxServings() {
        return 7;
    }
}
