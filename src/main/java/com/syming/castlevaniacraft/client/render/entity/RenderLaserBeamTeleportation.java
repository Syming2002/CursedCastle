package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelTeleportationLaserBeam;
import com.syming.castlevaniacraft.entity.EntityLaserBeamTeleportation;
import com.syming.castlevaniacraft.util.CCReferences;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.Nullable;

public class RenderLaserBeamTeleportation extends Render<EntityLaserBeamTeleportation> {
    public static final ResourceLocation TEXTURE_TELEPORTATION_LASER_BEAM = new ResourceLocation(CCReferences.MODID, "textures/entity/teleportation_laser_beam.png");
    protected ModelTeleportationLaserBeam modelTeleportationLaserBeam;

    public RenderLaserBeamTeleportation(RenderManager renderManager) {
        super(renderManager);
        this.modelTeleportationLaserBeam = new ModelTeleportationLaserBeam();
    }
    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(EntityLaserBeamTeleportation entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindEntityTexture(entity);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.modelTeleportationLaserBeam.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);


        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.scale(2.0F, 10.0F, 2.0F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityLaserBeamTeleportation entity) {
        return TEXTURE_TELEPORTATION_LASER_BEAM;
    }
}
