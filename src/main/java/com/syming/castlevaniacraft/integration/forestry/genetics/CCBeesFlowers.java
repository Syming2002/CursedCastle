package com.syming.castlevaniacraft.integration.forestry.genetics;

import com.google.common.collect.ImmutableList;
import com.syming.castlevaniacraft.block.CCBlock;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.FlowerManager;
import forestry.api.genetics.*;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.Collections;
import java.util.List;

public enum CCBeesFlowers implements IFlowerProvider, IAlleleFlowers {
    BLOOD,
    BONE,
    HOLY,
    ICE,
    MAGMA;

    private final boolean dominant;

    CCBeesFlowers() {
        this.dominant = true;
    }

    public static void doInit() {
        for (final CCBeesFlowers effect : values()) {
            effect.register();
        }
    }

    @Override
    public IFlowerProvider getProvider() {
        return this;
    }

    @Override
    public String getUID() {
        return "castlevaniacraft.flower." + this.toString().toLowerCase();
    }

    @Override
    public boolean isDominant() {
        return this.dominant;
    }

    @Override
    public String getName() {
        return this.getDescription();
    }

    @Override
    public String getUnlocalizedName() {
        return this.getUID();
    }

    @Override
    public boolean isAcceptedPollinatable(World world, ICheckPollinatable iCheckPollinatable) {
        return iCheckPollinatable.getPlantType() != EnumPlantType.Nether;
    }

    @Override
    public String getFlowerType() {
        return this.getUID();
    }

    @Override
    public String getDescription() {
        return I18n.format("castlevaniacraft.flowers." + this.name().toLowerCase() + ".name");
    }

    @Override
    public NonNullList<ItemStack> affectProducts(World world, IIndividual iIndividual, BlockPos blockPos, NonNullList<ItemStack> nonNullList) {
        return nonNullList;
    }

    public void register() {
        IFlowerRegistry flowerRegistry = FlowerManager.flowerRegistry;
        for (Block block : getAcceptableBlocks()) {
            flowerRegistry.registerAcceptableFlower(block, getUID());
        }

        AlleleManager.alleleRegistry.registerAllele(this, EnumBeeChromosome.FLOWER_PROVIDER);
    }

    public List<Block> getAcceptableBlocks() {
        switch (this) {
            case BLOOD: {
                return Collections.singletonList(CCBlock.BLOOD_FLUID);
            }
            case BONE: {
                return Collections.singletonList(Blocks.BONE_BLOCK);
            }
            case HOLY: {
                return Collections.singletonList(CCBlock.HOLY_WATER_FLUID);
            }
            case ICE: {
                return ImmutableList.of(Blocks.ICE, Blocks.PACKED_ICE, Blocks.FROSTED_ICE);
            }
            case MAGMA: {
                return Collections.singletonList(Blocks.MAGMA);
            }

            default: {
                return Collections.emptyList();
            }
        }
    }

    public boolean isAcceptedFlower(final World world, final BlockPos pos) {
        final Block block = world.getBlockState(pos).getBlock();
        switch (this) {
            case BLOOD: {
                return block == CCBlock.BLOOD_FLUID;
            }
            case BONE: {
                return block == Blocks.BONE_BLOCK;
            }
            case ICE: {
                return block == Blocks.ICE || block == Blocks.PACKED_ICE || block == Blocks.FROSTED_ICE;
            }
            case HOLY: {
                return block == CCBlock.HOLY_WATER_FLUID;
            }
            case MAGMA: {
                return block == Blocks.MAGMA;
            }
            default: {
                return false;
            }
        }
    }
}
