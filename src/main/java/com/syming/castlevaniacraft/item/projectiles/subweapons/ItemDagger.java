package com.syming.castlevaniacraft.item.projectiles.subweapons;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesContainer;
import com.syming.castlevaniacraft.DelayedTaskScheduler;
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

import java.util.ArrayList;
import java.util.List;

public class ItemDagger extends Item {

    private Item.ToolMaterial material;

    public ItemDagger(String name, Item.ToolMaterial material) {
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

        if (MinecraftForge.EVENT_BUS.post(event) && !isFireSpellBookEquipped(playerIn)) {
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

                if (isFireSpellBookEquipped(playerIn)) {
                    int tickDelay = 5;
                    List<EntityDagger> spawnedDagger = new ArrayList<>();
                    for (int i = 0; i <= 8; i++) {
                        int finalI = i;
                        DelayedTaskScheduler.schedule(() -> {
                            double angle = 2 * Math.PI * finalI / 8;
                            double x = Math.cos(angle);
                            double z = Math.sin(angle);

                            double spawnX = playerIn.posX + x;
                            double spawnY = playerIn.posY + playerIn.getEyeHeight();
                            double spawnZ = playerIn.posZ + z;

                            EntityDagger dagger = new EntityDagger(worldIn, playerIn, throwStack, spawnX, spawnY, spawnZ, x * 0.5D, 0, z * 0.5D, getMaterial());
                            dagger.motionX = 0;
                            dagger.motionY = 0;
                            dagger.motionZ = 0;
                            worldIn.spawnEntity(dagger);
                            spawnedDagger.add(dagger);
                        }, tickDelay * i);
                    }

                    DelayedTaskScheduler.schedule(() -> {
                        for (int i = 0; i < spawnedDagger.size(); i++) {
                            EntityDagger dagger = spawnedDagger.get(i);
                            if (!dagger.isDead) {
                                double angle = 2 * Math.PI * i / 8;
                                double dx = Math.cos(angle);
                                double dz = Math.sin(angle);

                                dagger.motionX = dx * 0.5;
                                dagger.motionY = 0;
                                dagger.motionZ = dz * 0.5;
                            }
                        }
                    }, tickDelay * 8);
                }
            }

            if (!worldIn.isRemote) {
                if (!isFireSpellBookEquipped(playerIn)) {
                    EntityDagger dagger = new EntityDagger(worldIn, playerIn, throwStack, stack.getMaxItemUseDuration() - timeLeft, getMaterial());
                    dagger.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
                    worldIn.spawnEntity(dagger);
                }
            }

            worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.DAGGER_THROW, SoundCategory.MASTER, 0.5F, 1.0F);
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
