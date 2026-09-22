package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.monster.boss.MedusaEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MedusaModel extends HierarchicalModel<MedusaEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation MEDUSA_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "medusa"), "main");
    private final ModelPart medusa;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart tailBody;
    private final ModelPart tailMiddle;
    private final ModelPart tailTip;

    // Hair roots and tips (20 pairs)
    private final ModelPart[] hairRoots = new ModelPart[20];
    private final ModelPart[] hairTips = new ModelPart[20];

    public MedusaModel(ModelPart pRoot) {

        this.medusa = pRoot.getChild("medusa");

        this.head = this.medusa.getChild("head");

        for (int i = 1; i < this.hairRoots.length; i++) {
            this.hairRoots[i] = this.head.getChild("hairRoot" + i);
        }

        for (int i = 1; i < this.hairTips.length; i++) {
            this.hairTips[i] = this.hairRoots[i].getChild("hairTip" + i);
        }

        this.body = this.medusa.getChild("body");

        this.rightArm = this.medusa.getChild("rightArm");
        this.leftArm = this.medusa.getChild("leftArm");

        this.tailBody = this.medusa.getChild("tailBody");
        this.tailMiddle = this.tailBody.getChild("tailMiddle");
        this.tailTip = this.tailMiddle.getChild("tailTip");

    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int color) {
        this.medusa.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.medusa;
    }

    @Override
    public void setupAnim(MedusaEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180.0F);
        this.head.xRot = pHeadPitch * ((float) Math.PI / 180.0F);

        float swing = Mth.sin(pLimbSwing * 0.0625F) * pLimbSwingAmount;

        this.tailBody.yRot += swing * -2.5F;
        this.tailMiddle.yRot += swing * -1.25F;
        this.tailTip.yRot += swing * -0.0625F;

        for (int i = 0; i < 20; i++) {
            float frequency = 0.1F + (float) Math.random() * 0.00005F;
            float amplitude = 0.2F + (float) Math.random() * 0.00001F;
            float oscillation = (float) Math.sin(pAgeInTicks * frequency + i) * amplitude;

            ModelPart hairRoot = this.hairRoots[i];
            if (hairRoot != null) {
                if (i <= 3) {
                    hairRoot.xRot = oscillation;
                }
                if (i >= 4 && i <= 8) {
                    hairRoot.zRot = oscillation;
                }
                if (i >= 9 && i <= 13) {
                    hairRoot.xRot = oscillation;
                }
                if (i >= 14 && i <= 18) {
                    hairRoot.zRot = oscillation;
                }
                if (i == 19) {
                    hairRoot.xRot = oscillation;
                }
            }
        }

        for (int i = 0; i < 20; i++) {
            float frequency = 0.1F + (float) Math.random() * 0.00005F;
            float amplitude = 0.2F + (float) Math.random() * 0.00001F;
            float oscillation = (float) Math.sin(pAgeInTicks * frequency + i) * amplitude;

            ModelPart hairTip = this.hairTips[i];
            if (hairTip != null) {
                if (i <= 3) {
                    hairTip.xRot = oscillation;
                }
                if (i >= 4 && i <= 8) {
                    hairTip.zRot = oscillation;
                }
                if (i >= 9 && i <= 13) {
                    hairTip.xRot = oscillation;
                }
                if (i >= 14 && i <= 18) {
                    hairTip.zRot = oscillation;
                }
                if (i == 19) {
                    hairTip.xRot = oscillation;
                }
            }
        }

        // Melee Attack Animation
        animate(pEntity.meleeAttackAnimationState, CCAnimation.MEDUSA_MELEE_ATTACK_ANIMATION, pAgeInTicks);

        // Stare Attack Animation
        animate(pEntity.stareAttackAnimationState, CCAnimation.MEDUSA_STARE_ATTACK_ANIMATION, pAgeInTicks);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition medusa = partdefinition.addOrReplaceChild("medusa", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = medusa.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -2.0F));

        PartDefinition hairRoot1 = head.addOrReplaceChild("hairRoot1", CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -4.0F));

        PartDefinition hairTip1 = hairRoot1.addOrReplaceChild("hairTip1", CubeListBuilder.create().texOffs(2, 60).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.3526F, 0.0F, 0.0F));

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

}
