package com.syming.cursedcastle.item;

import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class CCSpawnEggItem extends ForgeSpawnEggItem {
    public CCSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        if (pLevel == null) return;

        EntityType<?> type = getDefaultType();
        Mob mob = (Mob) type.create(pLevel);

        if (mob instanceof ILivingEntityStrengthWeakness strengthWeakness) {
            pTooltipComponents.addAll(strengthWeakness.getWeaknessDescription());
            pTooltipComponents.addAll(strengthWeakness.getStrengthDescription());
        }
    }
}
