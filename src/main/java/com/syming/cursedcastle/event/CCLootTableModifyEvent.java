package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCLootTableModifyEvent {

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

}
