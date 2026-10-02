package com.syming.cursedcastle.loot;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

import java.util.Collections;
import java.util.Set;

public class CCLootTable {

    private static final Set<ResourceLocation> CC_LOOT_TABLES = Sets.newHashSet();


    public static Set<ResourceLocation> getCcLootTables() {
        return Collections.unmodifiableSet(CC_LOOT_TABLES);
    }


}
