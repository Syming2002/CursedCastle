package com.syming.cursedcastle.world.entity.magic;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FirePillarEntity extends AreaEffectCloud {
    private double radius = 1.0;
    private float rotationAngle = 0;

    public FirePillarEntity(EntityType<? extends AreaEffectCloud> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FirePillarEntity(Level pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);

        setRadius(1.0F);
        setDuration(100);
        setParticle(ParticleTypes.FLAME);
        setDeltaMovement(0.0D, 0.75D, 0.0D);
        setNoGravity(true);
    }
    @Override
    public void tick() {
        super.tick();

        if (this.tickCount < 100) {
            applyFireDamage();
        }

        if (this.level().isClientSide()) {
            spawnFlamesParticles();
        }

        if (this.tickCount > 100) {
            discard();
        }
    }

    private void spawnFlamesParticles() {
        double xCenter = getX();
        double yCenter = getY();
        double zCenter = getZ();

        this.rotationAngle += 5;
        if (this.rotationAngle >= 360) {
            this.rotationAngle -= 360;
        }

        int particles = 10;
        for (int i = 0; i < particles; i++) {
            double angleRad = Math.toRadians(this.rotationAngle + (360.0F / particles) * i);

            double particleX = xCenter + this.radius * Math.cos(angleRad);
            double particleY = yCenter + (i * 0.05D);
            double particleZ = zCenter + this.radius * Math.sin(angleRad);


            this.level().addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ, 0.0D, 0.75D, 0.0D);
        }
    }

    private void applyFireDamage() {
        float damage = 5.0F;

        for (Entity entity : this.level().getEntities(this, getBoundingBox())) {
            if (entity instanceof LivingEntity living) {
                living.setSecondsOnFire(10);
            }
        }


    }


}
