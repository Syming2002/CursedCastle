package com.syming.cursedcastle.entity.mob.friendly;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.CCMonster;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FamiliarBatEntity extends CCMonster implements FlyingAnimal, OwnableEntity {
    private Player summoner;
    private BlockPos spawnPosition;

    public FamiliarBatEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.summoner = pLevel.getPlayerByUUID(getUUID());
        refreshDimensions();
    }

    public FamiliarBatEntity(Level pLevel) {
        super(CCEntity.FAMILIAR_BAT.get(), pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<Monster>(this, Monster.class, true));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.2D);
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
    public boolean isFlying() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        followPlayer();

        setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.spawnPosition != null && (!this.level().isEmptyBlock(this.spawnPosition) || this.spawnPosition.getY() <= this.level().getMinBuildHeight())) {
            this.spawnPosition = null;
        }

        if (this.spawnPosition == null || this.random.nextInt(30) == 0 || this.spawnPosition.closerToCenterThan(this.position(), 2.0D)) {
            this.spawnPosition = BlockPos.containing(this.getX() + (double) this.random.nextInt(7) - (double) this.random.nextInt(7), this.getY() + (double) this.random.nextInt(6) - 2.0D, this.getZ() + (double) this.random.nextInt(7) - (double) this.random.nextInt(7));
        }

        if (getTarget() != null) {
            double d2 = (double) this.getTarget().getX() + 0.5D - this.getX();
            double d0 = (double) this.getTarget().getY() + 0.1D - this.getY();
            double d1 = (double) this.getTarget().getZ() + 0.5D - this.getZ();
            Vec3 vec3 = this.getDeltaMovement();
            Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double) 0.1F, (Math.signum(d0) * (double) 0.7F - vec3.y) * (double) 0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double) 0.1F);
            this.setDeltaMovement(vec31);
            float f = (float) (Mth.atan2(vec31.z, vec31.x) * (double) (180F / (float) Math.PI)) - 90.0F;
            float f1 = Mth.wrapDegrees(f - this.getYRot());
            this.zza = 0.5F;
            this.setYRot(this.getYRot() + f1);
        }
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    private void followPlayer() {

        this.summoner = level().getNearestPlayer(this, -2);

        if (this.summoner != null && this.getTarget() == null) {
            double moveSpeed = 0.25F;

            if (!this.summoner.isAlive() || this.summoner.distanceToSqr(this) > Double.MAX_VALUE) {
                this.summoner = null;
                discard();
                return;
            }

            double distanceToPlayer = this.distanceToSqr(this.summoner);

            if (distanceToPlayer > 2.0D) {
                Vec3 lookAngle = this.summoner.getLookAngle().normalize();

                double dX = this.summoner.getX() - lookAngle.x - this.getX() - 2.0D;
                double dY = this.summoner.getY() + this.summoner.getEyeHeight() - this.getY();
                double dZ = this.summoner.getZ() - lookAngle.z - this.getZ() - 2.0D;
                double distance = Mth.sqrt((float) (dX * dX + dY * dY + dZ * dZ));

                setDeltaMovement(dX / distance * moveSpeed, dY / distance * moveSpeed,dZ / distance * moveSpeed);

                this.setYRot((float) (Math.toDegrees(Math.atan2(dZ, dX)) - 90.0F));
            } else {
                setDeltaMovement(0, 0, 0);
            }
        }
    }

    @Override
    protected float getSoundVolume() {
        return 0.1F;
    }

    @Override
    public float getVoicePitch() {
        return super.getVoicePitch() * 0.95F;
    }

    @Override
    @Nullable
    public SoundEvent getAmbientSound() {
        return this.random.nextInt(4) != 0 ? null : SoundEvents.BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }


    @Override
    public @Nullable UUID getOwnerUUID() {
        return this.summoner.getUUID();
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
