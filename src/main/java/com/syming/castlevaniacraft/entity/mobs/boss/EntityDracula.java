package com.syming.castlevaniacraft.entity.mobs.boss;

import com.syming.castlevaniacraft.api.ICustomBossBar;
import com.syming.castlevaniacraft.entity.ai.dracula.*;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;
import com.syming.castlevaniacraft.entity.mobs.EntityVampireBat;
import com.syming.castlevaniacraft.loot.CCTreasures;
import com.syming.castlevaniacraft.networking.CCNetworking;
import com.syming.castlevaniacraft.networking.MessageAddBossInfo;
import com.syming.castlevaniacraft.networking.MessageRemoveBossInfo;
import com.syming.castlevaniacraft.util.CCReferences;

import lumien.bloodmoon.Bloodmoon;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.ilexiconn.llibrary.server.entity.multipart.PartEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EntityDracula extends CCEntityMob implements IAnimatedEntity, ICustomBossBar {

    private static final DataParameter<Integer> TARGET_ID = EntityDataManager.createKey(EntityDracula.class, DataSerializers.VARINT);
    private Animation animation = NO_ANIMATION;
    public static final Animation FIREBALLS_ANIMATION = Animation.create(80);
    public static final Animation DARK_INFERNO_ANIMATION = Animation.create(80);
    public static final Animation FIRE_PILLAR_ANIMATION = Animation.create(60);
    public static final Animation BATS_ATTACK_ANIMATION = Animation.create(55);
    public static final Animation DEATH_ANIMATION = Animation.create(200);
    private int fireballsCooldown = 0;
    private int darkInfernoCooldown = 0;
    private int firePillarCooldown = 0;
    private int batsAttackCooldown = 0;
    public static final int FIREBALLS_COOLDOWN = 100;
    public static final int DARK_INFERNO_COOLDOWN = 200;
    public static final int FIRE_PILLAR_COOLDOWN = 250;
    public static final int BATS_ATTACK_COOLDOWN = 150;
    private static final Animation[] ANIMATIONS = {FIREBALLS_ANIMATION, DARK_INFERNO_ANIMATION, FIRE_PILLAR_ANIMATION, BATS_ATTACK_ANIMATION, DEATH_ANIMATION};
    private int animationTick;
    public AnimationAI currentAnim;
    private final BossInfoServer bossInfo = (BossInfoServer) new BossInfoServer(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS).setDarkenSky(false);
    private static final int BAT_GROUP_RADIUS = 20;
    private List<EntityVampireBat> aggressiveBats = new ArrayList<>();
    public PartEntity draculaHead;
//    public PartEntity draculaBody;

    private int teleportTick;
    private boolean isTeleporting;

    public ActionState currentState = ActionState.ATTACKING;
    private int stateTick = 0;


    public EntityDracula(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 2.5F);
        this.experienceValue = 666;
        this.isImmuneToFire = true;

        this.draculaHead = new EntityDraculaHead(this, 0.0F, 0, 2.0F, 0.7F, 0.6F, 0.0F);
//        this.draculaBody = new EntityDraculaBody(this, 0.0F, 0, 0.0F, 1.0625F, 2.0F, 0.0F);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
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
        if (animation == NO_ANIMATION) {
            onAnimationFinish(this.animation);
            setAnimationTick(0);
        }
        this.animation = animation;
    }

    protected void onAnimationFinish(Animation animation) {

    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    @Override
    protected void entityInit() {
        super.entityInit();

        this.dataManager.register(TARGET_ID, -1);
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entity) {
        super.setAttackTarget(entity);
        this.dataManager.set(TARGET_ID, entity == null ? -1 : entity.getEntityId());
    }

    @Override
    @Nullable
    public EntityLivingBase getAttackTarget() {
        int id = this.dataManager.get(TARGET_ID);
        Entity entity = this.world.getEntityByID(id);
        return (entity instanceof EntityLivingBase livingBase) ? livingBase : null;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(1, new AIDie(this, DEATH_ANIMATION));
        this.tasks.addTask(2, new AIFireballsAttack(this, FIREBALLS_ANIMATION));
        this.tasks.addTask(2, new AIDarkInfernoAttack(this, DARK_INFERNO_ANIMATION));
        this.tasks.addTask(2, new AIFirePillarAttack(this, FIRE_PILLAR_ANIMATION));
        this.tasks.addTask(2, new AIBatsAttack(this, BATS_ATTACK_ANIMATION));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, true));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityVillager.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
