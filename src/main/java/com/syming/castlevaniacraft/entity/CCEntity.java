package com.syming.castlevaniacraft.entity;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.entity.mobs.*;
import com.syming.castlevaniacraft.entity.mobs.boss.*;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyBat;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyZombie;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityThunderShade;
import com.syming.castlevaniacraft.entity.projectiles.*;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.*;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.block.BlockPlanks;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;


public class CCEntity {

    private static int entityID = 0;

    public static void registerEntities() {
        // Sub Weapons
        registerHolyCross("holy_cross", EntityHolyCross.class, Item.ToolMaterial.WOOD);
        registerHolyCross("holy_cross", EntityHolyCross.class, Item.ToolMaterial.STONE);
        registerHolyCross("holy_cross", EntityHolyCross.class, Item.ToolMaterial.IRON);
        registerHolyCross("holy_cross", EntityHolyCross.class, Item.ToolMaterial.GOLD);
        registerHolyCross("holy_cross", EntityHolyCross.class, Item.ToolMaterial.DIAMOND);

        registerFireHolyCross("fire_holy_cross", EntityFireHolyCross.class);

        registerProjectile("holy_water", EntityHolyWater.class, CCItem.holyWater);

        registerDagger("dagger", EntityDagger.class, Item.ToolMaterial.WOOD);
        registerDagger("dagger", EntityDagger.class, Item.ToolMaterial.STONE);
        registerDagger("dagger", EntityDagger.class, Item.ToolMaterial.IRON);
        registerDagger("dagger", EntityDagger.class, Item.ToolMaterial.GOLD);
        registerDagger("dagger", EntityDagger.class, Item.ToolMaterial.DIAMOND);

        registerThrowingAxe("throwing_axe", EntityThrowingAxe.class, Item.ToolMaterial.WOOD);
        registerThrowingAxe("throwing_axe", EntityThrowingAxe.class, Item.ToolMaterial.STONE);
        registerThrowingAxe("throwing_axe", EntityThrowingAxe.class, Item.ToolMaterial.IRON);
        registerThrowingAxe("throwing_axe", EntityThrowingAxe.class, Item.ToolMaterial.GOLD);
        registerThrowingAxe("throwing_axe", EntityThrowingAxe.class, Item.ToolMaterial.DIAMOND);

        registerProjectile("holy_bible", EntityHolyBible.class, CCItem.holyBible);

        // Projectiles
        registerProjectile("scythe_projectile", EntityScytheProjectile.class, CCItem.scytheProjectile);
        registerProjectile("throwing_bone", EntityThrowingBoneProjectile.class, CCItem.throwingBone);
        registerProjectile("fire_pillar", EntityFirePillar.class, null);
        registerEntityWithNoEgg("ice_blade", EntityIceBladeProjectile.class, 64);
        registerEntityWithNoEgg("harpy_feather", EntityHarpyFeather.class, 64);
        registerEntityWithNoEgg("fire_hydra", EntityFireHydra.class, 64);

        // Entity
        registerEntity("vampire_bat", EntityVampireBat.class, 64, 0x393939, 0x8C0808);
        registerEntity("blood_skeleton", EntityBloodSkeleton.class, 64, 0x8F0000, 0x420000);
        registerEntity("catoblepas", EntityCatoblepas.class, 64, 0x081010, 0x485800);
        registerEntity("dracula", EntityDracula.class, 64, 0x000000, 0xBF3030);
        registerEntity("dracula_second_form", EntityDraculaSecondForm.class, 64, 0x428473, 0xDE0000);
        registerEntity("flame_demon", EntityFlameDemon.class, 64, 0xA01810, 0xD8A800);
        registerEntity("flea_man", EntityFleaMan.class, 64, 0x4A9400, 0xFFB56B);
        registerEntity("friendly_bat", EntityFriendlyBat.class, 64, 0x4C3E30, 0x5A84FF);
        registerEntity("friendly_zombie", EntityFriendlyZombie.class, 64, 0x00AFAF, 0xD1CFCC);
        registerEntity("frozen_shade", EntityFrozenShade.class, 64, 0x0350B1, 0x1092F9);
        registerEntity("gorgon", EntityGorgon.class, 64, 0xE8B000, 0xF05850);
        registerEntity("grim_reaper", EntityGrimReaper.class, 64, 0x171815, 0xAAA398);
        registerEntity("harpy", EntityHarpy.class, 64, 0xF8B078, 0x08F5C6);
        registerEntity("heat_shade", EntityHeatShade.class, 64, 0xA10C05, 0xF01504);
        registerEntity("medusa", EntityMedusa.class, 64, 0x204018, 0xD8E068);
        registerEntity("medusa_snake", EntityMedusaSnake.class, 64, 0xD8E068, 0xB23434);
        registerEntity("poison_shade", EntityPoisonShade.class, 64, 0x45F248, 0x319E3D);
        registerEntity("rusalka", EntityRusalka.class, 64, 0x002080, 0x00F878);
        registerEntity("thunder_shade", EntityThunderShade.class,64, 0xFFFF75,0xB3B334);
        registerEntity("zephyr", EntityZephyr.class, 64, 0x4D1366 , 0x86BABA);

        registerCrucifix("crucifix", EntityCrucifix.class, BlockPlanks.EnumType.OAK);
        registerCrucifix("crucifix", EntityCrucifix.class, BlockPlanks.EnumType.SPRUCE);
        registerCrucifix("crucifix", EntityCrucifix.class, BlockPlanks.EnumType.BIRCH);
        registerCrucifix("crucifix", EntityCrucifix.class, BlockPlanks.EnumType.JUNGLE);
        registerCrucifix("crucifix", EntityCrucifix.class, BlockPlanks.EnumType.ACACIA);
        registerCrucifix("crucifix", EntityCrucifix.class, BlockPlanks.EnumType.DARK_OAK);

        registerEntityWithNoEgg("fire_breath", EntityFireBreath.class, 64);
        registerEntityWithNoEgg("ice_breath", EntityIceBreath.class, 64);
        registerEntityWithNoEgg("poison_breath", EntityPoisonBreath.class, 64);
        registerEntityWithNoEgg("stone_breath", EntityStoneBreath.class, 64);
    }

