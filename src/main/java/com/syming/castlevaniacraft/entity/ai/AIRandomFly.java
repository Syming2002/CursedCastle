package com.syming.castlevaniacraft.entity.ai;

import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;

import java.util.Random;

public class AIRandomFly extends EntityAIBase {

    private final CCEntityMob ccEntityMob;

    public AIRandomFly(CCEntityMob ccEntityMob) {
        this.ccEntityMob = ccEntityMob;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (ccEntityMob.getAttackTarget() != null)
            return false;

        EntityMoveHelper entityMoveHelper = this.ccEntityMob.getMoveHelper();
        double d0 = entityMoveHelper.getX() - this.ccEntityMob.posX;
        double d1 = entityMoveHelper.getY() - this.ccEntityMob.posY;
        double d2 = entityMoveHelper.getZ() - this.ccEntityMob.posZ;
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
        Random random = this.ccEntityMob.getRNG();
        double d0 = this.ccEntityMob.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d1 = this.ccEntityMob.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d2 = this.ccEntityMob.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        this.ccEntityMob.getMoveHelper().setMoveTo(d0, d1, d2, 0.4000000059604645D);
    }

}
