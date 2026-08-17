package com.syming.cursedcastle.entity.mob.boss.dracula;

import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.entity.magic.FirePillarEntity;
import com.syming.cursedcastle.entity.mob.boss.CCBossEntity;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
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
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//import com.syming.cursedcastle.network.CCBossInfoPacket;

public class DraculaEntity extends CCBossEntity {

    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(DraculaEntity.class, EntityDataSerializers.BYTE);

    public static final byte IDLE_ANIMATION = 0;
    public static final float IDLE_ANIMATION_LENGTH_IN_SECONDS = 2.0F;
    public static final int IDLE_ANIMATION_LENGTH_IN_TICKS = (int) (IDLE_ANIMATION_LENGTH_IN_SECONDS * 20);

    public static final byte FIREBALLS_ANIMATION = 1;
    public static final float FIREBALLS_ANIMATION_LENGTH_IN_SECONDS = 4.0F;
    public static final int FIREBALLS_ANIMATION_LENGTH_IN_TICKS = (int) (FIREBALLS_ANIMATION_LENGTH_IN_SECONDS * 20);

    public static final byte DARK_INFERNO_ANIMATION = 2;
    public static final float DARK_INFERNO_ANIMATION_LENGTH_IN_SECONDS = 4.0F;
    public static final int DARK_INFERNO_ANIMATION_LENGTH_IN_TICKS = (int) (DARK_INFERNO_ANIMATION_LENGTH_IN_SECONDS * 20);

    public static final byte FIRE_PILLAR_ANIMATION = 3;
    public static final float FIRE_PILLAR_ANIMATION_LENGTH_IN_SECONDS = 3.0F;
    public static final int FIRE_PILLAR_ANIMATION_LENGTH_IN_TICKS = (int) (FIRE_PILLAR_ANIMATION_LENGTH_IN_SECONDS * 20);

    public static final byte BATS_ATTACK_ANIMATION = 4;
    public static final float BATS_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 2.75F;
    public static final int BATS_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (BATS_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState fireballsAnimationState = new AnimationState();
    public final AnimationState darkInfernoAnimationState = new AnimationState();
    public final AnimationState firePillarAnimationState = new AnimationState();
    public final AnimationState batsAttackAnimationState = new AnimationState();

    private int fireballsAnimationTick = 0;
    private int darkInfernoAnimationTick = 0;
    private int firePillarAnimationTick = 0;
    private int batsAttackAnimationTick = 0;

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

        this.entityData.define(ANIMATION, IDLE_ANIMATION);
    }

    public void setAnimation(byte pAnimation) {
        this.entityData.set(ANIMATION, pAnimation);
    }

    public byte getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        return super.getTarget();
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
    public void tick() {

        if (this.level().isClientSide()) {
            this.idleAnimationState.animateWhen(!isAttacking() && getAnimation() == IDLE_ANIMATION && (this.fireballsAnimationTick <= 0 || this.darkInfernoAnimationTick <= 0 || this.firePillarAnimationTick <= 0), this.tickCount);
            this.fireballsAnimationState.animateWhen(isAttacking() && !isTeleporting() && getAnimation() == FIREBALLS_ANIMATION, this.tickCount);
            this.darkInfernoAnimationState.animateWhen(isAttacking() && !isTeleporting() && getAnimation() == DARK_INFERNO_ANIMATION, this.tickCount);
            this.firePillarAnimationState.animateWhen(isAttacking() && !isTeleporting() && getAnimation() == FIRE_PILLAR_ANIMATION, this.tickCount);
        }

        super.tick();

        LivingEntity target = getTarget();

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
            if ((!isTeleporting() && !isInvisible() && isAttacking()) && getAnimation() == IDLE_ANIMATION && (getRandom().nextFloat() <= 0.75F && getRandom().nextFloat() > 0.5F) && this.currentState == ActionState.ATTACKING) {
                setAnimation(FIREBALLS_ANIMATION);
            }
            if ((!isTeleporting() && !isInvisible() && isAttacking()) && getAnimation() == IDLE_ANIMATION && (getRandom().nextFloat() <= 0.5F && getRandom().nextFloat() > 0.33F) && this.currentState == ActionState.ATTACKING) {
                setAnimation(DARK_INFERNO_ANIMATION);
            }
            if ((!isTeleporting() && !isInvisible() && isAttacking()) && getAnimation() == IDLE_ANIMATION && (getRandom().nextFloat() <= 0.33F && getRandom().nextFloat() > 0.0F) && this.currentState == ActionState.ATTACKING) {
                setAnimation(FIRE_PILLAR_ANIMATION);
            }
        } else {
            setAnimation(IDLE_ANIMATION);
            this.fireballsAnimationTick = 0;
            this.darkInfernoAnimationTick = 0;
            this.firePillarAnimationTick = 0;
            this.batsAttackAnimationTick = 0;
            this.isAttacking = false;
            this.isTeleporting = false;
            setInvisible(false);
            setInvulnerable(false);
        }

