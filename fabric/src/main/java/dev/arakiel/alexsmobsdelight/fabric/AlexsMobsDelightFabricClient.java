package dev.arakiel.alexsmobsdelight.fabric;

import com.github.alexthe666.alexsmobs.client.render.RenderCrimsonMosquito;
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
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

/**
 * Fabric client entry point: entity renderers and the banana bow item properties.
 *
 * <p>The old {@code PropertyRegistry} and {@code RendererRegistry} were Forge mod bus subscribers;
 * on Fabric the same registrations happen here.</p>
 */
public final class AlexsMobsDelightFabricClient implements ClientModInitializer {
    private static final AMDItemstackRenderer DIMENSIONAL_RENDERER = new AMDItemstackRenderer();

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityTypeRegistry.THROWN_DART.get(), ThrownDartRenderer::new);
        EntityRendererRegistry.register(EntityTypeRegistry.THROWN_BANANA.get(), ThrownPointedItemRenderer::new);
        EntityRendererRegistry.register(EntityTypeRegistry.SUPER_SEAGULL.get(), SuperSeagullRenderer::new);
        EntityRendererRegistry.register(EntityTypeRegistry.SUPER_MOSQUITO.get(), RenderCrimsonMosquito::new);

        // Every block of the mod renders in the cutout layer, like the 1.20.1 sources did.
        for (RegistrySupplier<Block> block : BlockRegistry.BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(block.get(), RenderType.cutout());
        }

        // The dimensional food is drawn by a custom renderer, its model is a builtin/entity model.
        BuiltinItemRendererRegistry.INSTANCE.register(ItemRegistry.DIMENSIONAL_FOOD.get(),
                (stack, mode, matrices, vertexConsumers, light, overlay) ->
                        DIMENSIONAL_RENDERER.renderByItem(stack, mode, matrices, vertexConsumers, light, overlay));

        TooltipEvents.register();
        ClientTickEvents.register();

        ItemProperties.register(ItemRegistry.BANANA_BOW.get(),
                ResourceLocation.withDefaultNamespace("pull"), (stack, level, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }
                    return entity.getUseItem() != stack ? 0.0F
                            : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
                });
        ItemProperties.register(ItemRegistry.BANANA_BOW.get(),
                ResourceLocation.withDefaultNamespace("pulling"), (stack, level, entity, seed) ->
                        entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }
}
