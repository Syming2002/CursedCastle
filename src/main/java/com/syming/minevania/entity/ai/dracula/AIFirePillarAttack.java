package com.syming.minevania.entity.ai.dracula;

import com.syming.minevania.entity.EntityFirePillar;
import com.syming.minevania.entity.mobs.boss.EntityDracula;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;

public class AIFirePillarAttack extends AnimationAI<EntityDracula> {

    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityDracula dracula;

    public AIFirePillarAttack(EntityDracula entity, Animation animation) {
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
        if (dracula.getAnimationTick() == 30 && attackTarget != null) {
            spawnFirePillar();
            System.out.println("Fire Pillar is spawning");
        }
    }

    private void spawnFirePillar() {
        EntityFirePillar firePillar = new EntityFirePillar(dracula.world, attackTarget.posX, attackTarget.posY, attackTarget.posZ);
        firePillar.setPosition(this.attackTarget.posX, this.attackTarget.posY, this.attackTarget.posZ);
        dracula.world.spawnEntity(firePillar);
    }

}
