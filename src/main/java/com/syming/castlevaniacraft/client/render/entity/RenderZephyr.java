package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelZephyr;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityZephyr;
import com.syming.castlevaniacraft.util.MVReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderZephyr extends RenderLiving<EntityZephyr> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/zephyr.png");

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
