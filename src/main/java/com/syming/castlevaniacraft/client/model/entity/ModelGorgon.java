package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.EntityGorgon;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGorgon extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer body;
	private final AdvancedModelRenderer right_back_leg;
	private final AdvancedModelRenderer left_back_leg;
	private final AdvancedModelRenderer right_front_leg;
	private final AdvancedModelRenderer left_fornt_leg;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer cube_r1;

	private ModelAnimator animator;

	public ModelGorgon() {
		textureWidth = 128;
		textureHeight = 128;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -18.0F, -4.5F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -4.5F, -3.0F, -11.5F, 9, 9, 24, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 33, -5.5F, -3.5F, -6.0F, 11, 4, 11, 0.0F, false));

		right_back_leg = new AdvancedModelRenderer(this);
		right_back_leg.setRotationPoint(-3.0F, -12.0F, 4.0F);
		model_core.addChild(right_back_leg);
		right_back_leg.cubeList.add(new ModelBox(right_back_leg, 0, 48, -2.5F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		left_back_leg = new AdvancedModelRenderer(this);
		left_back_leg.setRotationPoint(4.0F, -12.0F, 4.0F);
		model_core.addChild(left_back_leg);
		left_back_leg.cubeList.add(new ModelBox(left_back_leg, 16, 48, -2.5F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		right_front_leg = new AdvancedModelRenderer(this);
		right_front_leg.setRotationPoint(-3.0F, -12.0F, -4.0F);
		model_core.addChild(right_front_leg);
		right_front_leg.cubeList.add(new ModelBox(right_front_leg, 32, 48, -2.5F, 0.0F, -6.0F, 4, 12, 4, 0.0F, false));

		left_fornt_leg = new AdvancedModelRenderer(this);
		left_fornt_leg.setRotationPoint(4.0F, -12.0F, -8.0F);
		model_core.addChild(left_fornt_leg);
		left_fornt_leg.cubeList.add(new ModelBox(left_fornt_leg, 48, 48, -2.5F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.5F, -16.5F, -13.5F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 44, 33, -4.0F, -3.5F, -10.5F, 7, 7, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 64, 1.5F, -5.0F, -7.0F, 1, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 12, 64, -3.5F, -5.0F, -7.0F, 1, 2, 1, 0.0F, false));

		cube_r1 = new AdvancedModelRenderer(this);
		cube_r1.setRotationPoint(-3.0F, -4.8F, -6.7F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, 1.0472F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 4, 64, -0.5F, -2.5F, -0.5F, 1, 3, 1, -0.025F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 64, 4.5F, -2.5F, -0.5F, 1, 3, 1, -0.025F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();

	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityGorgon gorgon = (EntityGorgon) entity;
		animate(gorgon, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityGorgon gorgon = (EntityGorgon) entity;
		animator.update(gorgon);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, gorgon);

		if (gorgon.getAnimation() == EntityGorgon.POISON_BREATH_ANIMATION && gorgon.isEntityAlive()) {
			animator.setAnimation(EntityGorgon.POISON_BREATH_ANIMATION);

			animator.startKeyframe(10);
			this.rotate(animator, head, -25, 0, 0);
			animator.endKeyframe();

			animator.startKeyframe(10);
			this.rotate(animator, head, 25, 0, 0);
			animator.endKeyframe();

			animator.startKeyframe(10);
			this.rotate(animator, head, -25, 0, 0);
			animator.endKeyframe();

			animator.startKeyframe(10);
			this.rotate(animator, head, 25, 0, 0);
			animator.endKeyframe();

			animator.startKeyframe(10);
			this.rotate(animator, head, 0, 0, 0);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
//		this.body.rotateAngleX = ((float)Math.PI / 2F);
		this.right_back_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_back_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.right_front_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.left_fornt_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}