package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelMedusaSnake;
import com.syming.minevania.entity.mobs.EntityMedusaSnake;
import com.syming.minevania.util.MVReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderMedusaSnake extends RenderLiving<EntityMedusaSnake> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/medusa_snake.png");

    public RenderMedusaSnake(RenderManager renderManager) {
        super(renderManager, new ModelMedusaSnake(), 0.25F);
    }

    @Override
    protected float getDeathMaxRotation(EntityMedusaSnake entityLivingBaseIn) {
        return 0.0F;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMedusaSnake entity) {
        return TEXTURE;
    }
}
