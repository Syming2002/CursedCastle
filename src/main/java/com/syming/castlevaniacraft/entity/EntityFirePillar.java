package com.syming.castlevaniacraft.entity;

import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityFirePillar extends EntityAreaEffectCloud {

    private float rotationAngle = 0;
    private double radius = 1.0;

    public EntityFirePillar(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.setRadius(1.0F);
        this.setDuration(100);
        this.setParticle(EnumParticleTypes.FLAME);
    }

    public EntityFirePillar(World world) {
        super(world);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted < 100) {
            applyFireDamage();
        }

        if (world.isRemote) {
            spawningFlamesParticles();
        }

        if (ticksExisted > 100) {
            setDead();
        }
    }

    private void applyFireDamage() {
        float damage = 5.0F;
        for (Entity entity : this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox())) {
            if (entity instanceof EntityLivingBase entityLivingBase) {
                entityLivingBase.setFire(10);
                if (entity instanceof CCEntityMob ccEntityMob) {
                    ccEntityMob.hasWeakness(CCDamageSource.getFireDamage(), damage * damage);
                }
            }
        }
    }

    private void spawningFlamesParticles() {
        double xCenter = this.posX;
        double yCenter = this.posY;
        double zCenter = this.posZ;

        // Update rotation angle
        rotationAngle += 5; // Adjust the rotation speed as needed
        if (rotationAngle >= 360) {
            rotationAngle -= 360;
        }

        int numberOfParticles = 10; // Number of particles to spawn in the circle
        for (int i = 0; i < numberOfParticles; i++) {
            double angleRad = Math.toRadians(rotationAngle + (360.0 / numberOfParticles) * i);

            // Calculate the particle position in a circle
            double particleX = xCenter + radius * Math.cos(angleRad);
            double particleZ = zCenter + radius * Math.sin(angleRad);
            double particleY = yCenter; // Start at the same Y level

            double motionX = 0;
            double motionY = 0.1; // Rising effect
            double motionZ = 0;

            // Spawn the particle
            this.world.spawnParticle(EnumParticleTypes.FLAME, particleX, particleY, particleZ, motionX, motionY, motionZ);
        }
    }
}
