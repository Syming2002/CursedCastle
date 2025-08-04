package com.syming.castlevaniacraft.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class HolyCrossUtil {

    public static void addHolyCrossTooltip(ItemStack pStack, Tier pTier, List<Component> pTooltips) {
        int knockback = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, pStack);
        int fireAspect = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, pStack);

        if (pTier == Tiers.WOOD) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 2.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("holy_cross.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("holy_cross.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else {
                pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.STONE) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 2.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("holy_cross.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("holy_cross.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else {
                pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.IRON) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 2.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("holy_cross.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("holy_cross.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else {
                pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.GOLD) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 2.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("holy_cross.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("holy_cross.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else {
                pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.DIAMOND) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 2.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("holy_cross.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("holy_cross.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else {
                pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.NETHERITE) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 2.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("holy_cross.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("holy_cross.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 2.0F).withStyle(ChatFormatting.GOLD));
                    pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("holy_cross.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("holy_cross.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else {
                pTooltips.add(Component.translatable("holy_cross.damage", pTier.getAttackDamageBonus() + 2.0F).withStyle(ChatFormatting.GOLD));
            }
        }
    }

}
