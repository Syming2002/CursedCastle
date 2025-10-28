package com.syming.cursedcastle.entity.mob.boss.dracula;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.ICustomBossBar;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
//import com.syming.cursedcastle.network.CCBossInfoPacket;
import com.syming.cursedcastle.network.CCPacketHandler;
import com.syming.cursedcastle.entity.magic.FirePillarEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;

public class DraculaEntity extends Monster implements IAnimatedEntity, ILivingEntityStrengthWeakness {
    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(DraculaEntity.class, EntityDataSerializers.INT);

    private Animation animation;

    public static final Animation FIREBALLS_ANIMATION = Animation.create(80);
    private int fireballsCooldown = 0;
    public static final int FIREBALLS_COOLDOWN = 100;

    public static final Animation DARK_INFERNO_ANIMATION = Animation.create(80);
    private int darkInfernoCooldown = 0;
    public static final int DARK_INFERNO_COOLDOWN = 200;

    public static final Animation FIRE_PILLAR_ANIMATION = Animation.create(60);
    private int firePillarCooldown = 0;
    public static final int FIRE_PILLAR_COOLDOWN = 250;

    public static final Animation BATS_ATTACK_ANIMATION = Animation.create(55);
    private int batsAttackCooldown = 0;
    public static final int BATS_ATTACK_COOLDOWN = 225;

    private static final Animation[] ANIMATIONS = {FIREBALLS_ANIMATION, DARK_INFERNO_ANIMATION, FIRE_PILLAR_ANIMATION, BATS_ATTACK_ANIMATION};

    private int animationTick;

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.translatable("entity.cursedcastle.dracula"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);


    private final DraculaHeadPartEntity head;
    private int teleportTick;
    private boolean isTeleporting = false;

    public ActionState currentState = ActionState.ATTACKING;
    private int stateTick = 0;

    private boolean isAttacking = false;

    public DraculaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.head = new DraculaHeadPartEntity(this, "head", 0.7F, 0.6F);

