package com.syming.castlevaniacraft.item.souls;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyZombie;
import com.syming.castlevaniacraft.entity.projectiles.EntityThrowingBoneProjectile;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.keybind.SoulKeyBind;
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

public class ItemBulletSoul extends Item implements IBauble {

    public ItemBulletSoul(String name) {
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
            for (int i = 0; i < EnumBulletSoul.SoulType.values().length; i++) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        for (int i = 0; i < EnumBulletSoul.SoulType.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getTranslationKey() + "." + EnumBulletSoul.SoulType.values()[i].getName();
            }
        }
        return this.getTranslationKey() + "." + EnumBulletSoul.SoulType.ZOMBIE_SOUL.getName();
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        for (int i = 0; i < 4; i++) {
            ItemStack soulStack = BaublesApi.getBaublesHandler((EntityPlayer) player).getStackInSlot(1 + i);
            if (!soulStack.isEmpty() && soulStack.getItem() instanceof ItemBulletSoul) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase livingBase) {
        if (!livingBase.world.isRemote) {
            if (SoulKeyBind.bulletKey.isPressed()) {
                if (livingBase instanceof EntityPlayer && stack.getMetadata() == EnumBulletSoul.SoulType.ZOMBIE_SOUL.getMeta()) {
                    spawnFriendlyZombie((EntityPlayer) livingBase);
                } else if (livingBase instanceof EntityPlayer && stack.getMetadata() == EnumBulletSoul.SoulType.SKELETON_SOUL.getMeta()) {
                    throwBone((EntityPlayer) livingBase);
                }
            }
        }
    }

    private void spawnFriendlyZombie(EntityPlayer player) {
        Vec3d direction = player.getLookVec();

        double x = player.posX + direction.x * 2.0D;
        double y = player.posY;
        double z = player.posZ + direction.z * 2.0D;
        EntityFriendlyZombie friendlyZombie = new EntityFriendlyZombie(player.world);
        friendlyZombie.setPosition(x, y, z);
        player.world.spawnEntity(friendlyZombie);
    }

    private void throwBone(EntityPlayer player) {
        EntityThrowingBoneProjectile boneProjectile = new EntityThrowingBoneProjectile(player.world, player);
        boneProjectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 0.0F);
        player.world.spawnEntity(boneProjectile);
    }

//    private void throwThrowingAxe(EntityPlayer player, Item.ToolMaterial material) {
//        EntityThrowingAxe throwingAxe = new EntityThrowingAxe(player.world, player, material);
//        throwingAxe.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 0.0F);
//        player.world.spawnEntity(throwingAxe);
//    }
}
