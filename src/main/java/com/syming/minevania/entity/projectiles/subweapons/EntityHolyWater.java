package com.syming.minevania.entity.projectiles.subweapons;

import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHolyWater extends EntityThrowable {

    public EntityHolyWater(World worldIn)
    {
        super(worldIn);
    }

    public EntityHolyWater(World worldIn, EntityLivingBase throwerIn, ItemStack holyWaterDamageIn)
    {
        super(worldIn, throwerIn);
    }

    public void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            Helpers.playSound(world, thrower, "holy_water_impact", 0.5F, 1.0F);
            if (result.entityHit instanceof EntityLivingBase)
            {
                EntityLivingBase entity = (EntityLivingBase) result.entityHit;
                entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), rand.nextFloat());
                if (entity instanceof EntityMob)
                {
                    entity.setFire(10);
                }
            }
            else
            {
                boolean flag1 = true;

                if (flag1)
                {
                    BlockPos blockPos = result.getBlockPos().offset(result.sideHit);

                    if (this.world.isAirBlock(blockPos))
                    {
                        this.world.setBlockState(blockPos, Blocks.FIRE.getDefaultState());
                    }
                }
            }
            setDead();
        }
    }

    protected float getGravityVelocity()
    {
        return 0.05F;
    }

}
