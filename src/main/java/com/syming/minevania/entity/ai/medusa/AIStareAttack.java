package com.syming.minevania.entity.ai.medusa;

import com.syming.minevania.entity.mobs.boss.EntityMedusa;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;

public class AIStareAttack extends AnimationAI<EntityMedusa> {

    protected Animation animation;
    protected EntityMedusa medusa;
    protected EntityLivingBase attackTarget;

    public AIStareAttack(EntityMedusa entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.medusa = entity;
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

    @Override
    public void startExecuting() {
        super.startExecuting();
        medusa.currentAnim = this;
        attackTarget = medusa.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        medusa.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        if (attackTarget == null) {
            return;
        }

        if (this.medusa.getAnimationTick() == 65) {
            if (isPlayerLookingAtMedusa(attackTarget)) {
                attackTarget.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 100, false, false));
                System.out.println("Slowness is given !");
            }
        }
    }

    private boolean isPlayerLookingAtMedusa(EntityLivingBase livingBase) {
        Vec3d medusaPos = new Vec3d( this.medusa.posX - livingBase.posX, this.medusa.posY - livingBase.posY, this.medusa.posZ - livingBase.posZ).normalize();

        Vec3d playerLookVec = livingBase.getLook(1.0F).normalize();

        double dotProduct = medusaPos.dotProduct(playerLookVec);

        double angle = Math.toDegrees(Math.acos(dotProduct));

        return angle < 60.0;
    }
}
