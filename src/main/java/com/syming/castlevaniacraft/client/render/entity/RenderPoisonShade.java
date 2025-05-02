package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelPoisonShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderPoisonShade extends RenderLiving<EntityPoisonShade> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/poison_shade.png");

    public RenderPoisonShade(RenderManager renderManager) {
        super(renderManager, new ModelPoisonShade(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPoisonShade entity) {
        return TEXTURE;
    }

    @Override
    protected float getDeathMaxRotation(EntityPoisonShade entityLivingBaseIn) {
        return 0.0F;
    }
}
