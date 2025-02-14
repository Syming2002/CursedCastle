package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import com.syming.castlevaniacraft.item.MVItem;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityDagger extends EntityArrow {

    public static final DataParameter<Boolean> INITIATED = EntityDataManager.createKey(EntityDagger.class, DataSerializers.BOOLEAN);
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
        setInited();

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
        setInited();

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

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(STACK, ItemStack.EMPTY);
        dataManager.register(INITIATED, false);
        dataManager.register(DURATION, 1);
    }

    public int getDurLoaded() {
        return dataManager.get(DURATION);
    }

    public ItemStack getItemStack() {
        return dataManager.get(STACK);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
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

                    if (this.knockbackStrength > 0)
                    {
                        float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                        if (f1 > 0.0F)
                        {
                            entitylivingbase.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)f1, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)f1);
                        }
                    }

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
    }

    @Override
    protected ItemStack getArrowStack() {
        ItemStack stack = this.getItemStack();
        if (stack.getItem() instanceof ItemDagger itemDagger) {
            switch (itemDagger.getMaterial()) {
                case WOOD:
                    return new ItemStack(MVItem.woodenDagger);
                case STONE:
                    return new ItemStack(MVItem.stoneDagger);
                case IRON:
                    return new ItemStack(MVItem.ironDagger);
                case GOLD:
                    return new ItemStack(MVItem.goldenDagger);
                case DIAMOND:
                    return new ItemStack(MVItem.diamondDagger);
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
    }

    public void setInited() {
        dataManager.set(INITIATED, true);
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
}
