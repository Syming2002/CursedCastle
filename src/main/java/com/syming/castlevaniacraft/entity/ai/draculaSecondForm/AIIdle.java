package com.syming.castlevaniacraft.entity.ai.draculaSecondForm;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaSecondForm;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;

public class AIIdle extends AnimationAI<EntityDraculaSecondForm> {
    protected Animation animation;
    protected EntityDraculaSecondForm draculaSecondForm;

    public AIIdle(EntityDraculaSecondForm entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.draculaSecondForm = entity;
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
        draculaSecondForm.currentAnim = this;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        draculaSecondForm.currentAnim = null;
    }
}
