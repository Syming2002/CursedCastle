package com.syming.castlevaniacraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AIFlyOverTarget extends EntityAIBase {

    private final EntityLiving living;
    private final double speed;
    private EntityLivingBase target;
    private final double hoverHeight;

    public AIFlyOverTarget(EntityLiving living, double hoverHeight, double speed) {
        this.living = living;
        this.speed = speed;
        this.target = living.getAttackTarget();
        this.hoverHeight = hoverHeight;
        this.setMutexBits(8);
    }

    @Override
    public boolean shouldExecute() {
        return this.target != null && this.target.isEntityAlive();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return shouldExecute();
    }

    @Override
    public void updateTask() {
        if (target == null) return;

        double targetX = target.posX;
        double targetY = target.posY + hoverHeight;
        double targetZ = target.posZ;

        living.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);

        double dx = targetX - living.posX;
        double dy = targetY - living.posY;
        double dz = targetZ - living.posZ;

        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        if (distance > 1.0D) {
            living.motionX = dx / distance * speed;
            living.motionY = dy / distance * speed;
            living.motionZ = dz / distance * speed;
        } else {
            living.motionX = 0;
            living.motionY = 0;
            living.motionZ = 0;
        }
    }
}
