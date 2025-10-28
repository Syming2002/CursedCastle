package com.syming.cursedcastle.entity.mob.boss;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.api.ICustomBossBar;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import com.syming.cursedcastle.damagesource.CCDamageSource;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.projectile.ScytheProjectileEntity;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class GrimReaperEntity extends FlyingMob implements Enemy, IAnimatedEntity, ICustomBossBar, ILivingEntityStrengthWeakness, RangedAttackMob {
    private Animation animation;

    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(35);
    private int meleeAttackCooldownTick = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 40;

    public static final Animation SCYTHE_ATTACK_ANIMATION = Animation.create(260);
    private int scytheAttackCooldownTick = 0;
    public static final int SCYTHE_PROJECTILE_ATTACK_COOLDOWN = 100;

    private static final Animation[] ANIMATIONS = {MELEE_ATTACK_ANIMATION, SCYTHE_ATTACK_ANIMATION};

    private int animationTick;
    private int tickCounter = 0;

    private double lockedTargetX;
    private double lockedTargetY;
    private double lockedTargetZ;
    private boolean hasLockedTargetPos = false;

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.translatable("entity.cursedcastle.grim_reaper"), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);

    public GrimReaperEntity(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.navigation = new FlyingPathNavigation(this, pLevel);

        this.moveControl = new FlyingMoveControl(this, 10, true);

        this.xpReward = 444;
    }

    public GrimReaperEntity(Level pLevel) {
        this(CCEntity.GRIM_REAPER.get(), pLevel);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return this.navigation;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,  444.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.FLYING_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(5, new RandomFloatGoal(this, 5.0D, 8.0D));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.2D, 100, 16.0F));

    }

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);

        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);

        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        this.bossEvent.setProgress(getHealth() / getMaxHealth());
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(pRandom, pDifficulty);
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(CCItem.DEATH_SCYTHE.get()));
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        populateDefaultEquipmentSlots(pLevel.getRandom(), pDifficulty);
        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        return pSpawnData;
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (!isAlive() && target == null) return;

        if (target != null) {
            getLookControl().setLookAt(target, 30.0F, 30.0F);
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.75F && this.meleeAttackCooldownTick <= 0) {
                setAnimation(MELEE_ATTACK_ANIMATION);
                this.meleeAttackCooldownTick = MELEE_ATTACK_COOLDOWN;
            }
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.5F && this.scytheAttackCooldownTick <= 0) {
                setAnimation(SCYTHE_ATTACK_ANIMATION);
                this.scytheAttackCooldownTick = SCYTHE_PROJECTILE_ATTACK_COOLDOWN;
            }
        }



        if (this.meleeAttackCooldownTick > 0 && getAnimation() == NO_ANIMATION) {
            this.meleeAttackCooldownTick--;
        }

        if (this.scytheAttackCooldownTick > 0 && getAnimation() == NO_ANIMATION) {
            this.scytheAttackCooldownTick--;
        }

        AnimationHandler.INSTANCE.updateAnimations(this);

        shouldDespawnInPeaceful();

        if (target != null && isAlive()) {
            if (getAnimation() == GrimReaperEntity.MELEE_ATTACK_ANIMATION) {

                double distToEnemySqr = distanceToSqr(target);
                double attackReachSqr = getMeleeAttackRangeSqr(target) * 5.0D;
                int animationTick = getAnimationTick();

                if (animationTick == 1 && !this.hasLockedTargetPos) {

                    this.lockedTargetX = target.getX();
                    this.lockedTargetY = target.getY();
                    this.lockedTargetZ = target.getZ();
                    this.hasLockedTargetPos = true;


                    double boostedSpeed = 1.5D;
                    getNavigation().moveTo(target, boostedSpeed);
                }

                if (animationTick < 25 && this.hasLockedTargetPos) {
                    Vec3 targetPos = new Vec3(this.lockedTargetX, this.lockedTargetY, this.lockedTargetZ);
                    Vec3 dir = targetPos.subtract(position()).normalize();
                    setDeltaMovement(dir.scale(0.6D));
                }

                if (animationTick == 25 && distToEnemySqr <= attackReachSqr) {
                    doHurtTarget(target);
                }

                if (animationTick > 25) {
                    double minDistanceSq = 4.0D;
                    if (distToEnemySqr < minDistanceSq) {
                        double x = getX() - target.getX();
                        double y = getY() - target.getY();
                        double z = getZ() - target.getZ();

                        double length = Math.sqrt(x * x + y * y + z * z);
                        if (length > 0.0D) {
                            x /= length;
                            y /= length;
                            z /= length;
                        }

                        double retreatX = getX() + x * 2.5D;
                        double retreatY = getY() + y * 2.5D;
                        double retreatZ = getZ() + z * 2.5D;

                        getNavigation().moveTo(retreatX, retreatY, retreatZ, 10.0D);
                    }
                }

                if (getNavigation().isDone() && this.hasLockedTargetPos) {
                    this.hasLockedTargetPos = false;
                }

            } else if (getAnimation() == GrimReaperEntity.SCYTHE_ATTACK_ANIMATION) {

                if (getAnimationTick() > 20 && getAnimationTick() <= 180) {
                    this.tickCounter++;
                    if (this.tickCounter >= 20) {

                        double radius = 2.0;
                        double angle = Math.random() * 2 * Math.PI;
                        double offsetX = radius * Math.cos(angle);
                        double offsetZ = radius * Math.sin(angle);

                        Vec3 randomPosition = new Vec3(getX() + offsetX, getY() + getEyeHeight(), getZ() + offsetZ);

                        Vec3 targetPosition = target.position().add(0, target.getEyeHeight() * 0.5, 0);

                        Vec3 direction = targetPosition.subtract(randomPosition).normalize();

                        ScytheProjectileEntity scytheProjectile = new ScytheProjectileEntity(this, this.level());
                        scytheProjectile.setPos(randomPosition.x, randomPosition.y, randomPosition.z);
                        scytheProjectile.shoot(direction.x, direction.y, direction.z, 0.75F, 0.1F);

                        this.level().addFreshEntity(scytheProjectile);

                        this.tickCounter = 0;
                    }
                }

            }
        }
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
    public @Nullable ResourceLocation getCustomBossBar() {
        return null;
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
                    return source.is(CCDamageSource.HOLY);
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
                    return source.is(CCDamageSource.DARK);
                }
            }
        }
        return false;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {

    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    static class RandomFloatGoal extends Goal {
        private final GrimReaperEntity grimReaper;
        private final double speed;
        private final double radius;
        private double targetX;
        private double targetY;
        private double targetZ;
        private int cooldown;

        public RandomFloatGoal(GrimReaperEntity grimReaper, double speed, double radius) {
            this.grimReaper = grimReaper;
            this.speed = speed;
            this.radius = radius;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.grimReaper.getAnimation() != GrimReaperEntity.MELEE_ATTACK_ANIMATION || this.grimReaper.getAnimation() != GrimReaperEntity.SCYTHE_ATTACK_ANIMATION) {
                if (this.grimReaper.isVehicle()) return false;
                if (--this.cooldown > 0) return false;
                this.cooldown = this.grimReaper.getRandom().nextInt(40) + 20; // every 1–3 seconds

                Vec3 randomPos = getRandomPosition();
                if (randomPos == null) return false;

                this.targetX = randomPos.x;
                this.targetY = randomPos.y;
                this.targetZ = randomPos.z;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !this.grimReaper.getNavigation().isDone();
        }

        @Override
        public void start() {
            this.grimReaper.getNavigation().moveTo(this.targetX, this.targetY, this.targetZ, this.speed);
        }

        private Vec3 getRandomPosition() {
            double x = this.grimReaper.getX() + (this.grimReaper.getRandom().nextDouble() * 2 - 1) * this.radius;
            double y = this.grimReaper.getY() + (this.grimReaper.getRandom().nextDouble() * 2 - 1) * (this.radius / 2);
            double z = this.grimReaper.getZ() + (this.grimReaper.getRandom().nextDouble() * 2 - 1) * this.radius;
            return new Vec3(x, y, z);
        }
    }
}
