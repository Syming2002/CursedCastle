package com.syming.cursedcastle.item;

import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import top.theillusivec4.curios.api.CuriosApi;

public abstract class CCThrowableItem extends TieredItem {
    public CCThrowableItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
}
