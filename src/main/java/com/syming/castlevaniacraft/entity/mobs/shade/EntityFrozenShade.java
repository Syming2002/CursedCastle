package com.syming.castlevaniacraft.entity.mobs.shade;

import com.syming.castlevaniacraft.api.IElementalDamage;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.entity.projectiles.EntityIceBladeProjectile;
import com.syming.castlevaniacraft.loot.CCTreasures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.List;


public class EntityFrozenShade extends EntityShade implements IRangedAttackMob {
    public EntityIceBladeProjectile iceBladeUp;

    private static final DataParameter<Integer> FIRST_ICE_BLADE_TARGET = EntityDataManager.<Integer>createKey(EntityFrozenShade.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> SECOND_ICE_BLADE_TARGET = EntityDataManager.<Integer>createKey(EntityFrozenShade.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> THIRD_ICE_BLADE_TARGET = EntityDataManager.<Integer>createKey(EntityFrozenShade.class, DataSerializers.VARINT);
    private static final DataParameter<Integer>[] ICE_BLADE_TARGETS = new DataParameter[] {FIRST_ICE_BLADE_TARGET, SECOND_ICE_BLADE_TARGET, THIRD_ICE_BLADE_TARGET};
    private final float[] xRotationIceBlades= new float[2];
    private final float[] yRotationIceBlades = new float[2];
    private final float[] xRotOIceBlades = new float[2];
    private final float[] yRotOIceBlades = new float[2];
    private final int[] nextIceBladeUpdate = new int[2];
    private final int[] idleIceBladeUpdates = new int[2];


    public EntityFrozenShade(World worldIn) {
        super(worldIn);
        this.setSize(1.33F, 2.0F);
        this.experienceValue = 4;
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getFireDamage();
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        EntityLivingBase living = getAttackingEntity();
        if (living != null) {
            ItemStack stack = living.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    return source == CCDamageSource.getIceDamage();
                }
            }
        }
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FIRST_ICE_BLADE_TARGET, Integer.valueOf(0));
        this.dataManager.register(SECOND_ICE_BLADE_TARGET, Integer.valueOf(0));
        this.dataManager.register(THIRD_ICE_BLADE_TARGET, Integer.valueOf(0));
    }


    @Override
    protected void initEntityAI() {
        super.initEntityAI();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        for (int i = 1; i < 3; ++i) {
            if (this.ticksExisted >= this.nextIceBladeUpdate[i - 1]) {
                this.nextIceBladeUpdate[i - 1] = this.ticksExisted + 10 + this.rand.nextInt(10);

                if (this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) {
                    int j3 = i - 1;
                    int k3 = this.idleIceBladeUpdates[i - 1];
                    this.idleIceBladeUpdates[j3] = this.idleIceBladeUpdates[i - 1] + 1;

                    if (k3 > 15) {
                        float f = 10.0F;
                        float f1 = 5.0F;
                        double d0 = MathHelper.nextDouble(this.rand, this.posX - 10.0D, this.posX + 10.0D);
                        double d1 = MathHelper.nextDouble(this.rand, this.posY - 5.0D, this.posY + 5.0D);
                        double d2 = MathHelper.nextDouble(this.rand, this.posZ - 10.0D, this.posZ + 10.0D);
                        this.launchIceBladeToCoords(i + 1, d0, d1, d2);
                        this.idleIceBladeUpdates[i - 1] = 0;
                    }
                }

                int k1 = this.getWatchedTargetId(i);

                if (k1 > 0) {
                    EntityLivingBase livingBase = (EntityLivingBase) this.world.getEntityByID(k1);

                    if (livingBase != null && livingBase.isEntityAlive() && this.getDistanceSq(livingBase) <= 900.0D && this.canEntityBeSeen(livingBase)) {
                        if (livingBase instanceof EntityPlayer && ((EntityPlayer) livingBase).capabilities.disableDamage) {
                            this.updateWatchedTargetId(i, 0);
                        } else {
//                            this.launchIceBladeToEntity(i + 1, livingBase);
                            this.nextIceBladeUpdate[i - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
                            this.idleIceBladeUpdates[i - 1] = 0;
                        }
                    } else {
                        this.updateWatchedTargetId(i, 0);
                    }
                } else {
                    List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(20.0D, 8.0D, 20.0D));

                    for (int j2 = 0; j2 < 10 && !list.isEmpty(); ++j2) {
                        EntityLivingBase entitylivingbase = list.get(this.rand.nextInt(list.size()));

                        if (entitylivingbase != this && entitylivingbase.isEntityAlive() && this.canEntityBeSeen(entitylivingbase)) {
                            if (entitylivingbase instanceof EntityPlayer) {
                                if (!((EntityPlayer) entitylivingbase).capabilities.disableDamage) {
                                    this.updateWatchedTargetId(i, entitylivingbase.getEntityId());
                                }
                            } else {
                                this.updateWatchedTargetId(i, entitylivingbase.getEntityId());
                            }

                            break;
                        }

                        list.remove(entitylivingbase);
                    }
                }
            }
        }

        if (this.getAttackTarget() != null) {
            this.updateWatchedTargetId(0, this.getAttackTarget().getEntityId());
        } else {
            this.updateWatchedTargetId(0, 0);
        }
    }

    /**
     * Returns the target entity ID if present, or -1 if not @param par1 The target offset, should be from 0-2
     */
    public int getWatchedTargetId(int iceBlade)
    {
        return ((Integer)this.dataManager.get(ICE_BLADE_TARGETS[iceBlade])).intValue();
    }

    /**
     * Updates the target entity ID
     */
    public void updateWatchedTargetId(int targetOffset, int newId)
    {
        this.dataManager.set(ICE_BLADE_TARGETS[targetOffset], Integer.valueOf(newId));
    }


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.world.isRaining() && this.world.getBiome(new BlockPos(this)).getEnableSnow()) {
            onShadeHeal(this);
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        this.launchIceBladeToEntity(0, target);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }

    private double getIceBladeX(int p_82214_1_)
    {
        if (p_82214_1_ <= 0)
        {
            return this.posX;
        }
        else
        {
            float f = (this.renderYawOffset + (float)(180 * (p_82214_1_ - 1))) * 0.017453292F;
            float f1 = MathHelper.cos(f);
            return this.posX + (double)f1 * 0.75D;
        }
    }

    private double getIceBladeY(int p_82208_1_)
    {
        return p_82208_1_ <= 0 ? this.posY + this.getEyeHeight() : this.posY + 2.2D;
    }

    private double getIceBladeZ(int p_82213_1_)
    {
        if (p_82213_1_ <= 0)
        {
            return this.posZ;
        }
        else
        {
            float f = (this.renderYawOffset + (float)(180 * (p_82213_1_ - 1))) * 0.017453292F;
            float f1 = MathHelper.sin(f);
            return this.posZ + (double)f1 * 0.75D;
        }
    }

        private float rotlerp(float p_82204_1_, float p_82204_2_, float p_82204_3_)
        {
            float f = MathHelper.wrapDegrees(p_82204_2_ - p_82204_1_);

            if (f > p_82204_3_)
            {
                f = p_82204_3_;
            }

            if (f < -p_82204_3_)
            {
                f = -p_82204_3_;
            }

            return p_82204_1_ + f;
        }

    private void launchIceBladeToEntity(int p_82216_1_, EntityLivingBase target) {
        this.launchIceBladeToCoords(p_82216_1_, target.posX, target.posY + (double)target.height / 2, target.posZ);
    }

    /**
     * Launches an Ice Blade toward (par2, par4, par6)
     */
    private void launchIceBladeToCoords(int p_82209_1_, double x, double y, double z) {
        double d0 = this.getIceBladeX(p_82209_1_);
        double d1 = this.getIceBladeY(p_82209_1_);
        double d2 = this.getIceBladeZ(p_82209_1_);
        double d3 = x - d0;
        double d4 = y - d1;
        double d5 = z - d2;
//        EntityIceBladeProjectile iceBladeLeft = new EntityIceBladeProjectile(world, this, d3, d4, d5);
//
//        EntityIceBladeProjectile iceBladeRight = new EntityIceBladeProjectile(world, this, d3, d4, d5);

         this.iceBladeUp = new EntityIceBladeProjectile(world, this, d3, d4, d5);
         this.iceBladeUp.setShooterID(this.getEntityId());
//        iceBladeLeft.posY = d1;
//        iceBladeLeft.posX = d0;
//        iceBladeLeft.posZ = d2;
//
//        iceBladeRight.posY = d1;
//        iceBladeRight.posX = d0;
//        iceBladeRight.posZ = d2;

        this.iceBladeUp.posY = d1;
        this.iceBladeUp.posX = d0;
        this.iceBladeUp.posZ = d2;
        if (!this.world.isRemote) {
//            this.world.spawnEntity(iceBladeLeft);
//            this.world.spawnEntity(iceBladeRight);
            this.world.spawnEntity(this.iceBladeUp);
            System.out.println("Ice Blades is Launching");
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (source != DamageSource.DROWN && !(source.getTrueSource() instanceof EntityIceBladeProjectile))
        {
            Entity entity1 = source.getTrueSource();

            if (entity1 != null && !(entity1 instanceof EntityPlayer) && entity1 instanceof EntityLivingBase && ((EntityLivingBase) entity1).getCreatureAttribute() == this.getCreatureAttribute()) {
                return false;
            } else {
                for (int i = 0; i < this.idleIceBladeUpdates.length; ++i) {
                    this.idleIceBladeUpdates[i] += 3;
                }

                return super.attackEntityFrom(source, amount);
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public ResourceLocation getLootTable() {
        return CCTreasures.FROZEN_SHADE;
    }
}
