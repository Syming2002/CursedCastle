package com.syming.minevania.client.models.entity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDagger extends ModelBase {
	private final ModelRenderer model_core;

	public ModelDagger() {
		textureWidth = 32;
		textureHeight = 32;

		model_core = new ModelRenderer(this);
		model_core.setRotationPoint(0.0F, 0.0F, 0.0F);
		model_core.cubeList.add(new ModelBox(model_core, 0, 11, -0.5F, 0.0F, -9.9167F, 1, 1, 9, 0.0F, false));
		model_core.cubeList.add(new ModelBox(model_core, 0, 0, -0.5F, -1.0F, -10.9167F, 1, 1, 10, 0.0F, false));
		model_core.cubeList.add(new ModelBox(model_core, 0, 7, -0.5F, -1.0F, 4.0833F, 1, 2, 1, 0.0F, false));
		model_core.cubeList.add(new ModelBox(model_core, 0, 11, -0.5F, -2.0F, 3.0833F, 1, 4, 1, 0.0F, false));
		model_core.cubeList.add(new ModelBox(model_core, 0, 0, -0.5F, -2.0F, -0.9167F, 1, 4, 3, 0.0F, false));
		model_core.cubeList.add(new ModelBox(model_core, 5, 0, -0.5F, -1.0F, 2.0833F, 1, 2, 1, 0.0F, false));
		setRotationAngle(model_core, 166.5F, -67.5F, 0.0F);
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