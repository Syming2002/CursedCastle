package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.head.CCHeadItem;
import com.syming.cursedcastle.item.projectile.ScytheProjectileItem;
import com.syming.cursedcastle.item.projectile.subweapon.*;
import com.syming.cursedcastle.item.relic.CCRelicItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.item.spellbook.SpellBookItem;
import com.syming.cursedcastle.item.sword.TyrfingItem;
import com.syming.cursedcastle.item.whip.WhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.TipItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CCItemModelProvider extends ItemModelProvider {
    public CCItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CursedCastleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        

        for (DeferredHolder<Item, ? extends Item> itemDeferredHolder : CCItem.ITEMS.getEntries()) {
            Item item = itemDeferredHolder.get();

            if (item instanceof StandingAndWallBlockItem) {
                withExistingParent(itemDeferredHolder.getId().getPath(), mcLoc("item/template_skull"));
            }
            
            if (item instanceof SoulItem) {
                for (int i = 0; i <= Soul.values().length; i++) {
                    registerSoul(itemDeferredHolder.getId());
                }
            }

            if (item instanceof CCHeadItem) continue;

            if (item instanceof SpawnEggItem) {
                withExistingParent(itemDeferredHolder.getId().getPath(), mcLoc("item/template_spawn_egg"));
            }

            if (item instanceof WhipItem) {
                withExistingParent("item/" + itemDeferredHolder.getId().getPath(), mcLoc("item/handheld"))
                        .texture("layer0", modLoc("item/whip/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof TipItem) {
                withExistingParent("item/" + itemDeferredHolder.getId().getPath(), mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/whip/tip/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof DaggerItem) {

                String name = itemDeferredHolder.getId().getPath();

                withExistingParent(name + '_' + "2d", mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath() + '_' + "2d"));
            }

            if (item instanceof HolyBibleItem) {

                String name = itemDeferredHolder.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof HolyCrossItem) {

                String name = itemDeferredHolder.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof HolyWaterItem) {

                String name = itemDeferredHolder.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof ThrowingAxeItem) {

                String name = itemDeferredHolder.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof ScytheProjectileItem) {

                String name = itemDeferredHolder.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath()));
            }

            if (item == CCItem.LEATHER_STRING.get()
                    || item == CCItem.THROWING_BONE.get()
                    || item == CCItem.BLOOD_BONE.get()
                    || item == CCItem.BLOOD_THROWING_BONE.get()
                    || item == CCItem.GOLDEN_BONE.get()
                    || item == CCItem.GOLDEN_THROWING_BONE.get()
                    || item == CCItem.ANIM_ICE_HOLY_CROSS.get()
                    || item == CCItem.ANIM_FIRE_HOLY_CROSS.get()
                    || item == CCItem.ANIM_WOOD_HOLY_CROSS.get()
                    || item == CCItem.ANIM_STONE_HOLY_CROSS.get()
                    || item == CCItem.ANIM_IRON_HOLY_CROSS.get()
                    || item == CCItem.ANIM_GOLD_HOLY_CROSS.get()
                    || item == CCItem.ANIM_DIAMOND_HOLY_CROSS.get()
                    || item == CCItem.ANIM_NETHERITE_HOLY_CROSS.get()
                    || item == CCItem.ANIM_WOOD_THROWING_AXE.get()
                    || item == CCItem.ANIM_STONE_THROWING_AXE.get()
                    || item == CCItem.ANIM_IRON_THROWING_AXE.get()
                    || item == CCItem.ANIM_GOLD_THROWING_AXE.get()
                    || item == CCItem.ANIM_DIAMOND_THROWING_AXE.get()
                    || item == CCItem.ANIM_NETHERITE_THROWING_AXE.get()
                    || item == CCItem.ANIM_SCYTHE_PROJECTILE_ITEM.get()) {

                String name = itemDeferredHolder.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof TyrfingItem) {
                withExistingParent("item/" + itemDeferredHolder.getId().getPath(), mcLoc("item/handheld"))
                        .texture("layer0", modLoc("item/sword/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof CCRelicItem) {
                withExistingParent("item/" + itemDeferredHolder.getId().getPath(), mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/relic/" + itemDeferredHolder.getId().getPath()));
            }

            if (item instanceof BucketItem) {
                withExistingParent("blood_bucket", ResourceLocation.fromNamespaceAndPath("neoforge", "item/bucket")).customLoader(DynamicFluidContainerModelBuilder::begin)
                        .fluid(CCFluid.SOURCE_BLOOD.get());

                withExistingParent("holy_water_bucket", ResourceLocation.fromNamespaceAndPath("neoforge", "item/bucket")).customLoader(DynamicFluidContainerModelBuilder::begin)
                        .fluid(CCFluid.SOURCE_HOLY_WATER.get());
            }

            if (item == CCItem.DEATH_SCYTHE.get()) {
                withExistingParent("item/" + itemDeferredHolder.getId().getPath(), mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemDeferredHolder.getId().getPath()))
                        .transforms()
                        .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                        .rotation(0, -90, 55)
                        .translation(0, 4.0F, 0.5F)
                        .scale(1.25F, 1.25F, 1.25F)
                        .end()
                        .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                        .rotation(0, 90, -55)
                        .translation(0, 4.0F, 0.5F)
                        .scale(1.25F, 1.25F, 1.25F)
                        .end()
                        .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                        .rotation(0, -90, 25)
                        .translation(1.13F, 3.2F, 1.13F)
                        .scale(1.25F, 1.25F, 1.25F)
                        .end()
                        .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                        .rotation(0, 90, -25)
                        .translation(1.13F, 3.2F, 1.13F)
                        .scale(1.25F, 1.25F, 1.25F);
            }

            if (item instanceof SpellBookItem) {
                for (int i = 0; i <= SpellBookElementType.values().length; i++) {
                    withExistingParent(itemDeferredHolder.getId().getPath(), mcLoc("item/generated"))
                            .texture("layer0", modLoc("item/spellbook/" + itemDeferredHolder.getId().getPath()));
                }
            }
        }

        withExistingParent(CursedCastleMod.MOD_ID + ':' + BuiltInRegistries.BLOCK.getKey(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get()).getPath(), modLoc("block/" + BuiltInRegistries.BLOCK.getKey(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get()).getPath()));
    }

    private void registerSoul(ResourceLocation id) {
        withExistingParent(id.getPath(), modLoc("item/soul"));
    }
}
