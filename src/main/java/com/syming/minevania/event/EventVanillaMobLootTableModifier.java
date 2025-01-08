package com.syming.minevania.event;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.util.LootUtils;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChanceWithLooting;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class EventVanillaMobLootTableModifier {

    @SubscribeEvent
    public void onMobsLootTablesLoaded(LootTableLoadEvent event) {
        LootCondition[] chance;
        LootCondition[] lootingEnchant;
        LootFunction[] count;
        LootEntryItem[] item;
        LootPool newPool;
        LootTable loot = event.getTable();

        if (event.getName().getPath().equals("entities/bat")) {
            LootUtils.addItemToTable(loot, MVItem.guardianSoul, 1, 1, 0.028F, 0, 1, 0, 0, "minevania:guardian_soul",
                    new LootUtils.IMethod() {
                        @Override
                        public void FunctionsCallback(ArrayList<LootFunction> lootfuncs) {
                            LootFunction metadata = new SetMetadata(null, new RandomValueRange(0));
                            lootfuncs.add(metadata);
                        }
                    },
                    new LootUtils.ICondition() {
                        @Override
                        public void FunctionsCallback(ArrayList<LootCondition> lootconds) {
                            LootCondition looting = new RandomChanceWithLooting(0.028F, 0.5F);
                            lootconds.add(looting);
                        }
                    });
        }

        if (event.getName().getPath().equals("entities/skeleton")) {
            LootUtils.addItemToTable(loot, MVItem.bulletSoul, 1, 1, 0.055F, 0, 1, 0, 0, "minevania:bullet_soul",
                    new LootUtils.IMethod() {
                        @Override
                        public void FunctionsCallback(ArrayList<LootFunction> lootfuncs) {
                            LootFunction metadata = new SetMetadata(null, new RandomValueRange(1));
                            lootfuncs.add(metadata);
                        }
                    },
                    new LootUtils.ICondition() {
                        @Override
                        public void FunctionsCallback(ArrayList<LootCondition> lootconds) {
                            LootCondition looting = new RandomChanceWithLooting(0.055F, 0.5F);
                            lootconds.add(looting);
                        }
                    });
        }

        if (event.getName().getPath().equals("entities/zombie")) {
            LootUtils.addItemToTable(loot, MVItem.bulletSoul, 1, 1, 0.055F, 0, 1, 0, 0, "minevania:bullet_soul",
                    new LootUtils.IMethod() {
                        @Override
                        public void FunctionsCallback(ArrayList<LootFunction> lootfuncs) {
                            LootFunction metadata = new SetMetadata(null, new RandomValueRange(0));
                            lootfuncs.add(metadata);
                        }
                    },
                    new LootUtils.ICondition() {
                        @Override
                        public void FunctionsCallback(ArrayList<LootCondition> lootconds) {
                            LootCondition looting = new RandomChanceWithLooting(0.055F, 0.5F);
                            lootconds.add(looting);
                        }
                    });
        }
    }
}
