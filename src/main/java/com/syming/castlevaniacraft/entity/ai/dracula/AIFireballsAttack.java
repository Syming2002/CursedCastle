package com.syming.castlevaniacraft.entity.ai.dracula;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class AIFireballsAttack extends AnimationAI<EntityDracula> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityDracula dracula;

    public AIFireballsAttack(EntityDracula entity, Animation animation) {
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

        if (dracula.getAnimationTick() == 40 && attackTarget != null) {
        Vec3d underRightArmPosSmallFireballs = new Vec3d(-0.5, 1.275,0);
        underRightArmPosSmallFireballs = underRightArmPosSmallFireballs.rotateYaw((float) Math.toRadians((-dracula.rotationYaw - 90)));
        underRightArmPosSmallFireballs = underRightArmPosSmallFireballs.add(dracula.getPositionVector());
        underRightArmPosSmallFireballs = underRightArmPosSmallFireballs.add(new Vec3d(0, 0, 1.5)
                .rotatePitch((float) Math.toRadians(-dracula.rotationPitch))
                .rotateYaw((float) Math.toRadians(-dracula.rotationYawHead)));

        double targetX = this.attackTarget.posX - this.dracula.posX;
        double targetY = this.attackTarget.getEntityBoundingBox().minY + (double) (this.attackTarget.height / 2.0F) - this.dracula.posY;
        double targetZ = this.attackTarget.posZ - this.dracula.posZ;

        float distance = MathHelper.sqrt(targetX * targetX + targetY * targetY + targetZ * targetZ) * 0.5F;

        double directionX = targetX / distance;
        double directionY = targetY / distance;
        double directionZ = targetZ / distance;

        double speed = 0.5;

            spawnSmallFireball(underRightArmPosSmallFireballs.x, underRightArmPosSmallFireballs.y, underRightArmPosSmallFireballs.z, directionX + 0.2, directionY, directionZ, speed);
            spawnSmallFireball(underRightArmPosSmallFireballs.x, underRightArmPosSmallFireballs.y, underRightArmPosSmallFireballs.z, directionX, directionY, directionZ, speed);
            spawnSmallFireball(underRightArmPosSmallFireballs.x, underRightArmPosSmallFireballs.y, underRightArmPosSmallFireballs.z, directionX - 0.2, directionY, directionZ, speed);
        }
    }

    private void spawnSmallFireball(double posX, double posY, double posZ, double directionX, double directionY, double directionZ, double speed) {
        if (!dracula.world.isRemote) {
            EntitySmallFireball smallFireball = new EntitySmallFireball(
                    dracula.world,
                    posX,
                    posY,
                    posZ,
                    directionX * speed,
                    directionY * speed,
                    directionZ * speed);
            dracula.world.spawnEntity(smallFireball);
        }
    }
}
