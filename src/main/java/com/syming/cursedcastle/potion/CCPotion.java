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

    public static final RegistryObject<Potion> ANTI_BLOOD_SKELETON = POTIONS.register("anti_blood_skeleton", () -> new Potion(new MobEffectInstance(CCEffect.ANTI_BLOOD_SKELETON.get(), 100)));
    public static final RegistryObject<Potion> LONG_ANTI_BLOOD_SKELETON = POTIONS.register("long_anti_blood_skeleton", () -> new Potion(new MobEffectInstance(CCEffect.ANTI_BLOOD_SKELETON.get(), 200)));
    public static final RegistryObject<Potion> STRONG_ANTI_BLOOD_SKELETON = POTIONS.register("strong_anti_blood_skeleton", () -> new Potion(new MobEffectInstance(CCEffect.ANTI_BLOOD_SKELETON.get(), 50, 1)));

    public static final RegistryObject<Potion> FREEZE = POTIONS.register("freeze", () -> new Potion(new MobEffectInstance(CCEffect.FREEZE.get(), 100)));
    public static final RegistryObject<Potion> LONG_FREEZE = POTIONS.register("long_freeze", () -> new Potion(new MobEffectInstance(CCEffect.FREEZE.get(), 200)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
