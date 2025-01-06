package com.syming.minevania.entity.mobs;

import com.syming.minevania.damagesource.CCDamageSource;
import com.syming.minevania.entity.ai.flameDemon.AIBigFireballAttack;
import com.syming.minevania.entity.ai.flameDemon.AIIdle;
import com.syming.minevania.entity.ai.flameDemon.AIMeleeAttack;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityFlameDemon extends EntityMob implements IAnimatedEntity, EntityFlying {
    private Animation animation = NO_ANIMATION;
    public static final Animation IDLE_ANIMATION = Animation.create(40);
    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(60);
    public static final Animation BIG_FIREBALL_ANIMATION = Animation.create(70);
    private static final Animation[] ANIMATIONS = {IDLE_ANIMATION, MELEE_ATTACK_ANIMATION, BIG_FIREBALL_ANIMATION};
    private int animationTick;
    public AnimationAI currentAnim;
    private int meleeAttackCooldown = 0;
    private int bigFireballAttackCooldown = 0;
    public static final int BIG_FIREBALL_ATTACK_COOLDOWN = 100;
    public static final int MELEE_ATTACK_COOLDOWN = 75;
    public float targetDistance;

    public EntityFlameDemon(World worldIn) {
        super(worldIn);
        this.setSize(3.85F, 5.0F);
        this.isImmuneToFire = true;
        this.experienceValue = (int) 4.44;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new AIIdle(this, IDLE_ANIMATION));
        this.tasks.addTask(2, new AIMeleeAttack(this, MELEE_ATTACK_ANIMATION));
        this.tasks.addTask(2, new AIBigFireballAttack(this, BIG_FIREBALL_ANIMATION));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, false));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.2D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(166.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.6D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    private void despawnIfPeaceful() {
        if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            setDead();
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getAttackTarget() != null) {

            targetDistance = getDistance(getAttackTarget()) - getAttackTarget().width / 2f;

            if (targetDistance > 5.0F && getAnimation() == NO_ANIMATION && bigFireballAttackCooldown <= 0 /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, BIG_FIREBALL_ANIMATION);
                this.bigFireballAttackCooldown = BIG_FIREBALL_ATTACK_COOLDOWN;
            }

            if (targetDistance <= 5.0F && getAnimation() == NO_ANIMATION && meleeAttackCooldown <= 0 /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, MELEE_ATTACK_ANIMATION);
                this.meleeAttackCooldown = MELEE_ATTACK_COOLDOWN;
            }
        }


        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }

        if (bigFireballAttackCooldown > 0) bigFireballAttackCooldown--;
        if (meleeAttackCooldown > 0) meleeAttackCooldown--;

        despawnIfPeaceful();

        if (world.isRaining()) {
            this.attackEntityFrom(CCDamageSource.getWaterDamage(), 2.0F);
        }

        BlockPos pos = new BlockPos(this);

        if (!this.world.isRemote && this.world.getBlockState(pos).getMaterial() == Material.WATER && this.world.getBlockState(pos).getMaterial().isLiquid()) {
            this.attackEntityFrom(CCDamageSource.getWaterDamage(), 4.0F);
        }

        if (!this.world.isRemote && this.world.getBlockState(pos).getMaterial() == Material.LAVA && this.world.getBlockState(pos).getMaterial().isLiquid()) {
            this.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 1, 3, true, false));
        }

    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }

    @Override
    public void travel(float strafe, float vertical, float forward)
    {
        if (this.isInWater())
        {
            this.moveRelative(strafe, vertical, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.isInLava())
        {
            this.moveRelative(strafe, vertical, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float f = 0.91F;

            if (this.onGround)
            {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
                IBlockState underState = this.world.getBlockState(underPos);
                f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
            }

            float f1 = 0.16277136F / (f * f * f);
            this.moveRelative(strafe, vertical, forward, this.onGround ? 0.1F * f1 : 0.02F);
            f = 0.91F;

            if (this.onGround)
            {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
                IBlockState underState = this.world.getBlockState(underPos);
                f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f;
            this.motionY *= (double)f;
            this.motionZ *= (double)f;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == CCDamageSource.getWaterDamage()) {
            amount *= 4.0F;
        }

        return super.attackEntityFrom(source, amount);
    }

    /**
     * Returns true if this entity should move as if it were on a ladder (either because it's actually on a ladder, or
     * for AI reasons)
     */
    @Override
    public boolean isOnLadder()
    {
        return false;
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
