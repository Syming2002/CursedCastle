package com.syming.cursedcastle.entity.mob;

import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.crypto.Mac;

public abstract class CCMonster extends Monster implements ILivingEntityStrengthWeakness {
    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(CCMonster.class, EntityDataSerializers.INT);

    public CCMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TARGET_ID,-1);
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
        this.entityData.set(TARGET_ID, living == null ? -1 : living.getId());
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        int id = this.entityData.get(TARGET_ID);
        Entity entity = this.level().getEntity(id);
        return (entity instanceof LivingEntity living) ? living : null;
    }
}
