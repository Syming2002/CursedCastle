package com.syming.cursedcastle.entity.goal;

import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class RusalkaGoToWaterGoal extends MoveToBlockGoal {

    private final RusalkaEntity rusalka;

    public RusalkaGoToWaterGoal(RusalkaEntity pRusalka, double pSpeedModifier, int pSearchRange) {
        super(pRusalka, pSpeedModifier, pSearchRange);

        this.rusalka = pRusalka;
    }

    public RusalkaGoToWaterGoal(RusalkaEntity pRusalka, double pSpeedModifier) {
        super(pRusalka, pSpeedModifier, 8, 2);

        this.rusalka = pRusalka;
    }

    @Override
    public boolean canUse() {
        return !this.rusalka.isInWater() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !this.rusalka.isInWater() && isValidTarget(this.rusalka.level(), this.blockPos);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos).is(Blocks.WATER) && pLevel.getBlockState(pPos.above()).isPathfindable(pLevel, pPos, PathComputationType.LAND);
    }
}
