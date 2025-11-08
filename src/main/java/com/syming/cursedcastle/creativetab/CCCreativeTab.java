package com.syming.cursedcastle.creativetab;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CCCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CursedCastleMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CURSEDCASTLE_TAB = CREATIVE_MODE_TAB.register("cursedcastle_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(CCItem.LEATHER_WHIP.get()))
                    .title(Component.translatable("creativetab.cursedcastle_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Materials
                        output.accept(CCItem.LEATHER_STRING.get());

                        // Whips
                        output.accept(CCItem.FLAME_WHIP.get());
                        output.accept(CCItem.LEATHER_WHIP.get());
                        output.accept(CCItem.ROSE_STEM_WHIP.get());
                        output.accept(CCItem.UNDEAD_KILLER_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_CRUSHING_STONE_TIP_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_BULLET_TIP_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_RED_STONE_TIP_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_BLUE_STONE_TIP_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_GREEN_STONE_TIP_WHIP.get());

                        // Whip Tips
                        output.accept(CCItem.BULLET_TIP.get());
                        output.accept(CCItem.CRUSHING_STONE_TIP.get());
                        output.accept(CCItem.RED_STONE_TIP.get());
                        output.accept(CCItem.BLUE_STONE_TIP.get());
                        output.accept(CCItem.GREEN_STONE_TIP.get());

                        // Subweapons
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

                        // Blocks
                        output.accept(CCBlock.DRACULA_CASTLE_COBBLESTONE.get());
                        output.accept(CCBlock.DRACULA_CASTLE_STONE.get());
                        output.accept(CCBlock.DRACULA_CASTLE_STONE_BRICKS.get());
                        output.accept(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get());
                        output.accept(CCBlock.DRACULA_HEAD.get());


                        // Souls
                        for (Soul soul : Soul.values()) {
                            output.accept(CCItem.SOUL_TYPES.get(soul).get());
                        }

                        // Spawn Eggs
                        output.accept(CCItem.BLOOD_SKELETON_SPAWN_EGG.get());
                        output.accept(CCItem.DRACULA_SPAWN_EGG.get());
                        output.accept(CCItem.FAMILIAR_BAT_SPAWN_EGG.get());
                        output.accept(CCItem.FROZEN_SHADE_SPAWN_EGG.get());
                        output.accept(CCItem.GRIM_REAPER_SPAWN_EGG.get());
                        output.accept(CCItem.HARPY_SPAWN_EGG.get());
                        output.accept(CCItem.HEAT_SHADE_SPAWN_EGG.get());
                        output.accept(CCItem.POISON_SHADE_SPAWN_EGG.get());
                        output.accept(CCItem.SKELETON_SPAWN_EGG.get());
                        output.accept(CCItem.THUNDER_SHADE_SPAWN_EGG.get());
                        output.accept(CCItem.VAMPIRE_BAT_SPAWN_EGG.get());

                        // Miscellaneous Items
                        output.accept(CCItem.BLOOD_SKELETON_BONE.get());
                        output.accept(CCItem.BLOOD_BUCKET.get());
                        output.accept(CCItem.SCYTHE_PROJECTILE_ITEM.get());
                        output.accept(CCItem.DEATH_SCYTHE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
