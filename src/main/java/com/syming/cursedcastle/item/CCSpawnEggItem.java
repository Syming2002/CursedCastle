package com.syming.cursedcastle.item;

import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class CCSpawnEggItem extends SpawnEggItem {

    public CCSpawnEggItem(EntityType<? extends Mob> defaultType, int backgroundColor, int highlightColor, Properties properties) {
        super(defaultType, backgroundColor, highlightColor, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        Level level = context.level();

        if (level == null) return;

        EntityType<?> type = getDefaultType();
        Mob mob = (Mob) type.create(level);

        if (mob instanceof ILivingEntityStrengthWeakness strengthWeakness) {
            tooltipComponents.addAll(strengthWeakness.getWeaknessDescription());
            tooltipComponents.addAll(strengthWeakness.getStrengthDescription());
        }
    }
}
