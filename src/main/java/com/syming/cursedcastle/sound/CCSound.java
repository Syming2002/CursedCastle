package com.syming.cursedcastle.sound;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCSound {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CursedCastleMod.MOD_ID);


    public static final RegistryObject<SoundEvent> DAGGER_THROW = registerSoundEvents("dagger_throw");
    public static final RegistryObject<SoundEvent> HOLY_CROSS_THROW = registerSoundEvents("holy_cross_throw");
    public static final RegistryObject<SoundEvent> HOLY_WATER_IMPACT = registerSoundEvents("holy_water_impact");
    public static final RegistryObject<SoundEvent> THROWING_AXE_THROW = registerSoundEvents("throwing_axe_throw");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CursedCastleMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
