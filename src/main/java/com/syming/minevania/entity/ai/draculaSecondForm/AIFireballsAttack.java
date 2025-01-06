package com.syming.minevania.entity.ai.draculaSecondForm;

import com.syming.minevania.entity.mobs.boss.EntityDraculaSecondForm;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.math.Vec3d;

public class AIFireballsAttack extends AnimationAI<EntityDraculaSecondForm> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityDraculaSecondForm draculaSecondForm;

    public AIFireballsAttack(EntityDraculaSecondForm entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.draculaSecondForm = entity;
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
        draculaSecondForm.currentAnim = this;
        attackTarget = draculaSecondForm.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        draculaSecondForm.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        Vec3d mouthPos = new Vec3d(0, 3.275,0);
        mouthPos = mouthPos.rotateYaw((float) Math.toRadians((-draculaSecondForm.rotationYaw - 90)));
        mouthPos = mouthPos.add(draculaSecondForm.getPositionVector());
        mouthPos = mouthPos.add(new Vec3d(0, 0, 1.25).rotatePitch((float) Math.toRadians(-draculaSecondForm.rotationPitch)).rotateYaw((float) Math.toRadians(-draculaSecondForm.rotationYawHead)));

        double lookX = -Math.sin(Math.toRadians(draculaSecondForm.rotationYawHead)) * Math.cos(Math.toRadians(draculaSecondForm.rotationPitch));
        double lookY = -Math.sin(Math.toRadians(draculaSecondForm.rotationPitch));
        double lookZ = Math.cos(Math.toRadians(draculaSecondForm.rotationYawHead)) * Math.cos(Math.toRadians(draculaSecondForm.rotationPitch));

        double speed = 0.5;

        if (attackTarget != null) {
            if (draculaSecondForm.getAnimationTick() == 40) {
                spawnSmallFireball(mouthPos.x, mouthPos.y, mouthPos.z, lookX + 0.2, lookY, lookZ, speed);
                spawnSmallFireball(mouthPos.x, mouthPos.y, mouthPos.z, lookX, lookY, lookZ, speed);
                spawnSmallFireball(mouthPos.x, mouthPos.y, mouthPos.z, lookX - 0.2, lookY, lookZ, speed);
                System.out.println("Fireballs are spawning!");
            }
            this.draculaSecondForm.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30, 30);
        }
    }

    private void spawnSmallFireball(double posX, double posY, double posZ, double directionX, double directionY, double directionZ, double speed) {
        if (!draculaSecondForm.world.isRemote) {
            EntitySmallFireball smallFireball = new EntitySmallFireball(
                    draculaSecondForm.world,
                    posX,
                    posY,
                    posZ,
                    directionX * speed,
                    directionY * speed,
                    directionZ * speed);
            draculaSecondForm.world.spawnEntity(smallFireball);
        }
    }
}
