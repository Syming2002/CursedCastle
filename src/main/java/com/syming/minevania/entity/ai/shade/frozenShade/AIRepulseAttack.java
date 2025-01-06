package com.syming.minevania.entity.ai.shade.frozenShade;

import com.syming.minevania.entity.EntityIceBreath;
import com.syming.minevania.entity.mobs.EntityFrozenShade;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AIRepulseAttack extends AnimationAI<EntityFrozenShade> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityFrozenShade frozenShade;
    protected EntityIceBreath iceBreath;

    public AIRepulseAttack(EntityFrozenShade entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.frozenShade = entity;
        this.animation = animation;
        this.attackTarget = null;
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
        attackTarget = frozenShade.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        frozenShade.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        Vec3d headPos = new Vec3d(0, 1.5,0);
        headPos = headPos.rotateYaw((float) Math.toRadians((-frozenShade.rotationYaw - 90)));
        headPos = headPos.add(frozenShade.getPositionVector());
        headPos = headPos.add(new Vec3d(0, 0, 0.25)
                .rotatePitch((float) Math.toRadians(-frozenShade.rotationPitch))
                .rotateYaw((float) Math.toRadians(-frozenShade.rotationYawHead)));

        if (this.frozenShade.getAnimationTick() == 95 && attackTarget != null) {
            this.iceBreath = new EntityIceBreath(this.frozenShade.world, this.frozenShade);
            this.iceBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.frozenShade.rotationYawHead, this.frozenShade.rotationPitch);
            if (!this.frozenShade.world.isRemote) {
                this.frozenShade.world.spawnEntity(this.iceBreath);
                this.iceBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.frozenShade.rotationYawHead, this.frozenShade.rotationPitch);
                System.out.println("Ice Breath is Launching");
            }
        }
    }
}
