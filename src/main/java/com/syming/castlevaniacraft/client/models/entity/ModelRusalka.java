package com.syming.castlevaniacraft.client.models.entity;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.boss.EntityRusalka;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRusalka extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer right_leg;
	private final AdvancedModelRenderer left_leg;
	private final AdvancedModelRenderer body;
	private final AdvancedModelRenderer right_arm;
	private final AdvancedModelRenderer left_arm;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer hair1;
	private final AdvancedModelRenderer cube_r1;
	private final AdvancedModelRenderer hair2;
	private final AdvancedModelRenderer cube_r2;
	private final AdvancedModelRenderer hair3;
	private final AdvancedModelRenderer cube_r3;
	private final AdvancedModelRenderer hair4;
	private final AdvancedModelRenderer cube_r4;
	private final AdvancedModelRenderer hair5;
	private final AdvancedModelRenderer cube_r5;
	private final AdvancedModelRenderer hair6;
	private final AdvancedModelRenderer cube_r6;
	private final AdvancedModelRenderer hair7;
	private final AdvancedModelRenderer cube_r7;
	private final AdvancedModelRenderer hair8;
	private final AdvancedModelRenderer cube_r8;
	private final AdvancedModelRenderer hair9;
	private final AdvancedModelRenderer cube_r9;
	private final AdvancedModelRenderer hair10;
	private final AdvancedModelRenderer cube_r10;
	private final AdvancedModelRenderer hair11;
	private final AdvancedModelRenderer cube_r11;
	private final AdvancedModelRenderer hair12;
	private final AdvancedModelRenderer cube_r12;
	private final AdvancedModelRenderer hair13;
	private final AdvancedModelRenderer cube_r13;
	private final AdvancedModelRenderer hair14;
	private final AdvancedModelRenderer cube_r14;
	private final AdvancedModelRenderer hair15;
	private final AdvancedModelRenderer cube_r15;
	private final AdvancedModelRenderer hair16;
	private final AdvancedModelRenderer cube_r16;
	private final AdvancedModelRenderer hair17;
	private final AdvancedModelRenderer cube_r17;
	private final AdvancedModelRenderer hair18;
	private final AdvancedModelRenderer cube_r18;
	private final AdvancedModelRenderer hair19;
	private final AdvancedModelRenderer cube_r19;
	private final AdvancedModelRenderer hair20;
	private final AdvancedModelRenderer cube_r20;
	private final AdvancedModelRenderer hair21;
	private final AdvancedModelRenderer cube_r21;
	private final AdvancedModelRenderer hair22;
	private final AdvancedModelRenderer cube_r22;
	private final AdvancedModelRenderer hair23;
	private final AdvancedModelRenderer cube_r23;
	private final AdvancedModelRenderer hair24;
	private final AdvancedModelRenderer cube_r24;
	private final AdvancedModelRenderer hair25;
	private final AdvancedModelRenderer cube_r25;
	private final AdvancedModelRenderer hair26;
	private final AdvancedModelRenderer cube_r26;
	private final AdvancedModelRenderer hair27;
	private final AdvancedModelRenderer cube_r27;
	private final AdvancedModelRenderer hair28;
	private final AdvancedModelRenderer cube_r28;
	private final AdvancedModelRenderer hair29;
	private final AdvancedModelRenderer cube_r29;
	private final AdvancedModelRenderer hair30;
	private final AdvancedModelRenderer cube_r30;
	private final AdvancedModelRenderer hair31;
	private final AdvancedModelRenderer cube_r31;
	private final AdvancedModelRenderer hair32;
	private final AdvancedModelRenderer cube_r32;
	private final AdvancedModelRenderer hair33;
	private final AdvancedModelRenderer cube_r33;
	private final AdvancedModelRenderer hair34;
	private final AdvancedModelRenderer cube_r34;
	private final AdvancedModelRenderer hair35;
	private final AdvancedModelRenderer cube_r35;
	private final AdvancedModelRenderer hair36;
	private final AdvancedModelRenderer cube_r36;
	private final AdvancedModelRenderer hair37;
	private final AdvancedModelRenderer cube_r37;
	private final AdvancedModelRenderer hair38;
	private final AdvancedModelRenderer cube_r38;
	private final AdvancedModelRenderer hair39;
	private final AdvancedModelRenderer cube_r39;
	private final AdvancedModelRenderer hair40;
	private final AdvancedModelRenderer cube_r40;
	private final AdvancedModelRenderer hair41;
	private final AdvancedModelRenderer cube_r41;
	private final AdvancedModelRenderer hair42;
	private final AdvancedModelRenderer cube_r42;
	private final AdvancedModelRenderer hair43;
	private final AdvancedModelRenderer cube_r43;
	private final AdvancedModelRenderer hair44;
	private final AdvancedModelRenderer cube_r44;
	private final AdvancedModelRenderer hair45;
	private final AdvancedModelRenderer cube_r45;
	private final AdvancedModelRenderer hair46;
	private final AdvancedModelRenderer cube_r46;
	private final AdvancedModelRenderer hair47;
	private final AdvancedModelRenderer cube_r47;
	private final AdvancedModelRenderer hair48;
	private final AdvancedModelRenderer cube_r48;
	private final AdvancedModelRenderer hair49;
	private final AdvancedModelRenderer cube_r49;
	private final AdvancedModelRenderer hair50;
	private final AdvancedModelRenderer cube_r50;

	private ModelAnimator animator;

	public ModelRusalka() {
		textureWidth = 128;
		textureHeight = 128;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);


		right_leg = new AdvancedModelRenderer(this);
		right_leg.setRotationPoint(-1.5F, -12.0F, 1.5F);
		model_core.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 18, 16, -1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F, false));

		left_leg = new AdvancedModelRenderer(this);
		left_leg.setRotationPoint(1.5F, -12.0F, 1.5F);
		model_core.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 30, 16, -1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -18.0F, 1.5F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 16, -3.0F, -6.0F, -1.5F, 6, 12, 3, 0.0F, false));

		right_arm = new AdvancedModelRenderer(this);
		right_arm.setRotationPoint(-4.0F, -23.0F, 1.0F);
		model_core.addChild(right_arm);
		setRotationAngle(right_arm, 0.0F, 0.0F, 0.2618F);
		right_arm.cubeList.add(new ModelBox(right_arm, 0, 31, -1.8459F, -0.7689F, -1.0F, 3, 6, 3, -0.025F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 24, 31, -1.8459F, 5.2311F, -1.0F, 3, 4, 3, 0.15F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 32, 14, -2.3459F, 13.9311F, 1.5F, 1, 1, 1, -0.15F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 36, 31, -1.8459F, 9.7311F, -1.0F, 3, 4, 3, 0.35F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 44, 12, -1.3459F, 13.9311F, -1.5F, 1, 1, 1, -0.15F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 44, 58, -2.3459F, 13.9311F, -0.5F, 1, 3, 1, -0.15F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 56, 58, -2.3459F, 13.9311F, 0.5F, 1, 2, 1, -0.15F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 0, 60, -2.3459F, 13.9311F, -1.5F, 1, 2, 1, -0.15F, false));

		left_arm = new AdvancedModelRenderer(this);
		left_arm.setRotationPoint(4.0F, -23.0F, 1.0F);
		model_core.addChild(left_arm);
		setRotationAngle(left_arm, 0.0F, 0.0F, -0.2618F);
		left_arm.cubeList.add(new ModelBox(left_arm, 32, 0, -1.1507F, 9.7053F, -1.0F, 3, 4, 3, 0.35F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 48, 58, 1.3493F, 13.9053F, -1.5F, 1, 2, 1, -0.15F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 20, 40, 0.3493F, 13.9053F, -1.5F, 1, 1, 1, -0.15F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 40, 58, 1.3493F, 13.9053F, -0.5F, 1, 3, 1, -0.15F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 36, 14, 1.3493F, 13.9053F, 1.5F, 1, 1, 1, -0.15F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 52, 58, 1.3493F, 13.9053F, 0.5F, 1, 2, 1, -0.15F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 12, 31, -1.1507F, -0.7947F, -1.0F, 3, 6, 3, -0.025F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 32, 7, -1.1507F, 5.2053F, -1.0F, 3, 4, 3, 0.15F, false));

		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -27.2F, 1.5F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, -0.8F, false));

		hair1 = new AdvancedModelRenderer(this);
		hair1.setRotationPoint(1.9F, -3.0F, 2.8F);
		head.addChild(hair1);


		cube_r1 = new AdvancedModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.8F, 2.0F);
		hair1.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.4363F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 24, 38, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair2 = new AdvancedModelRenderer(this);
		hair2.setRotationPoint(0.0F, 1.6771F, 3.6978F);
		hair1.addChild(hair2);


		cube_r2 = new AdvancedModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 1.5F, 1.4F);
		hair2.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.8727F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 34, 38, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair3 = new AdvancedModelRenderer(this);
		hair3.setRotationPoint(0.0F, 2.9653F, 2.6387F);
		hair2.addChild(hair3);


		cube_r3 = new AdvancedModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.5F, 2.0F);
		hair3.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.2182F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 40, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair4 = new AdvancedModelRenderer(this);
		hair4.setRotationPoint(0.0F, 0.9838F, 3.8267F);
		hair3.addChild(hair4);


		cube_r4 = new AdvancedModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 1.3F, 1.6F);
		hair4.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.7418F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 10, 40, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair5 = new AdvancedModelRenderer(this);
		hair5.setRotationPoint(0.0F, 2.5642F, 2.9734F);
		hair4.addChild(hair5);


		cube_r5 = new AdvancedModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 2.1F);
		hair5.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0436F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 42, 14, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair6 = new AdvancedModelRenderer(this);
		hair6.setRotationPoint(0.0F, -0.0421F, 4.0102F);
		hair5.addChild(hair6);


		cube_r6 = new AdvancedModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.8F, 1.9F);
		hair6.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.4363F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 42, 18, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair7 = new AdvancedModelRenderer(this);
		hair7.setRotationPoint(0.0F, 1.6124F, 3.6658F);
		hair6.addChild(hair7);


		cube_r7 = new AdvancedModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, -0.4F, 2.0F);
		hair7.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.2182F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 20, 42, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair8 = new AdvancedModelRenderer(this);
		hair8.setRotationPoint(0.0F, -0.8F, 3.9F);
		hair7.addChild(hair8);
		setRotationAngle(hair8, -0.5236F, 0.0F, 0.0F);


		cube_r8 = new AdvancedModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, -0.4874F, 1.9812F);
		hair8.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.2182F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 42, 22, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair9 = new AdvancedModelRenderer(this);
		hair9.setRotationPoint(0.0F, 1.1371F, 3.8201F);
		hair8.addChild(hair9);


		cube_r9 = new AdvancedModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, -2.4902F, 2.0664F);
		hair9.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.2182F, 0.0F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 42, 26, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair10 = new AdvancedModelRenderer(this);
		hair10.setRotationPoint(0.0F, -2.8486F, 4.005F);
		hair9.addChild(hair10);
		setRotationAngle(hair10, -0.7854F, 0.0F, 0.0F);


		cube_r10 = new AdvancedModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, -0.495F, 1.9092F);
		hair10.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.2182F, 0.0F, 0.0F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 30, 42, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair11 = new AdvancedModelRenderer(this);
		hair11.setRotationPoint(-0.1F, -3.0F, 3.1F);
		head.addChild(hair11);


		cube_r11 = new AdvancedModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.8F, 1.7F);
		hair11.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.4363F, 0.0F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 40, 42, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair12 = new AdvancedModelRenderer(this);
		hair12.setRotationPoint(0.0F, 1.5771F, 3.4978F);
		hair11.addChild(hair12);


		cube_r12 = new AdvancedModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, 1.6F, 1.3F);
		hair12.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.8727F, 0.0F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 0, 44, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair13 = new AdvancedModelRenderer(this);
		hair13.setRotationPoint(0.0F, 3.0653F, 2.5387F);
		hair12.addChild(hair13);


		cube_r13 = new AdvancedModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, 0.5F, 2.0F);
		hair13.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.2182F, 0.0F, 0.0F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 44, 0, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair14 = new AdvancedModelRenderer(this);
		hair14.setRotationPoint(0.0F, 0.9838F, 3.9267F);
		hair13.addChild(hair14);


		cube_r14 = new AdvancedModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 1.3F, 1.5F);
		hair14.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.7418F, 0.0F, 0.0F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 44, 4, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair15 = new AdvancedModelRenderer(this);
		hair15.setRotationPoint(0.0F, 2.6642F, 2.9734F);
		hair14.addChild(hair15);


		cube_r15 = new AdvancedModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, -0.1F, 2.0F);
		hair15.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0436F, 0.0F, 0.0F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 44, 8, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair16 = new AdvancedModelRenderer(this);
		hair16.setRotationPoint(0.0F, -0.1421F, 3.9102F);
		hair15.addChild(hair16);


		cube_r16 = new AdvancedModelRenderer(this);
		cube_r16.setRotationPoint(0.0F, 0.8F, 1.9F);
		hair16.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.4363F, 0.0F, 0.0F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 10, 44, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair17 = new AdvancedModelRenderer(this);
		hair17.setRotationPoint(0.0F, 1.6124F, 3.6658F);
		hair16.addChild(hair17);


		cube_r17 = new AdvancedModelRenderer(this);
		cube_r17.setRotationPoint(0.0F, -0.4F, 2.0F);
		hair17.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.2182F, 0.0F, 0.0F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 44, 38, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair18 = new AdvancedModelRenderer(this);
		hair18.setRotationPoint(0.0F, -0.7315F, 3.8595F);
		hair17.addChild(hair18);
		setRotationAngle(hair18, -0.5236F, 0.0F, 0.0F);


		cube_r18 = new AdvancedModelRenderer(this);
		cube_r18.setRotationPoint(0.0F, -0.567F, 1.9821F);
		hair18.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.2182F, 0.0F, 0.0F);
		cube_r18.cubeList.add(new ModelBox(cube_r18, 20, 46, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair19 = new AdvancedModelRenderer(this);
		hair19.setRotationPoint(0.0F, -0.9327F, 3.7872F);
		hair18.addChild(hair19);


		cube_r19 = new AdvancedModelRenderer(this);
		cube_r19.setRotationPoint(0.0F, -0.5F, 2.1F);
		hair19.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.2182F, 0.0F, 0.0F);
		cube_r19.cubeList.add(new ModelBox(cube_r19, 30, 46, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair20 = new AdvancedModelRenderer(this);
		hair20.setRotationPoint(0.0F, -0.8584F, 4.0386F);
		hair19.addChild(hair20);
		setRotationAngle(hair20, -0.7854F, 0.0F, 0.0F);


		cube_r20 = new AdvancedModelRenderer(this);
		cube_r20.setRotationPoint(0.0F, -0.495F, 1.9092F);
		hair20.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.2182F, 0.0F, 0.0F);
		cube_r20.cubeList.add(new ModelBox(cube_r20, 40, 46, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair21 = new AdvancedModelRenderer(this);
		hair21.setRotationPoint(-2.1F, -3.0F, 3.1F);
		head.addChild(hair21);


		cube_r21 = new AdvancedModelRenderer(this);
		cube_r21.setRotationPoint(0.0F, 0.8F, 1.7F);
		hair21.addChild(cube_r21);
		setRotationAngle(cube_r21, -0.4363F, 0.0F, 0.0F);
		cube_r21.cubeList.add(new ModelBox(cube_r21, 0, 48, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair22 = new AdvancedModelRenderer(this);
		hair22.setRotationPoint(0.0F, 1.6771F, 3.3978F);
		hair21.addChild(hair22);


		cube_r22 = new AdvancedModelRenderer(this);
		cube_r22.setRotationPoint(0.0F, 1.5F, 1.4F);
		hair22.addChild(cube_r22);
		setRotationAngle(cube_r22, -0.8727F, 0.0F, 0.0F);
		cube_r22.cubeList.add(new ModelBox(cube_r22, 10, 48, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair23 = new AdvancedModelRenderer(this);
		hair23.setRotationPoint(0.0F, 3.0653F, 2.6387F);
		hair22.addChild(hair23);


		cube_r23 = new AdvancedModelRenderer(this);
		cube_r23.setRotationPoint(0.0F, 0.4F, 2.0F);
		hair23.addChild(cube_r23);
		setRotationAngle(cube_r23, -0.2182F, 0.0F, 0.0F);
		cube_r23.cubeList.add(new ModelBox(cube_r23, 48, 30, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair24 = new AdvancedModelRenderer(this);
		hair24.setRotationPoint(0.0F, 0.7838F, 3.9267F);
		hair23.addChild(hair24);


		cube_r24 = new AdvancedModelRenderer(this);
		cube_r24.setRotationPoint(0.0F, 1.4F, 1.5F);
		hair24.addChild(cube_r24);
		setRotationAngle(cube_r24, -0.7418F, 0.0F, 0.0F);
		cube_r24.cubeList.add(new ModelBox(cube_r24, 48, 34, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair25 = new AdvancedModelRenderer(this);
		hair25.setRotationPoint(0.0F, 2.6642F, 2.8734F);
		hair24.addChild(hair25);


		cube_r25 = new AdvancedModelRenderer(this);
		cube_r25.setRotationPoint(0.0F, 0.0F, 2.1F);
		hair25.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0436F, 0.0F, 0.0F);
		cube_r25.cubeList.add(new ModelBox(cube_r25, 20, 50, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair26 = new AdvancedModelRenderer(this);
		hair26.setRotationPoint(0.0F, -0.0421F, 4.0102F);
		hair25.addChild(hair26);


		cube_r26 = new AdvancedModelRenderer(this);
		cube_r26.setRotationPoint(0.0F, 0.8F, 1.9F);
		hair26.addChild(cube_r26);
		setRotationAngle(cube_r26, -0.4363F, 0.0F, 0.0F);
		cube_r26.cubeList.add(new ModelBox(cube_r26, 30, 50, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair27 = new AdvancedModelRenderer(this);
		hair27.setRotationPoint(0.0F, 1.6124F, 3.6658F);
		hair26.addChild(hair27);


		cube_r27 = new AdvancedModelRenderer(this);
		cube_r27.setRotationPoint(0.0F, -0.4F, 2.0F);
		hair27.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.2182F, 0.0F, 0.0F);
		cube_r27.cubeList.add(new ModelBox(cube_r27, 40, 50, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair28 = new AdvancedModelRenderer(this);
		hair28.setRotationPoint(0.0F, -0.7315F, 3.8595F);
		hair27.addChild(hair28);
		setRotationAngle(hair28, -0.5236F, 0.0F, 0.0F);


		cube_r28 = new AdvancedModelRenderer(this);
		cube_r28.setRotationPoint(0.0F, -0.567F, 1.9821F);
		hair28.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.2182F, 0.0F, 0.0F);
		cube_r28.cubeList.add(new ModelBox(cube_r28, 50, 42, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair29 = new AdvancedModelRenderer(this);
		hair29.setRotationPoint(0.0F, 1.0686F, 3.8605F);
		hair28.addChild(hair29);


		cube_r29 = new AdvancedModelRenderer(this);
		cube_r29.setRotationPoint(0.0F, -2.5013F, 2.0267F);
		hair29.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.2182F, 0.0F, 0.0F);
		cube_r29.cubeList.add(new ModelBox(cube_r29, 50, 46, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair30 = new AdvancedModelRenderer(this);
		hair30.setRotationPoint(0.0F, -2.8584F, 3.9386F);
		hair29.addChild(hair30);
		setRotationAngle(hair30, -0.7854F, 0.0F, 0.0F);


		cube_r30 = new AdvancedModelRenderer(this);
		cube_r30.setRotationPoint(0.0F, -0.5148F, 1.9271F);
		hair30.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.2182F, 0.0F, 0.0F);
		cube_r30.cubeList.add(new ModelBox(cube_r30, 50, 50, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair31 = new AdvancedModelRenderer(this);
		hair31.setRotationPoint(-1.1F, -2.0F, 3.1F);
		head.addChild(hair31);


		cube_r31 = new AdvancedModelRenderer(this);
		cube_r31.setRotationPoint(0.0F, 0.8F, 1.7F);
		hair31.addChild(cube_r31);
		setRotationAngle(cube_r31, -0.4363F, 0.0F, 0.0F);
		cube_r31.cubeList.add(new ModelBox(cube_r31, 0, 52, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair32 = new AdvancedModelRenderer(this);
		hair32.setRotationPoint(0.0F, 1.5771F, 3.4978F);
		hair31.addChild(hair32);


		cube_r32 = new AdvancedModelRenderer(this);
		cube_r32.setRotationPoint(0.0F, 1.6F, 1.3F);
		hair32.addChild(cube_r32);
		setRotationAngle(cube_r32, -0.8727F, 0.0F, 0.0F);
		cube_r32.cubeList.add(new ModelBox(cube_r32, 10, 52, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair33 = new AdvancedModelRenderer(this);
		hair33.setRotationPoint(0.0F, 3.0653F, 2.5387F);
		hair32.addChild(hair33);


		cube_r33 = new AdvancedModelRenderer(this);
		cube_r33.setRotationPoint(0.0F, 0.5F, 2.0F);
		hair33.addChild(cube_r33);
		setRotationAngle(cube_r33, -0.2182F, 0.0F, 0.0F);
		cube_r33.cubeList.add(new ModelBox(cube_r33, 52, 12, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair34 = new AdvancedModelRenderer(this);
		hair34.setRotationPoint(0.0F, 0.8838F, 3.8267F);
		hair33.addChild(hair34);


		cube_r34 = new AdvancedModelRenderer(this);
		cube_r34.setRotationPoint(0.0F, 1.4F, 1.6F);
		hair34.addChild(cube_r34);
		setRotationAngle(cube_r34, -0.7418F, 0.0F, 0.0F);
		cube_r34.cubeList.add(new ModelBox(cube_r34, 52, 16, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair35 = new AdvancedModelRenderer(this);
		hair35.setRotationPoint(0.0F, 2.6642F, 2.9734F);
		hair34.addChild(hair35);


		cube_r35 = new AdvancedModelRenderer(this);
		cube_r35.setRotationPoint(0.0F, 0.0F, 2.1F);
		hair35.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.0436F, 0.0F, 0.0F);
		cube_r35.cubeList.add(new ModelBox(cube_r35, 52, 20, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair36 = new AdvancedModelRenderer(this);
		hair36.setRotationPoint(0.0F, -0.0421F, 3.9102F);
		hair35.addChild(hair36);


		cube_r36 = new AdvancedModelRenderer(this);
		cube_r36.setRotationPoint(0.0F, 0.8F, 2.0F);
		hair36.addChild(cube_r36);
		setRotationAngle(cube_r36, -0.4363F, 0.0F, 0.0F);
		cube_r36.cubeList.add(new ModelBox(cube_r36, 52, 24, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair37 = new AdvancedModelRenderer(this);
		hair37.setRotationPoint(0.0F, 1.6124F, 3.7658F);
		hair36.addChild(hair37);


		cube_r37 = new AdvancedModelRenderer(this);
		cube_r37.setRotationPoint(0.0F, -0.4F, 2.0F);
		hair37.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.2182F, 0.0F, 0.0F);
		cube_r37.cubeList.add(new ModelBox(cube_r37, 54, 0, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair38 = new AdvancedModelRenderer(this);
		hair38.setRotationPoint(0.0F, -0.7315F, 3.8595F);
		hair37.addChild(hair38);
		setRotationAngle(hair38, -0.5236F, 0.0F, 0.0F);


		cube_r38 = new AdvancedModelRenderer(this);
		cube_r38.setRotationPoint(0.0F, -0.567F, 1.9821F);
		hair38.addChild(cube_r38);
		setRotationAngle(cube_r38, 0.2182F, 0.0F, 0.0F);
		cube_r38.cubeList.add(new ModelBox(cube_r38, 54, 4, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair39 = new AdvancedModelRenderer(this);
		hair39.setRotationPoint(0.0F, -0.9314F, 3.8605F);
		hair38.addChild(hair39);


		cube_r39 = new AdvancedModelRenderer(this);
		cube_r39.setRotationPoint(0.0F, -0.5013F, 2.0267F);
		hair39.addChild(cube_r39);
		setRotationAngle(cube_r39, 0.2182F, 0.0F, 0.0F);
		cube_r39.cubeList.add(new ModelBox(cube_r39, 54, 8, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair40 = new AdvancedModelRenderer(this);
		hair40.setRotationPoint(0.0F, -0.7584F, 3.8386F);
		hair39.addChild(hair40);
		setRotationAngle(hair40, -0.7854F, 0.0F, 0.0F);


		cube_r40 = new AdvancedModelRenderer(this);
		cube_r40.setRotationPoint(0.0F, -0.2562F, 0.0271F);
		hair40.addChild(cube_r40);
		setRotationAngle(cube_r40, 0.2182F, 0.0F, 0.0F);
		cube_r40.cubeList.add(new ModelBox(cube_r40, 20, 54, -0.5F, 0.0207F, -0.0585F, 1, 0, 4, 0.0F, false));

		hair41 = new AdvancedModelRenderer(this);
		hair41.setRotationPoint(0.9F, -2.0F, 3.1F);
		head.addChild(hair41);


		cube_r41 = new AdvancedModelRenderer(this);
		cube_r41.setRotationPoint(0.0F, 0.8F, 1.7F);
		hair41.addChild(cube_r41);
		setRotationAngle(cube_r41, -0.4363F, 0.0F, 0.0F);
		cube_r41.cubeList.add(new ModelBox(cube_r41, 30, 54, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair42 = new AdvancedModelRenderer(this);
		hair42.setRotationPoint(0.0F, 1.6771F, 3.3978F);
		hair41.addChild(hair42);


		cube_r42 = new AdvancedModelRenderer(this);
		cube_r42.setRotationPoint(0.0F, 1.5F, 1.4F);
		hair42.addChild(cube_r42);
		setRotationAngle(cube_r42, -0.8727F, 0.0F, 0.0F);
		cube_r42.cubeList.add(new ModelBox(cube_r42, 54, 38, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair43 = new AdvancedModelRenderer(this);
		hair43.setRotationPoint(0.0F, 2.8653F, 2.5387F);
		hair42.addChild(hair43);


		cube_r43 = new AdvancedModelRenderer(this);
		cube_r43.setRotationPoint(0.0F, 0.6F, 2.1F);
		hair43.addChild(cube_r43);
		setRotationAngle(cube_r43, -0.2182F, 0.0F, 0.0F);
		cube_r43.cubeList.add(new ModelBox(cube_r43, 40, 54, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair44 = new AdvancedModelRenderer(this);
		hair44.setRotationPoint(0.0F, 1.0838F, 4.0267F);
		hair43.addChild(hair44);


		cube_r44 = new AdvancedModelRenderer(this);
		cube_r44.setRotationPoint(0.0F, 1.3F, 1.5F);
		hair44.addChild(cube_r44);
		setRotationAngle(cube_r44, -0.7418F, 0.0F, 0.0F);
		cube_r44.cubeList.add(new ModelBox(cube_r44, 50, 54, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair45 = new AdvancedModelRenderer(this);
		hair45.setRotationPoint(0.0F, 2.5642F, 2.8734F);
		hair44.addChild(hair45);


		cube_r45 = new AdvancedModelRenderer(this);
		cube_r45.setRotationPoint(0.0F, 0.0F, 2.1F);
		hair45.addChild(cube_r45);
		setRotationAngle(cube_r45, 0.0436F, 0.0F, 0.0F);
		cube_r45.cubeList.add(new ModelBox(cube_r45, 0, 56, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair46 = new AdvancedModelRenderer(this);
		hair46.setRotationPoint(0.0F, -0.0421F, 4.1102F);
		hair45.addChild(hair46);


		cube_r46 = new AdvancedModelRenderer(this);
		cube_r46.setRotationPoint(0.0F, 0.8F, 1.8F);
		hair46.addChild(cube_r46);
		setRotationAngle(cube_r46, -0.4363F, 0.0F, 0.0F);
		cube_r46.cubeList.add(new ModelBox(cube_r46, 10, 56, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair47 = new AdvancedModelRenderer(this);
		hair47.setRotationPoint(0.0F, 1.6124F, 3.5658F);
		hair46.addChild(hair47);


		cube_r47 = new AdvancedModelRenderer(this);
		cube_r47.setRotationPoint(0.0F, -0.4F, 2.0F);
		hair47.addChild(cube_r47);
		setRotationAngle(cube_r47, 0.2182F, 0.0F, 0.0F);
		cube_r47.cubeList.add(new ModelBox(cube_r47, 20, 58, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair48 = new AdvancedModelRenderer(this);
		hair48.setRotationPoint(0.0F, -0.7315F, 3.8595F);
		hair47.addChild(hair48);
		setRotationAngle(hair48, -0.5236F, 0.0F, 0.0F);


		cube_r48 = new AdvancedModelRenderer(this);
		cube_r48.setRotationPoint(0.0F, -0.567F, 1.9821F);
		hair48.addChild(cube_r48);
		setRotationAngle(cube_r48, 0.2182F, 0.0F, 0.0F);
		cube_r48.cubeList.add(new ModelBox(cube_r48, 58, 28, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair49 = new AdvancedModelRenderer(this);
		hair49.setRotationPoint(0.0F, -0.9314F, 3.8605F);
		hair48.addChild(hair49);


		cube_r49 = new AdvancedModelRenderer(this);
		cube_r49.setRotationPoint(0.0F, -0.5013F, 2.0267F);
		hair49.addChild(cube_r49);
		setRotationAngle(cube_r49, 0.2182F, 0.0F, 0.0F);
		cube_r49.cubeList.add(new ModelBox(cube_r49, 30, 58, -0.5F, 0.0F, -2.0F, 1, 0, 4, 0.0F, false));

		hair50 = new AdvancedModelRenderer(this);
		hair50.setRotationPoint(0.0F, -0.7584F, 3.9386F);
		hair49.addChild(hair50);
		setRotationAngle(hair50, -0.7854F, 0.0F, 0.0F);


		cube_r50 = new AdvancedModelRenderer(this);
		cube_r50.setRotationPoint(0.0F, -0.0855F, -0.0436F);
		hair50.addChild(cube_r50);
		setRotationAngle(cube_r50, 0.2182F, 0.0F, 0.0F);
		cube_r50.cubeList.add(new ModelBox(cube_r50, 58, 32, -0.5F, -0.0769F, -0.0368F, 1, 0, 4, 0.0F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		EntityRusalka rusalka = (EntityRusalka) entity;
		animate(rusalka, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityRusalka rusalka = (EntityRusalka) entity;
		animator.update(rusalka);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, rusalka);

		if (rusalka.getAnimation() == EntityRusalka.MELEE_ATTACK_ANIMATION) {
			animator.setAnimation(EntityRusalka.MELEE_ATTACK_ANIMATION);

			animator.startKeyframe(20);
			this.rotate(animator, left_arm, 90, -40, -90);
			animator.endKeyframe();
			animator.startKeyframe(5);
			this.rotate(animator, left_arm, -90, 2.5F, 90);
			animator.endKeyframe();
			animator.startKeyframe(15);
			this.rotate(animator, left_arm, 0, 0, 0);
			animator.endKeyframe();
		}

		if (rusalka.getAnimation() == EntityRusalka.HAIR_ATTACK_ANIMATION) {
			animator.setAnimation(EntityRusalka.HAIR_ATTACK_ANIMATION);

			animator.startKeyframe(10);
			this.rotate(animator, hair1, 0, -4, 0);
			this.rotate(animator, hair2, 30, 0, 0);
			this.rotate(animator, hair3, -30, 0, 0);
			this.rotate(animator, hair4, 30, 0, 0);
			this.rotate(animator, hair5, -60, 0, 0);
			animator.move(hair5, 0, -0.05F, -0.125F);
			this.rotate(animator, hair6, 30, 0, 0);
			this.rotate(animator, hair7, -30, 0, 0);
			this.rotate(animator, hair8, 27.5F, 0, 0);
			this.rotate(animator, hair10, 37.5F, 0, 0);

			this.rotate(animator, hair12, 30, 0, 0);
			this.rotate(animator, hair13, -30, 0, 0);
			this.rotate(animator, hair14, 30, 0, 0);
			this.rotate(animator, hair15, -50, 0, 0);
			this.rotate(animator, hair16, 30, 0, 0);
			this.rotate(animator, hair17, -42.5F, 0, 0);
			this.rotate(animator, hair18, 30, 0, 0);
			this.rotate(animator, hair19, -12.5F, 0, 0);
			animator.move(hair19, 0, -0.025F, 0.025F);
			this.rotate(animator, hair20, 40, 0, 0);

			this.rotate(animator, hair21, 0, 4, 0);
			this.rotate(animator, hair22, 30, 0, 0);
			this.rotate(animator, hair23, -40, 0, 0);
			this.rotate(animator, hair24, 27.5F, 0, 0);
			this.rotate(animator, hair25, -45, 0, 0);
			this.rotate(animator, hair26, 20, 0, 0);
			this.rotate(animator, hair27, -27.5F, 0, 0);
			this.rotate(animator, hair28, 30, 0, 0);
			this.rotate(animator, hair30, 32.5F, 0, 0);


			animator.startKeyframe(15);
			this.rotate(animator, model_core, 0, -360, 0);
			animator.endKeyframe();

			animator.startKeyframe(10);
			this.rotate(animator, hair1, 0, 0, 0);
			this.rotate(animator, hair2, 30, 0, 0);
			this.rotate(animator, hair3, 0, 0, 0);
			this.rotate(animator, hair4, 0, 0, 0);
			this.rotate(animator, hair5, 0, 0, 0);
			animator.move(hair5, 0, 0, 0);
			this.rotate(animator, hair6, 0, 0, 0);
			this.rotate(animator, hair7, 0, 0, 0);
			this.rotate(animator, hair8, 0, 0, 0);
			this.rotate(animator, hair10, 0, 0, 0);

			this.rotate(animator, hair12, 0, 0, 0);
			this.rotate(animator, hair13, 0, 0, 0);
			this.rotate(animator, hair14, 0, 0, 0);
			this.rotate(animator, hair15, 0, 0, 0);
			this.rotate(animator, hair16, 0, 0, 0);
			this.rotate(animator, hair17, 0, 0, 0);
			this.rotate(animator, hair18, 0, 0, 0);
			this.rotate(animator, hair19, 0, 0, 0);
			animator.move(hair19, 0, 0, 0);
			this.rotate(animator, hair20, 0, 0, 0);

			this.rotate(animator, hair21, 0, 0, 0);
			this.rotate(animator, hair22, 0, 0, 0);
			this.rotate(animator, hair23, 0, 0, 0);
			this.rotate(animator, hair24, 0, 0, 0);
			this.rotate(animator, hair25, 0, 0, 0);
			this.rotate(animator, hair26, 0, 0, 0);
			this.rotate(animator, hair27, 0, 0, 0);
			this.rotate(animator, hair28, 0, 0, 0);
			this.rotate(animator, hair30, 0, 0, 0);
			animator.endKeyframe();

		}

		if (rusalka.getAnimation() == EntityRusalka.WATER_ATTACK_ANIMATION) {
			animator.setAnimation(EntityRusalka.WATER_ATTACK_ANIMATION);

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, -145, 30, -30);
			animator.setStaticKeyframe(40);
			animator.endKeyframe();
			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 0);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		for (int i = 1; i <= 50; i++) {

			float frequency = 0.1F + (float) Math.random() * 0.000005F;
			float amplitude = 0.2F + (float) Math.random() * 0.000001F;
			float oscillation = (float) Math.sin(ageInTicks * frequency + i) * amplitude;

			switch (i) {
				case 1:  this.hair1.rotateAngleX = oscillation; break;
				case 2:  this.hair2.rotateAngleX = oscillation; break;
				case 3:  this.hair3.rotateAngleX = oscillation; break;
				case 4:  this.hair4.rotateAngleX = oscillation; break;
				case 5:  this.hair5.rotateAngleX = oscillation; break;
				case 6:  this.hair6.rotateAngleX = oscillation; break;
				case 7:  this.hair7.rotateAngleX = oscillation; break;
				case 8:  this.hair8.rotateAngleX = oscillation; break;
				case 9:  this.hair9.rotateAngleX = oscillation; break;
				case 10: this.hair10.rotateAngleX = oscillation; break;
				case 11: this.hair11.rotateAngleX = oscillation; break;
				case 12: this.hair12.rotateAngleX = oscillation; break;
				case 13: this.hair13.rotateAngleX = oscillation; break;
				case 14: this.hair14.rotateAngleX = oscillation; break;
				case 15: this.hair15.rotateAngleX = oscillation; break;
				case 16: this.hair16.rotateAngleX = oscillation; break;
				case 17: this.hair17.rotateAngleX = oscillation; break;
				case 18: this.hair18.rotateAngleX = oscillation; break;
				case 19: this.hair19.rotateAngleX = oscillation; break;
				case 20: this.hair20.rotateAngleX = oscillation; break;
				case 21: this.hair21.rotateAngleX = oscillation; break;
				case 22: this.hair22.rotateAngleX = oscillation; break;
				case 23: this.hair23.rotateAngleX = oscillation; break;
				case 24: this.hair24.rotateAngleX = oscillation; break;
				case 25: this.hair25.rotateAngleX = oscillation; break;
				case 26: this.hair26.rotateAngleX = oscillation; break;
				case 27: this.hair27.rotateAngleX = oscillation; break;
				case 28: this.hair28.rotateAngleX = oscillation; break;
				case 29: this.hair29.rotateAngleX = oscillation; break;
				case 30: this.hair30.rotateAngleX = oscillation; break;
				case 31: this.hair31.rotateAngleX = oscillation; break;
				case 32: this.hair32.rotateAngleX = oscillation; break;
				case 33: this.hair33.rotateAngleX = oscillation; break;
				case 34: this.hair34.rotateAngleX = oscillation; break;
				case 35: this.hair35.rotateAngleX = oscillation; break;
				case 36: this.hair36.rotateAngleX = oscillation; break;
				case 37: this.hair37.rotateAngleX = oscillation; break;
				case 38: this.hair38.rotateAngleX = oscillation; break;
				case 39: this.hair39.rotateAngleX = oscillation; break;
				case 40: this.hair40.rotateAngleX = oscillation; break;
				case 41: this.hair41.rotateAngleX = oscillation; break;
				case 42: this.hair42.rotateAngleX = oscillation; break;
				case 43: this.hair43.rotateAngleX = oscillation; break;
				case 44: this.hair44.rotateAngleX = oscillation; break;
				case 45: this.hair45.rotateAngleX = oscillation; break;
				case 46: this.hair46.rotateAngleX = oscillation; break;
				case 47: this.hair47.rotateAngleX = oscillation; break;
				case 48: this.hair48.rotateAngleX = oscillation; break;
				case 49: this.hair49.rotateAngleX = oscillation; break;
				case 50: this.hair50.rotateAngleX = oscillation; break;
			}
		}

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;

		this.right_arm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.left_arm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.right_arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.left_arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}
}