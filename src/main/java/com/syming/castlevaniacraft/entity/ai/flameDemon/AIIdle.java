package com.syming.castlevaniacraft.entity.ai.flameDemon;

import com.syming.castlevaniacraft.entity.mobs.EntityFlameDemon;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIIdle extends AnimationAI<EntityFlameDemon> {
    protected Animation animation;
    protected EntityFlameDemon flameDemon;

    public AIIdle(EntityFlameDemon entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.flameDemon = entity;
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
        flameDemon.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        flameDemon.currentAnim = null;
    }
}
