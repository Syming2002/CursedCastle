package com.syming.minevania.loot;

import com.syming.minevania.util.MVReferences;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class CCTreasures {
    public static final ResourceLocation CEMETERY = LootTableList.register(new ResourceLocation(MVReferences.MODID, "chests/cemetery"));
    public static final ResourceLocation CHURCH = LootTableList.register(new ResourceLocation(MVReferences.MODID, "chests/church"));
    public static final ResourceLocation DRACULA = LootTableList.register(new ResourceLocation(MVReferences.MODID, "entities/dracula"));
    public static final ResourceLocation FROZEN_SHADE = LootTableList.register(new ResourceLocation(MVReferences.MODID, "entities/frozen_shade"));
    public static final ResourceLocation HEAT_SHADE = LootTableList.register(new ResourceLocation(MVReferences.MODID, "entities/heat_shade"));
}
