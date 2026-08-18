package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.monster.shade.ShadeEntity;
import com.syming.cursedcastle.entity.monster.shade.ThunderShadeEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ShadeModel<T extends ShadeEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation SHADE_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "shade"), "main");
    private final ModelPart shade;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public ShadeModel(ModelPart pRoot) {
        this.shade = pRoot.getChild("shade");

        this.head = this.shade.getChild("head");
        this.body = this.shade.getChild("body");
        this.rightArm = this.shade.getChild("rightArm");
        this.leftArm = this.shade.getChild("leftArm");
        this.rightLeg = this.shade.getChild("rightLeg");
        this.leftLeg = this.shade.getChild("leftLeg");    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition shade = mesh.getRoot();

        shade.addOrReplaceChild(
                "shade",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        PartDefinition shadePart = shade.getChild("shade");

        shadePart.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -4.0F, -8.0F, -4.0F,
                                8.0F, 8.0F, 8.0F
                        ),
                PartPose.offset(
                        0.0F, -24.0F, 0.0F
                )
        );

        shadePart.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(14, 16)
                        .addBox(
                                -4.0F, -6.0F, -2.0F,
                                8.0F, 12.0F, 4.0F
                        ),
                PartPose.offset(
                        0.0F, -18.0F, 0.0F
                )
        );

        shadePart.addOrReplaceChild(
                "rightArm",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(
                                -1.5F, -1.5F, -2.0F,
                                3.0F, 12.0F, 4.0F
                        ),
                PartPose.offsetAndRotation(
                        -5.5F, -22.5F, 0.0F,
                        0.0F, 0.0F, 1.5708F
                )
        );

        shadePart.addOrReplaceChild(
                "leftArm",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(
                                -1.5F, -1.5F, -2.0F,
                                3.0F, 12.0F, 4.0F
                        ),
                PartPose.offsetAndRotation(
                        5.5F, -22.5F, 0.0F,
                        0.0F, 0.0F, -1.5708F
                )
        );

        shadePart.addOrReplaceChild(
                "rightLeg",
                CubeListBuilder.create()
                        .texOffs(38, 16)
                        .addBox(
                                -2.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F
                        ),
                PartPose.offset(
                        -2.0F, -10.0F, 0.0F
                )
        );

        shadePart.addOrReplaceChild(
                "leftLeg",
                CubeListBuilder.create()
                        .texOffs(38, 16)
                        .mirror()
                        .addBox(
                                -2.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F
                        ),
                PartPose.offset(
                        2.0F, -10.0F, 0.0F
                )
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = pNetHeadYaw * 0.017453292F;
        this.head.xRot = pHeadPitch * 0.017453292F;

        if (pEntity.getAnimation() != ShadeEntity.REPULSE_ATTACK_ANIMATION && pEntity.isAlive()) {
            float animationSpeed = (float) (2 * Math.PI / 40);
            float wave = (float) Math.sin(pAgeInTicks * animationSpeed);
            float waveAmount = (float) Math.toRadians(5);

            this.rightArm.zRot = 1.5708F + (wave * -waveAmount);
            this.leftArm.zRot = -1.5708F + (wave * waveAmount);
        }

        animate(pEntity.repulseAttackAnimationState, CCAnimation.SHADE_REPULSE_ATTACK_ANIMATION, pAgeInTicks);
        if (pEntity instanceof ThunderShadeEntity thunderShade) {
            animate(thunderShade.lightningBoltAttackAnimationState, CCAnimation.SHADE_LIGHTNING_BOLT_ATTACK_ANIMATION, pAgeInTicks);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.shade.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.shade;
    }
}
