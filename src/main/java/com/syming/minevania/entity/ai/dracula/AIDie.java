package com.syming.minevania.entity.ai.dracula;

import com.syming.minevania.entity.mobs.boss.EntityDracula;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIDie extends AnimationAI<EntityDracula> {
    protected final Animation animation;
    protected final EntityDracula dracula;

    public AIDie(EntityDracula entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.dracula = entity;
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
        dracula.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        dracula.currentAnim = null;
    }
}
