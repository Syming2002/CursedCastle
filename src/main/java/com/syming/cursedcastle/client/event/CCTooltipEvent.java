package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.potion.CCPotion;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCTooltipEvent {

    @SubscribeEvent
    public static void onTooltipEvent(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        PotionContents potionContents = itemStack.get(DataComponents.POTION_CONTENTS);

        if (potionContents != null) {
            if (potionContents.is(CCPotion.HOLY) || potionContents.is(CCPotion.LONG_HOLY)) {
                event.getToolTip().add(Component.translatable("tooltip.cursedcastle.potion.holy").setStyle(Style.EMPTY.withColor(ColorUtil.HOLY_GOLD_COLOR)));
            }
        }
    }
}
