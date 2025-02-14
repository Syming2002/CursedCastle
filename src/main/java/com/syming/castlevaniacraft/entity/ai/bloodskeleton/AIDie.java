package com.syming.castlevaniacraft.entity.ai.bloodskeleton;

import com.syming.castlevaniacraft.entity.mobs.EntityBloodSkeleton;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIDie extends AnimationAI<EntityBloodSkeleton> {
    protected final Animation animation;
    protected final EntityBloodSkeleton bloodSkeleton;

    public AIDie(EntityBloodSkeleton entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.bloodSkeleton = entity;
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
        bloodSkeleton.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        bloodSkeleton.currentAnim = null;
    }
}
