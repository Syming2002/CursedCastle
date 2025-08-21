package com.syming.castlevaniacraft.entity.mobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class CCEntityMob extends EntityMob {

    public float targetDistance;

    public CCEntityMob(World worldIn) {
        super(worldIn);
    }

    public abstract boolean hasWeakness(DamageSource source, float damageAmount);

    public abstract boolean hasStrength(DamageSource source, float damageAmount);

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (getAttackTarget() != null) {
            targetDistance = getDistance(getAttackTarget()) - getAttackTarget().width / 2F;
        }
    }

    public void despawnIfPeaceful() {
        if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            setDead();
        }
    }
}
