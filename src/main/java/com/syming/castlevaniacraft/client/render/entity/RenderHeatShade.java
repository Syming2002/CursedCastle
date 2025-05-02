package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelHeatShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderHeatShade extends RenderLiving<EntityHeatShade> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/heat_shade.png");

    public RenderHeatShade(RenderManager renderManager) {
        super(renderManager, new ModelHeatShade(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHeatShade entity) {
        return TEXTURE;
    }

    @Override
    protected float getDeathMaxRotation(EntityHeatShade entityLivingBaseIn) {
        return 0.0F;
    }
}
