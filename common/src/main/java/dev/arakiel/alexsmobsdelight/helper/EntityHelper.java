package dev.arakiel.alexsmobsdelight.helper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class EntityHelper {
    public static double getDistance(Vec3 vec3a, Vec3 vec3b){
        return Math.sqrt(Math.pow(vec3a.x - vec3b.x, 2) + Math.pow(vec3a.y - vec3b.y, 2) + Math.pow(vec3a.z - vec3b.z, 2));
    }

    public static Vec3 getVec3(Entity entity){
        return new Vec3(entity.getX(), entity.getY(), entity.getZ());
    }

    public static Player getClosestPlayer(Level level, Vec3 center, float range){
        Player player = null;
        double distance = range * 2;
        AABB aabb = new AABB(center, center).inflate(range);
        List<Player> targets = level.getEntitiesOfClass(Player.class, aabb);
        for (Player target : targets) {
            double d = EntityHelper.getDistance(EntityHelper.getVec3(target), center);
            if(d < distance){
                player = target;
                distance = d;
            }
        }
        return player;
    }
}
