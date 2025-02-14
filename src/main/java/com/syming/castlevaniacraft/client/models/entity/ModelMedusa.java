package com.syming.castlevaniacraft.client.models.entity;

// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.syming.castlevaniacraft.entity.mobs.boss.EntityMedusa;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelMedusa extends CCModel {
	private final AdvancedModelRenderer model_core;
	private final AdvancedModelRenderer head;
	private final AdvancedModelRenderer hair1;
	private final AdvancedModelRenderer hair_root1;
	private final AdvancedModelRenderer cube_r1;
	private final AdvancedModelRenderer hair_tip1;
	private final AdvancedModelRenderer hair2;
	private final AdvancedModelRenderer hair_root2;
	private final AdvancedModelRenderer cube_r2;
	private final AdvancedModelRenderer hair_tip2;
	private final AdvancedModelRenderer hair3;
	private final AdvancedModelRenderer hair_root3;
	private final AdvancedModelRenderer cube_r3;
	private final AdvancedModelRenderer hair_tip3;
	private final AdvancedModelRenderer hair4;
	private final AdvancedModelRenderer hair_root4;
	private final AdvancedModelRenderer cube_r4;
	private final AdvancedModelRenderer hair_tip4;
	private final AdvancedModelRenderer hair5;
	private final AdvancedModelRenderer hair_root5;
	private final AdvancedModelRenderer cube_r5;
	private final AdvancedModelRenderer hair_tip5;
	private final AdvancedModelRenderer hair6;
	private final AdvancedModelRenderer hair_root6;
	private final AdvancedModelRenderer cube_r6;
	private final AdvancedModelRenderer hair_tip6;
	private final AdvancedModelRenderer hair7;
	private final AdvancedModelRenderer hair_root7;
	private final AdvancedModelRenderer cube_r7;
	private final AdvancedModelRenderer hair_tip7;
	private final AdvancedModelRenderer hair8;
	private final AdvancedModelRenderer hair_root8;
	private final AdvancedModelRenderer cube_r8;
	private final AdvancedModelRenderer hair_tip8;
	private final AdvancedModelRenderer hair9;
	private final AdvancedModelRenderer hair_root9;
	private final AdvancedModelRenderer cube_r9;
	private final AdvancedModelRenderer hair_tip9;
	private final AdvancedModelRenderer hair10;
	private final AdvancedModelRenderer hair_root10;
	private final AdvancedModelRenderer cube_r10;
	private final AdvancedModelRenderer hair_tip10;
	private final AdvancedModelRenderer hair11;
	private final AdvancedModelRenderer hair_root11;
	private final AdvancedModelRenderer cube_r11;
	private final AdvancedModelRenderer hair_tip11;
	private final AdvancedModelRenderer hair12;
	private final AdvancedModelRenderer hair_root12;
	private final AdvancedModelRenderer cube_r12;
	private final AdvancedModelRenderer hair_tip12;
	private final AdvancedModelRenderer hair13;
	private final AdvancedModelRenderer hair_root13;
	private final AdvancedModelRenderer cube_r13;
	private final AdvancedModelRenderer hair_tip13;
	private final AdvancedModelRenderer hair14;
	private final AdvancedModelRenderer hair_root14;
	private final AdvancedModelRenderer cube_r14;
	private final AdvancedModelRenderer hair_tip14;
	private final AdvancedModelRenderer hair15;
	private final AdvancedModelRenderer hair_root15;
	private final AdvancedModelRenderer cube_r15;
	private final AdvancedModelRenderer hair_tip15;
	private final AdvancedModelRenderer hair16;
	private final AdvancedModelRenderer hair_root16;
	private final AdvancedModelRenderer cube_r16;
	private final AdvancedModelRenderer hair_tip16;
	private final AdvancedModelRenderer hair17;
	private final AdvancedModelRenderer hair_root17;
	private final AdvancedModelRenderer cube_r17;
	private final AdvancedModelRenderer hair_tip17;
	private final AdvancedModelRenderer hair18;
	private final AdvancedModelRenderer hair_root18;
	private final AdvancedModelRenderer cube_r18;
	private final AdvancedModelRenderer hair_tip18;
	private final AdvancedModelRenderer hair19;
	private final AdvancedModelRenderer hair_root19;
	private final AdvancedModelRenderer cube_r19;
	private final AdvancedModelRenderer hair_tip19;
	private final AdvancedModelRenderer hair20;
	private final AdvancedModelRenderer hair_root20;
	private final AdvancedModelRenderer cube_r20;
	private final AdvancedModelRenderer hair_tip20;
	private final AdvancedModelRenderer hair21;
	private final AdvancedModelRenderer hair_root21;
	private final AdvancedModelRenderer cube_r21;
	private final AdvancedModelRenderer hair_tip21;
	private final AdvancedModelRenderer hair22;
	private final AdvancedModelRenderer hair_root22;
	private final AdvancedModelRenderer cube_r22;
	private final AdvancedModelRenderer hair_tip22;
	private final AdvancedModelRenderer hair23;
	private final AdvancedModelRenderer hair_root23;
	private final AdvancedModelRenderer cube_r23;
	private final AdvancedModelRenderer hair_tip23;
	private final AdvancedModelRenderer hair24;
	private final AdvancedModelRenderer hair_root24;
	private final AdvancedModelRenderer cube_r24;
	private final AdvancedModelRenderer hair_tip24;
	private final AdvancedModelRenderer hair25;
	private final AdvancedModelRenderer hair_root25;
	private final AdvancedModelRenderer cube_r25;
	private final AdvancedModelRenderer hair_tip25;
	private final AdvancedModelRenderer hair26;
	private final AdvancedModelRenderer hair_root26;
	private final AdvancedModelRenderer cube_r26;
	private final AdvancedModelRenderer hair_tip26;
	private final AdvancedModelRenderer hair27;
	private final AdvancedModelRenderer hair_root27;
	private final AdvancedModelRenderer cube_r27;
	private final AdvancedModelRenderer hair_tip27;
	private final AdvancedModelRenderer hair28;
	private final AdvancedModelRenderer hair_root28;
	private final AdvancedModelRenderer cube_r28;
	private final AdvancedModelRenderer hair_tip28;
	private final AdvancedModelRenderer body;
	private final AdvancedModelRenderer upper_body;
	private final AdvancedModelRenderer lower_body;
	private final AdvancedModelRenderer right_arm;
	private final AdvancedModelRenderer left_arm;
	private final AdvancedModelRenderer tail;
	private final AdvancedModelRenderer tail_root;
	private final AdvancedModelRenderer tail1;
	private final AdvancedModelRenderer tail2;
	private final AdvancedModelRenderer tail3;

	private ModelAnimator animator;

	public ModelMedusa() {
		textureWidth = 128;
		textureHeight = 128;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 7.0F);


		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -25.4F, -3.8F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 32, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

		hair1 = new AdvancedModelRenderer(this);
		hair1.setRotationPoint(0.0F, 25.4F, 3.8F);
		head.addChild(hair1);


		hair_root1 = new AdvancedModelRenderer(this);
		hair_root1.setRotationPoint(-3.575F, -29.4F, -7.375F);
		hair1.addChild(hair_root1);
		setRotationAngle(hair_root1, 0.7418F, 0.7854F, 0.0F);


		cube_r1 = new AdvancedModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -0.6553F, 0.025F);
		hair_root1.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 32, 23, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip1 = new AdvancedModelRenderer(this);
		hair_tip1.setRotationPoint(-0.0331F, -2.0868F, 0.0419F);
		hair_root1.addChild(hair_tip1);
		setRotationAngle(hair_tip1, 1.2847F, -0.0002F, -0.0145F);
		hair_tip1.cubeList.add(new ModelBox(hair_tip1, 28, 32, -0.4663F, -3.0014F, 0.0358F, 1, 3, 0, 0.0F, false));

		hair2 = new AdvancedModelRenderer(this);
		hair2.setRotationPoint(0.0F, 8.0F, -0.5F);
		head.addChild(hair2);


		hair_root2 = new AdvancedModelRenderer(this);
		hair_root2.setRotationPoint(-3.8814F, -11.9378F, -2.175F);
		hair2.addChild(hair_root2);
		setRotationAngle(hair_root2, 0.0F, 0.0F, -0.7418F);


		cube_r2 = new AdvancedModelRenderer(this);
		cube_r2.setRotationPoint(-0.4818F, -1.204F, -0.5F);
		hair_root2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 14, 6, 0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip2 = new AdvancedModelRenderer(this);
		hair_tip2.setRotationPoint(-0.0268F, -2.7118F, 0.0F);
		hair_root2.addChild(hair_tip2);
		setRotationAngle(hair_tip2, 0.0F, 0.0F, -0.3054F);
		hair_tip2.cubeList.add(new ModelBox(hair_tip2, 0, 0, 0.0405F, -2.954F, -0.5F, 0, 3, 1, 0.0F, false));

		hair3 = new AdvancedModelRenderer(this);
		hair3.setRotationPoint(0.0F, 8.0F, 0.6F);
		head.addChild(hair3);


		hair_root3 = new AdvancedModelRenderer(this);
		hair_root3.setRotationPoint(-3.8814F, -11.9378F, -2.175F);
		hair3.addChild(hair_root3);
		setRotationAngle(hair_root3, 0.0F, 0.0F, -0.7418F);


		cube_r3 = new AdvancedModelRenderer(this);
		cube_r3.setRotationPoint(-0.4818F, -1.204F, -0.5F);
		hair_root3.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 28, 28, 0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip3 = new AdvancedModelRenderer(this);
		hair_tip3.setRotationPoint(-0.0018F, -2.7118F, 0.0F);
		hair_root3.addChild(hair_tip3);
		setRotationAngle(hair_tip3, 0.0F, 0.0F, -1.1781F);
		hair_tip3.cubeList.add(new ModelBox(hair_tip3, 28, 22, 0.01F, -2.9555F, -0.5F, 0, 3, 1, 0.0F, false));

		hair4 = new AdvancedModelRenderer(this);
		hair4.setRotationPoint(0.0F, 8.0F, 1.7F);
		head.addChild(hair4);


		hair_root4 = new AdvancedModelRenderer(this);
		hair_root4.setRotationPoint(-3.8814F, -11.9378F, -2.175F);
		hair4.addChild(hair_root4);
		setRotationAngle(hair_root4, 0.0F, 0.0F, -0.7418F);


		cube_r4 = new AdvancedModelRenderer(this);
		cube_r4.setRotationPoint(-0.4818F, -1.204F, -0.5F);
		hair_root4.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 14, 22, 0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip4 = new AdvancedModelRenderer(this);
		hair_tip4.setRotationPoint(-0.0268F, -2.6618F, 0.0F);
		hair_root4.addChild(hair_tip4);
		setRotationAngle(hair_tip4, 0.0F, 0.0F, -0.3054F);
		hair_tip4.cubeList.add(new ModelBox(hair_tip4, 2, 19, 0.0405F, -2.979F, -0.5F, 0, 3, 1, 0.0F, false));

		hair5 = new AdvancedModelRenderer(this);
		hair5.setRotationPoint(0.0F, 8.0F, 2.8F);
		head.addChild(hair5);


		hair_root5 = new AdvancedModelRenderer(this);
		hair_root5.setRotationPoint(-3.8814F, -11.9378F, -2.175F);
		hair5.addChild(hair_root5);
		setRotationAngle(hair_root5, 0.0F, 0.0F, -0.7418F);


		cube_r5 = new AdvancedModelRenderer(this);
		cube_r5.setRotationPoint(-0.4818F, -1.204F, -0.5F);
		hair_root5.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 12, 0, 0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip5 = new AdvancedModelRenderer(this);
		hair_tip5.setRotationPoint(-0.0018F, -2.7118F, 0.0F);
		hair_root5.addChild(hair_tip5);
		setRotationAngle(hair_tip5, 0.0F, 0.0F, -1.1781F);
		hair_tip5.cubeList.add(new ModelBox(hair_tip5, 14, 28, 0.01F, -2.9555F, -0.5F, 0, 3, 1, 0.0F, false));

		hair6 = new AdvancedModelRenderer(this);
		hair6.setRotationPoint(0.0F, 8.0F, 3.9F);
		head.addChild(hair6);


		hair_root6 = new AdvancedModelRenderer(this);
		hair_root6.setRotationPoint(-3.8814F, -11.9378F, -2.175F);
		hair6.addChild(hair_root6);
		setRotationAngle(hair_root6, 0.0F, 0.0F, -0.7418F);


		cube_r6 = new AdvancedModelRenderer(this);
		cube_r6.setRotationPoint(0.0182F, -2.704F, 0.0F);
		hair_root6.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 14, 0, 0.0F, 0.0F, -0.5F, 0, 3, 1, 0.0F, false));

		hair_tip6 = new AdvancedModelRenderer(this);
		hair_tip6.setRotationPoint(-0.0268F, -2.7868F, 0.0F);
		hair_root6.addChild(hair_tip6);
		setRotationAngle(hair_tip6, 0.0F, 0.0F, -0.3054F);
		hair_tip6.cubeList.add(new ModelBox(hair_tip6, 14, 19, 0.0105F, -2.8837F, -0.5F, 0, 3, 1, 0.0F, false));

		hair7 = new AdvancedModelRenderer(this);
		hair7.setRotationPoint(0.0F, 8.0F, 5.0F);
		head.addChild(hair7);


		hair_root7 = new AdvancedModelRenderer(this);
		hair_root7.setRotationPoint(-3.8814F, -11.9378F, -2.175F);
		hair7.addChild(hair_root7);
		setRotationAngle(hair_root7, 0.0F, 0.0F, -0.7418F);


		cube_r7 = new AdvancedModelRenderer(this);
		cube_r7.setRotationPoint(0.0182F, -2.704F, 0.0F);
		hair_root7.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 12, 19, 0.0F, 0.0F, -0.5F, 0, 3, 1, 0.0F, false));

		hair_tip7 = new AdvancedModelRenderer(this);
		hair_tip7.setRotationPoint(-0.0018F, -2.7118F, 0.0F);
		hair_root7.addChild(hair_tip7);
		setRotationAngle(hair_tip7, 0.0F, 0.0F, -1.1781F);
		hair_tip7.cubeList.add(new ModelBox(hair_tip7, 30, 25, 0.01F, -2.9555F, -0.5F, 0, 3, 1, 0.0F, false));

		hair8 = new AdvancedModelRenderer(this);
		hair8.setRotationPoint(-0.025F, 8.0F, 5.55F);
		head.addChild(hair8);


		hair_root8 = new AdvancedModelRenderer(this);
		hair_root8.setRotationPoint(-3.5666F, -12.0338F, -1.8187F);
		hair8.addChild(hair_root8);
		setRotationAngle(hair_root8, 0.7418F, 2.3562F, 0.0F);


		cube_r8 = new AdvancedModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, -0.6553F, 0.025F);
		hair_root8.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 32, 3, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip8 = new AdvancedModelRenderer(this);
		hair_tip8.setRotationPoint(-0.0326F, -2.1125F, 0.0326F);
		hair_root8.addChild(hair_tip8);
		setRotationAngle(hair_tip8, -2.9566F, 0.0018F, 3.0981F);
		hair_tip8.cubeList.add(new ModelBox(hair_tip8, 32, 20, -0.5329F, -2.9933F, 0.0065F, 1, 3, 0, 0.0F, false));

		hair9 = new AdvancedModelRenderer(this);
		hair9.setRotationPoint(1.0F, 8.0F, 5.5F);
		head.addChild(hair9);


		hair_root9 = new AdvancedModelRenderer(this);
		hair_root9.setRotationPoint(-3.675F, -12.0F, -1.425F);
		hair9.addChild(hair_root9);
		setRotationAngle(hair_root9, 0.7418F, 3.1416F, 0.0F);


		cube_r9 = new AdvancedModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, -0.6389F, 0.0429F);
		hair_root9.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 34, 23, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip9 = new AdvancedModelRenderer(this);
		hair_tip9.setRotationPoint(0.0F, -2.1368F, 0.0268F);
		hair_root9.addChild(hair_tip9);
		setRotationAngle(hair_tip9, -1.9635F, 0.0F, 3.1416F);
		hair_tip9.cubeList.add(new ModelBox(hair_tip9, 38, 26, -0.5F, -2.9839F, -0.0192F, 1, 3, 0, 0.0F, false));

		hair10 = new AdvancedModelRenderer(this);
		hair10.setRotationPoint(2.1F, 8.0F, 5.5F);
		head.addChild(hair10);


		hair_root10 = new AdvancedModelRenderer(this);
		hair_root10.setRotationPoint(-3.675F, -11.9F, -1.525F);
		hair10.addChild(hair_root10);
		setRotationAngle(hair_root10, 0.7418F, 3.1416F, 0.0F);


		cube_r10 = new AdvancedModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, -0.7802F, 0.0367F);
		hair_root10.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.0F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 34, 20, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip10 = new AdvancedModelRenderer(this);
		hair_tip10.setRotationPoint(0.0F, -2.2868F, 0.0268F);
		hair_root10.addChild(hair_tip10);
		setRotationAngle(hair_tip10, -2.8362F, 0.0F, 3.1416F);
		hair_tip10.cubeList.add(new ModelBox(hair_tip10, 38, 3, -0.5F, -2.9731F, -0.02F, 1, 3, 0, 0.0F, false));

		hair11 = new AdvancedModelRenderer(this);
		hair11.setRotationPoint(3.2F, 8.0F, 5.5F);
		head.addChild(hair11);


		hair_root11 = new AdvancedModelRenderer(this);
		hair_root11.setRotationPoint(-3.675F, -11.9F, -1.525F);
		hair11.addChild(hair_root11);
		setRotationAngle(hair_root11, 0.7418F, 3.1416F, 0.0F);


		cube_r11 = new AdvancedModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, -0.7802F, 0.0367F);
		hair_root11.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 34, 0, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip11 = new AdvancedModelRenderer(this);
		hair_tip11.setRotationPoint(0.025F, -2.3118F, 0.0268F);
		hair_root11.addChild(hair_tip11);
		setRotationAngle(hair_tip11, -1.9635F, 0.0F, 3.1416F);
		hair_tip11.cubeList.add(new ModelBox(hair_tip11, 38, 29, -0.5F, -2.998F, -0.0334F, 1, 3, 0, 0.0F, false));

		hair12 = new AdvancedModelRenderer(this);
		hair12.setRotationPoint(-2.1F, 25.4F, 3.8F);
		head.addChild(hair12);


		hair_root12 = new AdvancedModelRenderer(this);
		hair_root12.setRotationPoint(2.675F, -29.3F, 0.175F);
		hair12.addChild(hair_root12);
		setRotationAngle(hair_root12, 0.7418F, 3.1416F, 0.0F);


		cube_r12 = new AdvancedModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, -0.7802F, 0.0367F);
		hair_root12.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 32, 32, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip12 = new AdvancedModelRenderer(this);
		hair_tip12.setRotationPoint(0.0F, -2.2618F, 0.0268F);
		hair_root12.addChild(hair_tip12);
		setRotationAngle(hair_tip12, -2.8362F, 0.0F, 3.1416F);
		hair_tip12.cubeList.add(new ModelBox(hair_tip12, 38, 23, -0.5F, -2.9731F, -0.02F, 1, 3, 0, 0.0F, false));

		hair13 = new AdvancedModelRenderer(this);
		hair13.setRotationPoint(-1.05F, 25.4F, 3.8F);
		head.addChild(hair13);


		hair_root13 = new AdvancedModelRenderer(this);
		hair_root13.setRotationPoint(2.675F, -29.3F, 0.175F);
		hair13.addChild(hair_root13);
		setRotationAngle(hair_root13, 0.7418F, 3.1416F, 0.0F);


		cube_r13 = new AdvancedModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, -0.7802F, 0.0367F);
		hair_root13.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, 0.0F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 34, 3, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip13 = new AdvancedModelRenderer(this);
		hair_tip13.setRotationPoint(0.0F, -2.3118F, 0.0018F);
		hair_root13.addChild(hair_tip13);
		setRotationAngle(hair_tip13, -1.9635F, 0.0F, 3.1416F);
		hair_tip13.cubeList.add(new ModelBox(hair_tip13, 38, 32, -0.5F, -2.998F, -0.0334F, 1, 3, 0, 0.0F, false));

		hair14 = new AdvancedModelRenderer(this);
		hair14.setRotationPoint(0.0F, 25.4F, 3.8F);
		head.addChild(hair14);


		hair_root14 = new AdvancedModelRenderer(this);
		hair_root14.setRotationPoint(2.675F, -29.3F, 0.175F);
		hair14.addChild(hair_root14);
		setRotationAngle(hair_root14, 0.7418F, 3.1416F, 0.0F);


		cube_r14 = new AdvancedModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, -0.7802F, 0.0367F);
		hair_root14.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, 0.0F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 34, 26, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip14 = new AdvancedModelRenderer(this);
		hair_tip14.setRotationPoint(0.0F, -2.2868F, 0.0268F);
		hair_root14.addChild(hair_tip14);
		setRotationAngle(hair_tip14, -2.8362F, 0.0F, 3.1416F);
		hair_tip14.cubeList.add(new ModelBox(hair_tip14, 38, 20, -0.5F, -2.9731F, -0.02F, 1, 3, 0, 0.0F, false));

		hair15 = new AdvancedModelRenderer(this);
		hair15.setRotationPoint(0.025F, 8.0F, 5.55F);
		head.addChild(hair15);


		hair_root15 = new AdvancedModelRenderer(this);
		hair_root15.setRotationPoint(3.575F, -12.0F, -1.875F);
		hair15.addChild(hair_root15);
		setRotationAngle(hair_root15, 0.7418F, -2.3562F, 0.0F);


		cube_r15 = new AdvancedModelRenderer(this);
		cube_r15.setRotationPoint(0.0457F, -0.703F, 0.0229F);
		hair_root15.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, 0.0F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 14, 32, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip15 = new AdvancedModelRenderer(this);
		hair_tip15.setRotationPoint(0.0242F, -2.1963F, -0.0361F);
		hair_root15.addChild(hair_tip15);
		setRotationAngle(hair_tip15, -2.1185F, 0.0046F, -3.1407F);
		hair_tip15.cubeList.add(new ModelBox(hair_tip15, 30, 29, -0.5217F, -3.0082F, -0.0571F, 1, 3, 0, 0.0F, false));

		hair16 = new AdvancedModelRenderer(this);
		hair16.setRotationPoint(0.0F, 8.0F, 5.0F);
		head.addChild(hair16);


		hair_root16 = new AdvancedModelRenderer(this);
		hair_root16.setRotationPoint(3.9814F, -11.9378F, -2.175F);
		hair16.addChild(hair_root16);
		setRotationAngle(hair_root16, 0.0F, 0.0F, 0.7418F);


		cube_r16 = new AdvancedModelRenderer(this);
		cube_r16.setRotationPoint(0.4081F, -1.1365F, -0.5F);
		hair_root16.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, 0.0F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 10, 0, -0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip16 = new AdvancedModelRenderer(this);
		hair_tip16.setRotationPoint(-0.0732F, -2.5868F, 0.0F);
		hair_root16.addChild(hair_tip16);
		setRotationAngle(hair_tip16, 0.0F, 0.0F, 0.3054F);
		hair_tip16.cubeList.add(new ModelBox(hair_tip16, 14, 12, -0.0252F, -3.0176F, -0.5F, 0, 3, 1, 0.0F, false));

		hair17 = new AdvancedModelRenderer(this);
		hair17.setRotationPoint(0.0F, 8.0F, 3.9F);
		head.addChild(hair17);


		hair_root17 = new AdvancedModelRenderer(this);
		hair_root17.setRotationPoint(3.9814F, -12.0378F, -2.175F);
		hair17.addChild(hair_root17);
		setRotationAngle(hair_root17, 0.0F, 0.0F, 0.7418F);


		cube_r17 = new AdvancedModelRenderer(this);
		cube_r17.setRotationPoint(0.4756F, -1.0627F, -0.5F);
		hair_root17.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, 0.0F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 0, 19, -0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip17 = new AdvancedModelRenderer(this);
		hair_tip17.setRotationPoint(0.0018F, -2.4868F, 0.0F);
		hair_root17.addChild(hair_tip17);
		setRotationAngle(hair_tip17, 0.0F, 0.0F, 1.1781F);
		hair_tip17.cubeList.add(new ModelBox(hair_tip17, 30, 19, -0.066F, -2.9713F, -0.5F, 0, 3, 1, 0.0F, false));

		hair18 = new AdvancedModelRenderer(this);
		hair18.setRotationPoint(0.0F, 8.0F, 2.8F);
		head.addChild(hair18);


		hair_root18 = new AdvancedModelRenderer(this);
		hair_root18.setRotationPoint(3.9784F, -12.0445F, -2.175F);
		hair18.addChild(hair_root18);
		setRotationAngle(hair_root18, 0.0F, 0.0F, 0.7418F);


		cube_r18 = new AdvancedModelRenderer(this);
		cube_r18.setRotationPoint(-0.0244F, -1.0627F, 0.0F);
		hair_root18.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, 0.0F);
		cube_r18.cubeList.add(new ModelBox(cube_r18, 14, 3, 0.0F, -1.5F, -0.5F, 0, 3, 1, 0.0F, false));

		hair_tip18 = new AdvancedModelRenderer(this);
		hair_tip18.setRotationPoint(0.0297F, -2.5801F, 0.0F);
		hair_root18.addChild(hair_tip18);
		setRotationAngle(hair_tip18, 0.0F, 0.0F, 0.3054F);
		hair_tip18.cubeList.add(new ModelBox(hair_tip18, 14, 25, -0.0463F, -2.9668F, -0.5F, 0, 3, 1, 0.0F, false));

		hair19 = new AdvancedModelRenderer(this);
		hair19.setRotationPoint(0.0F, 8.0F, 1.7F);
		head.addChild(hair19);


		hair_root19 = new AdvancedModelRenderer(this);
		hair_root19.setRotationPoint(3.9814F, -12.0378F, -2.175F);
		hair19.addChild(hair_root19);
		setRotationAngle(hair_root19, 0.0F, 0.0F, 0.7418F);


		cube_r19 = new AdvancedModelRenderer(this);
		cube_r19.setRotationPoint(0.0756F, -2.5627F, -0.5F);
		hair_root19.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.0F, 0.0F);
		cube_r19.cubeList.add(new ModelBox(cube_r19, 14, 9, -0.1F, 0.0F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip19 = new AdvancedModelRenderer(this);
		hair_tip19.setRotationPoint(0.0018F, -2.4868F, 0.0F);
		hair_root19.addChild(hair_tip19);
		setRotationAngle(hair_tip19, 0.0F, 0.0F, 1.1781F);
		hair_tip19.cubeList.add(new ModelBox(hair_tip19, 30, 22, -0.0656F, -2.9722F, -0.5F, 0, 3, 1, 0.0F, false));

		hair20 = new AdvancedModelRenderer(this);
		hair20.setRotationPoint(0.0F, 8.0F, 0.6F);
		head.addChild(hair20);


		hair_root20 = new AdvancedModelRenderer(this);
		hair_root20.setRotationPoint(3.9814F, -12.0378F, -2.175F);
		hair20.addChild(hair_root20);
		setRotationAngle(hair_root20, 0.0F, 0.0F, 0.7418F);


		cube_r20 = new AdvancedModelRenderer(this);
		cube_r20.setRotationPoint(0.4756F, -1.0627F, -0.5F);
		hair_root20.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, 0.0F);
		cube_r20.cubeList.add(new ModelBox(cube_r20, 2, 0, -0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip20 = new AdvancedModelRenderer(this);
		hair_tip20.setRotationPoint(0.0268F, -2.4868F, 0.0F);
		hair_root20.addChild(hair_tip20);
		setRotationAngle(hair_tip20, 0.0F, 0.0F, 0.3054F);
		hair_tip20.cubeList.add(new ModelBox(hair_tip20, 28, 19, -0.064F, -3.0329F, -0.5F, 0, 3, 1, 0.0F, false));

		hair21 = new AdvancedModelRenderer(this);
		hair21.setRotationPoint(0.0F, 8.0F, -0.5F);
		head.addChild(hair21);


		hair_root21 = new AdvancedModelRenderer(this);
		hair_root21.setRotationPoint(3.9814F, -12.0378F, -2.175F);
		hair21.addChild(hair_root21);
		setRotationAngle(hair_root21, 0.0F, 0.0F, 0.7418F);


		cube_r21 = new AdvancedModelRenderer(this);
		cube_r21.setRotationPoint(0.4756F, -1.0627F, -0.5F);
		hair_root21.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 0.0F, 0.0F);
		cube_r21.cubeList.add(new ModelBox(cube_r21, 10, 19, -0.5F, -1.5F, 0.0F, 0, 3, 1, 0.0F, false));

		hair_tip21 = new AdvancedModelRenderer(this);
		hair_tip21.setRotationPoint(0.0018F, -2.4868F, 0.0F);
		hair_root21.addChild(hair_tip21);
		setRotationAngle(hair_tip21, 0.0F, 0.0F, 1.1781F);
		hair_tip21.cubeList.add(new ModelBox(hair_tip21, 28, 25, -0.066F, -2.9713F, -0.5F, 0, 3, 1, 0.0F, false));

		hair22 = new AdvancedModelRenderer(this);
		hair22.setRotationPoint(0.0F, 8.0F, -1.5F);
		head.addChild(hair22);


		hair_root22 = new AdvancedModelRenderer(this);
		hair_root22.setRotationPoint(3.675F, -12.1F, -2.075F);
		hair22.addChild(hair_root22);
		setRotationAngle(hair_root22, 0.7418F, -0.7854F, 0.0F);


		cube_r22 = new AdvancedModelRenderer(this);
		cube_r22.setRotationPoint(-0.0707F, -0.5338F, 0.0096F);
		hair_root22.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.0F);
		cube_r22.cubeList.add(new ModelBox(cube_r22, 32, 26, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip22 = new AdvancedModelRenderer(this);
		hair_tip22.setRotationPoint(-0.0669F, -2.0868F, -0.0331F);
		hair_root22.addChild(hair_tip22);
		setRotationAngle(hair_tip22, 0.2342F, 0.0123F, -0.0185F);
		hair_tip22.cubeList.add(new ModelBox(hair_tip22, 32, 0, -0.506F, -2.9036F, 0.0272F, 1, 3, 0, 0.0F, false));

		hair23 = new AdvancedModelRenderer(this);
		hair23.setRotationPoint(-1.0F, 8.0F, -1.5F);
		head.addChild(hair23);


		hair_root23 = new AdvancedModelRenderer(this);
		hair_root23.setRotationPoint(3.675F, -12.0F, -2.475F);
		hair23.addChild(hair_root23);
		setRotationAngle(hair_root23, 0.7418F, 0.0F, 0.0F);


		cube_r23 = new AdvancedModelRenderer(this);
		cube_r23.setRotationPoint(0.0F, -0.6389F, 0.0429F);
		hair_root23.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 0.0F, 0.0F);
		cube_r23.cubeList.add(new ModelBox(cube_r23, 30, 32, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip23 = new AdvancedModelRenderer(this);
		hair_tip23.setRotationPoint(0.0F, -2.0868F, -0.0018F);
		hair_root23.addChild(hair_tip23);
		setRotationAngle(hair_tip23, 1.1781F, 0.0F, 0.0F);
		hair_tip23.cubeList.add(new ModelBox(hair_tip23, 36, 32, -0.5F, -2.9451F, 0.051F, 1, 3, 0, 0.0F, false));

		hair24 = new AdvancedModelRenderer(this);
		hair24.setRotationPoint(-2.1F, 8.0F, -1.5F);
		head.addChild(hair24);


		hair_root24 = new AdvancedModelRenderer(this);
		hair_root24.setRotationPoint(3.675F, -12.0F, -2.475F);
		hair24.addChild(hair_root24);
		setRotationAngle(hair_root24, 0.7418F, 0.0F, 0.0F);


		cube_r24 = new AdvancedModelRenderer(this);
		cube_r24.setRotationPoint(0.0F, -0.6389F, 0.0429F);
		hair_root24.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, 0.0F);
		cube_r24.cubeList.add(new ModelBox(cube_r24, 34, 32, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip24 = new AdvancedModelRenderer(this);
		hair_tip24.setRotationPoint(0.0F, -2.0868F, -0.0268F);
		hair_root24.addChild(hair_tip24);
		setRotationAngle(hair_tip24, 0.3054F, 0.0F, 0.0F);
		hair_tip24.cubeList.add(new ModelBox(hair_tip24, 36, 26, -0.5F, -3.0045F, 0.0745F, 1, 3, 0, 0.0F, false));

		hair25 = new AdvancedModelRenderer(this);
		hair25.setRotationPoint(-3.15F, 8.0F, -1.5F);
		head.addChild(hair25);


		hair_root25 = new AdvancedModelRenderer(this);
		hair_root25.setRotationPoint(3.675F, -12.0F, -2.475F);
		hair25.addChild(hair_root25);
		setRotationAngle(hair_root25, 0.7418F, 0.0F, 0.0F);


		cube_r25 = new AdvancedModelRenderer(this);
		cube_r25.setRotationPoint(0.0F, -0.6389F, 0.0429F);
		hair_root25.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, 0.0F, 0.0F);
		cube_r25.cubeList.add(new ModelBox(cube_r25, 34, 29, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip25 = new AdvancedModelRenderer(this);
		hair_tip25.setRotationPoint(0.0F, -2.0868F, -0.0018F);
		hair_root25.addChild(hair_tip25);
		setRotationAngle(hair_tip25, 1.1781F, 0.0F, 0.0F);
		hair_tip25.cubeList.add(new ModelBox(hair_tip25, 36, 20, -0.5F, -2.946F, 0.0506F, 1, 3, 0, 0.0F, false));

		hair26 = new AdvancedModelRenderer(this);
		hair26.setRotationPoint(3.15F, 8.0F, -1.5F);
		head.addChild(hair26);


		hair_root26 = new AdvancedModelRenderer(this);
		hair_root26.setRotationPoint(-3.675F, -12.0F, -2.475F);
		hair26.addChild(hair_root26);
		setRotationAngle(hair_root26, 0.7418F, 0.0F, 0.0F);


		cube_r26 = new AdvancedModelRenderer(this);
		cube_r26.setRotationPoint(0.0F, -0.6389F, 0.0429F);
		hair_root26.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0F, 0.0F, 0.0F);
		cube_r26.cubeList.add(new ModelBox(cube_r26, 32, 29, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip26 = new AdvancedModelRenderer(this);
		hair_tip26.setRotationPoint(0.0F, -2.1868F, -0.0268F);
		hair_root26.addChild(hair_tip26);
		setRotationAngle(hair_tip26, 0.3054F, 0.0F, 0.0F);
		hair_tip26.cubeList.add(new ModelBox(hair_tip26, 36, 23, -0.5F, -2.9092F, 0.0444F, 1, 3, 0, 0.0F, false));

		hair27 = new AdvancedModelRenderer(this);
		hair27.setRotationPoint(2.1F, 8.0F, -1.5F);
		head.addChild(hair27);


		hair_root27 = new AdvancedModelRenderer(this);
		hair_root27.setRotationPoint(-3.675F, -12.0F, -2.475F);
		hair27.addChild(hair_root27);
		setRotationAngle(hair_root27, 0.7418F, 0.0F, 0.0F);


		cube_r27 = new AdvancedModelRenderer(this);
		cube_r27.setRotationPoint(0.0F, -0.6389F, 0.0429F);
		hair_root27.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0F, 0.0F, 0.0F);
		cube_r27.cubeList.add(new ModelBox(cube_r27, 36, 3, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip27 = new AdvancedModelRenderer(this);
		hair_tip27.setRotationPoint(0.0F, -2.0868F, -0.0018F);
		hair_root27.addChild(hair_tip27);
		setRotationAngle(hair_tip27, 1.1781F, 0.0F, 0.0F);
		hair_tip27.cubeList.add(new ModelBox(hair_tip27, 38, 0, -0.5F, -2.9451F, 0.051F, 1, 3, 0, 0.0F, false));

		hair28 = new AdvancedModelRenderer(this);
		hair28.setRotationPoint(1.0F, 8.0F, -1.5F);
		head.addChild(hair28);


		hair_root28 = new AdvancedModelRenderer(this);
		hair_root28.setRotationPoint(-3.675F, -12.0F, -2.475F);
		hair28.addChild(hair_root28);
		setRotationAngle(hair_root28, 0.7418F, 0.0F, 0.0F);


		cube_r28 = new AdvancedModelRenderer(this);
		cube_r28.setRotationPoint(0.0F, -0.6389F, 0.0429F);
		hair_root28.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.0F, 0.0F);
		cube_r28.cubeList.add(new ModelBox(cube_r28, 36, 0, -0.5F, -1.5F, 0.0F, 1, 3, 0, 0.0F, false));

		hair_tip28 = new AdvancedModelRenderer(this);
		hair_tip28.setRotationPoint(0.0F, -2.0868F, -0.0268F);
		hair_root28.addChild(hair_tip28);
		setRotationAngle(hair_tip28, 0.3054F, 0.0F, 0.0F);
		hair_tip28.cubeList.add(new ModelBox(hair_tip28, 36, 29, -0.5F, -3.0045F, 0.0745F, 1, 3, 0, 0.0F, false));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		model_core.addChild(body);


		upper_body = new AdvancedModelRenderer(this);
		upper_body.setRotationPoint(0.0F, -19.875F, -3.7F);
		body.addChild(upper_body);
		upper_body.cubeList.add(new ModelBox(upper_body, 24, 41, -4.0F, -2.0F, -2.0F, 8, 8, 4, -0.125F, false));

		lower_body = new AdvancedModelRenderer(this);
		lower_body.setRotationPoint(0.0F, -3.4978F, -6.2235F);
		body.addChild(lower_body);
		setRotationAngle(lower_body, -0.2618F, 0.0F, 0.0F);
		lower_body.cubeList.add(new ModelBox(lower_body, 0, 39, -4.0F, -11.4748F, -2.4317F, 8, 12, 4, -0.0625F, false));

		right_arm = new AdvancedModelRenderer(this);
		right_arm.setRotationPoint(-5.5F, -19.0F, -4.0F);
		model_core.addChild(right_arm);
		setRotationAngle(right_arm, -0.7854F, 0.0F, 0.0F);
		right_arm.cubeList.add(new ModelBox(right_arm, 0, 0, -1.5F, -2.4F, -1.6F, 3, 12, 4, 0.0F, false));

		left_arm = new AdvancedModelRenderer(this);
		left_arm.setRotationPoint(5.5F, -19.0F, -4.0F);
		model_core.addChild(left_arm);
		setRotationAngle(left_arm, -0.7854F, 0.0F, 0.0F);
		left_arm.cubeList.add(new ModelBox(left_arm, 0, 20, -1.5F, -2.4F, -1.7F, 3, 12, 4, 0.0F, false));

		tail = new AdvancedModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 0.0F);
		model_core.addChild(tail);


		tail_root = new AdvancedModelRenderer(this);
		tail_root.setRotationPoint(0.0F, -2.0F, -0.7F);
		tail.addChild(tail_root);
		tail_root.cubeList.add(new ModelBox(tail_root, 0, 0, -4.0F, -2.0F, -8.0F, 8, 4, 16, 0.0F, false));

		tail1 = new AdvancedModelRenderer(this);
		tail1.setRotationPoint(0.0F, 0.5F, 16.0F);
		tail_root.addChild(tail1);
		tail1.cubeList.add(new ModelBox(tail1, 0, 20, -3.0F, -1.5F, -8.0F, 6, 3, 16, 0.0F, false));

		tail2 = new AdvancedModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.5F, 16.0F);
		tail1.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 28, 23, -2.0F, -1.0F, -8.0F, 4, 2, 16, 0.0F, false));

		tail3 = new AdvancedModelRenderer(this);
		tail3.setRotationPoint(0.0F, 0.5F, 12.0F);
		tail2.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 40, 45, -1.0F, -0.5F, -4.0F, 2, 1, 8, 0.0F, false));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityMedusa medusa = (EntityMedusa) entity;
		animate(medusa, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		model_core.render(scale);
	}



	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityMedusa medusa = (EntityMedusa) entity;
		animator.update(medusa);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, medusa);



		// Idle Animation
