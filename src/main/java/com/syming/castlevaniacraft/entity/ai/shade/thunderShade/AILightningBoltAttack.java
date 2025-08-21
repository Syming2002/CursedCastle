package com.syming.castlevaniacraft.entity.ai.shade.thunderShade;

import com.syming.castlevaniacraft.entity.EntityFireBreath;
import com.syming.castlevaniacraft.entity.EntityIceBreath;
import com.syming.castlevaniacraft.entity.EntityPoisonBreath;
import com.syming.castlevaniacraft.entity.mobs.shade.*;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.Vec3d;

public class AILightningBoltAttack extends AnimationAI<EntityThunderShade> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityThunderShade thunderShade;

    public AILightningBoltAttack(EntityThunderShade entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.thunderShade = entity;
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
        this.thunderShade.currentAnim = this;
        this.attackTarget = thunderShade.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.thunderShade.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        if (this.attackTarget != null) {
            this.thunderShade.faceEntity(this.attackTarget, 30.0F, 30.0F);
            this.thunderShade.rotationYawHead = this.thunderShade.rotationYaw;
            this.thunderShade.renderYawOffset = this.thunderShade.rotationYaw;
            if (this.thunderShade.getAnimationTick() == 50) {
                if (!this.thunderShade.world.isRemote) {
                    EntityLightningBolt lightningBolt = new EntityLightningBolt(
                            this.thunderShade.world,
                            this.attackTarget.posX,
                            this.attackTarget.posY,
                            this.attackTarget.posZ,
                            false);
                    this.thunderShade.world.addWeatherEffect(lightningBolt);
                    System.out.println("Lightning Bolt Appear !");
                }
            }
        }
    }
}
