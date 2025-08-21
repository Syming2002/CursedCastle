package com.syming.cursedcastle.api;

import net.minecraft.world.damagesource.DamageSource;

public interface ILivingEntityStrengthWeakness {

    public boolean hasWeakness(DamageSource source, float damageAmount);

    public boolean hasStrength(DamageSource source, float damageAmount);

}
