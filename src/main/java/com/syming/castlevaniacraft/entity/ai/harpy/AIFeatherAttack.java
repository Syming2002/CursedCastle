package com.syming.castlevaniacraft.entity.ai.harpy;

import com.syming.castlevaniacraft.entity.mobs.EntityHarpy;
import com.syming.castlevaniacraft.entity.projectiles.EntityHarpyFeather;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AIFeatherAttack extends AnimationAI<EntityHarpy> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityHarpy harpy;

    public AIFeatherAttack(EntityHarpy entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.harpy = entity;
        this.animation = animation;
        this.attackTarget = null;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
    }

    @Override
    public boolean isAutomatic() {
        return true;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.harpy.currentAnim = this;
        attackTarget = this.harpy.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.harpy.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        this.harpy.faceEntity(this.attackTarget, 30.0F, 30.0F);

        if (attackTarget != null && this.harpy.getAnimationTick() == 22) {
            Vec3d harpyFeatherVec = new Vec3d(0, 0, 1.0F);
            harpyFeatherVec = harpyFeatherVec.rotatePitch((float) Math.toRadians(-this.harpy.rotationPitch));
            harpyFeatherVec = harpyFeatherVec.rotateYaw((float) Math.toRadians(-this.harpy.rotationYaw));
            harpyFeatherVec = harpyFeatherVec.add(this.harpy.posX, this.harpy.posY + this.harpy.getEyeHeight() / 1.25F, this.harpy.posZ);

            launchHarpyFeather(harpyFeatherVec.x, harpyFeatherVec.y, harpyFeatherVec.z, this.harpy.rotationPitch, this.harpy.rotationYaw);
        }
    }

//    private void spawnLeftEccentricHarpyFeather(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float speed, float rotationPitch, float rotationYaw) {
//        if (!this.harpy.world.isRemote) {
//            this.leftHarpyFeather = new EntityHarpyFeather(this.harpy.world, this.harpy, motionX * speed, motionY * speed, motionZ * speed);
//            leftHarpyFeather.setPosition(posX, posY, posZ);
//            leftHarpyFeather.rotationPitch = rotationPitch;
//            leftHarpyFeather.rotationYaw = rotationYaw;
//            this.harpy.world.spawnEntity(leftHarpyFeather);
//        }
//    }
//
//    private void spawnMiddleHarpyFeather(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float speed) {
//        if (!this.harpy.world.isRemote) {
//            this.middleHarpyFeather = new EntityHarpyFeather(this.harpy.world, this.harpy, motionX * speed, motionY * speed, motionZ * speed);
//            middleHarpyFeather.setPosition(posX, posY, posZ);
//            this.harpy.world.spawnEntity(middleHarpyFeather);
//        }
//    }
//
//    private void spawnRightEccentricHarpyFeather(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float speed, float rotationPitch, float rotationYaw) {
//        if (!this.harpy.world.isRemote) {
//            this.rightHarpyFeather = new EntityHarpyFeather(this.harpy.world, this.harpy, motionX * speed, motionY * speed, motionZ * speed);
//            rightHarpyFeather.setPosition(posX, posY, posZ);
//            rightHarpyFeather.rotationPitch = rotationPitch;
//            rightHarpyFeather.rotationYaw = rotationYaw;
//            this.harpy.world.spawnEntity(rightHarpyFeather);
//        }
//    }

    private void launchHarpyFeather(double posX, double posY, double posZ, float rotationPitch, float rotationYaw) {
        if (!this.harpy.world.isRemote) {
            double speed = 1.0D; // Speed of the feathers

            double motionX = -Math.sin(Math.toRadians(rotationYaw)) * Math.cos(Math.toRadians(rotationPitch)) * speed;
            double motionY = -Math.sin(Math.toRadians(rotationPitch)) * speed;
            double motionZ = Math.cos(Math.toRadians(rotationYaw)) * Math.cos(Math.toRadians(rotationPitch)) * speed;

            EntityHarpyFeather middleFeather = new EntityHarpyFeather(this.harpy.world, this.harpy, motionX, motionY, motionZ, rotationPitch, rotationYaw);
            middleFeather.setPosition(posX, posY, posZ);
            this.harpy.world.spawnEntity(middleFeather);


            float spreadAngle = 15F;

            float leftYaw = rotationYaw - spreadAngle;
            double leftMotionX = -Math.sin(Math.toRadians(leftYaw)) * Math.cos(Math.toRadians(rotationPitch)) * speed;
            double leftMotionZ = Math.cos(Math.toRadians(leftYaw)) * Math.cos(Math.toRadians(rotationPitch)) * speed;

            EntityHarpyFeather leftFeather = new EntityHarpyFeather(this.harpy.world, this.harpy, leftMotionX, motionY, leftMotionZ, rotationPitch, leftYaw);
            leftFeather.setPosition(posX, posY, posZ);
            this.harpy.world.spawnEntity(leftFeather);

            float rightYaw = rotationYaw + spreadAngle;
            double rightMotionX = -Math.sin(Math.toRadians(rightYaw)) * Math.cos(Math.toRadians(rotationPitch)) * speed;
            double rightMotionZ = Math.cos(Math.toRadians(rightYaw)) * Math.cos(Math.toRadians(rotationPitch)) * speed;

            EntityHarpyFeather rightFeather = new EntityHarpyFeather(this.harpy.world, this.harpy, rightMotionX, motionY, rightMotionZ, rotationPitch, rightYaw);
            rightFeather.setPosition(posX, posY, posZ);
            this.harpy.world.spawnEntity(rightFeather);
        }
    }

//    private double getHarpyFeatherX(int p_82214_1_)
//    {
//        if (p_82214_1_ <= 0)
//        {
//            return this.harpy.posX;
//        }
//        else
//        {
//            float f = (this.harpy.renderYawOffset + (float)(180 * (p_82214_1_ - 1))) * 0.017453292F;
//            float f1 = MathHelper.cos(f);
//            return this.harpy.posX + (double)f1 * 0.75D;
//        }
//    }
//
//    private double getHarpyFeatherY(int p_82208_1_)
//    {
//        return p_82208_1_ <= 0 ? this.harpy.posY + this.harpy.getEyeHeight() : this.harpy.posY + 2.2D;
//    }
//
//    private double getHarpyFeatherZ(int p_82213_1_)
//    {
//        if (p_82213_1_ <= 0)
//        {
//            return this.harpy.posZ;
//        }
//        else
//        {
//            float f = (this.harpy.renderYawOffset + (float)(180 * (p_82213_1_ - 1))) * 0.017453292F;
//            float f1 = MathHelper.sin(f);
//            return this.harpy.posZ + (double)f1 * 0.75D;
//        }
//    }
}
