package com.syming.castlevaniacraft.entity.ai.zephyr;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityZephyr;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIIdle extends AnimationAI<EntityZephyr> {
    protected Animation animation;
    protected EntityZephyr zephyr;

    public AIIdle(EntityZephyr entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.zephyr = entity;
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
        zephyr.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        zephyr.currentAnim = null;
    }
}
