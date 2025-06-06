package com.syming.castlevaniacraft.entity;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaHead;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLaserBeamTeleportation extends Entity {
    protected EntityDracula dracula;

    public EntityLaserBeamTeleportation(World worldIn) {
        super(worldIn);
    }

    public EntityLaserBeamTeleportation(World worldIn, EntityDracula dracula) {
        super(worldIn);

        setSize(1.0F, 4.0F);
        this.dracula = dracula;
        this.setPositionAndRotation(dracula.posX, dracula.posY + dracula.getEyeHeight(), dracula.posZ, dracula.rotationYaw, dracula.rotationPitch);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
