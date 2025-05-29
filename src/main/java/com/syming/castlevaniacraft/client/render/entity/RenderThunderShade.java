package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelPoisonShade;
import com.syming.castlevaniacraft.client.model.entity.ModelThunderShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityThunderShade;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderThunderShade extends RenderLiving<EntityThunderShade> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/thunder_shade.png");

    public RenderThunderShade(RenderManager renderManager) {
        super(renderManager, new ModelThunderShade(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityThunderShade thunderShade) {
        return TEXTURE;
    }

    @Override
    protected float getDeathMaxRotation(EntityThunderShade thunderShade) {
        return 0.0F;
    }
}
