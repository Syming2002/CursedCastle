package com.syming.cursedcastle.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.HarpyEntity;
import com.syming.cursedcastle.entity.mob.shade.ShadeEntity;
import com.syming.cursedcastle.entity.mob.shade.ThunderShadeEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class HarpyModel extends AdvancedEntityModel<HarpyEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation HARPY_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "harpy"), "main");
    private final AdvancedModelBox harpy;
    private final AdvancedModelBox head;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightThigh;
    private final AdvancedModelBox leftThigh;
    private final AdvancedModelBox body;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox rightWing;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox leftWing;

    public ModelAnimator animator;

    public HarpyModel() {
        this.texHeight = 128;
        this.texWidth = 128;

        // Root part
        this.harpy = new AdvancedModelBox(this, "harpy");
        this.harpy.setPos(0.0F, 24.0F, 0.0F);

        // Head
        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(0.0F, -37.0F, -5.0854F);
        this.head.setTextureOffset(0, 56)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.001F);
        this.harpy.addChild(this.head);

        // Right Leg
        this.rightLeg = new AdvancedModelBox(this, "rightLeg");
        this.rightLeg.setPos(-2.0F, -16.0F, -4.0854F);
        this.rightLeg.setTextureOffset(64, 27)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F);
        this.rightLeg.rotateAngleX = 0.4363F;
        this.rightLeg.rotateAngleY = 0.0F;
        this.rightLeg.rotateAngleZ = 0.0F;
        this.harpy.addChild(this.rightLeg);

        // Left Leg
        this.leftLeg = new AdvancedModelBox(this, "leftLeg");
        this.leftLeg.setPos(2.0F, -16.0F, -4.0854F);
        this.leftLeg.setTextureOffset(64, 27)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F);
        this.leftLeg.rotateAngleX = 0.4363F;
        this.leftLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;
        this.leftLeg.mirror = true;
        this.harpy.addChild(this.leftLeg);

        // Right Thigh
        this.rightThigh = new AdvancedModelBox(this, "rightThigh");
        this.rightThigh.setPos(-2.0F, -23.5F, 1.1646F);
        this.rightThigh.setTextureOffset(0, 40)
                .addBox(-2.0F, 0.5786F, -9.9278F, 4.0F, 4.0F, 12.0F);
        this.rightThigh.rotateAngleX = 0.4363F;
        this.rightThigh.rotateAngleY = 0.0F;
        this.rightThigh.rotateAngleZ = 0.0F;
        this.harpy.addChild(this.rightThigh);

        // Left Thigh
        this.leftThigh = new AdvancedModelBox(this, "leftThigh");
        this.leftThigh.setPos(2.0F, -22.5F, 1.6646F);
        this.leftThigh.setTextureOffset(0, 40)
                .addBox(-2.0F, -0.5391F, -9.9583F, 4.0F, 4.0F, 12.0F);
        this.leftThigh.rotateAngleX = 0.4363F;
        this.leftThigh.rotateAngleY = 0.0F;
        this.leftThigh.rotateAngleZ = 0.0F;
        this.leftThigh.mirror = true;
        this.harpy.addChild(this.leftThigh);

        // Body
        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(0.0F, -30.2566F, -1.9063F);
        this.body.setTextureOffset(32, 56)
                .addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 4.0F);
        this.body.rotateAngleX = 0.4363F;
        this.body.rotateAngleY = 0.0F;
        this.body.rotateAngleZ = 0.0F;
        this.harpy.addChild(this.body);

        // Right Arm
        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(-4.5F, -36.25F, -4.5854F);
        this.rightArm.setTextureOffset(56, 56)
                .addBox(-2.5F, -0.5F, -1.5F, 3.0F, 24.0F, 3.0F);
        this.rightArm.rotateAngleX = 0.1412F;
        this.rightArm.rotateAngleY = 0.0594F;
        this.rightArm.rotateAngleZ = 2.264F;
        this.harpy.addChild(this.rightArm);

        // Right Wing
        this.rightWing = new AdvancedModelBox(this, "rightWing");
        this.rightWing.setPos(0.0F, 0.0F, 0.0F);
        this.rightWing.setTextureOffset(0, 0)
                .addBox(-2.5F, -0.5F, 0.0F, 0.0F, 24.0F, 16.0F, 0.001F);
        this.rightArm.addChild(this.rightWing);

        // Left Arm
        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(4.5F, -36.25F, -4.5854F);
        this.leftArm.setTextureOffset(56, 56)
                .addBox(-0.5F, -0.5F, -1.5F, 3.0F, 24.0F, 3.0F);
        this.leftArm.rotateAngleX = 0.1412F;
        this.leftArm.rotateAngleY = -0.0594F;
        this.leftArm.rotateAngleZ = -2.264F;
        this.leftArm.mirror = true;
        this.harpy.addChild(this.leftArm);

        // Left Wing
        this.leftWing = new AdvancedModelBox(this, "leftWing");
        this.leftWing.setPos(0.0F, 0.0F, 0.0F);
        this.leftWing.setTextureOffset(0, 0)
                .addBox(2.5F, -0.5F, 0.0F, 0.0F, 24.0F, 16.0F, 0.001F);
        this.leftWing.mirror = true;
        this.leftArm.addChild(this.leftWing);

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

