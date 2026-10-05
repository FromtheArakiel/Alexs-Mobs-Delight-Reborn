package dev.arakiel.alexsmobsdelight.loot;

import java.util.List;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

/**
 * Fabric replacement for the global loot modifiers of the 1.20.1 sources.
 *
 * <p>NeoForge still uses the data driven {@code neoforge:loot_modifiers} files that ship with
 * this mod; Fabric has no global loot modifier system, so the same entries are applied
 * through {@code LootTableEvents.MODIFY}.</p>
 */
public final class FabricLootTables {
    private record Addition(String target, String addition) {
    }

    private record ItemAddition(String target, String item, float chance) {
    }

    private static final List<Addition> ADDITIONS = List.of(
            new Addition("alexsmobs:entities/grizzly_bear", "alexsmobsdelight:bear_meats"), // add_bear_meats
            new Addition("minecraft:entities/polar_bear", "alexsmobsdelight:bear_meats_from_polar_bear"), // add_bear_meats_to_polar_bear
            new Addition("alexsmobs:entities/bunfungus", "alexsmobsdelight:bunfungus_meats"), // add_bunfungus_meats
            new Addition("minecraft:chests/abandoned_mineshaft", "alexsmobsdelight:ghostly_burger"), // add_burger_to_abandoned_mineshaft
            new Addition("alexsmobs:entities/centipede_head", "alexsmobsdelight:raw_centipede_body"), // add_centipede_body
            new Addition("alexsmobs:entities/cockroach", "alexsmobsdelight:cockroach"), // add_cockroach
            new Addition("alexsmobs:entities/crocodile", "alexsmobsdelight:crocodile_meats"), // add_crocodile_meats
            new Addition("alexsmobs:entities/emu", "alexsmobsdelight:raw_emu"), // add_emu
            new Addition("alexsmobs:entities/froststalker", "alexsmobsdelight:froststalker_meats"), // add_froststalker_meats
            new Addition("alexsmobs:entities/giant_squid", "alexsmobsdelight:giant_squid_tentacle"), // add_giant_squid
            new Addition("alexsmobs:entities/tarantula_hawk", "alexsmobsdelight:raw_tarantula_hawk_abdomen"), // add_hawk_abdomen
            new Addition("alexsmobs:entities/hummingbird", "alexsmobsdelight:raw_hummingbird"), // add_hummingbird
            new Addition("alexsmobs:entities/mimicube", "alexsmobsdelight:mimicream"), // add_mimicream
            new Addition("alexsmobs:entities/moose", "alexsmobsdelight:moose_antler"), // add_moose_antler
            new Addition("alexsmobs:entities/seagull", "alexsmobsdelight:seagull_meat"), // add_seagull_meats
            new Addition("alexsmobs:entities/skelewag", "alexsmobsdelight:skelewag_eye") // add_skelewag_eye
    );

    private static final List<ItemAddition> ITEM_ADDITIONS = List.of(
    );

    private FabricLootTables() {
    }

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            ResourceLocation id = key.location();
            if (!source.isBuiltin()) {
                return;
            }
            for (Addition addition : ADDITIONS) {
                if (id.equals(ResourceLocation.parse(addition.target()))) {
                    tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(
                            ResourceKey.create(Registries.LOOT_TABLE,
                                    ResourceLocation.parse(addition.addition())))));
                }
            }
            for (ItemAddition addition : ITEM_ADDITIONS) {
                if (id.equals(ResourceLocation.parse(addition.target()))) {
                    BuiltInRegistries.ITEM.getOptional(ResourceLocation.parse(addition.item()))
                            .ifPresent(item -> tableBuilder.withPool(LootPool.lootPool()
                                    .add(LootItem.lootTableItem(item))
                                    .when(LootItemRandomChanceCondition.randomChance(addition.chance()))));
                }
            }
        });
    }
}
