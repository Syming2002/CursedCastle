package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.client.model.IceSpikeModel;
import com.syming.cursedcastle.entity.magic.IceSpikeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class IceSpikeRenderer extends EntityRenderer<IceSpikeEntity> {

    public static final ResourceLocation ICE_SPIKE_TEXTURE = new ResourceLocation("textures/block/packed_ice.png");
    private final IceSpikeModel iceSpikeModel;

    public IceSpikeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

        this.iceSpikeModel = new IceSpikeModel(pContext.bakeLayer(IceSpikeModel.ICE_SPIKE_LAYER));
    }

    @Override
    public void render(IceSpikeEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        float f = Mth.rotLerp(pPartialTick, pEntity.yRotO, pEntity.getYRot());
        float f1 = Mth.lerp(pPartialTick, pEntity.xRotO, pEntity.getXRot());
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.iceSpikeModel.renderType(getTextureLocation(pEntity)));
        this.iceSpikeModel.setupAnim(pEntity, 0.0F, 0.0F, pPartialTick, f, f1);
        this.iceSpikeModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(IceSpikeEntity pEntity) {
        return ICE_SPIKE_TEXTURE;
    }
}
