package dev.arakiel.alexsmobsdelight;

import dev.arakiel.alexsmobsdelight.config.Config;
import dev.arakiel.alexsmobsdelight.events.CombatEvents;
import dev.arakiel.alexsmobsdelight.events.InteractEvents;
import dev.arakiel.alexsmobsdelight.init.BlockRegistry;
import dev.arakiel.alexsmobsdelight.init.CriticalTriggerRegistry;
import dev.arakiel.alexsmobsdelight.init.EffectRegistry;
import dev.arakiel.alexsmobsdelight.init.EntityTypeRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import dev.arakiel.alexsmobsdelight.init.NetworkRegistry;
import dev.arakiel.alexsmobsdelight.init.RecipeRegistry;
import dev.arakiel.alexsmobsdelight.init.TabRegistry;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common entry point of Alex's Mobs Delight.
 *
 * <p>The mod id, the asset namespace and the data namespace are all {@code alexsmobsdelight}.
 * Everything that is not loader specific lives here; the platform projects only wire up their own
 * entry points.</p>
 */
public final class AlexsMobsDelight {
    public static final String MOD_ID = "alexsmobsdelight";
    public static final String MOD_NAME = "Alex's Mobs Delight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private AlexsMobsDelight() {
    }

    /** A resource location inside the mod's namespace. */
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    /** Registers every content holder. Runs once per loader from the platform entry points. */
    public static void init() {
        Config.load();
        CriticalTriggerRegistry.register();
        EffectRegistry.register();
        BlockRegistry.register();
        ItemRegistry.register();
        EntityTypeRegistry.register();
        RecipeRegistry.register();
        TabRegistry.register();
        NetworkRegistry.register();
        CombatEvents.register();
        InteractEvents.register();
    }
}
