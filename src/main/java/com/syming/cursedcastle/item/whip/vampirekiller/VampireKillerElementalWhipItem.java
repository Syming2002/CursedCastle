package com.syming.cursedcastle.item.whip.vampirekiller;

import com.syming.cursedcastle.item.whip.vampirekiller.tip.TipType;

public class VampireKillerElementalWhipItem extends VampireKillerWhipItem {
    private TipType tipType;

    public VampireKillerElementalWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier, TipType pTipType) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);

        this.tipType = pTipType;
    }

    public TipType getTipType() {
        return this.tipType;
    }
}
