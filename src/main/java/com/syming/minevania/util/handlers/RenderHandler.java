package com.syming.minevania.util.handlers;

import com.syming.minevania.MinevaniaMod;
//import com.syming.minevania.client.models.entity.ModelDraculaHead;
import com.syming.minevania.client.render.entity.*;
import com.syming.minevania.entity.EntityCrucifix;
import com.syming.minevania.entity.mobs.*;
import com.syming.minevania.entity.mobs.boss.*;
import com.syming.minevania.entity.mobs.friendly.EntityFriendlyBat;
import com.syming.minevania.entity.mobs.friendly.EntityFriendlyZombie;
import com.syming.minevania.entity.projectiles.EntityIceBladeProjectile;
import com.syming.minevania.init.CCBlock;
import com.syming.minevania.util.MVReferences;
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
                return new ModelResourceLocation(MVReferences.MODID + ":holy_water_fluid", "fluid");
            }
       });

       ModelLoader.setCustomStateMapper(CCBlock.HOLY_WATER_FLUID, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(MVReferences.MODID + ":holy_water_fluid", "fluid");
           }
    });

        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(CCBlock.BLOOD_FLUID), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(MVReferences.MODID + ":blood_fluid", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(CCBlock.BLOOD_FLUID, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(MVReferences.MODID + ":blood_fluid", "fluid");
            }
        });
    }

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityAggressiveBat.class, RenderAggressiveBat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodSkeleton.class, RenderBloodSkeleton::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCatoblepas.class, RenderCatoblepas::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCrucifix.class, RenderCrucifix::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDracula.class, RenderDracula::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDraculaSecondForm.class, RenderDraculaSecondForm::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFlameDemon.class, RenderFlameDemon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFleaMan.class, RenderFleaMan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyBat.class, RenderFriendlyBat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyZombie.class, RenderFriendlyZombie::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFrozenShade.class, RenderFrozenShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGorgon.class, RenderGorgon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGrimReaper.class, RenderGrimReaper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHarpy.class, RenderHarpy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHeatShade.class, RenderHeatShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceBladeProjectile.class, RenderIceBlade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMedusa.class, RenderMedusa::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMedusaSnake.class, RenderMedusaSnake::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRusalka.class, RenderRusalka::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZephyr.class, RenderZephyr::new);
    }

    public static void registerItemRenders() {
        MinevaniaMod.proxy.registerItemsVariantsRenderer();
    }
}
