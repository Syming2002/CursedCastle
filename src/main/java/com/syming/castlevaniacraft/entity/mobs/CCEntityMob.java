package com.syming.castlevaniacraft.entity.mobs;

import com.syming.castlevaniacraft.client.particles.ParticleHoly;
import com.syming.castlevaniacraft.init.CCParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class CCEntityMob extends EntityMob {

    public float targetDistance;

    public CCEntityMob(World worldIn) {
        super(worldIn);
    }

    public abstract boolean hasWeakness(DamageSource source, float damageAmount);

    public abstract boolean hasStrength(DamageSource source, float damageAmount);

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (getAttackTarget() != null) {
            targetDistance = getDistance(getAttackTarget()) - getAttackTarget().width / 2F;
        }
    }

    @SideOnly(Side.CLIENT)
    public void spawnDamageVanillaParticles(EnumParticleTypes particleTypes) {
        if (this.world.isRemote) {
            for (int i = 0; i < 5; i++) {
                this.world.spawnParticle(particleTypes, this.posX + rand.nextFloat() * 6.0F / 16.0F, this.posY + rand.nextFloat() * 6.0F / 16.0F, this.posZ + rand.nextFloat() * 6.0F / 16.0F, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void spawnDamageHolyParticles() {
        if (this.world.isRemote) {
            for (int i = 0; i < 5; i++) {
                Particle holyParticle = new ParticleHoly(CCParticle.HOLY, world, this.posX + rand.nextFloat() * 6.0F / 16.0F, this.posY + rand.nextFloat() * 6.0F / 16.0F, this.posZ + rand.nextFloat() * 6.0F / 16.0F, 0.0D, 0.0D, 0.0D)
                        .setCanCollide(false)
                        .setScale(1.5F);
                Minecraft.getMinecraft().effectRenderer.addEffect(holyParticle);
            }
        }
    }
}
