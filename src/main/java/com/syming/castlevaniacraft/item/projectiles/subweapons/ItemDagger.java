package com.syming.castlevaniacraft.item.projectiles.subweapons;

import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityDagger;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import com.syming.castlevaniacraft.util.handlers.SoundsHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemDagger extends Item {

    private Item.ToolMaterial material;

    public ItemDagger(String name, Item.ToolMaterial material) {
        super();
        this.material = material;
        setMaxStackSize(64);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    public Item.ToolMaterial getMaterial() {
        return this.material;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 18000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ArrowNockEvent event = new ArrowNockEvent(playerIn, playerIn.getHeldItemMainhand(), hand, worldIn, true);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return event.getAction();
        }
        playerIn.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase playerIn) {
        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase playerInl, int timeLeft) {
        if (playerInl instanceof EntityPlayer) {

            EntityPlayer playerIn = (EntityPlayer) playerInl;

            ItemStack throwStack = stack.copy();

            if (getMaxItemUseDuration(stack) - timeLeft > 0) {

                worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.DAGGER_THROW, SoundCategory.MASTER, 0.5F, 1.0F);
                if (!worldIn.isRemote) {
                    EntityDagger dagger = new EntityDagger(worldIn, playerIn, throwStack, stack.getMaxItemUseDuration() - timeLeft, getMaterial());
                    dagger.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
                    worldIn.spawnEntity(dagger);
                }
            }
        }
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
