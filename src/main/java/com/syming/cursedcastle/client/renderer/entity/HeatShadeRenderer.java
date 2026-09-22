package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.ShadeModel;
import com.syming.cursedcastle.entity.monster.shade.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HeatShadeRenderer extends MobRenderer<ShadeEntity, ShadeModel<ShadeEntity>> {

    public static final ResourceLocation HEAT_SHADE_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/entity/shade/heatshade/heat_shade.png");

    public HeatShadeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ShadeModel<>(pContext.bakeLayer(ShadeModel.SHADE_LAYER)), 0.5F);

        addLayer(new HeatShadeFlameLayer<>(this));
    }

    @Override
    protected float getFlipDegrees(ShadeEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(ShadeEntity pEntity) {
        return HEAT_SHADE_TEXTURE;
    }
}
