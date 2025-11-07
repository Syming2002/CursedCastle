package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.*;

public class DraculaHeadModel extends SkullModel {
    public DraculaHeadModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createDraculaHeadLayer() {
        MeshDefinition meshDefinition = createHeadModel();
        return LayerDefinition.create(meshDefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }


}
