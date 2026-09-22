package com.syming.cursedcastle.entity.monster.shade;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.entity.magic.IceBreathEntity;
import com.syming.cursedcastle.entity.magic.IceSpikeEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.network.CCSpawnParticle;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FrozenShadeEntity extends ShadeEntity {

    private final List<IceSpikeEntity> iceSpikes = new ArrayList<>();
    private int iceSpikeAttackCooldown = 0;
    private int iceSpikeSpawnTimer = 0;
    private int iceSpikesSpawned = 0;

    private IceBreathEntity iceBreath;

    public FrozenShadeEntity(EntityType<? extends ShadeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        return super.getTarget();
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageType.FIRE) || source == target.damageSources().onFire() || source == target.damageSources().inFire();
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageType.ICE);
                }
            }
        }
        return false;
    }

    @Override
    public List<Component> getWeaknessDescription() {
        return List.of(Component.translatable("entity.cursedcastle.fire_weakness_description").setStyle(Style.EMPTY.withColor(ColorUtil.FIRE_COLOR)));
    }

    @Override
    public List<Component> getStrengthDescription() {
        return List.of(Component.translatable("entity.cursedcastle.ice_strength_description").setStyle(Style.EMPTY.withColor(ColorUtil.ICE_COLOR)));
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if (this.iceSpikeAttackCooldown <= 0) {
            this.iceSpikeSpawnTimer = 20;
            this.iceSpikesSpawned = 0;
            this.iceSpikeAttackCooldown = 200;
        }
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (this.iceSpikeAttackCooldown > 0) {
            this.iceSpikeAttackCooldown--;
        }

        if (this.level().isClientSide()) {
            spawnSnowflakeParticles();
        }

        if (target != null) {
            if (getAnimation() == ShadeEntity.REPULSE_ATTACK_ANIMATION) {
                lookAt(target, 30.0F, 30.0F);
                if (this.repulseAttackAnimationTick == 95) {
                    if (!this.level().isClientSide()) {
                        Vec3 headPos = this.position().add(0.0D, getEyeHeight(), 0.0D);
                        Vec3 lookVec = getLookAngle().scale(0.5D);
                        headPos = headPos.add(lookVec);

                        this.iceBreath = new IceBreathEntity(this.level());
                        this.iceBreath.setOwner(this);
                        this.iceBreath.setPos(headPos.x, headPos.y, headPos.z);
                        this.level().addFreshEntity(this.iceBreath);
                    }
                }
            } else if (getAnimation() == IDLE_ANIMATION) {
                if (this.iceSpikeSpawnTimer > 0) {
                    this.iceSpikeSpawnTimer--;

                    if (!this.level().isClientSide() && this.iceSpikeSpawnTimer == 0 && this.iceSpikesSpawned < 3) {
                        Vec3 pos = getRandomFrontOffset(this.random);
                        IceSpikeEntity iceSpike = new IceSpikeEntity(this.level());
                        iceSpike.setOwner(this);
                        iceSpike.setPos(pos.x, pos.y, pos.z);
                        this.level().addFreshEntity(iceSpike);

                        this.iceSpikes.add(iceSpike);

                        this.iceSpikesSpawned++;
                        this.iceSpikeSpawnTimer = 20;
                    }
                }

                if (this.iceSpikesSpawned == 3 && this.iceSpikeSpawnTimer <= 0) {
                    for (IceSpikeEntity iceSpike : this.iceSpikes) {
                        Vec3 headAim = target.position().add(0, target.getEyeHeight() * 0.8D, 0);
                        Vec3 direction = headAim.subtract(iceSpike.position()).normalize().scale(0.5D);

                        iceSpike.setDeltaMovement(direction);
                        iceSpike.hasImpulse = true;
                        iceSpike.lockedRotation = true;
                    }
                    this.iceSpikes.clear();
                }
            }
        }

        if (getHealth() < getMaxHealth()) {
            if (this.level().isRaining() && this.level().getBiome(new BlockPos((int) getX(), (int) getY(), (int) getZ())).value().coldEnoughToSnow(new BlockPos((int) getX(), (int) getY(), (int) getZ()))) {
                onShadeHeal(this);
                if (this.level().isClientSide()) {
                    for (int i = 0; i < 2; ++i) {
                        double d0 = this.random.nextGaussian() * 0.02D;
                        double d1 = this.random.nextGaussian() * 0.02D;
                        double d2 = this.random.nextGaussian() * 0.02D;
                        this.level().addParticle(ParticleTypes.HEART, getRandomX(0.25D), this.getRandomY() + 0.25D, getRandomZ(0.25D), d0, d1, d2);
                    }
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        LivingEntity livingEntity = (LivingEntity) pSource.getEntity();
        if (livingEntity != null) {
            ItemStack stack = livingEntity.getMainHandItem();
            if (stack.getItem() instanceof IElementalDamage elementalWeapon && stack.getItem() == CCItem.ICE_WHIP.get()) {
                pSource = elementalWeapon.getElementalDamageSource(stack, getTarget(), livingEntity);
                if (hasStrength(pSource, pAmount) && pSource.is(CCDamageType.ICE)) {
                    if (pSource.is(CCDamageType.ICE)) {
                        if (stack.getItem() == CCItem.ICE_WHIP.get()) {
                            if (elementalWeapon != null) {
                                if (pSource != null) {
                                    PacketDistributor.sendToServer(new CCSpawnParticle(this.getId()));
                                    return super.hurt(pSource, 0);
                                }
                            }
                        }
                    } else {
                        return super.hurt(pSource, pAmount / pAmount);
                    }
                }
            }
        }
        if (hasWeakness(pSource, pAmount)) {
            return super.hurt(pSource, pAmount * pAmount);
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable() {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "entities/frozen_shade"));
    }

    private Vec3 getRandomFrontOffset(RandomSource pRandom) {
        float yawRad = this.yBodyRot * ((float) Math.PI / 180.0F);
        double forwardX = -Mth.sin(yawRad);
        double forwardZ = Mth.cos(yawRad);

        double distance = 1.5D + pRandom.nextDouble();

        double sideOffset = (pRandom.nextDouble() - 0.5D) * 1.5D;

        double perpX = -forwardZ;
        double perpZ = forwardX;

        double x = getX() + forwardX * distance + perpX * sideOffset;
        double y = getY() + 1.5D;
        double z = getZ() + forwardZ * distance + perpZ * sideOffset;

        return new Vec3(x, y, z);
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnSnowflakeParticles() {
        double minX = this.getX() - this.getBbWidth() * 0.5;
        double minY = this.getY();
        double minZ = this.getZ() - this.getBbWidth() * 0.5;

        double maxX = this.getX() + this.getBbWidth() * 0.5;
        double maxY = this.getY() + this.getBbHeight();
        double maxZ = this.getZ() + this.getBbWidth() * 0.5;

        for (int i = 0; i <= 1; i++) {
            double posX = minX + this.random.nextDouble() * (maxX - minX);
            double posY = minY + this.random.nextDouble() * (maxY - minY);
            double posZ = minZ + this.random.nextDouble() * (maxZ - minZ);

            double speedX = (this.random.nextDouble() - 0.5) * 0.02;
            double speedY = this.random.nextDouble() * 0.02;
            double speedZ = (this.random.nextDouble() - 0.5) * 0.02;

            this.level().addParticle(
                    ParticleTypes.SNOWFLAKE,
                    posX, posY, posZ,
                    speedX, speedY, speedZ
            );
        }
    }
}
