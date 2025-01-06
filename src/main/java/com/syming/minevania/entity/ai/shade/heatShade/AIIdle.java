package com.syming.minevania.entity.ai.shade.heatShade;

import com.syming.minevania.entity.mobs.EntityHeatShade;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIIdle extends AnimationAI<EntityHeatShade> {
    protected Animation animation;
    protected EntityHeatShade heatShade;

    public AIIdle(EntityHeatShade entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.heatShade = entity;
        this.animation = animation;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
    }
    @Override
    public boolean isAutomatic() {
        return true;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        heatShade.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        heatShade.currentAnim = null;
    }
}
