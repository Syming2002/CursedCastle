package com.syming.cursedcastle.entity.monster;

import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.entity.magic.HarpyFeatherEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.DaggerEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
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
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.BiConsumer;

public class HarpyEntity extends CCFlyingMonster implements RangedAttackMob {
    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(HarpyEntity.class, EntityDataSerializers.BYTE);

    public static final byte IDLE_ANIMATION = 0;

    public static final byte FEATHER_ATTACK_ANIMATION = 1;
    public static final float FEATHER_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 1.75F;
    public static final int FEATHER_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (FEATHER_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);
    private static final int FEATHER_ATTACK_COOLDOWN = 100;
    private int featherAttackCooldown = FEATHER_ATTACK_COOLDOWN;

    public static final byte DAGGER_ATTACK_ANIMATION = 2;
    public static final float DAGGER_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 1.6F;
    public static final int DAGGER_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (DAGGER_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);
    private static final int DAGGER_ATTACK_COOLDOWN = 200;
    private int daggerAttackCooldown = DAGGER_ATTACK_COOLDOWN;

    public final AnimationState featherAnimationState = new AnimationState();
    public final AnimationState daggerAnimationState = new AnimationState();

    private int featherAnimationTick = 0;
    private int daggerAnimationTick = 0;

    public HarpyEntity(EntityType<? extends FlyingMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createHarpyAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 52.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.FLYING_SPEED, 0.4000000059604645D);
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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(ANIMATION, IDLE_ANIMATION);
    }

    @Override
    public void tick() {

        if (this.level().isClientSide()) {
            this.featherAnimationState.animateWhen(getAnimation() == FEATHER_ATTACK_ANIMATION, this.tickCount);
            this.daggerAnimationState.animateWhen(getAnimation() == DAGGER_ATTACK_ANIMATION, this.tickCount);
        }

        super.tick();

        LivingEntity target = getTarget();

        if (target != null) {
            lookAt(target, 30.0F, 30.0F);

            if (getAnimation() == IDLE_ANIMATION) {
                if (this.featherAttackCooldown > 0) {
                    this.featherAttackCooldown--;
                }

                if (this.daggerAttackCooldown > 0) {
                    this.daggerAttackCooldown--;
                }
            }

            if (getAnimation() == IDLE_ANIMATION && this.featherAttackCooldown <= 0 && isAlive() && getRandom().nextFloat() <= 5.0F && getRandom().nextFloat() > 0.2F) {
                setAnimation(FEATHER_ATTACK_ANIMATION);
            }

            if (getAnimation() == IDLE_ANIMATION && this.daggerAttackCooldown <= 0 && isAlive() && getRandom().nextFloat() <= 0.2F) {
                setAnimation(DAGGER_ATTACK_ANIMATION);
            }

            if (getAnimation() == HarpyEntity.DAGGER_ATTACK_ANIMATION) {
                this.daggerAnimationTick++;

                Vec3 daggerPos = new Vec3(0.0D, 0.0D, 1.0F);
                daggerPos = daggerPos.xRot((float) Math.toRadians(this.getXRot()));
                daggerPos = daggerPos.yRot((float) Math.toRadians(this.getYHeadRot()));
                daggerPos = daggerPos.add(getX(), getY() + 0.5D, getZ());

                if (this.daggerAnimationTick == 25) {
                    launchDagger(daggerPos.x, daggerPos.y, daggerPos.z, 1.5F);
                }

                if (this.daggerAnimationTick > DAGGER_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.daggerAnimationTick = 0;
                    this.daggerAttackCooldown = DAGGER_ATTACK_COOLDOWN;
                }
            } else if (getAnimation() == HarpyEntity.FEATHER_ATTACK_ANIMATION) {
                this.featherAnimationTick++;

                Vec3 featherPos = new Vec3(0.0D, 0.0D, 1.0F);
                featherPos = featherPos.xRot((float) Math.toRadians(-getXRot()));
                featherPos = featherPos.yRot((float) Math.toRadians(-getYRot()));
                featherPos = featherPos.add(getX(),getY() + getEyeHeight() / 1.25F, getZ());

                if (this.featherAnimationTick == 22) {
                    launchHarpyFeather(featherPos, getXRot(), getYRot());
                }

                if (this.featherAnimationTick > FEATHER_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.featherAnimationTick = 0;
                    this.featherAttackCooldown = FEATHER_ATTACK_COOLDOWN;
                }
            }
        } else {
            setAnimation(IDLE_ANIMATION);
            this.featherAnimationTick = 0;
            this.featherAttackCooldown = FEATHER_ATTACK_COOLDOWN;
            this.daggerAnimationTick = 0;
            this.daggerAttackCooldown = DAGGER_ATTACK_COOLDOWN;
        }

        shouldDespawnInPeaceful();
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageType.ICE);
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
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageType.WIND);
                }
            }
        }
        return false;
    }

    @Override
    public List<Component> getWeaknessDescription() {
        return List.of(Component.translatable("entity.cursedcastle.ice_weakness_description").setStyle(Style.EMPTY.withColor(ColorUtil.ICE_COLOR)));
    }

    @Override
    public List<Component> getStrengthDescription() {
        return List.of(Component.translatable("entity.cursedcastle.wind_strength_description").setStyle(Style.EMPTY.withColor(ColorUtil.WIND_COLOR)));
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    public byte getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(byte pAnimation) {
        this.entityData.set(ANIMATION, pAnimation);
    }

    private void launchDagger(double posX, double posY, double posZ, double speed) {
        ItemStack woodenStack = new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(Tiers.WOOD).get());
        ItemStack stoneStack = new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(Tiers.STONE).get());
        ItemStack ironStack = new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(Tiers.IRON).get());
        ItemStack goldenStack = new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(Tiers.GOLD).get());
        ItemStack diamondStack = new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(Tiers.DIAMOND).get());
        ItemStack netheriteStack = new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(Tiers.NETHERITE).get());


        if (!this.level().isClientSide()) {
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
