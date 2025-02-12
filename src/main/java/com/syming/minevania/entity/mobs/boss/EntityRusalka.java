package com.syming.minevania.entity.mobs.boss;

import com.syming.minevania.api.IElementalWeapon;
import com.syming.minevania.damagesource.CCDamageSource;
import com.syming.minevania.entity.ai.rusalka.*;
import com.syming.minevania.entity.mobs.CCEntityMob;
import com.syming.minevania.entity.util.PathNavigateOnWater;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EntityRusalka extends CCEntityMob implements IAnimatedEntity {

    private Animation animation = NO_ANIMATION;
    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(40);
    public static final Animation HAIR_ATTACK_ANIMATION = Animation.create(35);
    public static final Animation WATER_ATTACK_ANIMATION = Animation.create(80);
    private static final Animation[] ANIMATIONS = {MELEE_ATTACK_ANIMATION, HAIR_ATTACK_ANIMATION, WATER_ATTACK_ANIMATION};
    private int animationTick;
    public AnimationAI currentAnim;
    private int meleeAttackCooldown = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 100;
    private int hairAttackCooldown = 0;
    public static final int HAIR_ATTACK_COOLDOWN = 200;
    private int waterAttackCooldown = 0;
    public static final int WATER_ATTACK_COOLDOWN = 200;

    private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS));

    public EntityRusalka(World worldIn) {
        super(worldIn);
        this.setSize(0.75F, 2.0F);
        this.experienceValue = (int) 20.00F;
        this.navigator = createNavigator(worldIn);
        this.setPathPriority(PathNodeType.WATER, 1.0F);
        this.setPathPriority(PathNodeType.WALKABLE, -1.0F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new AIMeleeAttack(this, MELEE_ATTACK_ANIMATION, 1.2D, false));
        this.tasks.addTask(2, new AIHairAttack(this, HAIR_ATTACK_ANIMATION, 1.0D, false));
        this.tasks.addTask(2, new AIWaterAttack(this, WATER_ATTACK_ANIMATION, 1.2D, false));
        this.tasks.addTask(7, new AIEntityOnWaterWanderAvoidGround(this, 1D));
        this.tasks.addTask(3, new AIEntityFleeGround(this, 1.0D));
//        this.tasks.addTask(2, new AIEntityRestrictGround(this));
        this.tasks.addTask(5, new AIEntityMoveTowardRestrictionWater(this, 1.0D));
        this.targetTasks.addTask(4, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1400.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);

    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote) {
            double radius = 16.0D;
            AxisAlignedBB area = new AxisAlignedBB(
                    this.posX - radius, this.posY - radius, this.posZ - radius,
                    this.posX + radius, this.posY + radius, this.posZ + radius
            );

            List<EntityPlayerMP> playersInRange = this.world.getEntitiesWithinAABB(EntityPlayerMP.class, area);

            for (EntityPlayerMP player : playersInRange) {
                if (!this.bossInfo.getPlayers().contains(player)) {
                    this.bossInfo.addPlayer(player);
                }
            }

            List<EntityPlayerMP> playersToRemove = new ArrayList<>(this.bossInfo.getPlayers());
            for (EntityPlayerMP player : playersToRemove) {
                if (!playersInRange.contains(player)) {
                    this.bossInfo.removePlayer(player);
                }
            }
        }

        if (getAttackTarget() != null) {
            if (targetDistance <= 2.25F && getAnimation() == NO_ANIMATION && meleeAttackCooldown <= 0 && this.isEntityAlive()) {
                if (rand.nextInt(4) == 0) {
                    AnimationHandler.INSTANCE.sendAnimationMessage(this, MELEE_ATTACK_ANIMATION);
                    this.meleeAttackCooldown = MELEE_ATTACK_COOLDOWN;
                }
            }
            if (targetDistance <= 6.0F && getAnimation() == NO_ANIMATION && hairAttackCooldown <= 0 && this.isEntityAlive()) {
                if (rand.nextInt(4) == 1) {
                    AnimationHandler.INSTANCE.sendAnimationMessage(this, HAIR_ATTACK_ANIMATION);
                    this.hairAttackCooldown = HAIR_ATTACK_COOLDOWN;
                }
            }
            if (targetDistance > 6.0F && getAnimation() == NO_ANIMATION && waterAttackCooldown <= 0 && this.isEntityAlive()) {
                if (rand.nextInt(4) == 2) {
                    AnimationHandler.INSTANCE.sendAnimationMessage(this, WATER_ATTACK_ANIMATION);
                    this.waterAttackCooldown = WATER_ATTACK_COOLDOWN;
                }
            }
            if (targetDistance <= 2.25F && getHealth() / getMaxHealth() * 100 <= 25.0F && getAnimation() == NO_ANIMATION && waterAttackCooldown <= 0 && this.isEntityAlive()) {
                if (rand.nextInt(4) == 3) {
                    AnimationHandler.INSTANCE.sendAnimationMessage(this, WATER_ATTACK_ANIMATION);
                    this.waterAttackCooldown = WATER_ATTACK_COOLDOWN;
                }
            }
        }
        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }

        if (this.getWaterCheck() && this.world.getBlockState(this.getPosition()) == Blocks.WATER.getDefaultState() || this.world.getBlockState(this.getPosition()) == Blocks.FLOWING_WATER.getDefaultState()) {
            this.motionY = 0.1F;
        } else if (this.getWaterCheck()) {
            this.motionY = 0.0F;
            this.onGround = true;
        }

        if (meleeAttackCooldown > 0) meleeAttackCooldown--;
        if (hairAttackCooldown > 0) hairAttackCooldown--;
        if (waterAttackCooldown > 0) waterAttackCooldown--;

        this.doBlockCollisions();
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
        this.doBlockCollisions();
        if (this.isInWater()) {
            this.fallDistance = 0.0F;
        } else {
            super.updateFallState(y, onGroundIn, state, pos);
        }
    }

    @Override
    public PathNavigate getNavigator() {
        return this.navigator;
    }

    @Override
    protected @NotNull PathNavigate createNavigator(World world) {
        return new PathNavigateOnWater(this, world);
    }

    @Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        if (player.getDistance(this) <= 16) {
            super.addTrackingPlayer(player);
            this.bossInfo.addPlayer(player);
        }

    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        if (player.getDistance(this) > 16) {
            super.removeTrackingPlayer(player);
            this.bossInfo.removePlayer(player);
        }
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getThunderDamage();
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getWaterDamage();
                }
            }
        }
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (hasWeakness(source, amount)) {
            return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount * amount);
        }
        if (hasStrength(source, amount)) {
            return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount / amount);
        }
        return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }


    @Override
    public float getBlockPathWeight(BlockPos pos) {
        IBlockState state = this.world.getBlockState(pos);

        return state.getBlock() == Blocks.WATER || state.getBlock() == Blocks.FLOWING_WATER ? 10.0F : -1.0F;
    }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos pos = new BlockPos(this.posX, this.posY - 1, this.posZ);
        return this.world.getBlockState(pos).getBlock() == Blocks.WATER || this.world.getBlockState(pos).getBlock() == Blocks.FLOWING_WATER;
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

    public boolean getWaterCheck() {
        return this.world.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getMaterial() == Material.WATER;
    }
}
