package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.projectile.CCThrowableWeapon;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.projectile.subweapon.HolyCrossItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;

public class FireHolyCrossEntity extends CCThrowableWeapon {

    private boolean isSpinning = false;
    private final int maxFireballs = 1;

    public FireHolyCrossEntity(EntityType<FireHolyCrossEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireHolyCrossEntity(Level pLevel) {
        super(CCEntity.FIRE_HOLY_CROSS.get(), pLevel);
    }

    public FireHolyCrossEntity(Level pLevel, LivingEntity pShooter) {
        super(CCEntity.FIRE_HOLY_CROSS.get(), pShooter, pLevel);
        setItem(new ItemStack(CCItem.ANIM_FIRE_HOLY_CROSS.get()));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Entity hitEntity = pResult.getEntity();
        ItemStack stack = getItemRaw();
        if (!this.level().isClientSide() && getOwner() != null) {
            if (hitEntity != null) {
                if (hitEntity == getOwner()) {
                    if (getOwner() instanceof Player player) {
                        if (stack.getMaxDamage() - stack.getDamageValue() > 0) {
                            player.getInventory().add(stack);
                            this.level().playSound(player, getX(), getY(), getZ(), SoundEvents.ITEM_PICKUP, SoundSource.MASTER, 0.5F, 1.0F);
                            discard();
                        }
                    }
                } else if (hitEntity instanceof LivingEntity target && hitEntity != getOwner()) {
                    target.setSecondsOnFire(5);
                    stack.hurt(1, this.random, (ServerPlayer) getOwner());
                }
            }
        }
    }

    @Override
    public float getImpactDamage() {
        return 12.0F;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        ItemStack stack = getItemRaw();

        if (!stack.isEmpty()) {
            spawnAtLocation(stack, 0.5F);
            stack.hurt(1, this.random, getOwner() instanceof ServerPlayer ? (ServerPlayer) getOwner() : null);
            discard();

        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            this.tickCount++;

            if (this.tickCount >= 10) {
                setDeltaMovement(Vec3.ZERO);
                this.hasImpulse = true;
                this.isSpinning = true;
            }

            if (this.tickCount >= 50) {
                shootRotatingFireballs();
                discard();
            }
        }

        if (this.level().isClientSide()) {
            double offsetX = (this.random.nextDouble() - 0.5D) * 0.5D;
            double offsetY = this.random.nextDouble() * 0.02D;
            double offsetZ = (this.random.nextDouble() - 0.5D) * 0.5D;

            double particleX = getX() + offsetX;
            double particleY = getY() + offsetY;
            double particleZ = getZ() + offsetZ;

            double motionY = 0.02D + this.random.nextDouble() * 0.02D;

            this.level().addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ, 0.0D, motionY, 0.0D);
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(CCItem.ANIM_FIRE_HOLY_CROSS.get());
    }

    @Override
    protected Item getDefaultItem() {
        return CCItem.ANIM_FIRE_HOLY_CROSS.get();
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    private void shootRotatingFireballs() {
        double angle = this.tickCount * 0.25;
        double radius = 0.1 * this.tickCount;

        double x1 = Math.cos(angle) * radius;
        double z1 = Math.sin(angle) * radius;

        double x2 = Math.cos(angle + Math.PI) * radius;
        double z2 = Math.sin(angle + Math.PI) * radius;

        shootFireball(x1, 0, z1);
        shootFireball(x2, 0, z2);

        this.level().playSound(null, getX(), getY(), getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1.0F, 1.0F);
        discard();
    }

    private void shootFireball(double x, double y, double z) {
        if (getOwner() != null) {
            LargeFireball fireball = new LargeFireball(this.level(), (LivingEntity) getOwner(), x, y, z, 0);

            fireball.setPos(getX(), getY() + 0.5, getZ());
            this.level().addFreshEntity(fireball);
        }
    }
}
