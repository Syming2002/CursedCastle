package com.syming.castlevaniacraft.client.models.entity;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.EntityFlameDemon;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFlameDemon extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer right_leg;
	private final AdvancedModelRenderer cube_r1;
	private final AdvancedModelRenderer cube_r2;
	private final AdvancedModelRenderer cube_r3;
	private final AdvancedModelRenderer right_foot;
	private final AdvancedModelRenderer cube_r4;
	private final AdvancedModelRenderer cube_r5;
	private final AdvancedModelRenderer cube_r6;
	private final AdvancedModelRenderer cube_r7;
	private final AdvancedModelRenderer cube_r8;
	private final AdvancedModelRenderer left_leg;
	private final AdvancedModelRenderer cube_r9;
	private final AdvancedModelRenderer cube_r10;
	private final AdvancedModelRenderer cube_r11;
	private final AdvancedModelRenderer left_foot;
	private final AdvancedModelRenderer cube_r12;
	private final AdvancedModelRenderer cube_r13;
	private final AdvancedModelRenderer cube_r14;
	private final AdvancedModelRenderer cube_r15;
	private final AdvancedModelRenderer body;
	private final AdvancedModelRenderer right_arm;
	private final AdvancedModelRenderer cube_r16;
	private final AdvancedModelRenderer cube_r17;
	private final AdvancedModelRenderer right_hand_finger1;
	private final AdvancedModelRenderer cube_r18;
	private final AdvancedModelRenderer right_hand_finger2;
	private final AdvancedModelRenderer cube_r19;
	private final AdvancedModelRenderer right_hand_finger3;
	private final AdvancedModelRenderer cube_r20;
	private final AdvancedModelRenderer right_hand_finger4;
	private final AdvancedModelRenderer cube_r21;
	private final AdvancedModelRenderer left_arm;
	private final AdvancedModelRenderer cube_r22;
	private final AdvancedModelRenderer cube_r23;
	private final AdvancedModelRenderer left_hand_finger1;
	private final AdvancedModelRenderer cube_r24;
	private final AdvancedModelRenderer left_hand_finger2;
	private final AdvancedModelRenderer cube_r25;
	private final AdvancedModelRenderer left_hand_finger3;
	private final AdvancedModelRenderer cube_r26;
	private final AdvancedModelRenderer left_hand_finger4;
	private final AdvancedModelRenderer cube_r27;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer cube_r28;
	private final AdvancedModelRenderer cube_r29;
	private final AdvancedModelRenderer cube_r30;
	private final AdvancedModelRenderer cube_r31;
	private final AdvancedModelRenderer cube_r32;
	private final AdvancedModelRenderer cube_r33;
	private final AdvancedModelRenderer right_wing;
	private final AdvancedModelRenderer left_wing;

	private ModelAnimator animator;

	public ModelFlameDemon() {
		textureWidth = 64;
		textureHeight = 64;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new AdvancedModelRenderer(this);
		right_leg.setRotationPoint(-3.6365F, -9.1558F, 3.1983F);
		model_core.addChild(right_leg);
		

		cube_r1 = new AdvancedModelRenderer(this);
		cube_r1.setRotationPoint(0.5588F, 2.4549F, -2.817F);
		right_leg.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.4363F, 0.4363F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 43, -1.0F, -3.5F, -1.0F, 2, 3, 4, 0.5F, false));

		cube_r2 = new AdvancedModelRenderer(this);
		cube_r2.setRotationPoint(-0.303F, 3.4058F, -4.6652F);
		right_leg.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.4363F, 0.4363F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 24, 48, -1.0F, -3.5F, -1.0F, 2, 5, 2, 0.125F, false));

		cube_r3 = new AdvancedModelRenderer(this);
		cube_r3.setRotationPoint(0.5615F, 7.7808F, -3.9483F);
		right_leg.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.4363F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 40, 49, -1.5F, -3.5F, -1.5F, 2, 4, 2, -0.125F, false));

		right_foot = new AdvancedModelRenderer(this);
		right_foot.setRotationPoint(-0.3314F, 8.713F, -4.5349F);
		right_leg.addChild(right_foot);
		

		cube_r4 = new AdvancedModelRenderer(this);
		cube_r4.setRotationPoint(-0.4946F, 0.0429F, -1.2853F);
		right_foot.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 12, 53, -0.2193F, -0.7F, -1.9205F, 1, 1, 1, -0.3F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 52, 30, -0.2193F, -0.3F, -2.9205F, 1, 1, 2, -0.3F, false));

		cube_r5 = new AdvancedModelRenderer(this);
		cube_r5.setRotationPoint(-0.4946F, 0.0429F, -1.2853F);
		right_foot.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.8727F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 52, 38, -0.7913F, -0.7F, -1.8978F, 1, 1, 1, -0.3F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 50, -0.7913F, -0.3F, -2.8978F, 1, 1, 2, -0.3F, false));

		cube_r6 = new AdvancedModelRenderer(this);
		cube_r6.setRotationPoint(-0.4946F, 0.0429F, -1.2853F);
		right_foot.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 53, -0.2298F, -0.6F, -1.2431F, 1, 1, 1, -0.025F, false));

		cube_r7 = new AdvancedModelRenderer(this);
		cube_r7.setRotationPoint(-0.4946F, 0.0429F, -1.2853F);
		right_foot.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.8727F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 4, 53, -0.7807F, -0.6F, -1.2205F, 1, 1, 1, -0.025F, false));

		cube_r8 = new AdvancedModelRenderer(this);
		cube_r8.setRotationPoint(-0.4946F, 0.0429F, -1.2853F);
		right_foot.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.4363F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 46, 6, -1.0375F, -0.6F, -0.3781F, 2, 1, 4, 0.0F, false));

		left_leg = new AdvancedModelRenderer(this);
		left_leg.setRotationPoint(3.6365F, -9.1558F, 3.1983F);
		model_core.addChild(left_leg);
		

		cube_r9 = new AdvancedModelRenderer(this);
		cube_r9.setRotationPoint(0.303F, 3.4058F, -4.6652F);
		left_leg.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.4363F, -0.4363F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 32, 48, -1.0F, -3.5F, -1.0F, 2, 5, 2, 0.125F, false));

		cube_r10 = new AdvancedModelRenderer(this);
		cube_r10.setRotationPoint(-0.5615F, 7.7808F, -3.9483F);
		left_leg.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, -0.4363F, 0.0F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 48, 49, -0.5F, -3.5F, -1.5F, 2, 4, 2, -0.125F, false));

		cube_r11 = new AdvancedModelRenderer(this);
		cube_r11.setRotationPoint(-0.5588F, 2.4549F, -2.817F);
		left_leg.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.4363F, -0.4363F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 42, 42, -1.0F, -3.5F, -1.0F, 2, 3, 4, 0.5F, false));

		left_foot = new AdvancedModelRenderer(this);
		left_foot.setRotationPoint(0.1329F, 8.713F, -4.4657F);
		left_leg.addChild(left_foot);
		

		cube_r12 = new AdvancedModelRenderer(this);
		cube_r12.setRotationPoint(0.6931F, 0.0429F, -1.3545F);
		left_foot.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 52, 33, -0.7807F, -0.3F, -2.9205F, 1, 1, 2, -0.3F, false));
		cube_r12.cubeList.add(new ModelBox(cube_r12, 16, 53, -0.7807F, -0.7F, -1.9205F, 1, 1, 1, -0.3F, false));

		cube_r13 = new AdvancedModelRenderer(this);
		cube_r13.setRotationPoint(0.6931F, 0.0429F, -1.3545F);
		left_foot.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, -0.8727F, 0.0F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 6, 50, -0.2087F, -0.3F, -2.8978F, 1, 1, 2, -0.3F, false));
		cube_r13.cubeList.add(new ModelBox(cube_r13, 52, 36, -0.2087F, -0.7F, -1.8978F, 1, 1, 1, -0.3F, false));
		cube_r13.cubeList.add(new ModelBox(cube_r13, 8, 53, -0.2193F, -0.6F, -1.2205F, 1, 1, 1, -0.025F, false));

		cube_r14 = new AdvancedModelRenderer(this);
		cube_r14.setRotationPoint(0.6931F, 0.0429F, -1.3545F);
		left_foot.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, 0.0F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 52, 40, -0.7702F, -0.6F, -1.2431F, 1, 1, 1, -0.025F, false));

		cube_r15 = new AdvancedModelRenderer(this);
		cube_r15.setRotationPoint(0.6931F, 0.0429F, -1.3545F);
		left_foot.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, -0.4363F, 0.0F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 12, 48, -0.9625F, -0.6F, -0.3781F, 2, 1, 4, 0.0F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 22, -4.0F, -20.0F, 1.0F, 8, 11, 4, 0.0F, false));

		right_arm = new AdvancedModelRenderer(this);
		right_arm.setRotationPoint(-3.9283F, -18.1467F, 3.0717F);
		model_core.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 44, 12, -3.0717F, -1.3533F, -1.5717F, 3, 3, 3, 0.3F, false));

		cube_r16 = new AdvancedModelRenderer(this);
		cube_r16.setRotationPoint(-5.3921F, 1.9646F, -5.4921F);
		right_arm.addChild(cube_r16);
		setRotationAngle(cube_r16, 1.2798F, -1.3156F, -1.5225F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 38, 30, -2.0F, -1.5F, -1.5F, 4, 3, 3, 0.1F, false));

		cube_r17 = new AdvancedModelRenderer(this);
		cube_r17.setRotationPoint(-3.4717F, 0.5467F, -1.8717F);
		right_arm.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.3295F, -0.7268F, -0.4754F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 30, 0, -3.5F, -1.5F, -1.5F, 5, 3, 3, 0.2F, false));

		right_hand_finger1 = new AdvancedModelRenderer(this);
		right_hand_finger1.setRotationPoint(-6.0421F, 3.8646F, -6.8921F);
		right_arm.addChild(right_hand_finger1);
		

		cube_r18 = new AdvancedModelRenderer(this);
		cube_r18.setRotationPoint(0.0F, 0.0F, -1.0F);
		right_hand_finger1.addChild(cube_r18);
		setRotationAngle(cube_r18, 2.0087F, -1.361F, -2.7127F);
		cube_r18.cubeList.add(new ModelBox(cube_r18, 40, 24, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		right_hand_finger2 = new AdvancedModelRenderer(this);
		right_hand_finger2.setRotationPoint(-6.3421F, 2.9646F, -7.1171F);
		right_arm.addChild(right_hand_finger2);
		

		cube_r19 = new AdvancedModelRenderer(this);
		cube_r19.setRotationPoint(0.0F, 0.0F, -1.0F);
		right_hand_finger2.addChild(cube_r19);
		setRotationAngle(cube_r19, 2.0087F, -1.361F, -2.7127F);
		cube_r19.cubeList.add(new ModelBox(cube_r19, 24, 36, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		right_hand_finger3 = new AdvancedModelRenderer(this);
		right_hand_finger3.setRotationPoint(-6.0921F, 1.5646F, -7.5921F);
		right_arm.addChild(right_hand_finger3);
		

		cube_r20 = new AdvancedModelRenderer(this);
		cube_r20.setRotationPoint(0.0F, 0.4F, -0.7F);
		right_hand_finger3.addChild(cube_r20);
		setRotationAngle(cube_r20, 1.2007F, -1.0883F, -1.9025F);
		cube_r20.cubeList.add(new ModelBox(cube_r20, 24, 30, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		right_hand_finger4 = new AdvancedModelRenderer(this);
		right_hand_finger4.setRotationPoint(-4.9921F, 1.3646F, -7.8921F);
		right_arm.addChild(right_hand_finger4);
		

		cube_r21 = new AdvancedModelRenderer(this);
		cube_r21.setRotationPoint(0.0F, 0.3F, -0.5F);
		right_hand_finger4.addChild(cube_r21);
		setRotationAngle(cube_r21, 1.8447F, -1.112F, -1.2885F);
		cube_r21.cubeList.add(new ModelBox(cube_r21, 30, 12, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		left_arm = new AdvancedModelRenderer(this);
		left_arm.setRotationPoint(3.9283F, -18.1467F, 3.0717F);
		model_core.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 46, 0, 0.0717F, -1.3533F, -1.5717F, 3, 3, 3, 0.3F, false));

		cube_r22 = new AdvancedModelRenderer(this);
		cube_r22.setRotationPoint(5.392F, 1.9646F, -5.492F);
		left_arm.addChild(cube_r22);
		setRotationAngle(cube_r22, 1.2798F, 1.3156F, 1.5225F);
		cube_r22.cubeList.add(new ModelBox(cube_r22, 38, 36, -2.0F, -1.5F, -1.5F, 4, 3, 3, 0.1F, false));

		cube_r23 = new AdvancedModelRenderer(this);
		cube_r23.setRotationPoint(3.4717F, 0.5467F, -1.8717F);
		left_arm.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.3295F, 0.7268F, 0.4754F);
		cube_r23.cubeList.add(new ModelBox(cube_r23, 30, 6, -1.5F, -1.5F, -1.5F, 5, 3, 3, 0.2F, false));

		left_hand_finger1 = new AdvancedModelRenderer(this);
		left_hand_finger1.setRotationPoint(6.242F, 3.8646F, -7.067F);
		left_arm.addChild(left_hand_finger1);
		

		cube_r24 = new AdvancedModelRenderer(this);
		cube_r24.setRotationPoint(0.0F, 0.0F, -0.8F);
		left_hand_finger1.addChild(cube_r24);
		setRotationAngle(cube_r24, 1.1567F, 1.3633F, 1.8441F);
		cube_r24.cubeList.add(new ModelBox(cube_r24, 14, 42, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		left_hand_finger2 = new AdvancedModelRenderer(this);
		left_hand_finger2.setRotationPoint(6.492F, 2.9646F, -7.367F);
		left_arm.addChild(left_hand_finger2);
		

		cube_r25 = new AdvancedModelRenderer(this);
		cube_r25.setRotationPoint(0.0F, 0.0F, -0.8F);
		left_hand_finger2.addChild(cube_r25);
		setRotationAngle(cube_r25, 1.3612F, 1.3768F, 2.0527F);
		cube_r25.cubeList.add(new ModelBox(cube_r25, 0, 37, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		left_hand_finger3 = new AdvancedModelRenderer(this);
		left_hand_finger3.setRotationPoint(6.092F, 1.6646F, -7.667F);
		left_arm.addChild(left_hand_finger3);
		

		cube_r26 = new AdvancedModelRenderer(this);
		cube_r26.setRotationPoint(0.0F, 0.3F, -0.7F);
		left_hand_finger3.addChild(cube_r26);
		setRotationAngle(cube_r26, 1.1277F, 1.1685F, 1.8219F);
		cube_r26.cubeList.add(new ModelBox(cube_r26, 40, 18, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		left_hand_finger4 = new AdvancedModelRenderer(this);
		left_hand_finger4.setRotationPoint(4.992F, 1.2646F, -7.717F);
		left_arm.addChild(left_hand_finger4);
		

		cube_r27 = new AdvancedModelRenderer(this);
		cube_r27.setRotationPoint(0.0F, 0.4F, -0.6F);
		left_hand_finger4.addChild(cube_r27);
		setRotationAngle(cube_r27, 2.1596F, 1.1209F, 1.6794F);
		cube_r27.cubeList.add(new ModelBox(cube_r27, 28, 42, -2.0F, -1.5F, -1.5F, 4, 3, 3, -1.225F, false));

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -22.0571F, 3.2706F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 24, 22, -2.0F, -1.9429F, -2.2706F, 4, 4, 4, 0.0F, false));

		cube_r28 = new AdvancedModelRenderer(this);
		cube_r28.setRotationPoint(1.2536F, -2.2429F, -0.2268F);
		head.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.4363F, 0.0F);
		cube_r28.cubeList.add(new ModelBox(cube_r28, 14, 37, -0.5F, -1.5F, -0.5F, 1, 1, 2, -0.2F, false));

		cube_r29 = new AdvancedModelRenderer(this);
		cube_r29.setRotationPoint(1.0F, -2.2429F, -0.7706F);
		head.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, 0.4363F, 0.0F);
		cube_r29.cubeList.add(new ModelBox(cube_r29, 20, 37, -0.5F, -1.5F, -0.5F, 1, 2, 1, -0.2F, false));

		cube_r30 = new AdvancedModelRenderer(this);
		cube_r30.setRotationPoint(2.0988F, -1.8429F, 1.5858F);
		head.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.0F, 0.4363F, 0.0F);
		cube_r30.cubeList.add(new ModelBox(cube_r30, 14, 40, -0.5F, -2.5F, -1.5F, 1, 1, 1, -0.2F, false));

		cube_r31 = new AdvancedModelRenderer(this);
		cube_r31.setRotationPoint(-2.0988F, -1.8429F, 1.5858F);
		head.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.0F, -0.4363F, 0.0F);
		cube_r31.cubeList.add(new ModelBox(cube_r31, 18, 40, -0.5F, -2.5F, -1.5F, 1, 1, 1, -0.2F, false));

		cube_r32 = new AdvancedModelRenderer(this);
		cube_r32.setRotationPoint(-1.2536F, -2.2429F, -0.2268F);
		head.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.0F, -0.4363F, 0.0F);
		cube_r32.cubeList.add(new ModelBox(cube_r32, 30, 18, -0.5F, -1.5F, -0.5F, 1, 1, 2, -0.2F, false));

		cube_r33 = new AdvancedModelRenderer(this);
		cube_r33.setRotationPoint(-1.0F, -2.2429F, -0.7706F);
		head.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.0F, -0.4363F, 0.0F);
		cube_r33.cubeList.add(new ModelBox(cube_r33, 36, 18, -0.5F, -1.5F, -0.5F, 1, 2, 1, -0.2F, false));

		right_wing = new AdvancedModelRenderer(this);
		right_wing.setRotationPoint(0.0F, -14.5F, 5.05F);
		model_core.addChild(right_wing);
		right_wing.cubeList.add(new ModelBox(right_wing, 0, 0, -15.0F, -5.5F, 0.0F, 15, 11, 0, 0.0F, false));

		left_wing = new AdvancedModelRenderer(this);
		left_wing.setRotationPoint(0.0F, -14.5F, 5.0F);
		model_core.addChild(left_wing);
		left_wing.cubeList.add(new ModelBox(left_wing, 0, 11, 0.0F, -5.5F, 0.0F, 15, 11, 0, 0.0F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityFlameDemon flameDemon = (EntityFlameDemon) entity;
		animate(flameDemon, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityFlameDemon flameDemon = (EntityFlameDemon) entity;
		animator.update(flameDemon);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, flameDemon);

		if (flameDemon.getAnimation() == EntityFlameDemon.MELEE_ATTACK_ANIMATION && flameDemon.isEntityAlive()) {
			animator.setAnimation(EntityFlameDemon.MELEE_ATTACK_ANIMATION);

			animator.startKeyframe(40);
			this.rotate(animator, left_arm, 0, -57.5F, 0);
			this.rotate(animator, right_arm, 0, -40, 0);
			this.rotate(animator, right_leg, 95, 0, 0);
			animator.endKeyframe();
			animator.startKeyframe(5);
			this.rotate(animator, left_arm, 0, 0, 0);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, right_leg, -57.5F, 0, 0);
			animator.endKeyframe();
			animator.startKeyframe(15);
			this.rotate(animator, right_leg, 0, 0, 0);
			animator.endKeyframe();
		}


		if (flameDemon.getAnimation() == EntityFlameDemon.BIG_FIREBALL_ANIMATION && flameDemon.isEntityAlive()) {
			animator.setAnimation(EntityFlameDemon.BIG_FIREBALL_ANIMATION);

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, -90, 0, 20);
			this.rotate(animator, left_arm, -90, 0, -20);
			animator.setStaticKeyframe(30);
			animator.endKeyframe();
			animator.startKeyframe(5);
			this.rotate(animator, right_arm, -0.0419F, -26.568F, -17.4078F);
			this.rotate(animator, left_arm, -8.194F, 30.1925F, 7.3107F);
			animator.endKeyframe();
			animator.startKeyframe(15);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, left_arm, 0, 0, 0);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;

		float wingFlapAngle = Math.abs((float) Math.cos(0.275F * ageInTicks)) * 0.5F;

		this.right_wing.rotateAngleY = wingFlapAngle;
		this.left_wing.rotateAngleY = -wingFlapAngle;

		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}