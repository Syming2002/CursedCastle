package com.syming.cursedcastle.entity.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class RusalkaRandomStrollOnWaterGoal extends RandomStrollGoal {
    public RusalkaRandomStrollOnWaterGoal(PathfinderMob pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
    }

    public RusalkaRandomStrollOnWaterGoal(PathfinderMob pMob, double pSpeedModifier, int pInterval) {
        super(pMob, pSpeedModifier, pInterval);
    }

    public RusalkaRandomStrollOnWaterGoal(PathfinderMob pMob, double pSpeedModifier, int pInterval, boolean pCheckNoActionTime) {
        super(pMob, pSpeedModifier, pInterval, pCheckNoActionTime);
    }

    @Override
    public boolean canUse() {
        return this.mob.isInWater() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.isInWater() && super.canContinueToUse();
    }

    @Override
    protected @Nullable Vec3 getPosition() {
        Vec3 pos = LandRandomPos.getPos(this.mob, 10, 7);

        if (pos == null) {
            return null;
        }

        return new Vec3(pos.x, this.mob.getY(), pos.z);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
