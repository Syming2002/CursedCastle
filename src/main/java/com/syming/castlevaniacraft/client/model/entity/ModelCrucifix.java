package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrucifix extends ModelBase {
	private final ModelRenderer model_core;
	private final ModelRenderer base;
	private final ModelRenderer beam;
	private final ModelRenderer right_beam;
	private final ModelRenderer left_beam;

	public ModelCrucifix() {
		textureWidth = 64;
		textureHeight = 64;

		model_core = new ModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);


		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, -1.5F, 0.0F);
		model_core.addChild(base);
		base.cubeList.add(new ModelBox(base, 0, 0, -6.0F, 0.5F, -6.0F, 12, 1, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 6, 13, -4.0F, -0.5F, -4.0F, 8, 1, 8, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 6, 25, -2.0F, -1.5F, -2.0F, 4, 1, 4, 0.0F, false));

		beam = new ModelRenderer(this);
		beam.setRotationPoint(0.0F, -23.0F, 0.0F);
		model_core.addChild(beam);
		beam.cubeList.add(new ModelBox(beam, 0, 13, -1.0F, -20.0F, -0.5F, 2, 40, 1, 0.0F, false));

		left_beam = new ModelRenderer(this);
		left_beam.setRotationPoint(5.5F, -34.0F, 0.0F);
		model_core.addChild(left_beam);
		left_beam.cubeList.add(new ModelBox(left_beam, 24, 22, -4.5F, -1.0F, -0.5F, 8, 2, 1, 0.0F, false));

		right_beam = new ModelRenderer(this);
		right_beam.setRotationPoint(-5.5F, -34.0F, 0.0F);
		model_core.addChild(right_beam);
		right_beam.cubeList.add(new ModelBox(right_beam, 6, 22, -3.5F, -1.0F, -0.5F, 8, 2, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		model_core.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}