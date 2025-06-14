package com.syming.castlevaniacraft.util;

import net.minecraftforge.fml.common.Loader;

public enum ModLoadedUtils {

    Forestry(ModNames.FORESTRY),
    NetherBackport(ModNames.NETHER_BACKPORT);

    public static class ModNames {
        public static final String FORESTRY = "forestry";
        public static final String NETHER_BACKPORT = "nb";
    }

    private final String ID;
    protected Boolean modLoaded;

    ModLoadedUtils(String id) {
        this.ID = id;
    }

    public boolean isModLoaded() {
        if (this.modLoaded == null) {
            this.modLoaded = Loader.isModLoaded(this.ID);
        }

        return this.modLoaded;
    }

}
