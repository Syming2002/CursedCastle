package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import com.syming.castlevaniacraft.entity.projectiles.MVThrowableWeapon;
import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.item.projectiles.subweapons.holycross.ItemHolyCross;
import com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe.ItemThrowingAxe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityThrowingAxe extends MVThrowableWeapon {


    public static final DataParameter<ItemStack> STACK = EntityDataManager.createKey(EntityThrowingAxe.class,
            DataSerializers.ITEM_STACK);

    private Item.ToolMaterial material;

    public EntityThrowingAxe(World worldIn) {
        super(worldIn);
    }

    public EntityThrowingAxe(World world, EntityLivingBase thrower, ItemStack stack, Item.ToolMaterial material) {
        super(world, thrower);
        this.material = material;
        setItemStack(stack);
        shoot(this.motionX, this.motionY, this.motionZ, 1.0F, 0.5F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();

        this.dataManager.register(STACK, ItemStack.EMPTY);
    }
    @Override
    protected float getGravityVelocity() {
        return 0.10F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        ItemStack stack = getItemStack();
        Entity hitEntity = result.entityHit;
        if (!this.world.isRemote && getThrower() != null) {
            if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                entityDropItem(getItemStack(), 0.5f);
                stack.attemptDamageItem(1, this.rand, getThrower() instanceof EntityPlayerMP ? (EntityPlayerMP) getThrower() : null);
                setDead();
            } else if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
                applyEntityHitEnchantments(hitEntity, stack);
                if (!stack.isItemEnchanted()) {
                    hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(hitEntity, getThrower()), getImpactDamage());
                }
                stack.attemptDamageItem(1, this.rand, (EntityPlayerMP) getThrower());
            }
        }
    }

    @Override
    protected float getImpactDamage() {
        return this.material.getAttackDamage() + 10.0F;
    }

    @Override
    public void onUpdate() {
        if (!isDead)
            super.onUpdate();
        if (ticksExisted > 100) {
            setDead();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {

        super.readEntityFromNBT(tagCompund);
        setItemStack(new ItemStack(tagCompund.getCompoundTag("ThrowingAxe")));
    }

    public void setItemStack(ItemStack stack) {
        if (!stack.isEmpty()) {
            this.dataManager.set(STACK, stack);
        }
    }

    public ItemStack getItemStack() {
        return this.dataManager.get(STACK);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        if (getItemStack() != null && !getItemStack().isEmpty()) {
            tagCompound.setTag("ThrowingAxe", getItemStack().writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        double length = MathHelper.sqrt(x * x + y * y + z * z);
        x /= length;
        y /= length;
        z /= length;

        x += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;

        x *= velocity;
        y *= velocity;
        z *= velocity;

        double time = 0.0;
        double amplitude = 0.5;
        double frequency = 1.0;

        double xOffset = amplitude * Math.sin(frequency * time);
        double yOffset = amplitude * Math.cos(frequency * time);

        x += xOffset;
        y += yOffset;

        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }
}
