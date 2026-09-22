//package com.syming.cursedcastle.compat.forestry;
//
//import forestry.api.core.IBlockSubtype;
//import forestry.api.core.IItemSubtype;
//import net.minecraft.util.StringRepresentable;
//
//import java.awt.*;
//
//public enum CCBeesEnumHoneyComb implements StringRepresentable, IItemSubtype, IBlockSubtype {
//
//    HOLY("holy", new Color(0x8FC7DF), new Color(0xE6E6E6)),
//    ROTTEN("rotten", new Color(0xBD422D), new Color(0x753D13)),
//    BONE("bone", new Color(0xFFFEF0), new Color(0xE4E2D2)),
//    BLOOD("blood", new Color(0x992626), new Color(0x8E1D26)),
//    FROZEN("frozen", new Color(0x1298FF), new Color(0x044fB0)),
//    WARMED("warmed", new Color(0xF44211), new Color(0xC41105));
//
//    private final String name;
//    private final Color primaryColor;
//    private final Color secondaryColor;
//
//    CCBeesEnumHoneyComb(String name, Color primaryColor, Color secondaryColor) {
//        this.name = name;
//        this.primaryColor = primaryColor;
//        this.secondaryColor = secondaryColor;
//    }
//
//    @Override
//    public String getSerializedName() {
//        return this.name;
//    }
//
//    public Color getPrimaryColor() {
//        return this.primaryColor;
//    }
//
//    public Color getSecondaryColor() {
//        return this.secondaryColor;
//    }
//}
