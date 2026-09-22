package com.syming.cursedcastle.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.client.KeyMapping;

public final class CCKeybinding {

    public static final CCKeybinding INSTANCE = new CCKeybinding();

    private CCKeybinding() {

    }

    private static final String CURSED_CASTLE_CATEGORY = "key.categories." + CursedCastleMod.MOD_ID;

    public final KeyMapping BULLET_SOUL_KEY = new KeyMapping(
            "key." + CursedCastleMod.MOD_ID + ".bullet_soul_key",
            InputConstants.Type.KEYSYM,
            InputConstants.getKey(InputConstants.KEY_B, -1).getValue(),
            CURSED_CASTLE_CATEGORY

    );

    public final KeyMapping GUARDIAN_SOUL_KEY = new KeyMapping(
            "key." + CursedCastleMod.MOD_ID + ".guardian_soul_key",
            InputConstants.Type.KEYSYM,
            InputConstants.getKey(InputConstants.KEY_N, -1).getValue(),
            CURSED_CASTLE_CATEGORY

    );

}
