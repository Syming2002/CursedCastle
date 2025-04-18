package com.syming.castlevaniacraft.client.model.entity;

import com.syming.castlevaniacraft.entity.mobs.EntityBloodSkeleton;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBloodSkeleton extends CCModel {

    private final AdvancedModelRenderer model_core;
    private final AdvancedModelRenderer head;
    private final AdvancedModelRenderer headwear;
    private final AdvancedModelRenderer body;
    private final AdvancedModelRenderer right_arm;
    private final AdvancedModelRenderer left_arm;
    private final AdvancedModelRenderer right_leg;
    private final AdvancedModelRenderer left_leg;
    private ModelAnimator animator;
    public ModelBloodSkeleton()
    {
        textureWidth = 64;
        textureHeight = 64;

        model_core = new AdvancedModelRenderer(this);
        model_core.setRotationPoint(0.0F, 24.0F, 0.0F);


        head = new AdvancedModelRenderer(this);
        head.setRotationPoint(0.0F, -28.0F, 0.0F);
        model_core.addChild(head);
        head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

        headwear = new AdvancedModelRenderer(this);
        headwear.setRotationPoint(0.0F, -26.0F, 0.0F);
        model_core.addChild(headwear);
        headwear.cubeList.add(new ModelBox(headwear, 0, 16, -4.0F, -6.0F, -4.0F, 8, 8, 8, 0.5F, false));

        body = new AdvancedModelRenderer(this);
        body.setRotationPoint(0.0F, -24.0F, 0.0F);
        model_core.addChild(body);
        body.cubeList.add(new ModelBox(body, 0, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false));

        right_arm = new AdvancedModelRenderer(this);
        right_arm.setRotationPoint(-4.0F, -22.0F, 0.0F);
        model_core.addChild(right_arm);
        right_arm.cubeList.add(new ModelBox(right_arm, 24, 32, -2.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F, false));

        left_arm = new AdvancedModelRenderer(this);
        left_arm.setRotationPoint(5.0F, -22.0F, 0.0F);
        model_core.addChild(left_arm);
        left_arm.cubeList.add(new ModelBox(left_arm, 32, 28, -1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F, true));

        right_leg = new AdvancedModelRenderer(this);
        right_leg.setRotationPoint(-2.0F, -12.0F, 0.0F);
        model_core.addChild(right_leg);
        right_leg.cubeList.add(new ModelBox(right_leg, 32, 0, -1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F, false));

        left_leg = new AdvancedModelRenderer(this);
        left_leg.setRotationPoint(2.0F, -12.0F, 0.0F);
        model_core.addChild(left_leg);
        left_leg.cubeList.add(new ModelBox(left_leg, 32, 14, -1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F, true));

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
        EntityBloodSkeleton bloodSkeleton = (EntityBloodSkeleton) entity;
        animate(bloodSkeleton, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
        model_core.render(scale);
    }

    public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
        EntityBloodSkeleton bloodSkeleton = (EntityBloodSkeleton) entity;
        animator.update(bloodSkeleton);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, bloodSkeleton);

        // Death Animation
        if (bloodSkeleton.getAnimation() == EntityBloodSkeleton.DEATH_ANIMATION) {
            animator.setAnimation(EntityBloodSkeleton.DEATH_ANIMATION);

            animator.startKeyframe(15);
            this.rotate(animator, head, 270, 0, 0);
            animator.move(head, 0, 24, -17);
            this.rotate(animator, headwear, 270, 0, 0);
            animator.move(headwear, 0, 22, -19);
            this.rotate(animator, body, -90, 0, 0);
            animator.move(body, 0, 22, 15);
            this.rotate(animator, right_arm, 0, 0, -90);
            animator.move(right_arm, -11, 20, 0);
            this.rotate(animator, left_arm, 0, 0, 90);
            animator.move(left_arm, 10, 21, 0);
            this.rotate(animator, right_leg, 90, 0, 0);
            animator.move(right_leg, 0, 11, -10);
            this.rotate(animator, left_leg, -68.25F, 0, 0);
            animator.move(left_leg, 0, 11, -10);
            animator.endKeyframe();
            animator.setStaticKeyframe(25);
            animator.startKeyframe(15);
            this.rotate(animator, head, 0, 0, 0);
            animator.move(head, 0, 0, 0);
            this.rotate(animator, headwear, 0, 0, 0);
            animator.move(headwear, 0, 0, 0);
            this.rotate(animator, body, 0, 0, 0);
            animator.move(body, 0, 0, 0);
            this.rotate(animator, right_arm, 0, 0, 0);
            animator.move(right_arm, 0, 0, 0);
            this.rotate(animator, left_arm, 0, 0, 90);
            animator.move(left_arm, 0, 0, 0);
            this.rotate(animator, right_leg, 0, 0, 0);
            animator.move(right_leg, 0, 0, 0);
            this.rotate(animator, left_leg, 0, 0, 0);
            animator.move(left_leg, 0, 0, 0);
            animator.endKeyframe();
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        resetToDefaultPose();

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.headwear.rotateAngleY = netHeadYaw * 0.017453292F;
        this.headwear.rotateAngleX = headPitch * 0.017453292F;

        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    }
}
