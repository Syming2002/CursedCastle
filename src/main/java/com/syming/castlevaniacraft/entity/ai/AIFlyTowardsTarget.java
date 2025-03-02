package com.syming.castlevaniacraft.entity.ai;

import com.syming.castlevaniacraft.entity.CCEntity;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class AIFlyTowardsTarget extends EntityAIBase {

    private final CCEntityMob ccEntityMob;
    private double targetDistance;

    public AIFlyTowardsTarget(CCEntityMob ccEntityMob, double targetDistance) {
        this.ccEntityMob = ccEntityMob;
        this.targetDistance = targetDistance;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        return this.ccEntityMob.getAttackTarget() != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        EntityLivingBase target = this.ccEntityMob.getAttackTarget();
        return target != null && target.isEntityAlive() /*&& this.taskOwner.getDistanceSq(target) > this.targetDistance * this.targetDistance*/;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void updateTask() {
        EntityLivingBase target = this.ccEntityMob.getAttackTarget();
        if (target != null) {
            double distanceSq = this.ccEntityMob.getDistanceSq(target);
            double targetDistanceSq = this.targetDistance * this.targetDistance;

            if (distanceSq > targetDistanceSq) {
                // Calculate direction towards the target
                double dX = target.posX - this.ccEntityMob.posX;
                double dY = target.posY - this.ccEntityMob.posY;
                double dZ = target.posZ - this.ccEntityMob.posZ;

                // Normalize the direction vector
                double length = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
                if (length > 0.0D) {
                    dX /= length;
                    dY /= length;
                    dZ /= length;
                }

                // Move the entity towards the target with the target distance
                double targetX = target.posX - dX * this.targetDistance;
                double targetY = target.posY - dY * this.targetDistance;
                double targetZ = target.posZ - dZ * this.targetDistance;

                this.ccEntityMob.getMoveHelper().setMoveTo(targetX, targetY, targetZ, 0.5F);
            }
        }
    }
}
