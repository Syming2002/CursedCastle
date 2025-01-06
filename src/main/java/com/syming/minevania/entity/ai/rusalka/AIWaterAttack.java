package com.syming.minevania.entity.ai.rusalka;

import com.syming.minevania.entity.mobs.boss.EntityRusalka;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;

public class AIWaterAttack extends AnimationAI<EntityRusalka> {

    protected Animation animation;
    protected EntityRusalka rusalka;
    protected EntityLivingBase attackTarget;

    private BlockPos currentWaterPos;
    private int waterTicks = 0;

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


    public AIWaterAttack(EntityRusalka entity, Animation animation, double speedIn, boolean useLongMemory) {
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
        currentWaterPos = null;
        this.rusalka.getNavigator().setPath(this.path, this.speedTowardsTarget);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        rusalka.currentAnim = null;

        if (currentWaterPos != null && rusalka.world.getBlockState(currentWaterPos).getMaterial() == Material.WATER) {
            rusalka.world.setBlockState(currentWaterPos, Blocks.AIR.getDefaultState(), 3);
        }
        currentWaterPos = null;
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

        if (waterTicks > 0) {
            waterTicks--;
            if (waterTicks == 0 && currentWaterPos != null) {
                removeWaterBlock();
            }
        }

        if (rusalka.getAnimationTick() == 40) {
            spawnWaterBlock();
        }
    }

    private void spawnWaterBlock() {
        if (!rusalka.world.isRemote && attackTarget != null) {
            if (currentWaterPos != null) {
                removeWaterBlock();
            }

            currentWaterPos = new BlockPos(attackTarget.posX, attackTarget.posY + attackTarget.height + 2.0F, attackTarget.posZ);

            rusalka.world.setBlockState(currentWaterPos,Blocks.FLOWING_WATER.getDefaultState(), 3);

            waterTicks = 60;
        }
    }

    private void removeWaterBlock() {
        if (currentWaterPos != null && rusalka.world.getBlockState(currentWaterPos).getMaterial() == Material.WATER) {
            rusalka.world.setBlockState(currentWaterPos, Blocks.AIR.getDefaultState(), 3);
        }

        currentWaterPos = null;
    }
}
