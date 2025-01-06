package com.syming.minevania.entity.ai.medusa;

import com.syming.minevania.entity.mobs.boss.EntityMedusa;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;

public class AIMeleeAttack extends AnimationAI<EntityMedusa> {

    protected Animation animation;
    protected EntityMedusa medusa;
    protected EntityLivingBase attackTarget;
    /** The speed with which the mob will approach the target */
    double speedTowardsTarget;
    /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
    boolean longMemory;
    /** The PathEntity of our entity. */
    Path path;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int delayCounter;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;


    public AIMeleeAttack(EntityMedusa entity, Animation animation, double speedIn, boolean useLongMemory) {
        super(entity);
        setMutexBits(8);
        this.medusa = entity;
        this.animation = animation;
        this.attackTarget = null;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
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
        medusa.currentAnim = this;
        attackTarget = medusa.getAttackTarget();
        this.medusa.getNavigator().setPath(this.path, this.speedTowardsTarget);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        medusa.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        if (attackTarget == null) {
            return;
        }

        this.medusa.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);
        double d0 = this.medusa.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
        --this.delayCounter;

        if ((this.longMemory || this.medusa.getEntitySenses().canSee(attackTarget)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || attackTarget.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.medusa.getRNG().nextFloat() < 0.05F))
        {
            this.targetX = attackTarget.posX;
            this.targetY = attackTarget.getEntityBoundingBox().minY;
            this.targetZ = attackTarget.posZ;
            this.delayCounter = 4 + this.medusa.getRNG().nextInt(7);

            if (this.canPenalize)
            {
                this.delayCounter += failedPathFindingPenalty;
                if (this.medusa.getNavigator().getPath() != null)
                {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.medusa.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && attackTarget.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                }
                else
                {
                    failedPathFindingPenalty += 10;
                }
            }

            if (d0 > 1024.0D)
            {
                this.delayCounter += 10;
            }
            else if (d0 > 256.0D)
            {
                this.delayCounter += 5;
            }

            if (!this.medusa.getNavigator().tryMoveToEntityLiving(attackTarget, this.speedTowardsTarget))
            {
                this.delayCounter += 15;
            }
        }

        if (medusa.getAnimationTick() == 25) {
            checkAndPerformAttack(attackTarget, d0);
        }
    }

    private void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr)
    {
        double d0 = this.getAttackReachSqr(enemy);

        if (distToEnemySqr <= d0)
        {
            this.medusa.swingArm(EnumHand.MAIN_HAND);
            this.medusa.swingArm(EnumHand.OFF_HAND);
            this.medusa.attackEntityAsMob(enemy);
        }
    }

    private double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double)(this.medusa.width * 1.5F * this.medusa.width * 1.5F + attackTarget.width);
    }
}
