package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.monster.boss.dracula.DraculaEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class DraculaModel extends HierarchicalModel<DraculaEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation DRACULA_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "dracula"), "main");
    private final ModelPart dracula;
    private final ModelPart head;
    private final ModelPart headWear;
    private final ModelPart body;
    private final ModelPart bodyWear;
    private final ModelPart rightArm;
    private final ModelPart rightArmWear;
    private final ModelPart leftArm;
    private final ModelPart leftArmWear;
    private final ModelPart rightLeg;
    private final ModelPart rightLegWear;
    private final ModelPart leftLeg;
    private final ModelPart leftLegWear;

    public DraculaModel(ModelPart pRoot) {
        this.dracula = pRoot.getChild("dracula");

        this.head = this.dracula.getChild("head");
        this.headWear = this.dracula.getChild("headWear");
        this.body = this.dracula.getChild("body");
        this.bodyWear = this.dracula.getChild("bodyWear");
        this.rightArm = this.dracula.getChild("rightArm");
        this.leftArm = this.dracula.getChild("leftArm");
        this.rightArmWear = this.dracula.getChild("rightArmWear");
        this.leftArmWear = this.dracula.getChild("leftArmWear");
        this.rightLeg = this.dracula.getChild("rightLeg");
        this.leftLeg = this.dracula.getChild("leftLeg");
        this.rightLegWear = this.dracula.getChild("rightLegWear");
        this.leftLegWear = this.dracula.getChild("leftLegWear");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();

        PartDefinition dracula = root.addOrReplaceChild(
                "dracula",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -4.0F,
                                8.0F, 8.0F, 8.0F),
                PartPose.offset(0.0F, -36.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "headWear",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-4.0F, -4.0F, -4.0F,
                                8.0F, 8.0F, 8.0F,
                                new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, -36.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 32)
                        .addBox(-4.0F, -8.0F, -2.0F,
                                8.0F, 16.0F, 4.0F),
                PartPose.offset(0.0F, -24.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "bodyWear",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, -8.0F, -2.0F,
                                8.0F, 16.0F, 4.0F,
                                new CubeDeformation(0.25F)),
                PartPose.offset(0.0F, -24.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "rightArm",
                CubeListBuilder.create()
                        .texOffs(0, 52)
                        .addBox(-2.0F, -2.0F, -2.0F,
                                4.0F, 14.0F, 4.0F),
                PartPose.offset(-6.0F, -30.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "rightArmWear",
                CubeListBuilder.create()
                        .texOffs(56, 0)
                        .addBox(-2.0F, -2.0F, -2.0F,
                                4.0F, 14.0F, 4.0F,
                                new CubeDeformation(0.26F)),
                PartPose.offset(-6.0F, -30.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "leftArm",
                CubeListBuilder.create()
                        .texOffs(56, 40)
                        .addBox(-2.0F, -2.0F, -2.0F,
                                4.0F, 14.0F, 4.0F),
                PartPose.offset(6.0F, -30.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "leftArmWear",
                CubeListBuilder.create()
                        .texOffs(56, 58)
                        .addBox(-2.0F, -2.0F, -2.0F,
                                4.0F, 14.0F, 4.0F,
                                new CubeDeformation(0.26F)),
                PartPose.offset(6.0F, -30.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "rightLeg",
                CubeListBuilder.create()
                        .texOffs(32, 20)
                        .addBox(-2.0F, -1.0F, -2.0F,
                                4.0F, 16.0F, 4.0F),
                PartPose.offset(-2.0F, -15.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "rightLegWear",
                CubeListBuilder.create()
                        .texOffs(24, 40)
                        .addBox(-2.0F, -1.0F, -2.0F,
                                4.0F, 16.0F, 4.0F,
                                new CubeDeformation(0.25F)),
                PartPose.offset(-2.0F, -15.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "leftLeg",
                CubeListBuilder.create()
                        .texOffs(40, 40)
                        .addBox(-2.0F, -1.0F, -2.0F,
                                4.0F, 16.0F, 4.0F),
                PartPose.offset(2.0F, -15.0F, 0.0F)
        );

        dracula.addOrReplaceChild(
                "leftLegWear",
                CubeListBuilder.create()
                        .texOffs(48, 20)
                        .addBox(-2.0F, -1.0F, -2.0F,
                                4.0F, 16.0F, 4.0F,
                                new CubeDeformation(0.25F)),
                PartPose.offset(2.0F, -15.0F, 0.0F)
        );

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.dracula.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.dracula;
    }

    @Override
    public void setupAnim(DraculaEntity dracula, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.headWear.yRot = pNetHeadYaw * ((float)Math.PI / 180F);

        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        this.headWear.xRot = pHeadPitch * ((float)Math.PI / 180F);

        animate(dracula.idleAnimationState, CCAnimation.DRACULA_IDLE_ANIMATION, pAgeInTicks);
        animate(dracula.fireballsAnimationState, CCAnimation.DRACULA_FIREBALLS_ATTACK_ANIMATION, pAgeInTicks);
        animate(dracula.darkInfernoAnimationState, CCAnimation.DRACULA_DARK_INFERNO_ATTACK_ANIMATION, pAgeInTicks);
        animate(dracula.firePillarAnimationState, CCAnimation.DRACULA_FIRE_PILLAR_ATTACK_ANIMATION, pAgeInTicks);

        if (dracula.getAnimation() == DraculaEntity.IDLE_ANIMATION || dracula.hurtMarked) {

            float f = 1.0F;

            this.rightArm.xRot = Mth.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 2.0F * pLimbSwingAmount * 0.5F / f;
            this.leftArm.xRot = Mth.cos(pLimbSwing * 0.6662F) * 2.0F * pLimbSwingAmount * 0.5F / f;
            this.rightArm.zRot = 0.0F;
            this.leftArm.zRot = 0.0F;

            this.rightArm.yRot = 0.0F;
            this.leftArm.yRot = 0.0F;

            this.rightArmWear.xRot = Mth.cos(pLimbSwing * 0.6662F + (float) Math.PI) * 2.0F * pLimbSwingAmount * 0.5F / f;
            this.leftArmWear.xRot = Mth.cos(pLimbSwing * 0.6662F) * 2.0F * pLimbSwingAmount * 0.5F / f;
            this.rightArmWear.zRot = 0.0F;
            this.leftArmWear.zRot = 0.0F;

            this.rightArmWear.yRot = 0.0F;
            this.leftArmWear.yRot = 0.0F;
        }
    }
}
