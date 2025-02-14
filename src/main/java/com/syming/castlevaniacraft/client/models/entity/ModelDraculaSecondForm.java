package com.syming.castlevaniacraft.client.models.entity;

// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaSecondForm;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDraculaSecondForm extends CCModel {
	public AdvancedModelRenderer model_core;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer jaw;
	public AdvancedModelRenderer upper_jaw;
	public AdvancedModelRenderer right_horn;
	public AdvancedModelRenderer left_horn;
	public AdvancedModelRenderer body;
	public AdvancedModelRenderer right_wing;
	public AdvancedModelRenderer left_wing;
	public AdvancedModelRenderer tail;
	public AdvancedModelRenderer middle_tail;
	public AdvancedModelRenderer tip_tail;
	public AdvancedModelRenderer right_arm;
	public AdvancedModelRenderer right_hand_thumb;
	public AdvancedModelRenderer right_hand_index_finger;
	public AdvancedModelRenderer right_hand_middle_finger;
	public AdvancedModelRenderer left_arm;
	public AdvancedModelRenderer left_hand_thumb;
	public AdvancedModelRenderer left_hand_index_finger;
	public AdvancedModelRenderer left_hand_middle_finger;
	public AdvancedModelRenderer right_leg;
	public AdvancedModelRenderer right_foot;
	public AdvancedModelRenderer left_leg;
	public AdvancedModelRenderer left_foot;

	public AdvancedModelRenderer cube_r1;
	public AdvancedModelRenderer cube_r2;
	public AdvancedModelRenderer cube_r3;
	public AdvancedModelRenderer cube_r4;
	public AdvancedModelRenderer cube_r5;
	public AdvancedModelRenderer cube_r6;
	public AdvancedModelRenderer cube_r7;
	public AdvancedModelRenderer cube_r8;
	public AdvancedModelRenderer cube_r9;
	public AdvancedModelRenderer cube_r10;
	public AdvancedModelRenderer cube_r11;
	public AdvancedModelRenderer cube_r12;
	public AdvancedModelRenderer cube_r13;
	public AdvancedModelRenderer cube_r14;
	public AdvancedModelRenderer cube_r15;
	public AdvancedModelRenderer cube_r16;
	public AdvancedModelRenderer cube_r17;
	public AdvancedModelRenderer cube_r18;
	public AdvancedModelRenderer cube_r19;
	public AdvancedModelRenderer cube_r20;
	public AdvancedModelRenderer cube_r21;
	public AdvancedModelRenderer cube_r22;
	public AdvancedModelRenderer cube_r23;
	public AdvancedModelRenderer cube_r24;
	public AdvancedModelRenderer cube_r25;
	public AdvancedModelRenderer cube_r26;
	public AdvancedModelRenderer cube_r27;
	public AdvancedModelRenderer cube_r28;
	public AdvancedModelRenderer cube_r29;
	public AdvancedModelRenderer cube_r30;
	public AdvancedModelRenderer cube_r31;
	public AdvancedModelRenderer cube_r32;
	public AdvancedModelRenderer cube_r33;
	public AdvancedModelRenderer cube_r34;
	public AdvancedModelRenderer cube_r35;
	public AdvancedModelRenderer cube_r36;
	public AdvancedModelRenderer cube_r37;
	public AdvancedModelRenderer cube_r38;
	public AdvancedModelRenderer cube_r39;
	public AdvancedModelRenderer cube_r40;
	public AdvancedModelRenderer cube_r41;
	public AdvancedModelRenderer cube_r42;
	public AdvancedModelRenderer cube_r43;
	public AdvancedModelRenderer cube_r44;
	public AdvancedModelRenderer cube_r45;
	public AdvancedModelRenderer cube_r46;
	public AdvancedModelRenderer cube_r47;
	public AdvancedModelRenderer cube_r48;
	public AdvancedModelRenderer cube_r49;
	public AdvancedModelRenderer cube_r50;
	public AdvancedModelRenderer cube_r51;
	public AdvancedModelRenderer cube_r52;
	public AdvancedModelRenderer cube_r53;
	public AdvancedModelRenderer cube_r54;
	public AdvancedModelRenderer cube_r55;
	public AdvancedModelRenderer cube_r56;
	public AdvancedModelRenderer cube_r57;

	private ModelAnimator animator;

	public ModelDraculaSecondForm() {
		textureWidth = 256;
		textureHeight = 256;

		model_core = new AdvancedModelRenderer(this);
		model_core.setRotationPoint(0.0F, 24.0F, 0.0F);


		head = new AdvancedModelRenderer(this);
		head.setRotationPoint(0.0F, -58.2F, 8.0F);
		model_core.addChild(head);
		head.cubeList.add(new ModelBox(head, 34, 79, -4.5F, -4.0F, -9.0F, 9, 8, 8, 0.0F, false));

		jaw = new AdvancedModelRenderer(this);
		jaw.setRotationPoint(0.0F, 2.4333F, -9.0667F);
		head.addChild(jaw);
		setRotationAngle(jaw, 0.4363F, 0.0F, 0.0F);
		jaw.cubeList.add(new ModelBox(jaw, 34, 74, -1.5F, 0.1667F, -3.9333F, 3, 1, 4, 0.0F, false));

		cube_r1 = new AdvancedModelRenderer(this);
		cube_r1.setRotationPoint(-0.5F, 1.5667F, 8.0667F);
		jaw.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.7854F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 100, 76, 9.3338F, -2.4F, -7.5711F, 0, 1, 1, 0.0F, false));

		cube_r2 = new AdvancedModelRenderer(this);
		cube_r2.setRotationPoint(-0.5F, 1.5667F, 8.0667F);
		jaw.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.7854F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 24, 107, -8.6267F, -2.4F, -8.2782F, 0, 1, 1, 0.0F, false));

		upper_jaw = new AdvancedModelRenderer(this);
		upper_jaw.setRotationPoint(0.0F, 1.3F, -11.08F);
		head.addChild(upper_jaw);
		upper_jaw.cubeList.add(new ModelBox(upper_jaw, 48, 74, 2.4F, -0.2F, 0.98F, 0, 1, 1, 0.0F, false));
		upper_jaw.cubeList.add(new ModelBox(upper_jaw, 48, 76, -2.4F, -0.2F, 0.98F, 0, 1, 1, 0.0F, false));
		upper_jaw.cubeList.add(new ModelBox(upper_jaw, 108, 40, -2.5F, -2.2F, -1.92F, 5, 2, 4, 0.0F, false));

		cube_r3 = new AdvancedModelRenderer(this);
		cube_r3.setRotationPoint(-0.5F, 2.7F, 10.08F);
		upper_jaw.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.7854F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 30, 109, 10.0409F, -2.9F, -6.864F, 0, 1, 1, 0.0F, false));

		cube_r4 = new AdvancedModelRenderer(this);
		cube_r4.setRotationPoint(-0.5F, 2.7F, 10.08F);
		upper_jaw.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -0.7854F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 32, 109, -9.3338F, -2.9F, -7.5711F, 0, 1, 1, 0.0F, false));

		right_horn = new AdvancedModelRenderer(this);
		right_horn.setRotationPoint(-5.0489F, -6.6508F, -1.5047F);
		head.addChild(right_horn);


		cube_r5 = new AdvancedModelRenderer(this);
		cube_r5.setRotationPoint(4.5489F, 10.6508F, 0.5047F);
		right_horn.addChild(cube_r5);
		setRotationAngle(cube_r5, -1.1249F, 0.3467F, 0.6207F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 110, 66, -12.943F, -3.3311F, -9.4069F, 4, 6, 4, -1.5F, false));

		cube_r6 = new AdvancedModelRenderer(this);
		cube_r6.setRotationPoint(4.5489F, 10.6508F, 0.5047F);
		right_horn.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.9599F, -0.7854F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 108, 112, -5.8562F, -11.9023F, -9.246F, 4, 6, 4, -1.25F, false));

		cube_r7 = new AdvancedModelRenderer(this);
		cube_r7.setRotationPoint(4.5489F, 10.6508F, 0.5047F);
		right_horn.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.48F, -0.7854F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 92, 112, -5.8891F, -11.2955F, -5.1226F, 4, 6, 4, -1.0F, false));

		left_horn = new AdvancedModelRenderer(this);
		left_horn.setRotationPoint(5.0489F, -6.6508F, -1.5047F);
		head.addChild(left_horn);


		cube_r8 = new AdvancedModelRenderer(this);
		cube_r8.setRotationPoint(-5.5489F, 10.6508F, 0.5047F);
		left_horn.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.9599F, 0.7854F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 20, 114, 2.5634F, -12.4815F, -8.8405F, 4, 6, 4, -1.25F, true));

		cube_r9 = new AdvancedModelRenderer(this);
		cube_r9.setRotationPoint(-5.5489F, 10.6508F, 0.5047F);
		left_horn.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.48F, 0.7854F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 36, 114, 2.5962F, -11.622F, -4.4954F, 4, 6, 4, -1.0F, true));

		cube_r10 = new AdvancedModelRenderer(this);
		cube_r10.setRotationPoint(-5.5489F, 10.6508F, 0.5047F);
		left_horn.addChild(cube_r10);
		setRotationAngle(cube_r10, -1.1249F, -0.3467F, -0.6207F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 34, 64, 9.708F, -2.8309F, -9.0013F, 4, 6, 4, -1.5F, true));

		body = new AdvancedModelRenderer(this);
		body.setRotationPoint(0.0F, -44.5714F, 10.8571F);
		model_core.addChild(body);
		body.cubeList.add(new ModelBox(body, 68, 79, -6.0F, 16.5714F, -3.8571F, 12, 8, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 48, -9.0F, 7.5714F, -3.8571F, 18, 9, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -13.0F, -8.4286F, -3.8571F, 26, 16, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 50, 56, -8.0F, -12.4286F, 4.1429F, 16, 20, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 24, -13.0F, -12.4286F, -3.8571F, 26, 4, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 36, -10.0F, -16.4286F, -3.8571F, 20, 4, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 50, 48, -13.0F, -8.4286F, -4.8571F, 26, 7, 1, 0.0F, false));

		right_wing = new AdvancedModelRenderer(this);
		right_wing.setRotationPoint(-7.0F, -0.4286F, 3.1429F);
		body.addChild(right_wing);


		cube_r11 = new AdvancedModelRenderer(this);
		cube_r11.setRotationPoint(-0.95F, -2.2143F, 0.9714F);
		right_wing.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, -0.7854F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 60, 95, 0.005F, -6.5F, 9.3489F, 0, 21, 2, 0.0F, false));
		cube_r11.cubeList.add(new ModelBox(cube_r11, 52, 114, 0.005F, -7.5F, 8.3589F, 0, 21, 1, 0.0F, false));
		cube_r11.cubeList.add(new ModelBox(cube_r11, 76, 92, 0.005F, -8.5F, 4.3991F, 0, 21, 4, 0.0F, false));
		cube_r11.cubeList.add(new ModelBox(cube_r11, 64, 95, 0.005F, -9.5F, 3.4092F, 0, 21, 1, 0.0F, false));
		cube_r11.cubeList.add(new ModelBox(cube_r11, 26, 84, 0.005F, -10.5F, -0.5707F, 0, 21, 4, 0.0F, false));

		left_wing = new AdvancedModelRenderer(this);
		left_wing.setRotationPoint(7.0F, -0.4286F, 3.1429F);
		body.addChild(left_wing);


		cube_r12 = new AdvancedModelRenderer(this);
		cube_r12.setRotationPoint(0.95F, -2.2143F, 0.9714F);
		left_wing.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.7854F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 54, 114, -0.005F, -7.5F, 8.3589F, 0, 21, 1, 0.0F, true));
		cube_r12.cubeList.add(new ModelBox(cube_r12, 84, 92, -0.005F, -8.5F, 4.3991F, 0, 21, 4, 0.0F, true));
		cube_r12.cubeList.add(new ModelBox(cube_r12, 66, 95, -0.005F, -9.5F, 3.4092F, 0, 21, 1, 0.0F, true));
		cube_r12.cubeList.add(new ModelBox(cube_r12, 68, 92, -0.005F, -10.5F, -0.5707F, 0, 21, 4, 0.0F, true));
		cube_r12.cubeList.add(new ModelBox(cube_r12, 56, 95, -0.005F, -6.5F, 9.3489F, 0, 21, 2, 0.0F, true));

		tail = new AdvancedModelRenderer(this);
		tail.setRotationPoint(0.0F, 22.5714F, 1.1429F);
		body.addChild(tail);


		cube_r13 = new AdvancedModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, 1.9F, 3.2F);
		tail.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.4363F, 0.0F, 0.0F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 68, 20, -1.5F, -1.5F, -5.0F, 3, 3, 14, 0.0F, false));

		middle_tail = new AdvancedModelRenderer(this);
		middle_tail.setRotationPoint(-0.5F, 5.0F, 11.0F);
		tail.addChild(middle_tail);


		cube_r14 = new AdvancedModelRenderer(this);
		cube_r14.setRotationPoint(-1.5F, -1.5548F, 5.4126F);
		middle_tail.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.3695F, -0.3272F, -0.1238F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 0, 84, -1.5F, -1.5F, -7.0F, 3, 3, 10, -0.25F, false));

		tip_tail = new AdvancedModelRenderer(this);
		tip_tail.setRotationPoint(-2.2521F, -1.9831F, 7.4107F);
		middle_tail.addChild(tip_tail);


		cube_r15 = new AdvancedModelRenderer(this);
		cube_r15.setRotationPoint(-2.0F, -1.0F, 2.0F);
		tip_tail.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.4942F, -0.7654F, -0.3572F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 0, 107, -1.5F, -1.5F, -3.5F, 3, 3, 7, -0.5F, false));

		right_arm = new AdvancedModelRenderer(this);
		right_arm.setRotationPoint(-13.6233F, -53.0977F, 9.4197F);
		model_core.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 92, 92, -5.3767F, -2.9023F, -2.4197F, 6, 5, 5, 0.75F, false));

		cube_r16 = new AdvancedModelRenderer(this);
		cube_r16.setRotationPoint(-9.3485F, 5.3316F, -12.4479F);
		right_arm.addChild(cube_r16);
		setRotationAngle(cube_r16, 1.7171F, -1.098F, -2.043F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 108, 30, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		cube_r17 = new AdvancedModelRenderer(this);
		cube_r17.setRotationPoint(-9.3487F, 2.4637F, -6.8581F);
		right_arm.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.9073F, -0.962F, -1.1177F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 56, 37, -5.0F, -2.5F, -2.5F, 8, 5, 5, 0.5F, false));

		cube_r18 = new AdvancedModelRenderer(this);
		cube_r18.setRotationPoint(-6.3767F, 0.5977F, -2.9197F);
		right_arm.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.2533F, -0.7519F, -0.3622F);
		cube_r18.cubeList.add(new ModelBox(cube_r18, 34, 95, -3.0F, -2.5F, -2.5F, 6, 5, 5, 0.6F, false));

		right_hand_thumb = new AdvancedModelRenderer(this);
		right_hand_thumb.setRotationPoint(-7.9043F, 4.6776F, -15.066F);
		right_arm.addChild(right_hand_thumb);


		cube_r19 = new AdvancedModelRenderer(this);
		cube_r19.setRotationPoint(0.0059F, 2.241F, -2.0563F);
		right_hand_thumb.addChild(cube_r19);
		setRotationAngle(cube_r19, 2.1018F, -0.7643F, -1.5538F);
		cube_r19.cubeList.add(new ModelBox(cube_r19, 82, 122, -2.5F, -2.0F, -2.0F, 5, 4, 4, -1.75F, false));

		cube_r20 = new AdvancedModelRenderer(this);
		cube_r20.setRotationPoint(-0.0059F, 0.759F, -0.7937F);
		right_hand_thumb.addChild(cube_r20);
		setRotationAngle(cube_r20, 2.1018F, -0.7643F, -1.5538F);
		cube_r20.cubeList.add(new ModelBox(cube_r20, 102, 76, -3.0F, -2.5F, -2.5F, 6, 5, 5, -1.75F, false));

		right_hand_index_finger = new AdvancedModelRenderer(this);
		right_hand_index_finger.setRotationPoint(-10.0191F, 6.012F, -15.426F);
		right_arm.addChild(right_hand_index_finger);


		cube_r21 = new AdvancedModelRenderer(this);
		cube_r21.setRotationPoint(0.9914F, 0.9939F, -1.5171F);
		right_hand_index_finger.addChild(cube_r21);
		setRotationAngle(cube_r21, 2.775F, -0.7107F, -2.3821F);
		cube_r21.cubeList.add(new ModelBox(cube_r21, 64, 117, -2.5F, -2.0F, -2.0F, 5, 4, 4, -1.75F, false));

		cube_r22 = new AdvancedModelRenderer(this);
		cube_r22.setRotationPoint(0.2086F, 0.1561F, -0.3329F);
		right_hand_index_finger.addChild(cube_r22);
		setRotationAngle(cube_r22, 2.775F, -0.7107F, -2.3821F);
		cube_r22.cubeList.add(new ModelBox(cube_r22, 102, 10, -3.0F, -2.5F, -2.5F, 6, 5, 5, -1.75F, false));

		right_hand_middle_finger = new AdvancedModelRenderer(this);
		right_hand_middle_finger.setRotationPoint(-9.6511F, 7.658F, -14.4002F);
		right_arm.addChild(right_hand_middle_finger);


		cube_r23 = new AdvancedModelRenderer(this);
		cube_r23.setRotationPoint(0.9559F, -0.2806F, -2.3008F);
		right_hand_middle_finger.addChild(cube_r23);
		setRotationAngle(cube_r23, -2.7998F, -1.1724F, -3.107F);
		cube_r23.cubeList.add(new ModelBox(cube_r23, 0, 117, -2.5F, -1.5F, -2.5F, 5, 4, 4, -1.75F, false));

		cube_r24 = new AdvancedModelRenderer(this);
		cube_r24.setRotationPoint(0.4367F, 0.2826F, -0.3669F);
		right_hand_middle_finger.addChild(cube_r24);
		setRotationAngle(cube_r24, -2.7998F, -1.1724F, -3.107F);
		cube_r24.cubeList.add(new ModelBox(cube_r24, 88, 56, -3.0F, -2.5F, -2.5F, 6, 5, 5, -1.75F, false));

		left_arm = new AdvancedModelRenderer(this);
		left_arm.setRotationPoint(13.6233F, -53.0977F, 9.4197F);
		model_core.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 0, 97, -0.6233F, -2.9023F, -2.4197F, 6, 5, 5, 0.75F, true));

		cube_r25 = new AdvancedModelRenderer(this);
		cube_r25.setRotationPoint(9.3485F, 5.3316F, -12.4479F);
		left_arm.addChild(cube_r25);
		setRotationAngle(cube_r25, 1.7171F, 1.098F, 2.043F);
		cube_r25.cubeList.add(new ModelBox(cube_r25, 110, 56, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, true));

		cube_r26 = new AdvancedModelRenderer(this);
		cube_r26.setRotationPoint(9.3487F, 2.4637F, -6.8581F);
		left_arm.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.9073F, 0.962F, 1.1177F);
		cube_r26.cubeList.add(new ModelBox(cube_r26, 82, 37, -3.0F, -2.5F, -2.5F, 8, 5, 5, 0.5F, true));

		cube_r27 = new AdvancedModelRenderer(this);
		cube_r27.setRotationPoint(6.3767F, 0.5977F, -2.9197F);
		left_arm.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.2533F, 0.7519F, 0.3622F);
		cube_r27.cubeList.add(new ModelBox(cube_r27, 102, 0, -3.0F, -2.5F, -2.5F, 6, 5, 5, 0.6F, true));

		left_hand_thumb = new AdvancedModelRenderer(this);
		left_hand_thumb.setRotationPoint(7.9043F, 4.6776F, -15.066F);
		left_arm.addChild(left_hand_thumb);


		cube_r28 = new AdvancedModelRenderer(this);
		cube_r28.setRotationPoint(0.0059F, 0.759F, -0.7937F);
		left_hand_thumb.addChild(cube_r28);
		setRotationAngle(cube_r28, 2.1018F, 0.7643F, 1.5538F);
		cube_r28.cubeList.add(new ModelBox(cube_r28, 92, 102, -3.0F, -2.5F, -2.5F, 6, 5, 5, -1.75F, true));

		cube_r29 = new AdvancedModelRenderer(this);
		cube_r29.setRotationPoint(-0.0059F, 2.241F, -2.0563F);
		left_hand_thumb.addChild(cube_r29);
		setRotationAngle(cube_r29, 2.1018F, 0.7643F, 1.5538F);
		cube_r29.cubeList.add(new ModelBox(cube_r29, 114, 94, -2.5F, -2.0F, -2.0F, 5, 4, 4, -1.75F, true));

		left_hand_index_finger = new AdvancedModelRenderer(this);
		left_hand_index_finger.setRotationPoint(10.0191F, 6.012F, -15.426F);
		left_arm.addChild(left_hand_index_finger);


		cube_r30 = new AdvancedModelRenderer(this);
		cube_r30.setRotationPoint(-0.2086F, 0.1561F, -0.3328F);
		left_hand_index_finger.addChild(cube_r30);
		setRotationAngle(cube_r30, 2.775F, 0.7107F, 2.3821F);
		cube_r30.cubeList.add(new ModelBox(cube_r30, 102, 20, -3.0F, -2.5F, -2.5F, 6, 5, 5, -1.75F, true));

		cube_r31 = new AdvancedModelRenderer(this);
		cube_r31.setRotationPoint(-0.9914F, 0.9939F, -1.5171F);
		left_hand_index_finger.addChild(cube_r31);
		setRotationAngle(cube_r31, 2.775F, 0.7107F, 2.3821F);
		cube_r31.cubeList.add(new ModelBox(cube_r31, 114, 102, -2.5F, -2.0F, -2.0F, 5, 4, 4, -1.75F, true));

		left_hand_middle_finger = new AdvancedModelRenderer(this);
		left_hand_middle_finger.setRotationPoint(9.651F, 7.658F, -14.4002F);
		left_arm.addChild(left_hand_middle_finger);


		cube_r32 = new AdvancedModelRenderer(this);
		cube_r32.setRotationPoint(-0.9559F, -0.2806F, -2.3008F);
		left_hand_middle_finger.addChild(cube_r32);
		setRotationAngle(cube_r32, -2.7998F, 1.1724F, 3.107F);
		cube_r32.cubeList.add(new ModelBox(cube_r32, 114, 86, -2.5F, -1.5F, -2.5F, 5, 4, 4, -1.75F, true));

		cube_r33 = new AdvancedModelRenderer(this);
		cube_r33.setRotationPoint(-0.4366F, 0.2826F, -0.3669F);
		left_hand_middle_finger.addChild(cube_r33);
		setRotationAngle(cube_r33, -2.7998F, 1.1724F, 3.107F);
		cube_r33.cubeList.add(new ModelBox(cube_r33, 88, 66, -3.0F, -2.5F, -2.5F, 6, 5, 5, -1.75F, true));

		right_leg = new AdvancedModelRenderer(this);
		right_leg.setRotationPoint(-7.0F, -24.0F, 10.0F);
		model_core.addChild(right_leg);


		cube_r34 = new AdvancedModelRenderer(this);
		cube_r34.setRotationPoint(-4.8312F, 13.2464F, -10.5723F);
		right_leg.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.2618F, 0.5236F, 0.0F);
		cube_r34.cubeList.add(new ModelBox(cube_r34, 30, 124, -1.5F, -6.0F, 3.5F, 3, 3, 2, 1.25F, false));

		cube_r35 = new AdvancedModelRenderer(this);
		cube_r35.setRotationPoint(-4.7665F, 13.7294F, -10.4602F);
		right_leg.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.2618F, 0.5236F, 0.0F);
		cube_r35.cubeList.add(new ModelBox(cube_r35, 56, 118, -1.5F, -2.0F, 3.5F, 3, 4, 1, 1.125F, false));

		cube_r36 = new AdvancedModelRenderer(this);
		cube_r36.setRotationPoint(-5.8191F, 3.9407F, -12.2834F);
		right_leg.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.2618F, 0.5236F, 0.0F);
		cube_r36.cubeList.add(new ModelBox(cube_r36, 68, 0, -1.5F, -4.0F, -1.5F, 3, 6, 14, 1.75F, false));

		cube_r37 = new AdvancedModelRenderer(this);
		cube_r37.setRotationPoint(-5.2368F, 8.2874F, -11.2748F);
		right_leg.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.2618F, 0.5236F, 0.0F);
		cube_r37.cubeList.add(new ModelBox(cube_r37, 102, 86, -1.5F, -1.0F, -1.5F, 3, 3, 3, 1.5F, false));

		cube_r38 = new AdvancedModelRenderer(this);
		cube_r38.setRotationPoint(-4.525F, 13.6F, -10.042F);
		right_leg.addChild(cube_r38);
		setRotationAngle(cube_r38, 0.2618F, 0.5236F, 0.0F);
		cube_r38.cubeList.add(new ModelBox(cube_r38, 124, 7, -1.5F, -2.0F, -1.5F, 3, 4, 3, 1.25F, false));

		right_foot = new AdvancedModelRenderer(this);
		right_foot.setRotationPoint(-4.2118F, 16.9167F, -9.5148F);
		right_leg.addChild(right_foot);
		right_foot.cubeList.add(new ModelBox(right_foot, 124, 110, -2.4882F, 6.0833F, -8.2852F, 1, 1, 4, 0.125F, false));
		right_foot.cubeList.add(new ModelBox(right_foot, 88, 76, -2.4882F, 6.4333F, -10.0852F, 1, 1, 2, -0.25F, false));
		right_foot.cubeList.add(new ModelBox(right_foot, 22, 101, -2.4882F, 5.9333F, -9.0852F, 1, 1, 1, -0.25F, false));

		cube_r39 = new AdvancedModelRenderer(this);
		cube_r39.setRotationPoint(-6.0276F, 6.4333F, -6.6245F);
		right_foot.addChild(cube_r39);
		setRotationAngle(cube_r39, 0.0F, 0.7854F, 0.0F);
		cube_r39.cubeList.add(new ModelBox(cube_r39, 20, 107, -0.5F, -0.5F, -0.5F, 1, 1, 1, -0.25F, false));

		cube_r40 = new AdvancedModelRenderer(this);
		cube_r40.setRotationPoint(-6.3811F, 6.9333F, -6.9781F);
		right_foot.addChild(cube_r40);
		setRotationAngle(cube_r40, 0.0F, 0.7854F, 0.0F);
		cube_r40.cubeList.add(new ModelBox(cube_r40, 102, 30, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.25F, false));

		cube_r41 = new AdvancedModelRenderer(this);
		cube_r41.setRotationPoint(-4.3882F, 6.5833F, -4.9852F);
		right_foot.addChild(cube_r41);
		setRotationAngle(cube_r41, 0.0F, 0.7854F, 0.0F);
		cube_r41.cubeList.add(new ModelBox(cube_r41, 20, 109, -0.5F, -0.5F, -2.0F, 1, 1, 4, 0.125F, false));

		cube_r42 = new AdvancedModelRenderer(this);
		cube_r42.setRotationPoint(-0.2882F, 2.5833F, -0.4852F);
		right_foot.addChild(cube_r42);
		setRotationAngle(cube_r42, 0.0F, 0.5236F, 0.0F);
		cube_r42.cubeList.add(new ModelBox(cube_r42, 124, 0, -1.5F, -3.5F, -1.0F, 3, 4, 3, 0.5F, false));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 100, 122, -1.5F, 1.5F, -2.0F, 3, 1, 5, 0.5F, false));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 104, 47, -1.5F, 3.5F, -4.0F, 3, 1, 8, 0.5F, false));

		cube_r43 = new AdvancedModelRenderer(this);
		cube_r43.setRotationPoint(4.0348F, 6.3833F, 6.9331F);
		right_foot.addChild(cube_r43);
		setRotationAngle(cube_r43, 0.0F, 0.5236F, 0.0F);
		cube_r43.cubeList.add(new ModelBox(cube_r43, 22, 103, -0.5F, -0.5F, -0.5F, 1, 1, 1, -0.25F, false));

		cube_r44 = new AdvancedModelRenderer(this);
		cube_r44.setRotationPoint(4.1957F, 6.8833F, 7.2168F);
		right_foot.addChild(cube_r44);
		setRotationAngle(cube_r44, 0.0F, 0.5236F, 0.0F);
		cube_r44.cubeList.add(new ModelBox(cube_r44, 124, 27, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.25F, false));

		cube_r45 = new AdvancedModelRenderer(this);
		cube_r45.setRotationPoint(2.8957F, 6.5833F, 4.9828F);
		right_foot.addChild(cube_r45);
		setRotationAngle(cube_r45, 0.0F, 0.5236F, 0.0F);
		cube_r45.cubeList.add(new ModelBox(cube_r45, 124, 76, -0.5F, -0.5F, -2.0F, 1, 1, 4, 0.125F, false));

		left_leg = new AdvancedModelRenderer(this);
		left_leg.setRotationPoint(7.0F, -24.0F, 10.0F);
		model_core.addChild(left_leg);


		cube_r46 = new AdvancedModelRenderer(this);
		cube_r46.setRotationPoint(5.2368F, 8.2874F, -11.2748F);
		left_leg.addChild(cube_r46);
		setRotationAngle(cube_r46, 0.2618F, -0.5236F, 0.0F);
		cube_r46.cubeList.add(new ModelBox(cube_r46, 124, 21, -1.5F, -1.0F, -1.5F, 3, 3, 3, 1.5F, true));

		cube_r47 = new AdvancedModelRenderer(this);
		cube_r47.setRotationPoint(4.8312F, 13.2464F, -10.5723F);
		left_leg.addChild(cube_r47);
		setRotationAngle(cube_r47, 0.2618F, -0.5236F, 0.0F);
		cube_r47.cubeList.add(new ModelBox(cube_r47, 40, 124, -1.5F, -6.0F, 3.5F, 3, 3, 2, 1.25F, true));

		cube_r48 = new AdvancedModelRenderer(this);
		cube_r48.setRotationPoint(4.7665F, 13.7294F, -10.4602F);
		left_leg.addChild(cube_r48);
		setRotationAngle(cube_r48, 0.2618F, -0.5236F, 0.0F);
		cube_r48.cubeList.add(new ModelBox(cube_r48, 56, 123, -1.5F, -2.0F, 3.5F, 3, 4, 1, 1.125F, true));

		cube_r49 = new AdvancedModelRenderer(this);
		cube_r49.setRotationPoint(4.525F, 13.6F, -10.042F);
		left_leg.addChild(cube_r49);
		setRotationAngle(cube_r49, 0.2618F, -0.5236F, 0.0F);
		cube_r49.cubeList.add(new ModelBox(cube_r49, 18, 124, -1.5F, -2.0F, -1.5F, 3, 4, 3, 1.25F, true));

		cube_r50 = new AdvancedModelRenderer(this);
		cube_r50.setRotationPoint(5.8191F, 3.9407F, -12.2834F);
		left_leg.addChild(cube_r50);
		setRotationAngle(cube_r50, 0.2618F, -0.5236F, 0.0F);
		cube_r50.cubeList.add(new ModelBox(cube_r50, 0, 64, -1.5F, -4.0F, -1.5F, 3, 6, 14, 1.75F, true));

		left_foot = new AdvancedModelRenderer(this);
		left_foot.setRotationPoint(4.2118F, 16.9167F, -9.5148F);
		left_leg.addChild(left_foot);
		left_foot.cubeList.add(new ModelBox(left_foot, 22, 99, 1.4882F, 5.9333F, -9.0852F, 1, 1, 1, -0.25F, true));
		left_foot.cubeList.add(new ModelBox(left_foot, 124, 115, 1.4882F, 6.0833F, -8.2852F, 1, 1, 4, 0.125F, true));
		left_foot.cubeList.add(new ModelBox(left_foot, 94, 76, 1.4882F, 6.4333F, -10.0852F, 1, 1, 2, -0.25F, true));

		cube_r51 = new AdvancedModelRenderer(this);
		cube_r51.setRotationPoint(0.2882F, 2.5833F, -0.4852F);
		left_foot.addChild(cube_r51);
		setRotationAngle(cube_r51, 0.0F, -0.5236F, 0.0F);
		cube_r51.cubeList.add(new ModelBox(cube_r51, 124, 14, -1.5F, -3.5F, -1.0F, 3, 4, 3, 0.5F, true));
		cube_r51.cubeList.add(new ModelBox(cube_r51, 34, 105, -1.5F, 3.5F, -4.0F, 3, 1, 8, 0.5F, true));
		cube_r51.cubeList.add(new ModelBox(cube_r51, 116, 122, -1.5F, 1.5F, -2.0F, 3, 1, 5, 0.5F, true));

		cube_r52 = new AdvancedModelRenderer(this);
		cube_r52.setRotationPoint(-4.1958F, 6.8833F, 7.2167F);
		left_foot.addChild(cube_r52);
		setRotationAngle(cube_r52, 0.0F, -0.5236F, 0.0F);
		cube_r52.cubeList.add(new ModelBox(cube_r52, 0, 125, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.25F, true));

		cube_r53 = new AdvancedModelRenderer(this);
		cube_r53.setRotationPoint(-2.8958F, 6.5833F, 4.9827F);
		left_foot.addChild(cube_r53);
		setRotationAngle(cube_r53, 0.0F, -0.5236F, 0.0F);
		cube_r53.cubeList.add(new ModelBox(cube_r53, 124, 81, -0.5F, -0.5F, -2.0F, 1, 1, 4, 0.125F, true));

		cube_r54 = new AdvancedModelRenderer(this);
		cube_r54.setRotationPoint(6.3811F, 6.9333F, -6.9781F);
		left_foot.addChild(cube_r54);
		setRotationAngle(cube_r54, 0.0F, -0.7854F, 0.0F);
		cube_r54.cubeList.add(new ModelBox(cube_r54, 102, 33, -0.5F, -0.5F, -1.0F, 1, 1, 2, -0.25F, true));

		cube_r55 = new AdvancedModelRenderer(this);
		cube_r55.setRotationPoint(4.3882F, 6.5833F, -4.9852F);
		left_foot.addChild(cube_r55);
		setRotationAngle(cube_r55, 0.0F, -0.7854F, 0.0F);
		cube_r55.cubeList.add(new ModelBox(cube_r55, 82, 117, -0.5F, -0.5F, -2.0F, 1, 1, 4, 0.125F, true));

		cube_r56 = new AdvancedModelRenderer(this);
		cube_r56.setRotationPoint(6.0275F, 6.4333F, -6.6245F);
		left_foot.addChild(cube_r56);
		setRotationAngle(cube_r56, 0.0F, -0.7854F, 0.0F);
		cube_r56.cubeList.add(new ModelBox(cube_r56, 22, 105, -0.5F, -0.5F, -0.5F, 1, 1, 1, -0.25F, true));

		cube_r57 = new AdvancedModelRenderer(this);
		cube_r57.setRotationPoint(-4.0348F, 6.3833F, 6.9331F);
		left_foot.addChild(cube_r57);
		setRotationAngle(cube_r57, 0.0F, -0.5236F, 0.0F);
		cube_r57.cubeList.add(new ModelBox(cube_r57, 22, 97, -0.5F, -0.5F, -0.5F, 1, 1, 1, -0.25F, true));

		this.animator = ModelAnimator.create();
		updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityDraculaSecondForm draculaSecondForm = (EntityDraculaSecondForm) entity;
		animate(draculaSecondForm, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
		this.model_core.render(scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		EntityDraculaSecondForm draculaSecondForm = (EntityDraculaSecondForm) entity;
		animator.update(draculaSecondForm);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, draculaSecondForm);



		// Idle Animation
		if (draculaSecondForm.getAnimation() == EntityDraculaSecondForm.IDLE_ANIMATION && draculaSecondForm.isEntityAlive()) {
			animator.setAnimation(EntityDraculaSecondForm.IDLE_ANIMATION);

			animator.startKeyframe(20);
			animator.move(head, 0, 0.375F, 0);
			animator.move(body, 0, 0.375F, 0);
			animator.move(right_arm, 0, 0.375F, 0);
			animator.move(left_arm, 0, 0.375F, 0);

			this.rotate(animator, right_hand_thumb, -27.5F, 0, 0);
			this.rotate(animator, right_hand_index_finger, 0, 27.5F, 0);
			this.rotate(animator, right_hand_middle_finger, 27.5F, 0, 0);

			this.rotate(animator, left_hand_thumb,-27.5F,0,0);
			this.rotate(animator, left_hand_index_finger,0,-27.5F,0);
			this.rotate(animator, left_hand_middle_finger, 27.5F, 0, 0);
			animator.endKeyframe();

			animator.startKeyframe(20);
			animator.move(head, 0, 0, 0);
			animator.move(body, 0, 0, 0);
			animator.move(right_arm, 0, 0, 0);
			animator.move(left_arm, 0, 0, 0);

			this.rotate(animator, right_hand_thumb, 0, 0, 0);
			this.rotate(animator, right_hand_index_finger, 0, 0, 0);
			this.rotate(animator, right_hand_middle_finger, 0, 0, 0);

			this.rotate(animator, left_hand_thumb,0,0,0);
			this.rotate(animator, left_hand_index_finger,0,0,0);
			this.rotate(animator, left_hand_middle_finger, 0, 0, 0);
			animator.endKeyframe();
		}

        // Melee Attack Animation
		if (draculaSecondForm.getAnimation() == EntityDraculaSecondForm.MELEE_ATTACK_ANIMATION && draculaSecondForm.isEntityAlive()) {
			animator.setAnimation(EntityDraculaSecondForm.MELEE_ATTACK_ANIMATION);

			animator.startKeyframe(30);

			this.rotate(animator, right_hand_thumb, -27.5F, 0, 0);
			this.rotate(animator, right_hand_index_finger, 0, 27.5F, 0);
			this.rotate(animator, right_hand_middle_finger, 27.5F, 0, 0);

			this.rotate(animator, left_hand_thumb, -27.5F, 0, 0);
			this.rotate(animator, left_hand_index_finger, 0, -27.5F, 0);
			this.rotate(animator, left_hand_middle_finger, 27.5F, 0, 0);

			this.rotate(animator, right_arm, -45, 25, 0);
			this.rotate(animator, left_arm, -62.5F, -20, 0);

			animator.endKeyframe();

			animator.startKeyframe(5);
			this.rotate(animator, right_arm, 46.2664F, -51.3183F, -44.0133F);
			this.rotate(animator, left_arm, 27.9804F, 39.9619F, 26.9043F);

			animator.endKeyframe();

			animator.startKeyframe(20);
			this.rotate(animator, right_hand_thumb, 0, 0, 0);
			this.rotate(animator, right_hand_index_finger, 0, 0, 0);
			this.rotate(animator, right_hand_middle_finger, 0, 0, 0);

			this.rotate(animator, left_hand_thumb, 0, 0, 0);
			this.rotate(animator, left_hand_index_finger, 0, 0, 0);
			this.rotate(animator, left_hand_middle_finger, 0, 0, 0);

			this.rotate(animator, right_arm, 0, 0, 0);
			this.rotate(animator, left_arm, 0, 0, 0);

			animator.endKeyframe();
		}

		// Fireballs Attack Animation
		if (draculaSecondForm.getAnimation() == EntityDraculaSecondForm.FIREBALLS_ANIMATION && draculaSecondForm.isEntityAlive()) {
			animator.setAnimation(EntityDraculaSecondForm.FIREBALLS_ANIMATION);

			animator.startKeyframe(20);

			this.rotate(animator, jaw, 35, 0, 0);

			this.rotate(animator, right_hand_thumb, -27.5F, 0, 0);
			this.rotate(animator, right_hand_index_finger, 0, 27.5F, 0);
			this.rotate(animator, right_hand_middle_finger, 27.5F, 0, 0);

			this.rotate(animator, left_hand_thumb,-27.5F,0,0);
			this.rotate(animator, left_hand_index_finger,0,-27.5F,0);
			this.rotate(animator, left_hand_middle_finger, 27.5F, 0, 0);
			animator.endKeyframe();

			animator.setStaticKeyframe(40);

			animator.startKeyframe(20);
			animator.move(head, 0, 0, 0);
			animator.move(body, 0, 0, 0);
			animator.move(right_arm, 0, 0, 0);
			animator.move(left_arm, 0, 0, 0);

			this.rotate(animator, right_hand_thumb, 0, 0, 0);
			this.rotate(animator, right_hand_index_finger, 0, 0, 0);
			this.rotate(animator, right_hand_middle_finger, 0, 0, 0);

			this.rotate(animator, left_hand_thumb,0,0,0);
			this.rotate(animator, left_hand_index_finger,0,0,0);
			this.rotate(animator, left_hand_middle_finger, 0, 0, 0);
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

//		this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//		this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}