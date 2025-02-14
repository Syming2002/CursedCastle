package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelBloodSkeleton;
import com.syming.castlevaniacraft.entity.mobs.EntityBloodSkeleton;

import com.syming.castlevaniacraft.util.MVReferences;
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
