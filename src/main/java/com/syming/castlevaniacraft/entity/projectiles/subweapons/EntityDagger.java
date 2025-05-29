package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import baubles.api.BaublesApi;
import baubles.api.cap.BaublesContainer;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.item.projectiles.subweapons.ItemDagger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityDagger extends EntityArrow {

//    public static final DataParameter<Boolean> INITIATED = EntityDataManager.createKey(EntityDagger.class, DataSerializers.BOOLEAN);
    public static final DataParameter<ItemStack> STACK = EntityDataManager.createKey(EntityDagger.class, DataSerializers.ITEM_STACK);
    public static final DataParameter<Integer> DURATION = EntityDataManager.createKey(EntityDagger.class, DataSerializers.VARINT);
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    private int ticksInAir;
    private double damage;
    private Item.ToolMaterial material;

    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;

    public EntityDagger(World world) {
        super(world);
    }

    /* this constructor is for thrower mob */
    public EntityDagger(World world, EntityLivingBase thrower, ItemStack stack, Item.ToolMaterial material) {
        super(world, thrower);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.damage = 2.0D + material.getAttackDamage();
        this.material = material;

        setItemStack(stack);
//        setInited();

        if (thrower instanceof EntityPlayer)
        {
            this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        }
    }

    public EntityDagger(World world, EntityLivingBase thrower, ItemStack stack, int durationLoaded, Item.ToolMaterial material) {
        super(world, thrower);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.damage = 2.0D + material.getAttackDamage();
        this.material = material;

        setItemStack(stack);
        setDurLoaded(durationLoaded);
//        setInited();

        if (durationLoaded < 20) {
            durationLoaded = 20;
        } else if (durationLoaded > 40) {
            durationLoaded = 40;
        }
        this.motionX *= (durationLoaded / 20D);
        this.motionY *= (durationLoaded / 20D);
        this.motionZ *= (durationLoaded / 20D);

        if (thrower instanceof EntityPlayer)
        {
            this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        }
    }

    public EntityDagger(World world, EntityLivingBase thrower, ItemStack stack, double x,double y, double z, double accelX, double accelY, double accelZ, Item.ToolMaterial material) {
        super(world, thrower);
        this.damage = 2.0D + material.getAttackDamage();
        this.material = material;

        setItemStack(stack);

        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        double d0 = (double)MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(STACK, ItemStack.EMPTY);
//        dataManager.register(INITIATED, false);
        dataManager.register(DURATION, 1);
    }

    public int getDurLoaded() {
        return dataManager.get(DURATION);
    }

    public ItemStack getItemStack() {
        return dataManager.get(STACK);
    }

    @Override
    public boolean hasNoGravity()
    {
        if (this.shootingEntity != null) {
            return isFireSpellBookEquipped((EntityPlayer) this.shootingEntity);
        }
        return false;
    }


    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.shootingEntity != null) {
            if (isFireSpellBookEquipped((EntityPlayer) this.shootingEntity)) {
                if (this.world.isRemote) {
                    double offsetX = (this.rand.nextDouble() - 0.5D) * 0.5D;
                    double offsetY = this.rand.nextDouble() * 0.02D;
                    double offsetZ = (this.rand.nextDouble() - 0.5D) * 0.5D;

                    double particleX = this.posX + offsetX;
                    double particleY = this.posY + offsetY;
                    double particleZ = this.posZ + offsetZ;

                    double motionY = 0.02D + this.rand.nextDouble() * 0.02D;

                    this.world.spawnParticle(EnumParticleTypes.FLAME, particleX, particleY, particleZ, 0.0D, motionY, 0.0D);
                }

                this.posX += this.motionX;
                this.posY += this.motionY;
                this.posZ += this.motionZ;
                ProjectileHelper.rotateTowardsMovement(this, 0.2F);
                float f = this.getMotionFactor();

                if (this.isInWater())
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        float f1 = 0.25F;
                        this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                    }

                    f = 0.8F;
                }

                this.motionX += this.accelerationX;
                this.motionY += this.accelerationY;
                this.motionZ += this.accelerationZ;
                this.motionX *= (double)f;
                this.motionY *= (double)f;
                this.motionZ *= (double)f;
                this.setPosition(this.posX, this.posY, this.posZ);
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        Entity entity = raytraceResultIn.entityHit;

        if (entity != null)
        {
            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            int i = MathHelper.ceil((double)f * this.damage);

            if (this.getIsCritical())
            {
                i += this.rand.nextInt(i / 2 + 2);
            }

            DamageSource damagesource;

            if (this.shootingEntity == null)
            {
                damagesource = DamageSource.causeArrowDamage(this, this);
            }
            else
            {
                damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
            }

            if (this.isBurning() && !(entity instanceof EntityEnderman))
            {
                entity.setFire(5);
            }

            if (entity.attackEntityFrom(damagesource, (float)i))
            {
                if (entity instanceof EntityLivingBase)
                {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

                    if (this.shootingEntity instanceof EntityLivingBase)
                    {
                        EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entitylivingbase);
                    }

                    this.arrowHit(entitylivingbase);

                    if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                }

                this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                if (!(entity instanceof EntityEnderman))
                {
                    this.setDead();
                }

                if (this.shootingEntity != null) {
                    if (isFireSpellBookEquipped((EntityPlayer) this.shootingEntity)) {
                        entity.setFire(5);
                    }
                }
            }
            else
            {
                this.motionX *= -0.10000000149011612D;
                this.motionY *= -0.10000000149011612D;
                this.motionZ *= -0.10000000149011612D;
                this.rotationYaw += 180.0F;
                this.prevRotationYaw += 180.0F;
                this.ticksInAir = 0;

                if (!this.world.isRemote && this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ < 0.0010000000474974513D)
                {
                    if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED)
                    {
                        this.entityDropItem(this.getArrowStack(), 0.1F);
                    }

                    this.setDead();
                }
            }
        }
        else
        {
            BlockPos blockpos = raytraceResultIn.getBlockPos();
            this.xTile = blockpos.getX();
            this.yTile = blockpos.getY();
            this.zTile = blockpos.getZ();
            IBlockState iblockstate = this.world.getBlockState(blockpos);
            this.inTile = iblockstate.getBlock();
            this.inData = this.inTile.getMetaFromState(iblockstate);
            this.motionX = (double)((float)(raytraceResultIn.hitVec.x - this.posX));
            this.motionY = (double)((float)(raytraceResultIn.hitVec.y - this.posY));
            this.motionZ = (double)((float)(raytraceResultIn.hitVec.z - this.posZ));
            float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
            this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
            this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
            this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            this.inGround = true;
            this.arrowShake = 7;
            this.setIsCritical(false);

            if (iblockstate.getMaterial() != Material.AIR)
            {
                this.inTile.onEntityCollision(this.world, blockpos, iblockstate, this);
            }
        }
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {

        super.readEntityFromNBT(tagCompund);
        setDurLoaded(tagCompund.getInteger("durloaded"));
        setItemStack(new ItemStack(tagCompund.getCompoundTag("item")));
        this.xTile = tagCompund.getInteger("xTile");
        this.yTile = tagCompund.getInteger("yTile");
        this.zTile = tagCompund.getInteger("zTile");
        this.inData = tagCompund.getByte("inData") & 255;

        if (tagCompund.hasKey("damage", 99))
        {
            this.damage = tagCompund.getDouble("damage");
        }

        if (this.shootingEntity != null) {
            if (isFireSpellBookEquipped((EntityPlayer) this.shootingEntity)) {
                if (tagCompund.hasKey("power", 9)) {
                    NBTTagList nbttaglist = tagCompund.getTagList("power", 6);

                    if (nbttaglist.tagCount() == 3) {
                        this.accelerationX = nbttaglist.getDoubleAt(0);
                        this.accelerationY = nbttaglist.getDoubleAt(1);
                        this.accelerationZ = nbttaglist.getDoubleAt(2);
                    }
                }
            }
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        ItemStack stack = this.getItemStack();
        if (stack.getItem() instanceof ItemDagger itemDagger) {
            switch (itemDagger.getMaterial()) {
                case WOOD:
                    return new ItemStack(CCItem.woodenDagger);
                case STONE:
                    return new ItemStack(CCItem.stoneDagger);
                case IRON:
                    return new ItemStack(CCItem.ironDagger);
                case GOLD:
                    return new ItemStack(CCItem.goldenDagger);
                case DIAMOND:
                    return new ItemStack(CCItem.diamondDagger);
            }
        }
        return stack;
    }

    public void setDurLoaded(int durloaded) {

        dataManager.set(DURATION, durloaded);
    }

    public void setItemStack(ItemStack stack) {

        if (stack == null || stack.isEmpty() || !(stack.getItem() instanceof ItemDagger)) {
            throw new IllegalArgumentException("Invalid Itemstack!");
        }
        dataManager.set(STACK, stack);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {

        super.writeEntityToNBT(tagCompound);

        tagCompound.setInteger("durloaded", getDurLoaded());

        if (getItemStack() != null && !getItemStack().isEmpty()) {
            tagCompound.setTag("item", getItemStack().writeToNBT(new NBTTagCompound()));
        }

        tagCompound.setInteger("xTile", this.xTile);
        tagCompound.setInteger("yTile", this.yTile);
        tagCompound.setInteger("zTile", this.zTile);
        tagCompound.setByte("inData", (byte)this.inData);
        tagCompound.setDouble("damage", this.damage);

        if (this.shootingEntity != null) {
            if (isFireSpellBookEquipped((EntityPlayer) this.shootingEntity)) {
                tagCompound.setTag("power", this.newDoubleNBTList(new double[]{this.accelerationX, this.accelerationY, this.accelerationZ}));
            }
        }
    }

//    public void setInited() {
//        dataManager.set(INITIATED, true);
//    }

    protected float getMotionFactor()
    {
        return 0.95F;
    }


    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        super.onCollideWithPlayer(entityIn);
    }
    @Override
    @Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end) {
        return super.findEntityOnPath(start, end);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        super.setPositionAndRotationDirect(x, y, z, yaw, pitch, posRotationIncrements, teleport);
    }

    private boolean isFireSpellBookEquipped(EntityPlayer thrower) {
        BaublesContainer baubles = (BaublesContainer) BaublesApi.getBaublesHandler(thrower);

        ItemStack belt = baubles.getStackInSlot(4);

        return !belt.isEmpty() && belt.getItem() == CCItem.fireBook;
    }
}
