package com.syming.cursedcastle.effect;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CCEffect {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, CursedCastleMod.MOD_ID);

    public static final Holder<MobEffect> FREEZE = EFFECTS.register("freeze", FreezeEffect::new);
    public static final Holder<MobEffect> HOLY = EFFECTS.register("holy", HolyEffect::new);


    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
