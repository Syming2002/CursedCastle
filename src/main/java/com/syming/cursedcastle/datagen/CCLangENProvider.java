package com.syming.cursedcastle.datagen;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.compat.forestry.CCBeesApicultureItems;
import com.syming.cursedcastle.compat.forestry.CCBeesEnumHoneyComb;
import com.syming.cursedcastle.compat.forestry.CCBeesItemHoneyComb;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.fluid.CCFluidType;
import com.syming.cursedcastle.item.CCItem;
import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.common.entity.bee.ConfigurableBee;
import cy.jdkdigital.productivebees.common.entity.bee.ProductiveBee;
import cy.jdkdigital.productivebees.datagen.BeeProvider;
import cy.jdkdigital.productivebees.init.ModEntities;
import forestry.api.apiculture.genetics.IBeeSpecies;
import forestry.core.utils.SpeciesUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.io.File;
import java.io.FileReader;

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

        for (IBeeSpecies beeSpecies : SpeciesUtil.getAllBeeSpecies()) {
            if (beeSpecies.id().toString().contains("cursedcastle")) {
                add("allele.forestry.bee_species.cursedcastle" + '.' + beeSpecies.id().getPath(), beeSpecies.getSpeciesName());
            }
        }

        add("allele.forestry.flower_type.cursedcastle.flower_type_blood", "Blood");
        add("allele.forestry.flower_type.cursedcastle.flower_type_fire", "Fire");
        add("allele.forestry.flower_type.cursedcastle.flower_type_ice", "Ice");

        add("creativetab.cursedcastle_tab", "Cursed Castle");

        addEffect(CCEffect.FREEZE, "Freeze");
        addEffect(CCEffect.HOLY, "Holy");

        addEnchantment(CCEnchantment.BLOOD_SKELETON_KILLER, "Blood Skeleton Killer");

        for (EntityType<?> type : ForgeRegistries.ENTITY_TYPES.getValues()) {
            ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(type);
            if (id != null) {
                if (id.toString().contains("cursedcastle")) {
                    addEntityType(() -> type, getEntityDisplayName(id));

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

        for (RegistryObject<VillagerProfession> villagerProfessions : CCVillager.VILLAGER_PROFESSIONS.getEntries()) {
            add("entity" + '.' + "minecraft" + '.' + "villager" + '.' + CursedCastleMod.MOD_ID + '.' + villagerProfessions.get().name(), getVillagerProfessionName(villagerProfessions));
            add("entity" + '.' + "minecraft" + '.' + "villager" + '.' + villagerProfessions.get().name(), getVillagerProfessionName(villagerProfessions));
        }

        for (RegistryObject<FluidType> fluidType : CCFluidType.FLUID_TYPES.getEntries()) {
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

        for (CCBeesItemHoneyComb ccBeesItemHoneyComb : CCBeesApicultureItems.CC_BEES_COMB.getItems()) {
            addItem(ccBeesItemHoneyComb.builtInRegistryHolder(), getCCHoneyCombDisplayName(ccBeesItemHoneyComb.getEnumHoneyComb()) + ' ' + "Comb");
        }

        for (RegistryObject<Item> item : CCItem.ITEMS.getEntries()) {
            if (item.getId() != null) {
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

    private String getEntityDisplayName(ResourceLocation id) {
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

    private String getFluidTypeDisplayName(RegistryObject<FluidType> fluidType) {
        String raw = fluidType.getId().getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

    private String getCCHoneyCombDisplayName(CCBeesEnumHoneyComb ccBeesEnumHoneyComb) {
        String raw = ccBeesEnumHoneyComb.getSerializedName();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

    private String getItemDisplayName(RegistryObject<Item> item) {
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

    private String getVillagerProfessionName(RegistryObject<VillagerProfession> villagerProfession) {
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
}
