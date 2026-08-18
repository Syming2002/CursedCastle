package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.monster.boss.GrimReaperEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GrimReaperModel<T extends GrimReaperEntity> extends HierarchicalModel<T> implements ArmedModel {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation GRIM_REAPER_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "grim_reaper"), "main");
    protected final ModelPart grimReaper;
    public final ModelPart head;
    public final ModelPart headWear;
    public final ModelPart body;
    public final ModelPart rightArm;
    public final ModelPart rightArmWear;
    public final ModelPart leftArm;
    public final ModelPart leftArmWear;

    public GrimReaperArmPose rightArmPose;

//    public ModelAnimator animator;

    public GrimReaperModel(ModelPart pRoot) {
        this.rightArmPose = GrimReaperArmPose.DEATH_SCYTHE;

        this.grimReaper = pRoot.getChild("grimReaper");

        this.head = this.grimReaper.getChild("head");
        this.headWear = this.grimReaper.getChild("headWear");
        this.body = this.grimReaper.getChild("body");
        this.rightArm = this.grimReaper.getChild("rightArm");
        this.leftArm = this.grimReaper.getChild("leftArm");
        this.rightArmWear = this.grimReaper.getChild("rightArmWear");
        this.leftArmWear = this.grimReaper.getChild("leftArmWear");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition grimReaper = partdefinition.addOrReplaceChild("grimReaper", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = grimReaper.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));

        PartDefinition headWear = grimReaper.addOrReplaceChild("headWear", CubeListBuilder.create().texOffs(24, 16).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -28.0F, 0.0F));

        PartDefinition body = grimReaper.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition rightArm = grimReaper.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.5F, 0.0F, -0.4659F, -0.1198F, -0.2333F));

        PartDefinition rightArmWear = grimReaper.addOrReplaceChild("rightArmWear", CubeListBuilder.create().texOffs(8, 28).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, -22.5F, 0.0F, -0.4659F, -0.1198F, -0.2333F));

        PartDefinition leftArm = grimReaper.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -22.5F, 0.0F, -1.0321F, 0.2261F, 0.1332F));

        PartDefinition leftArmWear = grimReaper.addOrReplaceChild("leftArmWear", CubeListBuilder.create().texOffs(8, 28).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(5.0F, -22.5F, 0.0F, -1.0321F, 0.2261F, 0.1332F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

        this.grimReaper.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.grimReaper;
    }

    @Override
    public void setupAnim(GrimReaperEntity grimReaper,float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.headWear.yRot = pNetHeadYaw * ((float)Math.PI / 180F);

        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        this.headWear.xRot = pHeadPitch * ((float)Math.PI / 180F);

        switch (this.rightArmPose) {
            case EMPTY:

                this.rightArm.yRot = 0.0F;
                this.rightArmWear.yRot = 0.0F;
                break;

            case DEATH_SCYTHE:

                this.rightArm.xRot = -0.4659F;
                this.rightArmWear.xRot = -0.4659F;
                this.rightArm.yRot = -0.1198F;
                this.rightArmWear.yRot = -0.1198F;
                this.rightArm.zRot = -0.2333F;
                this.rightArmWear.zRot = -0.2333F;

                this.leftArm.xRot = -1.0321F;
                this.leftArmWear.xRot = -1.0321F;
                this.leftArm.yRot = 0.2261F;
                this.leftArmWear.yRot = 0.2261F;
                this.leftArm.zRot = 0.1332F;
                this.leftArmWear.zRot = 0.1332F;
        }

        animate(grimReaper.idleAnimationState, CCAnimation.GRIM_REAPER_IDLE_ANIMATION, pAgeInTicks);
        animate(grimReaper.fastMeleeAttackAnimationState, CCAnimation.GRIM_REAPER_FAST_MELEE_ATTACK_ANIMATION, pAgeInTicks);
        animate(grimReaper.scytheProjectileAttackAnimationState, CCAnimation.GRIM_REAPER_SCYTHE_PROJECTILE_ATTACK_ANIMATION, pAgeInTicks);

    }

    @Override
    public void translateToHand(HumanoidArm pHumanoidArm, PoseStack pPoseStack) {
        this.grimReaper.translateAndRotate(pPoseStack);
        getArm(pHumanoidArm).translateAndRotate(pPoseStack);
    }

    private ModelPart getArm(HumanoidArm pHumanoidArm) {
        return pHumanoidArm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public GrimReaperArmPose getArmPose() {
        return GrimReaperArmPose.DEATH_SCYTHE;
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
