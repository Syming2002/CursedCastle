package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.VampireBatModel;
import com.syming.cursedcastle.entity.mob.bat.CCBatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class VampireBatRenderer extends CCBatRenderer {

    private static final ResourceLocation VAMPIRE_BAT_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/vampire_bat.png");

    public VampireBatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new VampireBatModel(pContext.bakeLayer(VampireBatModel.VAMPIRE_BAT_LAYER)));
    }

    @Override
    public ResourceLocation getTextureLocation(CCBatEntity ccBatEntity) {
        return VAMPIRE_BAT_TEXTURE;
    }
}
