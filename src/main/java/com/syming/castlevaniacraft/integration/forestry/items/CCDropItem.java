package com.syming.castlevaniacraft.integration.forestry.items;

import com.syming.castlevaniacraft.util.CCReferences;
import forestry.api.apiculture.BeeManager;
import forestry.api.arboriculture.TreeManager;
import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.api.genetics.ISpeciesRoot;
import forestry.api.lepidopterology.ButterflyManager;
import forestry.core.config.Constants;
import forestry.core.items.IColoredItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CCDropItem extends Item implements IColoredItem, IItemModelRegister {

    public CCDropItem() {
        super();
        setHasSubtypes(true);
        setCreativeTab(Tabs.tabApiculture);
        setTranslationKey("cc.honey_drop");
        setRegistryName(CCReferences.MODID, "cc.honey_drop");
        setResearchSuitability(BeeManager.beeRoot);
        setResearchSuitability(TreeManager.treeRoot);
        setResearchSuitability(ButterflyManager.butterflyRoot);
//        setResearchSuitability(AlleleManager.alleleRegistry.getSpeciesRoot("rootFlowers"));
    }

    private void setResearchSuitability(@Nullable ISpeciesRoot speciesRoot) {
        if (speciesRoot != null) {
            speciesRoot.setResearchSuitability(new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE), 0.5F);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void registerModel(Item item, IModelManager iModelManager) {
        iModelManager.registerItemModel(item, 0);
        for (int i = 0; i < CCDropType.VALUES.length; i++) {
            iModelManager.registerItemModel(item, i, Constants.MOD_ID, "cc.honey_drop");
        }
    }

    @NotNull
    @Override
    public String getTranslationKey(@NotNull ItemStack stack) {
        CCDropType type = CCDropType.getDrop(stack.getItemDamage());
        return super.getTranslationKey(stack) + "." + type.name;
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (tab == Tabs.tabApiculture) {
            for (CCDropType type : CCDropType.VALUES) {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack itemStack, int tintIndex) {
        CCDropType type = CCDropType.getDrop(itemStack.getItemDamage());
        if (tintIndex == 1) {
            return type.primaryColor;
        } else {
            return type.secondaryColor;
        }
    }
}
