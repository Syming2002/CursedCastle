package com.syming.castlevaniacraft.integration.jer;

import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.loot.CCTreasures;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;

import java.util.List;

public class JERCC implements IJERIntegration {
    private static JERCC instance;
    protected World world;
    protected LootTableManager manager;

    public JERCC() {
        this.world = CCJerIntegration.world;
        this.manager = CCJerIntegration.manager;
    }

    public static JERCC getInstance() {
        return instance != null ? instance : (instance = new JERCC());
    }

    @Override
    public void registerModEntities() {
        registerCCMonsters();
    }

    private void registerCCMonsters() {
        registerMob(new EntityDracula(world), LightLevel.hostile, CCTreasures.DRACULA);
        registerMob(new EntityFrozenShade(world), LightLevel.hostile, new String[] {Biomes.COLD_TAIGA.getBiomeName(), Biomes.ICE_PLAINS.getBiomeName(), Biomes.MUTATED_ICE_FLATS.getBiomeName()}, CCTreasures.FROZEN_SHADE);
        registerMob(new EntityHeatShade(world), LightLevel.hostile, new String[] {Biomes.DESERT.getBiomeName(), Biomes.DESERT_HILLS.getBiomeName()}, CCTreasures.HEAT_SHADE);
        registerMob(new EntityPoisonShade(this.world), LightLevel.hostile, new String[] {Biomes.JUNGLE.getBiomeName()}, CCTreasures.POISON_SHADE);
    }

    protected void registerMob(EntityLivingBase entity, LightLevel level, int minExp, int maxExp, String[] biomes, LootDrop... lootDrops) {
        CCJerIntegration.mobRegistry.register(entity, level, minExp, maxExp, biomes, lootDrops);
    }

    protected void registerMob(EntityLivingBase entity, LightLevel level, String[] biomes, LootDrop... lootDrops) {
        int exp = entity instanceof EntityLiving ? ((EntityLiving) entity).experienceValue : 0;
        if(exp > 0)
            registerMob(entity, level, exp, exp, biomes, lootDrops);
        else if(entity instanceof EntityAnimal)
            registerMob(entity, level, 1, 3, biomes, lootDrops);
        else
            registerMob(entity, level, 0, 0, biomes, lootDrops);
    }

    protected void registerMob(EntityLivingBase entity, LightLevel level, String[] biomes, List<LootDrop> lootdrops) {
        registerMob(entity, level, biomes, lootdrops.toArray(new LootDrop[0]));
    }

    protected void registerMob(EntityLivingBase entity, LightLevel level, String[] biomes, ResourceLocation lootDrops) {
        registerMob(entity, level, biomes, LootTableHelper.toDrops(world, lootDrops).toArray(new LootDrop[0]));
    }

    protected void registerMob(EntityLivingBase entity, LightLevel level, ResourceLocation lootDrops) {
        registerMob(entity, level, new String[] {"jer.any"}, LootTableHelper.toDrops(world, lootDrops).toArray(new LootDrop[0]));
    }

    protected void registerMob(EntityLivingBase entity, LightLevel level, LootDrop... lootDrops) {
        registerMob(entity, level, new String[] {"jer.any"}, lootDrops);
    }

    protected void registerMob(EntityLivingBase entity, LightLevel level, int minExp, int maxExp, String[] biomes, ResourceLocation lootDrops) {
        registerMob(entity, level, minExp, maxExp, biomes, LootTableHelper.toDrops(world, lootDrops).toArray(new LootDrop[0]));
    }
}
