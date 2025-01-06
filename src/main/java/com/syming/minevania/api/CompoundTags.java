package com.syming.minevania.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CompoundTags {

    public static final String DELAY = "delay";

    public static final String AMMUNITION = "ammo";

    public static final String CATALYST = "catalyst";

    public static final String IMPACT_DAMAGE = "impact_damage";

    public static final String ITEM_DAMAGE = "item_damage";

    public static final String ITEM_MATERIAL = "item_material";

    public static final String ACTIVATED = "activated";

    public static final String ENCHANTED = "enchanted";

    public static final String CANCELLED = "cancelled";

    public static final String STARTED = "started";

    public static final String STUNNED = "stunned";

    public static final String TIME = "time";

    public static NBTTagCompound giveCompound(ItemStack stack) {

        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);
        }
        return compound;
    }

}
