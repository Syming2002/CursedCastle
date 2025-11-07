package com.syming.cursedcastle.item.soul;

import net.minecraft.world.item.Rarity;

import java.awt.*;

public enum Soul {
    BAT("bat", SoulType.GUARDIAN, new Color(0x222222), Rarity.UNCOMMON),
    ENDERMAN("enderman", SoulType.GUARDIAN, new Color(0xC800FA), Rarity.RARE),
    ZOMBIE("zombie", SoulType.BULLET, new Color(0x3E692D), Rarity.UNCOMMON);


    private String name;
    private SoulType soulType;
    private Color color;
    private Rarity rarity;

    Soul(String pName, SoulType pSoulType, Color pColor, Rarity pRarity) {
        this.name = pName;
        this.soulType = pSoulType;
        this.color = pColor;
        this.rarity = pRarity;
    }

    public String getName() {
        return this.name;
    }

    public SoulType getSoulType() {
        return this.soulType;
    }

    public Color getColor() {
        return this.color;
    }

    public Rarity getRarity() {
        return this.rarity;
    }
}
