package dev.arakiel.alexsmobsdelight.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CrocodileDeathRollEffect extends MobEffect {
    public CrocodileDeathRollEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.isAutoSpinAttack()){
            entity.invulnerableTime = 20;
            AABB aabb = entity.getBoundingBox().inflate(0.5);
            List<Entity> list = entity.level().getEntities(entity, aabb);
            if (!list.isEmpty()) {
                for (Entity value : list) {
                    if (value instanceof LivingEntity) {
                        if (entity instanceof Player player && player.getFoodData().getFoodLevel() > 6) {
                            player.attack(value);
                            player.getFoodData().addExhaustion(0.1f);
                        }
                        (value).setDeltaMovement(Vec3.ZERO);
                        break;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration > 0;
    }
}
