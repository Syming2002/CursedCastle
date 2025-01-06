package com.syming.minevania.entity.ai.grimReaper;

import com.syming.minevania.entity.mobs.boss.EntityGrimReaper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;

import java.util.Random;

public class AIRandomFly extends EntityAIBase {

    private final EntityGrimReaper grimReaper;

    public AIRandomFly(EntityGrimReaper grimReaper) {
        this.grimReaper = grimReaper;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (grimReaper.getAttackTarget() != null)
            return false;

        EntityMoveHelper entityMoveHelper = this.grimReaper.getMoveHelper();
        double d0 = entityMoveHelper.getX() - this.grimReaper.posX;
        double d1 = entityMoveHelper.getY() - this.grimReaper.posY;
        double d2 = entityMoveHelper.getZ() - this.grimReaper.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        return d3 < 1.0D || d3 > 3600.0D;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        Random random = this.grimReaper.getRNG();
        double d0 = this.grimReaper.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d1 = this.grimReaper.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d2 = this.grimReaper.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        this.grimReaper.getMoveHelper().setMoveTo(d0, d1, d2, 0.5F);
    }

}
