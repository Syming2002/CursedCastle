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
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import xaero.common.core.XaeroMinimapCore;

public class CCSkeletonTintLayer extends RenderLayer<CCSkeletonEntity, CCSkeletonModel> {

    public static final ResourceLocation SKELETON_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/skeleton/skeleton.png");

    public CCSkeletonTintLayer(RenderLayerParent<CCSkeletonEntity, CCSkeletonModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, CCSkeletonEntity ccSkeletonEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(
                RenderType.entityCutoutNoCull(SKELETON_TEXTURE)
        );

        VertexConsumer transparentVertexConsumer = multiBufferSource.getBuffer(
                RenderType.entityTranslucent(SKELETON_TEXTURE)
        );


        if (ccSkeletonEntity instanceof BloodSkeletonEntity) {
            int i = FastColor.ARGB32.color(255, 145, 32, 26);

            this.getParentModel().renderToBuffer(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    i
            );

            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), vertexConsumer, i);
        } else if (ccSkeletonEntity instanceof GoldenSkeletonEntity) {
            int i = FastColor.ARGB32.color(255, 239, 191, 4);

            this.getParentModel().renderToBuffer(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    i
            );
            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), vertexConsumer, i);
        } else if (ccSkeletonEntity instanceof ClearBoneSkeletonEntity clearBoneSkeleton) {
            int i = FastColor.ARGB32.color(clearBoneSkeleton.isClearBoneInvulnerable() ? 100 : 255, 255, 255, 255);

            this.getParentModel().renderToBuffer(
                    poseStack,
                    transparentVertexConsumer,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    i
            );
            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), transparentVertexConsumer, i);
        } else {
            int i = FastColor.ARGB32.color(255, 255, 255, 255);

            this.getParentModel().renderToBuffer(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    i
            );
            XaeroMinimapCore.onEntityIconsModelRenderDetection(this.getParentModel(), vertexConsumer, i);
        }
    }
}
