package com.syming.cursedcastle.item;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.item.head.CCHeadItem;
import com.syming.cursedcastle.item.projectile.ScytheProjectileItem;
import com.syming.cursedcastle.item.projectile.subweapon.*;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.item.spellbook.SpellBookItem;
import com.syming.cursedcastle.item.whip.*;
import com.syming.cursedcastle.item.whip.vampirekiller.*;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.*;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class CCItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CursedCastleMod.MOD_ID);


    // Materials

    public static final RegistryObject<Item> LEATHER_STRING = ITEMS.register("leather_string",
            () -> new Item(new Item.Properties()));


    // Whips

    public static final RegistryObject<Item> FLAME_WHIP = ITEMS.register("flame_whip",
            () -> new FlameWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(250),
                    7,
                    -3.5F,
                    12.0F));

    public static final RegistryObject<Item> HOLY_WHIP = ITEMS.register("holy_whip",
            () -> new HolyWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(400),
                    8,
                    -3.5F,
                    12.0F));

    public static final RegistryObject<Item> LEATHER_WHIP = ITEMS.register("leather_whip",
            () -> new LeatherWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(100),
                    2,
                    -3.5F,
                    8.0F));

    public static final RegistryObject<Item> ROSE_STEM_WHIP = ITEMS.register("rose_stem_whip",
            () -> new RoseStemWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(200),
                    6,
                    -3.5F,
                    8.0F
            ));

    public static final RegistryObject<Item> UNDEAD_KILLER_WHIP = ITEMS.register("undead_killer_whip",
            () -> new UndeadKillerWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(300),
                    7,
                    -3.5F,
                    16.0F
            ));

    public static final RegistryObject<Item> VAMPIRE_KILLER_WHIP = ITEMS.register("vampire_killer_whip",
            () -> new VampireKillerWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(1000),
                    8,
                    -3.5F,
                    8.0F));

    public static final RegistryObject<Item> VAMPIRE_KILLER_CRUSHING_STONE_TIP_WHIP = ITEMS.register("vampire_killer_crushing_stone_tip_whip",
            () -> new VampireKillerCrushingStoneTipWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(900)
                            .craftRemainder(CCItem.VAMPIRE_KILLER_WHIP.get()),
                    3,
                    -3.5F,
                    8.0F,
                    5.0F));

    public static final RegistryObject<Item> VAMPIRE_KILLER_BULLET_TIP_WHIP = ITEMS.register("vampire_killer_bullet_tip_whip",
            () -> new VampireKillerBulletTipWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(950)
                            .craftRemainder(CCItem.VAMPIRE_KILLER_WHIP.get()),
                    13,
                    -3.5F,
                    8.0F));

    public static final RegistryObject<Item> VAMPIRE_KILLER_RED_STONE_TIP_WHIP = ITEMS.register("vampire_killer_red_stone_tip_whip",
            () -> new VampireKillerRedStoneTipWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(1150)
                            .craftRemainder(CCItem.VAMPIRE_KILLER_WHIP.get()),
                    10,
                    -3.5F,
                    8.0F));

    public static final RegistryObject<Item> VAMPIRE_KILLER_BLUE_STONE_TIP_WHIP = ITEMS.register("vampire_killer_blue_stone_tip_whip",
            () -> new VampireKillerBlueStoneTipWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(1150)
                            .craftRemainder(CCItem.VAMPIRE_KILLER_WHIP.get()),
                    10,
                    -3.5F,
                    8.0F));

    public static final RegistryObject<Item> VAMPIRE_KILLER_GREEN_STONE_TIP_WHIP = ITEMS.register("vampire_killer_green_stone_tip_whip",
            () -> new VampireKillerGreenStoneTipWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(1150)
                            .craftRemainder(CCItem.VAMPIRE_KILLER_WHIP.get()),
                    10,
                    -3.5F,
                    8.0F));


    // Tip Items

    public static final RegistryObject<Item> BULLET_TIP = ITEMS.register("bullet_tip",
            () -> new BulletTipItem(
                    new Item.Properties()
                            .stacksTo(1)));

    public static final RegistryObject<Item> CRUSHING_STONE_TIP = ITEMS.register("crushing_stone_tip",
            () -> new CrushingStoneTipItem(
                    new Item.Properties()
                            .stacksTo(1)));

    public static final RegistryObject<Item> RED_STONE_TIP = ITEMS.register("red_stone_tip",
            () -> new RedStoneTipItem(
                    new Item.Properties()
                            .stacksTo(1)));

    public static final RegistryObject<Item> BLUE_STONE_TIP = ITEMS.register("blue_stone_tip",
            () -> new BlueStoneTipItem(
                    new Item.Properties()
                            .stacksTo(1)));

    public static final RegistryObject<Item> GREEN_STONE_TIP = ITEMS.register("green_stone_tip",
            () -> new GreenStoneTipItem(
                    new Item.Properties()
                            .stacksTo(1)));


    // Subweapons

    public static final RegistryObject<Item> WOODEN_DAGGER = ITEMS.register("wooden_dagger",
            () -> new DaggerItem(Tiers.WOOD,
                    new Item.Properties()
                            .durability(Tiers.WOOD.getUses())
            ));
    public static final RegistryObject<Item> STONE_DAGGER = ITEMS.register("stone_dagger",
            () -> new DaggerItem(Tiers.STONE,
                    new Item.Properties()
                            .durability(Tiers.STONE.getUses())
            ));
    public static final RegistryObject<Item> IRON_DAGGER = ITEMS.register("iron_dagger",
            () -> new DaggerItem(Tiers.IRON,
                    new Item.Properties()
                            .durability(Tiers.IRON.getUses())
            ));
    public static final RegistryObject<Item> GOLDEN_DAGGER = ITEMS.register("golden_dagger",
            () -> new DaggerItem(Tiers.GOLD,
                    new Item.Properties()
                            .durability(Tiers.GOLD.getUses())
            ));
    public static final RegistryObject<Item> DIAMOND_DAGGER = ITEMS.register("diamond_dagger",
            () -> new DaggerItem(Tiers.DIAMOND,
                    new Item.Properties()
                            .durability(Tiers.DIAMOND.getUses())
            ));
    public static final RegistryObject<Item> NETHERITE_DAGGER = ITEMS.register("netherite_dagger",
            () -> new DaggerItem(Tiers.NETHERITE,
                    new Item.Properties()
                            .durability(Tiers.NETHERITE.getUses())
            ));



    public static final RegistryObject<Item> HOLY_BIBLE = ITEMS.register("holy_bible",
            () -> new HolyBibleItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> WOODEN_HOLY_CROSS = ITEMS.register("wooden_holy_cross",
            () -> new HolyCrossItem(Tiers.WOOD,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.WOOD.getUses())
            ));
    public static final RegistryObject<Item> STONE_HOLY_CROSS = ITEMS.register("stone_holy_cross",
            () -> new HolyCrossItem(Tiers.STONE,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.STONE.getUses())

            ));
    public static final RegistryObject<Item> IRON_HOLY_CROSS = ITEMS.register("iron_holy_cross",
            () -> new HolyCrossItem(Tiers.IRON,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.IRON.getUses())
            ));
    public static final RegistryObject<Item> GOLDEN_HOLY_CROSS = ITEMS.register("golden_holy_cross",
            () -> new HolyCrossItem(Tiers.GOLD,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.GOLD.getUses())
            ));
    public static final RegistryObject<Item> DIAMOND_HOLY_CROSS = ITEMS.register("diamond_holy_cross",
            () -> new HolyCrossItem(Tiers.DIAMOND,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.DIAMOND.getUses())
            ));
    public static final RegistryObject<Item> NETHERITE_HOLY_CROSS = ITEMS.register("netherite_holy_cross",
            () -> new HolyCrossItem(Tiers.NETHERITE,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.NETHERITE.getUses())
            ));

    public static final RegistryObject<Item> HOLY_WATER = ITEMS.register("holy_water",
            () -> new HolyWaterItem(new Item.Properties()
                    .stacksTo(1)));




    public static final RegistryObject<Item> WOODEN_THROWING_AXE = ITEMS.register("wooden_throwing_axe",
            () -> new ThrowingAxeItem(Tiers.WOOD,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.WOOD.getUses())
            ));
    public static final RegistryObject<Item> STONE_THROWING_AXE = ITEMS.register("stone_throwing_axe",
            () -> new ThrowingAxeItem(Tiers.STONE,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.STONE.getUses())

            ));
    public static final RegistryObject<Item> IRON_THROWING_AXE = ITEMS.register("iron_throwing_axe",
            () -> new ThrowingAxeItem(Tiers.IRON,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.IRON.getUses())
            ));
    public static final RegistryObject<Item> GOLDEN_THROWING_AXE = ITEMS.register("golden_throwing_axe",
            () -> new ThrowingAxeItem(Tiers.GOLD,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.GOLD.getUses())
            ));
    public static final RegistryObject<Item> DIAMOND_THROWING_AXE = ITEMS.register("diamond_throwing_axe",
            () -> new ThrowingAxeItem(Tiers.DIAMOND,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.DIAMOND.getUses())
            ));
    public static final RegistryObject<Item> NETHERITE_THROWING_AXE = ITEMS.register("netherite_throwing_axe",
            () -> new ThrowingAxeItem(Tiers.NETHERITE,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(Tiers.NETHERITE.getUses())
            ));

    // Miscellaneous Items

    public static final RegistryObject<Item> ANIM_WOOD_HOLY_CROSS = ITEMS.register("anim_wooden_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_STONE_HOLY_CROSS = ITEMS.register("anim_stone_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_IRON_HOLY_CROSS = ITEMS.register("anim_iron_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_GOLD_HOLY_CROSS = ITEMS.register("anim_golden_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_DIAMOND_HOLY_CROSS = ITEMS.register("anim_diamond_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_NETHERITE_HOLY_CROSS = ITEMS.register("anim_netherite_holy_cross",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> ANIM_WOOD_THROWING_AXE = ITEMS.register("anim_wooden_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_STONE_THROWING_AXE = ITEMS.register("anim_stone_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_IRON_THROWING_AXE = ITEMS.register("anim_iron_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_GOLD_THROWING_AXE = ITEMS.register("anim_golden_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_DIAMOND_THROWING_AXE = ITEMS.register("anim_diamond_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANIM_NETHERITE_THROWING_AXE = ITEMS.register("anim_netherite_throwing_axe",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_BONE = ITEMS.register("blood_bone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_THROWING_BONE = ITEMS.register("blood_throwing_bone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_BONE = ITEMS.register("golden_bone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THROWING_BONE = ITEMS.register("throwing_bone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_THROWING_BONE = ITEMS.register("golden_throwing_bone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket",
            () -> new BucketItem(CCFluid.SOURCE_BLOOD.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> HOLY_WATER_BUCKET = ITEMS.register("holy_water_bucket",
            () -> new BucketItem(CCFluid.SOURCE_HOLY_WATER.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> SCYTHE_PROJECTILE_ITEM = ITEMS.register("scythe_projectile", ScytheProjectileItem::new);

    public static final RegistryObject<Item> ANIM_SCYTHE_PROJECTILE_ITEM = ITEMS.register("anim_scythe_projectile", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEATH_SCYTHE = ITEMS.register("death_scythe", () -> new DeathScytheItem(new Item.Properties().stacksTo(1), 8, -3.6F, 5.0F));

    // Crucifixes

//    public static final Map<WoodType, RegistryObject<CrucifixItem>> CRUCIFIX_TYPES = new HashMap<>();
//
//    static {
//        for (WoodType woodType : WoodType.values().toList()) {
//            CRUCIFIX_TYPES.put(woodType, ITEMS.register(woodType.name() + '_' + "crucifix", () -> new CrucifixItem(woodType, new Item.Properties().stacksTo(1))));
//        }
//    }


    // Souls

    public static final Map<Soul, RegistryObject<SoulItem>> SOUL_TYPES = new HashMap<>();

    static {
        for (Soul soul : Soul.values()) {
            SOUL_TYPES.put(soul, ITEMS.register(soul.getName() + '_' + "soul", () -> new SoulItem(soul)));
        }
    }


    public static final RegistryObject<CCHeadItem> DRACULA_HEAD = ITEMS.register("dracula_head",
            () -> new CCHeadItem(CCBlock.DRACULA_HEAD.get(), CCBlock.DRACULA_WALL_HEAD.get(), Direction.DOWN));


    // Spell Books

    public static final Map<SpellBookElementType, RegistryObject<SpellBookItem>> SPELL_BOOK_TYPES = new HashMap<>();

    static {
        for (SpellBookElementType spellBookElementType : SpellBookElementType.values()) {
            SPELL_BOOK_TYPES.put(spellBookElementType, ITEMS.register(spellBookElementType.getName() + '_' + "spell_book", () -> new SpellBookItem(spellBookElementType)));
        }
    }

    // Spawn Eggs

    public static final RegistryObject<Item> BLOOD_SKELETON_SPAWN_EGG = ITEMS.register("blood_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.BLOOD_SKELETON,
                    0x8F0000,
                    0x420000,
                    new Item.Properties()));

    public static final RegistryObject<Item> CLEAR_BONE_SKELETON_SPAWN_EGG = ITEMS.register("clear_bone_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.CLEAR_BONE_SKELETON,
                    0xA0A0B0,
                    0xF8F8F8,
                    new Item.Properties()));

    public static final RegistryObject<Item> DRACULA_SPAWN_EGG = ITEMS.register("dracula_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.DRACULA,
                    0x000000,
                    0xBF3030,
                    new Item.Properties()));

    public static final RegistryObject<Item> FAMILIAR_BAT_SPAWN_EGG = ITEMS.register("familiar_bat_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.FAMILIAR_BAT,
                    0x4C3E30,
                    0x5A84FF,
                    new Item.Properties()));

    public static final RegistryObject<Item> FROZEN_SHADE_SPAWN_EGG = ITEMS.register("frozen_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.FROZEN_SHADE,
                    0x0350B1,
                    0x1092F9,
                    new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_SKELETON_SPAWN_EGG = ITEMS.register("golden_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.GOLDEN_SKELETON,
                    0xEFBF04,
                    0xD4AF37,
                    new Item.Properties()));

    public static final RegistryObject<Item> GRIM_REAPER_SPAWN_EGG = ITEMS.register("grim_reaper_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.GRIM_REAPER,
                    0x171815,
                    0xAAA398,
                    new Item.Properties()));

    public static final RegistryObject<Item> HARPY_SPAWN_EGG = ITEMS.register("harpy_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.HARPY,
                    0xFFDEB5,
                    0x006B6B,
                    new Item.Properties()));

    public static final RegistryObject<Item> HEAT_SHADE_SPAWN_EGG = ITEMS.register("heat_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.HEAT_SHADE,
                    0xA10C05,
                    0xF01504,
                    new Item.Properties()));

    public static final RegistryObject<Item> MEDUSA_SPAWN_EGG = ITEMS.register("medusa_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.MEDUSA,
                    0x7A8750,
                    0x4A5B3E,
                    new Item.Properties()));

    public static final RegistryObject<Item> POISON_SHADE_SPAWN_EGG = ITEMS.register("poison_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.POISON_SHADE,
                    0x45F248,
                    0x319E3D,
                    new Item.Properties()));

    public static final RegistryObject<Item> SKELETON_SPAWN_EGG = ITEMS.register("throwing_bone_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.THROWING_BONE_SKELETON,
                    0x828282,
                    0xA8A8A8,
                    new Item.Properties()));

    public static final RegistryObject<Item> THUNDER_SHADE_SPAWN_EGG = ITEMS.register("thunder_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.THUNDER_SHADE,
                    0xFFFF75,
                    0xB3B334,
                    new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRE_BAT_SPAWN_EGG = ITEMS.register("vampire_bat_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.VAMPIRE_BAT,
                    0x393939,
                    0x8C0808,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
