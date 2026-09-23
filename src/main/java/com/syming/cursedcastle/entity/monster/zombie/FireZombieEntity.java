package com.syming.cursedcastle.entity.monster.zombie;

import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class FireZombieEntity extends CCZombieEntity {

    public FireZombieEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireZombieEntity(Level pLevel) {
        super(CCEntity.FIRE_ZOMBIE.get(), pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        pEntity.setRemainingFireTicks(100);
        return super.doHurtTarget(pEntity);
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
