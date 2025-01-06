package com.syming.minevania.entity.mobs.friendly;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.*;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class EntityFriendlyBat extends EntityCreature implements EntityFlying {

    private EntityLivingBase target;
    private EntityPlayer summoner;
    private BlockPos spawnPosition;

    public EntityFriendlyBat(World worldIn) {
        super(worldIn);
        this.summoner = worldIn.getPlayerEntityByUUID(this.getUniqueID());
        this.setSize(0.5F, 0.9F);

    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(2, new EntityFriendlyBat.EntityFriendlyBatAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityMob.class, 8.0F));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityMob>(this, EntityMob.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    }

    @Override
    public PathNavigate getNavigator() {
        return super.getNavigator();
    }

    @Override
    protected PathNavigate createNavigator(World world) {
        return new PathNavigateFlying(this, world);
    }

    @Override
    public void setAttackTarget(EntityLivingBase target) {
        super.setAttackTarget(target);

        if (target instanceof EntityMob) {
            this.target = target;
        }
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume()
    {
        return 0.1F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    @Override
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.95F;
    }

    @Override
    @Nullable
    public SoundEvent getAmbientSound()
    {
        return this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed()
    {
        return true;
    }

    @Override
    protected void collideWithEntity(Entity entityIn)
    {
    }

    @Override
    protected void collideWithNearbyEntities()
    {
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        followPlayer();
        this.motionY *= 0.6000000238418579D;

    }

    private void followPlayer() {

        EntityPlayer summoner;
        summoner = world.getClosestPlayerToEntity(this, -2);

        if (summoner != null && this.getAttackTarget() == null) {
            double moveSpeed = 0.25F;

            if (!summoner.isEntityAlive() || summoner.getDistanceSq(this) > 64.0D) {
                summoner = null;
                return;
            }

            double distanceToPlayer = this.getDistanceSq(summoner);

            if (distanceToPlayer > 2.0D) {
                // Move towards the player
                double dX = summoner.posX - this.posX;
                double dY = summoner.posY - this.posY;
                double dZ = summoner.posZ - this.posZ;
                double distance = MathHelper.sqrt(dX * dX + dY * dY + dZ * dZ);

                this.motionX = dX / distance * moveSpeed;
                this.motionY = dY / distance * moveSpeed;
                this.motionZ = dZ / distance * moveSpeed;

                // Face the player
                this.rotationYaw = (float) Math.toDegrees(Math.atan2(dZ, dX)) - 90.0F;
            } else {
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
            }
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (this.summoner == null) {
            if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
                this.spawnPosition = null;
            }

            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double) ((int) this.posX), (double) ((int) this.posY), (double) ((int) this.posZ)) < 4.0D) {
                this.spawnPosition = new BlockPos((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }

            double d0 = (double) this.spawnPosition.getX() + 0.5D - this.posX;
            double d1 = (double) this.spawnPosition.getY() + 0.1D - this.posY;
            double d2 = (double) this.spawnPosition.getZ() + 0.5D - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
            this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
            this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
            float f = (float) (MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;
        }
    }


    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }

    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    @Override
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        else {
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0 && entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte)30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Override
    public float getEyeHeight() {
        return this.height / 2.0F;
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


    static class AIFlyTowardsTarget extends EntityAIBase {

        private final EntityFriendlyBat taskOwner;
        private double targetDistance;

        AIFlyTowardsTarget(EntityFriendlyBat friendlyBat, double targetDistance) {
            this.taskOwner = friendlyBat;
            this.targetDistance = targetDistance;
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            return this.taskOwner.getAttackTarget() != null;
        }

        @Override
        public boolean shouldContinueExecuting() {
            EntityLivingBase target = this.taskOwner.getAttackTarget();
            return target != null && target.isEntityAlive() /*&& this.taskOwner.getDistanceSq(target) > this.targetDistance * this.targetDistance*/;
        }

        @Override
        public void startExecuting() {
            EntityLivingBase target = taskOwner.getAttackTarget();
            if (target != null) {
                taskOwner.getMoveHelper().setMoveTo(target.posX, target.posY, target.posZ, 0.5F);
            }
        }

        @Override
        public void updateTask() {
            EntityLivingBase target = this.taskOwner.getAttackTarget();
            if (target != null) {
                double distanceSq = this.taskOwner.getDistanceSq(target);
                double targetDistanceSq = this.targetDistance * this.targetDistance;

                if (distanceSq > targetDistanceSq) {
                    // Calculate direction towards the target
                    double dX = target.posX - this.taskOwner.posX;
                    double dY = target.posY - this.taskOwner.posY;
                    double dZ = target.posZ - this.taskOwner.posZ;

                    // Normalize the direction vector
                    double length = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
                    if (length > 0.0D) {
                        dX /= length;
                        dY /= length;
                        dZ /= length;
                    }

                    // Move the entity towards the target with the target distance
                    double targetX = target.posX - dX * this.targetDistance;
                    double targetY = target.posY - dY * this.targetDistance;
                    double targetZ = target.posZ - dZ * this.targetDistance;

                    this.taskOwner.getMoveHelper().setMoveTo(targetX, targetY, targetZ, 0.5F);
                }
            }
        }
    }

    static class AILookAround extends EntityAIBase
    {
        private final EntityFriendlyBat parentEntity;

        public AILookAround(EntityFriendlyBat friendlyBat)
        {
            this.parentEntity = friendlyBat;
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            if (this.parentEntity.getAttackTarget() == null)
            {
                this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float)Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            }
            else
            {
                EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
                double d0 = 64.0D;

                if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D)
                {
                    double d1 = entitylivingbase.posX - this.parentEntity.posX;
                    double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
                    this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }
        }
    }

    static class AIRandomFly extends EntityAIBase
    {
        private final EntityFriendlyBat parentEntity;

        public AIRandomFly(EntityFriendlyBat friendlyBat)
        {
            this.parentEntity = friendlyBat;
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            if (parentEntity.getAttackTarget() != null)
                return false;

            EntityMoveHelper entityMoveHelper = this.parentEntity.getMoveHelper();
            double d0 = entityMoveHelper.getX() - this.parentEntity.posX;
            double d1 = entityMoveHelper.getY() - this.parentEntity.posY;
            double d2 = entityMoveHelper.getZ() - this.parentEntity.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0D || d3 > 3600.0D;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 0.5F);
        }
    }
    static class EntityFriendlyBatAIAttackMelee extends EntityAIBase
    {
        World world;
        protected EntityFriendlyBat attacker;
        /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
        protected int attackTick;
        /** The speed with which the mob will approach the target */
        double speedTowardsTarget;
        /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
        boolean longMemory;
        /** The PathEntity of our entity. */
        Path path;
        private int delayCounter;
        private double targetX;
        private double targetY;
        private double targetZ;
        protected final int attackInterval = 20;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;

        public EntityFriendlyBatAIAttackMelee(EntityFriendlyBat friendlyBat, double speedIn, boolean useLongMemory)
        {
            this.attacker = friendlyBat;
            this.world = friendlyBat.world;
            this.speedTowardsTarget = speedIn;
            this.longMemory = useLongMemory;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase == null)
            {
                return false;
            }
            else if (!entitylivingbase.isEntityAlive())
            {
                return false;
            }
            else
            {
                if (canPenalize)
                {
                    if (--this.delayCounter <= 0)
                    {
                        this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
                        this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
                        return this.path != null;
                    }
                    else
                    {
                        return true;
                    }
                }
                this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);

                if (this.path != null)
                {
                    return true;
                }
                else
                {
                    return this.getAttackReachSqr(entitylivingbase) >= this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase == null)
            {
                return false;
            }
            else if (!entitylivingbase.isEntityAlive())
            {
                return false;
            }
            else if (!this.longMemory)
            {
                return !this.attacker.getNavigator().noPath();
            }
            else
            {
                return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer)entitylivingbase).isSpectator() && !((EntityPlayer)entitylivingbase).isCreative();
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
            this.delayCounter = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer)entitylivingbase).isSpectator() || ((EntityPlayer)entitylivingbase).isCreative()))
            {
                this.attacker.setAttackTarget((EntityLivingBase)null);
            }

            this.attacker.getNavigator().clearPath();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
            double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            --this.delayCounter;

            if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
            {
                this.targetX = entitylivingbase.posX;
                this.targetY = entitylivingbase.getEntityBoundingBox().minY;
                this.targetZ = entitylivingbase.posZ;
                this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

                if (this.canPenalize)
                {
                    this.delayCounter += failedPathFindingPenalty;
                    if (this.attacker.getNavigator().getPath() != null)
                    {
                        net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                        if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    }
                    else
                    {
                        failedPathFindingPenalty += 10;
                    }
                }

                if (d0 > 1024.0D)
                {
                    this.delayCounter += 10;
                }
                else if (d0 > 256.0D)
                {
                    this.delayCounter += 5;
                }

                if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget))
                {
                    this.delayCounter += 15;
                }
            }

            this.attackTick = Math.max(this.attackTick - 1, 0);
            this.checkAndPerformAttack(entitylivingbase, d0);
        }

        protected void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr)
        {
            double d0 = this.getAttackReachSqr(enemy);

            if (distToEnemySqr <= d0 && this.attackTick <= 0)
            {
                this.attackTick = 20;
                this.attacker.swingArm(EnumHand.MAIN_HAND);
                this.attacker.attackEntityAsMob(enemy);
            }
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return (double)(this.attacker.width * 1.0 * this.attacker.width * 1.0 + attackTarget.width);
        }
    }
    abstract static class EntityFriendlyBatAITarget extends EntityAIBase
    {
        /** The entity that this task belongs to */
        protected final EntityFriendlyBat taskOwner;
        /** If true, EntityAI targets must be able to be seen (cannot be blocked by walls) to be suitable targets. */
        protected boolean shouldCheckSight;
        /** When true, only entities that can be reached with minimal effort will be targetted. */
        private final boolean nearbyOnly;
        /** When nearbyOnly is true: 0 -> No target, but OK to search; 1 -> Nearby target found; 2 -> Target too far. */
        private int targetSearchStatus;
        /** When nearbyOnly is true, this throttles target searching to avoid excessive pathfinding. */
        private int targetSearchDelay;
        /**
         * If  @shouldCheckSight is true, the number of ticks before the interuption of this AITastk when the entity does't
         * see the target
         */
        private int targetUnseenTicks;
        protected EntityLivingBase target;
        protected int unseenMemoryTicks;

        public EntityFriendlyBatAITarget(EntityFriendlyBat friendlyBat, boolean checkSight)
        {
            this(friendlyBat, checkSight, false);
        }

        public EntityFriendlyBatAITarget(EntityFriendlyBat friendlyBat, boolean checkSight, boolean onlyNearby)
        {
            this.unseenMemoryTicks = 60;
            this.taskOwner = friendlyBat;
            this.shouldCheckSight = checkSight;
            this.nearbyOnly = onlyNearby;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            EntityLivingBase entitylivingbase = this.taskOwner.getAttackTarget();

            if (entitylivingbase == null)
            {
                entitylivingbase = this.target;
            }

            if (entitylivingbase == null)
            {
                return false;
            }
            else if (!entitylivingbase.isEntityAlive())
            {
                return false;
            }
            else
            {
                Team team = this.taskOwner.getTeam();
                Team team1 = entitylivingbase.getTeam();

                if (team != null && team1 == team)
                {
                    return false;
                }
                else
                {
                    double d0 = this.getTargetDistance();

                    if (this.taskOwner.getDistanceSq(entitylivingbase) > d0 * d0)
                    {
                        return false;
                    }
                    else
                    {
                        if (this.shouldCheckSight)
                        {
                            if (this.taskOwner.getEntitySenses().canSee(entitylivingbase))
                            {
                                this.targetUnseenTicks = 0;
                            }
                            else if (++this.targetUnseenTicks > this.unseenMemoryTicks)
                            {
                                return false;
                            }
                        }

                        if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer)entitylivingbase).capabilities.disableDamage)
                        {
                            return false;
                        }
                        else
                        {
                            this.taskOwner.setAttackTarget(entitylivingbase);
                            return true;
                        }
                    }
                }
            }
        }

        protected double getTargetDistance()
        {
            IAttributeInstance iattributeinstance = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.targetSearchStatus = 0;
            this.targetSearchDelay = 0;
            this.targetUnseenTicks = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            this.taskOwner.setAttackTarget((EntityLivingBase)null);
            this.target = null;
        }

        /**
         * A static method used to see if an entity is a suitable target through a number of checks.
         */
        public static boolean isSuitableTarget(EntityLiving attacker, @javax.annotation.Nullable EntityLivingBase target, boolean includeInvincibles, boolean checkSight)
        {
            if (target == null)
            {
                return false;
            }
            else if (target == attacker)
            {
                return false;
            }
            else if (!target.isEntityAlive())
            {
                return false;
            }
            else if (!attacker.canAttackClass(target.getClass()))
            {
                return false;
            }
            else if (attacker.isOnSameTeam(target))
            {
                return false;
            }
            else
            {
                if (attacker instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId() != null)
                {
                    if (target instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId().equals(((IEntityOwnable)target).getOwnerId()))
                    {
                        return false;
                    }

                    if (target == ((IEntityOwnable)attacker).getOwner())
                    {
                        return false;
                    }
                }
                else if (target instanceof EntityPlayer && !includeInvincibles && ((EntityPlayer)target).capabilities.disableDamage)
                {
                    return false;
                }

                return !checkSight || attacker.getEntitySenses().canSee(target);
            }
        }

        /**
         * A method used to see if an entity is a suitable target through a number of checks. Args : entity,
         * canTargetInvinciblePlayer
         */
        protected boolean isSuitableTarget(@javax.annotation.Nullable EntityLivingBase target, boolean includeInvincibles)
        {
            if (!isSuitableTarget(this.taskOwner, target, includeInvincibles, this.shouldCheckSight))
            {
                return false;
            } else {
                if (this.nearbyOnly)
                {
                    if (--this.targetSearchDelay <= 0)
                    {
                        this.targetSearchStatus = 0;
                    }

                    if (this.targetSearchStatus == 0)
                    {
                        this.targetSearchStatus = this.canEasilyReach(target) ? 1 : 2;
                    }

                    if (this.targetSearchStatus == 2)
                    {
                        return false;
                    }
                }

                return true;
            }
        }

        /**
         * Checks to see if this entity can find a short path to the given target.
         */
        private boolean canEasilyReach(EntityLivingBase target)
        {
            this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
            Path path = this.taskOwner.getNavigator().getPathToEntityLiving(target);

            if (path == null)
            {
                return false;
            }
            else
            {
                PathPoint pathpoint = path.getFinalPathPoint();

                if (pathpoint == null)
                {
                    return false;
                }
                else
                {
                    int i = pathpoint.x - MathHelper.floor(target.posX);
                    int j = pathpoint.z - MathHelper.floor(target.posZ);
                    return (double)(i * i + j * j) <= 2.25D;
                }
            }
        }

        public EntityFriendlyBatAITarget setUnseenMemoryTicks(int p_190882_1_)
        {
            this.unseenMemoryTicks = p_190882_1_;
            return this;
        }
    }

    public static class EntityFriendlyBatAINearestAttackableTarget<T extends EntityLivingBase> extends EntityFriendlyBatAITarget {
        protected final Class<T> targetClass;
        private final int targetChance;
        /** Instance of EntityAINearestAttackableTargetSorter. */
        protected final EntityFriendlyBatAINearestAttackableTarget.Sorter sorter;
        protected final Predicate<? super T > targetEntitySelector;
        protected T targetEntity;

        public EntityFriendlyBatAINearestAttackableTarget(EntityFriendlyBat friendlyBat, Class<T> classTarget, boolean checkSight) {
            this(friendlyBat, classTarget, checkSight, false);
        }

        public EntityFriendlyBatAINearestAttackableTarget(EntityFriendlyBat friendlyBat, Class<T> classTarget, boolean checkSight, boolean onlyNearby) {
            this(friendlyBat, classTarget, 10, checkSight, onlyNearby, (Predicate)null);
        }

        public EntityFriendlyBatAINearestAttackableTarget(EntityFriendlyBat friendlyBat, Class<T> classTarget, int chance, boolean checkSight, boolean onlyNearby, @Nullable final Predicate <? super T > targetSelector) {
            super(friendlyBat, checkSight, onlyNearby);
            this.targetClass = classTarget;
            this.targetChance = chance;
            this.sorter = new EntityFriendlyBatAINearestAttackableTarget.Sorter(friendlyBat);
            this.setMutexBits(1);
            this.targetEntitySelector = new Predicate<T>() {
                public boolean apply(@javax.annotation.Nullable T p_apply_1_) {
                    if (p_apply_1_ == null) {
                        return false;
                    }
                    else if (targetSelector != null && !targetSelector.apply(p_apply_1_)) {
                        return false;
                    }
                    else {
                        return !EntitySelectors.NOT_SPECTATING.apply(p_apply_1_) ? false : EntityFriendlyBatAINearestAttackableTarget.this.isSuitableTarget(p_apply_1_, false);
                    }
                }
            };
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
                return false;
            }
            else if (this.targetClass != EntityPlayer.class && this.targetClass != EntityPlayerMP.class) {
                List<T> list = this.taskOwner.world.<T>getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);

                if (list.isEmpty()) {
                    return false;
                }
                else {
                    Collections.sort(list, this.sorter);
                    this.targetEntity = list.get(0);
                    return true;
                }
            }
            else {
                this.targetEntity = (T)this.taskOwner.world.getNearestAttackablePlayer(this.taskOwner.posX, this.taskOwner.posY + (double)this.taskOwner.getEyeHeight(), this.taskOwner.posZ, this.getTargetDistance(), this.getTargetDistance(), new Function<EntityPlayer, Double>() {
                    @Nullable
                    public Double apply(@Nullable EntityPlayer p_apply_1_)
                    {
                        return 1.0D;
                    }
                }, (Predicate<EntityPlayer>)this.targetEntitySelector);
                return this.targetEntity != null;
            }
        }

        protected AxisAlignedBB getTargetableArea(double targetDistance) {
            return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.taskOwner.setAttackTarget(this.targetEntity);
            super.startExecuting();
        }

        public static class Sorter implements Comparator<Entity> {
            private final Entity entity;

            public Sorter(Entity entityIn)
            {
                this.entity = entityIn;
            }

            public int compare(Entity p_compare_1_, Entity p_compare_2_) {
                double d0 = this.entity.getDistanceSq(p_compare_1_);
                double d1 = this.entity.getDistanceSq(p_compare_2_);

                if (d0 < d1) {
                    return -1;
                }
                else {
                    return d0 > d1 ? 1 : 0;
                }
            }
        }
    }

    static class EntityFriendlyBatAIFollowOwner<T extends EntityLiving & IEntityOwnable> extends EntityAIBase {
        private T friendlyBat;
        private Entity owner;
        World world;
        private final double followSpeed;
        private final PathNavigate petPathfinder;
        private int timeToRecalcPath;
        float maxDist;
        float minDist;

        public EntityFriendlyBatAIFollowOwner(T friendlyBat, double followSpeedIn, float minDistIn, float maxDistIn) {
            this.friendlyBat = friendlyBat;
            this.world = friendlyBat.world;
            this.followSpeed = followSpeedIn;
            this.petPathfinder = friendlyBat.getNavigator();
            this.minDist = minDistIn;
            this.maxDist = maxDistIn;
            this.setMutexBits(3);

            if (!(friendlyBat.getNavigator() instanceof PathNavigateGround) && !(friendlyBat.getNavigator() instanceof PathNavigateFlying)) {
                throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
            }
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            Entity entity = this.friendlyBat.getOwner();

            if (entity == null) {
                return false;
            } else if (entity instanceof EntityPlayer && ((EntityPlayer) entity).isSpectator()) {
                return false;
            } else if (this.friendlyBat.getDistanceSq(entity) < (double) (this.minDist * this.minDist)) {
                return false;
            } else {
                this.owner = entity;
                return true;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.petPathfinder.noPath() && this.friendlyBat.getDistanceSq(this.owner) > (double) (this.maxDist * this.maxDist);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.timeToRecalcPath = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.owner = null;
            this.petPathfinder.clearPath();
        }

        private boolean isAirBlock(BlockPos pos) {
            IBlockState state = this.world.getBlockState(pos);
            return state.getBlock() == Blocks.AIR || !state.isFullCube();

        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {
            this.friendlyBat.getLookHelper().setLookPositionWithEntity(this.owner, 10.0F, (float) this.friendlyBat.getVerticalFaceSpeed());

                if (--this.timeToRecalcPath <= 0)
                {
                    this.timeToRecalcPath = 10;

                    if (!this.petPathfinder.tryMoveToEntityLiving(this.owner, this.followSpeed))
                    {
                        if (!this.friendlyBat.getLeashed())
                        {
                            if (this.friendlyBat.getDistanceSq(this.owner) >= 144.0D)
                            {
                                int i = MathHelper.floor(this.owner.posX) - 2;
                                int j = MathHelper.floor(this.owner.posZ) - 2;
                                int k = MathHelper.floor(this.owner.getEntityBoundingBox().minY);

                                for (int l = 0; l <= 4; ++l)
                                {
                                    for (int i1 = 0; i1 <= 4; ++i1)
                                    {
                                        if ((l < 1 || i1 < 1 || l > 2 || i1 > 2) && this.isAirBlock(new BlockPos(i + l, k, j + i1)) && this.isAirBlock(new BlockPos(i + l, k + 1, j + i1))) {
                                        this.friendlyBat.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.friendlyBat.rotationYaw, this.friendlyBat.rotationPitch);
                                        this.petPathfinder.clearPath();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
