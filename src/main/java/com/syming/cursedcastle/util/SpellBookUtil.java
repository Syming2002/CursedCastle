package com.syming.cursedcastle.util;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.CuriosApi;

public class SpellBookUtil {

    public static boolean isWearingSpellBook(SpellBookElementType spellBookElementType, Player player) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SPELL_BOOK_TYPES.get(spellBookElementType).get(), player).isPresent();
    }

}
