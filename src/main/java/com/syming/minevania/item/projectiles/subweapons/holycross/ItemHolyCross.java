package com.syming.minevania.item.projectiles.subweapons.holycross;

import com.syming.minevania.entity.projectiles.subweapons.EntityHolyCross;
import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHolyCross extends com.syming.minevania.api.CCItem {

    private Item.ToolMaterial material;

    public ItemHolyCross(String name, Item.ToolMaterial material) {
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
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (stack.getItem().isDamageable())
            stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public boolean isDamageable()
    {
        return true;
    }

    @Override
    public float getHitDamage() {
        return this.getMaterial().getAttackDamage() + 1F;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND) {
            // import, otherwise references will cause chaos!
            ItemStack throwStack = playerIn.getHeldItemMainhand().copy();
            if (playerIn.capabilities.isCreativeMode || Helpers.consumeInventoryItem(playerIn, this))
            {
                Helpers.playSound(worldIn, playerIn, "holy_cross_throw", 0.5F, 1.0F);
                if (!worldIn.isRemote)
                {
                    EntityHolyCross holy_cross = new EntityHolyCross(worldIn, playerIn, throwStack, getMaterial());
                    worldIn.spawnEntity(holy_cross);
                }
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItemOffhand());
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
