package com.syming.cursedcastle.client.animation;

import com.syming.cursedcastle.client.model.CCSkeletonModel;
import com.syming.cursedcastle.client.model.HarpyModel;
import com.syming.cursedcastle.entity.mob.HarpyEntity;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import com.syming.cursedcastle.entity.mob.boss.MedusaEntity;
import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.mob.shade.ShadeEntity;
import com.syming.cursedcastle.entity.mob.shade.ThunderShadeEntity;
import com.syming.cursedcastle.entity.mob.skeleton.CCSkeletonEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class CCAnimation {

    public static final AnimationDefinition ARACHNE_CLAWS_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(2.25F)
            .addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 77.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.125F, KeyframeAnimations.degreeVec(-68.0511F, -34.9621F, 79.2495F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.75F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, -77.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.875F, KeyframeAnimations.degreeVec(-68.0511F, 34.9621F, -79.2495F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition ARACHNE_IDLE_ANIMATION = AnimationDefinition.Builder.withLength(2.0F).looping()
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightFrontLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftFrontLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightFrontLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightFirstMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightFirstMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftFirstMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftFirstMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightSecondMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightSecondMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftSecondMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftSecondMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightBackLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightBackLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftBackLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftBackLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition ARACHNE_WALK_ANIMATION = AnimationDefinition.Builder.withLength(4.0F).looping()
            .addAnimation("rightFrontLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftFrontLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -27.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightFrontLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 40.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftFrontLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightFirstMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightFirstMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 37.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftFirstMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -27.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftFirstMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightSecondMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 37.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightSecondMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftSecondMiddleLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -27.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftSecondMiddleLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightBackLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightBackLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftBackLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -27.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftBackLeg2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(3.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition ARACHNE_WEB_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(2.0F)
            .addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-42.5F, 0.0F, 40.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(-42.5F, 0.0F, 40.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-42.5F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(-42.5F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition CC_SKELETON_THROWING_BONE_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(CCSkeletonEntity.THROWING_BONE_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-102.5F, 27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(-206.4032F, 7.9813F, -74.3751F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.625F, KeyframeAnimations.degreeVec(-86.4F, 7.98F, -74.38F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();


    public static final AnimationDefinition CC_SKELETON_DEATH_ANIMATION = AnimationDefinition.Builder.withLength(CCSkeletonEntity.DEATH_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -24.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, -24.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("headWear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("headWear", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -24.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, -24.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -16.0F, -11.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, -16.0F, -11.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -90.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -90.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(-13.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.posVec(-13.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 90.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 90.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(10.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.posVec(10.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -11.0F, 9.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, -11.0F, 9.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -7.0F, -6.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, -7.0F, -6.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();


    public static final AnimationDefinition DRACULA_IDLE_ANIMATION = AnimationDefinition.Builder.withLength(DraculaEntity.IDLE_ANIMATION_LENGTH_IN_SECONDS).looping()
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.5F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .addAnimation("rightArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.5F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.5F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .addAnimation("leftArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.5F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .build();

    public static final AnimationDefinition DRACULA_FIREBALLS_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(DraculaEntity.FIREBALLS_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("rightArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition DRACULA_DARK_INFERNO_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(DraculaEntity.DARK_INFERNO_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("leftArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition DRACULA_FIRE_PILLAR_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(DraculaEntity.FIRE_PILLAR_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("rightArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("leftArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    // Grim Reaper Animations

    public static final AnimationDefinition GRIM_REAPER_IDLE_ANIMATION = AnimationDefinition.Builder.withLength(GrimReaperEntity.IDLE_ANIMATION_LENGTH_IN_SECONDS).looping()
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .addAnimation("rightArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .addAnimation("leftArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .build();

    public static final AnimationDefinition GRIM_REAPER_FAST_MELEE_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(GrimReaperEntity.FAST_MELEE_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-58.2727F, -42.4019F, -3.4223F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.25F,
                                    KeyframeAnimations.degreeVec(25.017F, 9.9853F, 20.5789F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.75F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("rightArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-58.2727F, -42.4019F, -3.4223F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.25F,
                                    KeyframeAnimations.degreeVec(25.017F, 9.9853F, 20.5789F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.75F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-83.2805F, -42.2102F, -15.1315F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.25F,
                                    KeyframeAnimations.degreeVec(22.9282F, -15.067F, 39.7721F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.75F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )


                    ))
            .addAnimation("leftArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-83.2805F, -42.2102F, -15.1315F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.25F,
                                    KeyframeAnimations.degreeVec(22.9282F, -15.067F, 39.7721F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.75F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    ))
            .build();

    public static final AnimationDefinition GRIM_REAPER_SCYTHE_PROJECTILE_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(GrimReaperEntity.SCYTHE_PROJECTILE_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-60.0079F, 12.4794F, 0.7185F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    12.0F,
                                    KeyframeAnimations.degreeVec(-60.0079F, 12.4794F, 0.7185F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    13.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))

            .addAnimation("rightArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-60.0079F, 12.4794F, 0.7185F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    12.0F,
                                    KeyframeAnimations.degreeVec(-60.0079F, 12.4794F, 0.7185F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    13.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))

            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-50.8617F, -91.9818F, -17.9605F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    12.0F,
                                    KeyframeAnimations.degreeVec(-50.8617F, -91.9818F, -17.9605F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    13.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))

            .addAnimation("leftArmWear",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-50.8617F, -91.9818F, -17.9605F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    12.0F,
                                    KeyframeAnimations.degreeVec(-50.8617F, -91.9818F, -17.9605F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    13.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition HARPY_FEATHER_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(HarpyEntity.FEATHER_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(51.2193380833F, -7.2766554875F, -68.429738455F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.1F,
                                    KeyframeAnimations.degreeVec(-62.35F, 13.66F, -80.95F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.75F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))

            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(51.2193380833F, 7.2766554875F, 68.429738455F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.1F,
                                    KeyframeAnimations.degreeVec(-62.35F, -13.66F, 80.95F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.75F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition HARPY_DAGGER_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(HarpyEntity.DAGGER_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("harpy",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.25F,
                                    KeyframeAnimations.degreeVec(-15.0F, 0, 0),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.6F,
                                    KeyframeAnimations.degreeVec(360.0F, 0, 0),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition MEDUSA_MELEE_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(MedusaEntity.MELEE_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-75.0F, 35.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.25F,
                                    KeyframeAnimations.degreeVec(-8.4724F, -20.5853F, 14.0858F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.25F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.0F,
                                    KeyframeAnimations.degreeVec(-75.0F, -35.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.25F,
                                    KeyframeAnimations.degreeVec(-5.202F, 14.9104F, -10.2241F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.25F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition MEDUSA_STARE_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(MedusaEntity.STARE_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(-95.8959F, 18.177F, -20.9969F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.25F,
                                    KeyframeAnimations.degreeVec(-48.4F, 18.18F, -21.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    5.0F,
                                    KeyframeAnimations.degreeVec(-48.4F, 18.18F, -21.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    6.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(-95.8959F, -18.177F, 20.9969F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.25F,
                                    KeyframeAnimations.degreeVec(-48.4F, -18.18F, -21.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    5.0F,
                                    KeyframeAnimations.degreeVec(-48.4F, -18.18F, -21.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    6.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition RUSALKA_MELEE_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(RusalkaEntity.MELEE_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    0.5F,
                                    KeyframeAnimations.degreeVec(-77.5247185802F, -1.3429457962F, 2.1088648868F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    0.75F,
                                    KeyframeAnimations.degreeVec(17.0167555934F, -1.4754803082F, 94.8174647537F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition RUSALKA_WATER_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(RusalkaEntity.WATER_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.degreeVec(-119.4861479388F, -24.8983739694F, 2.3272993503F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();

    public static final AnimationDefinition SHADE_REPULSE_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(ShadeEntity.REPULSE_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("head",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.posVec(0.0F, -1.0F, -1.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.posVec(0.0F, -1.0F, -1.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    )
            )

            .addAnimation("body",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,

                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.degreeVec(
                                            27.5F,
                                            0.0F,
                                            0.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.degreeVec(
                                            27.5F,
                                            0.0F,
                                            0.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    )
            )

            .addAnimation("body",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,

                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    )
            )

            .addAnimation("rightLeg",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 2.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 2.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    )
            )

            .addAnimation("leftLeg",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 2.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 2.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    )
            )

            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, -1.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, -1.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    )
            )

            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.POSITION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, -1.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, -1.5F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    )
            )

            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.75F,
                                    KeyframeAnimations.degreeVec(
                                            0.0F,
                                            0.0F,
                                            50.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    5.0F,
                                    KeyframeAnimations.degreeVec(
                                            -90.0F,
                                            0.0F,
                                            0.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.degreeVec(
                                            -90.0F,
                                            0.0F,
                                            0.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    )
            )

            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    1.5F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    4.75F,
                                    KeyframeAnimations.degreeVec(
                                            0.0F,
                                            0.0F,
                                            -50.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    5.0F,
                                    KeyframeAnimations.degreeVec(
                                            -90.0F,
                                            0.0F,
                                            0.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    8.0F,
                                    KeyframeAnimations.degreeVec(
                                            -90.0F,
                                            0.0F,
                                            0.0F
                                    ),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    9.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )

                    )
            )
            .build();

    public static final AnimationDefinition SHADE_LIGHTNING_BOLT_ATTACK_ANIMATION = AnimationDefinition.Builder.withLength(ThunderShadeEntity.LIGHTNING_BOLT_ATTACK_ANIMATION_LENGTH_IN_SECONDS)
            .addAnimation("rightArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 50.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 50.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    5.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))

            .addAnimation("leftArm",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(
                                    0.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    2.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -50.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    3.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, -50.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            ),
                            new Keyframe(
                                    5.0F,
                                    KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                    AnimationChannel.Interpolations.LINEAR
                            )
                    ))
            .build();
}
