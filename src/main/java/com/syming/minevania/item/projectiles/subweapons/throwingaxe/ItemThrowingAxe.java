package com.syming.minevania.item.projectiles.subweapons.throwingaxe;

import com.syming.minevania.entity.projectiles.subweapons.EntityThrowingAxe;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.handlers.SoundsHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemThrowingAxe extends Item {

    private Item.ToolMaterial material;

    public ItemThrowingAxe(String name, Item.ToolMaterial material) {
        this.material = material;
        setMaxDamage(material.getMaxUses());
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    public Item.ToolMaterial getMaterial() {
        return this.material;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 36000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack throwStack = playerIn.getHeldItem(hand);
            if (!worldIn.isRemote) {
                ItemStack throwStackCopy = throwStack.copy();
                EntityThrowingAxe throwingAxe = new EntityThrowingAxe(worldIn, playerIn, throwStackCopy, getMaterial());
                worldIn.spawnEntity(throwingAxe);
                worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.THROWING_AXE_THROW, SoundCategory.MASTER, 0.5F, 1.0F);
                throwStack.shrink(1);

            }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
    }

    @Override
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.SHARPNESS
                || enchantment == Enchantments.SMITE
                || enchantment == Enchantments.BANE_OF_ARTHROPODS
                || enchantment == Enchantments.KNOCKBACK
                || enchantment == Enchantments.FIRE_ASPECT
                || enchantment == Enchantments.LOOTING;
    }

}
