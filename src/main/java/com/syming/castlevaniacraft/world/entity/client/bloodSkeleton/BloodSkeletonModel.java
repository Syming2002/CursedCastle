package com.syming.castlevaniacraft.world.entity.client.bloodSkeleton;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.castlevaniacraft.world.entity.mob.BloodSkeletonEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BloodSkeletonModel extends AdvancedEntityModel<BloodSkeletonEntity> {

    public final AdvancedModelBox root;

    public final AdvancedModelBox head;
    public final AdvancedModelBox hat;
    public final AdvancedModelBox body;
    public final AdvancedModelBox rightArm;
    public final AdvancedModelBox leftArm;
    public final AdvancedModelBox rightLeg;
    public final AdvancedModelBox leftLeg;

    public ModelAnimator animator;

    public BloodSkeletonModel() {
        this.texWidth = 64;
        this.texHeight = 32;

        this.root = new AdvancedModelBox(this, "root");
        this.root.setPos(0.0F, 24.0F, 0.0F);

        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(0.0F, -24.0F, 0.0F);
        this.head.setTextureOffset(0, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
        this.root.addChild(this.head);

        this.hat = new AdvancedModelBox(this, "hat");
        this.hat.setPos(0.0F, -24.0F, 0.0F);
        this.hat.setTextureOffset(32, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F);
        this.root.addChild(this.hat);

        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(0.0F, -24.0F, 0.0F);
        this.body.setTextureOffset(16, 16)
                .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F);
        this.root.addChild(this.body);

        this.rightArm = new AdvancedModelBox(this, "right_arm");
        this.rightArm.setPos(-5.0F, -22.0F, 0.0F);
        this.rightArm.setTextureOffset(40, 16)
                .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.root.addChild(this.rightArm);

        this.leftArm = new AdvancedModelBox(this, "left_arm");
        this.leftArm.setPos(5.0F, -22.0F, 0.0F);
        this.leftArm.setTextureOffset(40, 16)
                .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.leftArm.mirror = true;
        this.root.addChild(this.leftArm);

        this.rightLeg = new AdvancedModelBox(this, "right_leg");
        this.rightLeg.setPos(-2.0F, -12.0F, 0.0F);
        this.rightLeg.setTextureOffset(0, 16)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.root.addChild(this.rightLeg);

        this.leftLeg = new AdvancedModelBox(this, "left_leg");
        this.leftLeg.setPos(2.0F, -12.0F, 0.0F);
        this.leftLeg.setTextureOffset(0, 16)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.leftLeg.mirror = true;
        this.root.addChild(this.leftLeg);

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

//    public static LayerDefinition createBodyLayer(CubeDeformation pCubeDeformation, float pYOffset) {
//        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
//        PartDefinition partdefinition = meshdefinition.getRoot();
//        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, pCubeDeformation), PartPose.offset(0.0F, 0.0F + pYOffset, 0.0F));
//        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, pCubeDeformation.extend(0.5F)), PartPose.offset(0.0F, 0.0F + pYOffset, 0.0F));
//        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, pCubeDeformation), PartPose.offset(0.0F, 0.0F + pYOffset, 0.0F));
//        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
//        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
//        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
//        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
//        return LayerDefinition.create(meshdefinition, 64, 32);
//    }

//    @Override
//    public void prepareMobModel(BloodSkeletonEntity p_103793_, float p_103794_, float p_103795_, float p_103796_) {
//        super.prepareMobModel(p_103793_, p_103794_, p_103795_, p_103796_);
//    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(
                this.head,
                this.body,
                this.rightArm,
                this.leftArm,
                this.rightLeg,
                this.leftLeg
        );
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.root);
    }

    @Override
    public void setupAnim(BloodSkeletonEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        animate(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        faceTarget(pNetHeadYaw, pHeadPitch, 1.0F, this.head, this.hat);
    }

    public void animate(BloodSkeletonEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.animator.update(pEntity);

        // Death Animation
        if (this.animator.setAnimation(BloodSkeletonEntity.DEATH_ANIMATION)) {
            pEntity.initDeathAnimationRandoms();
            float fallRotation = pEntity.getFallSideRotation();

            this.animator.startKeyframe(15);
            this.rotate(animator, head, 270 + fallRotation * 0.5F, 0, fallRotation);
            this.animator.move(head, 0, 24, -17);
            this.rotate(animator, hat, 270 + fallRotation * 0.5F, 0, fallRotation);
            this.animator.move(hat, 0, 22, -19);
            this.rotate(animator, body, -90, 0, fallRotation);
            this.animator.move(body, 0, 22, 15);
            this.rotate(animator, rightArm, 0, 0, -90 + fallRotation);
            this.animator.move(rightArm, -11, 20, 0);
            this.rotate(animator, leftArm, 0, 0, 90 + fallRotation);
            this.animator.move(leftArm, 10, 21, 0);
            this.rotate(animator, rightLeg, 90, 0, fallRotation);
            this.animator.move(rightLeg, 0, 11, -10);
            this.rotate(animator, leftLeg, -68.25F, 0, fallRotation);
            this.animator.move(leftLeg, 0, 11, -10);
            this.animator.endKeyframe();
            this.animator.setStaticKeyframe(25);
            this.animator.resetKeyframe(15);
        }

        this.leftArm.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
        this.rightArm.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;

        this.leftLeg.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
        this.rightLeg.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
    }
}
