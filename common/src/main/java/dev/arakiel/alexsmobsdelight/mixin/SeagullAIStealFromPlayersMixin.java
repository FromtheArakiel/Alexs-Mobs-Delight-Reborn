package dev.arakiel.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.ai.SeagullAIStealFromPlayers;
import dev.arakiel.alexsmobsdelight.init.AMDHolders;
import dev.arakiel.alexsmobsdelight.init.EffectRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SeagullAIStealFromPlayers.class, remap = false)
public class SeagullAIStealFromPlayersMixin {

    @Inject(
            method = "isBlacklisted",
            at = @At("HEAD"),
            cancellable = true)

    private void amd$isBlacklisted(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.is(ItemRegistry.KIVIAK.get())){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "hasFoods",
            at = @At("HEAD"),
            cancellable = true)

    private void amd$seagullEffect(Player player, CallbackInfoReturnable<Boolean> cir) {
        if(player.hasEffect(AMDHolders.effect(EffectRegistry.SEAGULL_ANOREXIA))){
            cir.setReturnValue(false);
        }
    }
}
