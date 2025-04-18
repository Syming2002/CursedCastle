package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.EntityMedusaSnake;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelMedusaSnake extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer tongue;
	private final AdvancedModelRenderer body;
	private final AdvancedModelRenderer body2;
	private final AdvancedModelRenderer body3;
	private final AdvancedModelRenderer body4;
	private final AdvancedModelRenderer body5;
	private final AdvancedModelRenderer tail;
	private final AdvancedModelRenderer tail2;

	private ModelAnimator animator;

	public ModelMedusaSnake() {
		textureWidth = 16;
		textureHeight = 16;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -0.5F, 1.5F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, false));

		tongue = new AdvancedModelRenderer(this);
		tongue.setRotationPoint(0.0F, 0.2F, -1.5F);
		head.addChild(tongue);
		tongue.cubeList.add(new ModelBox(tongue, 2, 4, -0.5F, 0.0F, -2.0F, 1, 0, 2, 0.0F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -0.5F, 3.9F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 4, 5, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.125F, false));

		body2 = new AdvancedModelRenderer(this);
		body2.setRotationPoint(0.0F, 0.0F, 1.75F);
		body.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 8, 6, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.125F, false));

		body3 = new AdvancedModelRenderer(this);
		body3.setRotationPoint(0.0F, 0.0F, 1.75F);
		body2.addChild(body3);
		body3.cubeList.add(new ModelBox(body3, 4, 8, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.125F, false));

		body4 = new AdvancedModelRenderer(this);
		body4.setRotationPoint(0.0F, 0.0F, 1.75F);
		body3.addChild(body4);
		body4.cubeList.add(new ModelBox(body4, 8, 3, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.125F, false));

		body5 = new AdvancedModelRenderer(this);
		body5.setRotationPoint(0.0F, 0.0F, 1.75F);
		body4.addChild(body5);
		body5.cubeList.add(new ModelBox(body5, 7, 0, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.125F, false));

		tail = new AdvancedModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.05F, 1.625F);
		body5.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 0, 7, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.25F, false));

		tail2 = new AdvancedModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, 1.5F);
		tail.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 0, 4, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.25F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityMedusaSnake medusaSnake = (EntityMedusaSnake) entity;
		animate(medusaSnake, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityMedusaSnake medusaSnake = (EntityMedusaSnake) entity;
		animator.update(medusaSnake);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, medusaSnake);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		swing(body, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
//		swing(body, 0.5F, 10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

		swing(body2, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
//		swing(body2, 0.5F, 10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

		swing(body3, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
//		swing(body3, 0.5F, 10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

		swing(body4, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
//		swing(body4, 0.5F, 10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

		swing(body5, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
//		swing(body5, 0.5F, 10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

		swing(tail, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
//		swing(tail, 0.5F, 10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

		swing(tail2, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
//		swing(tail2, 0.5F, 10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
	}
}