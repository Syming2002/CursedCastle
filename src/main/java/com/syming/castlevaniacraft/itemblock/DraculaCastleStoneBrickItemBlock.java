package com.syming.castlevaniacraft.itemblock;

import com.syming.castlevaniacraft.util.MVReferences;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class DraculaCastleStoneBrickItemBlock extends ItemBlock {

    String[] translationKey;

    public DraculaCastleStoneBrickItemBlock(Block block, String[] translationKey) {
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
        return MVReferences.MODID + '.' + translationKey[stack.getMetadata()];
    }
}
