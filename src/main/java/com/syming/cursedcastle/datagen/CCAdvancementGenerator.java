package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CCAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
    protected static AdvancementHolder ROOT;
    protected static AdvancementHolder DRACULA_KILLED;

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
        AdvancementHolder root = Advancement.Builder.advancement().display(
                        CCBlock.DRACULA_CASTLE_STONE_BRICKS.get(),
                        Component.translatable("advancements.cursedcastle.root.title"),
                        Component.translatable("advancements.cursedcastle.root.description"),
                        ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/block/dracula_castle_stone_bricks.png"),
                        AdvancementType.TASK,
                        false, true, false)
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("cursedCastle", PlayerTrigger.TriggerInstance.tick())
                .save(consumer, CursedCastleMod.MOD_ID + ':' + "root");
        ROOT = root;

        AdvancementHolder leatherWhip = Advancement.Builder.advancement().display(
                        CCItem.LEATHER_WHIP.get(),
                        Component.translatable("advancements.cursedcastle.leatherWhip.title"),
                        Component.translatable("advancements.cursedcastle.leatherWhip.description"),
                        null,
                        AdvancementType.TASK,
                        true, true, false
                )
                .parent(root)
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("leatherWhip", InventoryChangeTrigger.TriggerInstance.hasItems(CCItem.LEATHER_WHIP.get()))
                .save(consumer, CursedCastleMod.MOD_ID + ':' + "leatherWhip");
    }
}
