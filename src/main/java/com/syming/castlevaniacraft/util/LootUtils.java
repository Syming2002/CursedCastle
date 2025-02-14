package com.syming.castlevaniacraft.util;

import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;

import java.util.ArrayList;

public class LootUtils {

    public static void addItemToTable(LootTable table, Item item, int weight, float numRolls, float probability, int minQuantity, int maxQuantity, float minLootBonus, float maxLootBonus, String name) {
        addItemToTable(table, item, 0, 0, weight, numRolls, probability, minQuantity, maxQuantity, minLootBonus, maxLootBonus, name);
    }

    public static void addItemToTable(LootTable table, Item item, int weight, float numRolls, float probability, int minQuantity, int maxQuantity, float minLootBonus, float maxLootBonus, String name, IMethod LootCallbacks, ICondition LootConditions) {
        addItemToTable(table, item, 0, 0, weight, numRolls, probability, minQuantity, maxQuantity, minLootBonus, maxLootBonus, name, LootCallbacks, LootConditions);
    }

    public static void addItemToTable(LootTable table, Item item, int weight, float numRolls, float probability, int minQuantity, int maxQuantity, float minLootBonus, float maxLootBonus, String name, IMethod LootCallbacks) {
        addItemToTable(table, item, 0, 0, weight, numRolls, probability, minQuantity, maxQuantity, minLootBonus, maxLootBonus, name, LootCallbacks, new ICondition() {
            @Override
            public void FunctionsCallback(ArrayList<LootCondition> lootconds) {}
        });
    }

    public static void addItemToTable(LootTable table, Item item, int weight, float numRolls, float probability, int minQuantity, int maxQuantity, float minLootBonus, float maxLootBonus, String name, ICondition LootConditions) {
        addItemToTable(table, item, 0, 0, weight, numRolls, probability, minQuantity, maxQuantity, minLootBonus, maxLootBonus, name, new IMethod() {
            @Override
            public void FunctionsCallback(ArrayList<LootFunction> lootfuncs) {}
        }, LootConditions);
    }

    public static void addItemToTable(LootTable table, Item item, int minMeta, int maxMeta, int weight, float numRolls, float probability, int minQuantity, int maxQuantity, float minLootBonus, float maxLootBonus, String name) {
        addItemToTable(table, item, 0, 0, weight, numRolls, probability, minQuantity, maxQuantity, minLootBonus, maxLootBonus, name,
                new IMethod() {
                    @Override
                    public void FunctionsCallback(ArrayList<LootFunction> lootfuncs) {}
                },
                new ICondition() {
                    @Override
                    public void FunctionsCallback(ArrayList<LootCondition> lootconds) {}
                });
    }

    public static void addItemToTable(LootTable table, Item item, int minMeta, int maxMeta, int weight, float numRolls, float probability, int minQuantity, int maxQuantity, float minLootBonus, float maxLootBonus, String name, IMethod LootCallbacks, ICondition LootConditions) {
        ArrayList<LootCondition> _conditions = new ArrayList<LootCondition>();
        _conditions.add(new RandomChance(probability));
        LootConditions.FunctionsCallback(_conditions);
        LootCondition[] lchance = _conditions.toArray(new LootCondition[0]);
        /*LootCondition[] lchance = {new RandomChance(probability)};*/

        ArrayList<LootFunction> _functions = new ArrayList<LootFunction>();
        _functions.add(new SetCount(lchance, new RandomValueRange(minQuantity, maxQuantity)));
        _functions.add(new LootingEnchantBonus(new LootCondition[]{}, new RandomValueRange(minLootBonus, maxLootBonus), 0));
        _functions.add(new SetMetadata(new LootCondition[]{}, new RandomValueRange(minMeta, maxMeta)));
        LootCallbacks.FunctionsCallback(_functions);
        LootFunction[] lcount = _functions.toArray(new LootFunction[0]);

		/*ArrayList<LootEntryItem> _entries = new ArrayList<LootEntryItem>();
		_entries.add(new LootEntryItem(item, weight, 1, lcount, lchance, name));
		LootEntryItem[] leitem = _entries.toArray(new LootEntryItem[0]);*/
        LootEntryItem[] leitem = {new LootEntryItem(item, weight, 1, lcount, lchance, name)};

        LootPool newPool = createLootPool(leitem, lcount, lchance, new RandomValueRange(numRolls), new RandomValueRange(0), name);

        addItemToTable(table, newPool);
    }

    public static LootPool createLootPool(LootEntryItem[] leitem, LootFunction[] lcount, LootCondition[] lchance, RandomValueRange numRolls, RandomValueRange bonusRolls, String name) {
        return new LootPool(leitem, lchance, numRolls, bonusRolls, name);
    }

    public static void addItemToTable(LootTable table, LootPool pool) {
        table.addPool(pool);
    }

    public static interface IMethod {
        public void FunctionsCallback(ArrayList<LootFunction> lootfuncs);
    }

    public static interface ICondition {
        public void FunctionsCallback(ArrayList<LootCondition> lootconds);
    }
}
