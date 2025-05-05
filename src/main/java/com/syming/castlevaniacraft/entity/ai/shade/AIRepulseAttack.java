package com.syming.castlevaniacraft.entity.ai.shade;

import com.syming.castlevaniacraft.entity.EntityFireBreath;
import com.syming.castlevaniacraft.entity.EntityIceBreath;
import com.syming.castlevaniacraft.entity.EntityPoisonBreath;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityShade;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class AIRepulseAttack extends AnimationAI<EntityShade> {
    protected Animation animation;
    protected EntityLivingBase attackTarget;
    protected EntityShade shade;
    protected EntityIceBreath iceBreath;
    protected EntityFireBreath fireBreath;
    protected EntityPoisonBreath poisonBreath;

    public AIRepulseAttack(EntityShade entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.shade = entity;
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
        this.shade.currentAnim = this;
        this.attackTarget = shade.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.shade.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        Vec3d headPos = new Vec3d(0, 1.5,0);
        headPos = headPos.rotateYaw((float) Math.toRadians((-this.shade.rotationYaw - 90)));
        headPos = headPos.add(this.shade.getPositionVector());
        headPos = headPos.add(new Vec3d(0, 0, 0.25)
                .rotatePitch((float) Math.toRadians(-this.shade.rotationPitch))
                .rotateYaw((float) Math.toRadians(-this.shade.rotationYawHead)));

        if (this.attackTarget != null) {
            this.shade.faceEntity(this.attackTarget, 30.0F, 30.0F);
            this.shade.rotationYawHead = this.shade.rotationYaw;
            this.shade.renderYawOffset = this.shade.rotationYaw;
            if (this.shade.getAnimationTick() == 95) {
                if (!this.shade.world.isRemote) {
                    if (this.shade instanceof EntityFrozenShade frozenShade) {
                        this.iceBreath = new EntityIceBreath(frozenShade.world, frozenShade);
                        this.iceBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, frozenShade.rotationYawHead, frozenShade.rotationPitch);
                        frozenShade.world.spawnEntity(this.iceBreath);
                        System.out.println("Ice Breath is Launching");
                    } else if (this.shade instanceof EntityHeatShade heatShade) {
                        this.fireBreath = new EntityFireBreath(heatShade.world, heatShade);
                        this.fireBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, heatShade.rotationYawHead, heatShade.rotationPitch);
                        heatShade.world.spawnEntity(this.fireBreath);
                        System.out.println("Fire Breath is Launching");
                    } else if (this.shade instanceof EntityPoisonShade poisonShade) {
                        this.poisonBreath = new EntityPoisonBreath(poisonShade.world, poisonShade);
                        this.poisonBreath.setPositionAndRotation(headPos.x, headPos.y, headPos.z, poisonShade.rotationYawHead, poisonShade.rotationPitch);
                        poisonShade.world.spawnEntity(this.poisonBreath);
                        System.out.println("Poison Breath is Launching");
                    }
                }
            }
        }
    }
}
