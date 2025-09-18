package com.syming.cursedcastle.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class ThrowingAxeUtil {

    public static void addThrowingAxeTooltip(ItemStack pStack, Tier pTier, List<Component> pTooltips) {
        int knockback = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, pStack);
        int fireAspect = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, pStack);

        if (pTier == Tiers.WOOD) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 10.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else if (!pStack.isEnchanted()) {
                pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.STONE) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 10.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else if (!pStack.isEnchanted()) {
                pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.IRON) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 10.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else if (!pStack.isEnchanted()) {
                pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.GOLD) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 10.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else if (!pStack.isEnchanted()) {
                pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.DIAMOND) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 10.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else if (!pStack.isEnchanted()) {
                pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
            }
        } else if (pTier == Tiers.NETHERITE) {
            if (pStack.isEnchanted()) {
                if (pStack.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSharpness", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEFINED) + 10.0F).withStyle(ChatFormatting.GOLD));
                } else if (pStack.getAllEnchantments().containsKey(Enchantments.SMITE)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageSmite", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.UNDEAD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                } else if (pStack.getAllEnchantments().containsKey(Enchantments.BANE_OF_ARTHROPODS)) {
                    pTooltips.add(Component.translatable("throwing_axe.damageArthropods", pTier.getAttackDamageBonus() + EnchantmentHelper.getDamageBonus(pStack, MobType.ARTHROPOD) + 10.0F).withStyle(ChatFormatting.GOLD));
//                    pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));

                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.KNOCKBACK)) {
                    if (knockback > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.knockback", knockback).withStyle(ChatFormatting.GOLD));
                    }
                }
                if (pStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT)) {
                    if (fireAspect > 0) {
                        pTooltips.add(Component.translatable("throwing_axe.fireAspect", fireAspect).withStyle(ChatFormatting.GOLD));
                    }
                }
            } else if (!pStack.isEnchanted()) {
                pTooltips.add(Component.translatable("throwing_axe.damage", pTier.getAttackDamageBonus() + 10.0F).withStyle(ChatFormatting.GOLD));
            }
        }
    }

}
