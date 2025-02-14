package com.syming.castlevaniacraft.item;

import com.syming.castlevaniacraft.entity.EntityCrucifix;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.block.BlockPlanks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemCrucifix extends Item {

    private BlockPlanks.EnumType woodType;

    public ItemCrucifix(String name, BlockPlanks.EnumType woodType) {
        setRegistryName(name);
        setTranslationKey(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        this.woodType = woodType;

        MVItem.ITEMS.add(this);
    }

    public BlockPlanks.EnumType getWoodType() {
        return this.woodType;
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing == EnumFacing.DOWN)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
            BlockPos blockpos = flag ? pos : pos.offset(facing);
            ItemStack itemstack = player.getHeldItem(hand);

            if (!player.canPlayerEdit(blockpos, facing, itemstack))
            {
                return EnumActionResult.FAIL;
            }
            else
            {
                BlockPos blockpos1 = blockpos.up();
                boolean flag1 = !worldIn.isAirBlock(blockpos) && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
                flag1 = flag1 | (!worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1));

                if (flag1)
                {
                    return EnumActionResult.FAIL;
                }
                else
                {
                    double d0 = (double)blockpos.getX();
                    double d1 = (double)blockpos.getY();
                    double d2 = (double)blockpos.getZ();
                    List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));

                    if (!list.isEmpty())
                    {
                        return EnumActionResult.FAIL;
                    }
                    else
                    {
                        if (!worldIn.isRemote)
                        {
                            worldIn.setBlockToAir(blockpos);
                            worldIn.setBlockToAir(blockpos1);
                            EntityCrucifix crucifix = new EntityCrucifix(worldIn, d0 + 0.5D, d1, d2 + 0.5D, getWoodType());
                            float f = (float) MathHelper.floor((MathHelper.wrapDegrees(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                            crucifix.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f, 0.0F);
                            this.applyRandomRotations(crucifix, worldIn.rand);
                            ItemMonsterPlacer.applyItemEntityDataToEntity(worldIn, player, itemstack, crucifix);
                            worldIn.spawnEntity(crucifix);
                            worldIn.playSound((EntityPlayer)null, crucifix.posX, crucifix.posY, crucifix.posZ, SoundEvents.ENTITY_ARMORSTAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
                        }

                        itemstack.shrink(1);
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            List<EntityMob> nearbyMobs = worldIn.getEntitiesWithinAABB(EntityMob.class, playerIn.getEntityBoundingBox().grow(5.0));

            for (EntityMob mob : nearbyMobs) {
                mob.setAttackTarget(null);
                mob.setRevengeTarget(null);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    private void applyRandomRotations(EntityCrucifix crucifix, Random rand) {
        Rotations rotations = crucifix.getModelRotation();
        float f = rand.nextFloat() * 10.0F - 5.0F;
        Rotations rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
        crucifix.setModelRotation(rotations1);
    }

}
