package com.syming.castlevaniacraft.creativetab;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.item.CCItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CCCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CastlevaniaCraftMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MINEVANIA_TAB = CREATIVE_MODE_TAB.register("castlevaniacraft_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(CCItem.LEATHER_WHIP.get()))
                    .title(Component.translatable("creativetab.castlevaniacraft_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(CCItem.FLAME_WHIP.get());
                        output.accept(CCItem.LEATHER_WHIP.get());
                        output.accept(CCItem.UNDEAD_KILLER_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_WHIP.get());
                        output.accept(CCItem.CRUSHING_STONE_VAMPIRE_KILLER_WHIP.get());
                        output.accept(CCItem.BULLET_TIP_VAMPIRE_KILLER_WHIP.get());
                        output.accept(CCItem.CRUSHING_STONE.get());
                        output.accept(CCItem.BULLET_TIP.get());
                        output.accept(CCItem.WOODEN_DAGGER.get());
                        output.accept(CCItem.STONE_DAGGER.get());
                        output.accept(CCItem.IRON_DAGGER.get());
                        output.accept(CCItem.GOLDEN_DAGGER.get());
                        output.accept(CCItem.DIAMOND_DAGGER.get());
                        output.accept(CCItem.NETHERITE_DAGGER.get());
                        output.accept(CCItem.WOODEN_THROWING_AXE.get());
                        output.accept(CCItem.STONE_THROWING_AXE.get());
                        output.accept(CCItem.IRON_THROWING_AXE.get());
                        output.accept(CCItem.GOLDEN_THROWING_AXE.get());
                        output.accept(CCItem.DIAMOND_THROWING_AXE.get());
                        output.accept(CCItem.NETHERITE_THROWING_AXE.get());
                        output.accept(CCItem.WOODEN_HOLY_CROSS.get());
                        output.accept(CCItem.STONE_HOLY_CROSS.get());
                        output.accept(CCItem.IRON_HOLY_CROSS.get());
                        output.accept(CCItem.GOLDEN_HOLY_CROSS.get());
                        output.accept(CCItem.DIAMOND_HOLY_CROSS.get());
                        output.accept(CCItem.NETHERITE_HOLY_CROSS.get());
                        output.accept(CCItem.HOLY_BIBLE.get());
                        output.accept(CCItem.HOLY_WATER.get());
                        output.accept(CCItem.BLOOD_SKELETON_SPAWN_EGG.get());
                        output.accept(CCItem.DRACULA_SPAWN_EGG.get());
                        output.accept(CCBlock.DRACULA_CASTLE_COBBLESTONE.get());
                        output.accept(CCBlock.DRACULA_CASTLE_STONE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
