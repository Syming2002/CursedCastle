package com.syming.castlevaniacraft.entity.mobs;

import com.syming.castlevaniacraft.api.IElementalWeapon;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.entity.ai.shade.frozenShade.AIIdle;
import com.syming.castlevaniacraft.entity.ai.shade.frozenShade.AIRepulseAttack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import org.jetbrains.annotations.Nullable;

public class EntityVampireBat extends CCEntityMob implements EntityFlying {

    private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte>createKey(EntityVampireBat.class, DataSerializers.BYTE);
    /**
     * Coordinates of where the bat spawned.
     */
    private BlockPos spawnPosition;

    public EntityVampireBat(World worldIn) {
        super(worldIn);
        this.experienceValue = 1;
        this.setSize(0.5F, 0.9F);
        this.setIsBatHanging(true);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (hasWeakness(source, amount) || source == DamageSource.ON_FIRE) {
            return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount * amount);
        }
        if (hasStrength(source, amount)) {
            return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount / amount);
        }
        return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getFireDamage();
                }
            }
        }
        return source == DamageSource.ON_FIRE;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getDarkDamage();
                }
            }
        }
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(HANGING, Byte.valueOf((byte) 0));
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
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
        return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
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
        super.collideWithEntity(entityIn);
    }

    @Override
    protected void collideWithNearbyEntities()
    {
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, true));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityVillager.class, 8.0F));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    public boolean getIsBatHanging()
    {
        return (((Byte)this.dataManager.get(HANGING)).byteValue() & 1) != 0;
    }

    public void setIsBatHanging(boolean isHanging)
    {
        byte b0 = ((Byte)this.dataManager.get(HANGING)).byteValue();

        if (isHanging)
        {
            this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.getIsBatHanging()) {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.posY = (double) MathHelper.floor(this.posY) + 1.0D - (double) this.height;
        } else {
            this.motionY *= 0.6000000238418579D;
        }

        if (this.world.isDaytime() && !this.world.isRemote && this.shouldBurnInDay()) {
            float f = this.getBrightness();

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ))) {
                this.setFire(8);
            }
        }
    }

    protected boolean shouldBurnInDay()
    {
        return true;
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.up();

        if (this.getIsBatHanging()) {
            if (this.world.getBlockState(blockpos1).isNormalCube()) {
                if (this.rand.nextInt(200) == 0) {
                    this.rotationYawHead = (float) this.rand.nextInt(360);
                }

                if (this.world.getNearestPlayerNotCreative(this, 4.0D) != null) {
                    this.setIsBatHanging(false);
                    this.world.playEvent((EntityPlayer) null, 1025, blockpos, 0);
                }
            } else {
                this.setIsBatHanging(false);
                this.world.playEvent((EntityPlayer) null, 1025, blockpos, 0);
            }
        } else {
            if (this.getAttackTarget() == null) {
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

                if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube()) {
                    this.setIsBatHanging(true);
                }
            } else {
                double posX = this.getAttackTarget().posX - this.posX;
                double posY = this.getAttackTarget().posY + (double) (this.getAttackTarget().getEyeHeight() * 0.66F) - this.posY;
                double posZ = this.getAttackTarget().posZ - this.posZ;
                this.motionX += (Math.signum(posX) * 0.5D - this.motionX) * 0.10000000149011612D;
                this.motionY += (Math.signum(posY) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
                this.motionZ += (Math.signum(posZ) * 0.5D - this.motionZ) * 0.10000000149011612D;
                float f = (float) (MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
                float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
                this.moveForward = 0.5F;
                this.rotationYaw += f1;
            }
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
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
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
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }

    @Override
    protected void onDeathUpdate() {
       super.onDeathUpdate();
    }
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(HANGING, Byte.valueOf(compound.getByte("BatFlags")));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("BatFlags", ((Byte)this.dataManager.get(HANGING)).byteValue());
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (blockpos.getY() >= this.world.getSeaLevel())
        {
            return false;
        }
        else
        {
            int i = this.world.getLightFromNeighbors(blockpos);
            int j = 4;

            if (this.rand.nextBoolean())
            {
                return false;
            }

            return i > this.rand.nextInt(j) ? false : super.getCanSpawnHere();
        }
    }

    @Override
    public float getEyeHeight()
    {
        return this.height / 2.0F;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_BAT;
    }
}
