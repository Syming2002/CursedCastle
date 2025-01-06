package com.syming.minevania.entity.ai.fleaman;

import com.syming.minevania.entity.mobs.EntityFleaMan;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIJump extends AnimationAI<EntityFleaMan> {
    protected Animation animation;
    protected EntityFleaMan fleaMan;

    public AIJump(EntityFleaMan entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.fleaMan = entity;
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
        fleaMan.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        fleaMan.currentAnim = null;
    }
}
