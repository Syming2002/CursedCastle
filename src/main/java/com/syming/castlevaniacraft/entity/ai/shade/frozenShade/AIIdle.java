package com.syming.castlevaniacraft.entity.ai.shade.frozenShade;

import com.syming.castlevaniacraft.entity.mobs.EntityFrozenShade;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIIdle extends AnimationAI<EntityFrozenShade> {
    protected Animation animation;
    protected EntityFrozenShade frozenShade;

    public AIIdle(EntityFrozenShade entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.frozenShade = entity;
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
        frozenShade.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        frozenShade.currentAnim = null;
    }
}
