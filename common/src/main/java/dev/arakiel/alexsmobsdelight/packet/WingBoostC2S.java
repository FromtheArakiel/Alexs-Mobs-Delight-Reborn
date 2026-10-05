package dev.arakiel.alexsmobsdelight.packet;

import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.architectury.networking.NetworkManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;

/**
 * Client to server "fluttering" boost, sent while the effect is active and the player is gliding.
 *
 * <p>1.21 packets are {@link CustomPacketPayload}s; Architectury registers the type and the codec on
 * both loaders, so no loader specific channel code is needed.</p>
 */
public record WingBoostC2S() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<WingBoostC2S> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MOD_ID, "wing_boost"));
    public static final StreamCodec<RegistryFriendlyByteBuf, WingBoostC2S> CODEC =
            StreamCodec.unit(new WingBoostC2S());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(WingBoostC2S payload, NetworkManager.PacketContext context) {
        if (!(context.getPlayer() instanceof ServerPlayer player)) {
            return;
        }
        context.queue(() -> {
            if (player.getFallFlyingTicks() > 10 && player.getFoodData().getFoodLevel() > 6) {
                player.setDeltaMovement(player.getLookAngle().x * 1.5F, player.getLookAngle().y * 1.5F,
                        player.getLookAngle().z * 1.5F);
                player.hurtMarked = true;
                if (!player.getAbilities().instabuild) {
                    player.getFoodData().addExhaustion(4.0F);
                }
                player.level().playSound(null, player, AMSoundRegistry.TARANTULA_HAWK_WING.get(),
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        });
    }
}
