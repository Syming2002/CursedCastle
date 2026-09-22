package com.syming.cursedcastle.item.whip.vampirekiller.tip;

import net.minecraft.world.item.Item;

public class TipItem extends Item {

    private TipType tipType;

    public TipItem(TipType pTipType, Properties pProperties) {
        super(pProperties);

        this.tipType = pTipType;
    }

    public TipType getTipType() {
        return this.tipType;
    }
}
