package com.syming.minevania.entity.mobs;

import com.syming.minevania.entity.ai.shade.heatShade.AIIdle;
import com.syming.minevania.entity.ai.shade.heatShade.AIRepulseAttack;
import com.syming.minevania.loot.CCTreasures;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityHeatShade extends EntityMob implements IAnimatedEntity, EntityFlying {

    public static final Animation IDLE_ANIMATION = Animation.create(40);
    public static final Animation REPULSE_ATTACK_ANIMATION = Animation.create(180);
    private Animation animation = NO_ANIMATION;
    private static final Animation[] ANIMATIONS = {IDLE_ANIMATION, REPULSE_ATTACK_ANIMATION};
    private int repulseAttackCooldown = 0;
    public static final int REPULSE_ATTACK_COOLDOWN = 150;
    private int animationTick;
    public AnimationAI currentAnim;
    public float targetDistance;

    public EntityHeatShade(World worldIn) {
        super(worldIn);
        this.setSize(1.33F, 2.0F);
        this.experienceValue = (int) 1.36;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new AIIdle(this, IDLE_ANIMATION));
        this.tasks.addTask(2, new AIRepulseAttack(this, REPULSE_ATTACK_ANIMATION));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, true));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
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

            if (targetDistance <= 4.0F && getAnimation() == NO_ANIMATION && repulseAttackCooldown <= 0 /*&& getAnimation() != DEATH_ANIMATION*/ && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, REPULSE_ATTACK_ANIMATION);
                this.repulseAttackCooldown = REPULSE_ATTACK_COOLDOWN;
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

        if (repulseAttackCooldown > 0) repulseAttackCooldown--;
        despawnIfPeaceful();

        if (world.isRaining()) {
            this.attackEntityFrom(DamageSource.DROWN, 3.0F);
        }
    }

    @Override
    public float getEyeHeight() {
        if (this.isRepulseAnimation()) {
            return this.height * 0.75F;
        } else {
            return 1.62F;
        }
    }

    private boolean isRepulseAnimation() {
        return animation == REPULSE_ATTACK_ANIMATION;
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

    @Override
    public ResourceLocation getLootTable() {
        return CCTreasures.HEAT_SHADE;
    }
}
