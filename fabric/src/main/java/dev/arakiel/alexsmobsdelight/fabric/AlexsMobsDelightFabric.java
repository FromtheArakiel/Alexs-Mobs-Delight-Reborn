package dev.arakiel.alexsmobsdelight.fabric;

import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.entities.SuperMosquito;
import dev.arakiel.alexsmobsdelight.entities.SuperSeagull;
import dev.arakiel.alexsmobsdelight.events.Trades;
import dev.arakiel.alexsmobsdelight.init.EntityTypeRegistry;
import dev.arakiel.alexsmobsdelight.loot.FabricLootTables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;

/** Fabric entry point. */
public final class AlexsMobsDelightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AlexsMobsDelight.init();

        FabricDefaultAttributeRegistry.register(EntityTypeRegistry.SUPER_SEAGULL.get(), SuperSeagull.bakeAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypeRegistry.SUPER_MOSQUITO.get(), SuperMosquito.bakeAttributes());

        FabricLootTables.register();
        FabricBiomeModifications.register();

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3,
                offers -> offers.addAll(Trades.fishermanLevel3()));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 5,
                offers -> offers.addAll(Trades.fishermanLevel5()));
    }
}
