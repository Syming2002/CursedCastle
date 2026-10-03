package com.syming.cursedcastle.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.client.model.CCSkeletonModel;
import com.syming.cursedcastle.entity.monster.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.CCSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.ClearBoneSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.GoldenSkeletonEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import xaero.common.core.XaeroMinimapCore;

public class CCSkeletonTintLayer extends RenderLayer<CCSkeletonEntity, CCSkeletonModel> {

    public static final ResourceLocation SKELETON_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/skeleton/skeleton.png");

    public CCSkeletonTintLayer(RenderLayerParent<CCSkeletonEntity, CCSkeletonModel> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CCSkeletonEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        VertexConsumer vertexConsumer = pBuffer.getBuffer(
                RenderType.entityCutoutNoCull(SKELETON_TEXTURE)
        );

        VertexConsumer transparentVertexConsumer = pBuffer.getBuffer(
                RenderType.entityTranslucent(SKELETON_TEXTURE)
        );


        if (pLivingEntity instanceof BloodSkeletonEntity) {
            float red = 0.569F;
            float green = 0.126F;
            float blue = 0.102F;

            this.getParentModel().renderToBuffer(
                    pPoseStack,
                    vertexConsumer,
                    pPackedLight,
                    LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F),
                    red, green, blue, 1.0F
            );

            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), vertexConsumer, red, green, blue, 1.0F);
        } else if (pLivingEntity instanceof GoldenSkeletonEntity) {
            float red = 0.937F;
            float green = 0.749F;
            float blue = 0.016F;

            this.getParentModel().renderToBuffer(
                    pPoseStack,
                    vertexConsumer,
                    pPackedLight,
                    LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F),
                    red, green, blue, 1.0F
            );
            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), vertexConsumer, red, green, blue, 1.0F);
        } else if (pLivingEntity instanceof ClearBoneSkeletonEntity clearBoneSkeleton) {
            this.getParentModel().renderToBuffer(
                    pPoseStack,
                    clearBoneSkeleton.isClearBoneInvulnerable() ? transparentVertexConsumer : vertexConsumer,
                    pPackedLight,
                    LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F),
                    1.0F, 1.0F, 1.0F, clearBoneSkeleton.isClearBoneInvulnerable() ? 0.5F : 1.0F
            );
            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), clearBoneSkeleton.isClearBoneInvulnerable() ? transparentVertexConsumer : vertexConsumer,1.0F, 1.0F, 1.0F, clearBoneSkeleton.isClearBoneInvulnerable() ? 0.5F : 1.0F);
        } else {
            this.getParentModel().renderToBuffer(
                    pPoseStack,
                    vertexConsumer,
                    pPackedLight,
                    LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F),
                    1.0F, 1.0F, 1.0F, 1.0F
            );
            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), vertexConsumer, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
