package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Stream;

public class CCEntityLootSubProvider extends EntityLootSubProvider {
    protected CCEntityLootSubProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        add(CCEntity.ARACHNE.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.STRING)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 5.0F))))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.45F, 0.0F))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))

                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(CCItem.SPIDER_CLAW.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.0F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));

        add(CCEntity.BLOOD_SKELETON.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(CCItem.BLOOD_BONE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.4F, 0.0F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));


        add(CCEntity.CLEAR_BONE_SKELETON.get(),
                LootTable.lootTable());

        add(CCEntity.DRACULA.get(),
                LootTable.lootTable());

        add(CCEntity.FAMILIAR_BAT.get(),
                LootTable.lootTable());

        add(CCEntity.FIRE_ZOMBIE.get(),
                LootTable.lootTable());

        add(CCEntity.FRIENDLY_ZOMBIE.get(),
                LootTable.lootTable());

        add(CCEntity.FROST_ZOMBIE.get(),
                LootTable.lootTable());

        add(CCEntity.GRIM_REAPER.get(),
                LootTable.lootTable());

        add(CCEntity.HARPY.get(),
                LootTable.lootTable());

        add(CCEntity.HEAT_SHADE.get(),
                LootTable.lootTable());

        add(CCEntity.MEDUSA.get(),
                LootTable.lootTable());

        add(CCEntity.MEDUSA_SNAKE.get(),
                LootTable.lootTable());

        add(CCEntity.POISON_SHADE.get(),
                LootTable.lootTable());

        add(CCEntity.RUSALKA.get(),
                LootTable.lootTable());

        add(CCEntity.THROWING_BONE_SKELETON.get(),
                LootTable.lootTable());

        add(CCEntity.THUNDER_SHADE.get(),
                LootTable.lootTable());

        add(CCEntity.FROZEN_SHADE.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.SNOWBALL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(2.0F, 4.0F))
                                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.ICE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))
                                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.3F, 0.0F))
                                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BLUE_ICE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.0F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.PACKED_ICE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.1F, 0.0F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(CCItem.FROZEN_HEART.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05F, 0.0F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
        );

        add(CCEntity.GOLDEN_SKELETON.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(CCItem.GOLDEN_BONE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.045F, 0.075F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.02F, 0.05F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.02F, 0.05F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())))
        );

        add(CCEntity.VAMPIRE_BAT.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(CCItem.SOUL_TYPES.get(Soul.BAT).get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.028F, 0.075F)))));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(entityTypeRegistryObject -> ForgeRegistries.ENTITY_TYPES.getKey(entityTypeRegistryObject).getNamespace().equals(CursedCastleMod.MOD_ID));
    }
}
