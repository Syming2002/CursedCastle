package com.syming.cursedcastle.item.soul;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SoulBulletItem extends Item implements ICurioItem {
    private final Soul soul;

    public SoulBulletItem(Soul pSoul, Properties pProperties) {
        super(pProperties);
        this.soul = pSoul;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.identifier().equals("ring")) {
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
        return 9;
    }

    public Soul getSoul() {
        return this.soul;
    }
}
