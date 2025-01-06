package com.syming.minevania.client.models.entity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIceBlade extends ModelBase {
	private final ModelRenderer bottom_ice_blade;
	private final ModelRenderer middle_ice_blade;
	private final ModelRenderer top_ice_blade;

	public ModelIceBlade() {
		textureWidth = 64;
		textureHeight = 64;

		bottom_ice_blade = new ModelRenderer(this);
		bottom_ice_blade.setRotationPoint(0.0F, 0.0F, 0.0F);
		bottom_ice_blade.cubeList.add(new ModelBox(bottom_ice_blade, 15, 4, -2.0F, -7.8F, -2.0F, 4, 4, 7, -1.25F, false));

		middle_ice_blade = new ModelRenderer(this);
		middle_ice_blade.setRotationPoint(0.0F, 0.0F, 0.0F);
		middle_ice_blade.cubeList.add(new ModelBox(middle_ice_blade, 0, 0, -2.0F, -7.8F, -6.2F, 4, 4, 7, -1.5F, false));

		top_ice_blade = new ModelRenderer(this);
		top_ice_blade.setRotationPoint(0.0F, 0.0F, 0.0F);
		top_ice_blade.cubeList.add(new ModelBox(top_ice_blade, 0, 11, -2.0F, -7.8F, -9.95F, 4, 4, 7, -1.75F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom_ice_blade.render(f5);
		middle_ice_blade.render(f5);
		top_ice_blade.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
	 * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
	 * "far" arms and legs can swing at most.
	 */
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		this.bottom_ice_blade.rotateAngleY = netHeadYaw * 0.017453292F;
		this.bottom_ice_blade.rotateAngleX = headPitch * 0.017453292F;
		this.middle_ice_blade.rotateAngleY = netHeadYaw * 0.017453292F;
		this.middle_ice_blade.rotateAngleX = headPitch * 0.017453292F;
		this.top_ice_blade.rotateAngleY = netHeadYaw * 0.017453292F;
		this.top_ice_blade.rotateAngleX = headPitch * 0.017453292F;
	}
}