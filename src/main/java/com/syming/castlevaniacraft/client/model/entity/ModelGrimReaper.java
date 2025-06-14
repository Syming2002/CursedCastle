package com.syming.castlevaniacraft.client.model.entity;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import com.syming.castlevaniacraft.item.ItemDeathScythe;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
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
    private final AdvancedModelRenderer bodyWear;
    private final AdvancedModelRenderer humanoidRightArm;
    private final AdvancedModelRenderer humanoidLeftArm;
    private final AdvancedModelRenderer humanoidRightArmSleeve;
    private final AdvancedModelRenderer humanoidLeftArmSleeve;
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
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 32, 28, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));

        headWear = new AdvancedModelRenderer(this);
        headWear.setRotationPoint(0.0F, 0.0F, 0.0F);
        headWear.cubeList.add(new ModelBox(headWear, 0, 28, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false));

        body = new AdvancedModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, 0.0F, -2.0F, 8, 24, 4, 0.0F, false));

        bodyWear = new AdvancedModelRenderer(this);
        bodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
        bodyWear.cubeList.add(new ModelBox(bodyWear, 24, 0, -4.0F, 0.0F, -2.0F, 8, 24, 4, 0.25F, false));

        humanoidRightArm = new AdvancedModelRenderer(this);
        humanoidRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        humanoidRightArm.cubeList.add(new ModelBox(humanoidRightArm, 0, 44, -3.0F, -1.5F, -1.0F, 4, 12, 4, 0.0F, false));

        humanoidLeftArm = new AdvancedModelRenderer(this);
        humanoidLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        humanoidLeftArm.cubeList.add(new ModelBox(humanoidLeftArm, 16, 44, -1.0F, -1.5F, -1.0F, 4, 12, 4, 0.0F, false));

        humanoidRightArmSleeve = new AdvancedModelRenderer(this);
        humanoidRightArmSleeve.setRotationPoint(-5.0F, 2.0F, 0.0F);
        humanoidRightArmSleeve.cubeList.add(new ModelBox(humanoidRightArmSleeve, 48, 0, -3.0F, -1.5F, -1.0F, 4, 12, 4, 0.0625F, false));

        humanoidLeftArmSleeve = new AdvancedModelRenderer(this);
        humanoidLeftArmSleeve.setRotationPoint(5.0F, 2.0F, 0.0F);
        humanoidLeftArmSleeve.cubeList.add(new ModelBox(humanoidLeftArmSleeve, 32, 44, -1.0F, -1.5F, -1.0F, 4, 12, 4, 0.0625F, false));

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
        this.bodyWear.render(scale);
        this.humanoidRightArm.render(scale);
        this.humanoidRightArmSleeve.render(scale);
        this.humanoidLeftArm.render(scale);
        this.humanoidLeftArmSleeve.render(scale);
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
                this.humanoidRightArm.rotateAngleY = 0.0F;
                this.humanoidRightArmSleeve.rotateAngleY = 0.0F;
                break;
            case DEATH_SCYTHE:

                this.humanoidRightArm.rotateAngleX = -0.4659F;
                this.humanoidRightArmSleeve.rotateAngleX = -0.4659F;
                this.humanoidRightArm.rotateAngleY = -0.1198F;
                this.humanoidRightArmSleeve.rotateAngleY = -0.1198F;
                this.humanoidRightArm.rotateAngleZ = -0.2333F;
                this.humanoidRightArmSleeve.rotateAngleZ = -0.2333F;

                this.humanoidLeftArm.rotateAngleX = -1.0321F;
                this.humanoidLeftArmSleeve.rotateAngleX = -1.0321F;
                this.humanoidLeftArm.rotateAngleY = 0.2261F;
                this.humanoidLeftArmSleeve.rotateAngleY = 0.2261F;
                this.humanoidLeftArm.rotateAngleZ = 0.1332F;
                this.humanoidLeftArmSleeve.rotateAngleZ = 0.1332F;

                if (grimReaper.getAnimation() != EntityGrimReaper.MELEE_ATTACK_ANIMATION || grimReaper.getAnimation() != EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION) {
                    float waveSpeed = (float) (2 * Math.PI / 80);
                    float waveAmplitude = (float) Math.toRadians(10);
                    float baseAngle = (float) Math.toRadians(-45.0F);

                    float wave = (float) Math.sin(ageInTicks * waveSpeed);
                    float animatedAngle = baseAngle + wave * waveAmplitude;

                    this.humanoidRightArm.rotateAngleX = animatedAngle;
                    this.humanoidRightArmSleeve.rotateAngleX = animatedAngle;
                    this.humanoidLeftArm.rotateAngleX += animatedAngle;
                    this.humanoidLeftArmSleeve.rotateAngleX += animatedAngle;
                }
        }

        copyModelAngles(this.head, this.headWear);
    }

    public void postRenderArm(float scale, EnumHandSide side) {
        this.getArmForSide(side).postRender(scale);
    }

    protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.humanoidLeftArm : this.humanoidRightArm;
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
            this.rotate(animator, humanoidRightArm, -58.2727F, -42.4019F, -3.4223F);
            this.rotate(animator, humanoidRightArmSleeve, -58.2727F, -42.4019F, -3.4223F);
            this.rotate(animator, humanoidLeftArm, -83.2805F, -42.2102F, -15.1315F);
            this.rotate(animator, humanoidLeftArmSleeve, -83.2805F, -42.2102F, -15.1315F);
            animator.endKeyframe();
            animator.startKeyframe(5);
            this.rotate(animator, humanoidRightArm, 25.017F, 9.9853F, 20.5789F);
            this.rotate(animator, humanoidRightArmSleeve, 25.017F, 9.9853F, 20.5789F);
            this.rotate(animator, humanoidLeftArm, 22.9282F, -15.067F, 39.7721F);
            this.rotate(animator, humanoidLeftArmSleeve, 22.9282F, -15.067F, 39.7721F);
            animator.endKeyframe();
            animator.resetKeyframe(10);
        }

        if (grimReaper.getAnimation() == EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION && grimReaper.isEntityAlive()) {
            animator.setAnimation(EntityGrimReaper.SCYTHE_PROJECTILE_ATTACK_ANIMATION);
            animator.startKeyframe(20);
            this.rotate(animator, humanoidRightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidRightArmSleeve, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidLeftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, humanoidLeftArmSleeve, -50.8617F, -91.9818F, -17.9605F);
            animator.setStaticKeyframe(80);
            animator.endKeyframe();

            // Repeat the animation to set the arms animation static during the scythe animation
            this.rotate(animator, humanoidRightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidRightArmSleeve, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidLeftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, humanoidLeftArmSleeve, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(80);
            animator.endKeyframe();

            this.rotate(animator, humanoidRightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidRightArmSleeve, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidLeftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, humanoidLeftArmSleeve, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(20);
            animator.endKeyframe();

            this.rotate(animator, humanoidRightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidRightArmSleeve, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidLeftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, humanoidLeftArmSleeve, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(20);
            animator.endKeyframe();

            this.rotate(animator, humanoidRightArm, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidRightArmSleeve, -60.0079F, 12.4794F, 0.7185F);
            this.rotate(animator, humanoidLeftArm, -50.8617F, -91.9818F, -17.9605F);
            this.rotate(animator, humanoidLeftArmSleeve, -50.8617F, -91.9818F, -17.9605F);

            animator.startKeyframe(20);
            animator.endKeyframe();

            animator.resetKeyframe(20);
            this.rotate(animator, humanoidRightArm, 0, 0, 0);
            this.rotate(animator, humanoidRightArmSleeve, 0, 0, 0);
            this.rotate(animator, humanoidLeftArm, 0, 0, 0);
            this.rotate(animator, humanoidLeftArmSleeve, 0, 0, 0);
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
