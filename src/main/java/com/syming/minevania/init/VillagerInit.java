package com.syming.minevania.init;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.item.projectiles.subweapons.ItemHolyWater;
import com.syming.minevania.util.MVReferences;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class VillagerInit {

    public static VillagerRegistry.VillagerProfession villagerHighPriest;
    public static final String ID_VILLAGER_HIGH_PRIEST = new ResourceLocation(MVReferences.MODID, "high_priest").toString();
    public static final String TEXTURE_SKIN_HIGH_PRIEST = MVReferences.MODID + ':' + "textures/entity/high_priest.png";
    public static final String TEXTURE_SKIN_ZOMBIE_HIGH_PRIEST = MVReferences.MODID + ':' + "textures/entity/zombie_high_priest.png";

    public static void init() {

        villagerHighPriest = new VillagerRegistry.VillagerProfession(ID_VILLAGER_HIGH_PRIEST, TEXTURE_SKIN_HIGH_PRIEST, TEXTURE_SKIN_ZOMBIE_HIGH_PRIEST);
        IForgeRegistry<VillagerRegistry.VillagerProfession> villagerProfessions = ForgeRegistries.VILLAGER_PROFESSIONS;
        villagerProfessions.register(villagerHighPriest);

        VillagerRegistry.VillagerCareer highPriestCareer = new VillagerRegistry.VillagerCareer(villagerHighPriest, "high_priest");
        highPriestCareer.addTrade(1,
                new GiveHolyWaterForPreciousItems((ItemHolyWater) MVItem.holyWater,
                        new ItemStack(Items.DIAMOND),
                        new EntityVillager.PriceInfo(2, 4), new EntityVillager.PriceInfo(1, 2)
                ));

    }

    public static class GiveHolyWaterForPreciousItems implements EntityVillager.ITradeList {
        public final ItemHolyWater holyWater;
        public final ItemStack itemToBuy;
        @Nullable
        public final EntityVillager.PriceInfo buyInfo;
        @Nullable
        public final EntityVillager.PriceInfo priceInfo;

        public GiveHolyWaterForPreciousItems(ItemHolyWater holyWater, ItemStack stack, @Nullable EntityVillager.PriceInfo buyInfo, @Nullable EntityVillager.PriceInfo priceInfo) {
            this.holyWater = holyWater;
            this.itemToBuy = stack;
            this.buyInfo = buyInfo;
            this.priceInfo = priceInfo;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {

            int buyAmount = 1;
            if (this.buyInfo != null) {
                buyAmount = this.buyInfo.getPrice(random);
            }

            ItemStack itemToBuy = this.itemToBuy.copy();
            itemToBuy.setCount(buyAmount);
            recipeList.add(new MerchantRecipe(this.holyWater.getDefaultInstance(), itemToBuy));
        }

    }

}
