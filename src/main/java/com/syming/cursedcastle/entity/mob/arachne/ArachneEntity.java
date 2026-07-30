package com.syming.cursedcastle.entity.mob.arachne;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.magic.CobwebProjectileEntity;
import com.syming.cursedcastle.entity.mob.CCMonster;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;

import java.util.List;

public class ArachneEntity extends CCMonster {

    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(ArachneEntity.class, EntityDataSerializers.BYTE);

    public static final byte IDLE_ANIMATION = 0;
    public static final float IDLE_ANIMATION_LENGTH_IN_SECONDS = 2.0F;
    public static final int IDLE_ANIMATION_LENGTH_IN_TICKS = (int) (IDLE_ANIMATION_LENGTH_IN_SECONDS * 20);

    public static final byte CLAWS_ATTACK_ANIMATION = 1;
    public static final float CLAWS_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 2.25F;
    public static final int CLAWS_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (CLAWS_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);

    public static final byte WEB_ATTACK_ANIMATION = 2;
    public static final float WEB_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 2.0F;
    public static final int WEB_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (WEB_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState clawsAttackAnimationState = new AnimationState();
    public final AnimationState webAttackAnimationState = new AnimationState();

    private int clawsAttackAnimationTick = 0;
    private int webAttackAnimationTick = 0;

    private final ArachneHumanBodyPartEntity humanBody;

    public ArachneEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.humanBody = new ArachneHumanBodyPartEntity(this, "humanBody", 0.5F, 1.25F);
    }

    public ArachneEntity(Level pLevel) {
        super(CCEntity.ARACHNE.get(), pLevel);

        this.humanBody = new ArachneHumanBodyPartEntity(this, "humanBody", 0.5F, 1.25F);
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
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
    }

    @Override
    public void tick() {

        if (this.level().isClientSide()) {
            this.idleAnimationState.animateWhen(getAnimation() == IDLE_ANIMATION && (this.clawsAttackAnimationTick <= 0 || this.webAttackAnimationTick <= 0), this.tickCount);
            this.clawsAttackAnimationState.animateWhen(getAnimation() == CLAWS_ATTACK_ANIMATION, this.tickCount);
            this.webAttackAnimationState.animateWhen(getAnimation() == WEB_ATTACK_ANIMATION, this.tickCount);
        }

        super.tick();

        LivingEntity target = getTarget();

        if (getAnimation() == CLAWS_ATTACK_ANIMATION) this.clawsAttackAnimationTick++;
        if (getAnimation() == WEB_ATTACK_ANIMATION) this.webAttackAnimationTick++;

        if (target != null) {

            getLookControl().setLookAt(target, 30.0F, 30.0F);
            if (getAnimation() == IDLE_ANIMATION && target.getFeetBlockState().is(Blocks.COBWEB) && (getRandom().nextFloat() <= 0.9F && getRandom().nextFloat() > 0.33F) && getPerceivedTargetDistanceSquareForMeleeAttack(target) <= getMeleeAttackRangeSqr(target)) {
                setAnimation(CLAWS_ATTACK_ANIMATION);
            }
            if (getAnimation() == IDLE_ANIMATION && !target.getFeetBlockState().is(Blocks.COBWEB) && getRandom().nextFloat() <= 0.33F && getRandom().nextFloat() > 0.22F) {
                setAnimation(WEB_ATTACK_ANIMATION);
            }

            if (getAnimation() == ArachneEntity.CLAWS_ATTACK_ANIMATION) {
                    this.getNavigation().moveTo(target, 2.0D);
                    if (this.clawsAttackAnimationTick == 23 && getPerceivedTargetDistanceSquareForMeleeAttack(target) <= getMeleeAttackRangeSqr(target)) {
                        target.hurt(this.level().damageSources().mobAttack(this), 5.0F);
                    }
                    if (this.clawsAttackAnimationTick == 38 && getPerceivedTargetDistanceSquareForMeleeAttack(target) <= getMeleeAttackRangeSqr(target)) {
                        target.hurt(this.level().damageSources().mobAttack(this), 5.0F);
                    }

                    if (this.clawsAttackAnimationTick >= CLAWS_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                        setAnimation(IDLE_ANIMATION);
                        this.clawsAttackAnimationTick = 0;
                    }
            } else if (getAnimation() == ArachneEntity.WEB_ATTACK_ANIMATION) {
                this.getNavigation().moveTo(target, 1.0D);
                this.getMoveControl().strafe(-0.5F, 0.5F);

                if (this.webAttackAnimationTick >= 5 && this.webAttackAnimationTick <= 18) {
                    setDeltaMovement(Vec3.ZERO);
                }
                if (this.webAttackAnimationTick == 18) {
                    if (!this.level().isClientSide()) {
                        spitCobweb(target);
                    }
                }

                if (this.webAttackAnimationTick >= WEB_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.webAttackAnimationTick = 0;
                }
            }
        } else if (this.clawsAttackAnimationTick >= CLAWS_ATTACK_ANIMATION_LENGTH_IN_TICKS || this.webAttackAnimationTick >= WEB_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
            setAnimation(IDLE_ANIMATION);
            this.clawsAttackAnimationTick = 0;
            this.webAttackAnimationTick = 0;
        }
    }

