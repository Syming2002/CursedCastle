package com.syming.minevania.item.projectiles.subweapons.throwingaxe;

import com.syming.minevania.api.ICCItemExtension;
import com.syming.minevania.entity.projectiles.subweapons.EntityThrowingAxe;
import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemThrowingAxe extends com.syming.minevania.api.CCItem {

    private Item.ToolMaterial material;

    public ItemThrowingAxe(String name, Item.ToolMaterial material) {
        super();
        this.material = material;
        setMaxDamage(material.getMaxUses());
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    public Item.ToolMaterial getMaterial() {
        return this.material;
    }

    @Override
    public float getHitDamage() {
        return this.getMaterial().getAttackDamage() + 10F;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return ICCItemExtension.USE_DURATION;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack stack = playerIn.getHeldItemMainhand().copy();

        if (hand == EnumHand.MAIN_HAND && (playerIn.capabilities.isCreativeMode || Helpers.consumeInventoryItem(playerIn, this))) {
            playerIn.setActiveHand(EnumHand.MAIN_HAND);
            if (!worldIn.isRemote) {
                if (stack.getCount() > 1) {
                    stack = stack.splitStack(1);
                }
                EntityThrowingAxe throwingAxe = new EntityThrowingAxe(worldIn, playerIn, stack, getMaterial());
                worldIn.spawnEntity(throwingAxe);
                Helpers.playSound(worldIn, playerIn, "throwing_axe_throw", 0.5F, 1.0F);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItemOffhand());
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        return -2;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    @Override
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.type == EnumEnchantmentType.WEAPON || super.canApplyAtEnchantingTable(stack, enchantment);
    }

}
