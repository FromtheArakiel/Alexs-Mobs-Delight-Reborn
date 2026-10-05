package dev.arakiel.alexsmobsdelight.fabric;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * Fabric replacement for the NeoForge biome modifier json {@code add_end_cheese_ore.json}.
 *
 * <p>The NeoForge project keeps using the data driven {@code neoforge:add_features} modifier; Fabric
 * has no equivalent, so the same feature is injected into the end highlands here.</p>
 */
public final class FabricBiomeModifications {
    private static final ResourceKey<PlacedFeature> END_CHEESE_ORE =
            ResourceKey.create(Registries.PLACED_FEATURE, AlexsMobsDelight.id("end_cheese_ore_placed"));

    private FabricBiomeModifications() {
    }

    public static void register() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(net.minecraft.world.level.biome.Biomes.END_HIGHLANDS),
                GenerationStep.Decoration.UNDERGROUND_ORES, END_CHEESE_ORE);
    }
}
