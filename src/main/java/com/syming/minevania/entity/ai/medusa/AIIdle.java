package com.syming.minevania.entity.ai.medusa;

import com.syming.minevania.entity.mobs.boss.EntityMedusa;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIIdle extends AnimationAI<EntityMedusa> {
    protected Animation animation;
    protected EntityMedusa medusa;

    public AIIdle(EntityMedusa entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.medusa = entity;
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
        medusa.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        medusa.currentAnim = null;
    }
}
