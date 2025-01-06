package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelZephyr;
import com.syming.minevania.entity.mobs.boss.EntityZephyr;
import com.syming.minevania.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderZephyr extends RenderLiving<EntityZephyr> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/zephyr.png");

    public RenderZephyr(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZephyr(), 0.5F);
    }

    @Override
    protected float getDeathMaxRotation(EntityZephyr entityLivingBaseIn) {
        return 0.0F;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityZephyr entity) {
        return TEXTURE;
    }
}
