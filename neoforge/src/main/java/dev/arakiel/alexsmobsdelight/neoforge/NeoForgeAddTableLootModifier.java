package dev.arakiel.alexsmobsdelight.neoforge;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;

/**
 * Adds the contents of another loot table to the loot table it is attached to.
 *
 * <p>Direct 1.21 equivalent of the old {@code ModAddLootModifier}; NeoForge already ships the
 * behaviour, only the id and the codec are ours.</p>
 */
public class NeoForgeAddTableLootModifier extends AddTableLootModifier {
    public static final Supplier<MapCodec<NeoForgeAddTableLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
                    .and(ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("lootTable")
                            .forGetter(modifier -> modifier.lootTable))
                    .apply(instance, NeoForgeAddTableLootModifier::new)));

    private final ResourceKey<LootTable> lootTable;

    public NeoForgeAddTableLootModifier(LootItemCondition[] conditions, ResourceKey<LootTable> lootTable) {
        super(conditions, lootTable);
        this.lootTable = lootTable;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
