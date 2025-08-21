package com.syming.cursedcastle.world.entity.client.dracula;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.world.entity.mob.boss.dracula.DraculaEntity;


public class DraculaModel extends AdvancedEntityModel<DraculaEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "dracula"), "main");
    private final AdvancedModelBox root;
    private final AdvancedModelBox head;
    private final AdvancedModelBox headWear;
    private final AdvancedModelBox body;
    private final AdvancedModelBox bodyWear;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox rightArmWear;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox leftArmWear;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox rightLegWear;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox leftLegWear;

    public ModelAnimator animator;

    public DraculaModel() {
        this.texWidth = 128;
        this.texHeight = 128;

        this.root = new AdvancedModelBox(this, "root");
        this.root.setPos(0.0F, 24.0F, 0.0F);

        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(0.0F, -36.0F, 0.0F);
        this.root.addChild(this.head);
        this.head.setTextureOffset(0, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);

        this.headWear = new AdvancedModelBox(this, "headWear");
        this.headWear.setPos(0.0F, -36.0F, 0.0F);
        this.root.addChild(this.headWear);
        this.headWear.setTextureOffset(0, 16)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F);

        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(0.0F, -24.0F, 0.0F);
        this.root.addChild(this.body);
        this.body.setTextureOffset(0, 32)
                .addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 4.0F);

        this.bodyWear = new AdvancedModelBox(this, "bodyWear");
        this.bodyWear.setPos(0.0F, -24.0F, 0.0F);
        this.root.addChild(this.bodyWear);
        this.bodyWear.setTextureOffset(32,0)
                .addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 4.0F, 0.25F);

        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(-6.0F, -30.0F, 0.0F);
        this.root.addChild(this.rightArm);
        this.rightArm.setTextureOffset(0, 52)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F);

        this.rightArmWear = new AdvancedModelBox(this, "rightArmWear");
        this.rightArmWear.setPos(-6.0F, -30.0F, 0.0F);
        this.root.addChild(this.rightArmWear);
        this.rightArmWear.setTextureOffset(56, 0)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.26F);

        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(6.0F, -30.0F, 0.0F);
        this.root.addChild(this.leftArm);
        this.leftArm.setTextureOffset(56, 40)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F);

        this.leftArmWear = new AdvancedModelBox(this, "leftArmWear");
        this.leftArmWear.setPos(6.0F, -30.0F, 0.0F);
        this.root.addChild(this.leftArmWear);
        this.leftArmWear.setTextureOffset(56, 58)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.26F);

        this.rightLeg = new AdvancedModelBox(this, "rightLeg");
        this.rightLeg.setPos(-2.0F, -15.0F, 0.0F);
        this.root.addChild(this.rightLeg);
        this.rightLeg.setTextureOffset(32, 20)
                .addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F);

        this.rightLegWear = new AdvancedModelBox(this, "rightLegWear");
        this.rightLegWear.setPos(-2.0F, -15.0F, 0.0F);
        this.root.addChild(this.rightLegWear);
        this.rightLegWear.setTextureOffset(24, 40)
                .addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.25F);

        this.leftLeg = new AdvancedModelBox(this, "leftLeg");
        this.leftLeg.setPos(2.0F, -15.0F, 0.0F);
        this.root.addChild(this.leftLeg);
        this.leftLeg.setTextureOffset(40, 40)
                .addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F);

        this.leftLegWear = new AdvancedModelBox(this, "leftLegWear");
        this.leftLegWear.setPos(2.0F, -15.0F, 0.0F);
        this.root.addChild(this.leftLegWear);
        this.leftLegWear.setTextureOffset(48, 20)
                .addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.25F);

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.root);
    }

    @Override
    public void setupAnim(DraculaEntity dracula, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        animate(dracula, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        faceTarget(pNetHeadYaw, pHeadPitch, 1.0F, this.head, this.headWear);
    }

    public void animate(DraculaEntity dracula, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(dracula);

        // Fireballs Attack Animation
        if (this.animator.setAnimation(DraculaEntity.FIREBALLS_ANIMATION)) {
            this.animator.startKeyframe(20);
            this.animator.rotate(rightArm, 0, 0, (float) Math.toRadians(87.5F));
            this.animator.rotate(rightArmWear, 0, 0, (float) Math.toRadians(87.5F));
            this.animator.setStaticKeyframe(40);
            this.animator.endKeyframe();
            this.animator.resetKeyframe(20);
        }


        // Dark Inferno Attack Animation
        if (this.animator.setAnimation(DraculaEntity.DARK_INFERNO_ANIMATION)) {
            this.animator.startKeyframe(20);
            this.animator.rotate(leftArm, 0, 0, (float) Math.toRadians(-87.5F));
            this.animator.rotate(leftArmWear, 0, 0, (float) Math.toRadians(-87.5F));
            this.animator.setStaticKeyframe(40);
            this.animator.endKeyframe();
            this.animator.resetKeyframe(20);
        }

        // Fire Pillar Attack Animation
        if (this.animator.setAnimation(DraculaEntity.FIRE_PILLAR_ANIMATION)) {
            this.animator.startKeyframe(20);
            this.animator.rotate(rightArm, 0, 0, (float) Math.toRadians(87.5F));
            this.animator.rotate(rightArmWear, 0, 0, (float) Math.toRadians(87.5F));
            this.animator.rotate(leftArm, 0, 0, (float) Math.toRadians(-87.5F));
            this.animator.rotate(leftArmWear, 0, 0, (float) Math.toRadians(-87.5F));
            this.animator.setStaticKeyframe(20);
            this.animator.endKeyframe();
            this.animator.resetKeyframe(20);
        }

    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(
                this.root,
                this.head,
                this.headWear,
                this.body,
                this.bodyWear,
                this.rightArm,
                this.rightArmWear,
                this.leftArm,
                this.leftArmWear,
                this.rightLeg,
                this.rightLegWear,
                this.leftLeg,
                this.leftLegWear);
    }
}
