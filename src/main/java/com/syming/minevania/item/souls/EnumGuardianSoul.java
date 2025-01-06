package com.syming.minevania.item.souls;

import net.minecraft.util.IStringSerializable;

public class EnumGuardianSoul {

    public static enum SoulType implements IStringSerializable {
        BAT_SOUL(0, "bat_soul");

        private int meta;
        private String name;

        private SoulType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public int getMeta() {
            return meta;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
