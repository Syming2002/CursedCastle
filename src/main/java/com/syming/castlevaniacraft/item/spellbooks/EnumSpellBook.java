package com.syming.castlevaniacraft.item.spellbooks;

import net.minecraft.util.IStringSerializable;

public class EnumSpellBook {

    public static enum SpellBookType implements IStringSerializable {
        FIRE_BOOK(0, "fire_book");

        private int meta;
        private String name;

        private SpellBookType(int meta, String name) {
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
