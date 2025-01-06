package com.syming.minevania.entity.mobs.boss;

import com.syming.minevania.entity.ai.medusa.AIMeleeAttack;
import com.syming.minevania.entity.ai.medusa.AIStareAttack;
import com.syming.minevania.entity.mobs.CCEntityMob;
import com.syming.minevania.entity.mobs.EntityMedusaSnake;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.EntityLivingBase;
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

public class EntityMedusa extends CCEntityMob implements IAnimatedEntity {

    private Animation animation = NO_ANIMATION;
//    public static final Animation IDLE_ANIMATION = Animation.create(40);
    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(45);
    public static final Animation STARE_ATTACK_ANIMATION = Animation.create(120);
    private int meleeAttackCooldown = 0;
    private int stareAttackCooldown = 0;
    private int snakeAttackCooldown = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 50;
    public static final int STARE_ATTACK_COOLDOWN = 300;
    public static final int SNAKE_ATTACK_COOLDOWN = 450;
    private static final Animation[] ANIMATIONS = {/*IDLE_ANIMATION,*/ MELEE_ATTACK_ANIMATION, STARE_ATTACK_ANIMATION};
    private int animationTick;
    public AnimationAI currentAnim;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS));

    public EntityMedusa(World worldIn) {
        super(worldIn);
        this.setSize(1.5F, 2F);
        this.experienceValue = 25;
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
//        this.tasks.addTask(0, new AIIdle(this, IDLE_ANIMATION));
        this.tasks.addTask(1, new AIMeleeAttack(this, MELEE_ATTACK_ANIMATION, 1.2D, false));
        this.tasks.addTask(2, new AIStareAttack(this, STARE_ATTACK_ANIMATION));
        this.tasks.addTask(1, new EntityAIWander(this, 1D));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, false));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityVillager.class, 8.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(550.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);

    }

    @Override
    protected void entityInit() {
        super.entityInit();
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

    private void despawnIfPeaceful() {
        if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            setDead();
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityLivingBase target = this.getAttackTarget();
        if (!this.world.isRemote) {
            if (target != null && this.snakeAttackCooldown <= 0) {
                for (int i = 0; i < 3; i++) {
                    EntityMedusaSnake medusaSnake = new EntityMedusaSnake(this.world);
                    medusaSnake.setPosition(this.posX + i, this.posY, this.posZ + i);
                    this.world.spawnEntity(medusaSnake);
                    this.snakeAttackCooldown = SNAKE_ATTACK_COOLDOWN;
                }
            }
        }

        if (getAttackTarget() != null) {

            if (getAnimation() == NO_ANIMATION && meleeAttackCooldown <= 0 && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, MELEE_ATTACK_ANIMATION);
                this.meleeAttackCooldown = MELEE_ATTACK_COOLDOWN;
            }

            if (getAnimation() == NO_ANIMATION && stareAttackCooldown <= 0 && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, STARE_ATTACK_ANIMATION);
                this.stareAttackCooldown = STARE_ATTACK_COOLDOWN;
            }

        }
//        else if (!world.isRemote) {
//            if (getAnimation() == NO_ANIMATION && this.isEntityAlive()) {
//                AnimationHandler.INSTANCE.sendAnimationMessage(this, IDLE_ANIMATION);
//            }
//        }


        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }
        despawnIfPeaceful();
        if (meleeAttackCooldown > 0) meleeAttackCooldown--;
        if (stareAttackCooldown > 0) stareAttackCooldown--;
        if (snakeAttackCooldown > 0) snakeAttackCooldown--;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}