//		if (medusa.getAnimation() == EntityMedusa.IDLE_ANIMATION && medusa.isEntityAlive()) {
//			animator.setAnimation(EntityMedusa.IDLE_ANIMATION);
//			animator.startKeyframe(20);
//			this.rotate(animator, hair_root1, 35, 0, 0);
//			animator.move(hair_tip1, 0.1F, 0, 0);
//			this.rotate(animator, hair_tip1, -120, 0, 0);
//
//			this.rotate(animator, hair_root2, 0, 0, 32.5F);
//			animator.move(hair_tip2, 0, 0.1F, 0);
//			this.rotate(animator, hair_tip2, 0, 0, -72.5F);
//
//			this.rotate(animator, hair_root3, 0, 0, -42.5F);
//			animator.move(hair_tip3, 0, 0.1F, 0);
//			this.rotate(animator, hair_tip3, 0, 0, 120);
//
//			this.rotate(animator, hair_root4, 0, 0, 62.5F);
////			animator.move(hair_tip4, 0, 0, 0);
//			this.rotate(animator, hair_tip4, 0, 0, 125);
//
//			this.rotate(animator, hair_root5, 0, 0, -67.5F);
////			animator.move(hair_tip5, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip5, 0, 0, -42.5F);
//
//			this.rotate(animator, hair_root6, 0, 0, 45);
//			animator.move(hair_tip6, 0, 0.2F, 0);
//			this.rotate(animator, hair_tip6, 0, 0, -95);
//
//			this.rotate(animator, hair_root7, 0, 0, -70);
//			animator.move(hair_tip7, 0, 0.025F, 0);
//			this.rotate(animator, hair_tip7, 0, 0, 112.5F);
//
//			this.rotate(animator, hair_root8, -42.5F, 0, 0);
////			animator.move(hair_tip8, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip8, 55, 0, 0);
//
//			animator.move(hair_root9, 0, 0.4F, 0);
//			this.rotate(animator, hair_root9, 80, 0, 0);
////			animator.move(hair_tip9, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip9, 50, 0, 0);
//
//			this.rotate(animator, hair_root10, -50, 0, 0);
////			animator.move(hair_tip10, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip10, -85, 0, 0);
//
//			this.rotate(animator, hair_root11, 47.5F, 0, 0);
//			animator.move(hair_tip11, 0, 0.1F, 0);
//			this.rotate(animator, hair_tip11, -135, 0, 0);
//
//			this.rotate(animator, hair_root12, -35, 0, 0);
////			animator.move(hair_tip12, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip12, 32.5F, 0, 0);
//
//			this.rotate(animator, hair_root13, -40, 0, 0);
////			animator.move(hair_tip13, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip13, 67.5F, 0, 0);
//
//			this.rotate(animator, hair_root14, 52.5F, 0, 0);
////			animator.move(hair_tip14, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip14, 60, 0, 0);
//
//			this.rotate(animator, hair_root15, -32.5F, 0, 0);
////			animator.move(hair_tip15, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip15, 12.5F, 0, 0);
//
//			animator.move(hair_root16, 0, 0.1F, 0);
//			this.rotate(animator, hair_root16, 0, 0, 60);
////			animator.move(hair_tip16, -0.1F, 0, 0);
////			this.rotate(animator, hair_tip16, -120, 0, 0);
//
//			this.rotate(animator, hair_root17, 0, 0, -55);
////			animator.move(hair_tip17, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip17, 0, 0, -127.5F);
//
//			this.rotate(animator, hair_root18, 0, 0, 37.5F);
//			animator.move(hair_tip18, 0, 0.1F, 0);
//			this.rotate(animator, hair_tip18, 0, 0, -65);
//
//			this.rotate(animator, hair_root19, 0, 0, -42.5F);
////			animator.move(hair_tip19, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip19, 0, 0, -125);
//
//			this.rotate(animator, hair_root20, 0, 0, 47.5F);
//			animator.move(hair_tip20, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip20, 0, 0, -77.5F);
//
//			this.rotate(animator, hair_root21, 0, 0, 40);
//			animator.move(hair_tip21, 0.1F, 0, 0);
//			this.rotate(animator, hair_tip21, 0, 0, 60);
//
//			this.rotate(animator, hair_root21, 35, 0, 0);
//			animator.move(hair_tip21, 0.1F, 0, 0);
//			this.rotate(animator, hair_tip21, -120, 0, 0);
//
//			animator.move(hair_root22, 0, 0.1F, 0);
//			this.rotate(animator, hair_root22, -90, 0, 0);
////			animator.move(hair_tip22, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip22, -57.5F, 0, 0);
//
//			this.rotate(animator, hair_root23, 27.5F, 0, 0);
//			animator.move(hair_tip23, 0, 0.025F, -0.025F);
//			this.rotate(animator, hair_tip23, 50, 0, 0);
//
//			this.rotate(animator, hair_root24, -47.5F, 0, 0);
//			animator.move(hair_tip24, 0, 0.025F, -0.05F);
//			this.rotate(animator, hair_tip24, 45, 0, 0);
//
//			this.rotate(animator, hair_root25, 32.5F, 0, 0);
////			animator.move(hair_tip25, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip25, -95, 0, 0);
//
//			this.rotate(animator, hair_root26, 40, 0, 0);
//			animator.move(hair_tip26, 0, 0.05F, -0.05F);
//			this.rotate(animator, hair_tip26, -65, 0, 0);
//
//			this.rotate(animator, hair_root27, -47.5F, 0, 0);
////			animator.move(hair_tip27, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip27, -147.5F, 0, 0);
//
//			this.rotate(animator, hair_root28, 30, 0, 0);
////			animator.move(hair_tip28, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip28, -60, 0, 0);
//
//			this.rotate(animator, right_arm, -10, 0, 0);
//
//			this.rotate(animator, left_arm, 10, 0, 0);
//
//			animator.endKeyframe();
//
//			animator.startKeyframe(20);
//			this.rotate(animator, hair_root1, 0, 0, 0);
//			animator.move(hair_tip1, 0, 0, 0);
//			this.rotate(animator, hair_tip1, 0, 0, 0);
//
//			this.rotate(animator, hair_root2, 0, 0, 0);
//			animator.move(hair_tip2, 0, 0, 0);
//			this.rotate(animator, hair_tip2, 0, 0, 0);
//
//			this.rotate(animator, hair_root3, 0, 0, 0);
//			animator.move(hair_tip3, 0, 0, 0);
//			this.rotate(animator, hair_tip3, 0, 0, 0);
//
//			this.rotate(animator, hair_root4, 0, 0, 0);
////			animator.move(hair_tip4, 0, 0, 0);
//			this.rotate(animator, hair_tip4, 0, 0, 0);
//
//			this.rotate(animator, hair_root5, 0, 0, 0);
////			animator.move(hair_tip5, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip5, 0, 0, 0);
//
//			this.rotate(animator, hair_root6, 0, 0, 0);
//			animator.move(hair_tip6, 0, 0, 0);
//			this.rotate(animator, hair_tip6, 0, 0, 0);
//
//			this.rotate(animator, hair_root7, 0, 0, 0);
//			animator.move(hair_tip7, 0, 0, 0);
//			this.rotate(animator, hair_tip7, 0, 0, 0);
//
//			this.rotate(animator, hair_root8, 0, 0, 0);
////			animator.move(hair_tip8, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip8, 0, 0, 0);
//
//			animator.move(hair_root9, 0, 0, 0);
//			this.rotate(animator, hair_root9, 0, 0, 0);
////			animator.move(hair_tip9, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip9, 0, 0, 0);
//
//			this.rotate(animator, hair_root10, 0, 0, 0);
////			animator.move(hair_tip10, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip10, 0, 0, 0);
//
//			this.rotate(animator, hair_root11, 0, 0, 0);
//			animator.move(hair_tip11, 0, 0, 0);
//			this.rotate(animator, hair_tip11, 0, 0, 0);
//
//			this.rotate(animator, hair_root12, 0, 0, 0);
////			animator.move(hair_tip12, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip12, 0, 0, 0);
//
//			this.rotate(animator, hair_root13, 0, 0, 0);
////			animator.move(hair_tip13, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip13, 0, 0, 0);
//
//			this.rotate(animator, hair_root14, 0, 0, 0);
////			animator.move(hair_tip14, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip14, 0, 0, 0);
//
//			this.rotate(animator, hair_root15, 0, 0, 0);
////			animator.move(hair_tip15, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip15, 0, 0, 0);
//
//			animator.move(hair_root16, 0, 0, 0);
//			this.rotate(animator, hair_root16, 0, 0, 0);
////			animator.move(hair_tip16, -0.1F, 0, 0);
////			this.rotate(animator, hair_tip16, -120, 0, 0);
//
//			this.rotate(animator, hair_root17, 0, 0, 0);
////			animator.move(hair_tip17, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip17, 0, 0, 0);
//
//			this.rotate(animator, hair_root18, 0, 0, 0);
//			animator.move(hair_tip18, 0, 0, 0);
//			this.rotate(animator, hair_tip18, 0, 0, 0);
//
//			this.rotate(animator, hair_root19, 0, 0, 0);
////			animator.move(hair_tip19, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip19, 0, 0, 0);
//
//			this.rotate(animator, hair_root20, 0, 0, 0);
//			animator.move(hair_tip20, 0, 0, 0);
//			this.rotate(animator, hair_tip20, 0, 0, 0);
//
//			this.rotate(animator, hair_root21, 0, 0, 0);
//			animator.move(hair_tip21, 0, 0, 0);
//			this.rotate(animator, hair_tip21, 0, 0, 0);
//
//			this.rotate(animator, hair_root21, 0, 0, 0);
//			animator.move(hair_tip21, 0, 0, 0);
//			this.rotate(animator, hair_tip21, 0, 0, 0);
//
//			animator.move(hair_root22, 0, 0, 0);
//			this.rotate(animator, hair_root22, 0, 0, 0);
////			animator.move(hair_tip22, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip22, 0, 0, 0);
//
//			this.rotate(animator, hair_root23, 0, 0, 0);
//			animator.move(hair_tip23, 0, 0, 0);
//			this.rotate(animator, hair_tip23, 0, 0, 0);
//
//			this.rotate(animator, hair_root24, 0, 0, 0);
//			animator.move(hair_tip24, 0, 0, 0);
//			this.rotate(animator, hair_tip24, 0, 0, 0);
//
//			this.rotate(animator, hair_root25, 0, 0, 0);
////			animator.move(hair_tip25, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip25, 0, 0, 0);
//
//			this.rotate(animator, hair_root26, 0, 0, 0);
//			animator.move(hair_tip26, 0, 0, 0);
//			this.rotate(animator, hair_tip26, 0, 0, 0);
//
//			this.rotate(animator, hair_root27, 0, 0, 0);
////			animator.move(hair_tip27, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip27, 0, 0, 0);
//
//			this.rotate(animator, hair_root28, 0, 0, 0);
////			animator.move(hair_tip28, -0.1F, 0, 0);
//			this.rotate(animator, hair_tip28, 0, 0, 0);
//
//			this.rotate(animator, right_arm, 0, 0, 0);
//
//			this.rotate(animator, left_arm, 0, 0, 0);
//
//			animator.endKeyframe();
//		}

		if (medusa.getAnimation() == EntityMedusa.MELEE_ATTACK_ANIMATION && medusa.isEntityAlive()) {
			animator.setAnimation(EntityMedusa.MELEE_ATTACK_ANIMATION);

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, -75, 35, 0);
			this.rotate(animator, left_arm, -75, -35, 0);

			animator.endKeyframe();

			animator.startKeyframe(5);
			this.rotate(animator, right_arm, -8.4724F, -20.5853F, 14.0858F);
			this.rotate(animator, left_arm, -5.202F, 14.9104F, -10.2241F);
			animator.endKeyframe();

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, left_arm, 0, 0, 0);
		}

		if (medusa.getAnimation() == EntityMedusa.STARE_ATTACK_ANIMATION && medusa.isEntityAlive()) {
			animator.setAnimation(EntityMedusa.STARE_ATTACK_ANIMATION);

			animator.startKeyframe(60);
			this.rotate(animator, right_arm, -95.8959F, 18.177F, -20.9969F);
			this.rotate(animator, left_arm, -95.8959F, -18.177F, 20.9969F);
			animator.endKeyframe();

			animator.startKeyframe(5);
			this.rotate(animator, right_arm, -48.4F, 18.18F, -21F);
			this.rotate(animator, left_arm, -48.4F, -18.18F, 21F);
			animator.endKeyframe();
			animator.setStaticKeyframe(35);

			animator.startKeyframe(20);
			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, left_arm, 0, 0, 0);
			animator.endKeyframe();
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		resetToDefaultPose();

		swing(tail_root, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(tail1, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(tail2, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
		swing(tail3, 0.5F, -10, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

		for (int i = 1; i <= 28; i++) {

			float frequency = 0.1F + (float) Math.random() * 0.000005F;
			float amplitude = 0.2F + (float) Math.random() * 0.000001F;
			float oscillation = (float) Math.sin(ageInTicks * frequency + i) * amplitude;

			switch (i) {
				case 1:  this.hair_root1.rotateAngleZ = oscillation; break;
				case 2:  this.hair_root2.rotateAngleZ = oscillation; break;
				case 3:  this.hair_root3.rotateAngleZ = oscillation; break;
				case 4:  this.hair_root4.rotateAngleZ = oscillation; break;
				case 5:  this.hair_root5.rotateAngleZ = oscillation; break;
				case 6:  this.hair_root6.rotateAngleZ = oscillation; break;
				case 7:  this.hair_root7.rotateAngleZ = oscillation; break;
				case 8:  this.hair_root8.rotateAngleX = oscillation; break;
				case 9:  this.hair_root9.rotateAngleX = oscillation; break;
				case 10: this.hair_root10.rotateAngleX = oscillation; break;
				case 11: this.hair_root11.rotateAngleX = oscillation; break;
				case 12: this.hair_root12.rotateAngleX = oscillation; break;
				case 13: this.hair_root13.rotateAngleX = oscillation; break;
				case 14: this.hair_root14.rotateAngleX = oscillation; break;
				case 15: this.hair_root15.rotateAngleX = oscillation; break;
				case 16: this.hair_root16.rotateAngleZ = oscillation; break;
				case 17: this.hair_root17.rotateAngleZ = oscillation; break;
				case 18: this.hair_root18.rotateAngleZ = oscillation; break;
				case 19: this.hair_root19.rotateAngleZ = oscillation; break;
				case 20: this.hair_root20.rotateAngleZ = oscillation; break;
				case 21: this.hair_root21.rotateAngleZ = oscillation; break;
				case 22: this.hair_root22.rotateAngleX = oscillation; break;
				case 23: this.hair_root23.rotateAngleX = oscillation; break;
				case 24: this.hair_root24.rotateAngleX = oscillation; break;
				case 25: this.hair_root25.rotateAngleX = oscillation; break;
				case 26: this.hair_root26.rotateAngleX = oscillation; break;
				case 27: this.hair_root27.rotateAngleX = oscillation; break;
				case 28: this.hair_root28.rotateAngleX = oscillation; break;
			}
		}

		for (int i = 1; i <= 28; i++) {

			float frequency = 0.1F + (float) -Math.random() * 0.000005F;
			float amplitude = 0.2F + (float) -Math.random() * 0.000001F;
			float oscillation = (float) Math.sin(ageInTicks * frequency + i) * amplitude;

			switch (i) {
				case 1:  this.hair_tip1.rotateAngleZ = oscillation; break;
				case 2:  this.hair_tip2.rotateAngleZ = oscillation; break;
				case 3:  this.hair_tip3.rotateAngleZ = oscillation; break;
				case 4:  this.hair_tip4.rotateAngleZ = oscillation; break;
				case 5:  this.hair_tip5.rotateAngleZ = oscillation; break;
				case 6:  this.hair_tip6.rotateAngleZ = oscillation; break;
				case 7:  this.hair_tip7.rotateAngleZ = oscillation; break;
				case 8:  this.hair_tip8.rotateAngleX = oscillation; break;
				case 9:  this.hair_tip9.rotateAngleX = oscillation; break;
				case 10: this.hair_tip10.rotateAngleX = oscillation; break;
				case 11: this.hair_tip11.rotateAngleX = oscillation; break;
				case 12: this.hair_tip12.rotateAngleX = oscillation; break;
				case 13: this.hair_tip13.rotateAngleX = oscillation; break;
				case 14: this.hair_tip14.rotateAngleX = oscillation; break;
				case 15: this.hair_tip15.rotateAngleX = oscillation; break;
				case 16: this.hair_tip16.rotateAngleZ = oscillation; break;
				case 17: this.hair_tip17.rotateAngleZ = oscillation; break;
				case 18: this.hair_tip18.rotateAngleZ = oscillation; break;
				case 19: this.hair_tip19.rotateAngleZ = oscillation; break;
				case 20: this.hair_tip20.rotateAngleZ = oscillation; break;
				case 21: this.hair_tip21.rotateAngleZ = oscillation; break;
				case 22: this.hair_tip22.rotateAngleX = oscillation; break;
				case 23: this.hair_tip23.rotateAngleX = oscillation; break;
				case 24: this.hair_tip24.rotateAngleX = oscillation; break;
				case 25: this.hair_tip25.rotateAngleX = oscillation; break;
				case 26: this.hair_tip26.rotateAngleX = oscillation; break;
				case 27: this.hair_tip27.rotateAngleX = oscillation; break;
				case 28: this.hair_tip28.rotateAngleX = oscillation; break;
			}
		}

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;

	}
}