package dev.arakiel.alexsmobsdelight.blocks;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MushroomsBraisedWithCentipedeBlock extends FeastBlock {
    protected static final VoxelShape CONTAINER_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 10, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_A = Block.box(2.0, 0.0, 2.0, 14.0, 14, 14.0);
    public static final IntegerProperty ROLL_SERVINGS = IntegerProperty.create("servings", 0, 7);
    public final List<Supplier<Item>> riceRollServings;

    public MushroomsBraisedWithCentipedeBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
        this.riceRollServings = Arrays.asList(ModItems.MUSHROOM_RICE, ModItems.MUSHROOM_RICE, ModItems.MUSHROOM_RICE, ItemRegistry.PLATE_OF_MUSHROOMS_BRAISED_WITH_CENTIPEDE, ItemRegistry.PLATE_OF_MUSHROOMS_BRAISED_WITH_CENTIPEDE, ItemRegistry.PLATE_OF_MUSHROOMS_BRAISED_WITH_CENTIPEDE, ItemRegistry.PLATE_OF_MUSHROOMS_BRAISED_WITH_CENTIPEDE);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(this.getServingsProperty()) >= 4 ? Shapes.joinUnoptimized(CONTAINER_SHAPE_A, CONTAINER_SHAPE, BooleanOp.OR) : CONTAINER_SHAPE;
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
