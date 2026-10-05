package dev.arakiel.alexsmobsdelight.events;

import java.util.List;

import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Villager trades of the mod.
 *
 * <p>Fabric and NeoForge expose different trade events, so only the data lives in common code and
 * each platform registers it (see the platform entry points).</p>
 */
public final class Trades {
    private Trades() {
    }

    /** Fisherman level 3 and 5 offers. */
    public static List<VillagerTrades.ItemListing> fishermanLevel3() {
        return List.of(
                offer(ItemRegistry.MANTIS_SHRIMP_TAIL_LIME.get(), 8, 12, 10),
                offer(ItemRegistry.MANTIS_SHRIMP_TAIL_RED.get(), 8, 12, 10),
                offer(ItemRegistry.MANTIS_SHRIMP_TAIL_GREEN.get(), 8, 12, 10),
                offer(ItemRegistry.MANTIS_SHRIMP_TAIL_WHITE.get(), 8, 12, 10)
        );
    }

    public static List<VillagerTrades.ItemListing> fishermanLevel5() {
        return List.of(
                offer(ItemRegistry.MANTIS_SHRIMP_SHOVEL.get(), 24, 3, 30),
                offer(ItemRegistry.MANTIS_SHRIMP_SCYTHE.get(), 24, 3, 30),
                offer(ItemRegistry.MANTIS_SHRIMP_HAMMER.get(), 24, 3, 30),
                offer(ItemRegistry.MANTIS_SHRIMP_AXE.get(), 24, 3, 30)
        );
    }

    private static VillagerTrades.ItemListing offer(net.minecraft.world.item.Item item, int emeralds,
                                                    int maxUses, int villagerXp) {
        // The vanilla listing classes are private in 1.21, so the offer is built directly.
        return (trader, random) -> new net.minecraft.world.item.trading.MerchantOffer(
                new net.minecraft.world.item.trading.ItemCost(Items.EMERALD, emeralds),
                new ItemStack(item), maxUses, villagerXp, 0.2F);
    }
}
