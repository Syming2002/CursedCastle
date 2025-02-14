package com.syming.castlevaniacraft.integration.jer;

import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.api.render.IMobRenderHook;
import jeresources.registry.DungeonRegistry;
import jeresources.util.LootTableHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public abstract class JERBase {
    protected World world;
    protected LootTableManager manager;

    public JERBase() {
        this.world = CCJerIntegration.world;
        this.manager = CCJerIntegration.manager;
    }

    protected void registerDungeonLoot(String category, String unlocName, ResourceLocation drops) {
        if(!DungeonRegistry.categoryToLocalKeyMap.containsKey(category)) {
            CCJerIntegration.dungeonRegistry.registerCategory(category, unlocName);
        }
        CCJerIntegration.dungeonRegistry.registerChest(category, drops);
    }

    protected void registerDungeonLoot(String category, String unlocName, LootTable drops) {
        if(!DungeonRegistry.categoryToLocalKeyMap.containsKey(category)) {
            CCJerIntegration.dungeonRegistry.registerCategory(category, unlocName);
        }
        CCJerIntegration.dungeonRegistry.registerChest(category, drops);
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

    protected void registerRenderHook(Class<? extends EntityLivingBase> clazz, IMobRenderHook renderHook) {
        CCJerIntegration.mobRegistry.registerRenderHook(clazz, renderHook);
    }

    protected <T extends Item & IPlantable> void registerPlant(T plant, PlantDrop... drops) {
        CCJerIntegration.plantRegistry.register(plant, drops);
    }

    protected void registerPlant(ItemStack plant, PlantDrop... drops) {
        CCJerIntegration.plantRegistry.register(plant, drops);
    }

    protected void registerPlant(ItemStack stack, IPlantable plant, PlantDrop... drops) {
        CCJerIntegration.plantRegistry.register(stack, plant, drops);
    }

    protected <T extends Item & IPlantable> void registerPlant(T plant, IBlockState soil, PlantDrop... drops) {
        CCJerIntegration.plantRegistry.registerWithSoil(plant, soil, drops);
    }

    protected void registerPlant(ItemStack stack, IPlantable plant, IBlockState soil, PlantDrop... drops) {
        CCJerIntegration.plantRegistry.registerWithSoil(stack, plant, soil, drops);
    }
}
