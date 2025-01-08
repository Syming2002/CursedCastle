package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelMedusa;
import com.syming.minevania.entity.mobs.boss.EntityMedusa;
import com.syming.minevania.util.MVReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderMedusa extends RenderLiving<EntityMedusa> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/medusa.png");

    public RenderMedusa(RenderManager renderManager) {
        super(renderManager, new ModelMedusa(), 0.75F);
    }

    @Override
    protected float getDeathMaxRotation(EntityMedusa entityLivingBaseIn) {
        return 0.0F;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMedusa entity) {
        return TEXTURE;
    }
}
