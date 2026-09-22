package com.syming.cursedcastle.potion;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.effect.CCEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CCPotion {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, CursedCastleMod.MOD_ID);

    public static final Holder<Potion> FREEZE = POTIONS.register("freeze", () -> new Potion(new MobEffectInstance(CCEffect.FREEZE, 100)));
    public static final Holder<Potion> LONG_FREEZE = POTIONS.register("long_freeze", () -> new Potion(new MobEffectInstance(CCEffect.FREEZE, 200)));

    public static final Holder<Potion> HOLY = POTIONS.register("holy", () -> new Potion(new MobEffectInstance(CCEffect.HOLY, 100)));
    public static final Holder<Potion> LONG_HOLY = POTIONS.register("long_holy", () -> new Potion(new MobEffectInstance(CCEffect.HOLY, 200)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
