package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelBloodSkeleton;
import com.syming.minevania.entity.mobs.EntityBloodSkeleton;

import com.syming.minevania.util.MVReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.Nullable;

public class RenderBloodSkeleton extends RenderLiving<EntityBloodSkeleton> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/blood_skeleton.png");

    public RenderBloodSkeleton(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBloodSkeleton(), 0.5F);

        this.addLayer(new LayerHeldItem(this));
    }

    @Override
    protected float getDeathMaxRotation(EntityBloodSkeleton entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityBloodSkeleton entity) {
        return TEXTURE;
    }
}
