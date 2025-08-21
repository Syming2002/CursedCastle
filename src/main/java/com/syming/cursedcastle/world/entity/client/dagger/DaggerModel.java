package com.syming.cursedcastle.world.entity.client.dagger;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.world.entity.projectile.subweapon.DaggerEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class DaggerModel<T extends DaggerEntity> extends EntityModel<T> {

	public static ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "dagger"), "main");
	private final ModelPart model_core;

	public DaggerModel(ModelPart root) {
		this.model_core = root.getChild("model_core");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition model_core = partdefinition.addOrReplaceChild("model_core", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.0F, -10.9167F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-0.5F, 0.0F, -9.9167F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(20, 11).addBox(-0.5F, -2.0F, -0.9167F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 18).addBox(-0.5F, -2.0F, 3.0833F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-0.5F, -1.0F, 2.0833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 21).addBox(-0.5F, -1.0F, 4.0833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 166.5F, -67.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(DaggerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		model_core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}