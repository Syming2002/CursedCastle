package com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesContainer;
import com.syming.castlevaniacraft.entity.projectiles.EntityFireHydra;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityThrowingAxe;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import com.syming.castlevaniacraft.util.handlers.SoundsHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
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

        CCItem.ITEMS.add(this);
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
            if (isFireSpellBookEquipped(playerIn)) {
                EntityFireHydra fireHydra = new EntityFireHydra(worldIn, playerIn);

//                Vec3d look = playerIn.getLookVec();
//
//                double spawnX = playerIn.posX + look.x * 0.5;
//                double spawnY = playerIn.posY + playerIn.getEyeHeight();
//                double spawnZ = playerIn.posZ + look.z * 0.5;
//
//                fireHydra.setPosition(spawnX, spawnY, spawnZ);
//
//                fireHydra.rotationYaw = playerIn.rotationYaw;
//                fireHydra.rotationPitch = playerIn.rotationPitch;
//                fireHydra.renderYawOffset = playerIn.renderYawOffset;
//                fireHydra.rotationYawHead = playerIn.rotationYawHead;
//
//                double speed = 0.8D;
//
//                fireHydra.motionX = look.x * speed;
//                fireHydra.motionY = look.y * speed;
//                fireHydra.motionZ = look.z * speed;
//
//                fireHydra.move(MoverType.SELF, fireHydra.motionX, fireHydra.motionY, fireHydra.motionZ);

                fireHydra.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 0.5F);

                worldIn.spawnEntity(fireHydra);

                worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 0.5F, 1.0F);
            } else {
                EntityThrowingAxe throwingAxe = new EntityThrowingAxe(worldIn, playerIn, throwStackCopy, getMaterial());

                worldIn.spawnEntity(throwingAxe);
                worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.THROWING_AXE_THROW, SoundCategory.MASTER, 0.5F, 1.0F);
                throwStack.shrink(1);
            }
        }
        if (isFireSpellBookEquipped(playerIn)) {
            playerIn.getCooldownTracker().setCooldown(this, 200);
        } else {
            playerIn.getCooldownTracker().setCooldown(this, 100);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
    }

    @Override
    public int getItemEnchantability() {
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
