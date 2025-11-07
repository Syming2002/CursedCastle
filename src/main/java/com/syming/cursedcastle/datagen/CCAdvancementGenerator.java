package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CCAdvancementGenerator implements DataProvider {
    protected static Advancement ROOT;
    protected static Advancement DRACULA_KILLED;


    private final PackOutput packOutput;
    private final ExistingFileHelper fileHelper;

    public CCAdvancementGenerator(PackOutput pPackOutput, ExistingFileHelper pFileHelper) {

        this.packOutput = pPackOutput;
        this.fileHelper = pFileHelper;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        Path path = this.packOutput.getOutputFolder();
        List<CompletableFuture<?>> list = new ArrayList<>();
        Consumer<Advancement> consumer = (advancement -> {
           Path path1 = createPath(path, advancement);
           list.add(DataProvider.saveStable(pOutput, advancement.deconstruct().serializeToJson(), path1));
        });
        registerAdvancements(consumer, this.fileHelper);
        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Cursed Castle Advancement Generator";
    }

    protected void registerAdvancements(Consumer<Advancement> pConsumer, ExistingFileHelper pFileHelper) {
        Advancement root = Advancement.Builder.advancement().display(
                CCBlock.DRACULA_CASTLE_STONE_BRICKS.get(),
                Component.translatable("advancements.cursedcastle.root.title"),
                Component.translatable("advancements.cursedcastle.root.description"),
                new ResourceLocation(CursedCastleMod.MOD_ID, "textures/block/dracula_castle_stone_bricks.png"),
                FrameType.TASK,
                false, true, false)
                .requirements(RequirementsStrategy.OR)
                .addCriterion("cursedCastle", PlayerTrigger.TriggerInstance.tick())
                .save(pConsumer, CursedCastleMod.MOD_ID + ':' + "root");
        ROOT = root;

        Advancement leatherWhip = Advancement.Builder.advancement().display(
                        CCItem.LEATHER_WHIP.get(),
                        Component.translatable("advancements.cursedcastle.leatherWhip.title"),
                        Component.translatable("advancements.cursedcastle.leatherWhip.description"),
                        null,
                        FrameType.TASK,
                        true, true, false
                )
                .parent(root)
                .requirements(RequirementsStrategy.AND)
                .addCriterion("leatherWhip", InventoryChangeTrigger.TriggerInstance.hasItems(CCItem.LEATHER_WHIP.get()))
                .save(pConsumer, CursedCastleMod.MOD_ID + ':' + "leatherWhip");

    }

    private static Path createPath(Path pPath, Advancement pAdvancement) {
        return pPath.resolve("data" + '/' + pAdvancement.getId().getNamespace() + '/' + "advancements" + '/' + pAdvancement.getId().getPath() + ".json");
    }
}
