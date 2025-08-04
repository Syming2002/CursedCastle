package com.syming.castlevaniacraft.world.entity.projectile.subweapon;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.sound.CCSound;
import com.syming.castlevaniacraft.world.entity.CCEntity;
import com.syming.castlevaniacraft.world.entity.projectile.CCThrowableWeapon;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class HolyWaterEntity extends CCThrowableWeapon {
    public HolyWaterEntity(Level pLevel) {
        super(CCEntity.HOLY_WATER.get(), pLevel);
    }

    public HolyWaterEntity(double pX, double pY, double pZ, Level pLevel) {
        super(CCEntity.HOLY_WATER.get(), pX, pY, pZ, pLevel);
    }

    public HolyWaterEntity(LivingEntity pShooter, Level pLevel, ItemStack pStack) {
        super(CCEntity.HOLY_WATER.get(), pShooter, pLevel);
    }

    public HolyWaterEntity(EntityType<HolyWaterEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (getOwner() != null) {
            if (!this.level().isClientSide()) {
                BlockPos pos = pResult.getBlockPos().offset(pResult.getDirection().getNormal());
                if (this.level().isEmptyBlock(pos)) {
                    this.level().setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
                    discard();
                    System.out.println("Fire is Set");
                }
            }
            this.level().playSound((Player) getOwner(), pResult.getBlockPos(), CCSound.HOLY_WATER_IMPACT.get(), SoundSource.NEUTRAL, 0.5F, 1.0F);
        }
        System.out.println("Block is Hit");
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        ItemStack stack = getItem();
        Entity target = pResult.getEntity();

        if (getOwner() != null) {
            if (!this.level().isClientSide()) {
                if (!stack.isEmpty()) {
                    if (target instanceof Mob mob && getOwner() instanceof LivingEntity shooter) {
                        target.hurt(this.damageSources().mobProjectile(this, shooter), getImpactDamage());
                        mob.setSecondsOnFire(5);
                        discard();
                    }
                }
            }
            this.level().playSound((Player) getOwner(), target.getX(), target.getY(), target.getZ(), CCSound.HOLY_WATER_IMPACT.get(), SoundSource.NEUTRAL, 0.5F, 1.0F);
        }
        System.out.println("Entity is Hit");
    }

    @Override
    public float getImpactDamage() {
        return 0.5F;
    }

    @Override
    protected Item getDefaultItem() {
        return CCItem.HOLY_WATER.get();
    }
}
