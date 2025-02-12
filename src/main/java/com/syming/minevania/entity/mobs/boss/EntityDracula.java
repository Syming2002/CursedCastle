package com.syming.minevania.entity.mobs.boss;

import com.syming.minevania.api.ICustomBossBar;
import com.syming.minevania.entity.ai.dracula.*;
import com.syming.minevania.entity.mobs.CCEntityMob;
import com.syming.minevania.entity.mobs.EntityAggressiveBat;
import com.syming.minevania.item.whips.vampirekiller.ItemVampireKillerWhip;
import com.syming.minevania.loot.CCTreasures;
import com.syming.minevania.networking.MVNetworking;
import com.syming.minevania.networking.MessageAddBossInfo;
import com.syming.minevania.networking.MessageRemoveBossInfo;
import com.syming.minevania.util.MVReferences;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.ilexiconn.llibrary.server.entity.multipart.PartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EntityDracula extends CCEntityMob implements IAnimatedEntity, ICustomBossBar {

    private Animation animation = NO_ANIMATION;
    public static final Animation IDLE_ANIMATION = Animation.create(40);
    public static final Animation FIREBALLS_ANIMATION = Animation.create(80);
    public static final Animation DARK_INFERNO_ANIMATION = Animation.create(80);
    public static final Animation FIRE_PILLAR_ANIMATION = Animation.create(60);
    public static final Animation BATS_ATTACK_ANIMATION = Animation.create(35);
    public static final Animation DEATH_ANIMATION = Animation.create(200);
    private int fireballsCooldown = 0;
    private int darkInfernoCooldown = 0;
    private int firePillarCooldown = 0;
    private int batsAttackCooldown = 0;
    public static final int FIREBALLS_COOLDOWN = 100;
    public static final int DARK_INFERNO_COOLDOWN = 200;
    public static final int FIRE_PILLAR_COOLDOWN = 250;
    public static final int BATS_ATTACK_COOLDOWN = 150;
    private static final Animation[] ANIMATIONS = {IDLE_ANIMATION, FIREBALLS_ANIMATION, DARK_INFERNO_ANIMATION, FIRE_PILLAR_ANIMATION, BATS_ATTACK_ANIMATION, DEATH_ANIMATION};
    private int animationTick;
    public AnimationAI currentAnim;
    private final BossInfoServer bossInfo = (BossInfoServer) new BossInfoServer(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS).setDarkenSky(false);
    private static final int BAT_GROUP_RADIUS = 20;
    private List<EntityAggressiveBat> aggressiveBats = new ArrayList<>();
    public PartEntity draculaHead;
    public PartEntity draculaBody;
    private Item lastHitItem;


    public EntityDracula(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 2.5F);
        this.experienceValue = 200;
        this.draculaHead = new EntityDraculaHead(this, 0.0F, 0, 2.0F, 1.0625F, 0.5F, 0.0F);
        this.draculaBody = new EntityDraculaBody(this, 0.0F, 0, 0.0F, 1.0625F, 2.0F, 0.0F);
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
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(0, new AIIdle(this, IDLE_ANIMATION));
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
        this.targetTasks.addTask(1, new AIFindPlayer(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);

    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);

        MVNetworking.network.sendTo(new MessageAddBossInfo(this.bossInfo.getUniqueId(), getEntityId()), player);

        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);

        MVNetworking.network.sendTo(new MessageRemoveBossInfo(this.bossInfo.getUniqueId(), getEntityId()), player);

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

    private void despawnIfPeaceful() {
        if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            setDead();
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        aggressiveBats = this.world.getEntitiesWithinAABB(EntityAggressiveBat.class, this.getEntityBoundingBox().grow(BAT_GROUP_RADIUS));
        if (this.world.isDaytime() && !this.world.isRemote && this.shouldBurnInDay()) {
            float f = this.getBrightness();

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ))) {
                this.setFire(10);
            }
        }

        if (getAttackTarget() != null) {

            if (getAnimation() == NO_ANIMATION && fireballsCooldown <= 0 && getAnimation() != DEATH_ANIMATION && this.isEntityAlive() && this.rand.nextFloat() < 0.75F) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, FIREBALLS_ANIMATION);
                this.fireballsCooldown = FIREBALLS_COOLDOWN;
            }

            if (getAnimation() == NO_ANIMATION && darkInfernoCooldown <= 0 && getAnimation() != DEATH_ANIMATION && this.isEntityAlive() && this.rand.nextFloat() < 0.50F) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, DARK_INFERNO_ANIMATION);
                this.darkInfernoCooldown = DARK_INFERNO_COOLDOWN;
            }

            if (getAnimation() == NO_ANIMATION && firePillarCooldown <= 0 && getAnimation() != DEATH_ANIMATION && this.isEntityAlive() && this.rand.nextFloat() < 0.25F) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, FIRE_PILLAR_ANIMATION);
                this.firePillarCooldown = FIRE_PILLAR_COOLDOWN;
            }

            if (getAnimation() == NO_ANIMATION && batsAttackCooldown <= 0 && getAnimation() != DEATH_ANIMATION  && aggressiveBats.isEmpty() && this.isEntityAlive() && this.rand.nextFloat() < 0.50F) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, BATS_ATTACK_ANIMATION);
                this.batsAttackCooldown = BATS_ATTACK_COOLDOWN;
            }

        } else if (!world.isRemote) {
            if (getAnimation() == NO_ANIMATION && getAnimation() != DEATH_ANIMATION && this.isEntityAlive()) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, IDLE_ANIMATION);
            }
        }


        if (getAnimation() != NO_ANIMATION) {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) {
                setAnimation(NO_ANIMATION);
            }
        }

        if (animationTick == FIREBALLS_ANIMATION.getDuration() && this.isEntityAlive()) {
            teleportRandomlyNearTarget();
        }
        if (animationTick == DARK_INFERNO_ANIMATION.getDuration() && this.isEntityAlive()) {
            teleportRandomlyNearTarget();
        }
        if (animationTick == FIRE_PILLAR_ANIMATION.getDuration() && this.isEntityAlive()) {
            teleportRandomlyNearTarget();
        }
        despawnIfPeaceful();

        if (fireballsCooldown > 0) {
            fireballsCooldown--;
        }
        if (darkInfernoCooldown > 0) {
            darkInfernoCooldown--;
        }
        if (firePillarCooldown > 0) {
            firePillarCooldown--;
        }
        if (batsAttackCooldown > 0) {
            batsAttackCooldown--;
        }

    }

    protected boolean shouldBurnInDay() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.draculaHead.onUpdate();
        this.draculaBody.onUpdate();

        if (this.world.isDaytime() && !this.world.isRemote) {
            if (isEntityAlive()) {
                world.setWorldTime(18000);
            }
        }
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
        if (source.getTrueSource() instanceof EntityPlayer attackingPlayer) {

            ItemStack heldItem = attackingPlayer.getHeldItemMainhand();

            if (!heldItem.isEmpty()) {
                this.lastHitItem = heldItem.getItem();
            } else {
                this.lastHitItem = null;
            }
        }

        PartEntity[] parts = this.getParts();
        if (parts != null) {
            for (PartEntity part : parts) {
                if (this.lastHitItem != null) {
                    if (part == getDraculaHead()) {
                        if (this.lastHitItem instanceof ItemVampireKillerWhip) {
                            return !isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
                        }
                        if (hasWeakness(source, amount)) {
                            return !isEntityInvulnerable(source) && super.attackEntityFrom(source, amount * amount);
                        }
                        if (hasStrength(source, amount)) {
                            return !isEntityInvulnerable(source) && super.attackEntityFrom(source, amount / amount);
                        }
                    }

                    if (part == getDraculaBody()) {
                        return false;
                    }
                }
            }
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
            double radius = 8.0D; // Adjust the radius if needed

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

        if (flag) {
            this.world.playSound((EntityPlayer) null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
            this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
        }

        return flag;
    }

    @Override
    public ResourceLocation getLootTable() {
        return CCTreasures.DRACULA;
    }

    @Override
    public PartEntity[] getParts() {
        return new PartEntity[] {this.draculaHead, this.draculaBody};
    }

    public PartEntity getDraculaHead() {
        return this.draculaHead;
    }

    public PartEntity getDraculaBody() {
        return this.draculaBody;
    }

    @Override
    public @Nullable ResourceLocation getCustomBossBar() {
        return new ResourceLocation(MVReferences.MODID, "textures/gui/dracula_boss_bar.png");
    }
}
