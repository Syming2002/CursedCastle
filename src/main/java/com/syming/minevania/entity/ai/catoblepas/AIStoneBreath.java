package com.syming.minevania.entity.ai.catoblepas;

import com.syming.minevania.entity.EntityStoneBreath;
import com.syming.minevania.entity.mobs.EntityCatoblepas;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AIStoneBreath extends AnimationAI<EntityCatoblepas> {
    protected Animation animation;
    protected EntityCatoblepas catoblepas;
    protected EntityLivingBase attackTarget;
    protected EntityStoneBreath stoneBreath;


    public AIStoneBreath(EntityCatoblepas entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.catoblepas = entity;
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
        catoblepas.currentAnim = this;
        attackTarget = catoblepas.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        catoblepas.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        Vec3d headPos = new Vec3d(0, 1.015625,0);
        headPos = headPos.rotateYaw((float) Math.toRadians((-catoblepas.rotationYaw - 90)));
        headPos = headPos.add(catoblepas.getPositionVector());
        headPos = headPos.add(new Vec3d(0, 0, 1.5)
                .rotatePitch((float) Math.toRadians(-catoblepas.rotationPitch))
                .rotateYaw((float) Math.toRadians(-catoblepas.rotationYawHead)));

        if (this.catoblepas.getAnimationTick() == 1 && attackTarget != null) {
            this.stoneBreath = new EntityStoneBreath(this.catoblepas.world, this.catoblepas);
            this.stoneBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.catoblepas.rotationYawHead, this.catoblepas.rotationPitch);
            if (!this.catoblepas.world.isRemote) {
                this.catoblepas.world.spawnEntity(this.stoneBreath);
                this.stoneBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.catoblepas.rotationYawHead, this.catoblepas.rotationPitch);
                System.out.println("Stone Breath is Launching");
            }
        }
    }
}
