package com.syming.castlevaniacraft.item.whips.attachments;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.item.Item;

public class ItemYellowStoneAttachment extends Item {

    public ItemYellowStoneAttachment(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }
}
