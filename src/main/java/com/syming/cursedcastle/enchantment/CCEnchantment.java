package com.syming.cursedcastle.enchantment;

import com.mojang.serialization.MapCodec;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CCEnchantment {

    public static final ResourceKey<Enchantment> BLOOD_SKELETON_KILLER = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "blood_skeleton_killer"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        register(context, BLOOD_SKELETON_KILLER, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                3,
                1,
                Enchantment.dynamicCost(1, 8),
                Enchantment.dynamicCost(5, 8),
                4,
                EquipmentSlotGroup.MAINHAND
        )));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }

}
