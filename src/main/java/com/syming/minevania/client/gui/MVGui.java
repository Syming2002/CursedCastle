package com.syming.minevania.client.gui;

import com.syming.minevania.api.IExtendedReach;
import com.syming.minevania.item.whips.ItemWhip;
import com.syming.minevania.networking.MVNetworking;
//import com.syming.minevania.networking.MessageAttackIndicator;
import meldexun.reachfix.config.ReachFixConfig;
import meldexun.reachfix.util.BoundingBoxUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public class MVGui {
    private static final MVRenderBossBar bossBar = new MVRenderBossBar();

    @SubscribeEvent
    public void onRenderBossInfo(RenderGameOverlayEvent.BossInfo event) {
        Minecraft mc = Minecraft.getMinecraft();
        BossInfoClient bossInfo = event.getBossInfo();
        UUID bossUUID = bossInfo.getUniqueId();
        if (mc.world == null) return;

        if (MVRenderBossBar.isDraculaBossBar(bossUUID)) {
            bossBar.drawDraculaBossHealthBar(event.getX(), event.getY(), bossInfo);
            event.setIncrement(event.getIncrement());
            event.setCanceled(true);
        }
    }
}
