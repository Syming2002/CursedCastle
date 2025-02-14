package com.syming.castlevaniacraft.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityScytheProjectile extends EntityThrowable {
    public EntityScytheProjectile(World worldIn) {
        super(worldIn);
    }

    public EntityScytheProjectile(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityScytheProjectile(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase) result.entityHit;

            target.attackEntityFrom(DamageSource.causeIndirectDamage(this, this.getThrower()), 5.0F);
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted >= 100) {
            this.setDead();
        }
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.0F;
    }
}
