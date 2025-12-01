package com.syming.cursedcastle.entity.mob;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import com.syming.cursedcastle.datagen.CCDatapackBuiltinEntriesProvider;
import com.syming.cursedcastle.entity.magic.HarpyFeatherEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.DaggerEntity;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class HarpyEntity extends FlyingMob implements Enemy, IAnimatedEntity, ILivingEntityStrengthWeakness, RangedAttackMob {
    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(HarpyEntity.class, EntityDataSerializers.INT);


    protected Animation animation;

    public static final Animation FEATHER_ATTACK_ANIMATION = Animation.create(35);
    protected int featherAnimationCooldown = 0;
    public static final int FEATHER_ATTACK_COOLDOWN = 60;

    public static final Animation DAGGER_ATTACK_ANIMATION = Animation.create(32);
    private int daggerAttackAnimationCooldown = 0;
    public static final int DAGGER_ATTACK_COOLDOWN = 80;

    protected static final Animation[] ANIMATIONS = {FEATHER_ATTACK_ANIMATION, DAGGER_ATTACK_ANIMATION};

    protected int animationTick;

    public HarpyEntity(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createHarpyAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 52.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4000000059604645D);
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
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (target != null) {
            getLookControl().setLookAt(target, 30.0F, 30.0F);
            if (getAnimation() == NO_ANIMATION && this.daggerAttackAnimationCooldown <= 0 && isAlive() && getRandom().nextFloat() <= 0.33F) {
                setAnimation(DAGGER_ATTACK_ANIMATION);
                this.daggerAttackAnimationCooldown = DAGGER_ATTACK_COOLDOWN;
            }
            if (getAnimation() == NO_ANIMATION && this.featherAnimationCooldown <= 0 && isAlive() && getRandom().nextFloat() <= 0.45F) {
                setAnimation(FEATHER_ATTACK_ANIMATION);
                this.featherAnimationCooldown = FEATHER_ATTACK_COOLDOWN;
            }
        }

        if (target != null) {
            if (getAnimation() == HarpyEntity.DAGGER_ATTACK_ANIMATION) {
                lookAt(target, 30.0F, 30.0F);

                Vec3 daggerPos = new Vec3(0.0D, 0.0D, 1.0F);
                daggerPos = daggerPos.xRot((float) Math.toRadians(this.getXRot()));
                daggerPos = daggerPos.yRot((float) Math.toRadians(this.getYHeadRot()));
                daggerPos = daggerPos.add(getX(), getY() + 0.5D, getZ());

                if (getAnimationTick() == 25) {
                    launchDagger(daggerPos.x, daggerPos.y, daggerPos.z, 1.5F);
                }
            }
            if (getAnimation() == HarpyEntity.FEATHER_ATTACK_ANIMATION) {
                lookAt(target, 30.0F, 30.0F);

                Vec3 featherPos = new Vec3(0.0D, 0.0D, 1.0F);
                featherPos = featherPos.xRot((float) Math.toRadians(-getXRot()));
                featherPos = featherPos.yRot((float) Math.toRadians(-getYRot()));
                featherPos = featherPos.add(getX(),getY() + getEyeHeight() / 1.25F, getZ());

                if (getAnimationTick() == 22) {
                    launchHarpyFeather(featherPos, getXRot(), getYRot());
                }
            }
        }

        if (this.daggerAttackAnimationCooldown > 0) this.daggerAttackAnimationCooldown--;
        if (this.featherAnimationCooldown > 0) this.featherAnimationCooldown--;

        AnimationHandler.INSTANCE.updateAnimations(this);

        shouldDespawnInPeaceful();
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
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
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDatapackBuiltinEntriesProvider.ICE);
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
                    return source.is(CCDatapackBuiltinEntriesProvider.WIND);
                }
            }
        }
        return false;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    private void launchDagger(double posX, double posY, double posZ, double speed) {
        ItemStack woodenStack = new ItemStack(CCItem.WOODEN_DAGGER.get());
        ItemStack stoneStack = new ItemStack(CCItem.STONE_DAGGER.get());
        ItemStack ironStack = new ItemStack(CCItem.IRON_DAGGER.get());
        ItemStack goldenStack = new ItemStack(CCItem.GOLDEN_DAGGER.get());
        ItemStack diamondStack = new ItemStack(CCItem.DIAMOND_DAGGER.get());
        ItemStack netheriteStack = new ItemStack(CCItem.NETHERITE_DAGGER.get());


        if (!this.level().isClientSide) {
            if (getRandom().nextFloat() < 0.33F) {
                DaggerEntity dagger = new DaggerEntity(this, this.level(),  woodenStack, Tiers.WOOD);
                dagger.setPos(posX, posY, posZ);
                dagger.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, (float) speed, 0.1F);
                this.level().addFreshEntity(dagger);
            } else if (getRandom().nextFloat() < 0.45F) {
                DaggerEntity dagger = new DaggerEntity(this, this.level(), stoneStack, Tiers.STONE);
                dagger.setPos(posX, posY, posZ);
                dagger.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, (float) speed, 0.1F);
                this.level().addFreshEntity(dagger);
            } else if (getRandom().nextFloat() < 0.5F) {
                DaggerEntity dagger = new DaggerEntity(this, this.level(), ironStack, Tiers.IRON);
                dagger.setPos(posX, posY, posZ);
                dagger.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, (float) speed, 0.1F);
                this.level().addFreshEntity(dagger);
            } else if (getRandom().nextFloat() < 0.40F) {
                DaggerEntity dagger = new DaggerEntity(this, this.level(), goldenStack, Tiers.GOLD);
                dagger.setPos(posX, posY, posZ);
                dagger.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, (float) speed, 0.1F);
                this.level().addFreshEntity(dagger);
            } else if (getRandom().nextFloat() < 0.25F) {
                DaggerEntity dagger = new DaggerEntity(this, this.level(), diamondStack, Tiers.DIAMOND);
                dagger.setPos(posX, posY, posZ);
                dagger.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, (float) speed, 0.1F);
                this.level().addFreshEntity(dagger);
            } else if (getRandom().nextFloat() < 0.20F) {
                DaggerEntity dagger = new DaggerEntity(this, this.level(), netheriteStack, Tiers.NETHERITE);
                dagger.setPos(posX, posY, posZ);
                dagger.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, (float) speed, 0.1F);
                this.level().addFreshEntity(dagger);
            }
        }
    }

    private void launchHarpyFeather(Vec3 pFeatherPos, float pPitch, float pYaw) {
        if (this.level().isClientSide()) return;

        double speed = 1.2D;
        float spread = 15.0F;

        BiConsumer<Float, Float> spawnFeather = (yawOffset, pitchOffset) -> {
            float yaw = pYaw + yawOffset;
            float pitch = pPitch + pitchOffset;

            double motionX = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * speed;
            double motionY = -Math.sin(Math.toRadians(pitch)) * speed;
            double motionZ = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * speed;

            HarpyFeatherEntity feather = new HarpyFeatherEntity(this.level(), this);
            feather.setPos(pFeatherPos.x, pFeatherPos.y, pFeatherPos.z);
            feather.setDeltaMovement(motionX, motionY, motionZ);

            float yawDeg = (float)(Math.atan2(motionX, motionZ) * (180F / Math.PI));
            float pitchDeg = (float)(Math.atan2(motionY, Math.sqrt(motionX * motionX + motionZ * motionZ)) * (180F / Math.PI));
            feather.setYRot(yawDeg);
            feather.setXRot(pitchDeg);
            feather.yRotO = yawDeg;
            feather.xRotO = pitchDeg;

            this.level().addFreshEntity(feather);
        };

        spawnFeather.accept(0.0F, 0.0F);
        spawnFeather.accept(-spread, 0.0F);
        spawnFeather.accept(spread, 0.0F);
    }
}
