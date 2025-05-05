package com.syming.castlevaniacraft.entity.mobs.shade;

import com.syming.castlevaniacraft.api.IElementalWeapon;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.loot.CCTreasures;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityHeatShade extends EntityShade {


    private int animationTick;

    public EntityHeatShade(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getIceDamage();
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getFireDamage();
                }
            }
        }
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

        if (this.world.isRaining() && this.world.getBiome(new BlockPos(this)).canRain()) {
            this.attackEntityFrom(DamageSource.DROWN, 3.0F);
        }
    }

    @Override
    public ResourceLocation getLootTable() {
        return CCTreasures.HEAT_SHADE;
    }
}
