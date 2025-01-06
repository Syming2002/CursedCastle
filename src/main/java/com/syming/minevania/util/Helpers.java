package com.syming.minevania.util;

import com.syming.minevania.util.handlers.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.util.List;

public class Helpers {

    public static boolean consumeInventoryItem(EntityPlayer player, Item itemIn) {
        if (player.getHeldItemMainhand().isItemEqualIgnoreDurability(new ItemStack(itemIn))) {
            int i = player.inventory.currentItem;
            shrinkInventoryItem(player.inventory.mainInventory, i);
            return true;
        }

        boolean consumed = consumeInventoryItem(player.inventory.offHandInventory, itemIn);
        if (!consumed) {
            consumed = consumeInventoryItem(player.inventory.mainInventory, itemIn);
        }

        return consumed;
    }

    public static boolean consumeInventoryItem(NonNullList<ItemStack> inv, Item itemIn) {
        int i = getInventorySlotContainItem(inv, itemIn);
        if (i < 0) {
            return false;
        }
        shrinkInventoryItem(inv, i);
        return true;
    }

    public static void shrinkInventoryItem(NonNullList<ItemStack> inv, int i) {
        ItemStack item = inv.get(i);
        item.shrink(1);
        if (item.getCount() <= 0) {
            inv.set(i, ItemStack.EMPTY);
        }
    }

    public static void destroyCurrentEquippedItem(EntityPlayer player) {
        ItemStack orig = player.inventory.getCurrentItem();
        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
        ForgeEventFactory.onPlayerDestroyItem(player, orig, EnumHand.MAIN_HAND);
    }

    public static int getInventorySlotContainItem(NonNullList<ItemStack> inv, Item itemIn) {
        ItemStack stack = new ItemStack(itemIn);
        for (int i = 0; i < inv.size(); ++i) {
            if (inv.get(i).isItemEqualIgnoreDurability(stack)) {
                return i;
            }
        }
        return -1;
    }

    public static void playSound(World w, Entity e, String name, double volume, double pitch) {
        playSound(w, e, name, (float) volume, (float) pitch);
    }

    public static void playSound(World w, Entity e, String name, float volume, float pitch) {
        w.playSound(null, e.posX, e.posY, e.posZ, SoundsHandler.getSound(name), SoundCategory.MASTER, volume, pitch);
    }

    /**
     * @author jabelar
     */
    public static RayTraceResult getMouseOverExtended(float dist)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Entity theRenderViewEntity = mc.getRenderViewEntity();
        AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
                theRenderViewEntity.posX - 0.5D,
                theRenderViewEntity.posY - 0.0D,
                theRenderViewEntity.posZ - 0.5D,
                theRenderViewEntity.posX + 0.5D,
                theRenderViewEntity.posY + 1.5D,
                theRenderViewEntity.posZ + 0.5D);
        RayTraceResult returnMOP = null;
        if (mc.world != null)
        {
            double var2 = dist;
            returnMOP = theRenderViewEntity.rayTrace(var2, 0);
            double calcdist = var2;
            Vec3d pos = theRenderViewEntity.getPositionEyes(0);
            var2 = calcdist;
            if (returnMOP != null)
            {
                calcdist = returnMOP.hitVec.distanceTo(pos);
            }

            Vec3d lookvec = theRenderViewEntity.getLook(0);
            Vec3d var8 = pos.add(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2);
            Entity pointedEntity = null;
            float var9 = 1.0F;
            List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(theRenderViewEntity,
                    theViewBoundingBox.grow(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2).expand(var9, var9, var9));
            double d = calcdist;

            for (Entity entity : list)
            {
                if (entity.canBeCollidedWith())
                {
                    float bordersize = entity.getCollisionBorderSize();
                    AxisAlignedBB aabb = new AxisAlignedBB(entity.posX - entity.width / 2, entity.posY, entity.posZ - entity.width / 2,
                            entity.posX + entity.width / 2, entity.posY + entity.height, entity.posZ + entity.width / 2);
                    aabb.expand(bordersize, bordersize, bordersize);
                    RayTraceResult mop0 = aabb.calculateIntercept(pos, var8);

                    if (aabb.contains(pos))
                    {
                        if (0.0D < d || d == 0.0D)
                        {
                            pointedEntity = entity;
                            d = 0.0D;
                        }
                    }
                    else if (mop0 != null)
                    {
                        double d1 = pos.distanceTo(mop0.hitVec);

                        if (d1 < d || d == 0.0D)
                        {
                            pointedEntity = entity;
                            d = d1;
                        }
                    }
                }
            }

            if (pointedEntity != null && (d < calcdist || returnMOP == null))
            {
                returnMOP = new RayTraceResult(pointedEntity);
            }

        }
        return returnMOP;
    }

}
