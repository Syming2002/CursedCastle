package com.syming.minevania.util.handlers;

import com.syming.minevania.util.CCReferences;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {

    public static SoundEvent DAGGER_THROW;
    public static SoundEvent HOLY_CROSS_THROW;
    public static SoundEvent HOLY_WATER_IMPACT;
    public static SoundEvent WHIP_IMPACT;
    public static SoundEvent THROWING_AXE_THROW;

    public static void registerSounds() {

        DAGGER_THROW = registerSound("dagger_throw");
        HOLY_CROSS_THROW = registerSound("holy_cross_throw");
        HOLY_WATER_IMPACT = registerSound("holy_water_impact");
        WHIP_IMPACT = registerSound("whip_impact");
        THROWING_AXE_THROW = registerSound("throwing_axe_throw");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(CCReferences.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

    public static SoundEvent getSound(String name) {
        return SoundEvent.REGISTRY.getObject(new ResourceLocation(CCReferences.MODID, name));
    }

}
