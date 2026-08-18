package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.HarpyFeatherModel;
import com.syming.cursedcastle.entity.magic.HarpyFeatherEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HarpyFeatherRenderer extends EntityRenderer<HarpyFeatherEntity> {
    public static final ResourceLocation HARPY_FEATHER_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/harpy_feather.png");
    private final HarpyFeatherModel harpyFeatherModel;

    public HarpyFeatherRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

        this.harpyFeatherModel = new HarpyFeatherModel(pContext.bakeLayer(HarpyFeatherModel.HARPY_FEATHER_LAYER));
    }

    @Override
    public void render(HarpyFeatherEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        float f = pEntity.getYRot();
        float f1 = pEntity.getXRot();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.harpyFeatherModel.renderType(getTextureLocation(pEntity)));
        this.harpyFeatherModel.setupAnim(pEntity, 0.0F, 0.0F, 0.0F, f, f1);
        this.harpyFeatherModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HarpyFeatherEntity pEntity) {
        return HARPY_FEATHER_TEXTURE;
    }
}
