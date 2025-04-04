package com.syming.castlevaniacraft.item.spellbooks;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import com.syming.castlevaniacraft.item.souls.ItemBulletSoul;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemSpellBook extends Item implements IBauble {

    public ItemSpellBook(String name) {
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(CCTabs.CREATIVE_TAB);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (tab == CCTabs.CREATIVE_TAB) {
            for (int i = 0; i < EnumSpellBook.SpellBookType.values().length; i++) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        for (int i = 0; i < EnumSpellBook.SpellBookType.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getTranslationKey() + "." + EnumSpellBook.SpellBookType.values()[i].getName();
            }
        }
        return this.getTranslationKey() + "." + EnumSpellBook.SpellBookType.FIRE_BOOK.getName();
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.BELT;
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        for (int i = 0; i < 4; i++) {
            ItemStack soulStack = BaublesApi.getBaublesHandler((EntityPlayer) player).getStackInSlot(1 + i);
            if (!soulStack.isEmpty() && soulStack.getItem() instanceof ItemBulletSoul) {
                return false;
            }
        }
        return true;
    }

    public abstract EnumSpellBook.SpellBookType getSpellBook();
}
