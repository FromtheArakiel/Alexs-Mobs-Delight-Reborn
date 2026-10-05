package dev.arakiel.alexsmobsdelight.events;

import java.util.ArrayList;
import java.util.List;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityGrizzlyBear;
import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import com.github.alexthe666.alexsmobs.entity.EntitySeaBear;
import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import dev.arakiel.alexsmobsdelight.config.Config;
import dev.arakiel.alexsmobsdelight.helper.ItemHelper;
import dev.arakiel.alexsmobsdelight.init.AMDHolders;
import dev.arakiel.alexsmobsdelight.init.BlockRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Block breaking, right click and entity interactions.
 *
 * <p>Replaces Forge's {@code BlockEvent.BreakEvent}, {@code PlayerInteractEvent} and
 * {@code PlayerEvent.BreakSpeed} with Architectury's interaction events; the break speed bonus moved
 * into {@code PlayerBreakSpeedMixin}.</p>
 */
public final class InteractEvents {
    private InteractEvents() {
    }

    public static void register() {
        BlockEvent.BREAK.register(InteractEvents::onBlockBreak);
        InteractionEvent.INTERACT_ENTITY.register(InteractEvents::onInteractEntity);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(InteractEvents::onRightClickBlock);
    }

    private static boolean isMantisTool(ItemStack tool) {
        return tool.is(ItemRegistry.MANTIS_SHRIMP_HAMMER.get())
                || tool.is(ItemRegistry.MANTIS_SHRIMP_SCYTHE.get())
                || tool.is(ItemRegistry.MANTIS_SHRIMP_SHOVEL.get())
                || tool.is(ItemRegistry.MANTIS_SHRIMP_AXE.get());
    }

    private static EventResult onBlockBreak(Level level, BlockPos pos, BlockState state,
                                            net.minecraft.server.level.ServerPlayer player,
                                            dev.architectury.utils.value.IntValue xp) {
        if (player == null || !(level instanceof ServerLevel serverLevel)) {
            return EventResult.pass();
        }
        ItemStack tool = player.getMainHandItem();
        if (!isMantisTool(tool)) {
            return EventResult.pass();
        }
        // The mantis shrimp tools auto smelt the drops instead of dropping the raw block.
        List<ItemStack> drops = ItemHelper.getBlockDrops(serverLevel, pos, player, tool);
        List<ItemStack> smelted = new ArrayList<>();
        for (ItemStack drop : drops) {
            if (ItemHelper.itemHasSmeltResult(drop, serverLevel)) {
                ItemStack result = ItemHelper.getItemSmeltResult(drop, serverLevel);
                if (!result.isEmpty()) {
                    result = result.copy();
                    result.setCount(result.getCount() * drop.getCount());
                    smelted.add(result);
                    continue;
                }
            }
            smelted.add(drop);
        }
        serverLevel.removeBlock(pos, false);
        for (ItemStack stack : smelted) {
            Containers.dropItemStack(serverLevel, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
        }
        serverLevel.playSound(null, pos, AMSoundRegistry.MANTIS_SHRIMP_SNAP.get(), SoundSource.BLOCKS);
        return EventResult.interruptFalse();
    }

    private static EventResult onInteractEntity(Player player, net.minecraft.world.entity.Entity target,
                                                InteractionHand hand) {
        Level level = player.level();
        if (level.isClientSide) {
            return EventResult.pass();
        }
        ItemStack held = player.getMainHandItem();

        if (held.is(ItemRegistry.SEA_BEAR_STEW.get()) && target instanceof EntityGrizzlyBear bear
                && bear.isInWater()) {
            EntitySeaBear seaBear = ((EntityType<EntitySeaBear>) AMEntityRegistry.SEA_BEAR.get()).create(level);
            if (seaBear != null) {
                seaBear.moveTo(bear.getX(), bear.getY(), bear.getZ());
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.CLOUD, bear.getX(), bear.getY(), bear.getZ(), 200,
                            1.0, 1.0, 1.0, 0.0);
                }
                seaBear.playSound(SoundEvents.ZOMBIE_CONVERTED_TO_DROWNED, 1.0F, 1.0F);
                level.addFreshEntity(seaBear);
                bear.discard();
                consumeHeld(player, held);
            }
        }
        if (held.is(ItemRegistry.SEAL_MEAT.get()) && target instanceof EntityOrca orca) {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, orca.getX(), orca.getY(), orca.getZ(),
                        30, 1.5, 1.0, 1.5, 3.0);
            }
            orca.playSound(SoundEvents.GENERIC_EAT, 1.0F, 0.5F);
            if (orca.getRandom().nextInt(100) <= Config.ORCA_GIFT_CHANCE.get()) {
                orca.spawnAtLocation(new ItemStack(ItemRegistry.ORCAS_LEAP_SOUP.get()));
            }
            consumeHeld(player, held);
        }
        if (held.is(Items.CAKE) && player.hasEffect(AMDHolders.effect(AMEffectRegistry.TIGERS_BLESSING.get()))
                && target instanceof EntityTiger tiger) {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, tiger.getX(), tiger.getY(), tiger.getZ(),
                        15, 0.5, 0.35, 0.5, 3.0);
            }
            tiger.playSound(SoundEvents.GENERIC_EAT, 1.0F, 0.5F);
            tiger.spawnAtLocation(new ItemStack(ItemRegistry.TIGER_CAKE_ROLL.get()));
            consumeHeld(player, held);
        }
        return EventResult.pass();
    }

    private static void consumeHeld(Player player, ItemStack held) {
        if (!player.getAbilities().instabuild) {
            held.shrink(1);
        }
    }

    private static EventResult onRightClickBlock(Player player, InteractionHand hand, BlockPos clickedPos,
                                                 Direction face) {
        if (hand != InteractionHand.MAIN_HAND || face == Direction.DOWN || face == Direction.UP) {
            return EventResult.pass();
        }
        Level level = player.level();
        if (level.isClientSide) {
            return EventResult.pass();
        }
        ItemStack stack = player.getItemInHand(hand);
        BlockPos placePos = clickedPos.relative(face);
        BlockState target = level.getBlockState(placePos);
        if (!level.mayInteract(player, placePos) || !player.mayUseItemAt(placePos, face, stack)
                || !target.canBeReplaced()) {
            return EventResult.pass();
        }
        BlockState placed = null;
        if (stack.is(AMItemRegistry.ACACIA_BLOSSOM.get()) && level.getBlockState(clickedPos).is(BlockTags.ACACIA_LOGS)) {
            placed = BlockRegistry.ACACIA_BLOSSOM_BLOCK.get().defaultBlockState();
        } else if (stack.is(AMItemRegistry.BANANA.get()) && level.getBlockState(clickedPos).is(BlockTags.JUNGLE_LOGS)) {
            placed = BlockRegistry.BANANA_BLOCK.get().defaultBlockState();
        }
        if (placed == null) {
            return EventResult.pass();
        }
        if (!level.setBlock(placePos, placed.setValue(HorizontalDirectionalBlock.FACING, face.getOpposite()),
                Block.UPDATE_ALL)) {
            return EventResult.pass();
        }
        level.playSound(null, placePos, SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        consumeHeld(player, stack);
        return EventResult.interruptTrue();
    }
}
