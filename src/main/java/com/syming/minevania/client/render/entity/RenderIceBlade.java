package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelIceBlade;
import com.syming.minevania.entity.projectiles.EntityIceBladeProjectile;
import com.syming.minevania.util.CCReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderIceBlade extends Render<EntityIceBladeProjectile> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/ice_blade.png");
    public ModelIceBlade modelIceBlade;

    public RenderIceBlade(RenderManager renderManager) {
        super(renderManager);
        this.modelIceBlade = new ModelIceBlade();
    }

    private float getRenderYaw(float p_82400_1_, float p_82400_2_, float p_82400_3_)
    {
        float f;

        for (f = p_82400_2_ - p_82400_1_; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return p_82400_1_ + p_82400_3_ * f;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityIceBladeProjectile entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityIceBladeProjectile entity, double x, double y, double z, float entityYaw, float partialTicks) {

        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        float f = this.getRenderYaw(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
        float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.enableAlpha();
        this.bindEntityTexture(entity);

        this.modelIceBlade.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.0625F);

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
