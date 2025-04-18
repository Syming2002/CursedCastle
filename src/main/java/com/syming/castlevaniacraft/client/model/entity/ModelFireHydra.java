package com.syming.castlevaniacraft.client.model.entity;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFireHydra extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer body1;
	private final AdvancedModelRenderer cube_r1;
	private final AdvancedModelRenderer cube_r2;
	private final AdvancedModelRenderer cube_r3;
	private final AdvancedModelRenderer cube_r4;
	private final AdvancedModelRenderer cube_r5;
	private final AdvancedModelRenderer cube_r6;
	private final AdvancedModelRenderer cube_r7;
	private final AdvancedModelRenderer cube_r8;
	private final AdvancedModelRenderer body2;
	private final AdvancedModelRenderer body3;
	private final AdvancedModelRenderer body4;
	private final AdvancedModelRenderer body5;
	private final AdvancedModelRenderer body6;
	private final AdvancedModelRenderer body7;
	private final AdvancedModelRenderer body8;

	public ModelFireHydra() {
		textureWidth = 128;
		textureHeight = 128;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, -36.75F);
		

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -4.5F, 0.25F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 72, -3.0F, 0.5F, -12.75F, 6, 4, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 40, 72, -2.0F, -1.5F, -7.75F, 4, 2, 9, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 84, 16, -2.0F, -2.5F, -3.75F, 4, 1, 5, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 84, 22, -1.0F, -0.5F, -11.75F, 2, 1, 4, 0.0F, false));

		body1 = new AdvancedModelRenderer(this);
		body1.setRotationPoint(0.0F, -3.5F, 1.5F);
		model_core.addChild(body1);
		body1.cubeList.add(new ModelBox(body1, 0, 0, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.01F, false));

		cube_r1 = new AdvancedModelRenderer(this);
		cube_r1.setRotationPoint(3.6856F, -4.3F, 2.5321F);
		body1.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.6607F, -0.833F, -0.395F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 40, 83, -5.0F, -1.5F, -0.5F, 10, 3, 1, 0.001F, false));

		cube_r2 = new AdvancedModelRenderer(this);
		cube_r2.setRotationPoint(5.6856F, -2.0F, 2.5321F);
		body1.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.2992F, -0.833F, -0.395F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 84, 12, -5.0F, -1.5F, -0.5F, 10, 3, 1, 0.001F, false));

		cube_r3 = new AdvancedModelRenderer(this);
		cube_r3.setRotationPoint(3.0856F, 3.4F, 4.5321F);
		body1.addChild(cube_r3);
		setRotationAngle(cube_r3, -1.1036F, -0.993F, 0.1388F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 66, 72, -5.0F, -1.5F, -0.5F, 10, 3, 1, 0.001F, false));

		cube_r4 = new AdvancedModelRenderer(this);
		cube_r4.setRotationPoint(-4.4F, 2.5F, 1.0F);
		body1.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.8727F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 84, 4, -7.0F, -2.0F, -0.5F, 10, 3, 1, 0.0001F, false));

		cube_r5 = new AdvancedModelRenderer(this);
		cube_r5.setRotationPoint(-3.0856F, 3.4F, 4.5321F);
		body1.addChild(cube_r5);
		setRotationAngle(cube_r5, -1.1036F, 0.993F, -0.1388F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 66, 76, -5.0F, -1.5F, -0.5F, 10, 3, 1, 0.001F, false));

		cube_r6 = new AdvancedModelRenderer(this);
		cube_r6.setRotationPoint(-3.6856F, -4.3F, 2.5321F);
		body1.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.6607F, 0.833F, 0.395F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 66, 80, -5.0F, -1.5F, -0.5F, 10, 3, 1, 0.001F, false));

		cube_r7 = new AdvancedModelRenderer(this);
		cube_r7.setRotationPoint(-5.6856F, -2.0F, 2.5321F);
		body1.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.2992F, 0.833F, 0.395F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 84, 8, -5.0F, -1.5F, -0.5F, 10, 3, 1, 0.001F, false));

		cube_r8 = new AdvancedModelRenderer(this);
		cube_r8.setRotationPoint(4.4F, 2.5F, 1.0F);
		body1.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, -0.8727F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 84, 0, -3.0F, -2.0F, -0.5F, 10, 3, 1, 0.0001F, false));

		body2 = new AdvancedModelRenderer(this);
		body2.setRotationPoint(0.0F, 0.0F, 11.0F);
		body1.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 0, 18, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.001F, false));

		body3 = new AdvancedModelRenderer(this);
		body3.setRotationPoint(0.0F, 0.0F, 11.0F);
		body2.addChild(body3);
		body3.cubeList.add(new ModelBox(body3, 0, 36, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.01F, false));

		body4 = new AdvancedModelRenderer(this);
		body4.setRotationPoint(0.0F, 0.0F, 11.0F);
		body3.addChild(body4);
		body4.cubeList.add(new ModelBox(body4, 42, 0, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.001F, false));

		body5 = new AdvancedModelRenderer(this);
		body5.setRotationPoint(0.0F, 0.0F, 11.0F);
		body4.addChild(body5);
		body5.cubeList.add(new ModelBox(body5, 42, 18, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.01F, false));

		body6 = new AdvancedModelRenderer(this);
		body6.setRotationPoint(0.0F, 0.0F, 11.0F);
		body5.addChild(body6);
		body6.cubeList.add(new ModelBox(body6, 42, 36, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.001F, false));

		body7 = new AdvancedModelRenderer(this);
		body7.setRotationPoint(0.0F, 0.0F, 11.0F);
		body6.addChild(body7);
		body7.cubeList.add(new ModelBox(body7, 0, 54, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.01F, false));

		body8 = new AdvancedModelRenderer(this);
		body8.setRotationPoint(0.0F, 0.0F, 11.0F);
		body7.addChild(body8);
		body8.cubeList.add(new ModelBox(body8, 42, 54, -5.0F, -3.5F, -0.5F, 10, 7, 11, 0.001F, false));

		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		model_core.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;

		swing(body1, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(body2, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(body3, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(body4, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(body5, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(body6, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(body7, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(body8, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
	}

	public void setRotationAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
		AdvancedModelRenderer.rotateAngleX = x;
		AdvancedModelRenderer.rotateAngleY = y;
		AdvancedModelRenderer.rotateAngleZ = z;
	}
}