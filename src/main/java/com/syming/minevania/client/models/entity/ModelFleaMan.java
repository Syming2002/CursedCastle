package com.syming.minevania.client.models.entity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.minevania.entity.mobs.EntityFleaMan;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelFleaMan extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer body;
	private final AdvancedModelRenderer right_foot;
	private final AdvancedModelRenderer left_foot;
	private final AdvancedModelRenderer right_hand;
	private final AdvancedModelRenderer left_hand;

	private ModelAnimator animator;

	public ModelFleaMan() {
		textureWidth = 16;
		textureHeight = 16;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -4.0F, -2.4F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 8, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -3.4F, -0.3F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -2.0F, -2.0F, -2.0F, 4, 4, 4, -0.25F, false));

		right_foot = new AdvancedModelRenderer(this);
		right_foot.setRotationPoint(-1.0F, -1.5F, 0.5F);
		model_core.addChild(right_foot);
		right_foot.cubeList.add(new ModelBox(right_foot, 8, 8, -0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F, false));

		left_foot = new AdvancedModelRenderer(this);
		left_foot.setRotationPoint(1.0F, -1.5F, 0.5F);
		model_core.addChild(left_foot);
		left_foot.cubeList.add(new ModelBox(left_foot, 0, 0, -0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F, false));

		right_hand = new AdvancedModelRenderer(this);
		right_hand.setRotationPoint(-2.25F, -4.0F, -0.5F);
		model_core.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 11, 10, -0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F, false));

		left_hand = new AdvancedModelRenderer(this);
		left_hand.setRotationPoint(2.25F, -4.0F, -0.5F);
		model_core.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 7, 11, -0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityFleaMan fleaMan = (EntityFleaMan) entity;
		animate(fleaMan, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityFleaMan fleaMan = (EntityFleaMan) entity;
		animator.update(fleaMan);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, fleaMan);

		// Jump Animation
		if (fleaMan.getAnimation() == EntityFleaMan.JUMP_ANIMATION && fleaMan.isEntityAlive()) {
			animator.setAnimation(EntityFleaMan.JUMP_ANIMATION);
			animator.startKeyframe(5);
			this.rotate(animator, model_core, -5, 0, 0);
			this.rotate(animator, right_foot, 17.5F, 0, 0);
			this.rotate(animator, left_foot, 17.5F, 0, 0);
			this.rotate(animator, right_hand, -75, 40, 0);
			this.rotate(animator, left_hand, -75, -40, 0);
			animator.endKeyframe();
			animator.startKeyframe(10);
			this.rotate(animator, model_core, 5, 0, 0);
			this.rotate(animator, right_foot, -17.5F, 0, 0);
			this.rotate(animator, left_foot, -17.5F, 0, 0);
			this.rotate(animator, right_hand, -75, 40, 0);
			this.rotate(animator, left_hand, -75, -40, 0);
			animator.endKeyframe();
			animator.startKeyframe(5);
			this.rotate(animator, model_core, 0, 0, 0);
			this.rotate(animator, right_foot, 0, 0, 0);
			this.rotate(animator, left_foot, 0, 0, 0);
			this.rotate(animator, right_hand, 0, 0, 0);
			this.rotate(animator, left_hand, 0, 0, 0);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		float yawConversionFactor = 0.017453292F / 3.0F;

		this.head.rotateAngleY = netHeadYaw * yawConversionFactor;
		this.head.rotateAngleX = headPitch * 0.017453292F;
	}
}