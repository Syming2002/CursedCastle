package com.syming.cursedcastle.entity.monster;

import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class CCFlyingMonster extends FlyingMob implements Enemy, ILivingEntityStrengthWeakness {
    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(CCFlyingMonster.class, EntityDataSerializers.INT);

    public CCFlyingMonster(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(TARGET_ID, -1);
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
