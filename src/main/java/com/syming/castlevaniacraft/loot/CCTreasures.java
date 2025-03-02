package com.syming.castlevaniacraft.loot;

import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class CCTreasures {
    public static final ResourceLocation CEMETERY = LootTableList.register(new ResourceLocation(CCReferences.MODID, "chests/cemetery"));
    public static final ResourceLocation CHURCH = LootTableList.register(new ResourceLocation(CCReferences.MODID, "chests/church"));
    public static final ResourceLocation DRACULA = LootTableList.register(new ResourceLocation(CCReferences.MODID, "entities/dracula"));
    public static final ResourceLocation FROZEN_SHADE = LootTableList.register(new ResourceLocation(CCReferences.MODID, "entities/frozen_shade"));
    public static final ResourceLocation HEAT_SHADE = LootTableList.register(new ResourceLocation(CCReferences.MODID, "entities/heat_shade"));
}
