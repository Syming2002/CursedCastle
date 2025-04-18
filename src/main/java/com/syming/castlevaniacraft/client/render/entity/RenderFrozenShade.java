package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.EntityFrozenShade;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderFrozenShade extends RenderLiving<EntityFrozenShade> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/frozen_shade.png");


    public RenderFrozenShade(RenderManager renderManager) {
        super(renderManager, new ModelFrozenShade(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFrozenShade entity) {
        return TEXTURE;
    }

    @Override
    protected float getDeathMaxRotation(EntityFrozenShade entityLivingBaseIn) {
        return 0.0F;
    }
}
