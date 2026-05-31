package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.magic.HolyFireEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.projectile.CCThrowableWeapon;
import com.syming.cursedcastle.util.SpellBookUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.UUID;

public class HolyWaterEntity extends CCThrowableWeapon {

    public static int rainingCooldown = 0;
    public static final int RAINING_COOLDOWN = 200;
    public static UUID rainCaster;
    public static int holyRainTicks = 0;

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
    public void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (getOwner() != null) {
            if (getOwner() instanceof Player player) {
                if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.WIND, player)) {
                    if (player.getServer() != null) {
                        rainCaster = player.getUUID();

                        holyRainTicks = RAINING_COOLDOWN;

                        player.getServer().overworld().setWeatherParameters(0, holyRainTicks, true, false);
//                        player.getServer().overworld().setRainLevel(1.0F);
                    }
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (getOwner() != null) {
            if (getOwner() instanceof Player player) {
                if (!this.level().isClientSide()) {
                    HolyFireEntity holyFire = new HolyFireEntity(this.level(), this.getX(), this.getY(), this.getZ());
                    this.level().addFreshEntity(holyFire);
                    this.level().playSound(null, pResult.getBlockPos(), SoundEvents.SPLASH_POTION_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    discard();
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
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
                        mob.addEffect(new MobEffectInstance(CCEffect.HOLY.get(), 200));
                        discard();
                    }
                }
                this.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.SPLASH_POTION_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
            }
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

    @Override
    protected float getGravity() {
        return 0.05F;
    }
}
