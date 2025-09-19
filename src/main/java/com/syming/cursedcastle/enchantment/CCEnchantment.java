package com.syming.cursedcastle.enchantment;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCEnchantment {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<Enchantment> BLOOD_SKELETON_KILLER = ENCHANTMENTS.register("blood_skeleton_killer", () -> new BloodSkeletonKillerEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

}
