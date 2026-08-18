package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.monster.MedusaSnakeEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MedusaSnakeModel extends HierarchicalModel<MedusaSnakeEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation MEDUSA_SNAKE_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "medusa_snake"), "main");
    private final ModelPart medusaSnake;
    private final ModelPart head;
    private final ModelPart tongue;
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart body3;

    public MedusaSnakeModel(ModelPart root) {
        this.medusaSnake = root.getChild("medusaSnake");
        this.head = this.medusaSnake.getChild("head");
        this.tongue = this.head.getChild("tongue");
        this.body = this.medusaSnake.getChild("body");
        this.body2 = this.body.getChild("body2");
        this.body3 = this.body2.getChild("body3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition medusaSnake = partdefinition.addOrReplaceChild("medusaSnake", CubeListBuilder.create(), PartPose.offset(-0.225F, 24.0F, 1.75F));

        PartDefinition head = medusaSnake.addOrReplaceChild("head", CubeListBuilder.create().texOffs(10, 0).addBox(-1.0875F, -0.6F, -1.95F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.3125F, -0.4F, -5.05F));

        PartDefinition tongue = head.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(10, 3).addBox(-0.5F, 0.0F, -1.1F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0875F, 0.1F, -1.95F));

        PartDefinition body = medusaSnake.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.225F, -0.5F, -5.0F));

        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 5).addBox(-0.5F, -0.5F, -0.2F, 1.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 0.075F, 3.9F));

        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -0.5F, -0.9F, 1.0F, 1.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 0.025F, 3.6F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public ModelPart root() {
        return this.medusaSnake;
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.medusaSnake.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void setupAnim(MedusaSnakeEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        float swing = Mth.sin(pLimbSwing * 0.0625F) * pLimbSwingAmount;

        this.body.yRot += swing * -2.5F;
        this.body2.yRot += swing * -1.25F;
        this.body3.yRot += swing * -0.0625F;
    }
}
