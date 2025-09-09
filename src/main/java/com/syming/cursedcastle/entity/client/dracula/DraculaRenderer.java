package com.syming.cursedcastle.entity.client.dracula;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.util.BloodMoonUtil;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class DraculaRenderer extends MobRenderer<DraculaEntity, DraculaModel> {
    public static final ResourceLocation DRACULA_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/dracula.png");
    public static final ResourceLocation DRACULA_BLOODMOON_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/dracula_bloodmoon.png");
    public static final ResourceLocation LASER_BEAM = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/block/teleportation_laser_beam.png");
    private static final RenderType LASER_BEAM_RENDER_TYPE = RenderType.entitySmoothCutout(LASER_BEAM);


    public DraculaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DraculaModel(), 0.5F);
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
            System.out.println("Rendering Laser Beam");
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
                    .vertex(poseMatrix, -quadSize, 0.0F, 0.0F)
                    .color(1.0F, 1.0F, 1.0F, 1.0F)
                    .uv(uMin, vMax)
                    .overlayCoords(OverlayTexture.NO_OVERLAY)
                    .uv2(240)
                    .normal(pPoseStack.last().normal(), 0.0F, 1.0F, 0.0F).endVertex();
            vertexConsumer
                    .vertex(poseMatrix, quadSize, 0.0F, 0.0F)
                    .color(1.0F, 1.0F, 1.0F, 1.0F)
                    .uv(uMax, vMax)
                    .overlayCoords(OverlayTexture.NO_OVERLAY)
                    .uv2(240)
                    .normal(pPoseStack.last().normal(), 0.0F, 1.0F, 0.0F).endVertex();
            vertexConsumer
                    .vertex(poseMatrix, quadSize, quadHeight, 0.0F)
                    .color(1.0F, 1.0F, 1.0F, 1.0F)
                    .uv(uMax, vMin)
                    .overlayCoords(OverlayTexture.NO_OVERLAY)
                    .uv2(240)
                    .normal(pPoseStack.last().normal(), 0.0F, 1.0F, 0.0F).endVertex();
            vertexConsumer
                    .vertex(poseMatrix, -quadSize, quadHeight, 0.0F)
                    .color(1.0F, 1.0F, 1.0F, 1.0F)
                    .uv(uMin, vMin)
                    .overlayCoords(OverlayTexture.NO_OVERLAY)
                    .uv2(240)
                    .normal(pPoseStack.last().normal(), 0.0F, 1.0F, 0.0F).endVertex();

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
