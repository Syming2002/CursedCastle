package com.syming.castlevaniacraft.entity.projectiles;

import com.syming.castlevaniacraft.entity.EntityAttackMagic;
import com.syming.castlevaniacraft.entity.mobs.EntityHarpy;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHarpyFeather extends EntityAttackMagic {
    protected EntityHarpy harpy;

    public EntityHarpyFeather(World worldIn) {
        super(worldIn);
        setSize(1.0F, 0.0625F);
    }

    public EntityHarpyFeather(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, float rotationPitch, float rotationYaw) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        if (shooter instanceof EntityHarpy harpy) {
            this.harpy = harpy;

            this.rotationPitch = rotationPitch;
            this.rotationYaw = rotationYaw;
        }
        setSize(1.0F, 0.0625F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public void onUpdate() {

        if (this.ticksExisted > 1) {
            this.shooter = (EntityLivingBase) this.world.getEntityByID(getShooterID());
        }

        if (this.world.isRemote || (this.shooter == null || !this.shooter.isDead) && this.world.isBlockLoaded(new BlockPos(this))) {
            super.onUpdate();

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.forwardsRaycast(this, true, this.ticksInAir >= 25, this.shooter);

            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();

            this.rotationYaw = (float) (Math.toDegrees(Math.atan2(-this.motionX, this.motionZ)));
            this.rotationPitch = (float) (Math.toDegrees(Math.atan2(-this.motionY, Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ))));

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
        else if (this.ticksExisted >= 60)
        {
            this.setDead();
        }
    }

    protected float getMotionFactor()
    {
        return 0.98F;
    }

    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                if (result.entityHit != this.shooter) {
                    if (this.shooter != null) {
                        if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shooter), 8.0F)) {
                            if (result.entityHit.isEntityAlive()) {
                                this.applyEnchantments(this.shooter, result.entityHit);
                            }
                        }
                    } else {
                        result.entityHit.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.75F);
                    }
                }
            }
        }
    }
}
