package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.mob.ArachneEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ArachneModel extends HierarchicalModel<ArachneEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation ARACHNE_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "arachne"), "main");
    private final ModelPart arachne;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontLeg2;
    private final ModelPart rightFirstMiddleLeg;
    private final ModelPart rightFirstMiddleLeg2;
    private final ModelPart rightSecondMiddleLeg;
    private final ModelPart rightSecondMiddleLeg2;
    private final ModelPart rightBackLeg;
    private final ModelPart rightBackLeg2;
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontLeg2;
    private final ModelPart leftFirstMiddleLeg;
    private final ModelPart leftFirstMiddleLeg2;
    private final ModelPart leftSecondMiddleLeg;
    private final ModelPart leftSecondMiddleLeg2;
    private final ModelPart leftBackLeg;
    private final ModelPart leftBackLeg2;

    public ArachneModel(ModelPart root) {
        this.arachne = root.getChild("arachne");
        this.head = this.arachne.getChild("head");
        this.body = this.arachne.getChild("body");
        this.rightArm = this.arachne.getChild("rightArm");
        this.leftArm = this.arachne.getChild("leftArm");
        this.rightFrontLeg = this.arachne.getChild("rightFrontLeg");
        this.rightFrontLeg2 = this.rightFrontLeg.getChild("rightFrontLeg2");
        this.rightFirstMiddleLeg = this.arachne.getChild("rightFirstMiddleLeg");
        this.rightFirstMiddleLeg2 = this.rightFirstMiddleLeg.getChild("rightFirstMiddleLeg2");
        this.rightSecondMiddleLeg = this.arachne.getChild("rightSecondMiddleLeg");
        this.rightSecondMiddleLeg2 = this.rightSecondMiddleLeg.getChild("rightSecondMiddleLeg2");
        this.rightBackLeg = this.arachne.getChild("rightBackLeg");
        this.rightBackLeg2 = this.rightBackLeg.getChild("rightBackLeg2");
        this.leftFrontLeg = this.arachne.getChild("leftFrontLeg");
        this.leftFrontLeg2 = this.leftFrontLeg.getChild("leftFrontLeg2");
        this.leftFirstMiddleLeg = this.arachne.getChild("leftFirstMiddleLeg");
        this.leftFirstMiddleLeg2 = this.leftFirstMiddleLeg.getChild("leftFirstMiddleLeg2");
        this.leftSecondMiddleLeg = this.arachne.getChild("leftSecondMiddleLeg");
        this.leftSecondMiddleLeg2 = this.leftSecondMiddleLeg.getChild("leftSecondMiddleLeg2");
        this.leftBackLeg = this.arachne.getChild("leftBackLeg");
        this.leftBackLeg2 = this.leftBackLeg.getChild("leftBackLeg2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition arachne = partdefinition.addOrReplaceChild("arachne", CubeListBuilder.create(), PartPose.offset(0.0F, 9.7333F, -1.907F));

        PartDefinition head = arachne.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.7333F, -6.25F));

        PartDefinition body = arachne.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -0.9167F, -1.6667F, 12.0F, 8.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(32, 26).addBox(-5.0F, -0.1667F, -6.6667F, 10.0F, 6.0F, 6.0F, new CubeDeformation(-0.25F))
                .texOffs(32, 38).addBox(-4.0F, -11.9167F, -6.1667F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.8167F, -2.5833F));

        PartDefinition rightArm = arachne.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(32, 55).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -15.7333F, -6.25F, -0.7418F, 0.0F, 0.0F));

        PartDefinition leftArm = arachne.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(32, 55).mirror().addBox(-1.5F, -1.0F, -1.5F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, -15.7333F, -6.25F, -0.7418F, 0.0F, 0.0F));

        PartDefinition rightFrontLeg = arachne.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(0, 58).addBox(-7.1309F, -1.7512F, -0.9548F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-3.0F, 0.2667F, -7.25F, 0.3263F, -0.8328F, -0.4333F));

        PartDefinition rightFrontLeg2 = rightFrontLeg.addOrReplaceChild("rightFrontLeg2", CubeListBuilder.create().texOffs(0, 42).addBox(-13.8904F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.8809F, -1.5012F, 0.2952F, 0.8655F, -0.1842F, -1.4204F));

        PartDefinition rightFirstMiddleLeg = arachne.addOrReplaceChild("rightFirstMiddleLeg", CubeListBuilder.create().texOffs(58, 38).addBox(-7.9474F, -1.4706F, -0.8865F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-5.5F, 0.1667F, -1.75F, 0.2174F, 0.0126F, -0.1852F));

        PartDefinition rightFirstMiddleLeg2 = rightFirstMiddleLeg.addOrReplaceChild("rightFirstMiddleLeg2", CubeListBuilder.create().texOffs(0, 46).addBox(-13.8904F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.6974F, -1.2206F, 0.3635F, 0.8655F, -0.1842F, -1.4204F));

        PartDefinition rightSecondMiddleLeg = arachne.addOrReplaceChild("rightSecondMiddleLeg", CubeListBuilder.create().texOffs(58, 42).addBox(-7.9474F, -1.4706F, -0.8865F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-5.5F, 0.1667F, 6.25F, 0.2174F, 0.0126F, -0.1852F));

        PartDefinition rightSecondMiddleLeg2 = rightSecondMiddleLeg.addOrReplaceChild("rightSecondMiddleLeg2", CubeListBuilder.create().texOffs(0, 50).addBox(-13.8904F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.6974F, -1.2206F, 0.3635F, 0.8655F, -0.1842F, -1.4204F));

        PartDefinition rightBackLeg = arachne.addOrReplaceChild("rightBackLeg", CubeListBuilder.create().texOffs(44, 55).addBox(-7.9474F, -1.4706F, -0.8865F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-5.5F, 0.8667F, 13.25F, 0.2319F, 0.3531F, -0.1065F));

        PartDefinition rightBackLeg2 = rightBackLeg.addOrReplaceChild("rightBackLeg2", CubeListBuilder.create().texOffs(0, 54).addBox(-13.8904F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.6974F, -1.2206F, 0.3635F, 0.8655F, -0.1842F, -1.4204F));

        PartDefinition leftFrontLeg = arachne.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(0, 58).mirror().addBox(-0.8691F, -1.7512F, -0.9548F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.2667F, -7.25F, 0.3263F, 0.8328F, 0.4333F));

        PartDefinition leftFrontLeg2 = leftFrontLeg.addOrReplaceChild("leftFrontLeg2", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(-0.1096F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.8809F, -1.5012F, 0.2952F, 0.8655F, 0.1842F, 1.4204F));

        PartDefinition leftFirstMiddleLeg = arachne.addOrReplaceChild("leftFirstMiddleLeg", CubeListBuilder.create().texOffs(58, 38).mirror().addBox(-0.0526F, -1.4706F, -0.8865F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.1667F, -1.75F, 0.2174F, -0.0126F, 0.1852F));

        PartDefinition leftFirstMiddleLeg2 = leftFirstMiddleLeg.addOrReplaceChild("leftFirstMiddleLeg2", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-0.1096F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.6974F, -1.2206F, 0.3635F, 0.8655F, 0.1842F, 1.4204F));

        PartDefinition leftSecondMiddleLeg = arachne.addOrReplaceChild("leftSecondMiddleLeg", CubeListBuilder.create().texOffs(58, 42).mirror().addBox(-0.0526F, -1.4706F, -0.8865F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.1667F, 6.25F, 0.2174F, -0.0126F, 0.1852F));

        PartDefinition leftSecondMiddleLeg2 = leftSecondMiddleLeg.addOrReplaceChild("leftSecondMiddleLeg2", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-0.1096F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.6974F, -1.2206F, 0.3635F, 0.8655F, 0.1842F, 1.4204F));

        PartDefinition leftBackLeg = arachne.addOrReplaceChild("leftBackLeg", CubeListBuilder.create().texOffs(44, 55).mirror().addBox(-0.0526F, -1.4706F, -0.8865F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.8667F, 13.25F, 0.2319F, -0.3531F, 0.1065F));

        PartDefinition leftBackLeg2 = leftBackLeg.addOrReplaceChild("leftBackLeg2", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(-0.1096F, -1.1828F, -0.9225F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.6974F, -1.2206F, 0.3635F, 0.8655F, 0.1842F, 1.4204F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.arachne.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.arachne;
    }

    @Override
    public void setupAnim(ArachneEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);

        this.animateWalk(CCAnimation.ARACHNE_WALK_ANIMATION, pLimbSwing, pLimbSwingAmount, 2.0F, 2.5F);
        this.animate(pEntity.idleAnimationState, CCAnimation.ARACHNE_IDLE_ANIMATION, pAgeInTicks);
        this.animate(pEntity.clawsAttackAnimationState, CCAnimation.ARACHNE_CLAWS_ATTACK_ANIMATION, pAgeInTicks);
        this.animate(pEntity.webAttackAnimationState, CCAnimation.ARACHNE_WEB_ATTACK_ANIMATION, pAgeInTicks);
    }
}
