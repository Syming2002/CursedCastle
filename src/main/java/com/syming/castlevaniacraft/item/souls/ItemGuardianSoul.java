package com.syming.castlevaniacraft.item.souls;

import baubles.api.BaubleType;
import baubles.api.IBauble;
//import com.syming.castlevaniacraft.api.ISouls;
//import com.syming.castlevaniacraft.api.SoulType;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyBat;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.keybind.CCKeybind;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGuardianSoul extends Item implements IBauble {

    public ItemGuardianSoul(String name) {
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(CCTabs.CREATIVE_TAB);
        this.setHasSubtypes(true);
        this.setMaxStackSize(9);

        CCItem.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (tab == CCTabs.CREATIVE_TAB) {
            for (int i = 0; i < EnumGuardianSoul.SoulType.values().length; i++) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        for (int i = 0; i < EnumGuardianSoul.SoulType.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getTranslationKey() + "." + EnumGuardianSoul.SoulType.values()[i].getName();
            }
        }
        return this.getTranslationKey() + "." + EnumGuardianSoul.SoulType.BAT_SOUL.getName();
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase livingBase) {

        if (!livingBase.world.isRemote) {
            if (CCKeybind.guardianKey.isPressed()) {
                if (livingBase instanceof EntityPlayer && stack.getMetadata() == EnumGuardianSoul.SoulType.BAT_SOUL.getMeta() && stack.getCount() == 1) {
                    spawnFriendlyBat((EntityPlayer) livingBase);
                } else if (livingBase instanceof EntityPlayer && stack.getMetadata() == EnumGuardianSoul.SoulType.BAT_SOUL.getMeta() && stack.getCount() == 6) {
                    spawnFriendlyBat((EntityPlayer) livingBase);
                    spawnFriendlyBat((EntityPlayer) livingBase);
                } else if (livingBase instanceof EntityPlayer && stack.getMetadata() == EnumGuardianSoul.SoulType.BAT_SOUL.getMeta() && stack.getCount() == 9) {
                    spawnFriendlyBat((EntityPlayer) livingBase);
                    spawnFriendlyBat((EntityPlayer) livingBase);
                    spawnFriendlyBat((EntityPlayer) livingBase);
                }
            }
        }
    }

    private void spawnFriendlyBat(EntityPlayer player) {
        Vec3d direction = player.getLookVec();

        double x = player.posX + direction.x * (-2.0D);
        double y = player.posY;
        double z = player.posZ + direction.z * (-2.0D);
        EntityFriendlyBat friendlyBat = new EntityFriendlyBat(player.world);
        friendlyBat.setPosition(x, y, z);
        player.world.spawnEntity(friendlyBat);
    }
}
