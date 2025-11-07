package com.syming.cursedcastle.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GrimReaperModel extends AdvancedEntityModel<GrimReaperEntity> implements ArmedModel {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation GRIM_REAPER_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "grim_reaper"), "main");
//    protected final AdvancedModelBox grimReaper;
    public final AdvancedModelBox head;
    public final AdvancedModelBox headWear;
    public final AdvancedModelBox body;
    public final AdvancedModelBox rightArm;
    public final AdvancedModelBox rightArmWear;
    public final AdvancedModelBox leftArm;
    public final AdvancedModelBox leftArmWear;

    public GrimReaperArmPose rightArmPose;

    public ModelAnimator animator;

    public GrimReaperModel() {
        this.rightArmPose = GrimReaperArmPose.DEATH_SCYTHE;

        this.texWidth = 64;
        this.texHeight = 64;

//        this.grimReaper = new AdvancedModelBox(this, "grimReaper");
//        this.grimReaper.setPos(0.0F, 24.0F, 0.0F);

        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(0.0F, -4.0F, 0.0F);
        this.head.setTextureOffset(24, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);
//        this.grimReaper.addChild(this.head);

        this.headWear = new AdvancedModelBox(this, "headWear");
        this.headWear.setPos(0.0F, -4.0F, 0.0F);
        this.headWear.setTextureOffset(24, 16)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F);
//        this.grimReaper.addChild(this.headWear);

        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(0.0F, 12.0F, 0.0F);
        this.body.setTextureOffset(0, 0)
                .addBox(-4.0F, -12.0F, -2.0F, 8.0F, 24.0F, 4.0F);
//        this.grimReaper.addChild(this.body);

        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(-5.0F, 2.5F, 0.0F);
        this.rightArm.setTextureOffset(0, 28)
                .addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.rightArm.rotateAngleX = -0.4659F;
        this.rightArm.rotateAngleY = -0.1198F;
        this.rightArm.rotateAngleZ = -0.2333F;
//        this.grimReaper.addChild(this.rightArm);

        this.rightArmWear = new AdvancedModelBox(this, "rightArmWear");
        this.rightArmWear.setPos(-5.0F, 2.5F, 0.0F);
        this.rightArmWear.setTextureOffset(8, 28)
                .addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, 0.25F);
        this.rightArmWear.rotateAngleX = -0.4659F;
        this.rightArmWear.rotateAngleY = -0.1198F;
        this.rightArmWear.rotateAngleZ = -0.2333F;
//        this.grimReaper.addChild(this.rightArmWear);

        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(5.0F, 2.5F, 0.0F);
        this.leftArm.setTextureOffset(0, 28)
                .addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F);
        this.leftArm.mirror = true;
        this.leftArm.rotateAngleX = -1.0321F;
        this.leftArm.rotateAngleY = 0.2261F;
        this.leftArm.rotateAngleZ = 0.1332F;
//        this.grimReaper.addChild(this.leftArm);

        this.leftArmWear = new AdvancedModelBox(this, "leftArmWear");
        this.leftArmWear.setPos(5.0F, 2.5F, 0.0F);
        this.leftArmWear.setTextureOffset(8, 28)
                .addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, 0.25F);
        this.leftArmWear.mirror = true;
        this.leftArmWear.rotateAngleX = -1.0321F;
        this.leftArmWear.rotateAngleY = 0.2261F;
        this.leftArmWear.rotateAngleZ = 0.1332F;
//        this.grimReaper.addChild(this.leftArmWear);

        this.animator = ModelAnimator.create();

        updateDefaultPose();
    }

//    public static LayerDefinition createBodyLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//
//        PartDefinition grimReaper = partdefinition.addOrReplaceChild("grimReaper", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
//
//        PartDefinition head = grimReaper.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));
//
//        PartDefinition headWear = grimReaper.addOrReplaceChild("headWear", CubeListBuilder.create().texOffs(24, 16).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -28.0F, 0.0F));
//
//        PartDefinition body = grimReaper.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));
//
//        PartDefinition rightArm = grimReaper.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.5F, 0.0F, -0.4659F, -0.1198F, -0.2333F));
//
//        PartDefinition rightArmWear = grimReaper.addOrReplaceChild("rightArmWear", CubeListBuilder.create().texOffs(8, 28).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, -22.5F, 0.0F, -0.4659F, -0.1198F, -0.2333F));
//
//        PartDefinition leftArm = grimReaper.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -22.5F, 0.0F, -1.0321F, 0.2261F, 0.1332F));
//
//        PartDefinition leftArmWear = grimReaper.addOrReplaceChild("leftArmWear", CubeListBuilder.create().texOffs(8, 28).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(5.0F, -22.5F, 0.0F, -1.0321F, 0.2261F, 0.1332F));
//
//        return LayerDefinition.create(meshdefinition, 64, 64);
//    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(/*this.grimReaper*/
                this.head,
                this.headWear,
                this.body,
                this.rightArm,
                this.rightArmWear,
                this.leftArm,
                this.leftArmWear);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.head,
                this.headWear,
                this.body,
                this.rightArm,
                this.rightArmWear,
                this.leftArm,
                this.leftArmWear);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

