package com.syming.cursedcastle.effect;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCEffect {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<MobEffect> FREEZE = EFFECTS.register("freeze", FreezeEffect::new);
    public static final RegistryObject<MobEffect> HOLY = EFFECTS.register("holy", HolyEffect::new);


    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
