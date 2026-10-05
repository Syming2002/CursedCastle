package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.compat.forestry.CCBeesApicultureItems;
import com.syming.cursedcastle.compat.forestry.CCBeesEnumHoneyComb;
import com.syming.cursedcastle.compat.forestry.CCBeesItemHoneyComb;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.fluid.CCFluidType;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.potion.CCPotion;
import forestry.api.apiculture.genetics.IBeeSpecies;
import forestry.core.utils.SpeciesUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TippedArrowItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

        for (RegistryObject<Block> block : CCBlock.BLOCKS.getEntries()) {
            if (block.get() instanceof LiquidBlock liquidBlock) {
                if (block.getId() != null) {
                    addBlock(liquidBlock.builtInRegistryHolder(), getResourceLocationDisplayName(block.getId()));
                }
            }
        }

        for (IBeeSpecies beeSpecies : SpeciesUtil.getAllBeeSpecies()) {
            if (beeSpecies.id().toString().contains(CursedCastleMod.MOD_ID)) {
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

        for (RegistryObject<VillagerProfession> villagerProfessions : CCVillager.VILLAGER_PROFESSIONS.getEntries()) {
            if (villagerProfessions.getId() != null) {
                add("entity" + '.' + "minecraft" + '.' + "villager" + '.' + CursedCastleMod.MOD_ID + '.' + villagerProfessions.get().name(), getResourceLocationDisplayName(villagerProfessions.getId()));
                add("entity" + '.' + "minecraft" + '.' + "villager" + '.' + villagerProfessions.get().name(), getResourceLocationDisplayName(villagerProfessions.getId()));
            }
        }

        for (RegistryObject<FluidType> fluidType : CCFluidType.FLUID_TYPES.getEntries()) {
            if (fluidType.getId() != null) {
                add("fluid" + '_' + "type" + '.' + CursedCastleMod.MOD_ID + '.' + fluidType.getId().getPath(), getResourceLocationDisplayName(fluidType.getId()));
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
                addItem(item, getResourceLocationDisplayName(item.getId()));
            }
        }

        for (RegistryObject<Potion> potion : CCPotion.POTIONS.getEntries()) {
            for (Item item : BuiltInRegistries.ITEM) {
                if (potion.getId() != null) {
                    if (item instanceof PotionItem potionItem) {
                        add("item.minecraft." + potionItem.toString().replace("minecraft:", "") + ".effect" + '.' + potion.getId().getPath(), getResourceLocationDisplayName(potion.getId()));
                    }

                    if (item instanceof TippedArrowItem) {
                        add("item.minecraft.tipped_arrow.effect" + '.' + potion.getId().getPath(), getResourceLocationDisplayName(potion.getId()));
                    }
                }
            }
        }

        add("key.categories." + CursedCastleMod.MOD_ID, "Cursed Castle");
        add("key." + CursedCastleMod.MOD_ID + ".bullet_soul_key", "Use Bullet Type Soul");
        add("key." + CursedCastleMod.MOD_ID + ".guardian_soul_key", "Use Guardian Type Soul");

        add("throwing_axe.damage" , "Deals %s damage to other mob");
        add("throwing_axe.damageArthropods", "Deals %s damage to arthropods mob");
        add("throwing_axe.damageSharpness", "Deals %s damage");
        add("throwing_axe.damageSmite", "Deals %s damage to undead mob");
        add("throwing_axe.knockback", "Deals %s knockback");
        add("throwing_axe.fireAspect", "Deals %s seconds of fire to mob");

        add("tooltip.cursedcastle.potion.holy", "May God be with you !");
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
}
