package com.syming.cursedcastle.sound;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CCSound {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CursedCastleMod.MOD_ID);


    public static final Supplier<SoundEvent> DAGGER_THROW = registerSoundEvents("dagger_throw");
    public static final Supplier<SoundEvent> HOLY_CROSS_THROW = registerSoundEvents("holy_cross_throw");
    public static final Supplier<SoundEvent> HOLY_WATER_IMPACT = registerSoundEvents("holy_water_impact");
    public static final Supplier<SoundEvent> THROWING_AXE_THROW = registerSoundEvents("throwing_axe_throw");


    private static Supplier<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
