package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.CrucifixBlock;
import com.syming.cursedcastle.compat.forestry.CCBeesApicultureItems;
import com.syming.cursedcastle.compat.forestry.CCBeesEnumHoneyComb;
import com.syming.cursedcastle.compat.forestry.CCBeesItemHoneyComb;
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
import forestry.api.ForestryConstants;
import forestry.core.items.ItemForestry;
import forestry.core.models.FluidContainerModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.client.model.DynamicFluidContainerModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCItemModelProvider extends ItemModelProvider {
    public CCItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CursedCastleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(CCItem.DRACULA_HEAD.getId().getPath(), mcLoc("item/template_skull"));

        for (RegistryObject<Item> itemRegistryObject : CCItem.ITEMS.getEntries()) {
            Item item = itemRegistryObject.get();
            if (item instanceof SoulItem) {
                for (Soul soulType : Soul.values()) {
                    registerSoul(soulType);
                }
            }

            if (item instanceof CCHeadItem) continue;

            if (item instanceof ForgeSpawnEggItem) {
                withExistingParent(itemRegistryObject.getId().getPath(), mcLoc("item/template_spawn_egg"));
            }

            if (item instanceof WhipItem) {
                withExistingParent("item/" + itemRegistryObject.getId().getPath(), mcLoc("item/handheld"))
                        .texture("layer0", modLoc("item/whip/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof TipItem) {
                withExistingParent("item/" + itemRegistryObject.getId().getPath(), mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/whip/tip/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof DaggerItem) {

                String name = itemRegistryObject.getId().getPath();

                withExistingParent(name + '_' + "2d", mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath() + '_' + "2d"));
            }

            if (item instanceof HolyBibleItem) {

                String name = itemRegistryObject.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof HolyCrossItem) {

                String name = itemRegistryObject.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof HolyWaterItem) {

                String name = itemRegistryObject.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof ThrowingAxeItem) {

                String name = itemRegistryObject.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof ScytheProjectileItem) {

                String name = itemRegistryObject.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath()));
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
                    || item == CCItem.ANIM_NETHERITE_THROWING_AXE.get()) {

                String name = itemRegistryObject.getId().getPath();

                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof TyrfingItem) {
                withExistingParent("item/" + itemRegistryObject.getId().getPath(), mcLoc("item/handheld"))
                        .texture("layer0", modLoc("item/sword/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof CCRelicItem) {
                withExistingParent("item/" + itemRegistryObject.getId().getPath(), mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/relic/" + itemRegistryObject.getId().getPath()));
            }

            if (item instanceof BucketItem) {
                withExistingParent("blood_bucket", new ResourceLocation("forge", "item/bucket")).customLoader(DynamicFluidContainerModelBuilder::begin)
                        .fluid(CCFluid.SOURCE_BLOOD.get());

                withExistingParent("holy_water_bucket", new ResourceLocation("forge", "item/bucket")).customLoader(DynamicFluidContainerModelBuilder::begin)
                        .fluid(CCFluid.SOURCE_HOLY_WATER.get());
            }

            if (item == CCItem.DEATH_SCYTHE.get()) {
                withExistingParent("item/" +  itemRegistryObject.getId().getPath(), mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + itemRegistryObject.getId().getPath()))
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
                for (SpellBookElementType spellBookElementType : SpellBookElementType.values()) {
                    withExistingParent(CCItem.SPELL_BOOK_TYPES.get(spellBookElementType).getId().getPath(), mcLoc("item/generated"))
                            .texture("layer0", modLoc("item/spellbook/" + CCItem.SPELL_BOOK_TYPES.get(spellBookElementType).getId().getPath()));
                }
            }
        }

        withExistingParent(CursedCastleMod.MOD_ID + ':' + ForgeRegistries.BLOCKS.getKey(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get()).getPath(), modLoc("block/" + ForgeRegistries.BLOCKS.getKey(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get()).getPath()));
    }

    private void registerSoul(Soul soul) {
        withExistingParent(CCItem.SOUL_TYPES.get(soul).getId().getPath(), modLoc("item/soul"));
    }
}
