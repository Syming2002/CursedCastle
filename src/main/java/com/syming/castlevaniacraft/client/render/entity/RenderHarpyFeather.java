package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelHarpyFeather;
import com.syming.castlevaniacraft.entity.projectiles.EntityHarpyFeather;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

@SideOnly(Side.CLIENT)
public class RenderHarpyFeather extends Render<EntityHarpyFeather> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/harpy_feather.png");
    public ModelHarpyFeather modelHarpyFeather;

    public RenderHarpyFeather(RenderManager renderManager) {
        super(renderManager);
        this.modelHarpyFeather = new ModelHarpyFeather();
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityHarpyFeather entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityHarpyFeather entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();

        float yaw = entity.rotationYaw;
        float pitch = entity.rotationPitch;

        GlStateManager.translate((float) x, (float) y, (float) z);

        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

        GlStateManager.rotate(-yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);

        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.enableAlpha();
        this.bindEntityTexture(entity);

        this.modelHarpyFeather.render(entity, 0.0F, 0.0F, 0.0F, yaw, pitch, 0.04F);

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
