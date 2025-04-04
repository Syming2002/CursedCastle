package com.syming.castlevaniacraft.item.souls;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.keybind.CCKeybind;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnchantedSoul extends Item implements IBauble {

    public ItemEnchantedSoul(String name) {
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(CCTabs.CREATIVE_TAB);
        this.setHasSubtypes(true);
        this.setMaxStackSize(9);

        CCItem.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (tab == CCTabs.CREATIVE_TAB) {
            for (int i = 0; i < EnumEnchantedSoul.SoulType.values().length; i++) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        for (int i = 0; i < EnumEnchantedSoul.SoulType.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getTranslationKey() + "." + EnumEnchantedSoul.SoulType.values()[i].getName();
            }
        }
        return this.getTranslationKey() + "." + EnumEnchantedSoul.SoulType.GHOST_DANCER.getName();
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        for (int i = 0; i < 4; i++) {
            ItemStack soulStack = BaublesApi.getBaublesHandler((EntityPlayer) player).getStackInSlot(1 + i);
            if (!soulStack.isEmpty() && soulStack.getItem() instanceof ItemEnchantedSoul) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase livingBase) {
        if (!livingBase.world.isRemote) {
            if (CCKeybind.bulletKey.isPressed()) {
            }
        }
    }
}
