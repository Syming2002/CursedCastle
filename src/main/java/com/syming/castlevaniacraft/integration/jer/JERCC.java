package com.syming.castlevaniacraft.integration.jer;

import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.loot.CCTreasures;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Biomes;

public class JERCC extends JERBase implements IJERIntegration {
    private static JERCC instance;
    public JERCC() {}

    public static JERCC getInstance() {
        return instance != null ? instance : (instance = new JERCC());
    }

    @Override
    public void registerModEntities() {
        registerCCMonsters();
    }

    private void registerCCMonsters() {
        registerMob(new EntityDracula(world), LightLevel.hostile, CCTreasures.DRACULA);
        registerMob(new EntityFrozenShade(world), LightLevel.hostile, new String[] {Biomes.COLD_TAIGA.biomeName, Biomes.ICE_PLAINS.biomeName, Biomes.MUTATED_ICE_FLATS.biomeName}, CCTreasures.FROZEN_SHADE);
        registerMob(new EntityHeatShade(world), LightLevel.hostile, new String[] {Biomes.DESERT.biomeName, Biomes.DESERT_HILLS.biomeName}, CCTreasures.HEAT_SHADE);
        registerMob(new EntityPoisonShade(this.world), LightLevel.hostile, new String[] {Biomes.JUNGLE.biomeName}, CCTreasures.POISON_SHADE);
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05,-0.45,0);
            return renderInfo;
        }));
    }
}
