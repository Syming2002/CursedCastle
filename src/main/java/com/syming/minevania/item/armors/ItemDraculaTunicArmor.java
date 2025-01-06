package com.syming.minevania.item.armors;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemDraculaTunicArmor extends ItemArmor {

    public ItemDraculaTunicArmor(String name, ArmorMaterial material, int renderIndexIn, EntityEquipmentSlot equipmentSlot) {
        super(material, renderIndexIn, equipmentSlot);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLivingBase, ItemStack itemStack, EntityEquipmentSlot entityEquipmentSlot, ModelBiped _default) {
        return super.getArmorModel(entityLivingBase, itemStack, entityEquipmentSlot, _default);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return super.getArmorTexture(stack, entity, slot, type);
    }

    @Override
    public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks) {
        super.renderHelmetOverlay(stack, player, resolution, partialTicks);
    }
}
