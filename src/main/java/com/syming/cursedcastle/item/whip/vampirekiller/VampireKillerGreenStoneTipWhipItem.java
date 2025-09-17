package com.syming.cursedcastle.item.whip.vampirekiller;

import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.damagesource.CCDamageSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import static net.minecraft.core.particles.ParticleTypes.SNOWFLAKE;

public class VampireKillerGreenStoneTipWhipItem extends VampireKillerWhipItem implements IElementalWeapon {

    public VampireKillerGreenStoneTipWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }


    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return CCDamageSource.source(attacker.level().registryAccess(), CCDamageSource.WIND);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        getElementalDamageSource(pStack, pTarget, pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        super.onEntitySwing(stack, entity);

        if (entity instanceof Player player) {
            if (player.level().isClientSide()) {
                Vec3 look = player.getLookAngle();
                float reach = (float) player.getAttributeValue(ForgeMod.ENTITY_REACH.get());
                double stepSize = 0.5D;
                double maxSteps = reach / stepSize;

                for (int i = 0; i <= maxSteps; i++) {
                    double factor = i * stepSize;
                    double spawnX = player.getX() + look.x * factor;
                    double spawnY = player.getY() + player.getEyeHeight() + look.y * factor;
                    double spawnZ = player.getZ() + look.z * factor;
                    player.level().addParticle(ParticleTypes.CLOUD, spawnX, spawnY, spawnZ, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        return false;
    }
}
