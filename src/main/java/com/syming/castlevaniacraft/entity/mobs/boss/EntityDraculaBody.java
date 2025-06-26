package com.syming.castlevaniacraft.entity.mobs.boss;

import net.ilexiconn.llibrary.server.entity.multipart.PartEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public class EntityDraculaBody extends PartEntity {

    public EntityDraculaBody(EntityLiving parent, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(parent, radius, angleYaw, offsetY, sizeX, sizeY, damageMultiplier);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (this.parent instanceof EntityDracula && source.getTrueSource() instanceof EntityPlayer) {
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
