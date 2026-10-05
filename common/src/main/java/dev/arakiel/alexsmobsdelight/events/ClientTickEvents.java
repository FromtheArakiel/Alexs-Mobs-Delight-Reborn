package dev.arakiel.alexsmobsdelight.events;

import dev.arakiel.alexsmobsdelight.init.AMDHolders;
import dev.arakiel.alexsmobsdelight.init.EffectRegistry;
import dev.arakiel.alexsmobsdelight.init.NetworkRegistry;
import dev.arakiel.alexsmobsdelight.packet.WingBoostC2S;
import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

/**
 * Sends the "fluttering" wing boost when the player presses jump while gliding.
 *
 * <p>Replaces the Forge client tick subscriber.</p>
 */
public final class ClientTickEvents {
    private static boolean lastBoostPressed;

    private ClientTickEvents() {
    }

    public static void register() {
        ClientTickEvent.CLIENT_POST.register(ClientTickEvents::onClientTick);
    }

    private static void onClientTick(Minecraft client) {
        LocalPlayer player = client.player;
        if (player == null) {
            return;
        }
        boolean boost = client.options.keyJump.isDown();
        if (player.isFallFlying() && player.hasEffect(AMDHolders.effect(EffectRegistry.FLUTTERING)) && boost && !lastBoostPressed) {
            NetworkRegistry.sendToServer(new WingBoostC2S());
        }
        lastBoostPressed = boost;
    }
}
