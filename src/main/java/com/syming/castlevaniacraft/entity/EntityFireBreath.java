package com.syming.castlevaniacraft.entity;

import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;
import com.syming.castlevaniacraft.entity.mobs.EntityHeatShade;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityFireBreath extends EntityAttackMagic {

    private final int RANGE = 10;
    public EntityHeatShade heatShade;

    public EntityFireBreath(World world) {
        super(world);
        setSize(0, 0);
    }

    public EntityFireBreath(World world, EntityLivingBase caster) {
        super(world);
        setSize(0, 0);
        if (!world.isRemote) {
            this.setCasterID(caster.getEntityId());
        }
        this.heatShade = (EntityHeatShade) caster;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.caster != null && this.caster.isDead) {
            this.setDead();
        }

        if (this.caster != null) {
            Vec3d headPos = new Vec3d(0, 1.5,0);
            headPos = headPos.rotateYaw((float) Math.toRadians((-caster.rotationYaw - 90)));
            headPos = headPos.add(caster.getPositionVector());
            headPos = headPos.add(new Vec3d(0, 0, 0.25).rotatePitch((float) Math.toRadians(-caster.rotationPitch)).rotateYaw((float) Math.toRadians(-caster.rotationYawHead)));
            this.setPositionAndRotation(headPos.x, headPos.y, headPos.z, this.caster.rotationYawHead, this.caster.rotationPitch);
            spawnFireParticles();
        }

        if (ticksExisted > 10) {
            hitEntities();
        }
        if (ticksExisted > 65) {
            setDead();
        }
    }

    public void hitEntities() {
        if (this.caster != null) {
            List<EntityLivingBase> entitiesHit = getEntityLivingBaseNearby(RANGE, this.caster.getEyeHeight(), RANGE, 6);
            float damage = 2.5F;
            for (EntityLivingBase entityHit : entitiesHit) {
                if (entityHit != this.caster) {
                    float entityHitYaw = (float) ((Math.atan2(entityHit.posZ - posZ, entityHit.posX - posX) * (180 / Math.PI) - 90) % 360);
                    float entityAttackingYaw = rotationYaw % 360;
                    if (entityHitYaw < 0) {
                        entityHitYaw += 360;
                    }
                    if (entityAttackingYaw < 0) {
                        entityAttackingYaw += 360;
                    }
                    float entityRelativeYaw = entityHitYaw - entityAttackingYaw;

                    boolean yawCheck = entityRelativeYaw >= -45 / 2f && entityRelativeYaw <= 45 / 2f;

                    if (yawCheck) {
                        float entityHitDistance = (float) Math.sqrt((entityHit.posZ - posZ) * (entityHit.posZ - posZ) + (entityHit.posX - posX) * (entityHit.posX - posX) + (entityHit.posY - posY) * (entityHit.posY - posY));

                        boolean inRange = entityHitDistance <= RANGE;

                        if (inRange) {
                            entityHit.attackEntityFrom(CCDamageSource.getFireDamage(), damage);
                            entityHit.setFire(10);

                            if (entityHit instanceof CCEntityMob ccEntityMob) {
                                ccEntityMob.hasWeakness(CCDamageSource.getFireDamage(), damage * damage);
                            }
                        }
                    }
                }
            }
        }
    }

    public List<EntityLivingBase> getEntityLivingBaseNearby(double distanceX, double distanceY, double distanceZ, double radius) {
        return getEntitiesNearby(EntityLivingBase.class, distanceX, distanceY, distanceZ, radius);
    }

    public <T extends Entity> List<T> getEntitiesNearby(Class<T> entityClass, double distanceX, double distanceY, double distanceZ, double radius) {
        return world.getEntitiesWithinAABB(entityClass, getEntityBoundingBox().grow(distanceX, distanceY, distanceZ), entity -> entity != this && getDistance(entity) <= radius + entity.width / 2F && entity.posY <= posY + distanceY);
    }

    @SideOnly(Side.CLIENT)
    public void spawnFireParticles() {
        float yaw = (float) Math.toRadians(-rotationYaw);
        float pitch = (float) Math.toRadians(-rotationPitch);
        float spread = 0.25f;
        float speed = 0.56f;
        float xComp = (float) (Math.sin(yaw) * Math.cos(pitch));
        float yComp = (float) (Math.sin(pitch));
        float zComp = (float) (Math.cos(yaw) * Math.cos(pitch));
        if (this.world.isRemote) {
            for (int i = 0; i < 5; i++) {
                double xSpeed = speed * xComp + (spread * 0.7 * (rand.nextFloat() * 2 - 1) * (Math.sqrt(1 - xComp * xComp)));
                double ySpeed = speed * yComp + (spread * 0.7 * (rand.nextFloat() * 2 - 1) * (Math.sqrt(1 - yComp * yComp)));
                double zSpeed = speed * zComp + (spread * 0.7 * (rand.nextFloat() * 2 - 1) * (Math.sqrt(1 - zComp * zComp)));
                this.world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, xSpeed, ySpeed, zSpeed);
            }
        }
    }
}
