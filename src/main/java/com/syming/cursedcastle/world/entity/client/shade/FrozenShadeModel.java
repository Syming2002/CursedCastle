package com.syming.cursedcastle.world.entity.client.shade;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.world.entity.mob.shade.FrozenShadeEntity;
import com.syming.cursedcastle.world.entity.mob.shade.ShadeEntity;

public class FrozenShadeModel extends AdvancedEntityModel<FrozenShadeEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "shade_layer"), "main");
    private final AdvancedModelBox shade;
    private final AdvancedModelBox head;
    private final AdvancedModelBox body;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox leftLeg;

    public ModelAnimator animator;

    public FrozenShadeModel() {
        this.texWidth = 64;
        this.texHeight = 64;

        this.shade = new AdvancedModelBox(this,"shade");
        this.shade.setPos(0.0F, 24.0F, 0.0F);

        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(-4.0F, 24.0F, -4.0F);
        this.head.setRotationPoint(0.0F, -28.0F, 0.0F);
        this.shade.addChild(this.head);
        this.head.setTextureOffset(0, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);

        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(-4.0F, 12.0F, -2.0F);
        this.body.setRotationPoint(0.0F, -18.0F, 0.0F);
        this.shade.addChild(this.body);
        this.body.setTextureOffset(0, 16)
                .addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F);

        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(4.0F, 12.0F, -2.0F);
        this.rightArm.setRotationPoint(-5.5F, -22.5F, 0.0F);
        this.shade.addChild(this.rightArm);
        this.rightArm.setTextureOffset(32, 0)
                .addBox(-1.5F, -1.5F, -2.0F, 3.0F, 12.0F, 4.0F);

        this.rightArm.rotateAngleZ = 1.5708F;

        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(-7.0F, 12.0F, -2.0F);
        this.leftArm.setRotationPoint(5.5F, -22.5F, 0.0F);
        this.shade.addChild(this.leftArm);
        this.leftArm.setTextureOffset(16, 32)
                .addBox(-1.5F, -1.5F, -2.0F, 3.0F, 12.0F, 4.0F);

        this.leftArm.rotateAngleZ = -1.5708F;

        this.rightLeg = new AdvancedModelBox(this, "rightLeg");
        this.rightLeg.setPos(0.0F, 0.0F, -2.0F);
        this.rightLeg.setRotationPoint(-2.0F, -10.0F, 0.0F);
        this.shade.addChild(this.rightLeg);
        this.rightLeg.setTextureOffset(24, 16)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        this.leftLeg = new AdvancedModelBox(this, "leftLeg");
        this.leftLeg.setPos(-4.0F, 0.0F, -2.0F);
        this.leftLeg.setRotationPoint(2.0F, -10.0F, 0.0F);
        this.shade.addChild(this.leftLeg);
        this.leftLeg.setTextureOffset(0, 32)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(
                this.shade,
                this.head,
                this.body,
                this.rightArm,
                this.leftArm,
                this.rightLeg,
                this.leftLeg);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.shade);
    }

    @Override
    public void setupAnim(FrozenShadeEntity pEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        resetToDefaultPose();
        animate(pEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.shade.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void animate(ShadeEntity shade, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(shade);

        this.head.rotateAngleY = pNetHeadYaw * 0.017453292F;
        this.head.rotateAngleX = pHeadPitch * 0.017453292F;

        if (shade.getAnimation() != ShadeEntity.REPULSE_ATTACK_ANIMATION) {
            float animationSpeed = (float) (2 * Math.PI / 40);
            float wave = (float) Math.sin(pAgeInTicks * animationSpeed);
            float waveAmount = (float) Math.toRadians(5);

            this.rightArm.rotateAngleZ = 1.5708F + (wave * -waveAmount);
            this.leftArm.rotateAngleZ = -1.5708F + (wave * waveAmount);
        }

        // Repulse Attack Animation
        if (this.animator.setAnimation(ShadeEntity.REPULSE_ATTACK_ANIMATION) && shade.isAlive()) {

            animator.startKeyframe(30);
            animator.move(head, 0, 1, -1.5F);
            this.rotate(animator, body, 27.5F, 0, 0);
            animator.move(body, 0, 0.5F,0);
            animator.move(rightLeg, 0, 0, 2.5F);
            animator.move(leftLeg, 0, 0, 2.5F);
            animator.move(rightArm, 0, 0, -1.5F);
            animator.move(leftArm, 0, 0, -1.5F);
            animator.setStaticKeyframe(65);

            this.rotate(animator, rightArm, 0, 0, 50);
            this.rotate(animator, leftArm, 0, 0, -50);
            animator.endKeyframe();

            animator.move(head, 0, 1, -1.5F);
            this.rotate(animator, body, 27.5F, 0, 0);
            animator.move(body, 0, 0.5F,0);
            animator.move(rightLeg, 0, 0, 2.5F);
            animator.move(leftLeg, 0, 0, 2.5F);
            animator.move(rightArm, 0, 0, -1.5F);
            animator.move(leftArm, 0, 0, -1.5F);

            animator.startKeyframe(5);
            this.rotate(animator, rightArm, -90, 0, 0);
            this.rotate(animator, leftArm, -90, 0, 0);
            animator.endKeyframe();
            animator.setStaticKeyframe(60);

            animator.resetKeyframe(20);
        }
    }
}
