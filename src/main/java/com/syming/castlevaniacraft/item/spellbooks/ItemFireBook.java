package com.syming.castlevaniacraft.item.spellbooks;

import com.syming.castlevaniacraft.item.CCItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFireBook extends ItemSpellBook {
    public ItemFireBook(String name) {
        super(name);

        CCItem.ITEMS.add(this);
    }

    @Override
    public EnumSpellBook.SpellBookType getSpellBook() {
        return EnumSpellBook.SpellBookType.FIRE_BOOK;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

    }
}
