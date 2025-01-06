package com.syming.minevania.item.projectiles.subweapons;

import com.syming.minevania.entity.projectiles.subweapons.EntityHolyBible;
import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHolyBible extends com.syming.minevania.api.CCItem {

    public ItemHolyBible(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND) {
            ItemStack throwStack = playerIn.getHeldItemMainhand().copy();
            if (playerIn.capabilities.isCreativeMode || Helpers.consumeInventoryItem(playerIn, this)) {
                Helpers.playSound(worldIn, playerIn, "holy_cross_throw", 0.5F, 1.0F);
                if (!worldIn.isRemote) {
                    for (int i = 0; i < 20; i++) {
                        EntityHolyBible holyBible = new EntityHolyBible(worldIn, playerIn, throwStack);
                        holyBible.setPosition(playerIn.posX, playerIn.posY + playerIn.getEyeHeight() / 2, playerIn.posZ);
                        worldIn.spawnEntity(holyBible);
                        playerIn.getCooldownTracker().setCooldown(this, 240);
                    }
                }
            }

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItemOffhand());
    }
}
