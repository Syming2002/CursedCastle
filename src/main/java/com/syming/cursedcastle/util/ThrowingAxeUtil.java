package com.syming.cursedcastle.util;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;

public class ThrowingAxeUtil {

    public static void addThrowingAxeTooltip(Level pLevel, ItemStack pStack, List<Component> pTooltips) {
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
                            pTooltips.add(Component.translatable("throwing_axe.damageSharpness", tier.getAttackDamageBonus() + tier.getAttackDamageBonus() + sharpness + 10.0F).withStyle(ChatFormatting.GOLD));
                        }
                    } else if (enchantment == smiteHolder) {
                        if (smite > 0) {
                            pTooltips.add(Component.translatable("throwing_axe.damageSmite", tier.getAttackDamageBonus() + tier.getAttackDamageBonus() + smite + 10.0F).withStyle(ChatFormatting.GOLD));
                        }
                    } else if (enchantment == arthropodsHolder) {
                        if (arthropods > 0) {
                            pTooltips.add(Component.translatable("throwing_axe.damageArthropods", tier.getAttackDamageBonus() + tier.getAttackDamageBonus() + arthropods + 10.0F).withStyle(ChatFormatting.GOLD));
                        }
                    }

                    if (enchantment == knockbackHolder) {
                        if (knockback > 0) {
                            pTooltips.add(Component.translatable("throwing_axe.knockback", knockback).withStyle(ChatFormatting.GOLD));
                        }
                    }
                    if (enchantment == fireAspectHolder) {
                        if (fireAspect > 0) {
                            pTooltips.add(Component.translatable("throwing_axe.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                        }
                    }
                }
            } else {
                pTooltips.add(Component.translatable("throwing_axe.damage", tier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
            }
        }
    }

}
