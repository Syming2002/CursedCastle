package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.util.BloodMoonUtil;
import com.syming.castlevaniacraft.world.entity.mob.boss.DraculaEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CastlevaniaCraftMod.MOD_ID)
public class CCEvent {

    @SubscribeEvent
    public static void changeMobAttributes(LivingEvent.LivingTickEvent event) {
        Level level = event.getEntity().level();
        Entity entity = event.getEntity();
        if (entity instanceof  DraculaEntity dracula) {
            if (BloodMoonUtil.isBloodMoon(level)) {
                setAttribute(dracula, Attributes.FOLLOW_RANGE, 200.0D);
                setAttribute(dracula, Attributes.ATTACK_SPEED, 2.4D);
                setAttribute(dracula, Attributes.ATTACK_DAMAGE, 20.0D);
                setAttribute(dracula, Attributes.ARMOR, 15.0D);
                setAttribute(dracula, Attributes.ARMOR_TOUGHNESS, 10.0D);
            } else {
                setAttribute(dracula, Attributes.FOLLOW_RANGE, 100.0D);
                setAttribute(dracula, Attributes.ATTACK_SPEED, 1.2D);
                setAttribute(dracula, Attributes.ATTACK_DAMAGE, 10.0D);
                setAttribute(dracula, Attributes.ARMOR, 0.0D);
                setAttribute(dracula, Attributes.ARMOR_TOUGHNESS, 0.0D);
            }
        }
    }

    private static void setAttribute(LivingEntity entity, Attribute attribute, double value) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null && instance.getBaseValue() != value) {
            instance.setBaseValue(value);
        }
    }

}
