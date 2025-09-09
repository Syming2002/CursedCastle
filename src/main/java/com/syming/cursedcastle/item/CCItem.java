package com.syming.cursedcastle.item;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulBulletItem;
import com.syming.cursedcastle.item.soul.SoulGuardianItem;
import com.syming.cursedcastle.item.subweapon.*;
import com.syming.cursedcastle.item.whip.FlameWhipItem;
import com.syming.cursedcastle.item.whip.LeatherWhipItem;
import com.syming.cursedcastle.item.whip.UndeadKillerWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerBulletTipWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerCrushingStoneTipWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerRedStoneTipWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.BulletTipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.CrushingStoneTipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.RedStoneTipItem;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.CuriosApi;

public class CCItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CursedCastleMod.MOD_ID);

    // Whips

    public static final RegistryObject<Item> FLAME_WHIP = ITEMS.register("flame_whip",
            () -> new FlameWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(250),
                    (int) 6.5F,
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

    public static final RegistryObject<Item> UNDEAD_KILLER_WHIP = ITEMS.register("undead_killer_whip",
            () -> new UndeadKillerWhipItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(200),
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
                            .durability(1150),
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

    // Souls

    public static final RegistryObject<SoulGuardianItem> BAT_SOUL = ITEMS.register("bat_soul",
            () -> new SoulGuardianItem(Soul.BAT, new Item.Properties().stacksTo(9).defaultDurability(0).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<SoulBulletItem> ZOMBIE_SOUL = ITEMS.register("zombie_soul",
            () -> new SoulBulletItem(Soul.ZOMBIE, new Item.Properties().stacksTo(9).defaultDurability(0).rarity(Rarity.UNCOMMON)));


    // Spawn Eggs

    public static final RegistryObject<Item> BLOOD_SKELETON_SPAWN_EGG = ITEMS.register("blood_skeleton_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.BLOOD_SKELETON,
                    0x8F0000,
                    0x420000,
                    new Item.Properties()));

    public static final RegistryObject<Item> DRACULA_SPAWN_EGG = ITEMS.register("dracula_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.DRACULA,
                    0x000000,
                    0xBF3030,
                    new Item.Properties()));

    public static final RegistryObject<Item> FAMILIAR_BAT_SPAWN_EGG = ITEMS.register("familiar_bat_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.FAMILIAR_BAT,
                    0x4C3E30,
                    0x5A84FF,
                    new Item.Properties()));

    public static final RegistryObject<Item> FROZEN_SHADE_SPAWN_EGG = ITEMS.register("frozen_shade_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.FROZEN_SHADE,
                    0x0350B1,
                    0x1092F9,
                    new Item.Properties()));

    public static final RegistryObject<Item> HEAT_SHADE_SPAWN_EGG = ITEMS.register("heat_shade_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.HEAT_SHADE,
                    0xA10C05,
                    0xF01504,
                    new Item.Properties()));

    public static final RegistryObject<Item> POISON_SHADE_SPAWN_EGG = ITEMS.register("poison_shade_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.POISON_SHADE,
                    0x45F248,
                    0x319E3D,
                    new Item.Properties()));

    public static final RegistryObject<Item> THUNDER_SHADE_SPAWN_EGG = ITEMS.register("thunder_shade_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.THUNDER_SHADE,
                    0xFFFF75,
                    0xB3B334,
                    new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRE_BAT_SPAWN_EGG = ITEMS.register("vampire_bat_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    CCEntity.VAMPIRE_BAT,
                    0x393939,
                    0x8C0808,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
