package dev.arakiel.alexsmobsdelight.neoforge;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.entities.SuperMosquito;
import dev.arakiel.alexsmobsdelight.entities.SuperSeagull;
import dev.arakiel.alexsmobsdelight.events.Trades;
import dev.arakiel.alexsmobsdelight.init.EntityTypeRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.minecraft.world.entity.npc.VillagerProfession;

/** Mob attributes, the NeoForge way. */
@EventBusSubscriber(modid = AlexsMobsDelight.MOD_ID)
public final class NeoForgeEvents {
    private NeoForgeEvents() {
    }

    @SubscribeEvent
    public static void onEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityTypeRegistry.SUPER_SEAGULL.get(), SuperSeagull.bakeAttributes().build());
        event.put(EntityTypeRegistry.SUPER_MOSQUITO.get(), SuperMosquito.bakeAttributes().build());
    }

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FISHERMAN) {
            event.getTrades().get(3).addAll(Trades.fishermanLevel3());
            event.getTrades().get(5).addAll(Trades.fishermanLevel5());
        }
    }
}
