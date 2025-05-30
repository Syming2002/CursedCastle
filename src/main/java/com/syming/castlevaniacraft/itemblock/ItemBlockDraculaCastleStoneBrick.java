package com.syming.castlevaniacraft.itemblock;

import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDraculaCastleStoneBrick extends ItemBlock {

    private String[] translationKey;

    public ItemBlockDraculaCastleStoneBrick(Block block, String[] translationKey) {
        super(block);

        this.translationKey = translationKey;
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return CCReferences.MODID + '.' + translationKey[stack.getMetadata()];
    }
}
