package dev.arakiel.alexsmobsdelight.mixin;

import dev.arakiel.alexsmobsdelight.effects.CrystallizeWalkerEffect;
import dev.arakiel.alexsmobsdelight.init.AMDHolders;
import dev.arakiel.alexsmobsdelight.init.EffectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(
            method = "checkAutoSpinAttack",
            at = @At("HEAD"),
            cancellable = true)

    private void amd$spinningWithoutAttackCheck(AABB p_21072_, AABB p_21073_, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasEffect(AMDHolders.effect(EffectRegistry.CROCODILE_DEATH_ROLL))){
            AABB aabb = p_21072_.minmax(p_21073_);
            List<Entity> list = entity.level().getEntities(entity, aabb);
            if (!list.isEmpty()) {
                for(int i = 0; i < list.size(); ++i) {
                    Entity target = (Entity)list.get(i);
                    if (target instanceof LivingEntity) {
                        ci.cancel();
                        break;
                    }
                }
            }
        }
    }

    @Inject(
            method = "onChangedBlock",
            at = @At("HEAD")
    )
    // 1.21 turned onChangedBlock into onChangedBlock(ServerLevel, BlockPos); the handler has to take
    // the target's parameters before the callback.
    private void amd$onChangeBlock(ServerLevel level, BlockPos pos, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasEffect(AMDHolders.effect(EffectRegistry.CRYSTALLIZE_WALKER))){
            int i = entity.getEffect(AMDHolders.effect(EffectRegistry.CRYSTALLIZE_WALKER)).getAmplifier() + 1;
            CrystallizeWalkerEffect.onEntityMoved(entity, level, pos, i);
        }
    }
}