    public static void registerProjectile(String name, Class<? extends Entity> entity, Item item) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, name);
        EntityRegistry.registerModEntity(resourceLocation, entity, name, entityID, CastlevaniaCraftMod.instance, 64, 1, true);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int range, int primaryColor, int secondaryColor) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, name);
        EntityRegistry.registerModEntity(resourceLocation, entity, name, entityID, CastlevaniaCraftMod.instance, range, 1, true, primaryColor, secondaryColor);

    }

    private static int nextEntityID() {
        return entityID++;
    }

    private static void registerEntityWithNoEgg(String name, Class<? extends Entity> entity, int range) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, name);
        EntityRegistry.registerModEntity(resourceLocation, entity, name, entityID, CastlevaniaCraftMod.instance, range, 1, true);

    }

    private static void registerHolyCross(String name, Class<? extends EntityHolyCross> holyCross, Item.ToolMaterial material) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, material.toString() + "_" + name);
        EntityRegistry.registerModEntity(resourceLocation, holyCross, name, entityID, CastlevaniaCraftMod.instance, 64, 1, true);

    }

    private static void registerFireHolyCross(String name, Class<? extends EntityFireHolyCross> holyCross) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, name);
        EntityRegistry.registerModEntity(resourceLocation, holyCross, name, entityID, CastlevaniaCraftMod.instance, 64, 1, true);

    }

    private static void registerThrowingAxe(String name, Class<? extends EntityThrowingAxe> throwingAxe, Item.ToolMaterial material) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, material.toString() + "_" + name);
        EntityRegistry.registerModEntity(resourceLocation, throwingAxe, name, entityID, CastlevaniaCraftMod.instance, 64, 1, true);

    }

    private static void registerDagger(String name, Class<? extends EntityDagger> dagger, Item.ToolMaterial material) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, material.toString() + "_" + name);
        EntityRegistry.registerModEntity(resourceLocation, dagger, name, entityID, CastlevaniaCraftMod.instance, 64, 1, true);

    }

    private static void registerCrucifix(String name, Class<? extends EntityCrucifix> crucifix, BlockPlanks.EnumType woodType) {
        int entityID = nextEntityID();
        ResourceLocation resourceLocation = new ResourceLocation(CCReferences.MODID, woodType.getTranslationKey() + "_" + name);
        EntityRegistry.registerModEntity(resourceLocation, crucifix, name, entityID, CastlevaniaCraftMod.instance, 64, 1, true);
    }

}
