package com.syming.castlevaniacraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import java.util.List;

public abstract class EntityLivingAttackMagic extends EntityFlying {

    protected EntityLivingBase caster;
    private static final DataParameter<Integer> CASTER = EntityDataManager.createKey(EntityLivingAttackMagic.class, DataSerializers.VARINT);


    public EntityLivingAttackMagic(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        getDataManager().register(CASTER, -1);
    }

    public int getCasterID() {
        return getDataManager().get(CASTER);
    }

    public void setCasterID(int id) {
        getDataManager().set(CASTER, id);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (ticksExisted == 1) {
            caster = (EntityLiving) world.getEntityByID(getCasterID());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {

    }

    public List<EntityLivingBase> getEntityLivingBaseNearby(double radius) {
        return getEntitiesNearby(EntityLivingBase.class, radius);
    }

    public <T extends Entity> List<T> getEntitiesNearby(Class<T> entityClass, double r) {
        return world.getEntitiesWithinAABB(entityClass, getEntityBoundingBox().grow(r, r, r), e -> e != this && getDistance(e) <= r + e.width / 2f);
    }

}
