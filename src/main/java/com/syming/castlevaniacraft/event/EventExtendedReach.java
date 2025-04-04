package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.api.IExtendedReach;
import com.syming.castlevaniacraft.networking.CCNetworking;
import com.syming.castlevaniacraft.networking.MessageExtendedReachAttack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EventExtendedReach {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(MouseEvent event) {
        if (event.getButton() == 0 && event.isButtonstate()) {
            Minecraft minecraft = Minecraft.getMinecraft();
            EntityPlayer player = minecraft.player;

            if (player != null) {
                ItemStack stack = player.getHeldItemMainhand();
                IExtendedReach extendedReach;

                if (stack != null) {
                    if (stack.getItem() instanceof IExtendedReach) {
                        extendedReach = (IExtendedReach) stack.getItem();
                    } else {
                        extendedReach = null;
                    }

                    if (extendedReach != null) {
                        float reach = extendedReach.getReach();

                        RayTraceResult result = getMouseOver(reach);
                        if (result != null) {
                            if (result.entityHit != null && result.entityHit.hurtResistantTime == 0) {
                                if (result.entityHit != player) {
                                    CCNetworking.network.sendToServer(new MessageExtendedReachAttack(result.entityHit.getEntityId()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the block or object that is being moused over.
     */
    public static RayTraceResult getMouseOver(float distance)
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
            double var2 = distance;
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
