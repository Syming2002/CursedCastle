package com.syming.cursedcastle.item.soul;

public enum Soul {
    BAT("bat", SoulType.GUARDIAN),
    ZOMBIE("zombie", SoulType.BULLET);


    private String name;
    private SoulType soulType;

    Soul(String name, SoulType soulType) {
        this.name = name;
        this.soulType = soulType;
    }

    public String getName() {
        return this.name;
    }

    public SoulType getSoulType() {
        return this.soulType;
    }
}
