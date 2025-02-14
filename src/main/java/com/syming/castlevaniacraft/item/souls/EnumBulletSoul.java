package com.syming.castlevaniacraft.item.souls;

import net.minecraft.util.IStringSerializable;

public class EnumBulletSoul {

    public static enum SoulType implements IStringSerializable {
        ZOMBIE_SOUL(0, "zombie_soul"),
        SKELETON_SOUL(1, "skeleton_soul");

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
