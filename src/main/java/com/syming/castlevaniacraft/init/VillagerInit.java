package com.syming.castlevaniacraft.init;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.item.projectiles.subweapons.ItemHolyWater;
import com.syming.castlevaniacraft.util.CCReferences;
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

    public static VillagerRegistry.VillagerProfession villagerLeaderPriest;
    public static final String ID_VILLAGER_LEADER_PRIEST = new ResourceLocation(CCReferences.MODID, "leader_priest").toString();
    public static final String TEXTURE_SKIN_LEADER_PRIEST = CCReferences.MODID + ':' + "textures/entity/leader_priest.png";
    public static final String TEXTURE_SKIN_ZOMBIE_LEADER_PRIEST = CCReferences.MODID + ':' + "textures/entity/zombie_leader_priest.png";
    public static VillagerRegistry.VillagerProfession villagerHighPriest;
    public static final String ID_VILLAGER_HIGH_PRIEST = new ResourceLocation(CCReferences.MODID, "high_priest").toString();
    public static final String TEXTURE_SKIN_HIGH_PRIEST = CCReferences.MODID + ':' + "textures/entity/high_priest.png";
    public static final String TEXTURE_SKIN_ZOMBIE_HIGH_PRIEST = CCReferences.MODID + ':' + "textures/entity/zombie_high_priest.png";

    public static void init() {
        villagerLeaderPriest = new VillagerRegistry.VillagerProfession(ID_VILLAGER_LEADER_PRIEST, TEXTURE_SKIN_LEADER_PRIEST, TEXTURE_SKIN_ZOMBIE_LEADER_PRIEST);
        IForgeRegistry<VillagerRegistry.VillagerProfession> leaderPriestProfessions = ForgeRegistries.VILLAGER_PROFESSIONS;
        leaderPriestProfessions.register(villagerLeaderPriest);

        VillagerRegistry.VillagerCareer leaderPriestCareer = new VillagerRegistry.VillagerCareer(villagerLeaderPriest, "leader_priest");
        leaderPriestCareer.addTrade(1,
                new GiveEmeraldForHolyCrossItems( new ItemStack(CCItem.woodenHolyCross), new EntityVillager.PriceInfo(1, 2)));
        leaderPriestCareer.addTrade(2,
                new GiveEmeraldForHolyCrossItems( new ItemStack(CCItem.stoneHolyCross), new EntityVillager.PriceInfo(3, 4)));
        leaderPriestCareer.addTrade(3,
                new GiveEmeraldForHolyCrossItems( new ItemStack(CCItem.ironHolyCross), new EntityVillager.PriceInfo(4, 5)));
        leaderPriestCareer.addTrade(4,
                new GiveEmeraldForHolyCrossItems( new ItemStack(CCItem.goldenHolyCross), new EntityVillager.PriceInfo(3, 4)));
        leaderPriestCareer.addTrade(5,
                new GiveEmeraldForHolyCrossItems( new ItemStack(CCItem.diamondHolyCross), new EntityVillager.PriceInfo(5, 6)));

        villagerHighPriest = new VillagerRegistry.VillagerProfession(ID_VILLAGER_HIGH_PRIEST, TEXTURE_SKIN_HIGH_PRIEST, TEXTURE_SKIN_ZOMBIE_HIGH_PRIEST);
        IForgeRegistry<VillagerRegistry.VillagerProfession> highPriestProfessions = ForgeRegistries.VILLAGER_PROFESSIONS;
        highPriestProfessions.register(villagerHighPriest);

        VillagerRegistry.VillagerCareer highPriestCareer = new VillagerRegistry.VillagerCareer(villagerHighPriest, "high_priest");
        highPriestCareer.addTrade(1,
                new GiveHolyWaterForPreciousItems((ItemHolyWater) CCItem.holyWater,
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

    public static class GiveEmeraldForHolyCrossItems implements EntityVillager.ITradeList {
        public final ItemStack sellingItem;
        @Nullable
        public final EntityVillager.PriceInfo price;

        public GiveEmeraldForHolyCrossItems(ItemStack stack, @Nullable EntityVillager.PriceInfo priceInfo) {
            this.sellingItem = stack;
            this.price = priceInfo;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {

            int sellAmount = 1;
            if (this.price != null) {
                sellAmount = this.price.getPrice(random);
            }

            ItemStack itemToSell = this.sellingItem.copy();
            itemToSell.setCount(sellAmount);

            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD), itemToSell));
        }

    }
}
