package com.syming.cursedcastle.entity.monster.boss;

import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.entity.monster.MedusaSnakeEntity;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MedusaEntity extends CCBossEntity {
    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(MedusaEntity.class, EntityDataSerializers.BYTE);

    public static final byte IDLE_ANIMATION = 0;

    public static final byte MELEE_ATTACK_ANIMATION = 2;
    public static final float MELEE_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 2.25F;
    public static final int MELEE_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (MELEE_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);
    private int meleeAttackCooldown = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 60;
    private int meleeAttackAnimationTick = 0;

    public final AnimationState meleeAttackAnimationState = new AnimationState();

    public static final byte STARE_ATTACK_ANIMATION = 3;
    public static final float STARE_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 6.0F;
    public static final int STARE_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (STARE_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);
    private int stareAttackCooldown = 0;
    public static final int STARE_ATTACK_COOLDOWN = 150;
    public int stareAttackAnimationTick = 0;

    public final AnimationState stareAttackAnimationState = new AnimationState();

    private int snakeAttackCooldown = 0;
    public static final int SNAKE_ATTACK_COOLDOWN = 200;

    private boolean strafingBackwards;
    private boolean strafingClockwise;

    public MedusaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(ANIMATION, IDLE_ANIMATION);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
    }

    public static AttributeSupplier.Builder createMedusaAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 550.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            this.meleeAttackAnimationState.animateWhen(getAnimation() == MELEE_ATTACK_ANIMATION, this.tickCount);
            this.stareAttackAnimationState.animateWhen(getAnimation() == STARE_ATTACK_ANIMATION, this.tickCount);
        }

        super.tick();

        LivingEntity target = getTarget();

        if (target != null) {
            lookAt(target, 30.0F, 30.0F);

            if (getAnimation() == IDLE_ANIMATION) {
                if (this.meleeAttackCooldown > 0) {
                    this.meleeAttackCooldown--;
                }

                if (this.stareAttackCooldown > 0) {
                    this.stareAttackCooldown--;
                }

                if (this.snakeAttackCooldown > 0) {
                    this.snakeAttackCooldown--;
                }

                if (this.snakeAttackCooldown <= 0) {
                    for (int i = 0; i < 3; i++) {
                        MedusaSnakeEntity medusaSnake = new MedusaSnakeEntity(this.level());
                        medusaSnake.setPos(this.getX() + i, this.getY(), this.getZ() + i);
                        this.level().addFreshEntity(medusaSnake);
                        this.snakeAttackCooldown = SNAKE_ATTACK_COOLDOWN;
                    }
                }
            }

            if (getAnimation() == IDLE_ANIMATION && getRandom().nextFloat() > 0.33F && getRandom().nextFloat() <= 0.75F && this.meleeAttackCooldown <= 0) {
                setAnimation(MELEE_ATTACK_ANIMATION);
            }

            if (getAnimation() == IDLE_ANIMATION && getRandom().nextFloat() <= 0.33F && this.stareAttackCooldown <= 0 && !isWithinMeleeAttackRange(target)) {
                setAnimation(STARE_ATTACK_ANIMATION);
            }

            if (getAnimation() == MedusaEntity.MELEE_ATTACK_ANIMATION) {
                this.meleeAttackAnimationTick++;

                if (this.meleeAttackAnimationTick < 25) {
                    if (distanceTo(target) <= 10.0F) {
                        this.getNavigation().moveTo(target, 1.6D);
                    }
                }

                if (isWithinMeleeAttackRange(target) && this.meleeAttackAnimationTick == 25) {
                    doHurtTarget(target);
                }

                if (this.meleeAttackAnimationTick >= MELEE_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.meleeAttackAnimationTick = 0;
                    this.meleeAttackCooldown = MELEE_ATTACK_COOLDOWN;
                }
            } else if (getAnimation() == MedusaEntity.STARE_ATTACK_ANIMATION) {
                this.stareAttackAnimationTick++;

                if (this.getRandom().nextFloat() < 0.3F) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                if (this.getRandom().nextFloat() < 0.3F) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);

                if (this.stareAttackAnimationTick == 65) {
                    stareAttack(target);
                }

                if (this.stareAttackAnimationTick >= STARE_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.stareAttackAnimationTick = 0;
                    this.stareAttackCooldown = STARE_ATTACK_COOLDOWN;
                }
            }
        } else {
            setAnimation(IDLE_ANIMATION);
            this.meleeAttackAnimationTick = 0;
            this.meleeAttackCooldown = MELEE_ATTACK_COOLDOWN;
            this.stareAttackAnimationTick = 0;
            this.stareAttackCooldown = STARE_ATTACK_COOLDOWN;
        }
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
                    return source.is(CCDamageType.HOLY);
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
                    return source.is(CCDamageType.DARK);
                }
            }
        }
        return false;
    }

    @Override
    public List<Component> getWeaknessDescription() {
        return List.of(Component.translatable("entity.cursedcastle.holy_weakness_description").setStyle(Style.EMPTY.withColor(ColorUtil.HOLY_COLOR)));
    }

    @Override
    public List<Component> getStrengthDescription() {
        return List.of(Component.translatable("entity.cursedcastle.dark_strength_description").setStyle(Style.EMPTY.withColor(ColorUtil.DARK_COLOR)));
    }

    @Override
    public @Nullable ResourceLocation getCustomBossBarBackground() {
        return null;
    }

    @Override
    public @Nullable ResourceLocation getCustomBossBar() {
        return null;
    }

    public byte getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(byte pAnimation) {
        this.entityData.set(ANIMATION, pAnimation);
    }

    private void stareAttack(LivingEntity pTarget) {
        if (isTargetLookingAtMedusa(pTarget)) {
            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 100, false, false));
            System.out.println("Target look at Medusa");
        }
    }

    private boolean isTargetLookingAtMedusa(LivingEntity pTarget) {
        Vec3 medusaPos = new Vec3(getX() - pTarget.getX(), getY() - pTarget.getY(), getZ() - pTarget.getZ()).normalize();
        Vec3 targetLookVec = pTarget.getLookAngle().normalize();

        double dotProduct = medusaPos.dot(targetLookVec);
        double angle = Math.toDegrees(Math.acos(dotProduct));

        return angle < 60.0F;
    }
}
