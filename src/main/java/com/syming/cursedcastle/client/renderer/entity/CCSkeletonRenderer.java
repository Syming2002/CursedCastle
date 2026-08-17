package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.client.model.CCSkeletonModel;
import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.CCSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.ClearBoneSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.GoldenSkeleton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import org.jetbrains.annotations.Nullable;

public class CCSkeletonRenderer extends MobRenderer<CCSkeletonEntity, CCSkeletonModel> {

    public static final ResourceLocation SKELETON_TEXTURE = new ResourceLocation("textures/entity/skeleton/skeleton.png");


    public CCSkeletonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CCSkeletonModel(pContext.bakeLayer(CCSkeletonModel.CC_SKELETON_LAYER)), 0.5F);
    }

    @Override
    public void render(CCSkeletonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity instanceof BloodSkeletonEntity) {
            pPoseStack.pushPose();
            this.model.attackTime = this.getAttackAnim(pEntity, pPartialTicks);

            boolean shouldSit = pEntity.isPassenger() && (pEntity.getVehicle() != null && pEntity.getVehicle().shouldRiderSit());
            this.model.riding = shouldSit;
            this.model.young = pEntity.isBaby();
            float f = Mth.rotLerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot);
            float f1 = Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.yHeadRot);
            float f2 = f1 - f;
            if (shouldSit && pEntity.getVehicle() instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)pEntity.getVehicle();
                f = Mth.rotLerp(pPartialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
                f2 = f1 - f;
                float f3 = Mth.wrapDegrees(f2);
                if (f3 < -85.0F) {
                    f3 = -85.0F;
                }

                if (f3 >= 85.0F) {
                    f3 = 85.0F;
                }

                f = f1 - f3;
                if (f3 * f3 > 2500.0F) {
                    f += f3 * 0.2F;
                }

                f2 = f1 - f;
            }

            float f6 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
            if (isEntityUpsideDown(pEntity)) {
                f6 *= -1.0F;
                f2 *= -1.0F;
            }

            if (pEntity.hasPose(Pose.SLEEPING)) {
                Direction direction = pEntity.getBedOrientation();
                if (direction != null) {
                    float f4 = pEntity.getEyeHeight(Pose.STANDING) - 0.1F;
                    pPoseStack.translate((float)(-direction.getStepX()) * f4, 0.0F, (float)(-direction.getStepZ()) * f4);
                }
            }

            float f7 = this.getBob(pEntity, pPartialTicks);
            this.setupRotations(pEntity, pPoseStack, f7, f, pPartialTicks);
            pPoseStack.scale(-1.0F, -1.0F, 1.0F);
            this.scale(pEntity, pPoseStack, pPartialTicks);
            pPoseStack.translate(0.0F, -1.501F, 0.0F);
            float f8 = 0.0F;
            float f5 = 0.0F;
            if (!shouldSit && pEntity.isAlive()) {
                f8 = pEntity.walkAnimation.speed(pPartialTicks);
                f5 = pEntity.walkAnimation.position(pPartialTicks);
                if (pEntity.isBaby()) {
                    f5 *= 3.0F;
                }

                if (f8 > 1.0F) {
                    f8 = 1.0F;
                }
            }

            this.model.prepareMobModel(pEntity, f5, f8, pPartialTicks);
            this.model.setupAnim(pEntity, f5, f8, f7, f2, f6);
            Minecraft minecraft = Minecraft.getInstance();
            boolean flag = this.isBodyVisible(pEntity);
            boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
            boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);
            RenderType rendertype = this.getRenderType(pEntity, flag, flag1, flag2);
            if (rendertype != null) {
                VertexConsumer vertexconsumer = pBuffer.getBuffer(rendertype);
                int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));

                float red = 0.569F;
                float green = 0.126F;
                float blue = 0.102F;

                this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, i, red, green, blue, flag1 ? 0.15F : 1.0F);
            }

            if (!pEntity.isSpectator()) {
                for(RenderLayer<CCSkeletonEntity, CCSkeletonModel> renderlayer : this.layers) {
                    renderlayer.render(pPoseStack, pBuffer, pPackedLight, pEntity, f5, f8, pPartialTicks, f7, f2, f6);
                }
            }

            pPoseStack.popPose();
        } else if (pEntity instanceof GoldenSkeleton) {
            pPoseStack.pushPose();
            this.model.attackTime = this.getAttackAnim(pEntity, pPartialTicks);

            boolean shouldSit = pEntity.isPassenger() && (pEntity.getVehicle() != null && pEntity.getVehicle().shouldRiderSit());
            this.model.riding = shouldSit;
            this.model.young = pEntity.isBaby();
            float f = Mth.rotLerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot);
            float f1 = Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.yHeadRot);
            float f2 = f1 - f;
            if (shouldSit && pEntity.getVehicle() instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity) pEntity.getVehicle();
                f = Mth.rotLerp(pPartialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
                f2 = f1 - f;
                float f3 = Mth.wrapDegrees(f2);
                if (f3 < -85.0F) {
                    f3 = -85.0F;
                }

                if (f3 >= 85.0F) {
                    f3 = 85.0F;
                }

                f = f1 - f3;
                if (f3 * f3 > 2500.0F) {
                    f += f3 * 0.2F;
                }

                f2 = f1 - f;
            }

            float f6 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
            if (isEntityUpsideDown(pEntity)) {
                f6 *= -1.0F;
                f2 *= -1.0F;
            }

            if (pEntity.hasPose(Pose.SLEEPING)) {
                Direction direction = pEntity.getBedOrientation();
                if (direction != null) {
                    float f4 = pEntity.getEyeHeight(Pose.STANDING) - 0.1F;
                    pPoseStack.translate((float) (-direction.getStepX()) * f4, 0.0F, (float) (-direction.getStepZ()) * f4);
                }
            }

            float f7 = this.getBob(pEntity, pPartialTicks);
            this.setupRotations(pEntity, pPoseStack, f7, f, pPartialTicks);
            pPoseStack.scale(-1.0F, -1.0F, 1.0F);
            this.scale(pEntity, pPoseStack, pPartialTicks);
            pPoseStack.translate(0.0F, -1.501F, 0.0F);
            float f8 = 0.0F;
            float f5 = 0.0F;
            if (!shouldSit && pEntity.isAlive()) {
                f8 = pEntity.walkAnimation.speed(pPartialTicks);
                f5 = pEntity.walkAnimation.position(pPartialTicks);
                if (pEntity.isBaby()) {
                    f5 *= 3.0F;
                }

                if (f8 > 1.0F) {
                    f8 = 1.0F;
                }
            }

            this.model.prepareMobModel(pEntity, f5, f8, pPartialTicks);
            this.model.setupAnim(pEntity, f5, f8, f7, f2, f6);
            Minecraft minecraft = Minecraft.getInstance();
            boolean flag = this.isBodyVisible(pEntity);
            boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
            boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);
            RenderType rendertype = this.getRenderType(pEntity, flag, flag1, flag2);
            if (rendertype != null) {
                VertexConsumer vertexconsumer = pBuffer.getBuffer(rendertype);
                int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));

                float red = 0.937F;
                float green = 0.749F;
                float blue = 0.016F;

                this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, i, red, green, blue, flag1 ? 0.15F : 1.0F);
            }

            if (!pEntity.isSpectator()) {
                for(RenderLayer<CCSkeletonEntity, CCSkeletonModel> renderlayer : this.layers) {
                    renderlayer.render(pPoseStack, pBuffer, pPackedLight, pEntity, f5, f8, pPartialTicks, f7, f2, f6);
                }
            }

            pPoseStack.popPose();
        } else if (pEntity instanceof ClearBoneSkeletonEntity) {
            pPoseStack.pushPose();
            this.model.attackTime = this.getAttackAnim(pEntity, pPartialTicks);

            boolean shouldSit = pEntity.isPassenger() && (pEntity.getVehicle() != null && pEntity.getVehicle().shouldRiderSit());
            this.model.riding = shouldSit;
            this.model.young = pEntity.isBaby();
            float f = Mth.rotLerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot);
            float f1 = Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.yHeadRot);
            float f2 = f1 - f;
            if (shouldSit && pEntity.getVehicle() instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)pEntity.getVehicle();
                f = Mth.rotLerp(pPartialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
                f2 = f1 - f;
                float f3 = Mth.wrapDegrees(f2);
                if (f3 < -85.0F) {
                    f3 = -85.0F;
                }

                if (f3 >= 85.0F) {
                    f3 = 85.0F;
                }

                f = f1 - f3;
                if (f3 * f3 > 2500.0F) {
                    f += f3 * 0.2F;
                }

                f2 = f1 - f;
            }

            float f6 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
            if (isEntityUpsideDown(pEntity)) {
                f6 *= -1.0F;
                f2 *= -1.0F;
            }

            if (pEntity.hasPose(Pose.SLEEPING)) {
                Direction direction = pEntity.getBedOrientation();
                if (direction != null) {
                    float f4 = pEntity.getEyeHeight(Pose.STANDING) - 0.1F;
                    pPoseStack.translate((float) (-direction.getStepX()) * f4, 0.0F, (float) (-direction.getStepZ()) * f4);
                }
            }

            float f7 = this.getBob(pEntity, pPartialTicks);
            this.setupRotations(pEntity, pPoseStack, f7, f, pPartialTicks);
            pPoseStack.scale(-1.0F, -1.0F, 1.0F);
            this.scale(pEntity, pPoseStack, pPartialTicks);
            pPoseStack.translate(0.0F, -1.501F, 0.0F);
            float f8 = 0.0F;
            float f5 = 0.0F;
            if (!shouldSit && pEntity.isAlive()) {
                f8 = pEntity.walkAnimation.speed(pPartialTicks);
                f5 = pEntity.walkAnimation.position(pPartialTicks);
                if (pEntity.isBaby()) {
                    f5 *= 3.0F;
                }

                if (f8 > 1.0F) {
                    f8 = 1.0F;
                }
            }

            this.model.prepareMobModel(pEntity, f5, f8, pPartialTicks);
            this.model.setupAnim(pEntity, f5, f8, f7, f2, f6);
            Minecraft minecraft = Minecraft.getInstance();
            boolean flag = this.isBodyVisible(pEntity);
            boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
            boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);
            boolean flag3 = pEntity.isInvulnerable();
            RenderType rendertype = this.getRenderType(pEntity, flag, flag1, flag2);
            if (rendertype != null) {
                VertexConsumer vertexconsumer = pBuffer.getBuffer(rendertype);
                int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));
                if (flag1 || flag3) {
                    this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, i, 1.0F, 1.0F, 1.0F, 0.15F);
                } else {
                    this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, i, 1.0F, 1.0F, 1.0F, 1.0F);
                }
            }

            if (!pEntity.isSpectator()) {
                for (RenderLayer<CCSkeletonEntity, CCSkeletonModel> renderlayer : this.layers) {
                    renderlayer.render(pPoseStack, pBuffer, pPackedLight, pEntity, f5, f8, pPartialTicks, f7, f2, f6);
                }
            }

            pPoseStack.popPose();
        }
    }

    @Override
    protected float getFlipDegrees(CCSkeletonEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(CCSkeletonEntity pEntity) {
        return SKELETON_TEXTURE;
    }

    @Override
    protected @Nullable RenderType getRenderType(CCSkeletonEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        ResourceLocation texture = getTextureLocation(pLivingEntity);

        if (pLivingEntity.isInvulnerable()) {
            return RenderType.entityTranslucent(texture);
        }

        return super.getRenderType(pLivingEntity, pBodyVisible, pTranslucent, pGlowing);
    }
}
