package com.syming.castlevaniacraft.client.models.entity;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHarpyFeather extends ModelBase {
	private final ModelRenderer model_core;
	private final ModelRenderer feather;

	public ModelHarpyFeather() {
		textureWidth = 64;
		textureHeight = 64;

		model_core = new ModelRenderer(this);
		model_core.setRotationPoint(0.0F, 0.0F, 0.0F);


		feather = new ModelRenderer(this);
		feather.setRotationPoint(0.0F, 0.0F, 0.125F);
		model_core.addChild(feather);
		feather.cubeList.add(new ModelBox(feather, 32, 17, -0.5F, 1.0F, -6.625F, 1, 1, 12, 0.0F, false));
		feather.cubeList.add(new ModelBox(feather, 0, 17, -0.5F, 0.0F, -7.625F, 1, 1, 15, 0.0F, false));
		feather.cubeList.add(new ModelBox(feather, 0, 0, -0.5F, -1.0F, -7.625F, 1, 1, 16, 0.0F, false));
		feather.cubeList.add(new ModelBox(feather, 32, 30, -0.5F, -2.0F, -5.625F, 1, 1, 12, 0.0F, false));
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