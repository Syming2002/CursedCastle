package com.syming.cursedcastle.item;

import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import top.theillusivec4.curios.api.CuriosApi;

public abstract class CCThrowableItem extends Item {
    public CCThrowableItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.SHARPNESS
                || enchantment == Enchantments.SMITE
                || enchantment == Enchantments.BANE_OF_ARTHROPODS
                || enchantment == Enchantments.FIRE_ASPECT
                || enchantment == Enchantments.MOB_LOOTING
                || enchantment == Enchantments.KNOCKBACK
                || enchantment == Enchantments.UNBREAKING
                || enchantment == CCEnchantment.BLOOD_SKELETON_KILLER.get()
                || super.canApplyAtEnchantingTable(stack, enchantment);
    }
}
