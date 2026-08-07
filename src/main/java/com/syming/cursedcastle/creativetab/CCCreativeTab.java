package com.syming.cursedcastle.creativetab;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.compat.forestry.CCBeesApicultureItems;
import com.syming.cursedcastle.compat.forestry.CCBeesItemHoneyComb;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.CCSpawnEggItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.TipType;
import forestry.api.apiculture.genetics.IBeeSpecies;
import forestry.api.genetics.ILifeStage;
import forestry.core.utils.SpeciesUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
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
                        output.accept(CCItem.HOLY_WHIP.get());
                        output.accept(CCItem.ICE_WHIP.get());
                        output.accept(CCItem.LEATHER_WHIP.get());
                        output.accept(CCItem.ROSE_STEM_WHIP.get());
                        output.accept(CCItem.UNDEAD_KILLER_WHIP.get());
                        output.accept(CCItem.VAMPIRE_KILLER_WHIP.get());

                        // Whip Tips
                        for (TipType tipType : TipType.values()) {
                            output.accept(CCItem.TIP_TYPES.get(tipType).get());
                            output.accept(CCItem.VAMPIRE_KILLER_ELEMENTAL_WHIP.get(tipType).get());
                        }

                        // Subweapons
                        for (Tier tier : Tiers.values()) {
                            output.accept(CCItem.DAGGER_ITEM_TYPES.get(tier).get());
                            output.accept(CCItem.HOLY_CROSS_ITEM_TYPES.get(tier).get());
                            output.accept(CCItem.THROWING_AXE_ITEM_TYPES.get(tier).get());
                        }

                        output.accept(CCItem.HOLY_BIBLE.get());
                        output.accept(CCItem.HOLY_WATER.get());

                        // Blocks
                        output.accept(CCBlock.CHURCH_ALTAR.get());

                        output.accept(CCBlock.DRACULA_CASTLE_COBBLESTONE.get());
                        output.accept(CCBlock.DRACULA_CASTLE_STONE.get());
                        output.accept(CCBlock.DRACULA_CASTLE_STONE_BRICKS.get());
                        output.accept(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get());
                        output.accept(CCBlock.DRACULA_HEAD.get());

                        // Crucifixes Blocks
                        for (WoodType woodType : WoodType.values().toList()) {
                            if (woodType != null) {
                                if (woodType == WoodType.OAK
                                        || woodType == WoodType.SPRUCE
                                        || woodType == WoodType.BIRCH
                                        || woodType == WoodType.JUNGLE
                                        || woodType == WoodType.ACACIA
                                        || woodType == WoodType.DARK_OAK
                                        || woodType == WoodType.CRIMSON
                                        || woodType == WoodType.WARPED
                                        || woodType == WoodType.MANGROVE
                                        || woodType == WoodType.CHERRY
                                        || woodType == WoodType.BAMBOO) {
                                    output.accept(CCBlock.CRUCIFIX_BLOCK_TYPES.get(woodType).get());
                                }
                            }
                        }

                        // Relics
                        output.accept(CCItem.FROZEN_HEART.get());
                        output.accept(CCItem.SPIDER_CLAW.get());

                        // Souls
                        for (Soul soul : Soul.values()) {
                            output.accept(CCItem.SOUL_TYPES.get(soul).get());
                        }

                        // Spell Books
                        for (SpellBookElementType spellBookElementType : SpellBookElementType.values()) {
                            output.accept(CCItem.SPELL_BOOK_TYPES.get(spellBookElementType).get());
                        }

                        // Spawn Eggs
                        for (RegistryObject<Item> itemRegistryObject : CCItem.ITEMS.getEntries()) {
                            if (itemRegistryObject.get() instanceof CCSpawnEggItem spawnEggItem) {
                                output.accept(spawnEggItem);
                            }
                        }

                        // Swords
                        output.accept(CCItem.TYRFING.get());

                        // Miscellaneous Items
                        output.accept(CCItem.BLOOD_BONE.get());
                        output.accept(CCItem.BLOOD_BUCKET.get());
                        output.accept(CCItem.GOLDEN_BONE.get());
                        output.accept(CCItem.HOLY_WATER_BUCKET.get());
                        output.accept(CCItem.SCYTHE_PROJECTILE_ITEM.get());
                        output.accept(CCItem.DEATH_SCYTHE.get());

                        output.accept(CCItem.ANIM_FIRE_HOLY_CROSS.get());
                        output.accept(CCItem.ANIM_ICE_HOLY_CROSS.get());

                        if (ModList.get().isLoaded("forestry")) {
                            // Bees
                            for (ILifeStage stage : SpeciesUtil.BEE_TYPE.get().getLifeStages()) {
                                for (IBeeSpecies species : SpeciesUtil.getAllBeeSpecies()) {
                                    if (species.id().toString().contains("cursedcastle")) {
                                        output.accept(species.createStack(stage));
                                    }
                                }
                            }

                            // Combs
                            for (CCBeesItemHoneyComb comb : CCBeesApicultureItems.CC_BEES_COMB.getItems()) {
                                output.accept(comb);
                            }
                        }
                    })

                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
