package com.syming.castlevaniacraft.entity.mobs;

import com.syming.castlevaniacraft.api.IElementalWeapon;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.entity.ai.AIFlyOverTarget;
import com.syming.castlevaniacraft.entity.ai.harpy.AIDaggerAttack;
import com.syming.castlevaniacraft.entity.ai.harpy.AIFeatherAttack;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityHarpy extends CCEntityMob implements IAnimatedEntity, EntityFlying {

    private Animation animation = NO_ANIMATION;

    public static final Animation FEATHER_ATTACK = Animation.create(35);
    private int featherAttackCooldown = 0;
    public static final int FEATHER_ATTACK_COOLDOWN = 60;

    public static final Animation DAGGER_ATTACK = Animation.create(32);
    private int daggerAttackCooldown = 0;
    public static final int DAGGER_ATTACK_COOLDOWN = 80;

    private static final Animation[] ANIMATIONS = {FEATHER_ATTACK, DAGGER_ATTACK};

    private int animationTick;

    public AnimationAI currentAnim;

    public EntityHarpy(World worldIn) {
        super(worldIn);
        setSize(0.6875F,2.625F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new AIFeatherAttack(this, FEATHER_ATTACK));
        this.tasks.addTask(2, new AIDaggerAttack(this, DAGGER_ATTACK));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, true));
        this.tasks.addTask(2, new AIFlyOverTarget(this, 3.0, 1.2));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityVillager.class, 8.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(52.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4000000059604645D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);

    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        EntityPlayer player = (EntityPlayer) getAttackingEntity();
        if (player != null) {
            System.out.println("player is not null");
            ItemStack stack = player.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                System.out.println("item implements IElementalWeapon");
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    System.out.println("source damage detected");
                    return source == CCDamageSource.getIceDamage();
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        EntityPlayer player = (EntityPlayer) getAttackingEntity();
        if (player != null) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    System.out.println("source damage detected");
                    return source == CCDamageSource.getWindDamage();
                }
            }
        }
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (getAttackTarget() != null) {

            if (getAnimation() == NO_ANIMATION && featherAttackCooldown <= 0 && isEntityAlive() && this.rand.nextFloat() < 0.8F) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, FEATHER_ATTACK);
                this.featherAttackCooldown = FEATHER_ATTACK_COOLDOWN;
            }
            if (getAnimation() == NO_ANIMATION && daggerAttackCooldown <= 0 && isEntityAlive() && this.rand.nextFloat() < 0.6F) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, DAGGER_ATTACK);
                this.daggerAttackCooldown = DAGGER_ATTACK_COOLDOWN;
            }
        }

        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }

        if (featherAttackCooldown > 0) featherAttackCooldown--;
        if (daggerAttackCooldown > 0) daggerAttackCooldown--;
        despawnIfPeaceful();
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
