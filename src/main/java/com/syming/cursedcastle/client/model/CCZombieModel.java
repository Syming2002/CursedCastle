package com.syming.cursedcastle.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.mob.zombie.CCZombieEntity;
import com.syming.cursedcastle.entity.mob.zombie.FireZombieEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.function.Function;

import static net.minecraft.client.model.AnimationUtils.bobArms;

public class CCZombieModel<T extends CCZombieEntity> extends AdvancedEntityModel<T> {

    protected final AdvancedModelBox zombie;
    private final AdvancedModelBox head;
    private final AdvancedModelBox body;
    public final AdvancedModelBox rightArm;
    public final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox leftLeg;

    public ModelAnimator animator;

    public CCZombieModel() {
        this.texWidth = 64;
        this.texHeight = 32;

        this.zombie = new AdvancedModelBox(this, "zombie");
        this.zombie.setPos(-1.0F, 24.0F, -0.5F);

        this.head = new AdvancedModelBox(this, "head");
        this.head.setPos(1.0F, -28.0F, 1.0F);
        this.zombie.addChild(this.head);
        this.head.setTextureOffset(0, 0)
                .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);

        this.head.setRotationPoint(1.0F, -28.0F, 1.0F);

        this.body = new AdvancedModelBox(this, "body");
        this.body.setPos(1.0F, -18.0F, 1.0F);
        this.zombie.addChild(this.body);
        this.body.setTextureOffset(16, 16)
                .addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F);

        this.rightArm = new AdvancedModelBox(this, "rightArm");
        this.rightArm.setPos(-5.0F, -22.0F, 1.0F);
        this.zombie.addChild(this.rightArm);
        this.rightArm.setTextureOffset(0, 16)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        this.rightArm.rotateAngleX -= 90.0F;

        this.leftArm = new AdvancedModelBox(this, "leftArm");
        this.leftArm.setPos(7.0F, -22.0F, 1.0F);
        this.zombie.addChild(this.leftArm);
        this.leftArm.setTextureOffset(0, 16)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, true);

        this.leftArm.rotateAngleX -= 90.0F;

        this.rightLeg = new AdvancedModelBox(this, "rightLeg");
        this.rightLeg.setPos(-1.0F, -10.0F, 1.0F);
        this.zombie.addChild(this.rightLeg);
        this.rightLeg.setTextureOffset(40, 16)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        this.leftLeg = new AdvancedModelBox(this, "leftLeg");
        this.leftLeg.setPos(3.0F, -10.0F, 1.0F);
        this.zombie.addChild(this.leftLeg);
        this.leftLeg.setTextureOffset(40, 16)
                .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, true);

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.zombie.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.zombie);
    }

    @Override
    public void setupAnim(CCZombieEntity zombie, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        animate(zombie, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        faceTarget(pNetHeadYaw, pHeadPitch, 1.0F, this.head);

        float idleSpeed = 0.09F;
        float idleAmount = 0.05F;

        float f = 1.0F;

        this.rightArm.rotateAngleX += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;
        this.leftArm.rotateAngleX  += Mth.cos(pAgeInTicks * idleSpeed) * idleAmount;

        this.rightArm.rotateAngleZ += Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;
        this.leftArm.rotateAngleZ  -= Mth.sin(pAgeInTicks * idleSpeed) * 0.02F;

        this.rightLeg.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount / f;
        this.leftLeg.rotateAngleX = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount / f;
        this.rightLeg.rotateAngleY = 0.005F;
        this.leftLeg.rotateAngleY = -0.005F;
        this.rightLeg.rotateAngleZ = 0.005F;
        this.leftLeg.rotateAngleZ = -0.005F;
    }

    public void animate(CCZombieEntity zombie, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        resetToDefaultPose();
        this.animator.update(zombie);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(
                this.zombie,
                this.head,
                this.body,
                this.rightArm,
                this.leftArm,
                this.rightLeg,
                this.leftLeg);
    }
}
