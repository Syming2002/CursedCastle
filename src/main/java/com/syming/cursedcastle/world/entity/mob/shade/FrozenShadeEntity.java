package com.syming.cursedcastle.world.entity.mob.shade;

import com.syming.cursedcastle.world.entity.magic.IceBreathEntity;
import com.syming.cursedcastle.world.entity.magic.IceSpikeEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class FrozenShadeEntity extends ShadeEntity implements RangedAttackMob {

//    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(FrozenShadeEntity.class, EntityDataSerializers.INT);
//    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(FrozenShadeEntity.class, EntityDataSerializers.INT);
//    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(FrozenShadeEntity.class, EntityDataSerializers.INT);
//    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C);

    private final List<IceSpikeEntity> iceSpikes = new ArrayList<>();
    private int iceSpikeAttackCooldown = 0;
    private int iceSpikeSpawnTimer = 0;
    private int iceSpikesSpawned = 0;
    public Vec3 lockedTargetPos = null;

    private IceBreathEntity iceBreath;

    public FrozenShadeEntity(EntityType<? extends ShadeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
//        this.entityData.define(DATA_TARGET_A, 0);
//        this.entityData.define(DATA_TARGET_B, 0);
//        this.entityData.define(DATA_TARGET_C, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
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
            this.lockedTargetPos = pTarget.position();
            this.iceSpikeSpawnTimer = 20;
            this.iceSpikesSpawned = 0;
            this.iceSpikeAttackCooldown = 200;
        }
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (!isAlive() && target == null) return;

        if (this.iceSpikeAttackCooldown > 0) {
            this.iceSpikeAttackCooldown--;
        }

        if (target != null) {
            if (getAnimation() == ShadeEntity.REPULSE_ATTACK_ANIMATION) {
                Vec3 headPos = new Vec3(0.0F, 1.5F, 0.0F);
                headPos = headPos.xRot((float) Math.toRadians(-getXRot()));
                headPos = headPos.yRot((float) Math.toRadians(-getYHeadRot()));
                headPos = headPos.add(new Vec3(0.0F, 0.0F, 0.25F));
                headPos = headPos.add(getX(), getY(), getZ());

                getLookControl().setLookAt(target, 30.0F, 30.0F);
                if (getAnimationTick() == 95) {
                    if (!this.level().isClientSide()) {
                        this.iceBreath = new IceBreathEntity(this, headPos.x, headPos.y, headPos.z, this.level());
                        this.iceBreath.setPosRaw(headPos.x, headPos.y, headPos.z);
                        this.level().addFreshEntity(this.iceBreath);
                        System.out.println("Ice Breath is Launching");
                    }
                }
            }
            if (this.iceSpikeSpawnTimer > 0) {
                this.iceSpikeSpawnTimer--;

                if (!this.level().isClientSide() && this.iceSpikeSpawnTimer == 0 && this.iceSpikesSpawned < 3) {
                    Vec3 pos = getRandomFrontOffset(this.random);
                    IceSpikeEntity iceSpike = new IceSpikeEntity(this, 0, 0, 0, this.level());
                    iceSpike.setOwner(this);
                    iceSpike.setPosRaw(pos.x, pos.y, pos.z);
                    this.level().addFreshEntity(iceSpike);

                    this.iceSpikes.add(iceSpike);

                    this.iceSpikesSpawned++;
                    this.iceSpikeSpawnTimer = 20;
                }
            }

            if (this.iceSpikesSpawned == 3 && this.iceSpikeSpawnTimer == 0 && this.lockedTargetPos != null) {
                if (!this.iceSpikes.isEmpty()) {
                    for (IceSpikeEntity iceSpike : this.iceSpikes) {
                        if (iceSpike.isAlive()) {
                            Vec3 direction = this.lockedTargetPos.subtract(iceSpike.position()).normalize().scale(1.5D);
                            iceSpike.setDeltaMovement(direction);
                        }
                    }
                    this.iceSpikes.clear();
                    this.lockedTargetPos = null;
                }
            }
        }
    }

//    private double getIceSpikeX(int pIceSpike) {
//        if (pIceSpike <= 0) {
//            return this.getX();
//        } else {
//            float f = (this.yBodyRot + (float)(180 * (pIceSpike - 1))) * ((float)Math.PI / 180F);
//            float f1 = Mth.cos(f);
//            return this.getX() + (double)f1 * 1.3D;
//        }
//    }
//
//    private double getIceSpikeY(int pIceSpike) {
//        return pIceSpike <= 0 ? this.getY() + 3.0D : this.getY() + 2.2D;
//    }
//
//    private double getIceSpikeZ(int pIceSpike) {
//        if (pIceSpike <= 0) {
//            return this.getZ();
//        } else {
//            float f = (this.yBodyRot + (float)(180 * (pIceSpike - 1))) * ((float)Math.PI / 180F);
//            float f1 = Mth.sin(f);
//            return this.getZ() + (double)f1 * 1.3D;
//        }
//    }
//
//    private float rotlerp(float pAngle, float p_31444_, float p_31445_) {
//        float f = Mth.wrapDegrees(p_31444_ - pAngle);
//        if (f > p_31445_) {
//            f = p_31445_;
//        }
//
//        if (f < -p_31445_) {
//            f = -p_31445_;
//        }
//
//        return pAngle + f;
//    }

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
