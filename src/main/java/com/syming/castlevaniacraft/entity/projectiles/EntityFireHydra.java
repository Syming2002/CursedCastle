package com.syming.castlevaniacraft.entity.projectiles;

import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.entity.EntityAttackMagic;
import com.syming.castlevaniacraft.entity.EntityLivingAttackMagic;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;

import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityFireHydra extends EntityLivingAttackMagic implements IProjectile {

    public EntityFireHydra(World worldIn) {
        super(worldIn);
    }

    public EntityFireHydra(World worldIn, EntityLivingBase entityCaster) {
        super(worldIn);
        setSize(0, 0);
        this.caster = entityCaster;
        if (!worldIn.isRemote && entityCaster instanceof EntityPlayer) {
            setCasterID(entityCaster.getEntityId());
        }
        setNoGravity(true);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.caster != null && this.caster.isDead) {
            this.setDead();
            return;
        }

        if (this.caster != null) {
            move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            spawnFireParticles();
        }


        if (ticksExisted > 1) {
            for (EntityLivingBase entity : getEntityLivingBaseNearby(2.0)) {
                collideWithEntity(entity);
            }
        }
        if (ticksExisted > 80) {
            setDead();
        }
    }

    @Override
    protected void collideWithEntity(Entity entityIn)
    {
        entityIn.setFire(10);
        entityIn.attackEntityFrom(CCDamageSource.getFireDamage(), 5.0F);
    }

    public List<EntityLivingBase> getEntityLivingBaseNearby(double distanceX, double distanceY, double distanceZ, double radius) {
        return getEntitiesNearby(EntityLivingBase.class, distanceX, distanceY, distanceZ, radius);
    }

    public <T extends Entity> List<T> getEntitiesNearby(Class<T> entityClass, double distanceX, double distanceY, double distanceZ, double radius) {
        return world.getEntitiesWithinAABB(entityClass, getEntityBoundingBox().grow(distanceX, distanceY, distanceZ), entity -> entity != this && getDistance(entity) <= radius + entity.width / 2F && entity.posY <= posY + distanceY);
    }

    @SideOnly(Side.CLIENT)
    public void spawnFireParticles() {
        if (this.world.isRemote) {
            for (int i = 0; i < 5; i++) {
                double offsetX = (rand.nextFloat() - 0.5) * 0.2;
                double offsetY = (rand.nextFloat() - 0.5) * 0.2;
                double offsetZ = (rand.nextFloat() - 0.5) * 0.2;
                this.world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, offsetX, offsetY, offsetZ);
            }
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    public void shoot(Entity shooter, float pitch, float yaw, float velocity, float inaccuracy)
    {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float f1 = -MathHelper.sin(pitch * 0.017453292F);
        float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
        this.motionX += shooter.motionX;
        this.motionZ += shooter.motionZ;

        if (!shooter.onGround)
        {
            this.motionY += shooter.motionY;
        }
    }
}
