package com.syming.minevania.entity.projectiles;

import com.syming.minevania.entity.EntityAttackMagic;
import com.syming.minevania.entity.mobs.EntityFrozenShade;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityIceBladeProjectile extends EntityAttackMagic {

    protected EntityFrozenShade frozenShade;

    public EntityIceBladeProjectile(World world) {
        super(world);
    }

    public EntityIceBladeProjectile(World world, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(world, shooter, accelX, accelY, accelZ);
        if (shooter instanceof EntityFrozenShade) {
            this.frozenShade = (EntityFrozenShade) shooter;
        }
        this.setSize(1.3125F, 0.0625F);
    }

    @Override
    public void onUpdate() {

        if (ticksExisted == 1) {
            caster = (EntityLivingBase) world.getEntityByID(getCasterID());
        }

        if (this.world.isRemote || (this.caster == null || !this.caster.isDead) && this.world.isBlockLoaded(new BlockPos(this)))
        {
            super.onUpdate();

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.forwardsRaycast(this, true, this.ticksInAir >= 25, this.caster);

            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
            {
                this.onImpact(raytraceresult);
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
            this.world.spawnParticle(this.getParticleType(), this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            this.setPosition(this.posX, this.posY, this.posZ);
        }
        else
        {
            this.setDead();
        }
    }

    protected float getMotionFactor()
    {
        return 0.95F;
    }

    protected EnumParticleTypes getParticleType()
    {
        return EnumParticleTypes.SNOW_SHOVEL;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                if (result.entityHit != this.caster) {
                    if (this.caster != null) {
                        if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.caster), 8.0F)) {
                            if (result.entityHit.isEntityAlive()) {
                                this.applyEnchantments(this.caster, result.entityHit);
                            } else {
                                this.caster.heal(5.0F);
                            }
                        }
                    } else {
                        result.entityHit.attackEntityFrom(DamageSource.MAGIC, 5.0F);
                    }

                    if (result.entityHit instanceof EntityLivingBase) {
                        ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 2));
                    }
                } else {
                    return;
                }
            }
            setDead();
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return true;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }
}
