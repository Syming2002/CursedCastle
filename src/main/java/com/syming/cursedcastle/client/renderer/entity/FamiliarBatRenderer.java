package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.FamiliarBatModel;
import com.syming.cursedcastle.entity.mob.bat.CCBatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FamiliarBatRenderer extends CCBatRenderer {

    private static final ResourceLocation FAMILIAR_BAT_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/familiar_bat.png");

    public FamiliarBatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FamiliarBatModel(pContext.bakeLayer(FamiliarBatModel.FAMILIAR_BAT_LAYER)));
    }

    @Override
    public ResourceLocation getTextureLocation(CCBatEntity ccBatEntity) {
        return FAMILIAR_BAT_TEXTURE;
    }
}
