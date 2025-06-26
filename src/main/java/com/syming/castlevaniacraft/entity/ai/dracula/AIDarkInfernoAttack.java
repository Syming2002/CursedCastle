package com.syming.castlevaniacraft.entity.ai.dracula;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class AIDarkInfernoAttack extends AnimationAI<EntityDracula> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityDracula dracula;

    public AIDarkInfernoAttack(EntityDracula entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.dracula = entity;
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
        dracula.currentAnim = this;
        attackTarget = dracula.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        dracula.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        Vec3d underLeftArmPosFirstLargeFireball = new Vec3d(0.5, 1.275,0);
        underLeftArmPosFirstLargeFireball = underLeftArmPosFirstLargeFireball.rotateYaw((float) Math.toRadians((-dracula.rotationYaw - 90)));
        underLeftArmPosFirstLargeFireball = underLeftArmPosFirstLargeFireball.add(dracula.getPositionVector());
        underLeftArmPosFirstLargeFireball = underLeftArmPosFirstLargeFireball.add(new Vec3d(0, 0, 0.5).rotatePitch((float) Math.toRadians(-dracula.rotationPitch)).rotateYaw((float) Math.toRadians(-dracula.rotationYawHead)));

        Vec3d underLeftArmPosSecondLargeFireball = new Vec3d(0.5, 0.6375, 0);
        underLeftArmPosSecondLargeFireball = underLeftArmPosSecondLargeFireball.rotateYaw((float) Math.toRadians((-dracula.rotationYaw - 90)));
        underLeftArmPosSecondLargeFireball = underLeftArmPosSecondLargeFireball.add(dracula.getPositionVector());
        underLeftArmPosSecondLargeFireball = underLeftArmPosSecondLargeFireball.add(new Vec3d(0, 0, 0.5).rotatePitch((float) Math.toRadians(-dracula.rotationPitch)).rotateYaw((float) Math.toRadians(-dracula.rotationYawHead)));

        double targetX = this.attackTarget.posX - this.dracula.posX;
        double targetY = this.attackTarget.getEntityBoundingBox().minY + (double) (this.attackTarget.height / 2.0F) - this.dracula.posY;
        double targetZ = this.attackTarget.posZ - this.dracula.posZ;

        float distance = MathHelper.sqrt(targetX * targetX + targetY * targetY + targetZ * targetZ) * 0.5F;

        double directionX = targetX / distance;
        double directionY = targetY / distance;
        double directionZ = targetZ / distance;

        double speed = 0.5;

        if (this.dracula.getAnimationTick() == 40) {
            spawnFirstLargeFireball(
                    underLeftArmPosFirstLargeFireball.x,
                    underLeftArmPosFirstLargeFireball.y,
                    underLeftArmPosFirstLargeFireball.z,
                    directionX,
                    directionY,
                    directionZ,
                    speed);
        }
        if (this.dracula.getAnimationTick() == 60) {
            spawnSecondLargeFireball(
                    underLeftArmPosSecondLargeFireball.x,
                    underLeftArmPosSecondLargeFireball.y,
                    underLeftArmPosSecondLargeFireball.z,
                    directionX,
                    directionY,
                    directionZ,
                    speed);

        }
    }

    private void spawnFirstLargeFireball(double posX, double posY, double posZ, double directionX, double directionY, double directionZ, double speed) {
        if (!dracula.world.isRemote) {
            EntityLargeFireball largeFireball = new EntityLargeFireball(
                    dracula.world,
                    posX,
                    posY,
                    posZ,
                    directionX * speed,
                    directionY * speed,
                    directionZ * speed);
            largeFireball.explosionPower = 0;
            this.dracula.world.spawnEntity(largeFireball);
        }
    }

    private void spawnSecondLargeFireball(double posX, double posY, double posZ, double directionX, double directionY, double directionZ, double speed) {
        if (!dracula.world.isRemote) {
            EntityLargeFireball largeFireball = new EntityLargeFireball(
                    dracula.world,
                    posX,
                    posY,
                    posZ,
                    directionX * speed,
                    directionY * speed,
                    directionZ * speed);
            largeFireball.explosionPower = 0;
            this.dracula.world.spawnEntity(largeFireball);
        }
    }
}
