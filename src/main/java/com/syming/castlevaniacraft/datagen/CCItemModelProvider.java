package com.syming.castlevaniacraft.datagen;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.item.CCItem;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CCItemModelProvider extends ItemModelProvider {
    public CCItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CastlevaniaCraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(CCItem.DRACULA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(CCItem.BLOOD_SKELETON_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
