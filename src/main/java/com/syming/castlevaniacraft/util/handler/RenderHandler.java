package com.syming.castlevaniacraft.util.handler;


//import com.syming.castlevaniacraft.client.models.entity.ModelDraculaHead;
import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.client.render.entity.*;
import com.syming.castlevaniacraft.entity.EntityCrucifix;
import com.syming.castlevaniacraft.entity.mobs.*;
import com.syming.castlevaniacraft.entity.mobs.boss.*;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyBat;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyZombie;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.entity.projectiles.EntityFireHydra;
import com.syming.castlevaniacraft.entity.projectiles.EntityHarpyFeather;
import com.syming.castlevaniacraft.entity.projectiles.EntityIceBladeProjectile;
import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler {

    public static void registerCustomMeshesAndStates()
    {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(CCBlock.HOLY_WATER_FLUID), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(CCReferences.MODID + ":holy_water_fluid", "fluid");
            }
       });

       ModelLoader.setCustomStateMapper(CCBlock.HOLY_WATER_FLUID, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(CCReferences.MODID + ":holy_water_fluid", "fluid");
           }
    });

        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(CCBlock.BLOOD_FLUID), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(CCReferences.MODID + ":blood_fluid", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(CCBlock.BLOOD_FLUID, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(CCReferences.MODID + ":blood_fluid", "fluid");
            }
        });
    }

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class, RenderVampireBat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodSkeleton.class, RenderBloodSkeleton::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCatoblepas.class, RenderCatoblepas::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCrucifix.class, RenderCrucifix::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDracula.class, RenderDracula::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDraculaSecondForm.class, RenderDraculaSecondForm::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFireHydra.class, RenderFireHydra::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFlameDemon.class, RenderFlameDemon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFleaMan.class, RenderFleaMan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyBat.class, RenderFriendlyBat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyZombie.class, RenderFriendlyZombie::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFrozenShade.class, RenderFrozenShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGorgon.class, RenderGorgon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGrimReaper.class, RenderGrimReaper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHarpy.class, RenderHarpy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHarpyFeather.class, RenderHarpyFeather::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHeatShade.class, RenderHeatShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceBladeProjectile.class, RenderIceBlade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMedusa.class, RenderMedusa::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMedusaSnake.class, RenderMedusaSnake::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPoisonShade.class, RenderPoisonShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRusalka.class, RenderRusalka::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZephyr.class, RenderZephyr::new);
    }

    public static void registerItemRenders() {
        CastlevaniaCraftMod.proxy.registerItemsVariantsRenderer();
    }
}
