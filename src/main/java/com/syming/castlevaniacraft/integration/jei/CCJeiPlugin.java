package com.syming.castlevaniacraft.integration.jei;

import com.syming.castlevaniacraft.item.CCItem;
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
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animIronHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animWoodenHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animStoneHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animGoldenHolyCross));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animDiamondHolyCross));

        blacklist.addItemToBlacklist(new ItemStack(CCItem.animFireHolyCross));

        blacklist.addItemToBlacklist(new ItemStack(CCItem.animScytheProjectile));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animWoodenThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animStoneThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animIronThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animGoldenThrowingAxe));
        blacklist.addItemToBlacklist(new ItemStack(CCItem.animDiamondThrowingAxe));
    }
}
