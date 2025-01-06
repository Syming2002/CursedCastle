package com.syming.minevania.entity.ai.flameDemon;

import com.syming.minevania.entity.mobs.EntityFlameDemon;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;

public class AIMeleeAttack extends AnimationAI<EntityFlameDemon> {

    protected Animation animation;
    protected EntityFlameDemon flameDemon;
    protected EntityLivingBase attackTarget;


    public AIMeleeAttack(EntityFlameDemon entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.flameDemon = entity;
        this.animation = animation;
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
        flameDemon.currentAnim = this;
        attackTarget = flameDemon.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        flameDemon.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        if (attackTarget == null) {
            return;
        }

        double distToEnemySqr = this.flameDemon.getDistanceSq(attackTarget);
        double d0 = this.getAttackReachSqr(attackTarget);

        if (distToEnemySqr <= d0 && flameDemon.getAnimationTick() == 44) {
            this.flameDemon.attackEntityAsMob(attackTarget);
        }
    }

    private double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double)(this.flameDemon.width * 2.0F * this.flameDemon.width * 2.0F + attackTarget.width);
    }
}
