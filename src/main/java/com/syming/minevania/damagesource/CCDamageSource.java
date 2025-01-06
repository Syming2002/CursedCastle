package com.syming.minevania.damagesource;

import net.minecraft.util.DamageSource;

public class CCDamageSource {

    private static final DamageSource FIRE = new DamageSource("fire").setFireDamage();
    private static final DamageSource ICE = new DamageSource("ice");
    private static final DamageSource THUNDER = new DamageSource("thunder");
    private static final DamageSource WATER = new DamageSource("water");
    private static final DamageSource HOLY = new DamageSource("holy");
    private static final DamageSource DARK = new DamageSource("dark");
    private static final DamageSource WIND = new DamageSource("wind");
    private static final DamageSource EARTH = new DamageSource("earth");

    public static DamageSource getFireDamage() {
        return FIRE;
    }

    public static DamageSource getWaterDamage() {
        return WATER;
    }

    public static DamageSource getIceDamage() {
        return ICE;
    }

    public static DamageSource getThunderDamage() {
        return THUNDER;
    }

    public static DamageSource getHolyDamage() {
        return HOLY;
    }

    public static DamageSource getDarkDamage() {
        return DARK;
    }

    public static DamageSource getWindDamage() {
        return WIND;
    }

    public static DamageSource getEarthDamage() {
        return EARTH;
    }
}
