package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTeleportationLaserBeam extends ModelBase {
	private final ModelRenderer beam;

	public ModelTeleportationLaserBeam() {
		textureWidth = 32;
		textureHeight = 416;

		beam = new ModelRenderer(this);
		beam.setRotationPoint(0.0F, 24.0F, 0.0F);
		beam.cubeList.add(new ModelBox(beam, 0, 0, -8.0F, -8.0F, 0.0F, 16, 16, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		beam.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}