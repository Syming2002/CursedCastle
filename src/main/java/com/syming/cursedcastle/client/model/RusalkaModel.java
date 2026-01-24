package com.syming.cursedcastle.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class RusalkaModel<T extends RusalkaEntity> extends AdvancedEntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation RUSALKA_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "rusalka"), "main");
    private final AdvancedModelBox rusalka;
    private final AdvancedModelBox head;
    private final AdvancedModelBox body;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox leftArm;

    private final ModelAnimator animator;

    public RusalkaModel() {
        this.texWidth = 64;
        this.texHeight = 64;

        this.rusalka = new AdvancedModelBox(this, "rusalka");
        this.rusalka.setPos(-0.4917F, 24.0F, 0.2357F);

        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(-0.4917F, -24.0F, 1.7357F);
        this.head.setTextureOffset(0, 0)
                .addBox(-3.5083F, -8.0F, -4.2357F, 8, 8, 8, 0.0F);
        this.rusalka.addChild(this.head);

        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(-0.4917F, -18.0F, 1.7357F);
        this.body.setTextureOffset(0, 16)
                .addBox(-2.5083F, -6.0F, -1.7357F, 6, 12, 3, 0.0F);
        this.rusalka.addChild(this.body);

        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(-4.9917F, -23.0F, 1.7357F);
        this.rightArm.setTextureOffset(0, 31)
                .addBox(-1.5F, -1.0F, -1.5F, 3, 12, 3, 0.0F);
        this.rightArm.rotateAngleX = -0.3054F;
        this.rightArm.rotateAngleY = 0.4363F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.rusalka.addChild(this.rightArm);

        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(4.0083F, -23.0F, 1.7357F);
        this.leftArm.setTextureOffset(12, 31)
                .addBox(-1.5F, -1.0F, -1.5F, 3, 12, 3, 0.0F);
        this.leftArm.rotateAngleX = -0.8727F;
        this.leftArm.rotateAngleY = -0.4363F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rusalka.addChild(this.leftArm);

        this.rightLeg = new AdvancedModelBox(this, "rightLeg");
        this.rightLeg.setPos(-1.9917F, -11.0F, 1.7357F);
        this.rightLeg.setTextureOffset(18, 16)
                .addBox(-1.0083F, -1.0F, -1.7357F, 3, 12, 3, 0.0F);
        this.rusalka.addChild(this.rightLeg);

        this.leftLeg = new AdvancedModelBox(this, "leftLeg");
        this.leftLeg.setPos(1.0083F, -11.0F, 1.7357F);
        this.leftLeg.setTextureOffset(30, 16)
                .addBox(-1.0083F, -1.0F, -1.7357F, 3, 12, 3, 0.0F);

        this.rusalka.addChild(this.leftLeg);

        this.animator = ModelAnimator.create();

        this.updateDefaultPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rusalka.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(this.rusalka, this.head, this.body, this.rightArm, this.rightLeg, this.leftArm, this.leftLeg);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.rusalka);
    }

    @Override
    public void setupAnim(RusalkaEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        animate(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        faceTarget(pNetHeadYaw, pHeadPitch, 1.0F, this.head);

        float idleSpeed = 0.09F;
        float idleAmount = 0.05F;

        this.rightArm.rotateAngleX += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;
        this.leftArm.rotateAngleX  += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;

        this.rightArm.rotateAngleZ += Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;
        this.leftArm.rotateAngleZ  -= Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;
    }

    public void animate(RusalkaEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(pEntity);

        if (this.animator.setAnimation(RusalkaEntity.MELEE_ATTACK_ANIMATION)) {
            this.animator.startKeyframe(10);
            this.rotate(this.animator, this.leftArm, -77.5247185802F, -1.3429457962F, 2.1088648868F);
            this.animator.endKeyframe();
            this.animator.startKeyframe(5);
            this.rotate(this.animator, this.leftArm, 17.0167555934F, -1.4754803082F, 94.8174647537F);
            this.animator.endKeyframe();
            this.animator.resetKeyframe(25);
        }

        if (this.animator.setAnimation(RusalkaEntity.WATER_ATTACK_ANIMATION)) {
            this.animator.startKeyframe(30);
            this.rotate(this.animator, this.rightArm, -119.4861479388F, -24.8983739694F, 2.3272993503F);
            this.animator.endKeyframe();
            this.animator.resetKeyframe(50);
        }
    }
}
