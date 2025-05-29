package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;

//public class ModelGrimReaper extends CCModelHumanoidGrimReaper {
//	private final AdvancedModelRenderer modelCore;
//	private final AdvancedModelRenderer head;
//	private final AdvancedModelRenderer headwear;
//	private final AdvancedModelRenderer body;
//	private final AdvancedModelRenderer jacket;
//	private final AdvancedModelRenderer humanoidRightArm;
//	private final AdvancedModelRenderer humanoidLeftArm;
//	private final AdvancedModelRenderer humanoidRightArmSleeve;
//	private final AdvancedModelRenderer humanoidLeftArmSleeve;
////	private final AdvancedModelRenderer scythe;
////	private final AdvancedModelRenderer cube_r1;
////	private final AdvancedModelRenderer cube_r2;
////	private final AdvancedModelRenderer cube_r3;
////	private final AdvancedModelRenderer cube_r4;
////	private final AdvancedModelRenderer cube_r5;
//
//	private ModelAnimator animator;
//
//	public ModelGrimReaper() {
//		textureWidth = 128;
//		textureHeight = 128;
//
//		modelCore = new AdvancedModelRenderer(this);
//		modelCore.setRotationPoint(0.0F, 24.0F, 0.0F);
//
//
//		head = new AdvancedModelRenderer(this);
//		head.setRotationPoint(0.0F, -28.0F, 0.0F);
//		modelCore.addChild(head);
//		head.cubeList.add(new ModelBox(head, 21, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));
//
//		headwear = new AdvancedModelRenderer(this);
//		headwear.setRotationPoint(0.0F, -28.0F, 0.0F);
//		modelCore.addChild(headwear);
//		headwear.cubeList.add(new ModelBox(headwear, 45, 8, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.5F, false));
//
//		body = new AdvancedModelRenderer(this);
//		body.setRotationPoint(0.0F, -12.0F, 0.0F);
//		modelCore.addChild(body);
//		body.cubeList.add(new ModelBox(body, 24, 20, -4.0F, -12.0F, -2.0F, 8, 24, 4, 0.0F, false));
//
//		jacket = new AdvancedModelRenderer(this);
//		jacket.setRotationPoint(0.0F, -12.0F, 0.0F);
//		modelCore.addChild(jacket);
//		jacket.cubeList.add(new ModelBox(jacket, 0, 20, -4.0F, -12.0F, -2.0F, 8, 24, 4, 0.25F, false));
//
//		humanoidRightArm = new AdvancedModelRenderer(this);
//		humanoidRightArm.setRotationPoint(-5.0F, -22.5F, 0.0F);
//		modelCore.addChild(humanoidRightArm);
//		setRotationAngle(humanoidRightArm, -0.4659F, -0.1198F, -0.2333F);
//		humanoidRightArm.cubeList.add(new ModelBox(humanoidRightArm, 48, 24, -3.0F, -1.5F, -1.0F, 4, 12, 4, 0.0F, false));
//
//		humanoidLeftArm = new AdvancedModelRenderer(this);
//		humanoidLeftArm.setRotationPoint(5.0F, -22.5F, 0.0F);
//		modelCore.addChild(humanoidLeftArm);
//		setRotationAngle(humanoidLeftArm, -1.0321F, 0.2261F, 0.1332F);
//		humanoidLeftArm.cubeList.add(new ModelBox(humanoidLeftArm, 16, 48, -1.0F, -1.5F, -1.0F, 4, 12, 4, 0.0F, false));
//
//		humanoidRightArmSleeve = new AdvancedModelRenderer(this);
//		humanoidRightArmSleeve.setRotationPoint(-5.0F, -22.5F, 0.0F);
//		modelCore.addChild(humanoidRightArmSleeve);
//		setRotationAngle(humanoidRightArmSleeve, -0.4659F, -0.1198F, -0.2333F);
//		humanoidRightArmSleeve.cubeList.add(new ModelBox(humanoidRightArmSleeve, 0, 0, -3.0F, -1.5F, -1.0F, 4, 12, 4, 0.0625F, false));
//
//		humanoidLeftArmSleeve = new AdvancedModelRenderer(this);
//		humanoidLeftArmSleeve.setRotationPoint(5.0F, -22.5F, 0.0F);
//		modelCore.addChild(humanoidLeftArmSleeve);
//		setRotationAngle(humanoidLeftArmSleeve, -1.0321F, 0.2261F, 0.1332F);
//		humanoidLeftArmSleeve.cubeList.add(new ModelBox(humanoidLeftArmSleeve, 0, 48, -1.0F, -1.5F, -1.0F, 4, 12, 4, 0.0625F, false));
//
//		scythe = new AdvancedModelRenderer(this);
//		scythe.setRotationPoint(7.4823F, -23.5942F, -25.4402F);
//		model_core.addChild(scythe);
//		setRotationAngle(scythe, 0.0375F, 0.3473F, 0.137F);
//
//
//		cube_r1 = new AdvancedModelRenderer(this);
//		cube_r1.setRotationPoint(0.6237F, -9.4827F, -8.2443F);
//		scythe.addChild(cube_r1);
//		setRotationAngle(cube_r1, 1.309F, 0.0F, 0.9599F);
//		cube_r1.cubeList.add(new ModelBox(cube_r1, 21, 4, 6.8724F, 0.8361F, -6.4863F, 1, 1, 3, -0.375F, false));
//
//		cube_r2 = new AdvancedModelRenderer(this);
//		cube_r2.setRotationPoint(2.2791F, -10.6723F, -7.1496F);
//		scythe.addChild(cube_r2);
//		setRotationAngle(cube_r2, 0.8727F, 0.0F, 0.9599F);
//		cube_r2.cubeList.add(new ModelBox(cube_r2, 21, 0, 6.8724F, 2.8182F, -5.4545F, 1, 1, 3, -0.25F, false));
//
//		cube_r3 = new AdvancedModelRenderer(this);
//		cube_r3.setRotationPoint(3.6869F, -11.5856F, -5.0803F);
//		scythe.addChild(cube_r3);
//		setRotationAngle(cube_r3, 0.4363F, 0.0F, 0.9599F);
//		cube_r3.cubeList.add(new ModelBox(cube_r3, 20, 20, 6.8724F, 4.1785F, -3.6816F, 1, 1, 3, -0.0625F, false));
//
//		cube_r4 = new AdvancedModelRenderer(this);
//		cube_r4.setRotationPoint(6.5883F, -13.8308F, 11.5707F);
//		scythe.addChild(cube_r4);
//		setRotationAngle(cube_r4, 0.1309F, 0.0F, 0.9599F);
//		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, 7.0474F, 5.618F, -16.1738F, 1, 1, 19, 0.125F, false));
//
//		cube_r5 = new AdvancedModelRenderer(this);
//		cube_r5.setRotationPoint(-27.4117F, 0.1692F, 17.5707F);
//		scythe.addChild(cube_r5);
//		setRotationAngle(cube_r5, 0.1309F, 0.0F, 0.9599F);
//		cube_r5.cubeList.add(new ModelBox(cube_r5, 47, 47, 15.0974F, -31.382F, -1.9738F, 1, 45, 1, 0.0F, false));
//
//		this.animator = ModelAnimator.create();
//		updateDefaultPose();
//	}
//
//	@Override
//	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
//		EntityGrimReaper grimReaper = (EntityGrimReaper) entity;
//		animate(grimReaper, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
//		modelCore.render(scale);
//	}
//
//	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
//		EntityGrimReaper grimReaper = (EntityGrimReaper) entity;
//		animator.update(grimReaper);
//		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, grimReaper);
//
//		if (grimReaper.getAnimation() == EntityGrimReaper.IDLE_ANIMATION && grimReaper.isEntityAlive()) {
//			animator.setAnimation(EntityGrimReaper.IDLE_ANIMATION);
//			animator.startKeyframe(30);
//			this.rotate(animator, humanoidRightArm, -45, 0, 0);
//			this.rotate(animator, humanoidRightArmSleeve, -45, 0, 0);
//			this.rotate(animator, humanoidLeftArm, -45, 0, 0);
//			this.rotate(animator, humanoidLeftArmSleeve, -45, 0, 0);
//			animator.move(scythe, -2, -8, 0.725F);
//			this.rotate(animator, scythe, -3.3205F, -12.3949F, -12.7809F);
//			animator.endKeyframe();
//			animator.resetKeyframe(30);
//		}
//
//		if (grimReaper.getAnimation() == EntityGrimReaper.MELEE_ATTACK_ANIMATION && grimReaper.isEntityAlive()) {
//			animator.setAnimation(EntityGrimReaper.MELEE_ATTACK_ANIMATION);
//			animator.startKeyframe(20);
//			this.rotate(animator, humanoidRightArm, -58.2727F, -42.4019F, -3.4223F);
//			this.rotate(animator, humanoidRightArmSleeve, -58.2727F, -42.4019F, -3.4223F);
//			this.rotate(animator, humanoidLeftArm, -83.2805F, -42.2102F, 5.1315F);
//			this.rotate(animator, humanoidLeftArmSleeve, -83.2805F, -42.2102F, 5.1315F);
//			animator.move(scythe, 8, -19, 9);
//			this.rotate(animator, scythe, -30.1203F, -13.9338F, -15.8417F);
//			animator.endKeyframe();
//			animator.startKeyframe(5);
//			this.rotate(animator, humanoidRightArm, -25.017F, 9.9853F, 0.5789F);
//			this.rotate(animator, humanoidRightArmSleeve, -25.017F, 9.9853F, 0.5789F);
//			this.rotate(animator, humanoidLeftArm, 22.9282F, -15.067F, 9.7721F);
//			this.rotate(animator, humanoidLeftArmSleeve, 22.9282F, -15.067F, 9.7721F);
//			animator.move(scythe, 8, 2, 5);
//			this.rotate(animator, scythe, -22.4054F, -9.9905F, 23.7739F);
//			animator.endKeyframe();
//			animator.resetKeyframe(10);
//		}
//
//		if (grimReaper.getAnimation() == EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION && grimReaper.isEntityAlive()) {
//			animator.setAnimation(EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION);
//			animator.startKeyframe(20);
//			this.rotate(animator, humanoidRightArm, -60.0079F, 12.4794F, 0.7185F);
//			this.rotate(animator, humanoidRightArmSleeve, -60.0079F, 12.4794F, 0.7185F);
//			this.rotate(animator, humanoidLeftArm, -50.8617F, -91.9818F, -17.9605F);
//			this.rotate(animator, humanoidLeftArmSleeve, -50.8617F, -91.9818F, -17.9605F);
//			animator.setStaticKeyframe(80);
//
//			animator.move(scythe, -10, 0, -13);
//			this.rotate(animator, scythe, 0, 0, -72.5F);
//			animator.endKeyframe();
//
//			// Repeat the animation to set the arms animation static during the scythe animation
//			this.rotate(animator, humanoidRightArm, -60.0079F, 12.4794F, 0.7185F);
//			this.rotate(animator, humanoidRightArmSleeve, -60.0079F, 12.4794F, 0.7185F);
//			this.rotate(animator, humanoidLeftArm, -50.8617F, -91.9818F, -17.9605F);
//			this.rotate(animator, humanoidLeftArmSleeve, -50.8617F, -91.9818F, -17.9605F);
//			animator.move(scythe, -10, 0, -13);
//			this.rotate(animator, scythe, 0, 0, -72.5F);
//
//			animator.startKeyframe(80);
//			animator.move(scythe, -10, 0, -13);
//			this.rotate(animator, scythe, 14.7511F, 2.3554F, -1502.5588F);
//			animator.endKeyframe();
//
////			this.rotate(animator, right_arm, -60.0079F, 12.4794F, 0.7185F);
////			this.rotate(animator, right_arm_sleve, -60.0079F, 12.4794F, 0.7185F);
////			this.rotate(animator, left_arm, -50.8617F, -91.9818F, -17.9605F);
////			this.rotate(animator, left_arm_sleve, -50.8617F, -91.9818F, -17.9605F);
////			animator.move(scythe, -10, 0, -13);
////			this.rotate(animator, scythe, 0, 0, -72.5F);
////
////			animator.startKeyframe(20);
////			animator.move(scythe, -10, 0, -13);
////			this.rotate(animator, scythe, 14.7511F, 2.3554F, -785.13F);
////			animator.endKeyframe();
////
////			this.rotate(animator, right_arm, -60.0079F, 12.4794F, 0.7185F);
////			this.rotate(animator, right_arm_sleve, -60.0079F, 12.4794F, 0.7185F);
////			this.rotate(animator, left_arm, -50.8617F, -91.9818F, -17.9605F);
////			this.rotate(animator, left_arm_sleve, -50.8617F, -91.9818F, -17.9605F);
////			animator.move(scythe, -10, 0, -13);
////			this.rotate(animator, scythe, 0, 0, -72.5F);
////
////			animator.startKeyframe(20);
////			animator.move(scythe, -10, 0, -13);
////			this.rotate(animator, scythe, 14.7511F, 2.3554F, -1145.13F);
////			animator.endKeyframe();
////
////			this.rotate(animator, right_arm, -60.0079F, 12.4794F, 0.7185F);
////			this.rotate(animator, right_arm_sleve, -60.0079F, 12.4794F, 0.7185F);
////			this.rotate(animator, left_arm, -50.8617F, -91.9818F, -17.9605F);
////			this.rotate(animator, left_arm_sleve, -50.8617F, -91.9818F, -17.9605F);
////			animator.move(scythe, -10, 0, -13);
////			this.rotate(animator, scythe, 0, 0, -72.5F);
////
////			animator.startKeyframe(20);
////			animator.move(scythe, -10, 0, -13);
////			this.rotate(animator, scythe, 14.7511F, 2.3554F, -1502.5588F);
////			animator.endKeyframe();
//
//			animator.resetKeyframe(20);
////			this.rotate(animator, right_arm, 0, 0, 0);
////			this.rotate(animator, right_arm_sleve, 0, 0, 0);
////			this.rotate(animator, left_arm, 0, 0, 0);
////			this.rotate(animator, left_arm_sleve, 0, 0, 0);
////			animator.move(scythe, 0, 0, 0);
////			this.rotate(animator, scythe, 0, 0, 0);
////			animator.endKeyframe();
//		}
//
//
//	}
//
//	@Override
//	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
//		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
//		resetToDefaultPose();
//
//		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
//		this.head.rotateAngleX = headPitch * 0.017453292F;
//
//		this.headwear.rotateAngleY = netHeadYaw * 0.017453292F;
//		this.headwear.rotateAngleX = headPitch * 0.017453292F;
//	}
//}