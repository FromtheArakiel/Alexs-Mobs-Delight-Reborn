package dev.arakiel.alexsmobsdelight.items;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import vectorwing.farmersdelight.common.item.PopsicleItem;

import java.util.Queue;

/**
 * Eating the banana popsicle crystallizes nearby water into banana slug mucus, like a frost walker
 * that freezes the block the player stands on.
 */
public class BananaPopsicleItem extends PopsicleItem {
    private static final int MAX_BLOCKS = 64;

    public BananaPopsicleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            pickupFluids(level, entity.getOnPos(), entity instanceof Player player ? player : null);
        }
        return super.finishUsingItem(stack, level, entity);
    }

    private void pickupFluids(Level level, BlockPos origin, Player player) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(origin, 0));
        int visited = 0;
        Fluid lastFluid = null;
        Block mucus = AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get();

        while (!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos pos = tuple.getA();
            BlockState state = level.getBlockState(pos);
            int distance = tuple.getB();
            if (!state.getFluidState().isEmpty()) {
                if (state.getBlock() instanceof BucketPickup pickup) {
                    pickup.pickupBlock(player, level, pos, state);
                    if (level.getBlockState(pos).isAir()) {
                        level.setBlockAndUpdate(pos, mucus.defaultBlockState());
                    }
                } else {
                    level.setBlockAndUpdate(pos, mucus.defaultBlockState());
                }
            }

            for (Direction direction : Direction.values()) {
                BlockPos neighbour = pos.relative(direction);
                BlockState neighbourState = level.getBlockState(neighbour);
                FluidState fluidState = level.getFluidState(neighbour);
                if (lastFluid != null && !fluidState.isEmpty() && lastFluid != fluidState.getType()) {
                    continue;
                }
                if (neighbourState.getBlock() instanceof SimpleWaterloggedBlock) {
                    if (!fluidState.isEmpty()) {
                        lastFluid = fluidState.getType();
                    }
                    ++visited;
                    level.setBlockAndUpdate(neighbour,
                            neighbourState.setValue(BlockStateProperties.WATERLOGGED, false));
                    if (distance < 6) {
                        queue.add(new Tuple<>(neighbour, distance + 1));
                    }
                } else if (neighbourState.getBlock() instanceof BucketPickup pickup) {
                    if (!fluidState.isEmpty()) {
                        lastFluid = fluidState.getType();
                    }
                    ++visited;
                    pickup.pickupBlock(player, level, neighbour, neighbourState);
                    if (level.getBlockState(neighbour).isAir()) {
                        level.setBlockAndUpdate(neighbour, mucus.defaultBlockState());
                    }
                    if (distance < 6) {
                        queue.add(new Tuple<>(neighbour, distance + 1));
                    }
                } else if (neighbourState.getBlock() instanceof LiquidBlock) {
                    if (!fluidState.isEmpty()) {
                        lastFluid = fluidState.getType();
                    }
                    level.setBlockAndUpdate(neighbour, mucus.defaultBlockState());
                    ++visited;
                    if (distance < 6) {
                        queue.add(new Tuple<>(neighbour, distance + 1));
                    }
                }
            }

            if (visited > MAX_BLOCKS) {
                break;
            }
        }
    }
}
