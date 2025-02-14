package com.syming.castlevaniacraft.entity.mobs;

import com.syming.castlevaniacraft.entity.ai.gorgon.AIPoisonBreath;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGorgon extends EntityMob implements IAnimatedEntity {

    private Animation animation = NO_ANIMATION;
    public static final Animation POISON_BREATH_ANIMATION = Animation.create(50);
    private static final Animation[] ANIMATIONS = {POISON_BREATH_ANIMATION};
    private int animationTick;
    public AnimationAI currentAnim;
    private int poisonBreathAttackCooldown = 0;
    public static final int POISON_BREATH_ATTACK_COOLDOWN = 200;

    public EntityGorgon(World worldIn) {
        super(worldIn);
        setSize(1.5F, 1.3125F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(2, new AIPoisonBreath(this, POISON_BREATH_ANIMATION));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
        this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);

    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();

        if (this.getAttackTarget() == null) {
            this.setAttackTarget(this.world.getClosestPlayerToEntity(this, 20.0D));
        } else if (getAttackTarget().isEntityAlive()) {
            if (isEntityAlive() && canEntityBeSeen(getAttackTarget()) && getDistance(getAttackTarget()) <= 2.0D) {
                attackEntityAsMob(getAttackTarget());
            }
        } else {
            setAttackTarget(null);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getAttackTarget() != null) {
            if (getAnimation() == NO_ANIMATION && poisonBreathAttackCooldown <= 0 /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, POISON_BREATH_ANIMATION);
                this.poisonBreathAttackCooldown = POISON_BREATH_ATTACK_COOLDOWN;
            }
        }

        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }

        if (getAnimation() == POISON_BREATH_ANIMATION && getAnimationTick() <= this.animation.getDuration()) {
            this.motionX = 0.0F;
            this.motionY = 0.0F;
            this.motionZ = 0.0F;
        }
        if (poisonBreathAttackCooldown > 0) poisonBreathAttackCooldown--;
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }

    @Override
    public void setAnimation(Animation animation) {
        if (animation == NO_ANIMATION) {
            onAnimationFinish(this.animation);
            setAnimationTick(0);
        }
        this.animation = animation;
    }

    protected void onAnimationFinish(Animation animation) {

    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }
}
