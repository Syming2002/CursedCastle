package com.syming.castlevaniacraft.entity.mobs.shade;

import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPoisonShade extends EntityShade {

    public EntityPoisonShade(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (world.isRaining() && this.world.getBiome(new BlockPos(this)).canRain()) {
            onShadeHeal(this);
        }
    }

//    @Override
//    public ResourceLocation getLootTable() {
//        return CCTreasures.HEAT_SHADE;
//    }
}