        shouldDespawnInPeaceful();

        if (target != null && isAlive()) {
            if (getAnimation() == DraculaEntity.FIREBALLS_ANIMATION) {
                if (this.fireballsAnimationTick == 40) {
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
            } else if (getAnimation() == DraculaEntity.DARK_INFERNO_ANIMATION) {
                if (this.darkInfernoAnimationTick == 40) {
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
                if (this.darkInfernoAnimationTick == 60) {
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
            } else if (getAnimation() == DraculaEntity.FIRE_PILLAR_ANIMATION) {
                if (this.firePillarAnimationTick == 30) {
                    spawnFirePillar();
                    System.out.println("Fire Pillar appearing");
                }
            }


            this.stateTick++;

            switch (this.currentState) {
                case ATTACKING:

                    this.isAttacking = true;
                    this.isTeleporting = false;

                    if (getAnimation() == FIREBALLS_ANIMATION) this.fireballsAnimationTick++;
                    if (getAnimation() == DARK_INFERNO_ANIMATION) this.darkInfernoAnimationTick++;
                    if (getAnimation() == FIRE_PILLAR_ANIMATION) this.firePillarAnimationTick++;

                    if (this.fireballsAnimationTick >= FIREBALLS_ANIMATION_LENGTH_IN_TICKS
                            || this.darkInfernoAnimationTick >= DARK_INFERNO_ANIMATION_LENGTH_IN_TICKS
                            || this.firePillarAnimationTick >= FIRE_PILLAR_ANIMATION_LENGTH_IN_TICKS) {
                        transitionTo(ActionState.WAITING_AFTER_ATTACK);


                    }
                    break;

                case WAITING_AFTER_ATTACK:

                    this.fireballsAnimationTick = 0;
                    this.darkInfernoAnimationTick = 0;
                    this.firePillarAnimationTick = 0;
                    setAnimation(IDLE_ANIMATION);
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

            this.randomTeleport(d0, d1, d2, true);
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

    private void transitionTo(ActionState newState) {
        this.currentState = newState;
        this.stateTick = 0;
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
                    return source.is(CCDamageType.FIRE) || source.is(CCDamageType.HOLY) || source == target.damageSources().onFire() || source == target.damageSources().inFire();
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
        return List.of(
                Component.translatable("entity.cursedcastle.fire_weakness_description").setStyle(Style.EMPTY.withColor(ColorUtil.FIRE_COLOR)),
                Component.translatable("entity.cursedcastle.holy_weakness_description").setStyle(Style.EMPTY.withColor(ColorUtil.HOLY_COLOR)));
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

    public boolean isAttackingAnimation() {
        return getAnimation() == FIREBALLS_ANIMATION || getAnimation() == DARK_INFERNO_ANIMATION || getAnimation() == FIRE_PILLAR_ANIMATION;
    }

    public enum ActionState {
        ATTACKING,
        WAITING_AFTER_ATTACK,
        TELEPORTING,
        WAITING_AFTER_TELEPORT
    }
}
