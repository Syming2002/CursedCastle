package com.syming.cursedcastle.entity.mob;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class CCMonster extends Monster {
    protected CCMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public abstract boolean hasWeakness(DamageSource source, float damageAmount);

    public abstract boolean hasStrength(DamageSource source, float damageAmount);
}
