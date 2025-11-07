package com.syming.cursedcastle.entity.mob.shade;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.damagesource.CCDamageSource;
import com.syming.cursedcastle.entity.magic.IceBreathEntity;
import com.syming.cursedcastle.entity.magic.IceSpikeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
    protected void defineSynchedData() {
        super.defineSynchedData();
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
    public float getEyeHeight(Pose pPose) {
        return super.getEyeHeight(pPose);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageSource.FIRE) || source == target.damageSources().onFire() || source == target.damageSources().inFire();
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
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageSource.ICE);
                }
            }
        }
        return false;
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
                if (getAnimationTick() == 95) {
                    if (!this.level().isClientSide()) {
                        Vec3 headPos = this.position().add(0.0D, getEyeHeight(), 0.0D);
                        Vec3 lookVec = getLookAngle().scale(0.5D);
                        headPos = headPos.add(lookVec);

                        this.iceBreath = new IceBreathEntity(this.level());
                        this.iceBreath.setOwner(this);
                        this.iceBreath.setPos(headPos.x, headPos.y, headPos.z);
                        this.level().addFreshEntity(this.iceBreath);
                        System.out.println("Ice Breath is Launching at " + this.iceBreath.getX() + " " + this.iceBreath.getY() + " " + this.iceBreath.getZ());
                    }
                }
            }
            if (getAnimation() != ShadeEntity.REPULSE_ATTACK_ANIMATION) {
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
                    if (!this.iceSpikes.isEmpty()) {
                        for (IceSpikeEntity iceSpike : this.iceSpikes) {
                            if (iceSpike.isAlive()) {
                                Vec3 headAim = target.position().add(0, target.getEyeHeight() * 0.8D, 0);
                                Vec3 direction = headAim.subtract(iceSpike.position()).normalize().scale(0.5D);

                                double dx = direction.x;
                                double dy = direction.y;
                                double dz = direction.z;

                                float yaw = (float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;
                                float pitch = (float)(-(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180F / Math.PI)));

                                iceSpike.setYRot(yaw);
                                iceSpike.setXRot(pitch);
                                iceSpike.yRotO = yaw;
                                iceSpike.xRotO = pitch;

                                iceSpike.setLaunched(true);

                                iceSpike.setDeltaMovement(direction);
                                iceSpike.hasImpulse = true;
                            }
                        }
                        this.iceSpikes.clear();
                    }
                }
            }
        }

        if (getHealth() < getMaxHealth() && this.level().isRaining() && this.level().getBiome(new BlockPos((int) getX(), (int) getY(), (int) getZ())).get().coldEnoughToSnow(new BlockPos((int) getX(), (int) getY(), (int) getZ()))){
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

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (hasStrength(pSource, pAmount)) {
            System.out.println("Has Ice Strength");
            return super.hurt(pSource, pAmount / pAmount);
        }
        if (hasWeakness(pSource, pAmount)) {
            System.out.println("Has Fire Weakness");
            return super.hurt(pSource, pAmount * pAmount);
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(CursedCastleMod.MOD_ID, "entities/frozen_shade");
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

        for (int i = 0; i < 1; i++) {
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
