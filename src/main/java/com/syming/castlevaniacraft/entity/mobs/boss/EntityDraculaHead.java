package com.syming.castlevaniacraft.entity.mobs.boss;

import net.ilexiconn.llibrary.server.entity.multipart.PartEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class EntityDraculaHead extends PartEntity {

    public EntityEnderCrystal healingEnderCrystal;

    public EntityDraculaHead(EntityLiving parent, float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier) {
        super(parent, radius, angleYaw, offsetY, sizeX, sizeY, damageMultiplier);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (this.parent instanceof EntityDracula dracula) {
            dracula.attackEntityFrom(source, damage);
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

        updateDraculaEnderCrystal();
    }

    public void onCrystalDestroyed(EntityEnderCrystal crystal, BlockPos pos, DamageSource dmgSrc)
    {
        EntityPlayer entityplayer;

        if (dmgSrc.getTrueSource() instanceof EntityPlayer)
        {
            entityplayer = (EntityPlayer)dmgSrc.getTrueSource();
        }
        else
        {
            entityplayer = this.world.getNearestAttackablePlayer(pos, 64.0D, 64.0D);
        }

        if (crystal == this.healingEnderCrystal)
        {
            attackEntityFrom(DamageSource.causeExplosionDamage(entityplayer), 10.0F);
        }
    }

    /**
     * Updates the state of the enderdragon's current endercrystal.
     */
    private void updateDraculaEnderCrystal()
    {
        if (this.healingEnderCrystal != null)
        {
            if (this.healingEnderCrystal.isDead)
            {
                this.healingEnderCrystal = null;
            }
            else if (this.ticksExisted % 10 == 0 && this.parent.getHealth() < this.parent.getMaxHealth())
            {
                this.parent.setHealth(this.parent.getHealth() + 1.0F);
            }
        }

        if (this.rand.nextInt(10) == 0)
        {
            List<EntityEnderCrystal> list = this.world.<EntityEnderCrystal>getEntitiesWithinAABB(EntityEnderCrystal.class, this.getEntityBoundingBox().grow(32.0D));
            EntityEnderCrystal entityendercrystal = null;
            double d0 = Double.MAX_VALUE;

            for (EntityEnderCrystal entityendercrystal1 : list)
            {
                double d1 = entityendercrystal1.getDistanceSq(this);

                if (d1 < d0)
                {
                    d0 = d1;
                    entityendercrystal = entityendercrystal1;
                }
            }

            this.healingEnderCrystal = entityendercrystal;
        }
    }
}
