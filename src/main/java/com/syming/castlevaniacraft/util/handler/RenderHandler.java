package com.syming.castlevaniacraft.util.handler;


//import com.syming.castlevaniacraft.client.models.entity.ModelDraculaHead;
import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.client.render.entity.*;
import com.syming.castlevaniacraft.client.render.tile.TileEntityDraculaHeadRenderer;
import com.syming.castlevaniacraft.client.render.tile.TileEntityGrimReaperHeadRenderer;
import com.syming.castlevaniacraft.entity.EntityCrucifix;
import com.syming.castlevaniacraft.entity.EntityLaserBeamTeleportation;
import com.syming.castlevaniacraft.entity.mobs.*;
import com.syming.castlevaniacraft.entity.mobs.boss.*;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyBat;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyZombie;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityThunderShade;
import com.syming.castlevaniacraft.entity.projectiles.EntityFireHydra;
import com.syming.castlevaniacraft.entity.projectiles.EntityHarpyFeather;
import com.syming.castlevaniacraft.entity.projectiles.EntityIceBladeProjectile;
import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.tile.TileEntityDraculaHead;
import com.syming.castlevaniacraft.tile.TileEntityGrimReaperHead;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

    }

    public static void registerItemRenders() {
        CastlevaniaCraftMod.proxy.registerItemsVariantsRenderer();
    }

    public static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDraculaHead.class, TileEntityDraculaHeadRenderer.INSTANCE);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrimReaperHead.class, TileEntityGrimReaperHeadRenderer.INSTANCE);
    }
}
