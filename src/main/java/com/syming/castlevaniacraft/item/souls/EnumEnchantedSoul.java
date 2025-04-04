package com.syming.castlevaniacraft.item.souls;

import net.minecraft.util.IStringSerializable;

public class EnumEnchantedSoul {

    public static enum SoulType implements IStringSerializable {
        GHOST_DANCER(0, "ghost_dancer");

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
