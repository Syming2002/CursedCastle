package com.syming.cursedcastle.entity.client.skeleton;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.CCSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.ThrowingBoneSkeletonEntity;
import net.minecraft.util.Mth;

public class SkeletonModel extends AdvancedEntityModel<CCSkeletonEntity> {

    protected final AdvancedModelBox skeleton;
    protected final AdvancedModelBox head;
    protected final AdvancedModelBox headWear;
    protected final AdvancedModelBox body;
    protected final AdvancedModelBox rightArm;
    protected final AdvancedModelBox leftArm;
    protected final AdvancedModelBox rightLeg;
    protected final AdvancedModelBox leftLeg;

    protected ModelAnimator animator;

    public SkeletonModel() {
        this.texWidth = 64;
        this.texHeight = 64;

        this.skeleton = new AdvancedModelBox(this, "skeleton");
        this.skeleton.setPos(0.0F, 24.0F, 0.0F);

        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(0.0F, -28.0F, 0.0F);
        this.head.setTextureOffset(0, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);
        this.skeleton.addChild(this.head);

        this.headWear = new AdvancedModelBox(this, "headWear");
        this.headWear.setPos(0.0F, -28.0F, 0.0F);
        this.headWear.setTextureOffset(0, 16)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F);
        this.skeleton.addChild(this.headWear);

        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(0.0F, -18.0F, 0.0F);
        this.body.setTextureOffset(0, 32)
                .addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F);
        this.skeleton.addChild(this.body);

        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(-5.0F, -22.0F, 0.0F);
        this.rightArm.setTextureOffset(32, 0)
                .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.skeleton.addChild(this.rightArm);

        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(5.0F, -22.0F, 0.0F);
        this.leftArm.setTextureOffset(32, 14)
                .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.skeleton.addChild(this.leftArm);

        this.rightLeg = new AdvancedModelBox(this, "rightLeg");
        this.rightLeg.setPos(-2.0F, -12.0F, 0.0F);
        this.rightLeg.setTextureOffset(24, 32)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.skeleton.addChild(this.rightLeg);

        this.leftLeg = new AdvancedModelBox(this, "leftLeg");
        this.leftLeg.setPos(2.0F, -12.0F, 0.0F);
        this.leftLeg.setTextureOffset(32, 28)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.skeleton.addChild(this.leftLeg);

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.skeleton.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
        return ImmutableList.of(this.skeleton);
    }

    @Override
    public void setupAnim(CCSkeletonEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        animate(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
    }

    public void animate(CCSkeletonEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(pEntity);

        // Throwing Bone Animation
        if (pEntity instanceof ThrowingBoneSkeletonEntity) {
            if (this.animator.setAnimation(ThrowingBoneSkeletonEntity.THROWING_BONE_ANIMATION)) {
                this.animator.startKeyframe(20);
                this.rotate(this.animator, this.rightArm, -102.5F, 27.5F, 0.0F);
                this.animator.endKeyframe();
                this.animator.startKeyframe(10);
                this.rotate(this.animator, this.rightArm, -206.4031859401F, 7.9813342888F, -74.3751051883F);
                this.animator.endKeyframe();
                this.animator.startKeyframe(2);
                this.rotate(this.animator, this.rightArm, -86.4F, 7.98F, -74.38F);
                this.animator.endKeyframe();
                this.animator.resetKeyframe(8);
            }
        }

        // Death Animation
        if (this.animator.setAnimation(BloodSkeletonEntity.DEATH_ANIMATION)) {
            pEntity.initDeathAnimationRandoms();
            float fallRotation = pEntity.getFallSideRotation();

            this.animator.startKeyframe(15);
            this.rotate(animator, head, 270 + fallRotation * 0.5F, 0, fallRotation);
            this.animator.move(head, 0, 24, -17);
            this.rotate(animator, headWear, 270 + fallRotation * 0.5F, 0, fallRotation);
            this.animator.move(headWear, 0, 22, -19);
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

        if (pEntity.getAnimation() != ThrowingBoneSkeletonEntity.THROWING_BONE_ANIMATION) {
            this.rightArm.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 1.4F * pLimbSwingAmount;
        }
        this.leftArm.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;

        this.leftLeg.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
        this.rightLeg.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 1.4F * pLimbSwingAmount;
    }
}
