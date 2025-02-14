package com.syming.castlevaniacraft.client.models.entity;

// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.boss.EntityZephyr;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelZephyr extends CCModel {
	private AdvancedModelRenderer model_core;
	private AdvancedModelRenderer head;
	private AdvancedModelRenderer front_hair;
	private AdvancedModelRenderer right_hair;
	private AdvancedModelRenderer left_hair;
	private AdvancedModelRenderer back_hair;
	private AdvancedModelRenderer body;
	private AdvancedModelRenderer cube_r1;
	private AdvancedModelRenderer cube_r2;
	private AdvancedModelRenderer right_arm;
	private AdvancedModelRenderer cube_r3;
	private AdvancedModelRenderer cube_r4;
	private AdvancedModelRenderer left_arm;
	private AdvancedModelRenderer cube_r5;
	private AdvancedModelRenderer cube_r6;
	private AdvancedModelRenderer right_leg;
	private AdvancedModelRenderer left_leg;

	private ModelAnimator animator;

	public ModelZephyr() {
		textureWidth = 64;
		textureHeight = 64;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -34.0F, -1.0F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

		front_hair = new AdvancedModelRenderer(this);
		front_hair.setRotationPoint(0.0F, -7.1F, -2.4F);
		head.addChild(front_hair);
		setRotationAngle(front_hair, -0.48F, 0.0F, 0.0F);
		front_hair.cubeList.add(new ModelBox(front_hair, 32, 0, -1.0F, -5.5F, 0.0F, 2, 9, 0, 0.0F, false));
		front_hair.cubeList.add(new ModelBox(front_hair, 0, 0, -4.0F, -3.5F, 0.0F, 2, 7, 0, 0.0F, false));
		front_hair.cubeList.add(new ModelBox(front_hair, 34, 9, -2.0F, -2.5F, 0.0F, 1, 6, 0, 0.0F, false));
		front_hair.cubeList.add(new ModelBox(front_hair, 32, 9, 1.0F, -2.5F, 0.0F, 1, 6, 0, 0.0F, false));
		front_hair.cubeList.add(new ModelBox(front_hair, 24, 0, 2.0F, -3.5F, 0.0F, 2, 7, 0, 0.0F, false));

		right_hair = new AdvancedModelRenderer(this);
		right_hair.setRotationPoint(-4.0F, -6.25F, -3.5F);
		head.addChild(right_hair);
		setRotationAngle(right_hair, 0.0F, 0.1309F, 0.0F);
		right_hair.cubeList.add(new ModelBox(right_hair, 2, 15, 0.0F, 0.25F, -0.5F, 0, 2, 1, 0.0F, false));
		right_hair.cubeList.add(new ModelBox(right_hair, 6, 3, 0.0F, -1.75F, 0.5F, 0, 4, 1, 0.0F, false));
		right_hair.cubeList.add(new ModelBox(right_hair, 30, 0, 0.0F, -3.75F, 1.5F, 0, 6, 1, 0.0F, false));
		right_hair.cubeList.add(new ModelBox(right_hair, 40, 13, 0.0F, -3.75F, 2.5F, 0, 6, 5, 0.0F, false));

		left_hair = new AdvancedModelRenderer(this);
		left_hair.setRotationPoint(4.0F, -6.25F, -3.5F);
		head.addChild(left_hair);
		setRotationAngle(left_hair, 0.0F, -0.1309F, 0.0F);
		left_hair.cubeList.add(new ModelBox(left_hair, 0, 15, 0.0F, 0.25F, -0.5F, 0, 2, 1, 0.0F, false));
		left_hair.cubeList.add(new ModelBox(left_hair, 4, 3, 0.0F, -1.75F, 0.5F, 0, 4, 1, 0.0F, false));
		left_hair.cubeList.add(new ModelBox(left_hair, 28, 0, 0.0F, -3.75F, 1.5F, 0, 6, 1, 0.0F, false));
		left_hair.cubeList.add(new ModelBox(left_hair, 32, 37, 0.0F, -3.75F, 2.5F, 0, 6, 5, 0.0F, false));

		back_hair = new AdvancedModelRenderer(this);
		back_hair.setRotationPoint(0.0F, 34.0F, 1.0F);
		head.addChild(back_hair);
		back_hair.cubeList.add(new ModelBox(back_hair, 35, 31, -3.0F, -44.0F, -2.0F, 6, 6, 5, 0.0F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -18.5887F, -1.0F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, -11.4113F, -2.0F, 8, 14, 4, 0.0F, false));

		cube_r1 = new AdvancedModelRenderer(this);
		cube_r1.setRotationPoint(4.0F, 2.5887F, 0.0F);
		body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.8727F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, 0.0F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		cube_r2 = new AdvancedModelRenderer(this);
		cube_r2.setRotationPoint(-4.0F, 2.5887F, 0.0F);
		body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.8727F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 2, 0, -1.0F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		right_arm = new AdvancedModelRenderer(this);
		right_arm.setRotationPoint(-6.0F, -28.0F, -1.0F);
		model_core.addChild(right_arm);
		setRotationAngle(right_arm, -2.2689F, 0.6981F, 0.0F);
		

		cube_r3 = new AdvancedModelRenderer(this);
		cube_r3.setRotationPoint(4.0F, 12.523F, 4.6235F);
		right_arm.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.2618F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 46, 42, -4.0F, -1.0F, 1.0F, 1, 9, 0, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 32, 48, -6.0F, -1.0F, 1.0F, 1, 9, 0, 0.0F, false));

		cube_r4 = new AdvancedModelRenderer(this);
		cube_r4.setRotationPoint(2.0F, -1.0F, 1.0F);
		right_arm.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.2618F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 16, 36, -4.0F, -1.0F, -3.0F, 4, 14, 4, 0.0F, false));

		left_arm = new AdvancedModelRenderer(this);
		left_arm.setRotationPoint(6.0F, -28.0F, -1.0F);
		model_core.addChild(left_arm);
		setRotationAngle(left_arm, -2.2689F, -0.6981F, 0.0F);
		

		cube_r5 = new AdvancedModelRenderer(this);
		cube_r5.setRotationPoint(-4.0F, 12.523F, 4.6235F);
		left_arm.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.2618F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 42, 42, 3.0F, -1.0F, 1.0F, 1, 9, 0, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 44, 42, 5.0F, -1.0F, 1.0F, 1, 9, 0, 0.0F, false));

		cube_r6 = new AdvancedModelRenderer(this);
		cube_r6.setRotationPoint(-2.0F, -1.0F, 1.0F);
		left_arm.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.2618F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 36, 0, 0.0F, -1.0F, -3.0F, 4, 14, 4, 0.0F, false));

		right_leg = new AdvancedModelRenderer(this);
		right_leg.setRotationPoint(-2.0F, -14.0F, -1.0F);
		model_core.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 34, -2.0F, -2.0F, -2.0F, 4, 16, 4, 0.0F, false));

		left_leg = new AdvancedModelRenderer(this);
		left_leg.setRotationPoint(2.0F, -14.0F, -1.0F);
		model_core.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 24, 16, -2.0F, -2.0F, -2.0F, 4, 16, 4, 0.0F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityZephyr zephyr = (EntityZephyr) entity;
		animate(zephyr, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityZephyr zephyr = (EntityZephyr) entity;
		animator.update(zephyr);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, zephyr);

		// Idle Animation
		if (zephyr.getAnimation() == EntityZephyr.IDLE_ANIMATION && zephyr.isEntityAlive()) {
			animator.setAnimation(EntityZephyr.IDLE_ANIMATION);
			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 10, 0, 0);
			this.rotate(animator, left_arm, 10, 0, 0);
			animator.endKeyframe();

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, -10, 0, 0);
			this.rotate(animator, left_arm, -10, 0, 0);
			animator.endKeyframe();

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, left_arm, 0, 0, 0);
			animator.endKeyframe();
		}

		if (zephyr.getAnimation() == EntityZephyr.MELEE_ATTACK_ANIMATION && zephyr.isEntityAlive()) {
			animator.setAnimation(EntityZephyr.MELEE_ATTACK_ANIMATION);
			animator.startKeyframe(25);
			this.rotate(animator, right_arm, 136.9586F, 10.7901F, 56.3998F);
			animator.endKeyframe();

			animator.startKeyframe(5);
			this.rotate(animator, right_arm, 15.2706F, -46.3683F, 35.25F);
			animator.endKeyframe();

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 0);
			animator.endKeyframe();
		}

		// Dagger Launch Animation
		if (zephyr.getAnimation() == EntityZephyr.DAGGER_ATTACK_ANIMATION && zephyr.isEntityAlive()) {
			animator.setAnimation(EntityZephyr.DAGGER_ATTACK_ANIMATION);
			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 104.3513F, -21.9697F, 13.8157F);
			animator.endKeyframe();

			animator.startKeyframe(4);
			this.rotate(animator, right_arm, -57.0336F, -30.1299F, -2.1507F);
			animator.endKeyframe();

			animator.startKeyframe(4);
			this.rotate(animator, right_arm, 0, 0, 0);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;

		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}