package com.syming.cursedcastle.client.animation;

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

}
