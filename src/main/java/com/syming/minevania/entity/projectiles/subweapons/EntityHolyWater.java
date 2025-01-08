package com.syming.minevania.entity.projectiles.subweapons;

import com.syming.minevania.entity.projectiles.MVThrowableWeapon;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.util.handlers.SoundsHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHolyWater extends MVThrowableWeapon {

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
        if (!this.world.isRemote && getThrower() != null)
        {
            this.world.playSound((EntityPlayer) getThrower(), result.getBlockPos(), SoundsHandler.HOLY_WATER_IMPACT, SoundCategory.MASTER, 0.5F, 1.0F);
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

    @Override
    protected float getImpactDamage() {
        return 0.5F;
    }

}
