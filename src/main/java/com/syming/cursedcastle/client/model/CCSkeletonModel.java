package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.monster.skeleton.CCSkeletonEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import xaero.common.core.XaeroMinimapCore;

public class CCSkeletonModel extends HierarchicalModel<CCSkeletonEntity> {

    public static final ModelLayerLocation CC_SKELETON_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "cc_skeleton"), "main");

    private final ModelPart skeleton;
    private final ModelPart head;
    private final ModelPart headWear;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public CCSkeletonModel(ModelPart pRoot) {

        this.skeleton = pRoot.getChild("skeleton");

        this.head = this.skeleton.getChild("head");
        this.headWear = this.skeleton.getChild("headWear");
        this.body = this.skeleton.getChild("body");
        this.rightArm = this.skeleton.getChild("rightArm");
        this.leftArm = this.skeleton.getChild("leftArm");
        this.rightLeg = this.skeleton.getChild("rightLeg");
        this.leftLeg = this.skeleton.getChild("leftLeg");

    }

    public static LayerDefinition createBodyLayer() {

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition skeleton = partdefinition.addOrReplaceChild("skeleton", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = skeleton.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));

        PartDefinition headWear = skeleton.addOrReplaceChild("headWear", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -28.0F, 0.0F));

        PartDefinition body = skeleton.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

        PartDefinition rightArm = skeleton.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

        PartDefinition leftArm = skeleton.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

        PartDefinition rightLeg = skeleton.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

        PartDefinition leftLeg = skeleton.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.skeleton.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        XaeroMinimapCore.onEntityIconsModelRenderDetection(this, pBuffer, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public ModelPart root() {
        return this.skeleton;
    }

    @Override
    public void setupAnim(CCSkeletonEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);

        if (pEntity.getAnimation() !=CCSkeletonEntity.THROWING_BONE_ANIMATION) {
            this.rightArm.xRot = Mth.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 1.4F * pLimbSwingAmount;
        }
        this.leftArm.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;

        this.leftLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
        this.rightLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 1.4F * pLimbSwingAmount;

        // Throwing Bone Animation
        animate(pEntity.throwingBoneAnimationState, CCAnimation.CC_SKELETON_THROWING_BONE_ATTACK_ANIMATION, pAgeInTicks);

        // Death Animation
        animate(pEntity.deathAnimationState, CCAnimation.CC_SKELETON_DEATH_ANIMATION, pAgeInTicks);

        if (pEntity.deathAnimationState.isStarted()) {
            float fallRotation = pEntity.getFallSideRotation();

            float fallRotationRad = (float) Math.toRadians(fallRotation);

            this.head.xRot += (float) Math.toRadians(0.5F * fallRotation);
            this.head.zRot += fallRotationRad;

            this.headWear.xRot += (float) Math.toRadians(0.5F * fallRotation);
            this.headWear.zRot += fallRotationRad;

            this.body.zRot += fallRotationRad;

            this.rightArm.zRot += fallRotationRad;
            this.leftArm.zRot += fallRotationRad;

            this.rightLeg.zRot += fallRotationRad;
            this.leftLeg.zRot += fallRotationRad;
        }
    }
}
