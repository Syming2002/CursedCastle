package com.syming.cursedcastle.mixin.client;

import com.syming.cursedcastle.potion.CCPotion;
import com.syming.cursedcastle.util.ColorUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(TippedArrowItem.class)
public class TippedArrowItemMixin {

    @Inject(method = "appendHoverText", at = @At("RETURN"), cancellable = true)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag, CallbackInfo ci) {
        PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
        if (potionContents != null) {
            Holder<Potion> potion = potionContents.potion().get();
            if (potion == CCPotion.HOLY || potion == CCPotion.LONG_HOLY) {
                tooltipComponents.add(Component.translatable("item.cursedcastle.potion.holy").setStyle(Style.EMPTY.withColor(ColorUtil.HOLY_GOLD_COLOR)));
            }
        }
    }

}
