package com.syming.castlevaniacraft.client.models.entity;

// Made with Blockbench 4.10.0
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDracula extends CCModel {
	public AdvancedModelRenderer model_core;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer headwear;
	public AdvancedModelRenderer body;
	public AdvancedModelRenderer jacket;
	public AdvancedModelRenderer right_arm;
	public AdvancedModelRenderer right_arm_sleve;
	public AdvancedModelRenderer left_arm;
	public AdvancedModelRenderer left_arm_sleve;
	public AdvancedModelRenderer right_leg;
	public AdvancedModelRenderer right_pants;
	public AdvancedModelRenderer left_leg;
	public AdvancedModelRenderer left_pants;

	private ModelAnimator animator;

	public ModelDracula() {
		textureWidth = 128;
		textureHeight = 128;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -36.0F, 1.0F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 16, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

		headwear = new AdvancedModelRenderer(this);
		headwear.setRotationPoint(0.0F, -36.0F, 1.0F);
		model_core.addChild(headwear);
		headwear.cubeList.add(new ModelBox(headwear, 0, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.5F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -24.0F, 1.0F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 32, -4.0F, -8.0F, -2.0F, 8, 16, 4, 0.0F, false));

		jacket = new AdvancedModelRenderer(this);
		jacket.setRotationPoint(0.0F, -24.0F, 1.0F);
		model_core.addChild(jacket);
		jacket.cubeList.add(new ModelBox(jacket, 28, 28, -4.0F, -8.0F, -2.0F, 8, 16, 4, 0.25F, false));

		right_arm = new AdvancedModelRenderer(this);
		right_arm.setRotationPoint(-6.0F, -30.0F, 1.0F);
		model_core.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 52, 56, -2.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F, false));

		right_arm_sleve = new AdvancedModelRenderer(this);
		right_arm_sleve.setRotationPoint(-6.0F, -30.0F, 1.0F);
		model_core.addChild(right_arm_sleve);
		right_arm_sleve.cubeList.add(new ModelBox(right_arm_sleve, 52, 20, -2.0F, -2.0F, -2.0F, 4, 14, 4, 0.25F, false));

		left_arm = new AdvancedModelRenderer(this);
		left_arm.setRotationPoint(6.0F, -30.0F, 1.0F);
		model_core.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 52, 38, -2.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F, false));

		left_arm_sleve = new AdvancedModelRenderer(this);
		left_arm_sleve.setRotationPoint(6.0F, -30.0F, 1.0F);
		model_core.addChild(left_arm_sleve);
		setRotationAngle(left_arm_sleve, 0.0F, 0.0F, 0.0F);
		left_arm_sleve.cubeList.add(new ModelBox(left_arm_sleve, 0, 52, -2.0F, -2.0F, -2.0F, 4, 14, 4, 0.25F, false));

		right_leg = new AdvancedModelRenderer(this);
		right_leg.setRotationPoint(-2.0F, -15.0F, 1.0F);
		model_core.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 36, 48, -2.0F, -1.0F, -2.0F, 4, 16, 4, 0.0F, false));

		right_pants = new AdvancedModelRenderer(this);
		right_pants.setRotationPoint(-2.0F, -15.0F, 1.0F);
		model_core.addChild(right_pants);
		right_pants.cubeList.add(new ModelBox(right_pants, 48, 0, -2.0F, -1.0F, -2.0F, 4, 16, 4, 0.25F, false));

		left_leg = new AdvancedModelRenderer(this);
		left_leg.setRotationPoint(2.0F, -15.0F, 1.0F);
		model_core.addChild(left_leg);
		setRotationAngle(left_leg, 0.0F, 0.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 20, 48, -2.0F, -1.0F, -2.0F, 4, 16, 4, 0.0F, false));

		left_pants = new AdvancedModelRenderer(this);
		left_pants.setRotationPoint(2.0F, -15.0F, 1.0F);
		model_core.addChild(left_pants);
		left_pants.cubeList.add(new ModelBox(left_pants, 32, 0, -2.0F, -1.0F, -2.0F, 4, 16, 4, 0.25F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}



	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityDracula dracula = (EntityDracula) entity;
		animate(dracula, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityDracula dracula = (EntityDracula) entity;
		animator.update(dracula);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, dracula);



		// Idle Animation
		if (dracula.getAnimation() == EntityDracula.IDLE_ANIMATION && dracula.isEntityAlive()) {
			animator.setAnimation(EntityDracula.IDLE_ANIMATION);
			animator.startKeyframe(20);
			animator.move(left_arm, 0, 0.375F, 0);
			animator.move(left_arm_sleve, 0, 0.375F, 0);
			animator.move(right_arm, 0, 0.375F, 0);
			animator.move(right_arm_sleve, 0, 0.375F, 0);
			animator.endKeyframe();
			animator.startKeyframe(20);
			animator.move(left_arm, 0, 0, 0);
			animator.move(left_arm_sleve, 0, 0, 0);
			animator.move(right_arm, 0, 0, 0);
			animator.move(right_arm_sleve, 0, 0, 0);
			animator.endKeyframe();
		}

		// Fireballs Attack Animation
		if (dracula.getAnimation() == EntityDracula.FIREBALLS_ANIMATION && dracula.isEntityAlive()) {
			animator.setAnimation(EntityDracula.FIREBALLS_ANIMATION);

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 87.5F);
			this.rotate(animator, right_arm_sleve, 0, 0, 87.5F);
			animator.setStaticKeyframe(40);
			animator.endKeyframe();
			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, right_arm_sleve, 0, 0, 0);
			animator.endKeyframe();
		}

		// Dark Inferno Attack Animation
		if (dracula.getAnimation() == EntityDracula.DARK_INFERNO_ANIMATION && dracula.isEntityAlive()) {
			animator.setAnimation(EntityDracula.DARK_INFERNO_ANIMATION);

			animator.startKeyframe(20);
			this.rotate(animator, left_arm, 0, 0, -87.5F);
			this.rotate(animator, left_arm_sleve, 0, 0, -87.5F);
			animator.setStaticKeyframe(40);
			animator.endKeyframe();
			animator.startKeyframe(20);
			this.rotate(animator, left_arm, 0, 0, 0);
			this.rotate(animator, left_arm_sleve, 0, 0, 0);
			animator.endKeyframe();
		}

		// Fire Pillar Attack Animation
		if (dracula.getAnimation() == EntityDracula.FIRE_PILLAR_ANIMATION && dracula.isEntityAlive()) {
			animator.setAnimation(EntityDracula.FIRE_PILLAR_ANIMATION);

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 87.5F);
			this.rotate(animator, right_arm_sleve, 0, 0, 87.5F);
			this.rotate(animator, left_arm, 0, 0, -87.5F);
			this.rotate(animator, left_arm_sleve, 0, 0, -87.5F);
			animator.setStaticKeyframe(20);
			animator.endKeyframe();
			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, right_arm_sleve, 0, 0, 0);
			this.rotate(animator, left_arm, 0, 0, 0);
			this.rotate(animator, left_arm_sleve, 0, 0, 0);
			animator.endKeyframe();
		}

		// Bats Attack Animation
		if (dracula.getAnimation() == EntityDracula.BATS_ATTACK_ANIMATION) {
			animator.setAnimation(EntityDracula.BATS_ATTACK_ANIMATION);

			animator.startKeyframe(35);
			this.rotate(animator, right_arm, 0, 0, 135.0F);
			this.rotate(animator, right_arm_sleve, 0, 0, 135.0F);
			this.rotate(animator, left_arm, 0, 0, -135.0F);
			this.rotate(animator, left_arm_sleve, 0, 0, -135.0F);
			animator.endKeyframe();
		}

		// Death Animation
		if (dracula.getAnimation() == EntityDracula.DEATH_ANIMATION) {
			animator.setAnimation(EntityDracula.DEATH_ANIMATION);

			animator.startKeyframe(200);
			this.rotate(animator, right_arm, 0, 0, 87.5F);
			this.rotate(animator, right_arm_sleve, 0, 0, 87.5F);
			this.rotate(animator, left_arm, 0, 0, -87.5F);
			this.rotate(animator, left_arm_sleve, 0, 0, -87.5F);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;

		this.headwear.rotateAngleY = netHeadYaw * 0.017453292F;
		this.headwear.rotateAngleX = headPitch * 0.017453292F;

		this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.left_arm_sleve.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_arm_sleve.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.left_pants.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_pants.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}