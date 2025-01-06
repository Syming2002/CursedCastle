package com.syming.minevania.integration.forestry.items;

import java.awt.*;

public enum CCCombType {

    ROTTEN("rotten", new Color(0xBD422D), new Color(0x753D13)),
    BONE("bone", new Color(0xFFFEF0), new Color(0xE4E2D2)),
    BLOOD("blood", new Color(0x992626), new Color(0x8E1D26)),
    FROZEN("frozen", new Color(0x1298FF), new Color(0x044fB0)),
    WARMED("warmed", new Color(0xF44211), new Color(0xC41105));


    public static final CCCombType[] VALUES = values();

    public final boolean showInList;
    public final String name;
    public final int primaryColor;
    public final int secondaryColor;

    CCCombType(String name, Color primary, Color secondary) {
        this(name, primary, secondary, true);
    }

    CCCombType(String name, Color primary, Color secondary, boolean show) {
        this.name = name;
        this.primaryColor = primary.getRGB();
        this.secondaryColor = secondary.getRGB();
        this.showInList = show;
    }

    public static CCCombType getComb(int meta) {
        return meta < 0 || meta > VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
