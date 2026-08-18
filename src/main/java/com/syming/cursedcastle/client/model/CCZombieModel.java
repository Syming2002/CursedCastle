package com.syming.cursedcastle.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.monster.zombie.CCZombieEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CCZombieModel<T extends CCZombieEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation CC_ZOMBIE_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "cc_zombie"), "main");

    protected final ModelPart zombie;
    private final ModelPart head;
    private final ModelPart body;
    public final ModelPart rightArm;
    public final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public CCZombieModel(ModelPart pRoot) {

        this.zombie = pRoot.getChild("zombie");

        this.head = this.zombie.getChild("head");
        this.body = this.zombie.getChild("body");
        this.rightArm = this.zombie.getChild("rightArm");
        this.leftArm = this.zombie.getChild("leftArm");
        this.rightLeg = this.zombie.getChild("rightLeg");
        this.leftLeg = this.zombie.getChild("leftLeg");

    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition zombie = root.addOrReplaceChild(
                "zombie",
                CubeListBuilder.create(),
                PartPose.offset(-1.0F, 24.0F, -0.5F)
        );

        zombie.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -4.0F, -4.0F, -4.0F,
                                8.0F, 8.0F, 8.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        1.0F,
                        -28.0F,
                        1.0F
                )
        );

        zombie.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(
                                -4.0F, -6.0F, -2.0F,
                                8.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        1.0F,
                        -18.0F,
                        1.0F
                )
        );

        zombie.addOrReplaceChild(
                "rightArm",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(
                                -2.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offsetAndRotation(
                        -5.0F,
                        -22.0F,
                        1.0F,
                        (float) Math.toRadians(-90.0F),
                        0.0F,
                        0.0F
                )
        );

        zombie.addOrReplaceChild(
                "leftArm",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(
                                -2.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offsetAndRotation(
                        7.0F,
                        -22.0F,
                        1.0F,
                        (float) Math.toRadians(-90.0F),
                        0.0F,
                        0.0F
                )
        );

        zombie.addOrReplaceChild(
                "rightLeg",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(
                                -2.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        -1.0F,
                        -10.0F,
                        1.0F
                )
        );

        zombie.addOrReplaceChild(
                "leftLeg",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .mirror()
                        .addBox(
                                -2.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.0F)
                        ),
                PartPose.offset(
                        3.0F,
                        -10.0F,
                        1.0F
                )
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.zombie.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.zombie;
    }

    @Override
    public void setupAnim(CCZombieEntity zombie, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        float idleSpeed = 0.09F;
        float idleAmount = 0.05F;

        float f = 1.0F;

        this.rightArm.xRot += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;
        this.leftArm.xRot  += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;

        this.rightArm.zRot += Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;
        this.leftArm.zRot  -= Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;

        this.rightLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount / f;
        this.leftLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount / f;
        this.rightLeg.yRot = 0.005F;
        this.leftLeg.yRot = -0.005F;
        this.rightLeg.zRot = 0.005F;
        this.leftLeg.zRot = -0.005F;
    }
}
