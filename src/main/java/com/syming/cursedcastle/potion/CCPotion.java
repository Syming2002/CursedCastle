package com.syming.cursedcastle.potion;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.effect.CCEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCPotion {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<Potion> FREEZE = POTIONS.register("freeze", () -> new Potion(new MobEffectInstance(CCEffect.FREEZE.get(), 100)));
    public static final RegistryObject<Potion> LONG_FREEZE = POTIONS.register("long_freeze", () -> new Potion(new MobEffectInstance(CCEffect.FREEZE.get(), 200)));

    public static final RegistryObject<Potion> HOLY = POTIONS.register("holy", () -> new Potion(new MobEffectInstance(CCEffect.HOLY.get(), 100)));
    public static final RegistryObject<Potion> LONG_HOLY = POTIONS.register("long_holy", () -> new Potion(new MobEffectInstance(CCEffect.HOLY.get(), 200)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
