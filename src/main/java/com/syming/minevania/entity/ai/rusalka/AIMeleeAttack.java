package com.syming.minevania.entity.ai.rusalka;

import com.syming.minevania.entity.mobs.boss.EntityRusalka;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EnumHand;

public class AIMeleeAttack extends AnimationAI<EntityRusalka> {

    protected Animation animation;
    protected EntityRusalka rusalka;
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


    public AIMeleeAttack(EntityRusalka entity, Animation animation, double speedIn, boolean useLongMemory) {
        super(entity);
        setMutexBits(8);
        this.rusalka = entity;
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
        rusalka.currentAnim = this;
        attackTarget = rusalka.getAttackTarget();
        this.rusalka.getNavigator().setPath(this.path, this.speedTowardsTarget);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        rusalka.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        if (attackTarget == null) {
            return;
        }

        this.rusalka.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);
        double d0 = this.rusalka.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
        --this.delayCounter;

        if ((this.longMemory || this.rusalka.getEntitySenses().canSee(attackTarget)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || attackTarget.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.rusalka.getRNG().nextFloat() < 0.05F))
        {
            this.targetX = attackTarget.posX;
            this.targetY = attackTarget.getEntityBoundingBox().minY;
            this.targetZ = attackTarget.posZ;
            this.delayCounter = 4 + this.rusalka.getRNG().nextInt(7);

            if (this.canPenalize)
            {
                this.delayCounter += failedPathFindingPenalty;
                if (this.rusalka.getNavigator().getPath() != null)
                {
                    PathPoint finalPathPoint = this.rusalka.getNavigator().getPath().getFinalPathPoint();
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

            if (!this.rusalka.getNavigator().tryMoveToEntityLiving(attackTarget, this.speedTowardsTarget))
            {
                this.delayCounter += 15;
            }
        }

        if (rusalka.getAnimationTick() == 25) {
            checkAndPerformAttack(attackTarget, d0);
        }
    }

    private void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr)
    {
        double d0 = this.getAttackReachSqr(enemy);

        if (distToEnemySqr <= d0)
        {
            this.rusalka.swingArm(EnumHand.OFF_HAND);
            this.rusalka.attackEntityAsMob(enemy);
        }
    }

    private double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double)(this.rusalka.width * 2.25F * this.rusalka.width * 2.25F + attackTarget.width);
    }
}
