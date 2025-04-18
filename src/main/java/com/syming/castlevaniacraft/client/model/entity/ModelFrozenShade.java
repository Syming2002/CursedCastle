package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

import com.syming.castlevaniacraft.entity.mobs.EntityFrozenShade;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelFrozenShade extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer body;
	private final AdvancedModelRenderer right_arm;
	private final AdvancedModelRenderer left_arm;
	private final AdvancedModelRenderer right_leg;
	private final AdvancedModelRenderer left_leg;

	private ModelAnimator animator;

	public ModelFrozenShade() {
		textureWidth = 64;
		textureHeight = 64;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);


		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -28.0F, 0.0F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -18.0F, 0.0F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, -6.0F, -2.0F, 8, 12, 4, 0.0F, false));

		right_arm = new AdvancedModelRenderer(this);
		right_arm.setRotationPoint(-5.5F, -22.5F, 0.0F);
		model_core.addChild(right_arm);
		setRotationAngle(right_arm, 0.0F, 0.0F, 1.5708F);
		right_arm.cubeList.add(new ModelBox(right_arm, 32, 0, -1.5F, -1.5F, -2.0F, 3, 12, 4, 0.0F, false));

		left_arm = new AdvancedModelRenderer(this);
		left_arm.setRotationPoint(5.5F, -22.5F, 0.0F);
		model_core.addChild(left_arm);
		setRotationAngle(left_arm, 0.0F, 0.0F, -1.5708F);
		left_arm.cubeList.add(new ModelBox(left_arm, 16, 32, -1.5F, -1.5F, -2.0F, 3, 12, 4, 0.0F, false));

		right_leg = new AdvancedModelRenderer(this);
		right_leg.setRotationPoint(-2.0F, -10.0F, 0.0F);
		model_core.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 32, -2.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));

		left_leg = new AdvancedModelRenderer(this);
		left_leg.setRotationPoint(2.0F, -10.0F, 0.0F);
		model_core.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 24, 16, -2.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityFrozenShade frozenShade = (EntityFrozenShade) entity;
		animate(frozenShade, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityFrozenShade frozenShade = (EntityFrozenShade) entity;
		animator.update(frozenShade);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, frozenShade);

		// Idle Animation
		if (frozenShade.getAnimation() == EntityFrozenShade.IDLE_ANIMATION && frozenShade.isEntityAlive()) {
			animator.setAnimation(EntityFrozenShade.IDLE_ANIMATION);
			animator.startKeyframe(20);
			this.rotate(animator, left_arm, 0, 0, 5);
			this.rotate(animator, right_arm, 0, 0, -5);
			animator.endKeyframe();
			animator.startKeyframe(20);
			this.rotate(animator, left_arm, 0, 0, 0);
			this.rotate(animator, right_arm, 0, 0, 0);
			animator.endKeyframe();
		}

		// Repulse Attack Animation
		if (frozenShade.getAnimation() == EntityFrozenShade.REPULSE_ATTACK_ANIMATION && frozenShade.isEntityAlive()) {
			animator.setAnimation(EntityFrozenShade.REPULSE_ATTACK_ANIMATION);

			animator.startKeyframe(30);
			animator.move(head, 0, 1, -1.5F);
			this.rotate(animator, body, 27.5F, 0, 0);
			animator.move(body, 0, 0.5F,0);
			animator.move(right_leg, 0, 0, 2.5F);
			animator.move(left_leg, 0, 0, 2.5F);
			animator.move(right_arm, 0, 0, -1.5F);
			animator.move(left_arm, 0, 0, -1.5F);
			animator.setStaticKeyframe(65);

			this.rotate(animator, right_arm, 0, 0, 50);
			this.rotate(animator, left_arm, 0, 0, -50);
			animator.endKeyframe();

			animator.move(head, 0, 1, -1.5F);
			this.rotate(animator, body, 27.5F, 0, 0);
			animator.move(body, 0, 0.5F,0);
			animator.move(right_leg, 0, 0, 2.5F);
			animator.move(left_leg, 0, 0, 2.5F);
			animator.move(right_arm, 0, 0, -1.5F);
			animator.move(left_arm, 0, 0, -1.5F);

			animator.startKeyframe(5);
			this.rotate(animator, right_arm, -90, 0, 0);
			this.rotate(animator, left_arm, -90, 0, 0);
			animator.endKeyframe();
			animator.setStaticKeyframe(60);

			animator.startKeyframe(20);
			animator.move(head, 0, 0, 0);
			this.rotate(animator, body, 0, 0, 0);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, left_arm, 0, 0, 0);
			animator.move(right_leg, 0, 0, 0);
			animator.move(left_leg, 0, 0, 0);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;
	}
}