package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.HarpyModel;
import com.syming.cursedcastle.entity.monster.HarpyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HarpyRenderer extends MobRenderer<HarpyEntity, HarpyModel> {
    public static final ResourceLocation HARPY_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/entity/harpy.png");

    public HarpyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HarpyModel(pContext.bakeLayer(HarpyModel.HARPY_LAYER)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(HarpyEntity pEntity) {
        return HARPY_TEXTURE;
    }
}
