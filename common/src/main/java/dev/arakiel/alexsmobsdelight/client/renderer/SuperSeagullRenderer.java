package dev.arakiel.alexsmobsdelight.client.renderer;

import com.github.alexthe666.alexsmobs.client.model.ModelSeagull;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.arakiel.alexsmobsdelight.client.renderer.layers.SuperSeagullArmorLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SuperSeagullRenderer extends MobRenderer<EntitySeagull, ModelSeagull> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("alexsmobsdelight", "textures/entity/super_seagull.png");

    public SuperSeagullRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelSeagull(), 0.6F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new SuperSeagullArmorLayer(this));
    }

    protected void scale(EntitySeagull entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntitySeagull entity) {
        return TEXTURE;
    }

    @Override
    public void render(EntitySeagull entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(2.0F, 2.0F, 2.0F);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

    static class LayerHeldItem extends RenderLayer<EntitySeagull, ModelSeagull> {
        public LayerHeldItem(SuperSeagullRenderer render) {
            super(render);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntitySeagull entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            ItemStack itemstack = entitylivingbaseIn.getItemBySlot(EquipmentSlot.MAINHAND);
            matrixStackIn.pushPose();
            this.translateToHand(matrixStackIn);
            matrixStackIn.translate(0.0F, -0.24F, -0.275F);
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(-2.5F));
            matrixStackIn.mulPose(Axis.XP.rotationDegrees(-90.0F));
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
            ItemInHandRenderer renderer = Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer();
            renderer.renderItem(entitylivingbaseIn, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.popPose();
        }

        protected void translateToHand(PoseStack matrixStack) {
            ((ModelSeagull)this.getParentModel()).root.translateAndRotate(matrixStack);
            ((ModelSeagull)this.getParentModel()).body.translateAndRotate(matrixStack);
            ((ModelSeagull)this.getParentModel()).head.translateAndRotate(matrixStack);
        }
    }

    protected int getBlockLightLevel(EntitySeagull p_116443_, BlockPos p_116444_) {
        return 15;
    }
}
