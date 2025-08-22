package com.syming.cursedcastle.world.entity.mob.shade;

import com.syming.cursedcastle.world.entity.magic.IceBreathEntity;
import com.syming.cursedcastle.world.entity.magic.IceSpikeEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FrozenShadeEntity extends ShadeEntity implements RangedAttackMob {

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

        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 100, 15.0F));
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
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
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
            } else if (getAnimation() != ShadeEntity.REPULSE_ATTACK_ANIMATION) {
                if (this.iceSpikeSpawnTimer > 0) {
                    this.iceSpikeSpawnTimer--;

                    if (!this.level().isClientSide() && this.iceSpikeSpawnTimer == 0 && this.iceSpikesSpawned < 3) {
                        Vec3 pos = getRandomFrontOffset(this.random);
                        IceSpikeEntity iceSpike = new IceSpikeEntity(this.level());
                        iceSpike.setOwner(this);
                        iceSpike.setPosRaw(pos.x, pos.y, pos.z);
                        this.level().addFreshEntity(iceSpike);

                        this.iceSpikes.add(iceSpike);

                        this.iceSpikesSpawned++;
                        this.iceSpikeSpawnTimer = 20;
                    }
                }

                if (this.iceSpikesSpawned == 3 && this.iceSpikeSpawnTimer == 0) {
                    if (!this.iceSpikes.isEmpty()) {
                        for (IceSpikeEntity iceSpike : this.iceSpikes) {
                            if (iceSpike.isAlive()) {
                                Vec3 headAim = target.position().add(0, target.getEyeHeight() * 0.8D, 0);
                                Vec3 direction = headAim.subtract(iceSpike.position()).normalize().scale(0.5D);
                                iceSpike.setDeltaMovement(direction);
                                iceSpike.hasImpulse = true;
                                iceSpike.lockRotation();
                            }
                        }
                        this.iceSpikes.clear();
                    }
                }
            }
        }
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
}
