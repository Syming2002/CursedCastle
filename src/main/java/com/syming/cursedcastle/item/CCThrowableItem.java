package com.syming.cursedcastle.item;

import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import top.theillusivec4.curios.api.CuriosApi;

public abstract class CCThrowableItem extends TieredItem implements ProjectileItem {
    public CCThrowableItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
}