//        this.targetTasks.addTask(1, new AIFindPlayer(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(600.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);

        CCNetworking.network.sendTo(new MessageAddBossInfo(this.bossInfo.getUniqueId(), getEntityId()), player);

        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);

        CCNetworking.network.sendTo(new MessageRemoveBossInfo(this.bossInfo.getUniqueId(), getEntityId()), player);

        this.bossInfo.removePlayer(player);
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityLivingBase target = getAttackTarget();

        if (target == null && !isEntityAlive()) return;

        this.aggressiveBats = this.world.getEntitiesWithinAABB(EntityVampireBat.class, this.getEntityBoundingBox().grow(BAT_GROUP_RADIUS));
        if (this.world.isDaytime() && !this.world.isRemote && shouldBurnInDay()) {
            float f = this.getBrightness();

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ))) {
                this.setFire(10);
            }
        }

        if (target != null) {
            getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
            if ((!isTeleporting() && !isInvisible()) &&
                    getAnimation() == NO_ANIMATION &&
                    this.fireballsCooldown <= 0 &&
                    getAnimation() != DEATH_ANIMATION &&
                    isEntityAlive() &&
                    this.rand.nextFloat() < 0.75F &&
                    this.currentState == ActionState.ATTACKING) {

                AnimationHandler.INSTANCE.sendAnimationMessage(this, FIREBALLS_ANIMATION);
                this.fireballsCooldown = FIREBALLS_COOLDOWN;

            }

            if ((!isTeleporting() && !isInvisible()) &&
                    getAnimation() == NO_ANIMATION &&
                    darkInfernoCooldown <= 0 &&
                    getAnimation() != DEATH_ANIMATION &&
                    this.isEntityAlive() &&
                    this.rand.nextFloat() < 0.50F &&
                    this.currentState == ActionState.ATTACKING) {

                AnimationHandler.INSTANCE.sendAnimationMessage(this, DARK_INFERNO_ANIMATION);
                this.darkInfernoCooldown = DARK_INFERNO_COOLDOWN;

            }

            if ((!isTeleporting() && !isInvisible()) &&
                    getAnimation() == NO_ANIMATION &&
                    this.firePillarCooldown <= 0 &&
                    getAnimation() != DEATH_ANIMATION &&
                    isEntityAlive() &&
                    this.rand.nextFloat() < 0.25F &&
                    this.currentState == ActionState.ATTACKING) {

                AnimationHandler.INSTANCE.sendAnimationMessage(this, FIRE_PILLAR_ANIMATION);
                this.firePillarCooldown = FIRE_PILLAR_COOLDOWN;

            }

            if ((!isTeleporting() && !isInvisible()) &&
                    getAnimation() == NO_ANIMATION &&
                    this.batsAttackCooldown <= 0 &&
                    getAnimation() != DEATH_ANIMATION &&
                    this.aggressiveBats.isEmpty() &&
                    this.isEntityAlive() &&
                    this.rand.nextFloat() < 0.50F &&
                    this.currentState == ActionState.ATTACKING) {

                AnimationHandler.INSTANCE.sendAnimationMessage(this, BATS_ATTACK_ANIMATION);
                this.batsAttackCooldown = BATS_ATTACK_COOLDOWN;

            }



        }

        if (getAnimation() != NO_ANIMATION) {
            this.animationTick++;
            if (this.world.isRemote && this.animationTick >= this.animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }


        if (target != null && isEntityAlive()) {
            this.stateTick++;

            switch (this.currentState) {
                case ATTACKING:

                    this.isTeleporting = false;

                    if (isAnAttackAnimationFinished()) {
                        transitionTo(ActionState.WAITING_AFTER_ATTACK);
                    }
                    break;

                case WAITING_AFTER_ATTACK:

                    this.isTeleporting = false;

                    if (this.stateTick >= 20) {
                        this.teleportTick = 0;
                        transitionTo(ActionState.TELEPORTING);
                    }
                    break;

                case TELEPORTING:
                    this.teleportTick++;

                    this.isTeleporting = true;

                    if (this.teleportTick >= 70 && this.teleportTick < 100) {
                        setInvisible(true);
                        setEntityInvulnerable(true);
                    }

                    if (this.teleportTick >= 80 && this.teleportTick < 90) {
                        teleportRandomlyNearTarget();
                    }

                    if (this.teleportTick >= 110 && this.teleportTick < 130) {
                        setInvisible(false);
                        setEntityInvulnerable(false);
                    }

                    if (isTeleportTickIsFinished()) {
                        this.teleportTick = 0;
                        transitionTo(ActionState.WAITING_AFTER_TELEPORT);
                    }
                    break;

                case WAITING_AFTER_TELEPORT:

                    this.isTeleporting = false;

                    if (this.stateTick >= 20) {
                        transitionTo(ActionState.ATTACKING);
                    }
                    break;
            }
        }

        despawnIfPeaceful();

        if (this.fireballsCooldown > 0 && getAnimation() == NO_ANIMATION && (!isTeleporting() && !isInvisible())) {
            this.fireballsCooldown--;
        }
        if (this.darkInfernoCooldown > 0 && getAnimation() == NO_ANIMATION && (!isTeleporting() && !isInvisible())) {
            this.darkInfernoCooldown--;
        }
        if (this.firePillarCooldown > 0 && getAnimation() == NO_ANIMATION && (!isTeleporting() && !isInvisible())) {
            this.firePillarCooldown--;
        }
        if (this.batsAttackCooldown > 0 && getAnimation() == NO_ANIMATION && (!isTeleporting() && !isInvisible())) {
            this.batsAttackCooldown--;
        }

    }

    /**
     * Teleports the entity to the specified location. Used for Enderman and Chorus Fruit teleportation
     */
    @Override
    public boolean attemptTeleport(double x, double y, double z) {

        double d0 = this.posX;
        double d1 = this.posY;
        double d2 = this.posZ;
        this.posX = x;
        this.posY = y;
        this.posZ = z;

        BlockPos blockpos = new BlockPos(this);
        boolean flag = false;
        World world = this.world;

        if (world.isBlockLoaded(blockpos))
        {
            boolean flag1 = false;

            while (!flag1 && blockpos.getY() > 0)
            {
                BlockPos blockpos1 = blockpos.down();
                IBlockState iblockstate = world.getBlockState(blockpos1);

                if (iblockstate.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    --this.posY;
                    blockpos = blockpos1;
                }
            }

            if (flag1)
            {
                this.setPositionAndUpdate(this.posX, this.posY, this.posZ);

                if (world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(this.getEntityBoundingBox()))
                {
                    flag = true;
                }
            }

            if (!flag)
            {
                this.setPositionAndUpdate(d0, d1, d2);
                return false;
            }
        }

        if (this instanceof EntityCreature) {
            ((EntityCreature) this).getNavigator().clearPath();
        }

        return true;
    }

    protected boolean shouldBurnInDay() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.draculaHead.onUpdate();
