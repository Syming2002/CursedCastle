package com.syming.cursedcastle.entity.mob.boss;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.goal.RusalkaGoToWaterGoal;
import com.syming.cursedcastle.entity.goal.RusalkaPathNavigation;
import com.syming.cursedcastle.entity.goal.RusalkaRandomStrollOnWaterGoal;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RusalkaEntity extends CCBossEntity implements IAnimatedEntity {

    private Animation animation;

    public static final Animation MELEE_ATTACK_ANIMATION = Animation.create(40);
//    public static final Animation HAIR_ATTACK_ANIMATION = Animation.create(35);
    public static final Animation WATER_ATTACK_ANIMATION = Animation.create(80);

    private int meleeAttackCooldown = 0;
    public static final int MELEE_ATTACK_COOLDOWN = 100;
//    private int hairAttackCooldown = 0;
//    public static final int HAIR_ATTACK_COOLDOWN = 200;
    private int waterAttackCooldown = 0;
    public static final int WATER_ATTACK_COOLDOWN = 200;

    private static final Animation[] ANIMATIONS = {MELEE_ATTACK_ANIMATION, /*HAIR_ATTACK_ANIMATION,*/ WATER_ATTACK_ANIMATION};

    private int animationTick;

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.translatable("entity.cursedcastle.rusalka"), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);

    private BlockPos currentWaterPos;
    private int waterTicks = 0;

    public RusalkaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public RusalkaEntity(Level pLevel) {
        super(CCEntity.RUSALKA.get(), pLevel);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(3, new RusalkaGoToWaterGoal(this, 1.2D));
        this.goalSelector.addGoal(7, new RusalkaRandomStrollOnWaterGoal(this, 0.9D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));

    }

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);

//        CCPacketHandler.sendToPlayer(new CCBossInfoPacket.Display(this.bossEvent.getId(), this.getId()), pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);

//        CCPacketHandler.sendToPlayer(new CCBossInfoPacket.Remove(this.bossEvent.getId(), this.getId()), pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        this.bossEvent.setProgress(getHealth() / getMaxHealth());
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (!isAlive() && target == null) return;

        if (target != null) {
            getLookControl().setLookAt(target, 30.0F, 30.0F);
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.75F && this.meleeAttackCooldown <= 0 && distanceToSqr(target) <= getMeleeAttackRangeSqr(target)) {
                setAnimation(MELEE_ATTACK_ANIMATION);
                this.meleeAttackCooldown = MELEE_ATTACK_COOLDOWN;
            }
//            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.5F && this.hairAttackCooldown <= 0) {
//                setAnimation(HAIR_ATTACK_ANIMATION);
//                this.hairAttackCooldown = HAIR_ATTACK_COOLDOWN;
//            }
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.33F && this.waterAttackCooldown <= 0 && distanceToSqr(target) >= getMeleeAttackRangeSqr(target)) {
                setAnimation(WATER_ATTACK_ANIMATION);
                this.waterAttackCooldown = WATER_ATTACK_COOLDOWN;
            }

            if (getAnimation() == RusalkaEntity.MELEE_ATTACK_ANIMATION) {
                System.out.println("Rusalka Melee Attack Animation is set");
                double distToEnemySqr = getPerceivedTargetDistanceSquareForMeleeAttack(target);
                double attackReachSqr = getMeleeAttackRangeSqr(target);

                if (distToEnemySqr <= attackReachSqr && getAnimationTick() == 15) {
                    doHurtTarget(target);
                }
            } /*else if (getAnimation() == RusalkaEntity.HAIR_ATTACK_ANIMATION) {
                System.out.println("Rusalka Hair Attack Animation is set");
                double distToEnemy = distanceTo(target);
                double attackReachSqr = getMeleeAttackRangeSqr(target);

                if (distToEnemy <= attackReachSqr && getAnimationTick() == 20) {
                    doHurtTarget(target);
                }
            }*/ else if (getAnimation() == RusalkaEntity.WATER_ATTACK_ANIMATION) {
                System.out.println("Rusalka Water Attack Animation is set");
                if (getAnimationTick() == 30) {
                    spawnWaterBlock();
                }
            }
        }

        if (this.waterTicks > 0) {
            this.waterTicks--;
            if (this.waterTicks == 0 && this.currentWaterPos != null) {
                removeWaterBlock();
            }
        }


        if (this.meleeAttackCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.meleeAttackCooldown--;
        }

//        if (this.hairAttackCooldown > 0 && getAnimation() == NO_ANIMATION) {
//            this.hairAttackCooldown--;
//        }

        if (this.waterAttackCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.waterAttackCooldown--;
        }

        AnimationHandler.INSTANCE.updateAnimations(this);

        shouldDespawnInPeaceful();

        floatRusalka();
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new RusalkaPathNavigation(this, pLevel);
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        if (pLevel.getFluidState(pPos).is(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return 0.0F;
        }
    }

    @Override
    public boolean canStandOnFluid(FluidState pFluidState) {
        return pFluidState.is(FluidTags.WATER);
    }

    @Override
    public boolean isPushedByFluid(FluidType type) {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
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
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageType.THUNDER);
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
                    return source.is(CCDamageType.ICE);
                }
            }
        }
        return false;
    }

    @Override
    public List<Component> getWeaknessDescription() {
        return List.of(
                Component.translatable("entity.cursedcastle.thunder_weakness_description").setStyle(Style.EMPTY.withColor(ColorUtil.THUNDER_COLOR)));
    }

    @Override
    public List<Component> getStrengthDescription() {
        return List.of(Component.translatable("entity.cursedcastle.ice_strength_description").setStyle(Style.EMPTY.withColor(ColorUtil.ICE_COLOR)));
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
        if (this.currentWaterPos != null && this.level().getBlockState(this.currentWaterPos).is(Blocks.WATER)) {
            this.level().setBlock(this.currentWaterPos, Blocks.AIR.defaultBlockState(), 3);
        }

        this.currentWaterPos = null;
    }

    private void floatRusalka() {
        if (isInWater()) {
            CollisionContext collisionContext = CollisionContext.of(this);
            if (collisionContext.isAbove(LiquidBlock.STABLE_SHAPE, blockPosition(), true) && !this.level().getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                setOnGround(true);
            } else {
                setDeltaMovement(getDeltaMovement().scale(0.5D).add(0.0D, 0.05D, 0.0D));
            }
        }
    }

    @Override
    public @Nullable ResourceLocation getCustomBossBarBackground() {
        return null;
    }

    @Override
    public @Nullable ResourceLocation getCustomBossBar() {
        return null;
    }
}
