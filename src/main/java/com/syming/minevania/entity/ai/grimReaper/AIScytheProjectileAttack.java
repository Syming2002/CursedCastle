package com.syming.minevania.entity.ai.grimReaper;

import com.syming.minevania.entity.mobs.boss.EntityGrimReaper;
import com.syming.minevania.entity.projectiles.EntityScytheProjectile;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AIScytheProjectileAttack extends AnimationAI<EntityGrimReaper> {

    protected Animation animation;
    protected EntityGrimReaper grimReaper;
    protected EntityLivingBase attackTarget;
    private double attackRange;
    private int tickCounter = 0;

    public AIScytheProjectileAttack(EntityGrimReaper entity, Animation animation) {
        super(entity);
        this.setMutexBits(8);
        this.grimReaper = entity;
        this.animation = animation;
        this.attackTarget = null;
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }

    @Override
    public boolean isAutomatic() {
        return true;
    }

//    @Override
//    public boolean shouldExecute() {
//        return attackTarget != null
//                && this.grimReaper.getEntitySenses().canSee(attackTarget)
//                && this.grimReaper.getDistanceSq(attackTarget) > this.minTargetDistance
//                && this.grimReaper.getDistanceSq(attackTarget) <= this.attackRange * this.attackRange;
//    }

    @Override
    public void startExecuting() {
        this.entity.getNavigator().clearPath();
        grimReaper.currentAnim = this;
        attackTarget = grimReaper.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        grimReaper.currentAnim = null;
    }

    @Override
    public void updateTask() {
        if (attackTarget != null && grimReaper.getAnimationTick() > 20 && grimReaper.getAnimationTick() <= 180) {
            tickCounter++;
            if (tickCounter >= 20) {

                double radius = 2.0;
                double angle = Math.random() * 2 * Math.PI;
                double offsetX = radius * Math.cos(angle);
                double offsetZ = radius * Math.sin(angle);

                Vec3d randomPosition = new Vec3d(grimReaper.posX + offsetX, grimReaper.posY, grimReaper.posZ + offsetZ);

                Vec3d direction = attackTarget.getPositionVector().subtract(randomPosition).normalize();

                EntityScytheProjectile scytheProjectile = new EntityScytheProjectile(this.grimReaper.world, this.grimReaper);
                scytheProjectile.setPosition(randomPosition.x, grimReaper.posY + grimReaper.getEyeHeight(), randomPosition.z);
                scytheProjectile.shoot(direction.x, direction.y, direction.z, 0.75F, 1.0F);

                this.grimReaper.world.spawnEntity(scytheProjectile);

                tickCounter = 0;
            }
        }
    }
}
