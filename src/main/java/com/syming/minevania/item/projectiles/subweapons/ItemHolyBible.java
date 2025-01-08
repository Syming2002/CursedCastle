package com.syming.minevania.item.projectiles.subweapons;

import com.syming.minevania.entity.projectiles.subweapons.EntityHolyBible;
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

public class ItemHolyBible extends Item {

    public ItemHolyBible(String name) {
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack throwStack = playerIn.getHeldItem(hand);
        worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.HOLY_CROSS_THROW, SoundCategory.MASTER, 0.5F, 1.0F);
        if (!worldIn.isRemote) {
            for (int i = 0; i < 20; i++) {
                EntityHolyBible holyBible = new EntityHolyBible(worldIn, playerIn, throwStack);
                holyBible.setPosition(playerIn.posX, playerIn.posY + playerIn.getEyeHeight() / 2, playerIn.posZ);
                worldIn.spawnEntity(holyBible);
                playerIn.getCooldownTracker().setCooldown(this, 240);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
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
