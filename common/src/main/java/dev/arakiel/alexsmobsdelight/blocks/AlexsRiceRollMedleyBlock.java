package dev.arakiel.alexsmobsdelight.blocks;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class AlexsRiceRollMedleyBlock extends FeastBlock {
    public static final IntegerProperty ROLL_SERVINGS = IntegerProperty.create("servings", 0, 7);
    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
    protected static final VoxelShape FOOD_SHAPE;
    public final List<Supplier<Item>> riceRollServings;

    public AlexsRiceRollMedleyBlock(BlockBehaviour.Properties properties) {
        super(properties, ModItems.KELP_ROLL_SLICE, true);
        this.riceRollServings = Arrays.asList(AMItemRegistry.ACACIA_BLOSSOM, ItemRegistry.DEVILS_HOLE_PUPFISH_ROLL, ItemRegistry.MUDSKIPPER_ROLL, ItemRegistry.FLYING_FISH_ROLL, ItemRegistry.CATFISH_ROLL, ItemRegistry.TRIOPS_EGGS_ROLL, ItemRegistry.TRIOPS_EGGS_ROLL);
    }

    public IntegerProperty getServingsProperty() {
        return ROLL_SERVINGS;
    }

    public int getMaxServings() {
        return 7;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack((ItemLike)((Supplier)this.riceRollServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
    }

    @Override
    @SuppressWarnings("deprecation") // isSolid() is the legacy full-cube "solid" flag; isSolidRender() would stop glass-like blocks from supporting the dish.
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        return level.getBlockState(below).isSolid() || level.getFluidState(below).is(Fluids.WATER)
                || level.getBlockState(below).getBlock() instanceof IceBlock;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Integer)state.getValue(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROLL_SERVINGS);
    }

    static {
        FOOD_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0, 1.0, 2.0, 14.0, 3.0, 14.0), BooleanOp.OR);
    }
}
