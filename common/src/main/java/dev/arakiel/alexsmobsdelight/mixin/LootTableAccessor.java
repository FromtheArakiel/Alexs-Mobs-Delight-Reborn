package dev.arakiel.alexsmobsdelight.mixin;

import java.util.List;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/** Reads the pools of a loot table for the dimensional slicer's full drop. */
@Mixin(LootTable.class)
public interface LootTableAccessor {
    @Accessor("pools")
    List<LootPool> amd$getLootTablePools();
}
