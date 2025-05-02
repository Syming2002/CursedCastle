package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import com.syming.castlevaniacraft.entity.EntityHolyFire;
import com.syming.castlevaniacraft.entity.projectiles.CCThrowableWeapon;
import com.syming.castlevaniacraft.util.handler.SoundsHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHolyWater extends CCThrowableWeapon {

    public EntityHolyWater(World worldIn)
    {
        super(worldIn);
    }

    public EntityHolyWater(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    @Override
    public void onImpact(RayTraceResult result) {
        if (!this.world.isRemote && getThrower() != null) {
            if (result.typeOfHit == RayTraceResult.Type.ENTITY && result.entityHit instanceof EntityLivingBase livingBase) {
                livingBase.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 0.5F);
            } else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                this.world.playSound(null, result.getBlockPos(), SoundsHandler.HOLY_WATER_IMPACT, SoundCategory.MASTER, 0.5F, 1.0F);
                spawnHolyFire();
            }
            setDead();
        }
    }

    private void spawnHolyFire() {
        EntityHolyFire holyFire = new EntityHolyFire(this.world, this.posX, this.posY, this.posZ);
        holyFire.setOwner(this.getThrower());
        holyFire.setRadius(2.25F);
        holyFire.setRadiusOnUse(-0.5F);
        holyFire.setRadiusPerTick(-0.03F);
        holyFire.setColor(0x66CCFF);

        holyFire.setParticle(EnumParticleTypes.SPELL_MOB);
        this.world.spawnEntity(holyFire);
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.05F;
    }

    @Override
    protected float getImpactDamage() {
        return 0.5F;
    }

}
