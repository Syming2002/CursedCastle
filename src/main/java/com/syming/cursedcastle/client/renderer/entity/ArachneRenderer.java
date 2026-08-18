package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.ArachneModel;
import com.syming.cursedcastle.entity.monster.arachne.ArachneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ArachneRenderer extends MobRenderer<ArachneEntity, ArachneModel> {

    public static final ResourceLocation ARACHNE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID,"textures/entity/arachne.png");


    public ArachneRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ArachneModel(pContext.bakeLayer(ArachneModel.ARACHNE_LAYER)), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(ArachneEntity pEntity) {
        return ARACHNE_TEXTURE;
    }
}
