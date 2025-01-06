package com.syming.minevania.entity.ai.grimReaper;

import com.syming.minevania.entity.mobs.boss.EntityGrimReaper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class AIFlyTowardsTarget extends EntityAIBase {

    private final EntityGrimReaper grimReaper;
    private double targetDistance;

    public AIFlyTowardsTarget(EntityGrimReaper grimReaper, double targetDistance) {
        this.grimReaper = grimReaper;
        this.targetDistance = targetDistance;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        return this.grimReaper.getAttackTarget() != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        EntityLivingBase target = this.grimReaper.getAttackTarget();
        return target != null && target.isEntityAlive() /*&& this.taskOwner.getDistanceSq(target) > this.targetDistance * this.targetDistance*/;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void updateTask() {
        EntityLivingBase target = this.grimReaper.getAttackTarget();
        if (target != null) {
            double distanceSq = this.grimReaper.getDistanceSq(target);
            double targetDistanceSq = this.targetDistance * this.targetDistance;

            if (distanceSq > targetDistanceSq) {
                // Calculate direction towards the target
                double dX = target.posX - this.grimReaper.posX;
                double dY = target.posY - this.grimReaper.posY;
                double dZ = target.posZ - this.grimReaper.posZ;

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

                this.grimReaper.getMoveHelper().setMoveTo(targetX, targetY, targetZ, 1.75F);
            }
        }
    }
}
