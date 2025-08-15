package com.syming.castlevaniacraft.client.model.entity;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import com.syming.castlevaniacraft.item.ItemDeathScythe;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelGrimReaper extends CCModel {

    private final AdvancedModelRenderer head;
    private final AdvancedModelRenderer headWear;
    private final AdvancedModelRenderer body;
    private final AdvancedModelRenderer rightArm;
    private final AdvancedModelRenderer rightArmWear;
    private final AdvancedModelRenderer leftArm;
    private final AdvancedModelRenderer leftArmWear;
    public ArmPose rightArmPose;

    private ModelAnimator animator;

    public ModelGrimReaper()
    {
        this(64, 64);
    }

    public ModelGrimReaper(int textureWidthIn, int textureHeightIn) {
        this.rightArmPose = ArmPose.DEATH_SCYTHE;

        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;

        head = new AdvancedModelRenderer(this);
        head.setRotationPoint(0.0F, -4.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 24, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

        headWear = new AdvancedModelRenderer(this);
        headWear.setRotationPoint(0.0F, -4.0F, 0.0F);
        headWear.cubeList.add(new ModelBox(headWear, 24, 16, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.5F, false));

        body = new AdvancedModelRenderer(this);
        body.setRotationPoint(0.0F, 12.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, -12.0F, -2.0F, 8, 24, 4, 0.0F, false));

        rightArm = new AdvancedModelRenderer(this);
        rightArm.setRotationPoint(-5.0F, 1.5F, 0.0F);
        setRotationAngle(rightArm, -0.4659F, -0.1198F, -0.2333F);
        rightArm.cubeList.add(new ModelBox(rightArm, 0, 28, -1.0F, -1.5F, -1.0F, 2, 12, 2, 0.0F, false));

        rightArmWear = new AdvancedModelRenderer(this);
        rightArmWear.setRotationPoint(-5.0F, 1.5F, 0.0F);
        setRotationAngle(rightArmWear, -0.4659F, -0.1198F, -0.2333F);
        rightArmWear.cubeList.add(new ModelBox(rightArmWear, 8, 28, -1.0F, -1.5F, -1.0F, 2, 12, 2, 0.25F, false));

        leftArm = new AdvancedModelRenderer(this);
        leftArm.setRotationPoint(5.0F, 1.5F, 0.0F);
        setRotationAngle(leftArm, -1.0321F, 0.2261F, 0.1332F);
        leftArm.cubeList.add(new ModelBox(leftArm, 0, 28, -1.0F, -1.5F, -1.0F, 2, 12, 2, 0.0F, true));

        leftArmWear = new AdvancedModelRenderer(this);
        leftArmWear.setRotationPoint(5.0F, 1.5F, 0.0F);
        setRotationAngle(leftArmWear, -1.0321F, 0.2261F, 0.1332F);
        leftArmWear.cubeList.add(new ModelBox(leftArmWear, 8, 28, -1.0F, -1.5F, -1.0F, 2, 12, 2, 0.25F, true));

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        EntityGrimReaper grimReaper = (EntityGrimReaper) entityIn;
        animate(grimReaper, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.head.render(scale);
        this.headWear.render(scale);
        this.body.render(scale);
        this.rightArm.render(scale);
        this.rightArmWear.render(scale);
        this.leftArm.render(scale);
        this.leftArmWear.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    @SuppressWarnings("incomplete-switch")
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        EntityGrimReaper grimReaper = (EntityGrimReaper) entityIn;

        resetToDefaultPose();

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        switch (this.rightArmPose) {
            case EMPTY:
                this.rightArm.rotateAngleY = 0.0F;
                this.rightArmWear.rotateAngleY = 0.0F;
                break;
            case DEATH_SCYTHE:

                this.rightArm.rotateAngleX = -0.4659F;
                this.rightArmWear.rotateAngleX = -0.4659F;
                this.rightArm.rotateAngleY = -0.1198F;
                this.rightArmWear.rotateAngleY = -0.1198F;
                this.rightArm.rotateAngleZ = -0.2333F;
                this.rightArmWear.rotateAngleZ = -0.2333F;

                this.leftArm.rotateAngleX = -1.0321F;
                this.leftArmWear.rotateAngleX = -1.0321F;
                this.leftArm.rotateAngleY = 0.2261F;
                this.leftArmWear.rotateAngleY = 0.2261F;
                this.leftArm.rotateAngleZ = 0.1332F;
                this.leftArmWear.rotateAngleZ = 0.1332F;

                if (grimReaper.getAnimation() != EntityGrimReaper.MELEE_ATTACK_ANIMATION || grimReaper.getAnimation() != EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION) {
                    float waveSpeed = (float) (2 * Math.PI / 80);
                    float waveAmplitude = (float) Math.toRadians(10);
                    float baseAngle = (float) Math.toRadians(-45.0F);

                    float wave = (float) Math.sin(ageInTicks * waveSpeed);
                    float animatedAngle = baseAngle + wave * waveAmplitude;

                    this.rightArm.rotateAngleX = animatedAngle;
                    this.rightArmWear.rotateAngleX = animatedAngle;
                    this.leftArm.rotateAngleX += animatedAngle;
                    this.leftArmWear.rotateAngleX += animatedAngle;
                }
        }

        copyModelAngles(this.head, this.headWear);
    }

    public void postRenderArm(float scale, EnumHandSide side) {
        this.getArmForSide(side).postRender(scale);
    }

    protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.leftArm : this.rightArm;
    }

    protected EnumHandSide getMainHand(Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
        }
        else
        {
            return EnumHandSide.RIGHT;
        }
    }

    public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
        EntityGrimReaper grimReaper = (EntityGrimReaper) entity;
        animator.update(grimReaper);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, grimReaper);

        if (grimReaper.getAnimation() == EntityGrimReaper.MELEE_ATTACK_ANIMATION && grimReaper.isEntityAlive()) {
            animator.setAnimation(EntityGrimReaper.MELEE_ATTACK_ANIMATION);
            animator.startKeyframe(20);
            this.rotate(animator, rightArm, -58.2727F, -42.4019F, -3.4223F);
            this.rotate(animator, rightArmWear, -58.2727F, -42.4019F, -3.4223F);
            this.rotate(animator, leftArm, -83.2805F, -42.2102F, -15.1315F);
            this.rotate(animator, leftArmWear, -83.2805F, -42.2102F, -15.1315F);
            animator.endKeyframe();
            animator.startKeyframe(5);
            this.rotate(animator, rightArm, 25.017F, 9.9853F, 20.5789F);
            this.rotate(animator, rightArmWear, 25.017F, 9.9853F, 20.5789F);
            this.rotate(animator, leftArm, 22.9282F, -15.067F, 39.7721F);
            this.rotate(animator, leftArmWear, 22.9282F, -15.067F, 39.7721F);
            animator.endKeyframe();
            animator.resetKeyframe(10);
        }

        if (grimReaper.getAnimation() == EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION && grimReaper.isEntityAlive()) {
            animator.setAnimation(EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION);
            animator.startKeyframe(20);
            this.rotate(animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);
            animator.setStaticKeyframe(80);
            animator.endKeyframe();

            // Repeat the animation to set the arms animation static during the scythe animation
            this.rotate(animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(80);
            animator.endKeyframe();

            this.rotate(animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(20);
            animator.endKeyframe();

            this.rotate(animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(20);
            animator.endKeyframe();

            this.rotate(animator, rightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, rightArmWear, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, leftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, leftArmWear, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(20);
            animator.endKeyframe();

            animator.resetKeyframe(20);
            this.rotate(animator, rightArm, 0, 0, 0);
            this.rotate(animator, rightArmWear, 0, 0, 0);
            this.rotate(animator, leftArm, 0, 0, 0);
            this.rotate(animator, leftArmWear, 0, 0, 0);
            animator.endKeyframe();

        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        this.rightArmPose = ArmPose.EMPTY;
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);

        if (itemstack.getItem() instanceof ItemDeathScythe)
        {
            if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT)
            {
                this.rightArmPose = ArmPose.DEATH_SCYTHE;
            }
        }

        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
    }

    @SideOnly(Side.CLIENT)
    public static enum ArmPose {
        EMPTY,
        DEATH_SCYTHE;
    }
}
