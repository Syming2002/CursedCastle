package com.syming.cursedcastle.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.client.renderer.entity.SkeletonRenderer;
import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.CCSkeletonEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class BloodSkeletonTintLayer<T extends CCSkeletonEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public BloodSkeletonTintLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!(pLivingEntity instanceof BloodSkeletonEntity)) return;

        float red = 0.569F;
        float green = 0.126F;
        float blue = 0.102F;

        VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entitySolid(SkeletonRenderer.SKELETON_TEXTURE));
        this.getParentModel().renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
    }
}
