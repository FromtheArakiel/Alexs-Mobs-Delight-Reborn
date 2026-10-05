package dev.arakiel.alexsmobsdelight.client.renderer.layers;

import com.github.alexthe666.alexsmobs.client.model.ModelSeagull;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.arakiel.alexsmobsdelight.entities.SuperSeagull;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SuperSeagullArmorLayer extends RenderLayer<EntitySeagull, ModelSeagull> {

    private static final ResourceLocation ARMOR_TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/entity/wither/wither_armor.png");

    public SuperSeagullArmorLayer(RenderLayerParent<EntitySeagull, ModelSeagull> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, EntitySeagull entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entity instanceof SuperSeagull superSeagull && superSeagull.isPowered()) {
            float tick = (float) entity.tickCount + partialTick;
            EntityModel<EntitySeagull> model = this.getParentModel();
            model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
            this.getParentModel().copyPropertiesTo(model);

            VertexConsumer vertexConsumer = bufferSource.getBuffer(
                    RenderType.energySwirl(ARMOR_TEXTURE, Mth.cos(tick * 0.02F) * 3.0F % 1.0F, tick * 0.01F % 1.0F)
            );

            model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            float color1 = superSeagull.getHealth() * 2 > superSeagull.getMaxHealth() ? 0.5F : 1.0F;
            float color2 = superSeagull.getHealth() * 2 > superSeagull.getMaxHealth() ? 1.0F : 0.5F;
            int color = net.minecraft.util.FastColor.ARGB32.color(128,
                    (int) (color1 * 255.0F), 128, (int) (color2 * 255.0F));
            model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
        }
  }
}
