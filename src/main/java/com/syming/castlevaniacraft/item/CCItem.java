package com.syming.castlevaniacraft.item;

import com.syming.castlevaniacraft.item.armors.*;
import com.syming.castlevaniacraft.item.projectiles.ItemAnimScytheProjectile;
import com.syming.castlevaniacraft.item.projectiles.ItemScytheProjectile;
import com.syming.castlevaniacraft.item.projectiles.ItemThrowingBone;
import com.syming.castlevaniacraft.item.projectiles.subweapons.*;
import com.syming.castlevaniacraft.item.projectiles.subweapons.holycross.*;
import com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe.*;
import com.syming.castlevaniacraft.item.souls.ItemBulletSoul;
import com.syming.castlevaniacraft.item.souls.ItemGuardianSoul;
import com.syming.castlevaniacraft.item.spellbooks.ItemFireBook;
import com.syming.castlevaniacraft.item.spellbooks.ItemSpellBook;
import com.syming.castlevaniacraft.item.whips.*;
import com.syming.castlevaniacraft.item.whips.attachments.*;
import com.syming.castlevaniacraft.item.whips.vampirekiller.*;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class CCItem {

    public static  List<Item> ITEMS = new ArrayList<>();

    // Materials Armors
    public static ItemArmor.ArmorMaterial DRACULA_TUNIC_ARMOR = EnumHelper.addArmorMaterial(
            "dracula_tunic_material",
            CCReferences.MODID + ":dracula_tunic",
            10,
            new int[] {0, 0, 20, 0},
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0.99F);
    public static ItemArmor.ArmorMaterial LEON_BELMONT_ARMOR = EnumHelper.addArmorMaterial(
            "leon_belmont_armor_material",
            CCReferences.MODID + ":leon_belmont",
            10,
            new int[] {4, 1, 3, 0},
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0.125F);
    public static ItemArmor.ArmorMaterial JUSTE_BELMONT_ARMOR = EnumHelper.addArmorMaterial(
            "juste_belmont_armor_material",
            CCReferences.MODID + ":juste_belmont",
            10,
            new int[] {4, 1, 3, 0},
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0.125F);
    public static ItemArmor.ArmorMaterial OLROX_SUIT_ARMOR = EnumHelper.addArmorMaterial(
            "olrox_suit_material",
            CCReferences.MODID + ":olrox_suit",
            8,
            new int[] {0, 0, 16, 0},
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0.99F);
    public static ItemArmor.ArmorMaterial RICHTER_BELMONT_ARMOR = EnumHelper.addArmorMaterial(
            "richter_belmont_armor_material",
            CCReferences.MODID + ":richter_belmont",
            10,
            new int[] {2, 1, 3, 0},
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0.125F);
    public static ItemArmor.ArmorMaterial SIMON_BELMONT_ARMOR = EnumHelper.addArmorMaterial(
            "simon_belmont_armor_material",
            CCReferences.MODID + ":simon_belmont",
            10,
            new int[] {2, 1, 2, 0},
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0.25F);

    // Materials
//    public static  Item.ToolMaterial LEATHER_WHIP_MATERIAL = EnumHelper.addToolMaterial("leather_whip_material", 1, 100, 2.0F, 2.2F, 5).setRepairItem(new ItemStack(Items.LEATHER));
//    public static  Item.ToolMaterial STEEL_WHIP_MATERIAL = EnumHelper.addToolMaterial("steel_whip_material", 3, 400, 2.0F, 3.5F, 5);
//    public static  Item.ToolMaterial ROSE_STEM_WHIP_MATERIAL = EnumHelper.addToolMaterial("rose_stem_whip_material", 1, 50, 1.0F, 6.0F, 5).setRepairItem(new ItemStack(Blocks.VINE));
//    public static  Item.ToolMaterial MEDUSA_WHIP_MATERIAL = EnumHelper.addToolMaterial("medusa_whip_material", 2, 350, 2.0F, 3.0F, 5);
//    public static  Item.ToolMaterial NEBULA_MATERIAL = EnumHelper.addToolMaterial("nebula_material", 1, 200, 1.5F, 3.7F, 5);
//    public static  Item.ToolMaterial FLAME_WHIP_MATERIAL = EnumHelper.addToolMaterial("flame_whip_material", 1, 75, 1.0F, 6.5F, 5);
//    public static  Item.ToolMaterial JET_BLACK_WHIP_MATERIAL = EnumHelper.addToolMaterial("jet_black_whip_material", 2, 150, 2.0F, 4.4F, 5);
//    public static  Item.ToolMaterial UNDEAD_KILLER_MATERIAL = EnumHelper.addToolMaterial("undead_killer_material", 1, 200, 1.5F, 7.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_material", 3, 700, 2.5F, 8.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_CRUSHING_STONE_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_crushing_stone_attachment_material", 3, 700, 5.0F, 3.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_STEEL_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_steel_attachment_material", 3, 1100, 2.0F, 18.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_PLATINUM_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_platinum_attachment_material", 3, 1300, 4.0F, 28.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_ENERGY_CIRCLE_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_energy_circle_attachment_material", 3, 800, 3.0F, 8.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_BULLET_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_bullet_attachment_material", 3, 900, 3.0F, 13.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_RED_STONE_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_red_stone_attachment_material", 3, 1000, 3.0F, 10.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_BLUE_STONE_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_blue_stone_attachment_material", 3, 1000, 3.0F, 10.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_YELLOW_STONE_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_yellow_stone_attachment_material", 3, 1000, 3.0F, 10.0F, 5);
//    public static  Item.ToolMaterial VAMPIRE_KILLER_GREEN_STONE_ATTACHMENT_MATERIAL = EnumHelper.addToolMaterial("vampire_killer_green_stone_attachment_material", 3, 1000, 3.0F, 10.0F, 5);



    // Whips
    public static Item leatherWhip = new ItemLeatherWhip("leather_whip", 2.0F, -3.5F, 12.0F);
    public static Item steelWhip = new ItemSteelWhip("steel_whip", 4.0F, -3.5F, 12.0F);
    public static Item roseStemWhip = new ItemRoseStemWhip("rose_stem_whip", 6.0F, -3.5F, 8.0F);
    public static Item medusaWhip = new ItemMedusaWhip("medusa_whip", 3.0F, -3.5F, 12.0F);
    public static Item nebula = new ItemNebula("nebula", 3.7F, -3.5F, 20.0F);
    public static Item flameWhip = new ItemFlameWhip("flame_whip", 6.5F, -3.5F, 12.0F);
    public static Item jetBlackWhip = new ItemJetBlackWhip("jet_black_whip", 4.4F, -3.5F, 12.0F);
    public static Item undeadKiller = new ItemUndeadKiller("undead_killer", 7.0F, -3.5F, 16.0F);
    public static Item vampireKiller = new ItemVampireKiller("vampire_killer_whip", 8.0F, -3.5F, 12.0F);
    public static Item vampireKillerCrushingStoneAttachment = new ItemVampireKillerCrushingStoneAttachment("crushing_stone_vampire_killer_whip", 3.0F, -3.5F, 12.0F, 12.0F);
    public static Item vampireKillerSteelAttachment = new ItemVampireKillerSteelAttachment("steel_vampire_killer_whip", 18.0F, -3.5F, 12.0F);
    public static Item vampireKillerPlatinumAttachment = new ItemVampireKillerPlatinumAttachment("platinum_vampire_killer_whip", 28.0F, -3.5F, 12.0F);
    public static Item vampireKillerEnergyCircleAttachment = new ItemVampireKillerEnergyCircleAttachment("energy_circle_vampire_killer_whip", 8.0F, -3.5F, 12.0F);
    public static Item vampireKillerBulletAttachment = new ItemVampireKillerBulletAttachment("bullet_tip_vampire_killer_whip", 13.0F, -3.5F, 12.0F);
    public static Item vampireKillerRedStoneAttachment = new ItemVampireKillerRedStoneAttachment("red_stone_vampire_killer_whip", 10.0F, -3.5F, 12.0F);
    public static Item vampireKillerBlueStoneAttachment = new ItemVampireKillerBlueStoneAttachment("blue_stone_vampire_killer_whip", 10.0F, -3.5F, 12.0F);
    public static Item vampireKillerYellowStoneAttachment = new ItemVampireKillerYellowStoneAttachment("yellow_stone_vampire_killer_whip", 10.0F, -3.5F, 12.0F);
    public static Item vampireKillerGreenStoneAttachment = new ItemVampireKillerGreenStoneAttachment("green_stone_vampire_killer_whip", 10.0F, -3.5F, 12.0F);


    // Whips Attachments
    public static Item crushingStoneAttachment = new ItemCrushingStoneAttachment("crushing_stone");
    public static Item steelAttachment = new ItemSteelAttachment("steel_attachment");
    public static Item platinumAttachment = new ItemPlatinumAttachment("platinum_attachment");
    public static Item energyCircleAttachment = new ItemEnergyCircleAttachment("energy_circle_attachment");
    public static Item bulletAttachment = new ItemBulletAttachment("bullet_tip");
    public static Item redStoneAttachment = new ItemRedStoneAttachment("red_stone_attachment");
    public static Item blueStoneAttachment = new ItemBlueStoneAttachment("blue_stone_attachment");
    public static Item yellowStoneAttachment = new ItemYellowStoneAttachment("yellow_stone_attachment");
    public static Item greenStoneAttachment = new ItemGreenStoneAttachment("green_stone_attachment");


    // Soul
    public static Item bulletSoul = new ItemBulletSoul("bullet_soul");

    public static Item guardianSoul = new ItemGuardianSoul("guardian_soul");

    // SubWeapons
    public static ItemHolyCross ironHolyCross = new ItemHolyCross("iron_holy_cross", Item.ToolMaterial.IRON);
    public static ItemHolyCross woodenHolyCross = new ItemHolyCross("wooden_holy_cross", Item.ToolMaterial.WOOD);
    public static ItemHolyCross stoneHolyCross = new ItemHolyCross("stone_holy_cross", Item.ToolMaterial.STONE);
    public static ItemHolyCross goldenHolyCross = new ItemHolyCross("golden_holy_cross", Item.ToolMaterial.GOLD);
    public static ItemHolyCross diamondHolyCross = new ItemHolyCross("diamond_holy_cross", Item.ToolMaterial.DIAMOND);

    public static Item holyWater = new ItemHolyWater("holy_water");

    public static ItemDagger woodenDagger = new ItemDagger("wooden_dagger", Item.ToolMaterial.WOOD);
    public static ItemDagger stoneDagger = new ItemDagger("stone_dagger", Item.ToolMaterial.STONE);
    public static ItemDagger ironDagger = new ItemDagger("iron_dagger", Item.ToolMaterial.IRON);
    public static ItemDagger goldenDagger = new ItemDagger("golden_dagger", Item.ToolMaterial.GOLD);
    public static ItemDagger diamondDagger = new ItemDagger("diamond_dagger", Item.ToolMaterial.DIAMOND);

    public static ItemThrowingAxe ironThrowingAxe = new ItemThrowingAxe("iron_throwing_axe", Item.ToolMaterial.IRON);
    public static ItemThrowingAxe woodenThrowingAxe = new ItemThrowingAxe("wooden_throwing_axe", Item.ToolMaterial.WOOD);
    public static ItemThrowingAxe stoneThrowingAxe = new ItemThrowingAxe("stone_throwing_axe", Item.ToolMaterial.STONE);
    public static ItemThrowingAxe goldenThrowingAxe = new ItemThrowingAxe("golden_throwing_axe", Item.ToolMaterial.GOLD);
    public static ItemThrowingAxe diamondThrowingAxe = new ItemThrowingAxe("diamond_throwing_axe", Item.ToolMaterial.DIAMOND);

    public static Item holyBible = new ItemHolyBible("holy_bible");

    // Projectiles
    public static Item scytheProjectile = new ItemScytheProjectile("scythe_projectile");
    public static Item throwingBone = new ItemThrowingBone("throwing_bone");

    // SubWeapons Entity Throwable
    public static Item animIronHolyCross = new ItemAnimIronHolyCross("anim_iron_holy_cross");
    public static Item animWoodenHolyCross = new ItemAnimWoodenHolyCross("anim_wooden_holy_cross");
    public static Item animStoneHolyCross = new ItemAnimWoodenHolyCross("anim_stone_holy_cross");
    public static Item animGoldenHolyCross = new ItemAnimWoodenHolyCross("anim_golden_holy_cross");
    public static Item animDiamondHolyCross = new ItemAnimWoodenHolyCross("anim_diamond_holy_cross");

    public static Item animFireHolyCross = new ItemAnimFireHolyCross("anim_fire_holy_cross");

    public static Item animIronThrowingAxe = new ItemAnimIronThrowingAxe("anim_iron_throwing_axe");
    public static Item animWoodenThrowingAxe = new ItemAnimWoodThrowingAxe("anim_wooden_throwing_axe");
    public static Item animStoneThrowingAxe = new ItemAnimStoneThrowingAxe("anim_stone_throwing_axe");
    public static Item animGoldenThrowingAxe = new ItemAnimGoldThrowingAxe("anim_golden_throwing_axe");
    public static Item animDiamondThrowingAxe = new ItemAnimDiamondThrowingAxe("anim_diamond_throwing_axe");

    // Projectiles Entity Throwable
    public static Item animScytheProjectile = new ItemAnimScytheProjectile("anim_scythe_projectile");

    // Special Weapons
    public static Item deathScythe = new ItemDeathScythe("death_scythe");

    // Spell Books

    public static Item fireBook = new ItemFireBook("fire_book");

    // Armors
    public static Item draculaTunic = new ItemDraculaTunicArmor("dracula_tunic", DRACULA_TUNIC_ARMOR, 1, EntityEquipmentSlot.CHEST);

    public static Item justeBelmontChest = new ItemJusteBelmontArmor("juste_belmont_chest", JUSTE_BELMONT_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static Item justeBelmontLeggings = new ItemJusteBelmontArmor("juste_belmont_leggings", JUSTE_BELMONT_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static Item justeBelmontBoots = new ItemJusteBelmontArmor("juste_belmont_boots", JUSTE_BELMONT_ARMOR, 2, EntityEquipmentSlot.FEET);

    public static Item leonBelmontChest = new ItemLeonBelmontArmor("leon_belmont_chest", LEON_BELMONT_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static Item leonBelmontLeggings = new ItemLeonBelmontArmor("leon_belmont_leggings", LEON_BELMONT_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static Item leonBelmontBoots = new ItemLeonBelmontArmor("leon_belmont_boots", LEON_BELMONT_ARMOR, 1, EntityEquipmentSlot.FEET);

    public static Item olroxSuit = new ItemOlroxSuitArmor("olrox_suit", OLROX_SUIT_ARMOR, 1, EntityEquipmentSlot.CHEST);

    public static Item richterBelmontChest = new ItemRichterBelmontArmor("richter_belmont_chest", RICHTER_BELMONT_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static Item richterBelmontLeggings = new ItemRichterBelmontArmor("richter_belmont_leggings", RICHTER_BELMONT_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static Item richterBelmontBoots = new ItemRichterBelmontArmor("richter_belmont_boots", RICHTER_BELMONT_ARMOR, 2, EntityEquipmentSlot.FEET);

    public static Item simonBelmontChest = new ItemSimonBelmontArmor("simon_belmont_chest", SIMON_BELMONT_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static Item simonBelmontLeggings = new ItemSimonBelmontArmor("simon_belmont_leggings", SIMON_BELMONT_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static Item simonBelmontBoots = new ItemSimonBelmontArmor("simon_belmont_boots", SIMON_BELMONT_ARMOR, 1, EntityEquipmentSlot.FEET);


    // Misc Items
    public static ItemCrucifix oakCrucifix = new ItemCrucifix("oak_crucifix", BlockPlanks.EnumType.OAK);
    public static ItemCrucifix spruceCrucifix = new ItemCrucifix("spruce_crucifix", BlockPlanks.EnumType.SPRUCE);
    public static ItemCrucifix birchCrucifix = new ItemCrucifix("birch_crucifix", BlockPlanks.EnumType.BIRCH);
    public static ItemCrucifix jungleCrucifix = new ItemCrucifix("jungle_crucifix", BlockPlanks.EnumType.JUNGLE);
    public static ItemCrucifix acaciaCrucifix = new ItemCrucifix("acacia_crucifix", BlockPlanks.EnumType.ACACIA);
    public static ItemCrucifix darkOakCrucifix = new ItemCrucifix("dark_oak_crucifix", BlockPlanks.EnumType.DARK_OAK);
}
