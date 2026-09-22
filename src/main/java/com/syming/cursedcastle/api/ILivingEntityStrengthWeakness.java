package com.syming.cursedcastle.api;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;

import java.util.List;

public interface ILivingEntityStrengthWeakness {

    public boolean hasWeakness(DamageSource pSource, float pDamageAmount);

    public boolean hasStrength(DamageSource pSource, float pDamageAmount);

    public List<Component> getWeaknessDescription();

    public List<Component> getStrengthDescription();
}
