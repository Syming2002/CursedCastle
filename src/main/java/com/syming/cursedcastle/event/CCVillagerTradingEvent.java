package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.tag.CCTag;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCVillagerTradingEvent {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        if (event.getType() == CCVillager.HIGH_PRIEST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(CCItem.HOLY_WATER.get(), 1),
                    new ItemStack(Items.GOLD_INGOT, pRandom.nextInt(5) + 1),
                    3, 10, 0.0F));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(CCItem.HOLY_BIBLE.get(), 1),
                    new ItemStack(Items.DIAMOND, pRandom.nextInt(3) + 1),
                    2, 20, 0.0F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(CCItem.HOLY_WATER_BUCKET.get(), 1),
                    new ItemStack(Items.EMERALD, pRandom.nextInt(3) + 1),
                    1, 50, 0.0F));
        }
    }
}
