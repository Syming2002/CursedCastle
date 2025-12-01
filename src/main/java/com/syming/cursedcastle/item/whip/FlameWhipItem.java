package com.syming.cursedcastle.item.whip;

import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.datagen.CCDatapackBuiltinEntriesProvider;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import static net.minecraft.core.particles.ParticleTypes.FLAME;

public class FlameWhipItem extends WhipItem implements IElementalWeapon {
    public FlameWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }

    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return CCDatapackBuiltinEntriesProvider.source(attacker.level().registryAccess(), CCDatapackBuiltinEntriesProvider.FIRE);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        super.hurtEnemy(pStack, pTarget, pAttacker);
        pTarget.setSecondsOnFire(3);
        pStack.hurtAndBreak(1, pAttacker, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        getElementalDamageSource(pStack, pTarget, pAttacker);
        return true;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        super.onEntitySwing(stack, entity);

        entity.level().playSound((Player) entity,
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                SoundEvents.BLAZE_SHOOT,
                SoundSource.MASTER,
                1.0F, 0.5F);
        if (entity.level().isClientSide()) {
            Vec3 look = entity.getLookAngle();
            float reach = (float) entity.getAttributeValue(ForgeMod.ENTITY_REACH.get());
            double stepSize = 0.5D;
            double maxSteps = reach / stepSize;

            for (int i = 0; i <= maxSteps; i++) {
                double factor = i * stepSize;
                double spawnX = entity.getX() + look.x * factor;
                double spawnY = entity.getY() + entity.getEyeHeight() + look.y * factor;
                double spawnZ = entity.getZ() + look.z * factor;
                entity.level().addParticle(FLAME, spawnX, spawnY, spawnZ, 0.0D, 0.0D, 0.0D);
            }
        }
        return false;
    }
}
