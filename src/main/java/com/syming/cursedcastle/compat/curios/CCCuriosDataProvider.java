package com.syming.cursedcastle.compat.curios;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class CCCuriosDataProvider extends CuriosDataProvider {
    public CCCuriosDataProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(CursedCastleMod.MOD_ID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        this.createSlot("belt")
                .replace(false)
                .size(2);

        this.createSlot("hands")
                .replace(false)
                .size(1);

        this.createSlot("ring")
                .replace(false)
                .size(3);

        this.createEntities("entities")
                .addPlayer()
                .addEntities(EntityType.ARMOR_STAND)
                .addSlots("belt", "hands", "ring");
    }
}
