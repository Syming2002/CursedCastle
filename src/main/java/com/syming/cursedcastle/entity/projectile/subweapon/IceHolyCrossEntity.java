package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.magic.IceSpikeEntity;
import com.syming.cursedcastle.entity.projectile.CCThrowableWeapon;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IceHolyCrossEntity extends CCThrowableWeapon {

    public static final EntityDataAccessor<Integer> SPIKE_COUNT = SynchedEntityData.defineId(IceHolyCrossEntity.class, EntityDataSerializers.INT);
    public final List<IceSpikeEntity> iceSpikeEntities = new ArrayList<>();
    public int iceSpikeCooldown = 0;
    public int iceSpikePerShotCooldown = 0;

    public IceHolyCrossEntity(EntityType<IceHolyCrossEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public IceHolyCrossEntity(Level pLevel) {
        super(CCEntity.ICE_HOLY_CROSS.get(), pLevel);
    }

    public IceHolyCrossEntity(Level pLevel, LivingEntity pShooter) {
        super(CCEntity.ICE_HOLY_CROSS.get(), pShooter, pLevel);
        setItem(new ItemStack(CCItem.ANIM_ICE_HOLY_CROSS.get()));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(SPIKE_COUNT, 0);
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
                    target.addEffect(new MobEffectInstance(CCEffect.FREEZE.get()));
//                    stack.hurt(1, this.random, (ServerPlayer) getOwner());
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

        if (getOwner() != null) {

            if (this.level().isClientSide()) {
                this.level().addParticle(ParticleTypes.SNOWFLAKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
            }

            float radius = 1.5F;
            float speed = 0.2F;

            float angle = (this.tickCount * speed);

            double offsetX = Math.cos(angle) * radius;
            double offsetZ = Math.sin(angle) * radius;

            double targetX = getOwner().getX() + offsetX;
            double targetY = getOwner().getY() + getOwner().getEyeHeight() + 0.5D;
            double targetZ = getOwner().getZ() + offsetZ;

            double randomMove = 0.2;

            targetX += (this.random.nextDouble() - 0.5) * randomMove;
            targetY += (this.random.nextDouble() - 0.5) * randomMove;
            targetZ += (this.random.nextDouble() - 0.5) * randomMove;

            setPos(targetX, targetY, targetZ);

            if (this.iceSpikePerShotCooldown > 0) {
                this.iceSpikePerShotCooldown--;
            }

            if (this.iceSpikePerShotCooldown <= 0) {
                if (this.iceSpikeCooldown > 0) {
                    this.iceSpikeCooldown--;
                }

                List<Monster> monsters = this.level().getEntitiesOfClass(Monster.class, this.getBoundingBox().inflate(10.0D), LivingEntity::isAlive);
                for (Monster monster : monsters) {
                    if (!level().isClientSide()) {
                        if (this.iceSpikeCooldown <= 0) {
                            Vec3 pos = getRandomFrontOffset(this.random);
                            IceSpikeEntity iceSpike = new IceSpikeEntity(this.level());
                            iceSpike.setOwner(this);
                            iceSpike.setPos(pos.x, pos.y, pos.z);
                            level().addFreshEntity(iceSpike);

                            Vec3 headAim = monster.position().add(0, monster.getEyeHeight(), 0);
                            Vec3 direction = headAim.subtract(iceSpike.position()).normalize().scale(0.85D);

                            iceSpike.setDeltaMovement(direction);

                            this.iceSpikeEntities.add(iceSpike);

                            setSpikeCount();

                            this.iceSpikeCooldown = 10;

                            if (this.iceSpikeEntities.size() == 3 || this.iceSpikeEntities.size() == 6 || this.iceSpikeEntities.size() == 9) {
                                this.iceSpikePerShotCooldown = 60;
                            }
                        }
                    }
                }
            }

            if (this.iceSpikeEntities.size() >= 9) {
                discard();
            }
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(CCItem.ANIM_ICE_HOLY_CROSS.get());
    }

    @Override
    protected Item getDefaultItem() {
        return CCItem.ANIM_ICE_HOLY_CROSS.get();
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    public Vec3 getRandomFrontOffset(RandomSource pRandom) {
        float yawRad = this.getYRot() * ((float) Math.PI / 180.0F);
        double forwardX = -Mth.sin(yawRad);
        double forwardZ = Mth.cos(yawRad);

        double distance = 1.5D + pRandom.nextDouble();

        double sideOffset = (pRandom.nextDouble() - 0.5D) * 1.5D;

        double perpX = -forwardZ;
        double perpZ = forwardX;

        double x = getX() + forwardX * distance + perpX * sideOffset;
        double y = getY() + getEyeHeight();
        double z = getZ() + forwardZ * distance + perpZ * sideOffset;

        return new Vec3(x, y, z);
    }

    public void setSpikeCount() {
        this.entityData.set(SPIKE_COUNT, this.iceSpikeEntities.size());
    }
}
