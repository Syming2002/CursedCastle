package com.syming.castlevaniacraft.datagen;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.block.CCBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCBlockStateProvider extends BlockStateProvider {
    public CCBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CastlevaniaCraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(CCBlock.DRACULA_CASTLE_COBBLESTONE);
        blockWithItem(CCBlock.DRACULA_CASTLE_STONE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
