package dev.arakiel.alexsmobsdelight.mixin;

import dev.arakiel.alexsmobsdelight.events.CombatEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Applies the mod's combat effects and damage modifiers.
 *
 * <p>Replaces Forge's {@code LivingAttackEvent} and {@code LivingHurtEvent}: cancellation and side
 * effects run before the vanilla damage is calculated, the damage value itself is adjusted through
 * {@code ModifyVariable}.</p>
 */
@Mixin(LivingEntity.class)
public abstract class LivingHurtMixin {
    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            at = @At("HEAD"), cancellable = true)
    private void amd$onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> callback) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (CombatEvents.shouldCancelDamage(entity, source)
                || CombatEvents.applyHitEffects(entity, source, amount)) {
            callback.setReturnValue(Boolean.FALSE);
        }
    }

    @ModifyVariable(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            at = @At("HEAD"), argsOnly = true)
    private float amd$modifyDamage(float amount, DamageSource source) {
        return CombatEvents.modifyDamage((LivingEntity) (Object) this, source, amount);
    }
}
