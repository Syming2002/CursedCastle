package com.syming.cursedcastle.entity.monster.shade;

import com.syming.cursedcastle.entity.monster.CCFlyingMonster;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import static net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn;

public abstract class ShadeEntity extends CCFlyingMonster implements RangedAttackMob {

    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(ShadeEntity.class, EntityDataSerializers.BYTE);

    public static final byte IDLE_ANIMATION = 0;

    public static final byte REPULSE_ATTACK_ANIMATION = 1;
    public static final float REPULSE_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 9.0F;
    public static final int REPULSE_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (REPULSE_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);
    protected static final int REPULSE_ATTACK_COOLDOWN = 150;
    protected int repulseAttackCooldown = REPULSE_ATTACK_COOLDOWN;

    public final AnimationState repulseAttackAnimationState = new AnimationState();

    protected int repulseAttackAnimationTick = 0;

    protected int healTickCounter = 0;

    public ShadeEntity(EntityType<? extends ShadeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.xpReward = (int) 1.36F;

        refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(ANIMATION, IDLE_ANIMATION);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 100, 16.0F));
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

        if (this.level().isClientSide()) {
            this.repulseAttackAnimationState.animateWhen(getAnimation() == REPULSE_ATTACK_ANIMATION, this.tickCount);
        }

        super.tick();

        LivingEntity target = getTarget();

        if (target != null) {
            if (this.repulseAttackCooldown > 0) {
                this.repulseAttackCooldown--;
            }

            if (this instanceof ThunderShadeEntity ? distanceToSqr(target) <= 2.5F : distanceToSqr(target) <= 6.0F && getAnimation() == IDLE_ANIMATION && this.repulseAttackCooldown <= 0 && isAlive()) {
                setAnimation(REPULSE_ATTACK_ANIMATION);
            }

            if (getAnimation() == REPULSE_ATTACK_ANIMATION) {
                this.repulseAttackAnimationTick++;

                if (this.repulseAttackAnimationTick > REPULSE_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.repulseAttackAnimationTick = 0;
                    this.repulseAttackCooldown = REPULSE_ATTACK_COOLDOWN;
                }
            }
        } else {
            setAnimation(IDLE_ANIMATION);
            this.repulseAttackCooldown = REPULSE_ATTACK_COOLDOWN;
            this.repulseAttackAnimationTick = 0;
        }

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

    public byte getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(byte pAnimation) {
        this.entityData.set(ANIMATION, pAnimation);
    }

    protected boolean isRepulseAnimation() {
        return getAnimation() == REPULSE_ATTACK_ANIMATION;
    }

    protected void onShadeHeal(ShadeEntity shade) {
        if (!shade.isAlive() || shade.getHealth() >= shade.getMaxHealth()) return;

        this.healTickCounter++;

        if (this.healTickCounter == 20) {
            shade.heal(2.0F);
            this.healTickCounter = 0;
        }
    }

    public static boolean checkShadeSpawnRules(EntityType<? extends ShadeEntity> pType, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        BlockPos blockpos = pPos.below();
        return pSpawnType == MobSpawnType.SPAWNER || pLevel.getBlockState(blockpos).isValidSpawn(pLevel, blockpos, pType) && checkMonsterSpawnRules(pType, (ServerLevelAccessor) pLevel, pSpawnType, pPos, pRandom);
    }

    public static boolean checkMonsterSpawnRules(EntityType<? extends ShadeEntity> pType, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(pLevel, pPos, pRandom) && checkMobSpawnRules(pType, pLevel, pSpawnType, pPos, pRandom);
    }
}
