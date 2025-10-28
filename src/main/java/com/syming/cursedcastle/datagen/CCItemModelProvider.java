package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class CCItemModelProvider extends ItemModelProvider {
    public CCItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CursedCastleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(CCItem.BLOOD_SKELETON_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.DRACULA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.FAMILIAR_BAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.FROZEN_SHADE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.GRIM_REAPER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.HEAT_SHADE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.POISON_SHADE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.SKELETON_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.THUNDER_SHADE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.VAMPIRE_BAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        withExistingParent(CCItem.DRACULA_HEAD.getId().getPath(), mcLoc("item/template_skull"));

        withExistingParent(CursedCastleMod.MOD_ID + ':' + ForgeRegistries.BLOCKS.getKey(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get()).getPath(), modLoc("block/" + ForgeRegistries.BLOCKS.getKey(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get()).getPath()));
    }
}
