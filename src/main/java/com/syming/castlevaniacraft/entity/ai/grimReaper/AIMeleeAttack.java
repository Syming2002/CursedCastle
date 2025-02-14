package com.syming.castlevaniacraft.entity.ai.grimReaper;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;

public class AIMeleeAttack extends AnimationAI<EntityGrimReaper> {

    protected Animation animation;
    protected EntityGrimReaper grimReaper;
    protected EntityLivingBase attackTarget;
    protected double targetDistance;


    public AIMeleeAttack(EntityGrimReaper entity, Animation animation, double targetDistance) {
        super(entity);
        setMutexBits(8);
        this.grimReaper = entity;
        this.animation = animation;
        this.targetDistance = targetDistance;
        this.attackTarget = null;
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }

    @Override
    public boolean isAutomatic() {
        return true;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        grimReaper.currentAnim = this;
        attackTarget = grimReaper.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        grimReaper.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        if (attackTarget == null) {
            return;
        }

        double distToEnemySqr = this.grimReaper.getDistanceSq(attackTarget);
        double d0 = this.getAttackReachSqr(attackTarget);

        if (grimReaper.getAnimationTick() == 25 && distToEnemySqr <= d0) {
            this.grimReaper.attackEntityAsMob(attackTarget);
        }

        // Check if the mob is too close to the target
        double minDistanceSq = 2 * 2;
        if (distToEnemySqr < minDistanceSq) {
            // Calculate direction away from the target
            double dX = this.grimReaper.posX - attackTarget.posX;
            double dY = this.grimReaper.posY - attackTarget.posY;
            double dZ = this.grimReaper.posZ - attackTarget.posZ;

            // Normalize the direction vector
            double length = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
            if (length > 0.0D) {
                dX /= length;
                dY /= length;
                dZ /= length;
            }

            // Move the entity away from the target with the target distance
            double targetX = this.grimReaper.posX + dX * this.targetDistance;
            double targetY = this.grimReaper.posY + dY * this.targetDistance;
            double targetZ = this.grimReaper.posZ + dZ * this.targetDistance;

            this.grimReaper.getMoveHelper().setMoveTo(targetX, targetY, targetZ, 1.0F);
        }

    }

    private double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double)(this.grimReaper.width * 2.0F * this.grimReaper.width * 2.0F + attackTarget.width);
    }

}
