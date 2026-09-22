//package com.syming.cursedcastle.event;

//import com.syming.cursedcastle.CursedCastleMod;
//import net.neoforged.fml.common.EventBusSubscriber;
//
//@EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
//public class CCLootTableModifyEvent {

//    @SubscribeEvent
//    public static void modifyLootTables(LootTableLoadEvent event) {
//        if (event.getName().equals(EntityType.ZOMBIE.getDefaultLootTable())) {
//            LootPool pool = LootPool.lootPool()
//                    .setRolls(ConstantValue.exactly(1))
//                    .add(LootItem.lootTableItem(CCItem.ZOMBIE_SOUL.get())
//                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
//                            .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.055F, 0.1F))).build();
//
//            event.getTable().addPool(pool);
//        }
//    }

//}
