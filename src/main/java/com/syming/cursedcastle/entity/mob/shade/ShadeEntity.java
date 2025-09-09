package com.syming.cursedcastle.entity.mob.shade;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class ShadeEntity extends FlyingMob implements IAnimatedEntity, Enemy, ILivingEntityStrengthWeakness, RangedAttackMob {
    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(ShadeEntity.class, EntityDataSerializers.INT);


    protected Animation animation;

    public static final Animation REPULSE_ATTACK_ANIMATION = Animation.create(180);
    protected int repulseAnimationCooldown = 0;
    public static final int REPULSE_ATTACK_COOLDOWN = 150;

    protected static final Animation[] ANIMATIONS = {REPULSE_ATTACK_ANIMATION};

    protected int animationTick;

    protected int healTickCounter = 0;

    public ShadeEntity(EntityType<? extends ShadeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.xpReward = (int) 1.36F;

        refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(TARGET_ID,-1);
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
        this.entityData.set(TARGET_ID, living == null ? -1 : living.getId());
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        int id = this.entityData.get(TARGET_ID);
        Entity entity = this.level().getEntity(id);
        return (entity instanceof LivingEntity living) ? living : null;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 100, 15.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D);
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (target != null) {
            getLookControl().setLookAt(target, 30.0F, 30.0F);
            if (this instanceof ThunderShadeEntity ? distanceToSqr(target) <= 2.5F : distanceToSqr(target) <= 6.0F && getAnimation() == NO_ANIMATION && this.repulseAnimationCooldown <= 0 && isAlive()) {
                setAnimation(REPULSE_ATTACK_ANIMATION);
                this.repulseAnimationCooldown = REPULSE_ATTACK_COOLDOWN;
            }
        }

        if (this.repulseAnimationCooldown > 0) this.repulseAnimationCooldown--;

        AnimationHandler.INSTANCE.updateAnimations(this);

        shouldDespawnInPeaceful();
    }

    @Override
    public float getEyeHeight(Pose pPose) {
        if (isRepulseAnimation()) {
            return getBbHeight() * 0.75F;
        } else {
            return 1.62F;
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.5F, 1.875F);
    }

    @Override
    public int getAnimationTick() {
        return this.animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
    }

    @Override
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }

    protected boolean isRepulseAnimation() {
        return this.animation == REPULSE_ATTACK_ANIMATION;
    }

    protected void onShadeHeal(ShadeEntity shade) {
        if (!shade.isAlive() || shade.getHealth() >= shade.getMaxHealth()) return;

        this.healTickCounter++;

        if (this.healTickCounter == 20) {
            shade.heal(2.0F);
            this.healTickCounter = 0;
        }
    }
}
