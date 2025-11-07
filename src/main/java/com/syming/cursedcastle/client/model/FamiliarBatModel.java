package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.bat.CCBatEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FamiliarBatModel extends CCBatModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation FAMILIAR_BAT_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "familiar_bat"), "main");

    public FamiliarBatModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return CCBatModel.createBodyLayer();
    }

    @Override
    public void setupAnim(CCBatEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return super.root();
    }
}
