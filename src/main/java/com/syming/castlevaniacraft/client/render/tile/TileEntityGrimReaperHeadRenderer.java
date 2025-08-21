package com.syming.castlevaniacraft.client.render.tile;

import com.syming.castlevaniacraft.client.model.ModelCCHead;
import com.syming.castlevaniacraft.client.model.ModelGrimReaperHead;
import com.syming.castlevaniacraft.tile.TileEntityGrimReaperHead;
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
public class TileEntityGrimReaperHeadRenderer extends TileEntitySpecialRenderer<TileEntityGrimReaperHead>
{
    private static final ResourceLocation GRIM_REAPER_TEXTURES = new ResourceLocation(CCReferences.MODID,"textures/entity/grim_reaper.png");
    public static TileEntityGrimReaperHeadRenderer INSTANCE = new TileEntityGrimReaperHeadRenderer();
    private final ModelGrimReaperHead grimReaperHead = new ModelGrimReaperHead(24, 0, 24, 16, 64, 64);

    @Override
    public void render(TileEntityGrimReaperHead te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        EnumFacing enumfacing = EnumFacing.byIndex(te.getBlockMetadata() & 7);
        this.renderGrimReaperHead((float)x, (float)y, (float)z, enumfacing, (float)(te.getSkullRotation() * 360) / 16.0F, destroyStage);
    }

    public void renderGrimReaperHead(float x, float y, float z, EnumFacing facing, float rotationIn, int destroyStage)
    {

        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 2.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else {
            this.bindTexture(GRIM_REAPER_TEXTURES);
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

        this.grimReaperHead.render((Entity)null, 0.0F, 0.0F, 0.0F, rotationIn, 0.0F, 0.0625F);
        GlStateManager.popMatrix();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}
