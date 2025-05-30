package com.syming.castlevaniacraft.client.render.tile;

import com.syming.castlevaniacraft.client.model.ModelCCHead;
import com.syming.castlevaniacraft.tile.TileEntityDraculaHead;
import com.syming.castlevaniacraft.util.CCReferences;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityDraculaHeadRenderer extends TileEntitySpecialRenderer<TileEntityDraculaHead>
{
    private static final ResourceLocation DRACULA_TEXTURES = new ResourceLocation(CCReferences.MODID,"textures/entity/dracula.png");
    public static TileEntityDraculaHeadRenderer INSTANCE = new TileEntityDraculaHeadRenderer();
    private final ModelCCHead draculaHead = new ModelCCHead(0, 16, 128, 128);

    @Override
    public void render(TileEntityDraculaHead te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        EnumFacing enumfacing = EnumFacing.byIndex(te.getBlockMetadata() & 7);
        this.renderDraculaHead((float)x, (float)y, (float)z, enumfacing, (float)(te.getSkullRotation() * 360) / 16.0F, destroyStage);
    }

    public void renderDraculaHead(float x, float y, float z, EnumFacing facing, float rotationIn, int destroyStage)
    {
        ModelBase modelbase = this.draculaHead;

        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 2.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else {
            this.bindTexture(DRACULA_TEXTURES);
        }

        GlStateManager.pushMatrix();
        GlStateManager.disableCull();

        if (facing == EnumFacing.UP)
        {
            GlStateManager.translate(x + 0.5F, y, z + 0.5F);
        }
        else
        {
            switch (facing)
            {
                case NORTH:
                    GlStateManager.translate(x + 0.5F, y + 0.25F, z + 0.74F);
                    break;
                case SOUTH:
                    GlStateManager.translate(x + 0.5F, y + 0.25F, z + 0.26F);
                    rotationIn = 180.0F;
                    break;
                case WEST:
                    GlStateManager.translate(x + 0.74F, y + 0.25F, z + 0.5F);
                    rotationIn = 270.0F;
                    break;
                case EAST:
                default:
                    GlStateManager.translate(x + 0.26F, y + 0.25F, z + 0.5F);
                    rotationIn = 90.0F;
            }
        }

        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.enableAlpha();

        modelbase.render((Entity)null, 0.0F, 0.0F, 0.0F, rotationIn, 0.0F, 0.0625F);
        GlStateManager.popMatrix();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}
