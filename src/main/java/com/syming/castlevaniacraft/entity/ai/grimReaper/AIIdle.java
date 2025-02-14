package com.syming.castlevaniacraft.entity.ai.grimReaper;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIIdle extends AnimationAI<EntityGrimReaper> {
    protected Animation animation;
    protected EntityGrimReaper grimReaper;

    public AIIdle(EntityGrimReaper entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.grimReaper = entity;
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
        grimReaper.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        grimReaper.currentAnim = null;
    }
}
