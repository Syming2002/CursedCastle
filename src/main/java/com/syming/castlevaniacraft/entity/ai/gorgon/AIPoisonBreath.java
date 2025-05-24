package com.syming.castlevaniacraft.entity.ai.gorgon;

import com.syming.castlevaniacraft.entity.EntityPoisonBreath;
import com.syming.castlevaniacraft.entity.mobs.EntityGorgon;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AIPoisonBreath extends AnimationAI<EntityGorgon> {
    protected Animation animation;
    protected EntityGorgon gorgon;
    protected EntityLivingBase attackTarget;
    protected EntityPoisonBreath poisonBreath;


    public AIPoisonBreath(EntityGorgon entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.gorgon = entity;
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
        gorgon.currentAnim = this;
        attackTarget = gorgon.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        gorgon.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        Vec3d headPos = new Vec3d(0, 1.015625,0);
        headPos = headPos.rotateYaw((float) Math.toRadians((-gorgon.rotationYaw - 90)));
        headPos = headPos.add(gorgon.getPositionVector());
        headPos = headPos.add(new Vec3d(0, 0, 1.5)
                .rotatePitch((float) Math.toRadians(-gorgon.rotationPitch))
                .rotateYaw((float) Math.toRadians(-gorgon.rotationYawHead)));

        if (this.attackTarget != null) {
            this.gorgon.faceEntity(this.attackTarget, 30.0F, 30.0F);
            this.gorgon.rotationYawHead = this.gorgon.rotationYaw;
            this.gorgon.renderYawOffset = this.gorgon.rotationYaw;
            if (this.gorgon.getAnimationTick() == 1) {
                this.poisonBreath = new EntityPoisonBreath(this.gorgon.world, this.gorgon);
                this.poisonBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.gorgon.rotationYawHead, this.gorgon.rotationPitch);
                if (!this.gorgon.world.isRemote) {
                    this.gorgon.world.spawnEntity(this.poisonBreath);
                    this.poisonBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.gorgon.rotationYawHead, this.gorgon.rotationPitch);
                    System.out.println("Poison Breath is Launching");
                }
            }
        }
    }
}
