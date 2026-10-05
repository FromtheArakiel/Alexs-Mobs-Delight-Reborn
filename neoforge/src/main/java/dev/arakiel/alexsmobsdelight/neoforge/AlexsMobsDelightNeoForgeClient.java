package dev.arakiel.alexsmobsdelight.neoforge;

import com.github.alexthe666.alexsmobs.client.render.RenderCrimsonMosquito;
import dev.arakiel.alexsmobsdelight.AlexsMobsDelight;
import dev.arakiel.alexsmobsdelight.client.renderer.AMDItemstackRenderer;
import dev.arakiel.alexsmobsdelight.client.renderer.SuperSeagullRenderer;
import dev.arakiel.alexsmobsdelight.client.renderer.ThrownDartRenderer;
import dev.arakiel.alexsmobsdelight.client.renderer.ThrownPointedItemRenderer;
import dev.arakiel.alexsmobsdelight.events.ClientTickEvents;
import dev.arakiel.alexsmobsdelight.events.TooltipEvents;
import dev.arakiel.alexsmobsdelight.init.BlockRegistry;
import dev.arakiel.alexsmobsdelight.init.EntityTypeRegistry;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

/**
 * NeoForge client setup: entity renderers, render layers, the dimensional food renderer and the
 * Architectury client events.
 */
@EventBusSubscriber(modid = AlexsMobsDelight.MOD_ID, value = Dist.CLIENT)
public final class AlexsMobsDelightNeoForgeClient {
    private static final AMDItemstackRenderer DIMENSIONAL_RENDERER = new AMDItemstackRenderer();

    private AlexsMobsDelightNeoForgeClient() {
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_DART.get(), ThrownDartRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BANANA.get(), ThrownPointedItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.SUPER_SEAGULL.get(), SuperSeagullRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.SUPER_MOSQUITO.get(), RenderCrimsonMosquito::new);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return DIMENSIONAL_RENDERER;
            }
        }, ItemRegistry.DIMENSIONAL_FOOD.get());
    }

    @SubscribeEvent
    // NeoForge deprecated ItemBlockRenderTypes#setRenderLayer in favour of the per-model "render_type" JSON
    // entry; the mod keeps the blanket cutout layer (mirroring the Fabric BlockRenderLayerMap call).
    @SuppressWarnings("deprecation")
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (RegistrySupplier<Block> block : BlockRegistry.BLOCKS) {
                ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
            }
        });
        TooltipEvents.register();
        ClientTickEvents.register();
    }
}
