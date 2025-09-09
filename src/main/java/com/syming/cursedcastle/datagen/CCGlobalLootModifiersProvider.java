package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.loot.AddSoulModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class CCGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public CCGlobalLootModifiersProvider(PackOutput output) {
        super(output, CursedCastleMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("zombie_soul_from_zombie", new AddSoulModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build(),
                LootItemKilledByPlayerCondition.killedByPlayer().build(),
                LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(1.0F, 0.33F).build()
        }, CCItem.ZOMBIE_SOUL.get()));
    }
}
