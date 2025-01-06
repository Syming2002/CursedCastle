package com.syming.minevania.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityThrowingBoneProjectile extends EntityThrowable {
    public EntityThrowingBoneProjectile(World worldIn) {
        super(worldIn);
    }

    public EntityThrowingBoneProjectile(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityThrowingBoneProjectile(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            setDead();
            if (result.entityHit instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) result.entityHit;
                entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), 3);
            }
        }
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.2F;
    }
}
