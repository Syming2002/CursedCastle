package com.syming.cursedcastle.api;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public interface ISyncHurtMultipart {

    void onServerMultipartAttack(LivingEntity parent, DamageSource damageSource, float damage);
}
