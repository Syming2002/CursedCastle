package com.syming.cursedcastle.entity.client.familiarbat;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.VampireBatEntity;
import com.syming.cursedcastle.entity.mob.friendly.FamiliarBatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class FamiliarBatRenderer extends LivingEntityRenderer<FamiliarBatEntity, FamiliarBatModel> {

    private static final ResourceLocation FAMILIAR_BAT_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/familiar_bat.png");

    public FamiliarBatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FamiliarBatModel(pContext.bakeLayer(FamiliarBatModel.FAMILIAR_BAT_LAYER)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(FamiliarBatEntity familiarBatEntity) {
        return FAMILIAR_BAT_TEXTURE;
    }
}
