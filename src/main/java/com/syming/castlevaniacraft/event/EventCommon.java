package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.advancement.CCAdvancement;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaHead;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import com.syming.castlevaniacraft.world.gen.structure.cave.RusalkaCave;
import lumien.bloodmoon.Bloodmoon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
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

    @SubscribeEvent
    public void onDraculaChangeAttributes(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase livingBase = event.getEntityLiving();
        if (livingBase instanceof EntityDracula dracula) {
            if (Bloodmoon.proxy.isBloodmoon()) {
                setAttribute(dracula, SharedMonsterAttributes.ATTACK_DAMAGE,10.0D * 2.0D);
//                dracula.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
                setAttribute(dracula, SharedMonsterAttributes.ATTACK_SPEED,1.2D * 2.0D);
                setAttribute(dracula, SharedMonsterAttributes.MAX_HEALTH,600.0D);
                setAttribute(dracula, SharedMonsterAttributes.MOVEMENT_SPEED,0.0D);
                setAttribute(dracula, SharedMonsterAttributes.FOLLOW_RANGE,100.0D * 2.0D);
                setAttribute(dracula, SharedMonsterAttributes.KNOCKBACK_RESISTANCE,1.0D);
                setAttribute(dracula, SharedMonsterAttributes.ARMOR,15.0D);
                setAttribute(dracula, SharedMonsterAttributes.ARMOR_TOUGHNESS,10.0D);
            } else {
                setAttribute(dracula, SharedMonsterAttributes.ATTACK_DAMAGE, 10.0D);
//                dracula.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
                setAttribute(dracula, SharedMonsterAttributes.ATTACK_SPEED,1.2D);
                setAttribute(dracula, SharedMonsterAttributes.MAX_HEALTH,600.0D);
                setAttribute(dracula, SharedMonsterAttributes.MOVEMENT_SPEED,0.0D);
                setAttribute(dracula, SharedMonsterAttributes.FOLLOW_RANGE,100.0D);
                setAttribute(dracula, SharedMonsterAttributes.KNOCKBACK_RESISTANCE,1.0D);
                setAttribute(dracula, SharedMonsterAttributes.ARMOR,0.0D);
                setAttribute(dracula, SharedMonsterAttributes.ARMOR_TOUGHNESS,0.0D);
            }
        }
    }

    private void setAttribute(EntityLivingBase entity, IAttribute attribute, double value) {
        IAttributeInstance instance = entity.getEntityAttribute(attribute);
        if (instance != null && instance.getBaseValue() != value) {
            instance.setBaseValue(value);
        }
    }
}
