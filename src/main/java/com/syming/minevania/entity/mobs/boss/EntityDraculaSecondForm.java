package com.syming.minevania.entity.mobs.boss;

import com.syming.minevania.entity.ai.draculaSecondForm.AIFireballsAttack;
import com.syming.minevania.entity.ai.draculaSecondForm.AIIdle;
import com.syming.minevania.entity.ai.draculaSecondForm.AIMeleeAttack;

import com.syming.minevania.entity.mobs.CCEntityMob;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityDraculaSecondForm extends CCEntityMob implements IAnimatedEntity {

    public static final Animation IDLE_ANIMATION = Animation.create(40);
    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(55);
    public static final Animation FIREBALLS_ANIMATION = Animation.create(80);
    private Animation animation = NO_ANIMATION;
    private static final Animation[] ANIMATIONS = {IDLE_ANIMATION, MELEE_ATTACK_ANIMATION, FIREBALLS_ANIMATION};
    private int attackMeleeCooldown = 0;
    private int fireballsCooldown = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 80;
    public static final int FIREBALLS_COOLDOWN = 100;
    private int animationTick;
    public AnimationAI currentAnim;
    public float targetDistance;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public EntityDraculaSecondForm(World worldIn) {
        super(worldIn);
        this.setSize(1.25F, 4.0F);
        this.experienceValue = 500;
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
        this.tasks.addTask(0, new AIIdle(this, IDLE_ANIMATION));
        this.tasks.addTask(1, new AIMeleeAttack(this, MELEE_ATTACK_ANIMATION));
        this.tasks.addTask(2, new AIFireballsAttack(this, FIREBALLS_ANIMATION));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 16.0F));
        this.tasks.addTask(1, new EntityAIWander(this, 1D));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, true));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityVillager.class, 16.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.99D);

    }

    @Override
    public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    @Override
    public boolean isNonBoss()
    {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getAttackTarget() != null) {

            if (targetDistance >= 4.0F && getAnimation() == NO_ANIMATION && fireballsCooldown <= 0 /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, FIREBALLS_ANIMATION);
                this.fireballsCooldown = FIREBALLS_COOLDOWN;
            }

            if (targetDistance <= 4.0F && getAnimation() == NO_ANIMATION && attackMeleeCooldown <= 0 && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, MELEE_ATTACK_ANIMATION);
                this.attackMeleeCooldown = MELEE_ATTACK_COOLDOWN;
            }

            targetDistance = getDistance(getAttackTarget()) - getAttackTarget().width / 2f;

        } else if (!world.isRemote) {
            if (getAnimation() == NO_ANIMATION /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, IDLE_ANIMATION);
            }
        }

        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }

        if (attackMeleeCooldown > 0) attackMeleeCooldown--;
        if (fireballsCooldown > 0) fireballsCooldown--;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.world.isDaytime() && !this.world.isRemote) {
            if (isEntityAlive()) {
                world.setWorldTime(18000);
            }
        } else if (this.isDead) {
            world.setWorldTime(0);
        }
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
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
