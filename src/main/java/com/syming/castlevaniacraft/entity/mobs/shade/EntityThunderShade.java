package com.syming.castlevaniacraft.entity.mobs.shade;

import com.syming.castlevaniacraft.entity.ai.shade.AIRepulseAttack;
import com.syming.castlevaniacraft.entity.ai.shade.thunderShade.AILightningBoltAttack;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityThunderShade extends EntityShade {

    public static final Animation LIGHTNING_ATTACK_ANIMATION = Animation.create(100);
    protected int lightningAttackCooldown = 0;
    public static final int LIGHTNING_ATTACK_COOLDOWN = 300;

    private static final Animation[] ANIMATIONS = {LIGHTNING_ATTACK_ANIMATION};

    public EntityThunderShade(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(2, new AILightningBoltAttack(this, LIGHTNING_ATTACK_ANIMATION));
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getAttackTarget() != null) {

            if (targetDistance <= 4.0F && getAnimation() == NO_ANIMATION && lightningAttackCooldown <= 0 && isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, LIGHTNING_ATTACK_ANIMATION);
                this.lightningAttackCooldown = LIGHTNING_ATTACK_COOLDOWN;
            }
            this.targetDistance = getDistance(getAttackTarget()) - getAttackTarget().width / 2.0F;
        }

        if (getAnimation() != NO_ANIMATION) {
            this.animationTick++;
            if (this.world.isRemote && this.animationTick >= this.animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }

        if (lightningAttackCooldown > 0) lightningAttackCooldown--;
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt) {
        if (!this.world.isRemote && !this.isDead) {
            onShadeHeal(this);
        }
    }

    @Override
    public int getAnimationTick() {
        return super.getAnimationTick();
    }

    @Override
    public void setAnimationTick(int tick) {
        super.setAnimationTick(tick);
    }

    @Override
    public Animation getAnimation() {
        return super.getAnimation();
    }

    @Override
    public void setAnimation(Animation animation) {
        if (animation == NO_ANIMATION) {
            onAnimationFinish(this.animation);
            setAnimationTick(0);
        }
        this.animation = animation;
    }

    @Override
    protected void onAnimationFinish(Animation animation) {

    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }

//    @Override
//    public ResourceLocation getLootTable() {
//        return CCTreasures.POISON_SHADE;
//    }
}