//        this.draculaBody.onUpdate();

//        if (this.world.isDaytime() && !this.world.isRemote) {
//            if (isEntityAlive()) {
//                this.world.setWorldTime(11999);
//            }
//        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, cause)) return;

        if (!this.dead) {
            Entity entity = cause.getTrueSource();
            EntityLivingBase entitylivingbase = this.getAttackingEntity();

            if (this.scoreValue >= 0 && entitylivingbase != null) {
                entitylivingbase.awardKillScore(this, this.scoreValue, cause);
            }

            if (entity != null) {
                entity.onKillEntity(this);
            }

            this.dead = true;
            this.getCombatTracker().reset();

            if (!this.world.isRemote) {
                int i = net.minecraftforge.common.ForgeHooks.getLootingLevel(this, entity, cause);

                captureDrops = true;
                capturedDrops.clear();

                if (this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")) {
                    boolean flag = this.recentlyHit > 0;
                    this.dropLoot(flag, i, cause);
                }

                captureDrops = false;

                if (!net.minecraftforge.common.ForgeHooks.onLivingDrops(this, cause, capturedDrops, i, recentlyHit > 0)) {
                    for (EntityItem item : capturedDrops) {
                        world.spawnEntity(item);
                    }
                }
            }

            this.world.setEntityState(this, (byte) 3);
        }
    }

    @Override
    protected void onDeathUpdate() {
        ++this.deathTime;

        if (this.deathTime == 200) {
            this.recentlyHit = 200;
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot"))) {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0) {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
                EntityDraculaSecondForm draculaSecondForm = new EntityDraculaSecondForm(this.world);

                if (this.hasCustomName()) {
                    draculaSecondForm.setCustomNameTag(this.getCustomNameTag());
                    draculaSecondForm.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
                }

                draculaSecondForm.copyLocationAndAnglesFrom(this);
                this.world.spawnEntity(draculaSecondForm);
                System.out.println("Second Form Appeared!");
            }

            this.setDead();

            for (int k = 0; k < 20; ++k) {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0, d1);
            }
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    public boolean hurtHead(PartEntity part, DamageSource source, float amount) {
        if (getHealth() <= 0) {
            AnimationHandler.INSTANCE.sendAnimationMessage(this, DEATH_ANIMATION);
        }
        if (part == getDraculaHead() || source.isExplosion()) {
            if (hasWeakness(source, amount)) {
                return super.attackEntityFrom(source, amount * amount);
            }
            if (hasStrength(source, amount)) {
                return super.attackEntityFrom(source, amount / amount);
            }
            return super.attackEntityFrom(source, amount);
        }
        return false;
    }

    public boolean shouldAttackPlayer(EntityPlayer player) {

        Vec3d vec3d = player.getLook(1.0F).normalize();
        Vec3d vec3d1 = new Vec3d(this.posX - player.posX, this.getEntityBoundingBox().minY + (double) this.getEyeHeight() - (player.posY + (double) player.getEyeHeight()), this.posZ - player.posZ);
        double d0 = vec3d1.length();
        vec3d1 = vec3d1.normalize();
        double d1 = vec3d.dotProduct(vec3d1);
        return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
    }

    public void teleportRandomlyNearTarget() {
        EntityLivingBase target = this.getAttackTarget();
        if (target != null) {
            double radius = 8.0D;

            double d0 = target.posX + (this.rand.nextDouble() - 0.5D) * radius;
            double d1 = target.posY + (double)(this.rand.nextInt(16) - 8);
            double d2 = target.posZ + (this.rand.nextDouble() - 0.5D) * radius;

            this.teleportTo(d0, d1, d2);
        }
    }

    public boolean teleportToEntity(Entity p_70816_1_) {
        Vec3d vec3d = new Vec3d(this.posX - p_70816_1_.posX, this.getEntityBoundingBox().minY + (double)(this.height / 2.0F) - p_70816_1_.posY + (double)p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
        vec3d = vec3d.normalize();
        double d0 = 16.0D;
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * 8.0D;
        double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3d.y * 8.0D;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * 8.0D;
        return this.teleportTo(d1, d2, d3);
    }

    public boolean teleportTo(double x, double y, double z) {
        EnderTeleportEvent event = new EnderTeleportEvent(this, x, y, z, 0);
        if (MinecraftForge.EVENT_BUS.post(event)) return false;
        boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());
        return flag;
    }

    @Override
    public ResourceLocation getLootTable() {
        return CCTreasures.DRACULA;
    }

    @Override
    public PartEntity[] getParts() {
        return new PartEntity[] {this.draculaHead/*, this.draculaBody*/};
    }

    public PartEntity getDraculaHead() {
        return this.draculaHead;
    }

//    public PartEntity getDraculaBody() {
//        return this.draculaBody;
//    }

    public boolean isAnAttackAnimationFinished() {
        return this.animationTick >= FIREBALLS_ANIMATION.getDuration()||
                this.animationTick >=  DARK_INFERNO_ANIMATION.getDuration() ||
                this.animationTick >=  FIRE_PILLAR_ANIMATION.getDuration() ||
                this.animationTick >=  BATS_ATTACK_ANIMATION.getDuration();
    }

    public boolean isTeleportTickIsFinished() {
        return this.teleportTick >= 130;
    }

    @Override
    public @Nullable ResourceLocation getCustomBossBar() {
        return new ResourceLocation(CCReferences.MODID, "textures/gui/dracula_boss_bar.png");
    }

    public boolean isTeleporting() {
        return this.isTeleporting;
    }

    public int getTeleportTick() {
        return this.teleportTick;
    }

    private void transitionTo(ActionState newState) {
        this.currentState = newState;
        this.stateTick = 0;
    }

    public enum ActionState {
        ATTACKING,
        WAITING_AFTER_ATTACK,
        TELEPORTING,
        WAITING_AFTER_TELEPORT
    }
}
