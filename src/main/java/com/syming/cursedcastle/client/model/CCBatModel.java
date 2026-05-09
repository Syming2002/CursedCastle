package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.entity.mob.bat.CCBatEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public abstract class CCBatModel extends HierarchicalModel<CCBatEntity> {

    protected final ModelPart bat;
    protected final ModelPart head;
    protected final ModelPart rightEar;
    protected final ModelPart leftEar;
    protected final ModelPart body;
    protected final ModelPart rightWing;
    protected final ModelPart leftWing;

    public CCBatModel(ModelPart root) {
        this.bat = root.getChild("bat");
        this.head = this.bat.getChild("head");
        this.rightEar = this.head.getChild("rightEar");
        this.leftEar = this.head.getChild("leftEar");
        this.body = this.bat.getChild("body");
        this.rightWing = this.bat.getChild("rightWing");
        this.leftWing = this.bat.getChild("leftWing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bat = partdefinition.addOrReplaceChild("bat", CubeListBuilder.create(), PartPose.offset(0.0F, 32.0F, 0.0F));

        PartDefinition head = bat.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(64, 4).addBox(-1.0F, -1.0F, 0.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.3F, -1.0F, -1.0F));

        PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(64, 5).addBox(0.0F, -1.0F, 0.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.3F, -1.0F, -1.0F));

        PartDefinition body = bat.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 64).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.5F));

        PartDefinition rightWing = bat.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 0).addBox(-32.0F, -29.0F, 0.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -12.0F, 0.0F, 0.1644F, 0.648F, -0.162F));

        PartDefinition leftWing = bat.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -29.0F, 0.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -12.0F, 0.0F, 0.1644F, -0.648F, 0.162F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(CCBatEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.body.xRot = ((float)Math.PI / 4F) + Mth.cos(pAgeInTicks * 0.1F) * 0.15F;
        this.body.yRot = 0.0F;
        this.rightWing.yRot = Mth.cos(pAgeInTicks * 74.48451F * ((float)Math.PI / 180F)) * (float)Math.PI * 0.25F;
        this.leftWing.yRot = -this.rightWing.yRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.bat;
    }

}
