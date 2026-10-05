package dev.arakiel.alexsmobsdelight.mixin;

import dev.arakiel.alexsmobsdelight.init.AMDHolders;
import dev.arakiel.alexsmobsdelight.init.EffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.registry.ModEffects;

/**
 * Nourish Supplement: applying Nourishment again adds its duration to the running effect.
 *
 * <p>Forge had {@code MobEffectEvent.Applicable} for this; neither Fabric nor Architectury has an
 * equivalent event, so the check lives on {@code LivingEntity#addEffect} with a re-entrancy guard.</p>
 */
@Mixin(LivingEntity.class)
public abstract class NourishSupplementMixin {
    @Unique
    private static final ThreadLocal<Boolean> AMD_EXTENDING = ThreadLocal.withInitial(() -> Boolean.FALSE);

    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;"
                    + "Lnet/minecraft/world/entity/Entity;)Z",
            at = @At("HEAD"), cancellable = true)
    private void amd$extendNourishment(MobEffectInstance instance, Entity source,
                                       CallbackInfoReturnable<Boolean> callback) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (instance.getEffect().value() != ModEffects.NOURISHMENT.value()
                || !entity.hasEffect(AMDHolders.effect(EffectRegistry.NOURISH_SUPPLEMENT))) {
            return;
        }
        MobEffectInstance running = entity.getEffect(ModEffects.NOURISHMENT);
        if (running == null || AMD_EXTENDING.get()) {
            return;
        }
        AMD_EXTENDING.set(Boolean.TRUE);
        try {
            entity.addEffect(new MobEffectInstance(ModEffects.NOURISHMENT,
                    running.getDuration() + instance.getDuration()));
        } finally {
            AMD_EXTENDING.set(Boolean.FALSE);
        }
        callback.setReturnValue(Boolean.TRUE);
    }
}
