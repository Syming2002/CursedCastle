package com.syming.cursedcastle.datagen;

import com.mojang.serialization.MapCodec;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.enchantment.CCEnchantmentEffectEntity;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.fluid.CCFluidType;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static net.minecraft.network.protocol.game.DebugEntityNameGenerator.getEntityName;

public class CCLangENProvider extends LanguageProvider {

    public CCLangENProvider(PackOutput output, String locale) {
        super(output, CursedCastleMod.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        addENTranslations();
    }


    private void addENTranslations() {
        add("advancements.cursedcastle.root.title", "Welcome to Cursed Castle");
        add("advancements.cursedcastle.root.description", "Join a world with Cursed Castle installed");

        add("advancements.cursedcastle.leatherWhip.title", "A leather weapon");
        add("advancements.cursedcastle.leatherWhip.description", "Get a leather whip");

//        for (IBeeSpecies beeSpecies : SpeciesUtil.getAllBeeSpecies()) {
//            if (beeSpecies.id().toString().contains("cursedcastle")) {
//                add("allele.forestry.bee_species.cursedcastle" + '.' + beeSpecies.id().getPath(), beeSpecies.getSpeciesName());
//            }
//        }

        add("allele.forestry.flower_type.cursedcastle.flower_type_blood", "Blood");
        add("allele.forestry.flower_type.cursedcastle.flower_type_fire", "Fire");
        add("allele.forestry.flower_type.cursedcastle.flower_type_ice", "Ice");

        add("creativetab.cursedcastle_tab", "Cursed Castle");

        for (DeferredHolder<MobEffect, ? extends MobEffect> deferredHolder : CCEffect.EFFECTS.getEntries()) {
            addEffect(deferredHolder, getResourceLocationDisplayName(deferredHolder.getId()));
        }

        for (DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, ? extends MapCodec<? extends EnchantmentEntityEffect>> deferredHolder : CCEnchantmentEffectEntity.ENCHANTMENTS.getEntries()) {
            add("enchantment" + '.' + CursedCastleMod.MOD_ID + '.' + deferredHolder.getId().getPath(), getResourceLocationDisplayName(deferredHolder.getId()));
        }

        for (EntityType<?> type : BuiltInRegistries.ENTITY_TYPE.stream().toList()) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
            if (id != null) {
                if (id.toString().contains(CursedCastleMod.MOD_ID)) {
                    addEntityType(() -> type, getResourceLocationDisplayName(id));

                }
            }
        }

        add("entity.productivebees.blood_bee", "Blood Bee");
        add("entity.productivebees.frozen_shade_bee", "Frozen Shade Bee");
        add("entity.productivebees.heat_shade_bee", "Heat Shade Bee");
        add("entity.productivebees.vampire_bee", "Vampire Bee");

        for (ResourceKey<DamageType> damageType : CCDamageType.DAMAGE_TYPES_LIST) {
            add("entity" + '.' + CursedCastleMod.MOD_ID + "." + damageType.location().getPath(), damageType.location().getPath());
            add("entity" + '.' + CursedCastleMod.MOD_ID + "." + damageType.location().getPath() + '_' + "strength" + '_' + "description", "Has" + ' ' + damageType.location().getPath() + ' ' + "strength");
            add("entity" + '.' + CursedCastleMod.MOD_ID + "." + damageType.location().getPath() + '_' + "weakness" + '_' + "description", "Has" + ' ' + damageType.location().getPath() + ' ' + "weakness");
        }

        for (DeferredHolder<VillagerProfession, ? extends VillagerProfession> villagerProfessions : CCVillager.VILLAGER_PROFESSIONS.getEntries()) {
            add("entity" + '.' + "minecraft" + '.' + "villager" + '.' + CursedCastleMod.MOD_ID + '.' + villagerProfessions.get().name(), getVillagerProfessionName(villagerProfessions));
            add("entity" + '.' + "minecraft" + '.' + "villager" + '.' + villagerProfessions.get().name(), getVillagerProfessionName(villagerProfessions));
        }

        for (DeferredHolder<FluidType, ? extends FluidType> fluidType : CCFluidType.FLUID_TYPES.getEntries()) {
            if (fluidType.getId() != null) {
                add("fluid" + '_' + "type" + '.' + CursedCastleMod.MOD_ID + '.' + fluidType.getId().getPath(), getFluidTypeDisplayName(fluidType));
            }
        }

        add("holy_cross.damage", "Deals %s damage to other mob");
        add("holy_cross.damageArthropods", "Deals %s damage to arthropods mob");
        add("holy_cross.damageSharpness", "Deals %s damage");
        add("holy_cross.damageSmite", "Deals %s damage to undead mob");
        add("holy_cross.knockback", "Deals %s knockback");
        add("holy_cross.fireAspect", "Deals %s seconds of fire to mob");

//        for (CCBeesItemHoneyComb ccBeesItemHoneyComb : CCBeesApicultureItems.CC_BEES_COMB.getItems()) {
//            addItem(ccBeesItemHoneyComb::asItem, getCCHoneyCombDisplayName(ccBeesItemHoneyComb.getEnumHoneyComb()) + ' ' + "Comb");
//        }

        for (DeferredHolder<Item, ? extends Item> item : CCItem.ITEMS.getEntries()) {
            if (item.getKey().registry() != null) {
                addItem(item, getItemDisplayName(item));
            }
        }

        add("throwing_axe.damage" , "Deals %s damage to other mob");
        add("throwing_axe.damageArthropods", "Deals %s damage to arthropods mob");
        add("throwing_axe.damageSharpness", "Deals %s damage");
        add("throwing_axe.damageSmite", "Deals %s damage to undead mob");
        add("throwing_axe.knockback", "Deals %s knockback");
        add("throwing_axe.fireAspect", "Deals %s seconds of fire to mob");
    }

    private String getResourceLocationDisplayName(ResourceLocation id) {
        String raw = id.getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

    private String getFluidTypeDisplayName(DeferredHolder<FluidType, ? extends FluidType> fluidType) {
        String raw = fluidType.getKey().registry().getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

//    private String getCCHoneyCombDisplayName(CCBeesEnumHoneyComb ccBeesEnumHoneyComb) {
//        String raw = ccBeesEnumHoneyComb.getSerializedName();
//        String[] parts = raw.split("_");
//        StringBuilder builder = new StringBuilder();
//
//        for (String part : parts) {
//            if (!builder.isEmpty()) builder.append(' ');
//            builder.append(Character.toUpperCase(part.charAt(0)))
//                    .append(part.substring(1));
//        }
//
//        return builder.toString();
//    }

    private String getItemDisplayName(DeferredHolder<Item, ? extends Item> item) {
        String raw = item.getId().getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

    private String getVillagerProfessionName(DeferredHolder<VillagerProfession, ? extends VillagerProfession> villagerProfession) {
        String raw = villagerProfession.getId().getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

    private String getEffectName(ResourceKey<MobEffect> mobEffect) {
        String raw = mobEffect.registry().getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }
}
