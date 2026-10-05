package dev.arakiel.alexsmobsdelight.init;

import dev.arakiel.alexsmobsdelight.packet.WingBoostC2S;
import dev.architectury.networking.NetworkManager;

/** Packet registration. The channel itself is provided by Architectury. */
public final class NetworkRegistry {
    private NetworkRegistry() {
    }

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.c2s(), WingBoostC2S.TYPE, WingBoostC2S.CODEC,
                WingBoostC2S::handle);
    }

    public static void sendToServer(Object payload) {
        if (payload instanceof WingBoostC2S boost) {
            NetworkManager.sendToServer(boost);
        }
    }
}
