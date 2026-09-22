package com.syming.cursedcastle.item.spellbook;

import com.syming.cursedcastle.item.soul.Soul;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SpellBookItem extends Item implements ICurioItem {
    protected final SpellBookElementType spellBookElementType;

    public SpellBookItem(SpellBookElementType pSpellBookElementType) {
        super(new Properties().rarity(pSpellBookElementType.getRarity()).stacksTo(1));
        this.spellBookElementType = pSpellBookElementType;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.identifier().equals("belt")) {
            return false;
        }

        return CuriosApi.getCuriosHelper().findEquippedCurio(this, slotContext.entity()).isEmpty();
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    public SpellBookElementType getSpellBookElementType() {
        return this.spellBookElementType;
    }


}
