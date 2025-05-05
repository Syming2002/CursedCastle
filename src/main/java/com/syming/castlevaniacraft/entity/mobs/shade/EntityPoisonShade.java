package com.syming.castlevaniacraft.entity.mobs.shade;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
