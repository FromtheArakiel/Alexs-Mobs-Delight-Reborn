package dev.arakiel.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntityDevilsHolePupfish;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import dev.arakiel.alexsmobsdelight.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDevilsHolePupfish.class)
public class EntityDevilsHolePupfishMixin{

    @Inject(method = "spawnBabiesWith", at = @At("TAIL"), remap = false)
    private void amd$breakBlockWhenSpawnBabies(EntityDevilsHolePupfish chasePartner, CallbackInfo ci) {
        if(!Config.PUPFISH_BREAK.get()) return;
        Level level = chasePartner.level();
        if(!level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) return;
        BlockPos pos = chasePartner.getOnPos().above();
        for(Direction dir : Direction.values()){
            BlockPos tempos = pos.relative(dir);
            if(level.getBlockState(tempos).is(AMTagRegistry.PUPFISH_EATABLES)){
                level.levelEvent(2001, tempos, Block.getId(level.getBlockState(tempos)));
                level.setBlock(tempos, Blocks.AIR.defaultBlockState(), 3);
                break;
            }
        }
    }
}
