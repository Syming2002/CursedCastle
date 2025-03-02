package com.syming.castlevaniacraft.entity.mobs.boss;

import com.syming.castlevaniacraft.entity.ai.zephyr.AIDaggerAttack;
import com.syming.castlevaniacraft.entity.ai.zephyr.AIIdle;
import com.syming.castlevaniacraft.entity.ai.zephyr.AIMeleeAttack;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;
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
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityZephyr extends CCEntityMob implements IAnimatedEntity {

    public static final Animation IDLE_ANIMATION = Animation.create(60);
    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(50);
    public static final Animation DAGGER_ATTACK_ANIMATION = Animation.create(28);
    private Animation animation = NO_ANIMATION;
    private static final Animation[] ANIMATIONS = {IDLE_ANIMATION, MELEE_ATTACK_ANIMATION, DAGGER_ATTACK_ANIMATION};
    private int attackMeleeCooldown = 0;
    private int daggerAttackCooldown = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 40;
    public static final int DAGGER_ATTACK_COOLDOWN = 150;
    private int animationTick;
    public AnimationAI currentAnim;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS));

    public EntityZephyr(World worldIn) {
        super(worldIn);
        setSize(1.0F, 2.375F);
        this.experienceValue = (int) 56.78F;
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
        this.tasks.addTask(1, new AIMeleeAttack(this, MELEE_ATTACK_ANIMATION, 1.2D, false));
        this.tasks.addTask(2, new AIDaggerAttack(this, DAGGER_ATTACK_ANIMATION));
        this.tasks.addTask(1, new EntityAIWander(this, 1D));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, true));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityVillager.class, 8.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(617.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);

    }

    @Override
    protected void entityInit() {
        super.entityInit();
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

            if (getAnimation() == NO_ANIMATION && attackMeleeCooldown <= 0 /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive() && targetDistance <= 5.0) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, MELEE_ATTACK_ANIMATION);
                this.attackMeleeCooldown = MELEE_ATTACK_COOLDOWN;
            }

            if (getAnimation() == NO_ANIMATION && daggerAttackCooldown <= 0 /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive() && targetDistance > 5.0) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, DAGGER_ATTACK_ANIMATION);
                this.daggerAttackCooldown = DAGGER_ATTACK_COOLDOWN;
                System.out.println("Send Dagger Animation");
            }

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

        despawnIfPeaceful();

        if (attackMeleeCooldown > 0) attackMeleeCooldown--;
        if (daggerAttackCooldown > 0) daggerAttackCooldown--;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

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
