package com.syming.minevania.entity.ai.rusalka;

//public class AIEntityRestrictGround extends EntityAIBase
//{
//    private final EntityCreature entity;
//
//    public AIEntityRestrictGround(EntityCreature creature)
//    {
//        this.entity = creature;
//    }
//
//    /**
//     * Returns whether the EntityAIBase should begin execution.
//     */
//    public boolean shouldExecute()
//    {
//        return !this.entity.isInWater();
//    }
//
//    /**
//     * Execute a one shot task or start executing a continuous task
//     */
//    public void startExecuting()
//    {
//        ((PathNavigateOnWater)this.entity.getNavigator()).setAvoidGround(true);
//    }
//
//    /**
//     * Reset the task's internal state. Called when this task is interrupted by another one
//     */
//    public void resetTask()
//    {
//        ((PathNavigateOnWater)this.entity.getNavigator()).setAvoidGround(false);
//    }
//}
