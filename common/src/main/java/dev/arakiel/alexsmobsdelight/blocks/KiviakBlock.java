package dev.arakiel.alexsmobsdelight.blocks;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Iterator;

public class KiviakBlock extends Block {
    public static IntegerProperty COMPOSTING = IntegerProperty.create("composting", 0, 7);
    public static final DirectionProperty FACING;
    public static BooleanProperty OPENED = BooleanProperty.create("opened");
    protected static final VoxelShape SHAPE_N = Block.box(0.0, 0.0, 3.0, 15.0, 13.0, 12.0);
    protected static final VoxelShape SHAPE_W = Block.box(3.0, 0.0, 1.0, 12.0, 13.0, 16.0);
    protected static final VoxelShape SHAPE_S = Block.box(1.0, 0.0, 4.0, 16.0, 13.0, 13.0);
    protected static final VoxelShape SHAPE_E = Block.box(4.0, 0.0, 0.0, 13.0, 13.0, 15.0);

    public KiviakBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(super.defaultBlockState().setValue(COMPOSTING, 0).setValue(FACING, Direction.NORTH).setValue(OPENED, false));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(COMPOSTING) != 7;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COMPOSTING, FACING, OPENED);
        super.createBlockStateDefinition(builder);
    }

    public int getMaxCompostingStage() {
        return 7;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            float chance = 0.0F;
            boolean coldBiome = false;
            int maxLight = 0;

            for (BlockPos neighborPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                BlockState neighborState = level.getBlockState(neighborPos);
                if (neighborState.is(BlockTags.ICE)) {
                    chance += 0.02F;
                }

                if (level.getBiome(pos).is(BiomeTags.SPAWNS_SNOW_FOXES) || level.getBiome(pos).is(BiomeTags.SPAWNS_COLD_VARIANT_FROGS)) {
                    coldBiome = true;
                }

                int light = level.getBrightness(LightLayer.SKY, neighborPos.above());
                if (light > maxLight) {
                    maxLight = light;
                }
            }

            chance += maxLight < 5 ? 0.1F : 0.05F;
            chance += coldBiome ? 0.1F : 0.0F;
            if (level.getRandom().nextFloat() <= chance) {
                if (state.getValue(COMPOSTING) != this.getMaxCompostingStage()) {
                    level.setBlock(pos, state.setValue(COMPOSTING, state.getValue(COMPOSTING) + 1), 3);
                }
            }

        }
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return this.getMaxCompostingStage() + 1 - blockState.getValue(COMPOSTING);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(100) == state.getValue(COMPOSTING) * 10) {
            level.addParticle(ParticleTypes.MYCELIUM, (double)pos.getX() + (double)random.nextFloat(), (double)pos.getY() + 1.1, (double)pos.getZ() + (double)random.nextFloat(), 0.0, 0.0, 0.0);
        }
    }

    /**
     * 1.21 replaced {@code Block#use} with {@code useItemOn}/{@code useWithoutItem}. The knife is required
     * to open the kiviak, so the knife branch is checked with the held stack here; when the interaction is
     * not consumed the player still gets the hint message from {@link #useWithoutItem}.
     */
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!stack.is(ModTags.Items.KNIVES)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        InteractionResult result = this.takeServing(level, pos, state, player, hand);
        return result.consumesAction() ? ItemInteractionResult.SUCCESS : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (player.getItemInHand(InteractionHand.MAIN_HAND).is(ModTags.Items.KNIVES)) {
            // Already handled by useItemOn, avoid showing the hint twice.
            return InteractionResult.PASS;
        }
        return this.takeServing(level, pos, state, player, InteractionHand.MAIN_HAND);
    }

    protected InteractionResult takeServing(LevelAccessor level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
        int shouldOpen = state.getValue(COMPOSTING);
        boolean opened = state.getValue(OPENED);
        if (shouldOpen == 7 && player.getItemInHand(hand).is(ModTags.Items.KNIVES) && !opened) {
            level.setBlock(pos, state.setValue(OPENED, true), 3);
            if(level instanceof ServerLevel serverLevel){
                Direction direction = state.getValue(FACING).getOpposite();
                ItemStack itemS = new ItemStack(ItemRegistry.KIVIAK.get(), 3);
                ItemEntity itemE = new ItemEntity(serverLevel, pos.getX()+0.5, pos.getY()+0.3, pos.getZ()+0.5, itemS);
                itemE.setDefaultPickUpDelay();
                itemE.setDeltaMovement(direction.getStepX() * Math.random() + (0.6 * Math.random() - 0.3), 0.4 * Math.random() - 0.1, direction.getStepZ() * Math.random() + (0.6 * Math.random() - 0.3));
                level.addFreshEntity(itemE);
                level.playSound(null, pos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1.0f, 1.5f);
                serverLevel.sendParticles(AMParticleRegistry.SMELLY.get(), pos.getX()+0.5, pos.getY()+0.3, pos.getZ()+0.5, 30, 0.0D, 0.0D, 0.0D, 0.2D);
                serverLevel.sendParticles(ParticleTypes.CLOUD, pos.getX()+0.5, pos.getY()+0.3, pos.getZ()+0.5, 60, 0.0D, 0.0D, 0.0D, 0.12D);

                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));

                for(int i = 0; i < 10 + player.getRandom().nextInt(6); ++i) {
                    level.addParticle(AMParticleRegistry.SMELLY.get(), player.getRandomX(1.0), player.getRandomY(), player.getRandomZ(1.0), 0.0, 0.0, 0.0);
                }
                for (Mob nearby : level.getEntitiesOfClass(Mob.class, player.getBoundingBox().inflate(15.0))) {
                    if (!nearby.isAlliedTo(player) && !(player instanceof IHurtableMultipart)) {
                        nearby.setLastHurtByMob(player);
                        nearby.setTarget(player);
                    }
                }

            }

            return InteractionResult.SUCCESS;
        } else if (opened) {
            return InteractionResult.PASS;
        } else {
            player.displayClientMessage(Component.translatable("alexsmobsdelight.jei.kiviak_decomposition.tips"), true);
            return InteractionResult.PASS;
        }
    }

    public VoxelShape getShape(BlockState p_51787_, BlockGetter p_51788_, BlockPos p_51789_, CollisionContext p_51790_) {
        return switch (p_51787_.getValue(FACING)) {
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            case EAST -> SHAPE_E;
            default -> SHAPE_N;
        };
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
   }
}
