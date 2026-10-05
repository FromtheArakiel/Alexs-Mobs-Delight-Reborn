package dev.arakiel.alexsmobsdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public class CommonCakeBlock extends CakeBlock {
    public final Supplier<Item> sliceItem;
    public CommonCakeBlock(Properties p_51184_, Supplier<Item> slice) {
        super(p_51184_);
        this.sliceItem = slice;
    }

    /**
     * 1.21 splits {@code Block#use} into {@code useItemOn} (an item is in hand) and {@code useWithoutItem}
     * (empty hand, or the fall-through once the item did not consume the interaction). Cutting a slice with
     * a knife stays on the item path, candle placement is inherited from {@link CakeBlock}, and eating a
     * slice lives on the empty-hand path.
     */
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (isKnife(stack)) {
            this.cutSlice(level, pos, state, player);
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hit);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            if (super.useWithoutItem(state, level, pos, player, hit) == InteractionResult.SUCCESS) {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return this.eatSlice(level, pos, state, player);
    }

    /**
     * 1.20.1 checked {@code farmersdelight:tools/knives} and {@code forge:tools/knives}; in 1.21 the
     * common tag is {@code c:tools/knife} and Farmer's Delight keeps its own knife tag.
     */
    private static boolean isKnife(ItemStack stack) {
        return stack.is(TagKey.create(Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives")))
                || stack.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/knife")));
    }

    protected InteractionResult eatSlice(LevelAccessor p_51186_, BlockPos p_51187_, BlockState p_51188_, Player p_51189_) {
        if (!p_51189_.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            p_51189_.awardStat(Stats.EAT_CAKE_SLICE);
            Item sliceItem = this.sliceItem.get();
            ItemStack sliceStack = new ItemStack(sliceItem);
            sliceItem.finishUsingItem(sliceStack, (Level) p_51186_, p_51189_);
            int $$4 = (Integer)p_51188_.getValue(BITES);
            p_51186_.gameEvent(p_51189_, GameEvent.EAT, p_51187_);
            if ($$4 < 6) {
                p_51186_.setBlock(p_51187_, (BlockState)p_51188_.setValue(BITES, $$4 + 1), 3);
            } else {
                p_51186_.removeBlock(p_51187_, false);
                p_51186_.gameEvent(p_51189_, GameEvent.BLOCK_DESTROY, p_51187_);
            }

            return InteractionResult.SUCCESS;
        }
    }

    protected InteractionResult cutSlice(Level level, BlockPos pos, BlockState state, Player player) {
        int bites = (Integer)state.getValue(BITES);
        if (bites < 6) {
            level.setBlock(pos, (BlockState)state.setValue(BITES, bites + 1), 3);
        } else {
            level.removeBlock(pos, false);
        }
        ItemStack slice = new ItemStack(sliceItem.get());
        ItemEntity entityToSpawn = new ItemEntity(level, (double)pos.getX() + (double)bites * 0.1, (double)pos.getY() + 0.2, (double)pos.getZ() + 0.5, slice);
        entityToSpawn.setDeltaMovement(-0.05, 0.0, 0.0);
        entityToSpawn.setPickUpDelay(10);
        level.addFreshEntity(entityToSpawn);

        level.playSound((Player)null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
        return InteractionResult.SUCCESS;
    }
}
