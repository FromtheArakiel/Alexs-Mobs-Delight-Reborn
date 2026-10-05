package dev.arakiel.alexsmobsdelight.items;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import dev.arakiel.alexsmobsdelight.config.Config;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import java.util.List;

public class TigerCakeRoll extends ConsumableItem {
    public TigerCakeRoll(Properties properties) {
        super(properties, true, true);
    }

    public ItemStack finishUsingItem(ItemStack st, Level level, LivingEntity living) {
        final Vec3 center = new Vec3(living.getX(), living.getY(), living.getZ());
        AABB aabb = new AABB(center, center).inflate(Config.TIGER_SUMMON_RANGE.get());
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, aabb);
        for (LivingEntity target : targets) {
            if(target instanceof EntityTiger){
                return super.finishUsingItem(st, level, living);
            }
        }
        for (LivingEntity target : targets) {
            if(target instanceof Mob mob && mob.getTarget() != null) {
                if(mob.getTarget().is(living)){
                    EntityTiger tiger = (EntityTiger)((EntityType<?>) AMEntityRegistry.TIGER.get()).create(level);
                    tiger.moveTo(mob.getX(), mob.getY(), mob.getZ());
                    if(level instanceof ServerLevel serverLevel){
                        serverLevel.sendParticles(ParticleTypes.CLOUD,mob.getX(), mob.getY(), mob.getZ(), 200, 1.0D, 1.0D, 1.0D, 0.0D);
                    }
                    tiger.setTarget(mob);
                    tiger.setLastHurtByMob(mob);
                    level.addFreshEntity(tiger);
                    break;
                }
            }
        }
        return super.finishUsingItem(st, level, living);
    }
}
