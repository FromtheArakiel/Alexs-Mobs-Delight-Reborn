package dev.arakiel.alexsmobsdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.arakiel.alexsmobsdelight.entities.ThrownDartEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;

public class ThrownDartRenderer<T extends Entity & ItemSupplier> extends EntityRenderer<T> {
    private final ItemRenderer itemRenderer;
    private final float scale;

    public ThrownDartRenderer(EntityRendererProvider.Context p_174416_, float p_174417_) {
        super(p_174416_);
        this.itemRenderer = p_174416_.getItemRenderer();
        this.scale = p_174417_;
    }

    public ThrownDartRenderer(EntityRendererProvider.Context p_174414_) {
        this(p_174414_, 1.0F);
    }

    protected int getBlockLightLevel(T p_116092_, BlockPos p_116093_) {
        return super.getBlockLightLevel(p_116092_, p_116093_);
    }

    public void render(T p_116085_, float p_116086_, float p_116087_, PoseStack p_116088_, MultiBufferSource p_116089_, int p_116090_) {
        if (p_116085_.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(p_116085_) < 12.25)) {
            p_116088_.pushPose();
            //From Goety SwordProjectileRenderer
            p_116088_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_116087_, p_116085_.yRotO, p_116085_.getYRot()) - 90.0F));
            p_116088_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_116087_, p_116085_.xRotO, p_116085_.getXRot()) - 135.0F));
            p_116088_.translate(0, -0.2f, 0);
            this.itemRenderer.renderStatic(((ItemSupplier)p_116085_).getItem(), ItemDisplayContext.GROUND, p_116090_, OverlayTexture.NO_OVERLAY, p_116088_, p_116089_, p_116085_.level(), p_116085_.getId());
            p_116088_.popPose();
            super.render(p_116085_, p_116086_, p_116087_, p_116088_, p_116089_, p_116090_);
        }
    }

    public ResourceLocation getTextureLocation(Entity p_116083_) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
