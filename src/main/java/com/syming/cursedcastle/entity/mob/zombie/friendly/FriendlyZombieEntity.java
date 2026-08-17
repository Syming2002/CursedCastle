package com.syming.cursedcastle.entity.mob.zombie.friendly;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.entity.mob.zombie.CCZombieEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class FriendlyZombieEntity extends CCZombieEntity {
    public FriendlyZombieEntity(EntityType<? extends CCZombieEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FriendlyZombieEntity(Level pLevel) {
        super(CCEntity.FRIENDLY_ZOMBIE.get(), pLevel);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, (double) 1.0F, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, (double) 1.0F));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<Monster>(this, Monster.class, 10, true, false, (livingEntity) -> {
            return !(livingEntity instanceof FriendlyZombieEntity) && !(livingEntity instanceof FamiliarBatEntity);
        }));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getDirectEntity() instanceof Player) {
            return false;
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount > 100) {
            discard();
        }
    }

    @Override
    public boolean hasWeakness(DamageSource pSource, float pDamageAmount) {
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource pSource, float pDamageAmount) {
        return false;
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
