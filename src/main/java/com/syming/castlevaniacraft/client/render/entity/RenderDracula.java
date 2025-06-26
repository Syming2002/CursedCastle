package com.syming.castlevaniacraft.client.render.entity;

import akka.dispatch.sysmsg.Suspend;
import com.syming.castlevaniacraft.client.model.entity.ModelDracula;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaHead;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import static net.minecraft.client.renderer.entity.RenderDragon.ENDERCRYSTAL_BEAM_TEXTURES;

@SideOnly(Side.CLIENT)
public class RenderDracula extends RenderLiving<EntityDracula> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/dracula.png");

    public RenderDracula(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDracula(), 0.5F);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(@NotNull EntityDracula entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        EntityDraculaHead head = (EntityDraculaHead) entity.getDraculaHead();

        if (head.healingEnderCrystal != null) {
            this.bindTexture(ENDERCRYSTAL_BEAM_TEXTURES);
            float f = MathHelper.sin(((float)head.healingEnderCrystal.ticksExisted + partialTicks) * 0.2F) / 2.0F + 0.5F;
            f = (f * f + f) * 0.2F;
            renderCrystalBeams(x, y, z, partialTicks, entity.posX + (entity.prevPosX - entity.posX) * (double)(1.0F - partialTicks), entity.posY + (entity.prevPosY - entity.posY) * (double)(1.0F - partialTicks), entity.posZ + (entity.prevPosZ - entity.posZ) * (double)(1.0F - partialTicks), entity.ticksExisted, head.healingEnderCrystal.posX, (double)f + head.healingEnderCrystal.posY, head.healingEnderCrystal.posZ);
        }

        if (entity != null
                && entity.world != null
                && entity.world.isRemote
                && ((entity.getTeleportTick() >= 50 && entity.getTeleportTick() < 70)
                || (entity.getTeleportTick() >= 100 && entity.getTeleportTick() < 120))) {
            renderLaserBeamTeleportation(entity, x, y, z, partialTicks);
            System.out.println("Rendering Laser Beam");
        }
    }

    public static void renderCrystalBeams(double p_188325_0_, double p_188325_2_, double p_188325_4_, float p_188325_6_, double p_188325_7_, double p_188325_9_, double p_188325_11_, int p_188325_13_, double p_188325_14_, double p_188325_16_, double p_188325_18_) {
        float f = (float)(p_188325_14_ - p_188325_7_);
        float f1 = (float)(p_188325_16_ - 1.0D - p_188325_9_);
        float f2 = (float)(p_188325_18_ - p_188325_11_);
        float f3 = MathHelper.sqrt(f * f + f2 * f2);
        float f4 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)p_188325_0_, (float)p_188325_2_ + 2.0F, (float)p_188325_4_);
        GlStateManager.rotate((float)(-Math.atan2((double)f2, (double)f)) * (180F / (float)Math.PI) - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(-Math.atan2((double)f3, (double)f1)) * (180F / (float)Math.PI) - 90.0F, 1.0F, 0.0F, 0.0F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableCull();
        GlStateManager.shadeModel(7425);
        float f5 = 0.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
        float f6 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2) / 32.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
        bufferbuilder.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
        int i = 8;

        for (int j = 0; j <= 8; ++j) {
            float f7 = MathHelper.sin((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
            float f8 = MathHelper.cos((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
            float f9 = (float)(j % 8) / 8.0F;
            bufferbuilder.pos((double)(f7 * 0.2F), (double)(f8 * 0.2F), 0.0D).tex((double)f9, (double)f5).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos((double)f7, (double)f8, (double)f4).tex((double)f9, (double)f6).color(255, 255, 255, 255).endVertex();
        }

        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(7424);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    public void renderLaserBeamTeleportation(EntityDracula entity, double x, double y, double z, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);

        float scale = entity.width * 6.0F;
        GlStateManager.scale(scale, entity.world.getHeight(), scale);

        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        this.bindTexture(new ResourceLocation(CCReferences.MODID, "textures/blocks/teleportation_laser_beam.png"));

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        int ticks = entity.ticksExisted;
        int numFrames = 8;
        float frameHeight = 1.0F / numFrames;
        int currentFrame = (ticks / 4) % numFrames;
        float vMin = currentFrame * frameHeight;
        float vMax = vMin + frameHeight;

        float uMin = 0.0F;
        float uMax = 1.0F;

        float quadSize = 0.5F;
        float quadHeight = 1.0F;

        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);

        for (int pass = 0; pass < 2; pass++) {
            GlStateManager.pushMatrix();
            if (pass == 1) {
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

            bufferbuilder.pos(-quadSize, 0.0F, 0.0F).tex(uMin, vMax).endVertex();
            bufferbuilder.pos(quadSize, 0.0F, 0.0F).tex(uMax, vMax).endVertex();
            bufferbuilder.pos(quadSize, quadHeight, 0.0F).tex(uMax, vMin).endVertex();
            bufferbuilder.pos(-quadSize, quadHeight, 0.0F).tex(uMin, vMin).endVertex();

            tessellator.draw();
            GlStateManager.popMatrix();
        }

        int i = entity.getBrightnessForRender();

        if (entity.isTeleporting()) {
            i = 15728880;
        }

        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);

        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    @Override
    protected float getDeathMaxRotation(EntityDracula entityLivingBaseIn) {
        return 0.0F;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDracula entity) {
        return TEXTURE;
    }
}
