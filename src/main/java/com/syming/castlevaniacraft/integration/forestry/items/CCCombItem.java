package com.syming.castlevaniacraft.integration.forestry.items;

import com.syming.castlevaniacraft.util.CCReferences;
import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.core.config.Constants;
import forestry.core.items.IColoredItem;
import forestry.core.utils.ItemTooltipUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CCCombItem extends Item implements IColoredItem, IItemModelRegister {

    public CCCombItem() {
        super();
        setRegistryName(CCReferences.MODID, "cc.comb");
        setTranslationKey("cc.comb");
        setHasSubtypes(true);
        setCreativeTab(Tabs.tabApiculture);
    }

    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel(@NotNull Item item, IModelManager manager) {
        manager.registerItemModel(item, 0);
        for (int i = 0; i < CCCombType.values().length; i++) {
            manager.registerItemModel(item, i, Constants.MOD_ID, "cc.comb");
        }
    }

    @NotNull
    @Override
    public String getTranslationKey(ItemStack stack) {
        CCCombType type = CCCombType.getComb(stack.getItemDamage());
        return super.getTranslationKey(stack) + "." + type.name;
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (tab == Tabs.tabApiculture) {
            for (CCCombType type : CCCombType.VALUES) {
                if (!type.showInList) continue;
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        CCCombType type = CCCombType.getComb(stack.getItemDamage());
        if (tintIndex == 1) {
            return type.primaryColor;
        } else {
            return type.secondaryColor;
        }
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemTooltipUtil.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
