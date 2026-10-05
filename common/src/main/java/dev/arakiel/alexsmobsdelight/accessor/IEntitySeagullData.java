package dev.arakiel.alexsmobsdelight.accessor;

import java.util.Map;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

/**
 * Data attached to Alex's Mobs seagulls by {@code EntitySeagullMixin}.
 *
 * <p>1.21 effect instances are keyed by {@code Holder<MobEffect>} instead of the effect object.</p>
 */
public interface IEntitySeagullData {
    boolean amd$getConsumedEternalFood();

    void amd$setConsumedEternalFood(boolean consumed);

    Map<Holder<MobEffect>, MobEffectInstance> amd$getEffects();

    void amd$setEffectMap(Map<Holder<MobEffect>, MobEffectInstance> effects);
}
