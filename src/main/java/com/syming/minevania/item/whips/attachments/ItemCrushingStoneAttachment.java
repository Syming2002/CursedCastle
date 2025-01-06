package com.syming.minevania.item.whips.attachments;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.item.Item;

public class ItemCrushingStoneAttachment extends Item {

    public ItemCrushingStoneAttachment(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }
}
