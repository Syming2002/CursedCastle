package com.syming.castlevaniacraft.integration.forestry.items;

import java.awt.*;

public enum CCDropType {
    HOLY("holy", new Color(0x8FC7DF), new Color(0xE6E6E6)),
    BLOOD("blood", new Color(0x992626), new Color(0x8E1D26)),
    FROZEN("frozen", new Color(0x1298FF), new Color(0x044fB0)),
    WARMED("warmed", new Color(0xF44211), new Color(0xC41105));

    public static final CCDropType[] VALUES = values();

    public final String name;
    public final int primaryColor;
    public final int secondaryColor;

    CCDropType(String name, Color primary, Color secondary) {
        this.name = name;
        this.primaryColor = primary.getRGB();
        this.secondaryColor = secondary.getRGB();
    }

    public static CCDropType getDrop(int meta) {
        return meta < 0 || meta >= VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
