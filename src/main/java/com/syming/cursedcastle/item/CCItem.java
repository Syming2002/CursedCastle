package com.syming.cursedcastle.item;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyCrossEntity;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.item.head.CCHeadItem;
import com.syming.cursedcastle.item.projectile.ScytheProjectileItem;
import com.syming.cursedcastle.item.projectile.subweapon.*;
import com.syming.cursedcastle.item.relic.FrozenHeartRelicItem;
import com.syming.cursedcastle.item.relic.SpiderClawRelicItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.item.spellbook.SpellBookItem;
import com.syming.cursedcastle.item.sword.TyrfingItem;
import com.syming.cursedcastle.item.whip.*;
import com.syming.cursedcastle.item.whip.vampirekiller.*;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.*;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class CCItem {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CursedCastleMod.MOD_ID);


    // Materials

    public static final Supplier<Item> LEATHER_STRING = ITEMS.register("leather_string",
            () -> new Item(new Item.Properties()));


    // Whips

    public static final Supplier<Item> FLAME_WHIP = ITEMS.register("flame_whip",
            () -> new FlameWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(250)
                            .attributes(CCWeaponItem.createAttributes(7, -3.5F, 12.0F))));

    public static final Supplier<Item> HOLY_WHIP = ITEMS.register("holy_whip",
            () -> new HolyWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(400)
                            .attributes(CCWeaponItem.createAttributes(8, -3.5F, 12.0F))));

    public static final Supplier<Item> ICE_WHIP = ITEMS.register("ice_whip",
            () -> new IceWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(100)
                            .attributes(CCWeaponItem.createAttributes(10, -3.5F, 12.0F))));

    public static final Supplier<Item> LEATHER_WHIP = ITEMS.register("leather_whip",
            () -> new LeatherWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(100)
                            .attributes(CCWeaponItem.createAttributes(2, -3.5F, 8.0F))));

    public static final Supplier<Item> ROSE_STEM_WHIP = ITEMS.register("rose_stem_whip",
            () -> new RoseStemWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(200)
                            .attributes(CCWeaponItem.createAttributes(6, -3.5F, 8.0F))));

    public static final Supplier<Item> UNDEAD_KILLER_WHIP = ITEMS.register("undead_killer_whip",
            () -> new UndeadKillerWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(300)
                            .attributes(CCWeaponItem.createAttributes(7, -3.5F, 16.0F))));

    public static final Supplier<Item> VAMPIRE_KILLER_WHIP = ITEMS.register("vampire_killer_whip",
            () -> new VampireKillerWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(1000)
                            .attributes(CCWeaponItem.createAttributes(8, -3.5F, 8.0F))));

    public static final Map<TipType, Supplier<Item>> VAMPIRE_KILLER_ELEMENTAL_WHIP = new HashMap<>();

    static {
        for (TipType tipType : TipType.values()) {
            VAMPIRE_KILLER_ELEMENTAL_WHIP.put(tipType, ITEMS.register(tipType.getSerializedName() + '_' + "stone" + '_' + "tip" + '_' + "vampire_killer_whip",
                    () -> new VampireKillerElementalWhipItem(
                            new Item.Properties()
                                    .stacksTo(1)
                                    .durability(777)
                                    .attributes(CCWeaponItem.createAttributes(8, -3.5F, 8.0F)),
                            tipType)));
        }
    }


    // Tip Items

    public static final Map<TipType, Supplier<TipItem>> TIP_TYPES = new HashMap<>();

    static {
        for (TipType tipType : TipType.values()) {
            TIP_TYPES.put(tipType, ITEMS.register(tipType.getSerializedName() + '_' + "stone" + '_' + "tip", () -> new TipItem(tipType, new Item.Properties().stacksTo(1))));
        }
    }

    // Subweapons

    public static final Map<Tier, Supplier<DaggerItem>> DAGGER_ITEM_TYPES = new HashMap<>();

    static {
        for (Tiers tier : Tiers.values()) {
            DAGGER_ITEM_TYPES.put(tier, ITEMS.register(tier.name().toLowerCase() + '_' + "dagger",
                    () -> new DaggerItem(tier,
                            new Item.Properties()
                                    .stacksTo(1)
                                    .durability(tier.getUses())
                    )));
        }
    }

    public static final Supplier<Item> HOLY_BIBLE = ITEMS.register("holy_bible",
            () -> new HolyBibleItem(new Item.Properties()
                    .stacksTo(1)));

    public static final Map<Tier, Supplier<HolyCrossItem>> HOLY_CROSS_ITEM_TYPES = new HashMap<>();

    static {
        for (Tiers tier : Tiers.values()) {
            HOLY_CROSS_ITEM_TYPES.put(tier, ITEMS.register(tier.name().toLowerCase() + '_' + "holy_cross",
                    () -> new HolyCrossItem(tier,
                            new Item.Properties()
                                    .stacksTo(1)
                                    .durability(tier.getUses())
                    )));
        }
    }

    public static final Supplier<Item> HOLY_WATER = ITEMS.register("holy_water",
            () -> new HolyWaterItem(new Item.Properties()
                    .stacksTo(1)));

    public static final Map<Tier, Supplier<ThrowingAxeItem>> THROWING_AXE_ITEM_TYPES = new HashMap<>();

    static {
        for (Tiers tier : Tiers.values()) {
            THROWING_AXE_ITEM_TYPES.put(tier, ITEMS.register(tier.name().toLowerCase() + '_' + "throwing_axe",
                    () -> new ThrowingAxeItem(tier,
                            new Item.Properties()
                                    .stacksTo(1)
                                    .durability(tier.getUses())
                    )));
        }
    }

    // Swords

    public static final Supplier<Item> TYRFING = ITEMS.register("tyrfing",
            () -> new TyrfingItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(500)
                            .attributes(CCWeaponItem.createAttributes(0, -2.4F, 3))));

    // Miscellaneous Items

    public static final Supplier<Item> ANIM_WOOD_HOLY_CROSS = ITEMS.register("anim_wood_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_STONE_HOLY_CROSS = ITEMS.register("anim_stone_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_IRON_HOLY_CROSS = ITEMS.register("anim_iron_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_GOLD_HOLY_CROSS = ITEMS.register("anim_gold_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_DIAMOND_HOLY_CROSS = ITEMS.register("anim_diamond_holy_cross",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_NETHERITE_HOLY_CROSS = ITEMS.register("anim_netherite_holy_cross",
            () -> new Item(new Item.Properties()));


    public static final Supplier<Item> ANIM_FIRE_HOLY_CROSS = ITEMS.register("anim_fire_holy_cross",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> ANIM_ICE_HOLY_CROSS = ITEMS.register("anim_ice_holy_cross",
            () -> new Item(new Item.Properties()));


    public static final Supplier<Item> ANIM_WOOD_THROWING_AXE = ITEMS.register("anim_wood_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_STONE_THROWING_AXE = ITEMS.register("anim_stone_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_IRON_THROWING_AXE = ITEMS.register("anim_iron_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_GOLD_THROWING_AXE = ITEMS.register("anim_gold_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_DIAMOND_THROWING_AXE = ITEMS.register("anim_diamond_throwing_axe",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANIM_NETHERITE_THROWING_AXE = ITEMS.register("anim_netherite_throwing_axe",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> BLOOD_BONE = ITEMS.register("blood_bone",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> BLOOD_THROWING_BONE = ITEMS.register("blood_throwing_bone",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> GOLDEN_BONE = ITEMS.register("gold_bone",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> THROWING_BONE = ITEMS.register("throwing_bone",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> GOLDEN_THROWING_BONE = ITEMS.register("gold_throwing_bone",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket",
            () -> new BucketItem(CCFluid.SOURCE_BLOOD.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final Supplier<Item> HOLY_WATER_BUCKET = ITEMS.register("holy_water_bucket",
            () -> new BucketItem(CCFluid.SOURCE_HOLY_WATER.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final Supplier<Item> SCYTHE_PROJECTILE_ITEM = ITEMS.register("scythe_projectile", ScytheProjectileItem::new);

    public static final Supplier<Item> ANIM_SCYTHE_PROJECTILE_ITEM = ITEMS.register("anim_scythe_projectile", () -> new Item(new Item.Properties()));

    public static final Supplier<Item> DEATH_SCYTHE = ITEMS.register("death_scythe", () -> new DeathScytheItem(new Item.Properties().stacksTo(1).attributes(CCWeaponItem.createAttributes(8,-3.6F,5.0F))));


    // Relics

    public static final Supplier<Item> FROZEN_HEART = ITEMS.register("frozen_heart", () -> new FrozenHeartRelicItem(new Item.Properties()));
    public static final Supplier<Item> SPIDER_CLAW = ITEMS.register("spider_claw", () -> new SpiderClawRelicItem(new Item.Properties()));


    // Souls

    public static final Map<Soul, Supplier<SoulItem>> SOUL_TYPES = new HashMap<>();

    static {
        for (Soul soul : Soul.values()) {
            SOUL_TYPES.put(soul, ITEMS.register(soul.getName() + '_' + "soul", () -> new SoulItem(soul)));
        }
    }


    public static final Supplier<CCHeadItem> DRACULA_HEAD = ITEMS.register("dracula_head",
            () -> new CCHeadItem(CCBlock.DRACULA_HEAD.get(), CCBlock.DRACULA_WALL_HEAD.get(), Direction.DOWN));


    // Spell Books

    public static final Map<SpellBookElementType, Supplier<SpellBookItem>> SPELL_BOOK_TYPES = new HashMap<>();

    static {
        for (SpellBookElementType spellBookElementType : SpellBookElementType.values()) {
            SPELL_BOOK_TYPES.put(spellBookElementType, ITEMS.register(spellBookElementType.getSerializedName() + '_' + "spell_book", () -> new SpellBookItem(spellBookElementType)));
        }
    }

    // Spawn Eggs

    public static final Supplier<Item> ARACHNE_SPAWN_EGG = ITEMS.register("arachne_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.ARACHNE.get(),
                    0x2D242C,
                    0x392D36,
                    new Item.Properties()));

    public static final Supplier<Item> BLOOD_SKELETON_SPAWN_EGG = ITEMS.register("blood_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.BLOOD_SKELETON.get(),
                    0x8F0000,
                    0x420000,
                    new Item.Properties()));

    public static final Supplier<Item> CLEAR_BONE_SKELETON_SPAWN_EGG = ITEMS.register("clear_bone_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.CLEAR_BONE_SKELETON.get(),
                    0xA0A0B0,
                    0xF8F8F8,
                    new Item.Properties()));

    public static final Supplier<Item> DRACULA_SPAWN_EGG = ITEMS.register("dracula_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.DRACULA.get(),
                    0x000000,
                    0xBF3030,
                    new Item.Properties()));

    public static final Supplier<Item> FAMILIAR_BAT_SPAWN_EGG = ITEMS.register("familiar_bat_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.FAMILIAR_BAT.get(),
                    0x4C3E30,
                    0x5A84FF,
                    new Item.Properties()));

    public static final Supplier<Item> FIRE_ZOMBIE_SPAWN_EGG = ITEMS.register("fire_zombie_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.FIRE_ZOMBIE.get(),
                    0x282523,
                    0x761D10,
                    new Item.Properties()));

    public static final Supplier<Item> FROST_ZOMBIE_SPAWN_EGG = ITEMS.register("frost_zombie_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.FROST_ZOMBIE.get(),
                    0xFFFFFF,
                    0xAFC8D9,
                    new Item.Properties()));

    public static final Supplier<Item> FROZEN_SHADE_SPAWN_EGG = ITEMS.register("frozen_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.FROZEN_SHADE.get(),
                    0x9BB8CD,
                    0xFFFFFF,
                    new Item.Properties()));

    public static final Supplier<Item> GOLDEN_SKELETON_SPAWN_EGG = ITEMS.register("golden_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.GOLDEN_SKELETON.get(),
                    0xEFBF04,
                    0xD4AF37,
                    new Item.Properties()));

    public static final Supplier<Item> GRIM_REAPER_SPAWN_EGG = ITEMS.register("grim_reaper_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.GRIM_REAPER.get(),
                    0x171815,
                    0xAAA398,
                    new Item.Properties()));

    public static final Supplier<Item> HARPY_SPAWN_EGG = ITEMS.register("harpy_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.HARPY.get(),
                    0xFFDEB5,
                    0x006B6B,
                    new Item.Properties()));

    public static final Supplier<Item> HEAT_SHADE_SPAWN_EGG = ITEMS.register("heat_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.HEAT_SHADE.get(),
                    0x360908,
                    0xF24A18,
                    new Item.Properties()));

    public static final Supplier<Item> MEDUSA_SPAWN_EGG = ITEMS.register("medusa_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.MEDUSA.get(),
                    0x7A8750,
                    0x4A5B3E,
                    new Item.Properties()));

    public static final Supplier<Item> MEDUSA_SNAKE_SPAWN_EGG = ITEMS.register("medusa_snake_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.MEDUSA_SNAKE.get(),
                    0x404E36,
                    0x4C5E40,
                    new Item.Properties()));

    public static final Supplier<Item> POISON_SHADE_SPAWN_EGG = ITEMS.register("poison_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.POISON_SHADE.get(),
                    0x1A440A,
                    0x87A703,
                    new Item.Properties()));

    public static final Supplier<Item> RUSALKA_SPAWN_EGG = ITEMS.register("rusalka_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.RUSALKA.get(),
                    0xD6CCC3,
                    0x355F4B,
                    new Item.Properties()));

    public static final Supplier<Item> SKELETON_SPAWN_EGG = ITEMS.register("throwing_bone_skeleton_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.THROWING_BONE_SKELETON.get(),
                    0x828282,
                    0xA8A8A8,
                    new Item.Properties()));

    public static final Supplier<Item> THUNDER_SHADE_SPAWN_EGG = ITEMS.register("thunder_shade_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.THUNDER_SHADE.get(),
                    0x0C2550,
                    0x64BDF4,
                    new Item.Properties()));

    public static final Supplier<Item> VAMPIRE_BAT_SPAWN_EGG = ITEMS.register("vampire_bat_spawn_egg",
            () -> new CCSpawnEggItem(
                    CCEntity.VAMPIRE_BAT.get(),
                    0x393939,
                    0x8C0808,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