//        this.grimReaper.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

        this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headWear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightArmWear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftArmWear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(GrimReaperEntity grimReaperEntity,float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        animate(grimReaperEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        faceTarget(pNetHeadYaw, pHeadPitch, 1.0F, this.head, this.headWear);
    }

    @Override
    public void translateToHand(HumanoidArm pHumanoidArm, PoseStack pPoseStack) {
        getArm(pHumanoidArm).translateRotate(pPoseStack);
    }

    private AdvancedModelBox getArm(HumanoidArm pHumanoidArm) {
        return pHumanoidArm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public GrimReaperArmPose getArmPose() {
        return GrimReaperArmPose.DEATH_SCYTHE;
    }

    public void animate(GrimReaperEntity grimReaper, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(grimReaper);

        switch (this.rightArmPose) {
            case EMPTY:

                this.rightArm.rotateAngleY = 0.0F;
                this.rightArmWear.rotateAngleY = 0.0F;
                break;

            case DEATH_SCYTHE:

                this.rightArm.rotateAngleX = -0.4659F;
                this.rightArmWear.rotateAngleX = -0.4659F;
                this.rightArm.rotateAngleY = -0.1198F;
                this.rightArmWear.rotateAngleY = -0.1198F;
                this.rightArm.rotateAngleZ = -0.2333F;
                this.rightArmWear.rotateAngleZ = -0.2333F;

                this.leftArm.rotateAngleX = -1.0321F;
                this.leftArmWear.rotateAngleX = -1.0321F;
                this.leftArm.rotateAngleY = 0.2261F;
                this.leftArmWear.rotateAngleY = 0.2261F;
                this.leftArm.rotateAngleZ = 0.1332F;
                this.leftArmWear.rotateAngleZ = 0.1332F;
        }

        if (grimReaper.getAnimation() != GrimReaperEntity.MELEE_ATTACK_ANIMATION || grimReaper.getAnimation() != GrimReaperEntity.SCYTHE_ATTACK_ANIMATION) {
            float waveSpeed = (float) (2 * Math.PI / 80);
            float waveAmplitude = (float) Math.toRadians(10);
            float baseAngle = (float) Math.toRadians(-45.0F);

            float wave = (float) Math.sin(pAgeInTicks * waveSpeed);
            float animatedAngle = baseAngle + wave * waveAmplitude;

            this.rightArm.rotateAngleX = animatedAngle;
            this.rightArmWear.rotateAngleX = animatedAngle;
            this.leftArm.rotateAngleX += animatedAngle;
            this.leftArmWear.rotateAngleX += animatedAngle;
        }

        // Melee Attack Animation
        if (this.animator.setAnimation(GrimReaperEntity.MELEE_ATTACK_ANIMATION)) {
            this.animator.startKeyframe(20);
            this.rotate(this.animator, this.rightArm, -58.2727F, -42.4019F, -3.4223F);
            this.rotate(this.animator, this.rightArmWear, -58.2727F, -42.4019F, -3.4223F);
            this.rotate(this.animator, this.leftArm, -83.2805F, -42.2102F, -15.1315F);
            this.rotate(this.animator, this.leftArmWear, -83.2805F, -42.2102F, -15.1315F);
            this.animator.endKeyframe();
            this.animator.startKeyframe(5);
            this.rotate(this.animator, this.rightArm, 25.017F, 9.9853F, 20.5789F);
            this.rotate(this.animator, this.rightArmWear, 25.017F, 9.9853F, 20.5789F);
            this.rotate(this.animator, this.leftArm, 22.9282F, -15.067F, 39.7721F);
            this.rotate(this.animator, this.leftArmWear, 22.9282F, -15.067F, 39.7721F);
            this.animator.endKeyframe();
            this.animator.resetKeyframe(10);
        } else if (this.animator.setAnimation(GrimReaperEntity.SCYTHE_ATTACK_ANIMATION)) {
            this.animator.startKeyframe(20);
            this.rotate(this.animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(this.animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);
            this.animator.setStaticKeyframe(80);
            this.animator.endKeyframe();

            this.rotate(this.animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(this.animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            this.animator.startKeyframe(80);
            this.animator.endKeyframe();

            this.rotate(this.animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(this.animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            this.animator.startKeyframe(20);
            this.animator.endKeyframe();

            this.rotate(this.animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(this.animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            this.animator.startKeyframe(20);
            this.animator.endKeyframe();

            this.rotate(this.animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(this.animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(this.animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            this.animator.startKeyframe(20);
            this.animator.endKeyframe();

            this.animator.resetKeyframe(20);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static enum GrimReaperArmPose {
        EMPTY(false),
        DEATH_SCYTHE(true);

        private final boolean twoHanded;

        private GrimReaperArmPose(boolean isTwoHanded) {
            this.twoHanded = isTwoHanded;
        }

        public boolean isTwoHanded() {
            return this.twoHanded;
        }
    }
}
