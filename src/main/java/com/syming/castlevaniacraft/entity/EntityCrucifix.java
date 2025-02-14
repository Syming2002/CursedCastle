package com.syming.castlevaniacraft.entity;

import com.google.common.base.Predicate;
import com.syming.castlevaniacraft.item.MVItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Rotations;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.List;

public class EntityCrucifix extends EntityLiving {

    private static final Rotations DEFAULT_MODEL_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
    public static final DataParameter<Byte> STATUS = EntityDataManager.<Byte>createKey(EntityCrucifix.class, DataSerializers.BYTE);
    public static final DataParameter<Rotations> MODEL_ROTATION = EntityDataManager.<Rotations>createKey(EntityCrucifix.class, DataSerializers.ROTATIONS);
    private static final Predicate<Entity> IS_RIDEABLE_MINECART = new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return p_apply_1_ instanceof EntityMinecart && ((EntityMinecart)p_apply_1_).canBeRidden();
        }
    };
    /** After punching the stand, the cooldown before you can punch it again without breaking it. */
    private boolean canInteract;
    public long punchCooldown;
    private boolean wasMarker;
    private Rotations modelRotation;
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityCrucifix.class, DataSerializers.VARINT);

    public EntityCrucifix(World worldIn) {
        super(worldIn);

        setSize(0.5F, 2.5F);
        this.modelRotation = DEFAULT_MODEL_ROTATION;
        this.noClip = this.hasNoGravity();
    }

    public EntityCrucifix(World worldIn, double posX, double posY, double posZ, BlockPlanks.EnumType woodType)
    {
        this(worldIn);
        this.setPosition(posX, posY, posZ);
        this.setWoodType(woodType);
    }

    /**
     * Returns whether the entity is in a server world
     */
    @Override
    public boolean isServerWorld()
    {
        return super.isServerWorld() && !this.hasNoGravity();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(STATUS, Byte.valueOf((byte)0));
        this.dataManager.register(MODEL_ROTATION, DEFAULT_MODEL_ROTATION);
        this.dataManager.register(VARIANT, Integer.valueOf(0));
    }

    public void setWoodType(BlockPlanks.EnumType woodType) {
        this.dataManager.set(VARIANT, woodType.getMetadata());
    }

    public BlockPlanks.EnumType getWoodType() {
        return BlockPlanks.EnumType.byMetadata(this.dataManager.get(VARIANT));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Invisible", this.isInvisible());
        compound.setTag("Pose", this.readPoseFromNBT());
        compound.setInteger("WoodType", this.getWoodType().getMetadata());
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        this.setInvisible(compound.getBoolean("Invisible"));
        this.noClip = this.hasNoGravity();
        NBTTagCompound nbttagcompound = compound.getCompoundTag("Pose");
        this.writePoseToNBT(nbttagcompound);
        this.setWoodType(BlockPlanks.EnumType.byMetadata(compound.getInteger("WoodType")));
    }

    /**
     * Saves the pose to an NBTTagCompound.
     */
    private void writePoseToNBT(NBTTagCompound tagCompound)
    {
        NBTTagList nbttaglist = tagCompound.getTagList("Model", 5);
        this.setModelRotation(nbttaglist.isEmpty() ? DEFAULT_MODEL_ROTATION : new Rotations(nbttaglist));
    }

    private NBTTagCompound readPoseFromNBT()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (!DEFAULT_MODEL_ROTATION.equals(this.modelRotation))
        {
            nbttagcompound.setTag("Model", this.modelRotation.writeToNBT());
        }

        return nbttagcompound;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (entityIn instanceof EntityMob target) {
            target.setFire(5);
        }
    }

    @Override
    protected void collideWithNearbyEntities()
    {
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), IS_RIDEABLE_MINECART);

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity = list.get(i);

            if (this.getDistanceSq(entity) <= 0.2D)
            {
                entity.applyEntityCollision(this);
            }
        }
    }

    private void playParticles()
    {
        if (this.world instanceof WorldServer)
        {
            ((WorldServer)this.world).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + (double)this.height / 1.5D, this.posZ, 10, (double)(this.width / 4.0F), (double)(this.height / 4.0F), (double)(this.width / 4.0F), 0.05D, Block.getStateId(Blocks.PLANKS.getDefaultState()));
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!this.world.isRemote && !this.isDead)
        {
            if (DamageSource.OUT_OF_WORLD.equals(source))
            {
                this.setDead();
                return false;
            }
            else if (!this.isEntityInvulnerable(source) && !this.canInteract && !this.hasMarker())
            {
                if (source.isExplosion())
                {
                    this.setDead();
                    return false;
                }
                else if (DamageSource.IN_FIRE.equals(source))
                {
                    if (this.isBurning())
                    {
                        this.damageCrucifix(0.15F);
                    }
                    else
                    {
                        this.setFire(5);
                    }

                    return false;
                }
                else if (DamageSource.ON_FIRE.equals(source) && this.getHealth() > 0.5F)
                {
                    this.damageCrucifix(4.0F);
                    return false;
                }
                else
                {
                    boolean flag = "arrow".equals(source.getDamageType());
                    boolean flag1 = "player".equals(source.getDamageType());

                    if (!flag1 && !flag)
                    {
                        return false;
                    }
                    else
                    {
                        if (source.getImmediateSource() instanceof EntityArrow)
                        {
                            source.getImmediateSource().setDead();
                        }

                        if (source.getTrueSource() instanceof EntityPlayer && !((EntityPlayer)source.getTrueSource()).capabilities.allowEdit)
                        {
                            return false;
                        }
                        else if (source.isCreativePlayer())
                        {
                            this.playBrokenSound();
                            this.playParticles();
                            this.setDead();
                            return false;
                        }
                        else
                        {
                            long i = this.world.getTotalWorldTime();

                            if (i - this.punchCooldown > 5L && !flag)
                            {
                                this.world.setEntityState(this, (byte)32);
                                this.punchCooldown = i;
                            }
                            else
                            {
                                this.dropBlock();
                                this.playParticles();
                                this.setDead();
                            }

                            return false;
                        }
                    }
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private void damageCrucifix(float damage) {
        float f = this.getHealth();
        f = f - damage;

        if (f <= 0.5F) {
            this.setDead();
        } else {
            this.setHealth(f);
        }
    }

    private void dropBlock() {
        this.playBrokenSound();
        BlockPlanks.EnumType woodType = this.getWoodType();
        switch (woodType) {
            case OAK:
                Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(MVItem.oakCrucifix));
            case SPRUCE:
                Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(MVItem.spruceCrucifix));
            case BIRCH:
                Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(MVItem.birchCrucifix));
            case JUNGLE:
                Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(MVItem.jungleCrucifix));
            case ACACIA:
                Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(MVItem.acaciaCrucifix));
            case DARK_OAK:
                Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(MVItem.darkOakCrucifix));
        }
    }

    private void playBrokenSound() {
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ARMORSTAND_BREAK, this.getSoundCategory(), 1.0F, 1.0F);
    }

    @Override
    protected float updateDistance(float p_110146_1_, float p_110146_2_)
    {
        this.prevRenderYawOffset = this.prevRotationYaw;
        this.renderYawOffset = this.rotationYaw;
        return 0.0F;
    }

    @Override
    public float getEyeHeight()
    {
        return this.isChild() ? this.height * 0.5F : this.height * 0.9F;
    }

    /**
     * Returns the Y Offset of this entity.
     */
    @Override
    public double getYOffset()
    {
        return this.hasMarker() ? 0.0D : 0.10000000149011612D;
    }

    @Override
    public void travel(float strafe, float vertical, float forward)
    {
        if (!this.hasNoGravity())
        {
            super.travel(strafe, vertical, forward);
        }
    }

    /**
     * Set the render yaw offset
     */
    @Override
    public void setRenderYawOffset(float offset)
    {
        this.prevRenderYawOffset = this.prevRotationYaw = offset;
        this.prevRotationYawHead = this.rotationYawHead = offset;
    }

    /**
     * Sets the head's yaw rotation of the entity.
     */
    @Override
    public void setRotationYawHead(float rotation)
    {
        this.prevRenderYawOffset = this.prevRotationYaw = rotation;
        this.prevRotationYawHead = this.rotationYawHead = rotation;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        Rotations rotations = (Rotations)this.dataManager.get(MODEL_ROTATION);

        if (!this.modelRotation.equals(rotations))
        {
            this.setModelRotation(rotations);
        }

        boolean flag = this.hasMarker();

        if (this.wasMarker != flag)
        {
            this.updateBoundingBox(flag);
            this.preventEntitySpawning = !flag;
            this.wasMarker = flag;
        }
    }

    private void updateBoundingBox(boolean p_181550_1_)
    {
        if (p_181550_1_)
        {
            this.setSize(0.0F, 0.0F);
        }
        else
        {
            this.setSize(2.25F, 4.1875F);
        }
    }

    private void setNoBasePlate(boolean noBasePlate)
    {
        this.dataManager.set(STATUS, Byte.valueOf(this.setBit(((Byte)this.dataManager.get(STATUS)).byteValue(), 8, noBasePlate)));
    }

    public boolean hasNoBasePlate()
    {
        return (((Byte)this.dataManager.get(STATUS)).byteValue() & 8) != 0;
    }

    /**
     * Marker defines where if true, the size is 0 and will not be rendered or intractable.
     */
    private void setMarker(boolean marker)
    {
        this.dataManager.set(STATUS, Byte.valueOf(this.setBit(((Byte)this.dataManager.get(STATUS)).byteValue(), 16, marker)));
        this.setSize(0.5F, 1.975F);
    }

    /**
     * Gets whether the armor stand has marker enabled. If true, the armor stand's bounding box is set to zero and
     * cannot be interacted with.
     */
    public boolean hasMarker()
    {
        return (((Byte)this.dataManager.get(STATUS)).byteValue() & 16) != 0;
    }

    private byte setBit(byte p_184797_1_, int p_184797_2_, boolean p_184797_3_)
    {
        if (p_184797_3_)
        {
            p_184797_1_ = (byte)(p_184797_1_ | p_184797_2_);
        }
        else
        {
            p_184797_1_ = (byte)(p_184797_1_ & ~p_184797_2_);
        }

        return p_184797_1_;
    }

    public void setModelRotation(Rotations vec)
    {
        this.modelRotation = vec;
        this.dataManager.set(MODEL_ROTATION, vec);
    }

    public Rotations getModelRotation()
    {
        return this.modelRotation;
    }

    @Override
    public void setInvisible(boolean invisible)
    {
        this.canInteract = invisible;
        super.setInvisible(invisible);
    }

    @Override
    protected SoundEvent getFallSound(int heightIn)
    {
        return SoundEvents.ENTITY_ARMORSTAND_FALL;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_ARMORSTAND_HIT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ARMORSTAND_BREAK;
    }

    /**
     * Returns false if the entity is an armor stand. Returns true for all other entity living bases.
     */
    @Override
    public boolean canBeHitWithPotion()
    {
        return true;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (STATUS.equals(key))
        {
            this.setSize(2.25F, 4.1875F);
        }

        super.notifyDataManagerChange(key);
    }

    @Override
    public boolean attackable()
    {
        return false;
    }

    @Override
    public EnumPushReaction getPushReaction()
    {
        return EnumPushReaction.IGNORE;
    }
}
