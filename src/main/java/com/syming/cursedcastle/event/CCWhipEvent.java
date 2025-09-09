package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.item.whip.FlameWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerRedStoneTipWhipItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCWhipEvent {

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos blockPos = event.getPos();

        BlockPos firePos = blockPos.relative(event.getFace());

        if (!level.isClientSide()) {
            if (player.getMainHandItem().getItem() instanceof VampireKillerRedStoneTipWhipItem || player.getMainHandItem().getItem() instanceof FlameWhipItem) {
                if (level.getBlockState(firePos).isAir()) {
                    level.setBlock(firePos, Blocks.FIRE.defaultBlockState(), 3);
                }
            }
        }
    }

}
