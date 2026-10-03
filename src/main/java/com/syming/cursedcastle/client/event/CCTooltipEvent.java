package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.potion.CCPotion;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCTooltipEvent {

    @SubscribeEvent
    public static void onTooltipEvent(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();

        if (PotionUtils.getPotion(itemStack) == CCPotion.HOLY.get() || PotionUtils.getPotion(itemStack) == CCPotion.LONG_HOLY.get()) {
            event.getToolTip().add(Component.translatable("tooltip.cursedcastle.potion.holy").setStyle(Style.EMPTY.withColor(ColorUtil.HOLY_GOLD_COLOR)));
        }
    }
}
