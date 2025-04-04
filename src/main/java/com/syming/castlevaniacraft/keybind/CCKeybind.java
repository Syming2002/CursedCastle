package com.syming.castlevaniacraft.keybind;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class CCKeybind extends CastlevaniaCraftMod {

    public static KeyBinding bulletKey = new KeyBinding("key.castlevaniacraft.bullet_soul_keybind", Keyboard.KEY_B, "key.categories.misc");
    public static KeyBinding guardianKey = new KeyBinding("key.castlevaniacraft.guardian_soul_keybind", Keyboard.KEY_G, "key.categories.misc");

}
