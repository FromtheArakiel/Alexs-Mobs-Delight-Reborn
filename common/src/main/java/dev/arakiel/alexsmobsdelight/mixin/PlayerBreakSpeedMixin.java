package dev.arakiel.alexsmobsdelight.mixin;

import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Whale tooth pickaxe has aqua affinity built in.
 *
 * <p>Replaces Forge's {@code PlayerEvent.BreakSpeed}.</p>
 */
@Mixin(Player.class)
public abstract class PlayerBreakSpeedMixin {
    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void amd$whaleToothAquaAffinity(BlockState state, CallbackInfoReturnable<Float> callback) {
        Player player = (Player) (Object) this;
        if (player.getMainHandItem().is(ItemRegistry.WHALE_TOOTH_PICKAXE.get())
                && player.isEyeInFluid(FluidTags.WATER)
                && !hasAquaAffinity(player)) {
            callback.setReturnValue(callback.getReturnValue() * 5.0F);
        }
    }

    /**
     * 1.21 removed {@code EnchantmentHelper.hasAquaAffinity}, so the helmet enchantment is read from
     * the enchantment component directly.
     */
    private static boolean hasAquaAffinity(Player player) {
        Holder<Enchantment> aquaAffinity = player.level().registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(Enchantments.AQUA_AFFINITY);
        return player.getItemBySlot(EquipmentSlot.HEAD).getEnchantments().getLevel(aquaAffinity) > 0;
    }
}
