package dev.arakiel.alexsmobsdelight.effects;

import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SeagullAnorexiaEffect extends MobEffect {
    public SeagullAnorexiaEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        AABB aabb = entity.getBoundingBox().inflate(16);
        List<EntitySeagull> list = entity.level().getEntitiesOfClass(EntitySeagull.class, aabb);
        if (!list.isEmpty()) {
            for (EntitySeagull seagull : list) {
                if(seagull.stealCooldown < 200) seagull.stealCooldown = 200;
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration > 0;
    }
}