//    public static LayerDefinition createBodyLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//
//        PartDefinition harpy = partdefinition.addOrReplaceChild("harpy", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
//
//        PartDefinition head = harpy.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -37.0F, -5.0854F));
//
//        PartDefinition rightLeg = harpy.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(64, 27).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -16.0F, -4.0854F, 0.4363F, 0.0F, 0.0F));
//
//        PartDefinition leftLeg = harpy.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(64, 27).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -16.0F, -4.0854F, 0.4363F, 0.0F, 0.0F));
//
//        PartDefinition rightThigh = harpy.addOrReplaceChild("rightThigh", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, 0.5786F, -9.9278F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -23.5F, 1.1646F, 0.4363F, 0.0F, 0.0F));
//
//        PartDefinition leftThigh = harpy.addOrReplaceChild("leftThigh", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, -0.5391F, -9.9583F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -22.5F, 1.6646F, 0.4363F, 0.0F, 0.0F));
//
//        PartDefinition body = harpy.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 56).addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -30.2566F, -1.9063F, 0.4363F, 0.0F, 0.0F));
//
//        PartDefinition rightArm = harpy.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(56, 56).addBox(-2.5F, -0.5F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -36.25F, -4.5854F, 0.1412F, 0.0594F, 2.264F));
//
//        PartDefinition rightWing = rightArm.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -0.5F, 0.0F, 0.0F, 24.0F, 16.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//        PartDefinition leftArm = harpy.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(56, 56).addBox(-0.5F, -0.5F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -36.25F, -4.5854F, 0.1412F, -0.0594F, -2.264F));
//
//        PartDefinition leftWing = leftArm.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 0).addBox(2.5F, -0.5F, 0.0F, 0.0F, 24.0F, 16.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//        return LayerDefinition.create(meshdefinition, 128, 128);
//    }

    @Override
    public void setupAnim(HarpyEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        animate(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.harpy.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.harpy);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(this.harpy,
                this.head,
                this.body,
                this.rightArm,
                this.leftArm,
                this.rightWing,
                this.leftWing,
                this.rightThigh,
                this.leftThigh,
                this.rightLeg,
                this.leftLeg);
    }

    public void animate(HarpyEntity harpy, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(harpy);

        this.head.rotateAngleY = pNetHeadYaw * 0.017453292F;
        this.head.rotateAngleX = pHeadPitch * 0.017453292F;

        if (this.animator.setAnimation(HarpyEntity.DAGGER_ATTACK_ANIMATION)) {
            this.animator.startKeyframe(25);
            this.rotate(this.animator, this.harpy, -15.0F, 0, 0);
            this.animator.endKeyframe();

            this.animator.startKeyframe(7);
            this.rotate(this.animator, this.harpy, 360, 0, 0);
            this.animator.endKeyframe();
        }
        if (this.animator.setAnimation(HarpyEntity.FEATHER_ATTACK_ANIMATION)) {
            this.animator.startKeyframe(20);
            this.rotate(this.animator, this.rightArm, 51.2193380833F, -7.2766554875F, -68.429738455F);
            this.rotate(this.animator, this.leftArm, 51.2193380833F, 7.2766554875F, 68.429738455F);
            this.animator.endKeyframe();

            this.animator.startKeyframe(2);
            this.rotate(this.animator, this.rightArm, -62.35F, 13.66F, -80.95F);
            this.rotate(this.animator, this.leftArm, -62.35F, -13.66F, 80.95F);
            this.animator.endKeyframe();

            this.animator.resetKeyframe(13);
        }

        if (harpy.getAnimation() != HarpyEntity.FEATHER_ATTACK_ANIMATION) {
            float wingFlapAngle = Math.abs((float) Math.cos(0.275F * pAgeInTicks)) * 2.5F;

            this.rightArm.rotateAngleZ = wingFlapAngle;
            this.leftArm.rotateAngleZ = -wingFlapAngle;
        }
    }
}
