package com.syming.castlevaniacraft.entity.ai.dracula;

import com.syming.castlevaniacraft.entity.mobs.EntityVampireBat;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;

public class AIBatsAttack extends AnimationAI<EntityDracula> {
    protected Animation animation;
    protected EntityDracula dracula;
    protected EntityLivingBase attackTarget;

    public AIBatsAttack(EntityDracula entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.dracula = entity;
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
        dracula.currentAnim = this;
        attackTarget = dracula.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        dracula.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        if (dracula.getAnimationTick() == 30 && attackTarget != null) {
            if (!dracula.world.isRemote) {
                spawnAggressiveBats(this.dracula.posX, this.dracula.posY, this.dracula.posZ, 10);
            }
        }
    }

    private void spawnAggressiveBats(double x, double y, double z, int count) {
        for (int i = 0; i < count; i++) {

            double angle = this.dracula.getRNG().nextDouble() * 2 * Math.PI;
            double distance = this.dracula.getRNG().nextDouble() * 5;

            double offsetX = Math.cos(angle) * distance;
            double offsetZ = Math.sin(angle) * distance;

            EntityVampireBat aggressiveBat = new EntityVampireBat(this.dracula.world);
            aggressiveBat.setLocationAndAngles(x + offsetX, y, z + offsetZ, this.dracula.rotationYaw, this.dracula.rotationPitch);
            this.dracula.world.spawnEntity(aggressiveBat);
        }
    }
}
