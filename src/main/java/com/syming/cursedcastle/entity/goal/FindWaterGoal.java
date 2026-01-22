package com.syming.cursedcastle.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class FindWaterGoal extends Goal {
    private final PathfinderMob mob;
    private final double speed;

    public FindWaterGoal(PathfinderMob mob, double speed) {
        this.mob = mob;
        this.speed = speed;
    }


    @Override
    public boolean canUse() {
        return !this.mob.isInWater();
    }

    @Override
    public void start() {
        super.start();

        BlockPos waterPos = findNearestWater();
        if (waterPos != null) {
            this.mob.getNavigation().moveTo(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
        }
    }

    private BlockPos findNearestWater() {
        BlockPos mobPos = this.mob.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(mobPos.offset(-16, -4, -16), mobPos.offset(16, 4, 16))) {
            if (this.mob.level().getFluidState(pos).is(FluidTags.WATER)) {
                return pos.immutable();
            }
        }
        return null;
    }
}
