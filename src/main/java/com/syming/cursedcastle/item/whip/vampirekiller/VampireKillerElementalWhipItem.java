package com.syming.cursedcastle.item.whip.vampirekiller;

import com.syming.cursedcastle.item.whip.vampirekiller.tip.TipType;

public class VampireKillerElementalWhipItem extends VampireKillerWhipItem {
    private TipType tipType;

    public VampireKillerElementalWhipItem(Properties pProperties, TipType pTipType) {
        super(pProperties);

        this.tipType = pTipType;
    }

    public TipType getTipType() {
        return this.tipType;
    }
}
