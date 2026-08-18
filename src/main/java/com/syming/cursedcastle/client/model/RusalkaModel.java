package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.animation.CCAnimation;
import com.syming.cursedcastle.entity.monster.boss.RusalkaEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RusalkaModel extends HierarchicalModel<RusalkaEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation RUSALKA_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "rusalka"), "main");
    private final ModelPart rusalka;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart leftArm;

    public RusalkaModel(ModelPart pRoot) {
        this.rusalka = pRoot.getChild("rusalka");

        this.head = this.rusalka.getChild("head");
        this.body = this.rusalka.getChild("body");
        this.rightArm = this.rusalka.getChild("rightArm");
        this.leftArm = this.rusalka.getChild("leftArm");
        this.rightLeg = this.rusalka.getChild("rightLeg");
        this.leftLeg = this.rusalka.getChild("leftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition rusalka = root.addOrReplaceChild(
                "rusalka",
                CubeListBuilder.create(),
                PartPose.offset(-0.4917F, 24.0F, 0.2357F)
        );

        rusalka.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -3.5083F, -8.0F, -4.2357F,
                                8.0F, 8.0F, 8.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        -0.4917F,
                        -24.0F,
                        1.7357F
                )
        );

        rusalka.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(
                                -2.5083F, -6.0F, -1.7357F,
                                6.0F, 12.0F, 3.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        -0.4917F,
                        -18.0F,
                        1.7357F
                )
        );

        rusalka.addOrReplaceChild(
                "rightArm",
                CubeListBuilder.create()
                        .texOffs(0, 31)
                        .addBox(
                                -1.5F, -1.0F, -1.5F,
                                3.0F, 12.0F, 3.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offsetAndRotation(
                        -4.9917F,
                        -23.0F,
                        1.7357F,
                        -0.3054F,
                        0.4363F,
                        0.0F
                )
        );

        rusalka.addOrReplaceChild(
                "leftArm",
                CubeListBuilder.create()
                        .texOffs(12, 31)
                        .addBox(
                                -1.5F, -1.0F, -1.5F,
                                3.0F, 12.0F, 3.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offsetAndRotation(
                        4.0083F,
                        -23.0F,
                        1.7357F,
                        -0.8727F,
                        -0.4363F,
                        0.0F
                )
        );

        rusalka.addOrReplaceChild(
                "rightLeg",
                CubeListBuilder.create()
                        .texOffs(18, 16)
                        .addBox(
                                -1.0083F, -1.0F, -1.7357F,
                                3.0F, 12.0F, 3.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        -1.9917F,
                        -11.0F,
                        1.7357F
                )
        );

        rusalka.addOrReplaceChild(
                "leftLeg",
                CubeListBuilder.create()
                        .texOffs(30, 16)
                        .addBox(
                                -1.0083F, -1.0F, -1.7357F,
                                3.0F, 12.0F, 3.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        1.0083F,
                        -11.0F,
                        1.7357F
                )
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rusalka.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.rusalka;
    }

    @Override
    public void setupAnim(RusalkaEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        float idleSpeed = 0.09F;
        float idleAmount = 0.05F;

        this.rightArm.xRot += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;
        this.leftArm.xRot  += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;

        this.rightArm.zRot += Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;
        this.leftArm.zRot  -= Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;

        // Melee Attack
        animate(pEntity.meleeAttackAnimationState, CCAnimation.RUSALKA_MELEE_ATTACK_ANIMATION, pAgeInTicks);

        // Water Attack
        animate(pEntity.waterAttackAnimationState, CCAnimation.RUSALKA_WATER_ATTACK_ANIMATION, pAgeInTicks);
    }
}
