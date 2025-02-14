package com.syming.castlevaniacraft.entity.ai.draculaSecondForm;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaSecondForm;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;

public class AIMeleeAttack extends AnimationAI<EntityDraculaSecondForm> {

    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityDraculaSecondForm draculaSecondForm;

    public AIMeleeAttack(EntityDraculaSecondForm entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.draculaSecondForm = entity;
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
        draculaSecondForm.currentAnim = this;
        attackTarget = draculaSecondForm.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        draculaSecondForm.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        double d0 = this.draculaSecondForm.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);

        if (attackTarget != null) {
            if (draculaSecondForm.getAnimationTick() == 35) {
                checkAndPerformAttack(attackTarget, d0);
//                System.out.println("Hit");
            }
            this.draculaSecondForm.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30, 30);
        }
    }

    protected void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr)
    {
        double d0 = this.getAttackReachSqr(enemy);

        if (distToEnemySqr <= d0 && this.draculaSecondForm.getAnimationTick() == 35) {
            this.draculaSecondForm.attackEntityAsMob(enemy);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return (double)(this.draculaSecondForm.width * 2.0F * this.draculaSecondForm.width * 2.0F + attackTarget.width);
    }
}
