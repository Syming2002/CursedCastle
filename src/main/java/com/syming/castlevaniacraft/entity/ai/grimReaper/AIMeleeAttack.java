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
        if (this.attackTarget == null) {
            return;
        }

        double distToEnemySqr = this.grimReaper.getDistanceSq(this.attackTarget);
        double attackReachSqr = this.getAttackReachSqr(this.attackTarget);
        int animationTick = this.grimReaper.getAnimationTick();

        if (animationTick < 25) {
            this.grimReaper.getNavigator().tryMoveToEntityLiving(this.attackTarget, 50.0D);
        }

        if (animationTick == 25 && distToEnemySqr <= attackReachSqr) {
            this.grimReaper.attackEntityAsMob(this.attackTarget);
        }

        if (animationTick > 25) {
            double minDistanceSq = 4.0D;
            if (distToEnemySqr < minDistanceSq) {
                double x = this.grimReaper.posX - this.attackTarget.posX;
                double y = this.grimReaper.posY - this.attackTarget.posY;
                double z = this.grimReaper.posZ - this.attackTarget.posZ;

                double length = Math.sqrt(x * x + y * y + z * z);
                if (length > 0.0D) {
                    x /= length;
                    y /= length;
                    z /= length;
                }

                double retreatX = this.grimReaper.posX + x * this.targetDistance;
                double retreatY = this.grimReaper.posY + y * this.targetDistance;
                double retreatZ = this.grimReaper.posZ + z * this.targetDistance;

                this.grimReaper.getMoveHelper().setMoveTo(retreatX, retreatY, retreatZ, 1.0F);
            }
        }

    }

    private double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double)(this.grimReaper.width * 2.0F * this.grimReaper.width * 2.0F + attackTarget.width);
    }

}
