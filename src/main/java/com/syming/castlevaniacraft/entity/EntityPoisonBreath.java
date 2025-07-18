package com.syming.castlevaniacraft.entity;

import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.particle.ParticleHoly;
import com.syming.castlevaniacraft.particle.ParticlePoisonBreath;
import com.syming.castlevaniacraft.entity.mobs.EntityGorgon;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityPoisonBreath extends EntityAttackMagic {

    private final int RANGE = 5;

    public EntityPoisonBreath(World world) {
        super(world);
        setSize(0, 0);
    }

    public EntityPoisonBreath(World world, EntityLivingBase caster) {
        super(world);
        setSize(0, 0);

        if (!world.isRemote) {
            this.setCasterID(caster.getEntityId());
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.caster != null && this.caster.isDead) {
            this.setDead();
        }

        if (this.caster != null) {
            if (this.caster instanceof EntityPoisonShade poisonShade) {
                Vec3d headPos = new Vec3d(0, 1.5,0);
                headPos = headPos.rotateYaw((float) Math.toRadians((-poisonShade.rotationYaw - 90)));
                headPos = headPos.add(poisonShade.getPositionVector());
                headPos = headPos.add(new Vec3d(0, 0, 0.25)
                        .rotatePitch((float) Math.toRadians(-poisonShade.rotationPitch))
                        .rotateYaw((float) Math.toRadians(-poisonShade.rotationYawHead)));
                setPositionAndRotation(headPos.x, headPos.y, headPos.z, poisonShade.rotationYawHead, poisonShade.rotationPitch);
                spawnPoisonBreathParticles();
            } else if (this.caster instanceof EntityGorgon gorgon) {
                Vec3d headPos = new Vec3d(0, 1.015625,0);
                headPos = headPos.rotateYaw((float) Math.toRadians((-gorgon.rotationYaw - 90)));
                headPos = headPos.add(gorgon.getPositionVector());
                headPos = headPos.add(new Vec3d(0, 0, 1.5)
                        .rotatePitch((float) Math.toRadians(-gorgon.rotationPitch))
                        .rotateYaw((float) Math.toRadians(-gorgon.rotationYawHead)));
                setPositionAndRotation(headPos.x, headPos.y, headPos.z, gorgon.rotationYawHead, gorgon.rotationPitch);
                spawnPoisonBreathParticles();
            }
        }


        if (ticksExisted > 0) {
            hitEntities();
        }
        if (ticksExisted > 50) {
            setDead();
        }
    }

    public void hitEntities() {
        if (caster != null) {
            List<EntityLivingBase> entitiesHit = getEntityLivingBaseNearby(RANGE, caster.getEyeHeight(), RANGE, 5);
            float damage = 0.5F;
            for (EntityLivingBase entityHit : entitiesHit) {
                if (entityHit != caster) {
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
                            entityHit.attackEntityFrom(DamageSource.MAGIC, damage);
                            entityHit.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
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
    public void spawnPoisonBreathParticles() {
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
                ParticlePoisonBreath poisonBreathParticle = new ParticlePoisonBreath(this.world, this.posX, this.posY, this.posZ, xSpeed, ySpeed, zSpeed);
                Minecraft.getMinecraft().effectRenderer.addEffect(poisonBreathParticle);
            }
        }
    }
}
