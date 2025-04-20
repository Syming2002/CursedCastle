package com.syming.castlevaniacraft.entity;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

public class EntityHolyFire extends EntityAreaEffectCloud {
    public EntityHolyFire(World worldIn) {
        super(worldIn);
    }

    public EntityHolyFire(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }


    @Override
    public void onUpdate() {
        super.onUpdate();

        List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox());

        for (EntityLivingBase entity : list) {
            if (entity instanceof EntityMob && entity != getOwner()) {
                if (!entity.isImmuneToFire()) {
                    entity.setFire(5);
                }
                addEffect(new PotionEffect(MobEffects.WITHER, 200, 1));
            }
        }
    }
}
