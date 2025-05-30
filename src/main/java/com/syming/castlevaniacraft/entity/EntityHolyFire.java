package com.syming.castlevaniacraft.entity;

import com.syming.castlevaniacraft.api.IElementalWeapon;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class EntityHolyFire extends EntityAreaEffectCloud implements IElementalWeapon {
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
            if (entity instanceof EntityMob mob && entity != getOwner()) {
                if (!mob.isImmuneToFire()) {
                    mob.setFire(5);
                }
                addEffect(new PotionEffect(MobEffects.WITHER, 200, 1));
                mob.attackEntityFrom(CCDamageSource.getHolyDamage(), 10);
                mob.motionX *= 0;
                mob.motionZ *= 0;
                System.out.println("Mob is being damaged with Holy Damage");
            }
        }
    }

    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return CCDamageSource.getHolyDamage();
    }
}
