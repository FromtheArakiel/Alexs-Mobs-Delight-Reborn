package dev.arakiel.alexsmobsdelight.blocks;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;

public class MaggotFarmBlock extends Block implements WorldlyContainerHolder {
    public static final IntegerProperty LEVEL;
    public static final Object2FloatMap<ItemLike> COMPOSTABLES;
    private static final VoxelShape OUTER_SHAPE;
    private static final VoxelShape[] SHAPES;

    public static void bootStrap() {
        COMPOSTABLES.defaultReturnValue(-1.0F);
        float $$0 = 0.3F;
        float $$1 = 0.5F;
        float $$2 = 0.65F;
        float $$3 = 0.85F;
        float $$4 = 1.0F;
        add($$0, Items.BONE_MEAL);
        add($$0, Items.SPIDER_EYE);

        add($$1, Items.BONE);

        add($$2, Items.COD);
        add($$2, Items.SALMON);
        add($$2, Items.TROPICAL_FISH);
        add($$2, ItemRegistry.RAW_DEVILS_HOLE_PUPFISH.get());
        add($$2, ItemRegistry.RAW_MUDSKIPPER.get());
        add($$2, AMItemRegistry.FLYING_FISH.get());
        add($$2, AMItemRegistry.RAW_CATFISH.get());
        add($$2, AMItemRegistry.BLOBFISH.get());
        add($$2, AMItemRegistry.COSMIC_COD.get());
        add($$2, AMItemRegistry.FISH_BONES.get());

        add($$3, ModItems.ROTTEN_TOMATO.get());
        add($$3, AMBlockRegistry.BANANA_PEEL.get());
        add($$3, Items.ROTTEN_FLESH);

        add($$4, ModItems.ORGANIC_COMPOST.get());
    }

    private static void add(float p_51921_, ItemLike p_51922_) {
        COMPOSTABLES.put(p_51922_.asItem(), p_51921_);
    }

