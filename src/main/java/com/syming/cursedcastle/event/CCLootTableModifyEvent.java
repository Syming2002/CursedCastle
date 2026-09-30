package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

@EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCLootTableModifyEvent {

    @SubscribeEvent
    public static void modifyLootTables(LootTableLoadEvent event) {
        if (event.getKey().equals(EntityType.ZOMBIE.getDefaultLootTable())) {
            LootPool pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(CCItem.SOUL_TYPES.get(Soul.ZOMBIE).get())
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(event.getRegistries(), 0.033F, 0.01F))).build();

            event.getTable().addPool(pool);
        }
    }

}
