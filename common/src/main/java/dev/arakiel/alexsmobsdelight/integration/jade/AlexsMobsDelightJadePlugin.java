package dev.arakiel.alexsmobsdelight.integration.jade;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.blocks.KiviakBlock;
import dev.arakiel.alexsmobsdelight.blocks.MaggotFarmBlock;
import dev.arakiel.alexsmobsdelight.blocks.SteamedStuffedCrocodileBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import vectorwing.farmersdelight.common.block.FeastBlock;

/**
 * Jade integration, shared by both loaders.
 *
 * <p>The structure mirrors Twilight Forest's {@code twilightforest.compat.jade.JadeCompat}: one
 * singleton provider per kind of block, registered on the client only. Fabric loads this class from
 * the {@code jade} entrypoint in {@code fabric.mod.json}; NeoForge discovers it through the
 * {@code @WailaPlugin} annotation. Jade is optional, so the class is never referenced from anywhere
 * else and the jar is not bundled.</p>
 */
@WailaPlugin(AlexsMobsDelight.MOD_ID)
public final class AlexsMobsDelightJadePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        // Jade resolves block providers through a class hierarchy lookup, so FeastBlock covers all of
        // our feast blocks.
        registration.registerBlockComponent(FeastServingsProvider.INSTANCE, FeastBlock.class);
        registration.registerBlockComponent(FeastServingsProvider.INSTANCE, SteamedStuffedCrocodileBlock.class);
        registration.registerBlockComponent(KiviakProvider.INSTANCE, KiviakBlock.class);
        registration.registerBlockComponent(MaggotFarmProvider.INSTANCE, MaggotFarmBlock.class);
    }
}
