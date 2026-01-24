package com.syming.cursedcastle.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public class RusalkaPathNavigation extends GroundPathNavigation {
    public RusalkaPathNavigation(Mob pMob, Level pLevel) {
        super(pMob, pLevel);
    }

    @Override
    protected PathFinder createPathFinder(int pMaxVisitedNodes) {
        this.nodeEvaluator = new WalkNodeEvaluator();
        return new PathFinder(this.nodeEvaluator, pMaxVisitedNodes);
    }

    @Override
    protected boolean hasValidPathType(BlockPathTypes pPathType) {
        return pPathType == BlockPathTypes.WATER;
    }

    @Override
    public boolean isStableDestination(BlockPos pPos) {
        return this.level.getBlockState(pPos).is(Blocks.WATER);
    }
}