    public static AttributeSupplier.Builder createArachneAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        updateHumanBody();
    }

//    @Override
//    protected AABB makeBoundingBox() {
//        EntityDimensions dimensions = getDimensions(getPose());
//
//        double width = dimensions.width;
//        double height = dimensions.height;
//
//        double yOffset = 0.5D;
//
//        return new AABB(
//                getX() - width / 2,
//                getY() + yOffset,
//                getZ() - width / 2,
//                getX() + width / 2,
//                getY() + height + yOffset,
//                getZ() + width / 2
//        );
//    }

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

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(CursedCastleMod.MOD_ID, "entities/arachne");
    }

    @Override
    public void makeStuckInBlock(BlockState pState, Vec3 pMotionMultiplier) {
        if (!pState.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(pState, pMotionMultiplier);
        }
    }

    @Override
    public PartEntity<ArachneEntity>[] getParts() {
        return new PartEntity[] {this.humanBody};
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

        this.humanBody.setId(id + 1);
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    protected void updateHumanBody() {
        moveAndRotateHumanBody(this.humanBody, getYRot());
    }

    protected void moveAndRotateHumanBody(ArachneHumanBodyPartEntity humanBody, float yaw) {
        Vec3 offset = new Vec3(0, 1.25, 0.5).yRot(-yaw * Mth.DEG_TO_RAD);

        humanBody.setPos(
                this.getX() + offset.x,
                this.getY() + offset.y,
                this.getZ() + offset.z
        );

        humanBody.setYRot(yaw);
        humanBody.yRotO = yaw;
        humanBody.refreshDimensions();
    }

    private void spitCobweb(LivingEntity pTarget) {
        if (!this.level().isClientSide()) {
            CobwebProjectileEntity cobwebProjectile = new CobwebProjectileEntity(this.level());

            this.level().playSound(null, this, SoundEvents.LLAMA_SPIT, SoundSource.MASTER, 1.0F, 1.5F);

            Vec3 headPos = this.position().add(0.0D, getEyeHeight(), 0.5D);
            Vec3 lookVec = getLookAngle();
            headPos = headPos.add(lookVec);

            cobwebProjectile.setOwner(this);
            cobwebProjectile.setPos(headPos.x, headPos.y, headPos.z);

            Vec3 headAim = pTarget.position().add(0, pTarget.getEyeHeight() * 0.8D, 0);
            Vec3 direction = headAim.subtract(cobwebProjectile.position()).normalize().scale(0.5D);

            cobwebProjectile.shoot(direction.x, direction.y, direction.z, 1.9F, 0.3F);
            this.hasImpulse = true;

            this.level().addFreshEntity(cobwebProjectile);
        }
    }

    public boolean hurtHumanBody(PartEntity<?> part, DamageSource pSource, float pAmount) {
        if (part == getArachneHumanBody()) {
            return super.hurt(pSource, pAmount * 1.5F);
        }
        return false;
    }

    public PartEntity<?> getArachneHumanBody() {
        return this.humanBody;
    }
}
