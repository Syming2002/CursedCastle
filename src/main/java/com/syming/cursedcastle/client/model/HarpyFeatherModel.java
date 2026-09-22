package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.magic.HarpyFeatherEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class HarpyFeatherModel extends EntityModel<HarpyFeatherEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation HARPY_FEATHER_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "harpy_feather"), "main");
    private final ModelPart harpyFeather;
    private final ModelPart topHarpyFeather;
    private final ModelPart topMiddleHarpyFeather;
    private final ModelPart bottomMiddleHarpyFeather;
    private final ModelPart bottomHarpyFeather;

	public HarpyFeatherModel(ModelPart root) {
        this.harpyFeather = root.getChild("harpyFeather");
        this.topHarpyFeather = this.harpyFeather.getChild("topHarpyFeather");
        this.topMiddleHarpyFeather = this.harpyFeather.getChild("topMiddleHarpyFeather");
        this.bottomMiddleHarpyFeather = this.harpyFeather.getChild("bottomMiddleHarpyFeather");
        this.bottomHarpyFeather = this.harpyFeather.getChild("bottomHarpyFeather");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition harpyFeather = partdefinition.addOrReplaceChild("harpyFeather", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 0.5F));

        PartDefinition topHarpyFeather = harpyFeather.addOrReplaceChild("topHarpyFeather", CubeListBuilder.create().texOffs(32, 30).addBox(-1.0F, -4.0F, -6.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition topMiddleHarpyFeather = harpyFeather.addOrReplaceChild("topMiddleHarpyFeather", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -8.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bottomMiddleHarpyFeather = harpyFeather.addOrReplaceChild("bottomMiddleHarpyFeather", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0F, -2.0F, -8.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bottomHarpyFeather = harpyFeather.addOrReplaceChild("bottomHarpyFeather", CubeListBuilder.create().texOffs(32, 17).addBox(-1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(HarpyFeatherEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.harpyFeather.yRot = (pNetHeadYaw + 180.0F) * ((float)Math.PI / 180F);
        this.harpyFeather.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int color) {
        this.harpyFeather.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
    }
}
