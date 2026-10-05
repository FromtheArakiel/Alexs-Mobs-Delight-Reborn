package dev.arakiel.alexsmobsdelight.client.renderer;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.arakiel.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.List;

public class AMDItemstackRenderer extends BlockEntityWithoutLevelRenderer {
    private static List<ItemStack> SHARDS_ITEM_RENDERER;

    public AMDItemstackRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    private static List<ItemStack> getShards() {
        if (SHARDS_ITEM_RENDERER == null || SHARDS_ITEM_RENDERER.isEmpty()) {
            SHARDS_ITEM_RENDERER = Util.make(Lists.newArrayList(), (list) -> {
                list.add(new ItemStack(Items.DIAMOND));
                list.add(new ItemStack(Items.EMERALD));
                list.add(new ItemStack(Items.GOLD_INGOT));
                list.add(new ItemStack(Items.LAPIS_LAZULI));
                list.add(new ItemStack(Items.REDSTONE));
                list.add(new ItemStack(Items.COAL));
                list.add(new ItemStack(Items.NETHERITE_INGOT));
                list.add(new ItemStack(Items.ENDER_PEARL));
            });
        }

        return SHARDS_ITEM_RENDERER;
    }

    public void renderByItem(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        int tick;
        Level level = Minecraft.getInstance().level;
        if (Minecraft.getInstance().player != null && !Minecraft.getInstance().isPaused()) {
            tick = Minecraft.getInstance().player.tickCount;
        } else {
            tick = 0;
        }

        int entityIndex;
        List mobIcons;
        if (itemStackIn.getItem() == ItemRegistry.DIMENSIONAL_FOOD.get()) {
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            float f = (float) tick;
            mobIcons = getShards();
            matrixStackIn.pushPose();
            if (transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
                matrixStackIn.translate(-0.2F, 0.0F, 0.0F);
                matrixStackIn.scale(1.3F, 1.3F, 1.3F);
                matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F));
                matrixStackIn.mulPose(Axis.XP.rotationDegrees(60.0F));
            }

            for(entityIndex = 0; entityIndex < mobIcons.size(); ++entityIndex) {
                matrixStackIn.pushPose();
                ItemStack shard = (ItemStack)mobIcons.get(entityIndex);
                matrixStackIn.translate((float)Math.sin((double)(f * 0.15F + (float)entityIndex * 1.0F)) * 0.035F, -((float)Math.cos((double)(f * 0.15F + (float)entityIndex * 1.0F))) * 0.035F, (float)Math.cos((double)(f * 0.15F + (float)entityIndex * 0.5F) + 1.5707963267948966) * 0.025F);
                Minecraft.getInstance().getItemRenderer().renderStatic(shard, transformType, transformType == ItemDisplayContext.GROUND ? combinedLightIn : 240, combinedOverlayIn, matrixStackIn, bufferIn, level, 0);
                matrixStackIn.popPose();
            }

            matrixStackIn.popPose();
        }
    }
}
