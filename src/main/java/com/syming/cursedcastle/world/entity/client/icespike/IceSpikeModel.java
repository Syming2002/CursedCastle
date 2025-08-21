package com.syming.cursedcastle.world.entity.client.icespike;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class IceSpikeModel extends SkullModelBase {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "ice_spike_layer"), "main");
    private final ModelPart iceSpike;
    private final ModelPart bottomIceSpike;
    private final ModelPart middleIceSpike;
    private final ModelPart topIceSpike;

    public IceSpikeModel(ModelPart root) {
        this.iceSpike = root.getChild("iceSpike");
        this.bottomIceSpike = this.iceSpike.getChild("bottomIceSpike");
        this.middleIceSpike = this.iceSpike.getChild("middleIceSpike");
        this.topIceSpike = this.iceSpike.getChild("topIceSpike");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition iceSpike = partdefinition.addOrReplaceChild("iceSpike", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 3.75F));

        PartDefinition bottomIceSpike = iceSpike.addOrReplaceChild("bottomIceSpike", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -3.5F, 4.0F, 4.0F, 7.0F, new CubeDeformation(-1.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition middleIceSpike = iceSpike.addOrReplaceChild("middleIceSpike", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -2.0F, -7.75F, 4.0F, 4.0F, 7.0F, new CubeDeformation(-1.5F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition topIceSpike = iceSpike.addOrReplaceChild("topIceSpike", CubeListBuilder.create().texOffs(0, 11).addBox(-2.0F, -2.0F, -11.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(-1.75F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(float pMouthAnimation, float pYRot, float pXRot) {
        this.iceSpike.yRot = pYRot * ((float) Math.PI / 180.0F);
        this.iceSpike.xRot = pXRot * ((float) Math.PI / 180.0F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.iceSpike.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
