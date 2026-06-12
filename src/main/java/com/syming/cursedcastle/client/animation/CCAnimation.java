package com.syming.cursedcastle.client.animation;

import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class CCAnimation {

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
}
