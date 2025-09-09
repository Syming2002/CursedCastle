package com.syming.cursedcastle.entity.client.vampirebat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.VampireBatEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class VampireBatModel extends HierarchicalModel<VampireBatEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation VAMPIRE_BAT_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "vampire_bat"), "main");
    private final ModelPart vampireBat;
    private final ModelPart head;
    private final ModelPart rightEar;
    private final ModelPart leftEar;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public VampireBatModel(ModelPart root) {
        this.vampireBat = root.getChild("vampireBat");
        this.head = this.vampireBat.getChild("head");
        this.rightEar = this.head.getChild("rightEar");
        this.leftEar = this.head.getChild("leftEar");
        this.body = this.vampireBat.getChild("body");
        this.rightWing = this.vampireBat.getChild("rightWing");
        this.leftWing = this.vampireBat.getChild("leftWing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition vampireBat = partdefinition.addOrReplaceChild("vampireBat", CubeListBuilder.create(), PartPose.offset(0.0F, 32.0F, 0.0F));

        PartDefinition head = vampireBat.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(64, 4).addBox(-1.0F, -1.0F, 0.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.3F, -1.0F, -1.0F));

        PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(64, 5).addBox(0.0F, -1.0F, 0.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.3F, -1.0F, -1.0F));

        PartDefinition body = vampireBat.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 64).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.5F));

        PartDefinition rightWing = vampireBat.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 0).addBox(-32.0F, -29.0F, 0.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -12.0F, 0.0F));

        PartDefinition leftWing = vampireBat.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -29.0F, 0.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(VampireBatEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.body.xRot = ((float)Math.PI / 4F) + Mth.cos(pAgeInTicks * 0.1F) * 0.15F;
        this.body.yRot = 0.0F;
        this.rightWing.yRot = Mth.cos(pAgeInTicks * 74.48451F * ((float)Math.PI / 180F)) * (float)Math.PI * 0.25F;
        this.leftWing.yRot = -this.rightWing.yRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.vampireBat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.vampireBat;
    }
}
