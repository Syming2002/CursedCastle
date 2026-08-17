package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.mob.HarpyEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class HarpyModel extends HierarchicalModel<HarpyEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation HARPY_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "harpy"), "main");

    private final ModelPart root;

    private final ModelPart harpy;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightThigh;
    private final ModelPart leftThigh;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart rightWing;
    private final ModelPart leftArm;
    private final ModelPart leftWing;

    public HarpyModel(ModelPart pRoot) {
        this.root = pRoot;

        this.harpy = pRoot.getChild("harpy");

        this.head = this.harpy.getChild("head");
        this.body = this.harpy.getChild("body");
        this.rightArm = this.harpy.getChild("rightArm");
        this.leftArm = this.harpy.getChild("leftArm");
        this.rightWing = this.rightArm.getChild("rightWing");
        this.leftWing = this.leftArm.getChild("leftWing");
        this.rightLeg = this.harpy.getChild("rightLeg");
        this.leftLeg = this.harpy.getChild("leftLeg");
        this.rightThigh = this.harpy.getChild("rightThigh");
        this.leftThigh = this.harpy.getChild("leftThigh");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition harpy = partdefinition.addOrReplaceChild("harpy", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = harpy.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -37.0F, -5.0854F));

        PartDefinition rightLeg = harpy.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(64, 27).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -16.0F, -4.0854F, 0.4363F, 0.0F, 0.0F));

        PartDefinition leftLeg = harpy.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(64, 27).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -16.0F, -4.0854F, 0.4363F, 0.0F, 0.0F));

        PartDefinition rightThigh = harpy.addOrReplaceChild("rightThigh", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, 0.5786F, -9.9278F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -23.5F, 1.1646F, 0.4363F, 0.0F, 0.0F));

        PartDefinition leftThigh = harpy.addOrReplaceChild("leftThigh", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-2.0F, -0.5391F, -9.9583F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -22.5F, 1.6646F, 0.4363F, 0.0F, 0.0F));

        PartDefinition body = harpy.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 56).addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -30.2566F, -1.9063F, 0.4363F, 0.0F, 0.0F));

        PartDefinition rightArm = harpy.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(56, 56).addBox(-2.5F, -0.5F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -36.25F, -4.5854F, 0.1412F, 0.0594F, 2.264F));

        PartDefinition rightWing = rightArm.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -0.5F, 0.0F, 0.0F, 24.0F, 16.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftArm = harpy.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(56, 56).mirror().addBox(-0.5F, -0.5F, -1.5F, 3.0F, 24.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5F, -36.25F, -4.5854F, 0.1412F, -0.0594F, -2.264F));

        PartDefinition leftWing = leftArm.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2.5F, -0.5F, 0.0F, 0.0F, 24.0F, 16.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(HarpyEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = pNetHeadYaw * 0.017453292F;
        this.head.xRot = pHeadPitch * 0.017453292F;

        if (pEntity.getAnimation() != HarpyEntity.FEATHER_ATTACK_ANIMATION) {
            float wingFlapAngle = Math.abs((float) Math.cos(0.275F * pAgeInTicks)) * 2.5F;

            this.rightArm.zRot = wingFlapAngle;
            this.leftArm.zRot = -wingFlapAngle;
        }

        animate(pEntity.featherAnimationState, CCAnimation.HARPY_FEATHER_ATTACK_ANIMATION, pAgeInTicks);
        animate(pEntity.daggerAnimationState, CCAnimation.HARPY_DAGGER_ATTACK_ANIMATION, pAgeInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.harpy.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