        refreshDimensions();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])));
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

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 600.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.0D);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    protected void updateHead() {
        moveHead(this.head, 0, 2.0F, 0);
    }

    protected void moveHead(DraculaHeadPartEntity head, double x, double y, double z) {
        Vec3 lastPos = new Vec3(head.getX(), head.getY(), head.getZ());
        head.setPos(this.getX() + x, this.getY() + y, this.getZ() + z);

        head.xo = lastPos.x;
        head.yo = lastPos.y;
        head.zo = lastPos.z;

        head.xOld = lastPos.x;
        head.yOld = lastPos.y;
        head.zOld = lastPos.z;
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
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (!isAlive() && target == null) return;

        if (this.level().isDay() && !this.level().isClientSide()) {
            float lightness = getLightLevelDependentMagicValue();

            if (lightness > 0.5F && this.random.nextFloat() * 30.0F < (lightness - 0.4F) * 2.0F && this.level().canSeeSky(new BlockPos((int) getX(), (int) (getY() + (double) getEyeHeight()), (int) getZ()))) {
                setSecondsOnFire(10);
            }
        }

        if (!this.level().isClientSide() && this.level().isDay() && this.level() instanceof ServerLevel serverLevel) {
            if (isAlive()) {
                serverLevel.setDayTime(18000);
            }
        }

        if (target != null) {
            getLookControl().setLookAt(target, 30.0F, 30.0F);
            if ((!isTeleporting() && !isInvisible() && isAttacking()) && getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.75F && this.fireballsCooldown <= 0 && this.currentState == ActionState.ATTACKING) {
                setAnimation(FIREBALLS_ANIMATION);
                this.fireballsCooldown = FIREBALLS_COOLDOWN;
            }
            if ((!isTeleporting() && !isInvisible() && isAttacking()) && getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.5F && this.darkInfernoCooldown <= 0 && this.currentState == ActionState.ATTACKING) {
                setAnimation(DARK_INFERNO_ANIMATION);
                this.darkInfernoCooldown = DARK_INFERNO_COOLDOWN;
            }
            if ((!isTeleporting() && !isInvisible() && isAttacking()) && getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.33F && this.firePillarCooldown <= 0 && this.currentState == ActionState.ATTACKING) {
                setAnimation(FIRE_PILLAR_ANIMATION);
                this.firePillarCooldown = FIRE_PILLAR_COOLDOWN;
            }
        }



        if (this.fireballsCooldown > 0 && getAnimation() == NO_ANIMATION && (!isTeleporting() && !isInvisible())) {
            this.fireballsCooldown--;
        }

        if (this.darkInfernoCooldown > 0 && getAnimation() == NO_ANIMATION && (!isTeleporting() && !isInvisible())) {
            this.darkInfernoCooldown--;
        }

        if (this.firePillarCooldown > 0 && getAnimation() == NO_ANIMATION && (!isTeleporting() && !isInvisible())) {
            this.firePillarCooldown--;
        }

        AnimationHandler.INSTANCE.updateAnimations(this);

        shouldDespawnInPeaceful();

        if (target != null && isAlive()) {
            if (getAnimation() == DraculaEntity.FIREBALLS_ANIMATION && isAttacking()) {
                if (getAnimationTick() == 40) {
                    Vec3 underRightArmPosSmallFireballs = new Vec3(-0.5F, 1.275F, 1.0F);
                    underRightArmPosSmallFireballs = underRightArmPosSmallFireballs.xRot((float) Math.toRadians(-getXRot()));
                    underRightArmPosSmallFireballs = underRightArmPosSmallFireballs.yRot((float) Math.toRadians(-getYHeadRot()));
                    underRightArmPosSmallFireballs = underRightArmPosSmallFireballs.add(getX(), getY(), getZ());


                    double targetX = target.getX() - getX();
                    double targetY = target.getBoundingBox().minY + (double) (target.getEyeHeight() / 2.0F) - getY();
                    double targetZ = target.getZ() - getZ();

                    float distance = Mth.sqrt((float) (targetX * targetX + targetY * targetY + targetZ * targetZ)) * 0.5F;

                    double directionX = targetX / distance;
                    double directionY = targetY / distance;
                    double directionZ = targetZ / distance;

                    double speed = 0.5F;

                    launchFireball(underRightArmPosSmallFireballs.x, underRightArmPosSmallFireballs.y, underRightArmPosSmallFireballs.z, directionX + 0.2F, directionY, directionZ, speed);
                    launchFireball(underRightArmPosSmallFireballs.x, underRightArmPosSmallFireballs.y, underRightArmPosSmallFireballs.z, directionX, directionY, directionZ, speed);
                    launchFireball(underRightArmPosSmallFireballs.x, underRightArmPosSmallFireballs.y, underRightArmPosSmallFireballs.z, directionX - 0.2F, directionY, directionZ, speed);


                    System.out.println("Launching Fireballs");
                }
            } else if (getAnimation() == DraculaEntity.DARK_INFERNO_ANIMATION && isAttacking()) {
                if (getAnimationTick() == 40) {
                    Vec3 underLeftArmPosFirstLargeFireball = new Vec3(0.5F, 1.275F, 1.0F);
                    underLeftArmPosFirstLargeFireball = underLeftArmPosFirstLargeFireball.xRot((float) Math.toRadians(-getXRot()));
                    underLeftArmPosFirstLargeFireball = underLeftArmPosFirstLargeFireball.yRot((float) Math.toRadians(-getYHeadRot()));
                    underLeftArmPosFirstLargeFireball = underLeftArmPosFirstLargeFireball.add(getX(), getY(), getZ());

                    double targetX = target.getX() - getX();
                    double targetY = target.getBoundingBox().minY + (double) (target.getEyeHeight() / 2.0F) - getY();
                    double targetZ = target.getZ() - getZ();

                    float distance = Mth.sqrt((float) (targetX * targetX + targetY * targetY + targetZ * targetZ)) * 0.5F;

                    double directionX = targetX / distance;
                    double directionY = targetY / distance;
                    double directionZ = targetZ / distance;

                    double speed = 0.5F;

                    launchLargeFireball(
                            underLeftArmPosFirstLargeFireball.x,
                            underLeftArmPosFirstLargeFireball.y,
                            underLeftArmPosFirstLargeFireball.z,
                            directionX,
                            directionY,
                            directionZ,
                            speed);
                    System.out.println("First Large Fireball is spawning!");
                }
                if (getAnimationTick() == 60) {
                    Vec3 underLeftArmPosSecondLargeFireball = new Vec3(0.5F, 0.6375F, 1.0F);
                    underLeftArmPosSecondLargeFireball = underLeftArmPosSecondLargeFireball.xRot((float) Math.toRadians(-getXRot()));
                    underLeftArmPosSecondLargeFireball = underLeftArmPosSecondLargeFireball.yRot((float) Math.toRadians(-getYHeadRot()));
                    underLeftArmPosSecondLargeFireball = underLeftArmPosSecondLargeFireball.add(getX(), getY(), getZ());

                    double targetX = target.getX() - getX();
                    double targetY = target.getBoundingBox().minY + (double) (target.getEyeHeight() / 2.0F) - getY();
                    double targetZ = target.getZ() - getZ();

                    float distance = Mth.sqrt((float) (targetX * targetX + targetY * targetY + targetZ * targetZ)) * 0.5F;

                    double directionX = targetX / distance;
                    double directionY = targetY / distance;
                    double directionZ = targetZ / distance;

                    double speed = 0.5F;

                    launchLargeFireball(
                            underLeftArmPosSecondLargeFireball.x,
                            underLeftArmPosSecondLargeFireball.y,
                            underLeftArmPosSecondLargeFireball.z,
                            directionX,
                            directionY,
                            directionZ,
                            speed);

                    System.out.println("Second Large Fireball is spawning!");
                }
            } else if (getAnimation() == DraculaEntity.FIRE_PILLAR_ANIMATION && isAttacking()) {
                if (getAnimationTick() == 30) {
                    spawnFirePillar();
                    System.out.println("Fire Pillar appearing");
                }
            }
            this.stateTick++;

            switch (this.currentState) {
                case ATTACKING:

                    this.isAttacking = true;
                    this.isTeleporting = false;

                    if (isFireballsAttackAnimationIsFinished() || isDarkInfernoAttackAnimationIsFinished() || isFirePillarAttackAnimationIsFinished()) {
                        transitionTo(ActionState.WAITING_AFTER_ATTACK);
                        this.isAttacking = false;
                    }
                    break;

                case WAITING_AFTER_ATTACK:

                    this.isTeleporting = false;
                    this.isAttacking = false;

                    if (this.stateTick >= 5) {
                        this.teleportTick = 0;
                        transitionTo(ActionState.TELEPORTING);
                    }
                    break;

                case TELEPORTING:
                    this.teleportTick++;

                    this.isTeleporting = true;
                    this.isAttacking = false;

                    if (this.teleportTick >= 70 && this.teleportTick < 100) {
                        setInvisible(true);
                        setInvulnerable(true);
                    }

                    if (this.teleportTick >= 80 && this.teleportTick < 90) {
                        teleportAroundTarget();
                    }

                    if (this.teleportTick >= 110 && this.teleportTick < 130) {
                        setInvisible(false);
                        setInvulnerable(false);
                    }

                    if (isTeleportTickIsFinished()) {
                        this.teleportTick = 0;
                        transitionTo(ActionState.WAITING_AFTER_TELEPORT);
                    }
                    System.out.println(this.teleportTick);
                    break;

                case WAITING_AFTER_TELEPORT:

                    this.isTeleporting = false;
                    this.isAttacking = false;

                    if (this.stateTick >= 5) {
                        transitionTo(ActionState.ATTACKING);
                    }
                    break;
            }
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    public boolean hurtHead(PartEntity<?> part, DamageSource pSource, float pAmount) {
        if (part == getDraculaHead()) {
            return super.hurt(pSource, pAmount);
        }
        return false;
    }

    public PartEntity<?> getDraculaHead() {
        return this.head;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        updateHead();

        this.bossEvent.setProgress(getHealth() / getMaxHealth());
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public PartEntity<DraculaEntity>[] getParts() {
        return new PartEntity[] {this.head};
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);

        PartEntity<?>[] parts = getParts();

        for (int i = 0; i < parts.length; ++i) {
            parts[i].setId(i + pPacket.getId());
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void setId(int id) {
        super.setId(id);

        this.head.setId(id + 1);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
    }


    public void teleportAroundTarget() {
        LivingEntity target = getTarget();
        if (target != null) {
            double radius = 8.0D;

            double d0 = target.getX() + (this.random.nextDouble() - 0.5D) * radius;
            double d1 = target.getY() + (double)(this.random.nextInt(16) - 8);
            double d2 = target.getZ() + (this.random.nextDouble() - 0.5D) * radius;

            this.teleportTo(d0, d1, d2);
        }
    }

    @Override
    public void teleportTo(double pX, double pY, double pZ) {
        Level level = this.level();
        double oldX = this.getX();
        double oldY = this.getY();
        double oldZ = this.getZ();

        this.setPos(pX, pY, pZ);

        BlockPos pos = this.blockPosition();
        boolean success = false;

        if (level.hasChunkAt(pos)) {
            boolean foundGround = false;

            while (!foundGround && pos.getY() > 0) {
                BlockPos below = pos.below();
                BlockState state = level.getBlockState(below);

                if (state.blocksMotion()) {
                    foundGround = true;
                } else {
                    this.setPos(this.getX(), this.getY() - 1.0, this.getZ());
                    pos = below;
                }
            }

            if (foundGround) {
                this.setPos(this.getX(), this.getY(), this.getZ());

                AABB boundingBox = this.getBoundingBox();
                boolean noCollision = level.noCollision(this, boundingBox);
                boolean notInLiquid = !level.containsAnyLiquid(boundingBox);

                if (noCollision && notInLiquid) {
                    success = true;
                }
            }

            if (!success) {
                this.setPos(oldX, oldY, oldZ);
                return;
            }
        }

        if (this instanceof PathfinderMob) {
            getNavigation().stop();
        }
    }

    private void launchFireball(double posX, double posY, double posZ, double directionX, double directionY, double directionZ, double speed) {
        if (!this.level().isClientSide()) {
            SmallFireball smallFireball = new SmallFireball(
                    this.level(),
                    this,
                    directionX * speed,
                    directionY * speed,
                    directionZ * speed);
            smallFireball.setPos(posX, posY, posZ);
            this.level().addFreshEntity(smallFireball);
        }
    }

    private void launchLargeFireball(double posX, double posY, double posZ, double directionX, double directionY, double directionZ, double speed) {
        if (!this.level().isClientSide()) {
            LargeFireball largeFireball = new LargeFireball(
                    level(),
                    this,
                    directionX * speed,
                    directionY * speed,
                    directionZ * speed,
                    0);
            largeFireball.setPos(posX, posY, posZ);
            this.level().addFreshEntity(largeFireball);
        }
    }

    private void spawnFirePillar() {
        LivingEntity target = getTarget();
        if (target != null) {
            FirePillarEntity firePillar = new FirePillarEntity(this.level(), target.getX(), target.getY(), target.getZ());
            this.level().addFreshEntity(firePillar);
        }
    }

    public boolean isTeleportTickIsFinished() {
        return this.teleportTick > 130;
    }

    public boolean isTeleporting() {
        return this.isTeleporting;
    }

    public int getTeleportTick() {
        return this.teleportTick;
    }

    public boolean isAttacking() {
        return this.isAttacking;
    }

    public boolean isFireballsAttackAnimationIsFinished() {
        return getAnimation() == FIREBALLS_ANIMATION && this.animationTick >= FIREBALLS_ANIMATION.getDuration() - 10;
    }

    public boolean isDarkInfernoAttackAnimationIsFinished() {
        return getAnimation() == DARK_INFERNO_ANIMATION && this.animationTick >= DARK_INFERNO_ANIMATION.getDuration() - 10;
    }

    public boolean isFirePillarAttackAnimationIsFinished() {
        return getAnimation() == FIRE_PILLAR_ANIMATION && this.animationTick >= FIRE_PILLAR_ANIMATION.getDuration() - 10;
    }

    private void transitionTo(ActionState newState) {
        this.currentState = newState;
        this.stateTick = 0;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.6F, 2.5F);
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
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        return false;
    }

    public enum ActionState {
        ATTACKING,
        WAITING_AFTER_ATTACK,
        TELEPORTING,
        WAITING_AFTER_TELEPORT
    }
}
