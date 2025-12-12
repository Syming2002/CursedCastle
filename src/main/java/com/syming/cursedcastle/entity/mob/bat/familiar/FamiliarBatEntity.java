package com.syming.cursedcastle.entity.mob.bat.familiar;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.bat.CCBatEntity;
import com.syming.cursedcastle.entity.mob.friendly.ZombieFriendlyEntity;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class FamiliarBatEntity extends CCBatEntity implements OwnableEntity {
    private Player summoner;
    private boolean isBehindSummoner = false;
    private Vec3 spawnOffset = null;
    private int attackCount = 0;

    public FamiliarBatEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.summoner = pLevel.getPlayerByUUID(getUUID());
        this.noPhysics = true;

    }

    public FamiliarBatEntity(Level pLevel) {
        super(CCEntity.FAMILIAR_BAT.get(), pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<Monster>(this, Monster.class, 10, true, false, (livingEntity) -> {
            return !(livingEntity instanceof FamiliarBatEntity) && !(livingEntity instanceof ZombieFriendlyEntity);
        }));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    public void tick() {
        super.tick();
        followPlayer();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean result = super.doHurtTarget(pEntity);
        if (result) {
            this.attackCount++;

            if (this.attackCount >= 10) {
                this.discard();
            }
        }
        return result;
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public @Nullable UUID getOwnerUUID() {
        return this.summoner.getUUID();
    }

    private void followPlayer() {

        this.summoner = level().getNearestPlayer(this, -1);

        if (this.summoner != null && this.getTarget() == null) {
            double moveSpeed = 0.25D;

            if (!this.summoner.isAlive() || this.summoner.distanceToSqr(this) > Double.MAX_VALUE) {
                this.summoner = null;
                discard();
                return;
            }

            double distanceToPlayer = this.distanceToSqr(this.summoner);

            if (distanceToPlayer > 4.0D) {
                Vec3 direction = new Vec3(this.summoner.getX() - getX(), (this.summoner.getY() + this.summoner.getEyeHeight()) - getY(), this.summoner.getZ() - getZ());

                direction = direction.normalize().scale(moveSpeed);
                this.setDeltaMovement(direction);

                this.isBehindSummoner = false;
            } else {
                if (this.spawnOffset == null) {
                    float xOffset = (this.random.nextBoolean() ? 1.0F : -1.0F) * this.random.nextFloat();

                    float zOffset = -1.25F;

                    this.spawnOffset = new Vec3(xOffset, 0.0F, zOffset);
                }

                Vec3 familiarBatPos = this.spawnOffset;
                familiarBatPos = familiarBatPos.yRot((float) Math.toRadians(-this.summoner.getYRot()));
                familiarBatPos = familiarBatPos.add(this.summoner.getX(), this.summoner.getY() + this.summoner.getEyeHeight(), this.summoner.getZ());



                this.setPos(familiarBatPos.x, familiarBatPos.y, familiarBatPos.z);
                this.setDeltaMovement(Vec3.ZERO);

                this.isBehindSummoner = true;
            }

            if (isBehindSummoner()) {
                this.setYRot(this.summoner.getYRot());
            }
        }
    }

    private boolean isBehindSummoner() {
        return this.isBehindSummoner;
    }

    @Override
    public List<Component> getWeaknessDescription() {
        return List.of();
    }

    @Override
    public List<Component> getStrengthDescription() {
        return List.of();
    }
}
