package com.syming.castlevaniacraft.item.projectiles.subweapons.holycross;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.IBaubleType;
import baubles.api.cap.BaublesContainer;
import baubles.api.cap.IBaublesItemHandler;
import baubles.common.Baubles;
import baubles.common.init.BaubleTypes;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityFireHolyCross;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityHolyCross;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.item.spellbooks.EnumSpellBook;
import com.syming.castlevaniacraft.item.spellbooks.ItemFireBook;
import com.syming.castlevaniacraft.item.spellbooks.ItemSpellBook;
import com.syming.castlevaniacraft.tab.CCTabs;
import com.syming.castlevaniacraft.util.handlers.SoundsHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemHolyCross extends Item {

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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack throwStack = playerIn.getHeldItem(hand);
        worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.HOLY_CROSS_THROW, SoundCategory.MASTER, 0.5F, 1.0F);
        if (!worldIn.isRemote) {
            ItemStack throwStackCopy = throwStack.copy();

            BaublesContainer baubles = (BaublesContainer) BaublesApi.getBaublesHandler(playerIn);

            ItemStack belt = baubles.getStackInSlot(4);


            EntityHolyCross holyCross = new EntityHolyCross(worldIn, playerIn, throwStackCopy, getMaterial());
            EntityFireHolyCross fireHolyCross = new EntityFireHolyCross(worldIn, playerIn, throwStackCopy, getMaterial());
            if (isFireSpellBookEquipped(playerIn)) {
                worldIn.spawnEntity(fireHolyCross);
            } else {
                worldIn.spawnEntity(holyCross);
            }

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

    private boolean isFireSpellBookEquipped(EntityPlayer player) {
        BaublesContainer baubles = (BaublesContainer) BaublesApi.getBaublesHandler(player);

        for (int i = 0; i < baubles.getSlots(); i++) {
            ItemStack stack = baubles.getStackInSlot(i);

            if (!stack.isEmpty() && stack.getItem() == CCItem.fireBook) {
                if (stack.getItem() instanceof IBauble bauble) {
                    if (bauble.getBaubleType(stack) == BaubleType.BELT) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
