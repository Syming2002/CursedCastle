package com.syming.cursedcastle.util;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

import java.util.List;

public class HolyCrossUtil {

    public static void addHolyCrossTooltip(Level pLevel, ItemStack pStack, List<Component> pTooltips) {
        Registry<Enchantment> enchantmentRegistryLookup = pLevel.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> sharpnessHolder = pLevel.holderOrThrow(Enchantments.SHARPNESS);
        Holder<Enchantment> smiteHolder = pLevel.holderOrThrow(Enchantments.SMITE);
        Holder<Enchantment> arthropodsHolder = pLevel.holderOrThrow(Enchantments.BANE_OF_ARTHROPODS);
        Holder<Enchantment> knockbackHolder = pLevel.holderOrThrow(Enchantments.KNOCKBACK);
        Holder<Enchantment> fireAspectHolder = pLevel.holderOrThrow(Enchantments.FIRE_ASPECT);


        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(sharpnessHolder, pStack);
        int smite = EnchantmentHelper.getItemEnchantmentLevel(smiteHolder, pStack);
        int arthropods = EnchantmentHelper.getItemEnchantmentLevel(arthropodsHolder, pStack);
        int knockback = EnchantmentHelper.getItemEnchantmentLevel(knockbackHolder, pStack);
        int fireAspect = EnchantmentHelper.getItemEnchantmentLevel(fireAspectHolder, pStack);


        for (Tier tier : Tiers.values()) {
            if (pStack.isEnchanted()) {
                for (Holder<Enchantment> enchantment : pStack.getAllEnchantments(enchantmentRegistryLookup.asLookup()).keySet()) {
                    if (enchantment == sharpnessHolder) {
                        if (sharpness > 0) {
                            pTooltips.add(Component.translatable("holy_cross.damageSharpness", tier.getAttackDamageBonus() + sharpness + 2.0F).withStyle(ChatFormatting.GOLD));
                        }
                    } else if (enchantment == smiteHolder) {
                        if (smite > 0) {
                            pTooltips.add(Component.translatable("holy_cross.damageSmite", tier.getAttackDamageBonus() + smite + 2.0F).withStyle(ChatFormatting.GOLD));
                        }
                    } else if (enchantment == arthropodsHolder) {
                        if (arthropods > 0) {
                            pTooltips.add(Component.translatable("holy_cross.damageArthropods", tier.getAttackDamageBonus() + arthropods + 2.0F).withStyle(ChatFormatting.GOLD));
                        }
                    }

                    if (enchantment == knockbackHolder) {
                        if (knockback > 0) {
                            pTooltips.add(Component.translatable("holy_cross.knockback", knockback).withStyle(ChatFormatting.GOLD));
                        }
                    }
                    if (enchantment == fireAspectHolder) {
                        if (fireAspect > 0) {
                            pTooltips.add(Component.translatable("holy_cross.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                        }
                    }
                }
            } else {
                pTooltips.add(Component.translatable("holy_cross.damage", tier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
            }
        }
    }

}
