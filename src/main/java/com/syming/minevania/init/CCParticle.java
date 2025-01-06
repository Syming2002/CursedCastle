package com.syming.minevania.init;

import com.syming.minevania.client.particles.ParticleHoly;
import com.syming.minevania.client.particles.ParticlePoisonBreath;
import com.syming.minevania.client.particles.ParticleStoneBreath;

public class CCParticle {
    public static final ParticleHoly.TextureDefinition HOLY = new ParticleHoly.TextureDefinition("holy_particle");
    public static final ParticleStoneBreath.TextureDefinition STONE_BREATH = new ParticleStoneBreath.TextureDefinition("stone_breath_particle", true, 4);
    public static final ParticlePoisonBreath.TextureDefinition POISON_BREATH = new ParticlePoisonBreath.TextureDefinition("poison_breath_particle", true, 4);

}
