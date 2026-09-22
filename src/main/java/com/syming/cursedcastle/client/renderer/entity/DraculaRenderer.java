package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.DraculaModel;
import com.syming.cursedcastle.util.BloodMoonUtil;
import com.syming.cursedcastle.entity.monster.boss.dracula.DraculaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class DraculaRenderer extends MobRenderer<DraculaEntity, DraculaModel> {
    public static final ResourceLocation DRACULA_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/entity/dracula.png");
    public static final ResourceLocation DRACULA_BLOODMOON_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/entity/dracula_bloodmoon.png");
    public static final ResourceLocation LASER_BEAM = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/block/teleportation_laser_beam.png");
    private static final RenderType LASER_BEAM_RENDER_TYPE = RenderType.entitySmoothCutout(LASER_BEAM);


    public DraculaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DraculaModel(pContext.bakeLayer(DraculaModel.DRACULA_LAYER)), 0.5F);
    }

    @Override
    public void render(DraculaEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
        if (pEntity != null
                && pEntity.level() != null
                && pEntity.level().isClientSide()
                && ((pEntity.getTeleportTick() >= 50 && pEntity.getTeleportTick() < 70)
                || (pEntity.getTeleportTick() >= 100 && pEntity.getTeleportTick() < 120))) {
            renderLaserBeam(pEntity, pPoseStack, pBuffer);
        }
    }

    private void renderLaserBeam(DraculaEntity pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer) {

        pPoseStack.pushPose();

        pPoseStack.translate(0.0D, 0.0D, 0.0D);

        float scale = pEntity.getBbWidth() * 6.0F;
        pPoseStack.scale(scale, pEntity.level().getMaxBuildHeight(), scale);

        int ticks = pEntity.tickCount;
        int numFrames = 8;
        float frameHeight = 1.0F / numFrames;
        int currentFrame = (ticks / 4) % numFrames;
        float vMin = currentFrame * frameHeight;
        float vMax = vMin + frameHeight;

        float uMin = 0.0F;
        float uMax = 1.0F;

        float quadSize = 0.5F;
        float quadHeight = 1.0F;

        pPoseStack.mulPose(Axis.YP.rotationDegrees(-this.entityRenderDispatcher.camera.getYRot()));

        VertexConsumer vertexConsumer = pBuffer.getBuffer(LASER_BEAM_RENDER_TYPE);

        for (int pass = 0; pass < 2; pass++) {
            pPoseStack.pushPose();
            if (pass == 1) {
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            }

            Matrix4f poseMatrix = pPoseStack.last().pose();

            vertexConsumer
                    .addVertex(poseMatrix, -quadSize, 0.0F, 0.0F)
                    .setColor(1.0F, 1.0F, 1.0F, 1.0F)
                    .setUv(uMin, vMax)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(240)
                    .setNormal(pPoseStack.last(), 0.0F, 1.0F, 0.0F);
            vertexConsumer
                    .addVertex(poseMatrix, quadSize, 0.0F, 0.0F)
                    .setColor(1.0F, 1.0F, 1.0F, 1.0F)
                    .setUv(uMax, vMax)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(240)
                    .setNormal(pPoseStack.last(), 0.0F, 1.0F, 0.0F);
            vertexConsumer
                    .addVertex(poseMatrix, quadSize, quadHeight, 0.0F)
                    .setColor(1.0F, 1.0F, 1.0F, 1.0F)
                    .setUv(uMax, vMin)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(240)
                    .setNormal(pPoseStack.last(), 0.0F, 1.0F, 0.0F);
            vertexConsumer
                    .addVertex(poseMatrix, -quadSize, quadHeight, 0.0F)
                    .setColor(1.0F, 1.0F, 1.0F, 1.0F)
                    .setUv(uMin, vMin)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(240)
                    .setNormal(pPoseStack.last(), 0.0F, 1.0F, 0.0F);

            pPoseStack.popPose();
        }

        pPoseStack.popPose();
    }

    @Override
    protected float getFlipDegrees(DraculaEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(DraculaEntity draculaEntity) {
        if (BloodMoonUtil.isBloodMoon(draculaEntity.level())) {
            return DRACULA_BLOODMOON_TEXTURE;
        }
        return DRACULA_TEXTURE;
    }
}
