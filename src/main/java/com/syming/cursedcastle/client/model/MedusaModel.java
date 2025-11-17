package com.syming.cursedcastle.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.boss.MedusaEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;

import static com.github.alexthe666.citadel.animation.IAnimatedEntity.NO_ANIMATION;

public class MedusaModel extends AdvancedEntityModel<MedusaEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "medusa"), "main");
    private final AdvancedModelBox medusa;
    private final AdvancedModelBox head;
//    private final AdvancedModelBox hairRoot;
//    private final AdvancedModelBox hairTip;
//    private final AdvancedModelBox hairRoot2;
//    private final AdvancedModelBox hairTip2;
//    private final AdvancedModelBox hairRoot3;
//    private final AdvancedModelBox hairTip3;
//    private final AdvancedModelBox hairRoot4;
//    private final AdvancedModelBox hairTip4;
//    private final AdvancedModelBox hairRoot5;
//    private final AdvancedModelBox hairTip5;
//    private final AdvancedModelBox hairRoot6;
//    private final AdvancedModelBox hairTip6;
//    private final AdvancedModelBox hairRoot7;
//    private final AdvancedModelBox hairTip7;
//    private final AdvancedModelBox hairRoot8;
//    private final AdvancedModelBox hairTip8;
//    private final AdvancedModelBox hairRoot9;
//    private final AdvancedModelBox hairTip9;
//    private final AdvancedModelBox hairRoot10;
//    private final AdvancedModelBox hairTip10;
//    private final AdvancedModelBox hairRoot11;
//    private final AdvancedModelBox hairTip11;
//    private final AdvancedModelBox hairRoot12;
//    private final AdvancedModelBox hairTip12;
//    private final AdvancedModelBox hairRoot13;
//    private final AdvancedModelBox hairTip13;
//    private final AdvancedModelBox hairRoot14;
//    private final AdvancedModelBox hairTip14;
//    private final AdvancedModelBox hairRoot15;
//    private final AdvancedModelBox hairTip15;
//    private final AdvancedModelBox hairRoot16;
//    private final AdvancedModelBox hairTip16;
//    private final AdvancedModelBox hairRoot17;
//    private final AdvancedModelBox hairTip17;
//    private final AdvancedModelBox hairRoot18;
//    private final AdvancedModelBox hairTip18;
//    private final AdvancedModelBox hairRoot19;
//    private final AdvancedModelBox hairTip19;
//    private final AdvancedModelBox hairRoot20;
//    private final AdvancedModelBox hairTip20;
    private final AdvancedModelBox body;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox tailBody;
    private final AdvancedModelBox tailMiddle;
    private final AdvancedModelBox tailTip;

    // Hair roots and tips (20 pairs)
    private final AdvancedModelBox[] hairRoots = new AdvancedModelBox[20];
    private final AdvancedModelBox[] hairTips = new AdvancedModelBox[20];

    public ModelAnimator animator;

    public MedusaModel() {
//        this.medusa = root.getChild("medusa");
//        this.head = this.medusa.getChild("head");
//        this.hairRoot = this.head.getChild("hairRoot");
//        this.hairTip = this.hairRoot.getChild("hairTip");
//        this.hairRoot2 = this.head.getChild("hairRoot2");
//        this.hairTip2 = this.hairRoot2.getChild("hairTip2");
//        this.hairRoot3 = this.head.getChild("hairRoot3");
//        this.hairTip3 = this.hairRoot3.getChild("hairTip3");
//        this.hairRoot4 = this.head.getChild("hairRoot4");
//        this.hairTip4 = this.hairRoot4.getChild("hairTip4");
//        this.hairRoot5 = this.head.getChild("hairRoot5");
//        this.hairTip5 = this.hairRoot5.getChild("hairTip5");
//        this.hairRoot6 = this.head.getChild("hairRoot6");
//        this.hairTip6 = this.hairRoot6.getChild("hairTip6");
//        this.hairRoot7 = this.head.getChild("hairRoot7");
//        this.hairTip7 = this.hairRoot7.getChild("hairTip7");
//        this.hairRoot8 = this.head.getChild("hairRoot8");
//        this.hairTip8 = this.hairRoot8.getChild("hairTip8");
//        this.hairRoot9 = this.head.getChild("hairRoot9");
//        this.hairTip9 = this.hairRoot9.getChild("hairTip9");
//        this.hairRoot10 = this.head.getChild("hairRoot10");
//        this.hairTip10 = this.hairRoot10.getChild("hairTip10");
//        this.hairRoot11 = this.head.getChild("hairRoot11");
//        this.hairTip11 = this.hairRoot11.getChild("hairTip11");
//        this.hairRoot12 = this.head.getChild("hairRoot12");
//        this.hairTip12 = this.hairRoot12.getChild("hairTip12");
//        this.hairRoot13 = this.head.getChild("hairRoot13");
//        this.hairTip13 = this.hairRoot13.getChild("hairTip13");
//        this.hairRoot14 = this.head.getChild("hairRoot14");
//        this.hairTip14 = this.hairRoot14.getChild("hairTip14");
//        this.hairRoot15 = this.head.getChild("hairRoot15");
//        this.hairTip15 = this.hairRoot15.getChild("hairTip15");
//        this.hairRoot16 = this.head.getChild("hairRoot16");
//        this.hairTip16 = this.hairRoot16.getChild("hairTip16");
//        this.hairRoot17 = this.head.getChild("hairRoot17");
//        this.hairTip17 = this.hairRoot17.getChild("hairTip17");
//        this.hairRoot18 = this.head.getChild("hairRoot18");
//        this.hairTip18 = this.hairRoot18.getChild("hairTip18");
//        this.hairRoot19 = this.head.getChild("hairRoot19");
//        this.hairTip19 = this.hairRoot19.getChild("hairTip19");
//        this.hairRoot20 = this.head.getChild("hairRoot20");
//        this.hairTip20 = this.hairRoot20.getChild("hairTip20");
//        this.body = this.medusa.getChild("body");
//        this.rightArm = this.medusa.getChild("rightArm");
//        this.leftArm = this.medusa.getChild("leftArm");
//        this.tailBody = this.medusa.getChild("tailBody");
//        this.tailMiddle = this.tailBody.getChild("tailMiddle");
//        this.tailTip = this.tailMiddle.getChild("tailTip");

        this.texWidth = 128;
        this.texHeight = 128;

        // Root
        this.medusa = new AdvancedModelBox(this, "medusa");
        this.medusa.setPos(0.0F, 24.0F, 0.0F);

        // Head
        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(0.0F, -20.0F, -2.0F);
        this.head.setTextureOffset(48, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
        this.medusa.addChild(this.head);

        // Generate 20 hair roots and tips
        makeHairSnake(this.head, 0, 0.0F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F);
        makeHairSnake(this.head, 1, -1.5F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F);
        makeHairSnake(this.head, 2, -3.0F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F);
        makeHairSnake(this.head, 3, -4.0F, -8.0F, -3.0F, 0.0F, 1.5708F, 0.0F);
        makeHairSnake(this.head, 4, -4.0F, -8.0F, -1.5F, 0.0F, 1.5708F, 0.0F);
        makeHairSnake(this.head, 5, -4.0F, -8.0F, 0.0F, 0.0F, 1.5708F, 0.0F);
        makeHairSnake(this.head, 6, -4.0F, -8.0F, 1.5F, 0.0F, 1.5708F, 0.0F);
        makeHairSnake(this.head, 7, -4.0F, -8.0F, 3.0F, 0.0F, 1.5708F, 0.0F);
        makeHairSnake(this.head, 8, -3.0F, -8.0F, 4.0F, 0.0F, 3.1416F, 0.0F);
        makeHairSnake(this.head, 9, -1.5F, -8.0F, 4.0F, 0.0F, 3.1416F, 0.0F);
        makeHairSnake(this.head, 10, 0.0F, -8.0F, 4.0F, 0.0F, 3.1416F, 0.0F);
        makeHairSnake(this.head, 11, 1.5F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F);
        makeHairSnake(this.head, 12, 3.0F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F);
        makeHairSnake(this.head, 13, 4.0F, -8.0F, -3.0F, 0.0F, -1.5708F, 0.0F);
        makeHairSnake(this.head, 14, 4.0F, -8.0F, -1.5F, 0.0F, -1.5708F, 0.0F);
        makeHairSnake(this.head, 15, 4.0F, -8.0F, 0.0F, 0.0F, -1.5708F, 0.0F);
        makeHairSnake(this.head, 16, 4.0F, -8.0F, 1.5F, 0.0F, -1.5708F, 0.0F);
        makeHairSnake(this.head, 17, 4.0F, -8.0F, 3.0F, 0.0F, -1.5708F, 0.0F);
        makeHairSnake(this.head, 18, 3.0F, -8.0F, 4.0F, 0.0F, -3.1416F, 0.0F);
        makeHairSnake(this.head, 19, 1.5F, -8.0F, 4.0F, 0.0F, -3.1416F, 0.0F);

        // Body
        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(0.0F, -12.0F, -2.0F);
        this.body.setTextureOffset(48, 16)
                .addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 4.0F);
        this.medusa.addChild(this.body);

        // Right Arm
        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(-5.5F, -18.0F, -2.0F);
        this.rightArm.setTextureOffset(48, 36)
                .addBox(-1.5F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F);
        this.rightArm.rotateAngleX = -0.2182F;
        this.medusa.addChild(this.rightArm);

        // Left Arm
        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(5.5F, -18.0F, -2.0F);
        this.leftArm.setTextureOffset(48, 36)
                .addBox(-1.5F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F);
        this.leftArm.rotateAngleX = -0.2182F;
        this.medusa.addChild(this.leftArm);

        // Tail Body
        this.tailBody = new AdvancedModelBox(this, "tailBody");
        this.tailBody.setPos(0.0F, -2.0F, -2.0F);
        this.tailBody.setTextureOffset(0, 0)
                .addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 16.0F);
        this.medusa.addChild(this.tailBody);

        // Tail Middle
        this.tailMiddle = new AdvancedModelBox(this, "tailMiddle");
        this.tailMiddle.setPos(0.0F, 0.5F, 15.5F);
        this.tailMiddle.setTextureOffset(0, 20)
                .addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 16.0F, -0.5F);
        this.tailBody.addChild(this.tailMiddle);

        // Tail Tip
        this.tailTip = new AdvancedModelBox(this, "tailTip");
        this.tailTip.setPos(0.0F, 0.5F, 14.5F);
        this.tailTip.setTextureOffset(0, 40)
                .addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 16.0F, -1.0F);
        this.tailMiddle.addChild(this.tailTip);

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.medusa.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void setupAnim(MedusaEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        animate(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.medusa);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(this.medusa, this.head, this.body, this.rightArm, this.leftArm, this.tailBody, this.tailMiddle, this.tailTip);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition medusa = partdefinition.addOrReplaceChild("medusa", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = medusa.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -2.0F));

        PartDefinition hairRoot = head.addOrReplaceChild("hairRoot", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -4.0F));

        PartDefinition hairTip = hairRoot.addOrReplaceChild("hairTip", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot2 = head.addOrReplaceChild("hairRoot2", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -8.0F, -4.0F));

        PartDefinition hairTip2 = hairRoot2.addOrReplaceChild("hairTip2", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot3 = head.addOrReplaceChild("hairRoot3", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -8.0F, -4.0F));

        PartDefinition hairTip3 = hairRoot3.addOrReplaceChild("hairTip3", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot4 = head.addOrReplaceChild("hairRoot4", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, -3.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition hairTip4 = hairRoot4.addOrReplaceChild("hairTip4", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot5 = head.addOrReplaceChild("hairRoot5", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, -1.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition hairTip5 = hairRoot5.addOrReplaceChild("hairTip5", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot6 = head.addOrReplaceChild("hairRoot6", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition hairTip6 = hairRoot6.addOrReplaceChild("hairTip6", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot7 = head.addOrReplaceChild("hairRoot7", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 1.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition hairTip7 = hairRoot7.addOrReplaceChild("hairTip7", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot8 = head.addOrReplaceChild("hairRoot8", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition hairTip8 = hairRoot8.addOrReplaceChild("hairTip8", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot9 = head.addOrReplaceChild("hairRoot9", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -8.0F, 4.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition hairTip9 = hairRoot9.addOrReplaceChild("hairTip9", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot10 = head.addOrReplaceChild("hairRoot10", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -8.0F, 4.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition hairTip10 = hairRoot10.addOrReplaceChild("hairTip10", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot11 = head.addOrReplaceChild("hairRoot11", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 4.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition hairTip11 = hairRoot11.addOrReplaceChild("hairTip11", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot12 = head.addOrReplaceChild("hairRoot12", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -8.0F, -4.0F));

        PartDefinition hairTip12 = hairRoot12.addOrReplaceChild("hairTip12", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot13 = head.addOrReplaceChild("hairRoot13", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -8.0F, -4.0F));

        PartDefinition hairTip13 = hairRoot13.addOrReplaceChild("hairTip13", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot14 = head.addOrReplaceChild("hairRoot14", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -8.0F, -3.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition hairTip14 = hairRoot14.addOrReplaceChild("hairTip14", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot15 = head.addOrReplaceChild("hairRoot15", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -8.0F, -1.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition hairTip15 = hairRoot15.addOrReplaceChild("hairTip15", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot16 = head.addOrReplaceChild("hairRoot16", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -8.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition hairTip16 = hairRoot16.addOrReplaceChild("hairTip16", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot17 = head.addOrReplaceChild("hairRoot17", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -8.0F, 1.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition hairTip17 = hairRoot17.addOrReplaceChild("hairTip17", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot18 = head.addOrReplaceChild("hairRoot18", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -8.0F, 3.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition hairTip18 = hairRoot18.addOrReplaceChild("hairTip18", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot19 = head.addOrReplaceChild("hairRoot19", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -8.0F, 4.0F, 0.0F, -3.1416F, 0.0F));

        PartDefinition hairTip19 = hairRoot19.addOrReplaceChild("hairTip19", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition hairRoot20 = head.addOrReplaceChild("hairRoot20", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -8.0F, 4.0F, 0.0F, -3.1416F, 0.0F));

        PartDefinition hairTip20 = hairRoot20.addOrReplaceChild("hairTip20", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

        PartDefinition body = medusa.addOrReplaceChild("body", CubeListBuilder.create().texOffs(48, 16).addBox(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -2.0F));

        PartDefinition rightArm = medusa.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(48, 36).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -18.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition leftArm = medusa.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(48, 36).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -18.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition tailBody = medusa.addOrReplaceChild("tailBody", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

        PartDefinition tailMiddle = tailBody.addOrReplaceChild("tailMiddle", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 16.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.5F, 15.5F));

        PartDefinition tailTip = tailMiddle.addOrReplaceChild("tailTip", CubeListBuilder.create().texOffs(0, 40).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 16.0F, new CubeDeformation(-1.0F)), PartPose.offset(0.0F, 0.5F, 14.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    private void makeHairSnake(AdvancedModelBox head, int index, float x, float y, float z, float rotX, float rotY, float rotZ) {
        AdvancedModelBox root = new AdvancedModelBox(this, "hairRoot" + (index + 1));
        root.setPos(x, y, z);
        root.setTextureOffset(0, 60)
                .addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F);
        root.rotateAngleX = rotX;
        root.rotateAngleY = rotY;
        root.rotateAngleZ = rotZ;
        head.addChild(root);

        AdvancedModelBox tip = new AdvancedModelBox(this, "hairTip" + (index + 1));
        tip.setPos(0.0F, -2.0F, 0.0F);
        tip.setTextureOffset(2, 60)
                .addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F);
        tip.rotateAngleX = 1.3526F;
        root.addChild(tip);

        this.hairRoots[index] = root;
        this.hairTips[index] = tip;
    }

    public void animate(MedusaEntity medusa, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(medusa);

        this.head.rotateAngleY = pNetHeadYaw * ((float) Math.PI / 180.0F);
        this.head.rotateAngleX = pHeadPitch * ((float) Math.PI / 180.0F);

        swing(this.tailBody, 0.0625F, -2.5F, false, 0.0F, 0.0F, pLimbSwing, pLimbSwingAmount);
        swing(this.tailMiddle, 0.0625F, -1.25F, false, 0.0F, 0.0F, pLimbSwing, pLimbSwingAmount);
        swing(this.tailTip, 0.0625F, -0.0625F, false, 0.0F, 0.0F, pLimbSwing, pLimbSwingAmount);

        for (int i = 0; i < 20; i++) {
            float frequency = 0.1F + (float) Math.random() * 0.00005F;
            float amplitude = 0.2F + (float) Math.random() * 0.00001F;
            float oscillation = (float) Math.sin(pAgeInTicks * frequency + i) * amplitude;

            AdvancedModelBox hairRoot = this.hairRoots[i];
            if (hairRoot != null) {
                if (i <= 3) {
                    hairRoot.rotateAngleX = oscillation;
                }
                if (i >= 4 && i <= 8) {
                    hairRoot.rotateAngleZ = oscillation;
                }
                if (i >= 9 && i <= 13) {
                    hairRoot.rotateAngleX = oscillation;
                }
                if (i >= 14 && i <= 18) {
                    hairRoot.rotateAngleZ = oscillation;
                }
                if (i == 19) {
                    hairRoot.rotateAngleX = oscillation;
                }
            }
        }

        for (int i = 0; i < 20; i++) {
            float frequency = 0.1F + (float) Math.random() * 0.00005F;
            float amplitude = 0.2F + (float) Math.random() * 0.00001F;
            float oscillation = (float) Math.sin(pAgeInTicks * frequency + i) * amplitude;

            AdvancedModelBox hairTip = this.hairTips[i];
            if (hairTip != null) {
                if (i <= 3) {
                    hairTip.rotateAngleX = oscillation;
                }
                if (i >= 4 && i <= 8) {
                    hairTip.rotateAngleZ = oscillation;
                }
                if (i >= 9 && i <= 13) {
                    hairTip.rotateAngleX = oscillation;
                }
                if (i >= 14 && i <= 18) {
                    hairTip.rotateAngleZ = oscillation;
                }
                if (i == 19) {
                    hairTip.rotateAngleX = oscillation;
                }
            }
        }

    }
}
