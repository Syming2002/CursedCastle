package com.syming.minevania.integration.jei;

import com.syming.minevania.item.MVItem;
import mezz.jei.api.IItemBlacklist;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CCJeiPlugin implements IModPlugin {
    @Override
    public void register(IModRegistry registry) {
        IItemBlacklist blacklist = registry.getJeiHelpers().getItemBlacklist();
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animIronHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animWoodenHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animStoneHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animGoldenHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animDiamondHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animScytheProjectile));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animWoodenThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animStoneThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animIronThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animGoldenThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(MVItem.animDiamondThrowingAxe));
    }
}
