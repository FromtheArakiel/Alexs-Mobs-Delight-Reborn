package dev.arakiel.alexsmobsdelight.effects;

import java.util.Iterator;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

/**
 * Frost walker style effect that turns water into crystallized banana slug mucus.
 *
 * <p>The 1.20.1 sources asked Forge whether the placement was allowed before setting the block; with
 * no loader specific block place event in common code the placement always happens, which matches
 * the vanilla frost walker behaviour.</p>
 */
public class CrystallizeWalkerEffect extends MobEffect {
    public CrystallizeWalkerEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration > 0;
    }

    public static void onEntityMoved(LivingEntity entity, Level level, BlockPos origin, int amplifier) {
        if (!entity.onGround()) {
            return;
        }
        BlockState mucus = AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get().defaultBlockState();
        int radius = Math.min(16, 2 + amplifier);
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        Iterator<BlockPos> iterator = BlockPos.betweenClosed(origin.offset(-radius, -1, -radius),
                origin.offset(radius, -1, radius)).iterator();

        while (iterator.hasNext()) {
            BlockPos pos = iterator.next();
            if (!pos.closerToCenterThan(entity.position(), radius)) {
                continue;
            }
            cursor.set(pos.getX(), pos.getY() + 1, pos.getZ());
            if (!level.getBlockState(cursor).isAir()) {
                continue;
            }
            BlockState below = level.getBlockState(pos);
            if (below.getBlock() instanceof BucketPickup && mucus.canSurvive(level, pos)
                    && level.isUnobstructed(mucus, pos, CollisionContext.empty())) {
                level.setBlockAndUpdate(pos, mucus);
                level.scheduleTick(pos, AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get(),
                        Mth.nextInt(entity.getRandom(), 60, 120));
            }
        }
    }
}
