package com.syming.minevania.item.projectiles.subweapons;

import com.syming.minevania.api.ICCItemExtension;
import com.syming.minevania.entity.projectiles.subweapons.EntityDagger;
import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemDagger extends com.syming.minevania.api.CCItem {

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
    public float getHitDamage() {
        return 2 + this.material.getAttackDamage();
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return ICCItemExtension.USE_DURATION;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND) {
            ArrowNockEvent event = new ArrowNockEvent(
                    playerIn, playerIn.getHeldItemMainhand(), EnumHand.MAIN_HAND, worldIn, true);
            if (MinecraftForge.EVENT_BUS.post(event))
                return event.getAction();

            playerIn.setActiveHand(EnumHand.MAIN_HAND);

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItemOffhand());
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

            if (timeLeft <= getMaxItemUseDuration(stack) - 7
                    && (playerIn.capabilities.isCreativeMode || Helpers.consumeInventoryItem(playerIn, this))) {

                Helpers.playSound(worldIn, playerIn, "dagger_throw", 0.5F, 1.0F);
                if (!worldIn.isRemote) {
                    if (throwStack.getCount() > 1) {
                        throwStack = throwStack.splitStack(1);
                    }
                    EntityDagger dagger = new EntityDagger(worldIn, playerIn, throwStack, stack.getMaxItemUseDuration() - timeLeft, getMaterial());
                    dagger.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
                    worldIn.spawnEntity(dagger);
                }
            }
        }
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        return -1;
    }

}
