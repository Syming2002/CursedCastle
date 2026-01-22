package com.syming.cursedcastle.entity.mob.boss;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.goal.FindWaterGoal;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaHeadPartEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.fluids.FluidType;

import java.util.List;

public class RusalkaEntity extends Monster implements IAnimatedEntity, ILivingEntityStrengthWeakness {
    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(DraculaEntity.class, EntityDataSerializers.INT);

    private Animation animation;

    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(40);
    public static final Animation HAIR_ATTACK_ANIMATION = Animation.create(35);
    public static final Animation WATER_ATTACK_ANIMATION = Animation.create(80);

    private int meleeAttackCooldown = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 100;
    private int hairAttackCooldown = 0;
    public static final int HAIR_ATTACK_COOLDOWN = 200;
    private int waterAttackCooldown = 0;
    public static final int WATER_ATTACK_COOLDOWN = 200;

    private static final Animation[] ANIMATIONS = {MELEE_ATTACK_ANIMATION, HAIR_ATTACK_ANIMATION, WATER_ATTACK_ANIMATION};

    private int animationTick;

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.translatable("entity.cursedcastle.rusalka"), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);

    private BlockPos currentWaterPos;
    private int waterTicks = 0;

    public RusalkaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RusalkaEntity(Level pLevel) {
        super(CCEntity.RUSALKA.get(), pLevel);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(0, new FindWaterGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (!isAlive() && target == null) return;

        if (target != null) {
            getLookControl().setLookAt(target, 30.0F, 30.0F);
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.75F && this.meleeAttackCooldown <= 0) {
                setAnimation(MELEE_ATTACK_ANIMATION);
                this.meleeAttackCooldown = MELEE_ATTACK_COOLDOWN;
            }
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.5F && this.hairAttackCooldown <= 0) {
                setAnimation(HAIR_ATTACK_ANIMATION);
                this.hairAttackCooldown = HAIR_ATTACK_COOLDOWN;
            }
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.33F && this.waterAttackCooldown <= 0) {
                setAnimation(WATER_ATTACK_ANIMATION);
                this.waterAttackCooldown = WATER_ATTACK_COOLDOWN;
            }

            if (getAnimation() == RusalkaEntity.MELEE_ATTACK_ANIMATION) {
                double distToEnemySqr = getPerceivedTargetDistanceSquareForMeleeAttack(target);
                double attackReachSqr = getMeleeAttackRangeSqr(target);

                if (distToEnemySqr <= attackReachSqr && getAnimationTick() == 15) {
                    doHurtTarget(target);
                }
            } else if (getAnimation() == RusalkaEntity.HAIR_ATTACK_ANIMATION) {
                double distToEnemy = distanceTo(target);
                double attackReachSqr = getMeleeAttackRangeSqr(target);

                if (distToEnemy <= attackReachSqr && getAnimationTick() == 20) {
                    doHurtTarget(target);
                }
            } else if (getAnimation() == RusalkaEntity.WATER_ATTACK_ANIMATION) {
                if (this.waterTicks > 0) {
                    this.waterTicks--;
                    if (this.waterTicks == 0 && this.currentWaterPos != null) {
                        removeWaterBlock();
                    }
                }

                if (getAnimationTick() == 30) {
                    spawnWaterBlock();
                }
            }
        }



        if (this.meleeAttackCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.meleeAttackCooldown--;
        }

        if (this.hairAttackCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.hairAttackCooldown--;
        }

        if (this.waterAttackCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.waterAttackCooldown--;
        }

        AnimationHandler.INSTANCE.updateAnimations(this);

        shouldDespawnInPeaceful();
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        if (pLevel.getFluidState(pPos).is(FluidTags.WATER)) {
            return 10.0F;
        }
        return -5.0F;
    }

    @Override
    public boolean canStandOnFluid(FluidState pFluidState) {
        return pFluidState.is(Fluids.WATER) || pFluidState.is(Fluids.FLOWING_WATER);
    }

    @Override
    public boolean isPushedByFluid(FluidType type) {
        return false;
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

    private void spawnWaterBlock() {
        if (getTarget() == null) return;

        if (!this.level().isClientSide()) {
            if (this.currentWaterPos != null) {
                removeWaterBlock();
            }

            this.currentWaterPos = new BlockPos((int) getTarget().getX(), (int) (getTarget().getY() + getTarget().getEyeHeight() + 2.0F), (int) getTarget().getZ());

            this.level().setBlock(this.currentWaterPos, Blocks.WATER.defaultBlockState(), 3);

            this.waterTicks = 60;
        }
    }

    private void removeWaterBlock() {
        if (this.currentWaterPos != null && this.level().getBlockState(this.currentWaterPos).getFluidState().is(FluidTags.WATER)) {
            this.level().setBlock(this.currentWaterPos, Blocks.AIR.defaultBlockState(), 3);
        }

        this.currentWaterPos = null;
    }
}
