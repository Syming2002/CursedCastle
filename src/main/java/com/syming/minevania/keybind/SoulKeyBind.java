package com.syming.minevania.keybind;

import com.syming.minevania.MinevaniaMod;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class SoulKeyBind extends MinevaniaMod {

    public static KeyBinding bulletKey = new KeyBinding("key.minevania.bullet_soul_keybind", Keyboard.KEY_B, "key.categories.misc");
    public static KeyBinding guardianKey = new KeyBinding("key.minevania.guardian_soul_keybind", Keyboard.KEY_G, "key.categories.misc");

}
