package com.syming.minevania.block;

import com.syming.minevania.damagesource.CCDamageSource;
import com.syming.minevania.init.CCBlock;
import com.syming.minevania.item.CCItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockHolyWaterFluid extends BlockFluidClassic {

    public BlockHolyWaterFluid(String name, Fluid fluid, Material material) {
        super(fluid, material);
        setTranslationKey(name);
        setRegistryName(name);


        CCBlock.BLOCKS.add(this);
        CCItem.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
        super.onEntityCollision(world, pos, state, entity);

        if (entity instanceof EntityMob) {
            entity.attackEntityFrom(CCDamageSource.getHolyDamage(), 5.0F * 5.0F);
        }
    }

}
