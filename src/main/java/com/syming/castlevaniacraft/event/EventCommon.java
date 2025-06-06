package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.advancement.CCAdvancement;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaHead;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import com.syming.castlevaniacraft.world.gen.structure.cave.RusalkaCave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class EventCommon {

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.world.isRemote && event.world instanceof WorldServer) {
            RusalkaCave.processQueue((WorldServer) event.world);
        }
    }

    @SubscribeEvent
    public void onGrimReaperDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof EntityGrimReaper) {
            if (event.getSource().getImmediateSource() instanceof EntityPlayerMP playerMP) {
                CCAdvancement.GRIM_REAPER_DEATH.trigger(playerMP);
            }
        }
    }

    @SubscribeEvent
    public void onCrystalDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityEnderCrystal enderCrystal) {
            EntityDraculaHead head = (EntityDraculaHead) event.getEntity();
            DamageSource source = event.getSource();
            Entity entity = head.world.getEntityByID(head.getEntityId());

            if (entity instanceof EntityDraculaHead) {
                head.onCrystalDestroyed(enderCrystal, new BlockPos(enderCrystal), source);
            }
        }
    }
}
