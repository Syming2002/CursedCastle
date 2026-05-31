package com.syming.cursedcastle.item.relic;

import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class FrozenHeartItem extends Item implements ICurioItem {
    public FrozenHeartItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity wearer = slotContext.entity();

        if (CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.FROZEN_HEART.get(), wearer).isPresent() && wearer.getLastHurtMob() != null) {
            wearer.getLastHurtMob().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 3));
            wearer.getLastHurtMob().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 5));
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.identifier().equals("belt")) {
            return false;
        }

        return CuriosApi.getCuriosHelper().findEquippedCurio(this, slotContext.entity()).isEmpty();
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
