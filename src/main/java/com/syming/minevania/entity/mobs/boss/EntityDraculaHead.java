package com.syming.minevania.entity.mobs.boss;

import net.ilexiconn.llibrary.server.entity.multipart.PartEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class EntityDraculaHead extends PartEntity {

    public EntityDraculaHead(EntityLiving parent, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(parent, radius, angleYaw, offsetY, sizeX, sizeY, damageMultiplier);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (this.parent instanceof EntityDracula dracula) {
            dracula.attackEntityFrom(source, damage);
            System.out.println("Head is Hit");
//            super.attackEntityFrom(source, damage);
            return true;
        }
        return false;
    }

    public EntityLivingBase getParent() {
        return this.parent;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

}
