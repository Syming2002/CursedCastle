package com.syming.minevania.entity.projectiles.subweapons;

import com.syming.minevania.api.CompoundTags;
import com.syming.minevania.api.CCThrowable;
import com.syming.minevania.item.projectiles.subweapons.ItemHolyBible;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHolyBible extends CCThrowable {

    private int spiralTicks = 0;
    public static final DataParameter<Float> THROWER_X = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> THROWER_Y = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> THROWER_Z = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> YAW = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<ItemStack> STACK = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.ITEM_STACK);

    public EntityHolyBible(World world) {
        super(world);
    }

    public EntityHolyBible(World world, EntityLivingBase thrower, ItemStack stack) {
        super(world, thrower);

        setInited();
        setCoords(thrower.posX, thrower.posY + thrower.getEyeHeight() / 2, thrower.posZ);
        setItemStack(stack);
    }
    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(STACK, ItemStack.EMPTY);
        dataManager.register(YAW, 0F);
        dataManager.register(THROWER_X, 0F);
        dataManager.register(THROWER_Y, 0F);
        dataManager.register(THROWER_Z, 0F);
        rotationYaw = dataManager.get(YAW);
    }

    @Override
    protected float getGravityVelocity() {
        return 0f;
    }

    public double getPosX() {
        return dataManager.get(THROWER_X);
    }

    public double getPosY() {
        return dataManager.get(THROWER_Y);
    }

    public double getPosZ() {
        return dataManager.get(THROWER_Z);
    }


    public ItemStack getItemStack() {
        return dataManager.get(STACK);
    }

    public void setItemStack(ItemStack stack) {
        if (stack == null || stack.isEmpty() || !(stack.getItem() instanceof ItemHolyBible)) {
            throw new IllegalArgumentException("Invalid Itemstack!");
        }
        dataManager.set(STACK, stack);
    }

    @Override
    protected float getImpactDamage(Entity target) {
        return 20;
    }

    @Override
    protected boolean onEntityHit(Entity hitEntity) {
        // It's an hit entity
        hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(hitEntity, getThrower()), getImpactDamage(hitEntity));
        ItemStack stack = getItemStack();
        setItemStack(stack);
        return false;
    }

    @Override
    protected void onImpact(RayTraceResult target) {
        if (!world.isRemote) {
            isDead = false;
            if (target.entityHit == null) {
                boolean blockHitResult = onBlockHit(target.getBlockPos());
                if (blockHitResult) {
                    isDead = false;
                }
            } else {
                if (target.entityHit instanceof EntityLivingBase && target.entityHit.equals(getThrower()) && ticksExisted < 5) {
                    return;
                }

                boolean entityHitResult = onEntityHit(target.entityHit);
                if (entityHitResult) {
                    isDead = false;
                }
            }
            if (isDead) {
                setDead();
            }
        }
    }

    @Override
    public void onUpdated() {

        if (getThrower() != null && CompoundTags.giveCompound(getItemStack()).getBoolean(CompoundTags.ENCHANTED)) {
            setCoords(getThrower().posX, getThrower().posY + getThrower().getEyeHeight() / 2, getThrower().posZ);
        }

        if (getThrower() != null) {

            // Initial position set to the thrower's position
            double throwerX = getThrower().posX;
            double throwerY = getThrower().posY + getThrower().getEyeHeight() / 2;
            double throwerZ = getThrower().posZ;

            setCoords(getThrower().posX, getThrower().posY + getThrower().getEyeHeight() / 2, getThrower().posZ);

            double angle = rand.nextDouble() * Math.PI * 2;
            double distance = this.spiralTicks * 0.0078125;
            double speed = 0.125;

            // Random direction multiplier
            double dirX = MathHelper.cos((float) angle);
            double dirZ = MathHelper.sin((float) angle);
            double dirY = (rand.nextDouble() - 0.5) * 2; // Small random vertical movement

            // Update positions outward from the thrower
            double randomX = throwerX + dirX * distance;
            double randomY = throwerY + dirY * distance;
            double randomZ = throwerZ + dirZ * distance;

            // Set velocities for outward movement
            double randomMotionX = dirX * (0.1 + rand.nextDouble() * 0.1) * speed;
            double randomMotionY = (rand.nextDouble() - 0.5) * 0.2 * speed; // Random up and down movement
            double randomMotionZ = dirZ * (0.1 + rand.nextDouble() * 0.1) * speed;

            this.posX = randomX;
            this.posY = randomY;
            this.posZ = randomZ;

            this.motionX = randomMotionX;
            this.motionY = randomMotionY;
            this.motionZ = randomMotionZ;


            if (this.ticksExisted >= 120) {
                this.setDead();
            }
            this.spiralTicks += 2;
        }
    }

    public void setCoords(double playerX, double playerY, double playerZ) {
        dataManager.set(THROWER_X, (float) playerX);
        dataManager.set(THROWER_Y, (float) playerY);
        dataManager.set(THROWER_Z, (float) playerZ);
    }
}

