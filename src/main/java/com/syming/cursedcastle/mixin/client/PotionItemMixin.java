package com.syming.cursedcastle.mixin.client;

import com.syming.cursedcastle.potion.CCPotion;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(PotionItem.class)
public class PotionItemMixin {

    @Inject(method = "appendHoverText", at = @At("RETURN"), cancellable = true)
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltip, TooltipFlag pFlag, CallbackInfo ci) {
        if (PotionUtils.getPotion(pStack) == CCPotion.HOLY.get() || PotionUtils.getPotion(pStack) == CCPotion.LONG_HOLY.get()) {
            pTooltip.add(Component.translatable("item.cursedcastle.potion.holy").setStyle(Style.EMPTY.withColor(ColorUtil.HOLY_GOLD_COLOR)));
        }
    }

}
