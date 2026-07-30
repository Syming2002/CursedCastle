package com.syming.cursedcastle.block;

import net.minecraft.util.StringRepresentable;

public enum TripleBlockPart implements StringRepresentable {
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right"),;

    private final String name;

    TripleBlockPart(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
