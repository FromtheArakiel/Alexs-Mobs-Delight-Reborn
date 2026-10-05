package dev.arakiel.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntityFroststalker;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityFroststalker.class)
public interface FroststalkerAccessor {

    @Accessor("leader")
    LivingEntity amd$getLeader();
}
