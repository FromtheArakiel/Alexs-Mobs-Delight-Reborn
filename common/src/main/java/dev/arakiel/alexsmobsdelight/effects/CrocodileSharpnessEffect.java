package dev.arakiel.alexsmobsdelight.effects;

import dev.arakiel.alexsmobsdelight.init.EffectRegistry;
import dev.arakiel.alexsmobsdelight.init.AMDHolders;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CrocodileSharpnessEffect extends MobEffect {
    public CrocodileSharpnessEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.hasEffect(AMDHolders.effect(EffectRegistry.CROCODILE_CRUSH))
                && entity.hasEffect(AMDHolders.effect(EffectRegistry.CROCODILE_TOUGHNESS))
                && entity.hasEffect(AMDHolders.effect(EffectRegistry.CROCODILE_HACKSAW))){
            entity.addEffect(new MobEffectInstance(AMDHolders.effect(EffectRegistry.CROCODILE_DEATH_ROLL), 1800, amplifier));
            entity.removeEffect(AMDHolders.effect(EffectRegistry.CROCODILE_SHARPNESS));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration > 0;
    }
}
