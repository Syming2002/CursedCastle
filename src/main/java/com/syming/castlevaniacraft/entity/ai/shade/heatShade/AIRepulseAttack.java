package com.syming.castlevaniacraft.entity.ai.shade.heatShade;

import com.syming.castlevaniacraft.entity.EntityFireBreath;
import com.syming.castlevaniacraft.entity.mobs.EntityHeatShade;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AIRepulseAttack extends AnimationAI<EntityHeatShade> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityHeatShade heatShade;
    protected EntityFireBreath fireBreath;


    public AIRepulseAttack(EntityHeatShade entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.heatShade = entity;
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
        heatShade.currentAnim = this;
        attackTarget = heatShade.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        heatShade.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        Vec3d headPos = new Vec3d(0, 1.5,0);
        headPos = headPos.rotateYaw((float) Math.toRadians((-heatShade.rotationYaw - 90)));
        headPos = headPos.add(heatShade.getPositionVector());
        headPos = headPos.add(new Vec3d(0, 0, 0.25).rotatePitch((float) Math.toRadians(-heatShade.rotationPitch)).rotateYaw((float) Math.toRadians(-heatShade.rotationYawHead)));

        if (this.heatShade.getAnimationTick() == 95 && attackTarget != null) {
            this.fireBreath = new EntityFireBreath(this.heatShade.world, this.heatShade);
            this.fireBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.heatShade.rotationYawHead, this.heatShade.rotationPitch);
            if (!this.heatShade.world.isRemote) {
                this.heatShade.world.spawnEntity(this.fireBreath);
                this.fireBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.heatShade.rotationYawHead, this.heatShade.rotationPitch);
                System.out.println("Fire Breath is Launching");
            }
        }
    }
}