    public MaggotFarmBlock(BlockBehaviour.Properties p_51919_) {
        super(p_51919_);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LEVEL, 0));
    }

    public static void handleFill(Level p_51924_, BlockPos p_51925_, boolean p_51926_) {
        BlockState $$3 = p_51924_.getBlockState(p_51925_);
        p_51924_.playLocalSound(p_51925_, p_51926_ ? SoundEvents.COMPOSTER_FILL_SUCCESS : SoundEvents.COMPOSTER_FILL, SoundSource.BLOCKS, 1.0F, 1.0F, false);
        double $$4 = $$3.getShape(p_51924_, p_51925_).max(Axis.Y, 0.5, 0.5) + 0.03125;
        double $$5 = 0.13124999403953552;
        double $$6 = 0.737500011920929;
        RandomSource $$7 = p_51924_.getRandom();

        for(int $$8 = 0; $$8 < 10; ++$$8) {
            double $$9 = $$7.nextGaussian() * 0.02;
            double $$10 = $$7.nextGaussian() * 0.02;
            double $$11 = $$7.nextGaussian() * 0.02;
            p_51924_.addParticle(ParticleTypes.COMPOSTER, (double)p_51925_.getX() + 0.13124999403953552 + 0.737500011920929 * (double)$$7.nextFloat(), (double)p_51925_.getY() + $$4 + (double)$$7.nextFloat() * (1.0 - $$4), (double)p_51925_.getZ() + 0.13124999403953552 + 0.737500011920929 * (double)$$7.nextFloat(), $$9, $$10, $$11);
        }

    }

    public VoxelShape getShape(BlockState p_51973_, BlockGetter p_51974_, BlockPos p_51975_, CollisionContext p_51976_) {
        return SHAPES[(Integer)p_51973_.getValue(LEVEL)];
    }

    public VoxelShape getInteractionShape(BlockState p_51969_, BlockGetter p_51970_, BlockPos p_51971_) {
        return OUTER_SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState p_51990_, BlockGetter p_51991_, BlockPos p_51992_, CollisionContext p_51993_) {
        return SHAPES[0];
    }

    public void onPlace(BlockState p_51978_, Level p_51979_, BlockPos p_51980_, BlockState p_51981_, boolean p_51982_) {
        if ((Integer)p_51978_.getValue(LEVEL) == 7) {
            p_51979_.scheduleTick(p_51980_, p_51978_.getBlock(), 20);
        }

    }

    /** 1.21 replaced {@code Block#use} with {@code useItemOn}, which returns an {@link ItemInteractionResult}. */
    @Override
    protected ItemInteractionResult useItemOn(ItemStack p_51953_, BlockState p_51949_, Level p_51950_, BlockPos p_51951_, Player p_51952_, InteractionHand hand, BlockHitResult p_51954_) {
        int $$6 = (Integer)p_51949_.getValue(LEVEL);
        ItemStack $$7 = p_51952_.getItemInHand(hand);
        if ($$6 < 8 && COMPOSTABLES.containsKey($$7.getItem())) {
            if ($$6 < 7 && !p_51950_.isClientSide) {
                BlockState $$8 = addItem(p_51952_, p_51949_, p_51950_, p_51951_, $$7);
                p_51950_.levelEvent(1500, p_51951_, p_51949_ != $$8 ? 1 : 0);
                p_51952_.awardStat(Stats.ITEM_USED.get($$7.getItem()));
                if (!p_51952_.getAbilities().instabuild) {
                    $$7.shrink(1);
                }
            }

            return ItemInteractionResult.sidedSuccess(p_51950_.isClientSide);
        } else if ($$6 == 8) {
            extractProduce(p_51952_, p_51949_, p_51950_, p_51951_);
            return ItemInteractionResult.sidedSuccess(p_51950_.isClientSide);
        } else {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    public static BlockState insertItem(Entity p_270919_, BlockState p_270087_, ServerLevel p_270284_, ItemStack p_270253_, BlockPos p_270678_) {
        int $$5 = (Integer)p_270087_.getValue(LEVEL);
        if ($$5 < 7 && COMPOSTABLES.containsKey(p_270253_.getItem())) {
            BlockState $$6 = addItem(p_270919_, p_270087_, p_270284_, p_270678_, p_270253_);
            p_270253_.shrink(1);
            return $$6;
        } else {
            return p_270087_;
        }
    }

    public static BlockState extractProduce(Entity p_270467_, BlockState p_51999_, Level p_52000_, BlockPos p_52001_) {
        if (!p_52000_.isClientSide) {
            Vec3 $$4 = Vec3.atLowerCornerWithOffset(p_52001_, 0.5, 1.01, 0.5).offsetRandom(p_52000_.random, 0.7F);
            ItemEntity $$5 = new ItemEntity(p_52000_, $$4.x(), $$4.y(), $$4.z(), new ItemStack(AMItemRegistry.MAGGOT.get(), 3));
            $$5.setDefaultPickUpDelay();
            p_52000_.addFreshEntity($$5);
        }

        BlockState $$6 = empty(p_270467_, p_51999_, p_52000_, p_52001_);
        p_52000_.playSound((Player)null, p_52001_, SoundEvents.COMPOSTER_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        return $$6;
    }

    static BlockState empty(Entity p_270236_, BlockState p_270873_, LevelAccessor p_270963_, BlockPos p_270211_) {
        BlockState $$4 = (BlockState)p_270873_.setValue(LEVEL, 0);
        p_270963_.setBlock(p_270211_, $$4, 3);
        p_270963_.gameEvent(GameEvent.BLOCK_CHANGE, p_270211_, Context.of(p_270236_, $$4));
        return $$4;
    }

    static BlockState addItem(Entity p_270464_, BlockState p_270603_, LevelAccessor p_270151_, BlockPos p_270547_, ItemStack p_270354_) {
        int $$5 = (Integer)p_270603_.getValue(LEVEL);
        float $$6 = COMPOSTABLES.getFloat(p_270354_.getItem());
        if (($$5 != 0 || !($$6 > 0.0F)) && !(p_270151_.getRandom().nextDouble() < (double)$$6)) {
            return p_270603_;
        } else {
            int $$7 = $$5 + 1;
            BlockState $$8 = (BlockState)p_270603_.setValue(LEVEL, $$7);
            p_270151_.setBlock(p_270547_, $$8, 3);
            p_270151_.gameEvent(GameEvent.BLOCK_CHANGE, p_270547_, Context.of(p_270464_, $$8));
            if ($$7 == 7) {
                p_270151_.scheduleTick(p_270547_, p_270603_.getBlock(), 20);
            }

            return $$8;
        }
    }

    public void tick(BlockState p_221015_, ServerLevel p_221016_, BlockPos p_221017_, RandomSource p_221018_) {
        if ((Integer)p_221015_.getValue(LEVEL) == 7) {
            p_221016_.setBlock(p_221017_, (BlockState)p_221015_.cycle(LEVEL), 3);
            p_221016_.playSound((Player)null, p_221017_, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

    }

    public boolean hasAnalogOutputSignal(BlockState p_51928_) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState p_51945_, Level p_51946_, BlockPos p_51947_) {
        return (Integer)p_51945_.getValue(LEVEL);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51965_) {
        p_51965_.add(LEVEL);
    }

    @Override
    protected boolean isPathfindable(BlockState p_51940_, PathComputationType p_51943_) {
        return false;
    }

    public WorldlyContainer getContainer(BlockState p_51956_, LevelAccessor p_51957_, BlockPos p_51958_) {
        int $$3 = (Integer)p_51956_.getValue(LEVEL);
        if ($$3 == 8) {
            return new OutputContainer(p_51956_, p_51957_, p_51958_, new ItemStack(AMItemRegistry.MAGGOT.get(), 3));
        } else {
            return (WorldlyContainer)($$3 < 7 ? new InputContainer(p_51956_, p_51957_, p_51958_) : new EmptyContainer());
        }
    }

    static {
        LEVEL = BlockStateProperties.LEVEL_COMPOSTER;
        COMPOSTABLES = new Object2FloatOpenHashMap<>();
        OUTER_SHAPE = Shapes.block();
        SHAPES = (VoxelShape[])Util.make(new VoxelShape[9], (p_51967_) -> {
            for(int $$1 = 0; $$1 < 8; ++$$1) {
                p_51967_[$$1] = Shapes.join(OUTER_SHAPE, Block.box(2.0, (double)Math.max(2, 1 + $$1 * 2), 2.0, 14.0, 16.0, 14.0), BooleanOp.ONLY_FIRST);
            }

            p_51967_[8] = p_51967_[7];
        });
    }

    static class OutputContainer extends SimpleContainer implements WorldlyContainer {
        private final BlockState state;
        private final LevelAccessor level;
        private final BlockPos pos;
        private boolean changed;

        public OutputContainer(BlockState p_52042_, LevelAccessor p_52043_, BlockPos p_52044_, ItemStack p_52045_) {
            super(new ItemStack[]{p_52045_});
            this.state = p_52042_;
            this.level = p_52043_;
            this.pos = p_52044_;
        }

        public int getMaxStackSize() {
            return 1;
        }

        public int[] getSlotsForFace(Direction p_52053_) {
            return p_52053_ == Direction.DOWN ? new int[]{0} : new int[0];
        }

        public boolean canPlaceItemThroughFace(int p_52049_, ItemStack p_52050_, Direction p_52051_) {
            return false;
        }

        public boolean canTakeItemThroughFace(int p_52055_, ItemStack p_52056_, Direction p_52057_) {
            return !this.changed && p_52057_ == Direction.DOWN && p_52056_.is(AMItemRegistry.MAGGOT.get());
        }

        public void setChanged() {
            MaggotFarmBlock.empty((Entity)null, this.state, this.level, this.pos);
            this.changed = true;
        }
    }

    static class InputContainer extends SimpleContainer implements WorldlyContainer {
        private final BlockState state;
        private final LevelAccessor level;
        private final BlockPos pos;
        private boolean changed;

        public InputContainer(BlockState p_52022_, LevelAccessor p_52023_, BlockPos p_52024_) {
            super(1);
            this.state = p_52022_;
            this.level = p_52023_;
            this.pos = p_52024_;
        }

        public int getMaxStackSize() {
            return 1;
        }

        public int[] getSlotsForFace(Direction p_52032_) {
            return p_52032_ == Direction.UP ? new int[]{0} : new int[0];
        }

        public boolean canPlaceItemThroughFace(int p_52028_, ItemStack p_52029_, Direction p_52030_) {
            return !this.changed && p_52030_ == Direction.UP && MaggotFarmBlock.COMPOSTABLES.containsKey(p_52029_.getItem());
        }

        public boolean canTakeItemThroughFace(int p_52034_, ItemStack p_52035_, Direction p_52036_) {
            return false;
        }

        public void setChanged() {
            ItemStack $$0 = this.getItem(0);
            if (!$$0.isEmpty()) {
                this.changed = true;
                BlockState $$1 = MaggotFarmBlock.addItem((Entity)null, this.state, this.level, this.pos, $$0);
                this.level.levelEvent(1500, this.pos, $$1 != this.state ? 1 : 0);
                this.removeItemNoUpdate(0);
            }

        }
    }

    static class EmptyContainer extends SimpleContainer implements WorldlyContainer {
        public EmptyContainer() {
            super(0);
        }

        public int[] getSlotsForFace(Direction p_52012_) {
            return new int[0];
        }

        public boolean canPlaceItemThroughFace(int p_52008_, ItemStack p_52009_, Direction p_52010_) {
            return false;
        }

        public boolean canTakeItemThroughFace(int p_52014_, ItemStack p_52015_, Direction p_52016_) {
            return false;
        }
    }
}
