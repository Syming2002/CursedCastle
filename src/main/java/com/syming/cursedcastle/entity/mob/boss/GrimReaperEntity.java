package com.syming.cursedcastle.entity.mob.boss;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.api.ICustomBossBar;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import com.syming.cursedcastle.damagesource.CCDamageSource;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.projectile.ScytheProjectileEntity;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class GrimReaperEntity extends Monster implements IAnimatedEntity, ICustomBossBar, ILivingEntityStrengthWeakness, FlyingAnimal {
    private Animation animation;

    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(35);
    private int meleeAttackCooldownTick = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 40;

    public static final Animation SCYTHE_ATTACK_ANIMATION = Animation.create(200);
    private int scytheAttackCooldownTick = 0;
    public static final int SCYTHE_PROJECTILE_ATTACK_COOLDOWN = 100;

    private static final Animation[] ANIMATIONS = {MELEE_ATTACK_ANIMATION, SCYTHE_ATTACK_ANIMATION};

    private int animationTick;
    private int tickCounter = 0;

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.translatable("entity.cursedcastle.grim_reaper"), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);

    public GrimReaperEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.navigation = new FlyingPathNavigation(this, pLevel);
    }

    public GrimReaperEntity(Level pLevel) {
        this(CCEntity.GRIM_REAPER.get(), pLevel);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,  444.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.FLYING_SPEED, 0.5D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FloatGoal(this));
//        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));

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
                double attackReachSqr = getMeleeAttackRangeSqr(target);
                int animationTick = getAnimationTick();

                if (animationTick < 25) {
                    this.getNavigation().moveTo(target, 2.5D);
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

                        getMoveControl().setWantedPosition(retreatX, retreatY, retreatZ, 5.0F);
                    }
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

                        ScytheProjectileEntity scytheProjectile = new ScytheProjectileEntity(this.level());
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
    protected boolean shouldDespawnInPeaceful() {
        return true;
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
    public boolean isFlying() {
        return !onGround();
    }
}
