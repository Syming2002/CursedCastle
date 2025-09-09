package com.syming.cursedcastle.entity.client.vampirebat;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.VampireBatEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class VampireBatRenderer extends LivingEntityRenderer<VampireBatEntity, VampireBatModel> {

    private static final ResourceLocation VAMPIRE_BAT_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/vampire_bat.png");

    public VampireBatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new VampireBatModel(pContext.bakeLayer(VampireBatModel.VAMPIRE_BAT_LAYER)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(VampireBatEntity vampireBatEntity) {
        return VAMPIRE_BAT_TEXTURE;
    }
}
