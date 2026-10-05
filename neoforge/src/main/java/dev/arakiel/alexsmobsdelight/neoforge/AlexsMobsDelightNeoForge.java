package dev.arakiel.alexsmobsdelight.neoforge;

import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * NeoForge entry point.
 *
 * <p>NeoForge keeps Forge's global loot modifiers, so the sixteen "add another loot table" entries of
 * the 1.20.1 sources stay data driven: the json files live in
 * {@code data/alexsmobsdelight/loot_modifiers/} and are listed by
 * {@code data/neoforge/loot_modifiers/global_loot_modifiers.json}.</p>
 */
@Mod(AlexsMobsDelight.MOD_ID)
public final class AlexsMobsDelightNeoForge {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    AlexsMobsDelight.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_TABLE = LOOT_MODIFIERS.register(
            "add_loot_table", () -> NeoForgeAddTableLootModifier.CODEC.get());

    public AlexsMobsDelightNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        AlexsMobsDelight.init();
        LOOT_MODIFIERS.register(modEventBus);
    }
}
