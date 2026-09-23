package com.syming.cursedcastle.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.capability.CCCapabilities;
import com.syming.cursedcastle.capability.data.IPlayerData;
import com.syming.cursedcastle.capability.data.PlayerData;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerRenderer.class, remap = false)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final ResourceLocation HOLY_BEAM_APPEAR = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/block/holy_beam_appear.png");
    private static final RenderType HOLY_BEAM_APPEAR_RENDER_TYPE = RenderType.entitySmoothCutout(HOLY_BEAM_APPEAR);

    private static final ResourceLocation HOLY_BEAM_STATIC = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/block/holy_beam_static.png");
    private static final RenderType HOLY_BEAM_STATIC_RENDER_TYPE = RenderType.entitySmoothCutout(HOLY_BEAM_STATIC);

    private static final ResourceLocation HOLY_BEAM_DISAPPEAR = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/block/holy_beam_disappear.png");
    private static final RenderType HOLY_BEAM_DISAPPEAR_RENDER_TYPE = RenderType.entitySmoothCutout(HOLY_BEAM_DISAPPEAR);

    public PlayerRendererMixin(EntityRendererProvider.Context pContext, PlayerModel<AbstractClientPlayer> pModel, float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
    }

    @Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"))
    private void renderHolyBeam(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci) {
        PlayerData playerData = pEntity.getData(CCCapabilities.PLAYER_DATA);
        if (playerData != null) {
            if (playerData.isBoltAttackEnabled()) {
                if (playerData.getHolyBeamTicks() > 200 && playerData.getHolyBeamTicks() <= 300) {
                    renderAppearHolyBeam(pEntity, pPoseStack, pBuffer);

                }

                if (playerData.getHolyBeamTicks() > 100 && playerData.getHolyBeamTicks() <= 200) {
                    renderStaticHolyBeam(pEntity, pPoseStack, pBuffer);

                }

                if (playerData.getHolyBeamTicks() > 0 && playerData.getHolyBeamTicks() <= 100) {
                    renderDisappearHolyBeam(pEntity, pPoseStack, pBuffer);

                }
            }
        }
    }

    private void renderAppearHolyBeam(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer) {
        pPoseStack.pushPose();

        pPoseStack.translate(0.0D, 0.0D, 0.0D);

        float scale = pEntity.getBbWidth() * 6.0F;
        pPoseStack.scale(scale, pEntity.level().getMinBuildHeight() + pEntity.level().getMaxBuildHeight(), scale);

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

        VertexConsumer vertexConsumer = pBuffer.getBuffer(HOLY_BEAM_APPEAR_RENDER_TYPE);

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

    private void renderStaticHolyBeam(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer) {
        pPoseStack.pushPose();

        pPoseStack.translate(0.0D, 0.0D, 0.0D);

        float scale = pEntity.getBbWidth() * 6.0F;
        pPoseStack.scale(scale, pEntity.level().getMinBuildHeight() + pEntity.level().getMaxBuildHeight(), scale);

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

        VertexConsumer vertexConsumer = pBuffer.getBuffer(HOLY_BEAM_STATIC_RENDER_TYPE);

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

    private void renderDisappearHolyBeam(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer) {
        pPoseStack.pushPose();

        pPoseStack.translate(0.0D, 0.0D, 0.0D);

        float scale = pEntity.getBbWidth() * 6.0F;
        pPoseStack.scale(scale, pEntity.level().getMinBuildHeight() + pEntity.level().getMaxBuildHeight(), scale);

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

        VertexConsumer vertexConsumer = pBuffer.getBuffer(HOLY_BEAM_DISAPPEAR_RENDER_TYPE);

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
}
