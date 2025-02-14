package com.syming.castlevaniacraft.integration.forestry.items;

import java.awt.*;

public enum CCDropType {
    BLOOD("blood", new Color(0x992626), new Color(0x8E1D26));

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
