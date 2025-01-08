package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelCatoblepas;
import com.syming.minevania.entity.mobs.EntityCatoblepas;
import com.syming.minevania.util.MVReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderCatoblepas extends RenderLiving<EntityCatoblepas> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/catoblepas.png");

    public RenderCatoblepas(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCatoblepas(), 0.7F);
    }

    @Override
    protected float getDeathMaxRotation(EntityCatoblepas entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityCatoblepas entity) {
        return TEXTURE;
    }
}
