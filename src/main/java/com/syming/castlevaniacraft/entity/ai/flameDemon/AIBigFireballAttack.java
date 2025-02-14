package com.syming.castlevaniacraft.entity.ai.flameDemon;

import com.syming.castlevaniacraft.entity.mobs.EntityFlameDemon;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AIBigFireballAttack extends AnimationAI<EntityFlameDemon> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityFlameDemon flameDemon;

    public AIBigFireballAttack(EntityFlameDemon entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.flameDemon = entity;
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
        flameDemon.currentAnim = this;
        attackTarget = flameDemon.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        flameDemon.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        if (this.flameDemon.getAnimationTick() == 50 && this.attackTarget != null) {
            launchBigFireball();
        }
    }

    private void launchBigFireball() {

        if (!this.flameDemon.world.isRemote) {
            Vec3d vec3d = this.flameDemon.getLook(1.0F);
            double d2 = attackTarget.posX - (this.flameDemon.posX + vec3d.x * 4.0D);
            double d3 = attackTarget.getEntityBoundingBox().minY + (double) (attackTarget.height / 2.0F) - (0.5D + this.flameDemon.posY + (double) (this.flameDemon.height / 2.0F));
            double d4 = attackTarget.posZ - (this.flameDemon.posZ + vec3d.z * 4.0D);
            this.flameDemon.world.playEvent((EntityPlayer) null, 1016, new BlockPos(this.flameDemon), 0);
            EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.flameDemon.world, this.flameDemon, d2, d3, d4);
            entitylargefireball.posX = this.flameDemon.posX + vec3d.x;
            entitylargefireball.posY = this.flameDemon.posY + (double) this.flameDemon.height + 1.0D;
            entitylargefireball.posZ = this.flameDemon.posZ + vec3d.z;
            this.flameDemon.world.spawnEntity(entitylargefireball);
            System.out.println("Big Fireball is spawning !");
        }
    }
}
