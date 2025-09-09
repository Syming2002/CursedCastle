package com.syming.cursedcastle.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class CCKeybinding {

    public static final CCKeybinding INSTANCE = new CCKeybinding();

    private CCKeybinding() {

    }

    private static final String CURSED_CASTLE_CATEGORY = "key.categories." + CursedCastleMod.MOD_ID;

    public final KeyMapping BULLET_SOUL_KEY = new KeyMapping(
            "key." + CursedCastleMod.MOD_ID + ".bullet_soul_key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_B, -1),
            CURSED_CASTLE_CATEGORY

    );

    public final KeyMapping GUARDIAN_SOUL_KEY = new KeyMapping(
            "key." + CursedCastleMod.MOD_ID + ".guardian_soul_key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_N, -1),
            CURSED_CASTLE_CATEGORY

    );

}
