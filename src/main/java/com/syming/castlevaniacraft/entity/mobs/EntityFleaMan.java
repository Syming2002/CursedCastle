package com.syming.castlevaniacraft.entity.mobs;

import com.syming.castlevaniacraft.entity.ai.fleaman.AIJump;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFleaMan extends EntityMob implements IAnimatedEntity {

    private Animation animation = NO_ANIMATION;
    public static final Animation JUMP_ANIMATION = Animation.create(20);
    private static final Animation[] ANIMATIONS = {JUMP_ANIMATION};
    private int animationTick;
    public AnimationAI currentAnim;
    private int jumpCooldown = 0;

    public EntityFleaMan(World worldIn) {
        super(worldIn);
        setSize(0.2875F, 0.43125F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(0, new AIJump(this, JUMP_ANIMATION));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.tasks.addTask(8, new EntityAIWander(this, 1.2D));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.5D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);

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

        if (!world.isRemote) {
            if (this.onGround && this.jumpCooldown == 0 && getAnimation() == NO_ANIMATION) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, JUMP_ANIMATION);
                jump();
                jumpCooldown = 20;
            }
        }

        if (jumpCooldown > 0) {
            jumpCooldown--;
        }


        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }
    }

    @Override
    protected float getJumpUpwardsMotion()
    {
        if (this.rand.nextFloat() < 0.33F) {
            return 0.84F;
        } else {
            return 0.42F;
        }
    }

    @Override
    protected void jump() {
        this.motionY = (double)this.getJumpUpwardsMotion();

        if (this.isPotionActive(MobEffects.JUMP_BOOST))
        {
            this.motionY += (double)((float)(this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
        }

        if (this.isSprinting())
        {
            float f = this.rotationYaw * 0.017453292F;
            this.motionX -= (double)(MathHelper.sin(f) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(f) * 0.2F);
        }

        this.isAirBorne = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public int getAnimationTick() {
        return this.animationTick;
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
