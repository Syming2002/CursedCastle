package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.MedusaSnakeModel;
import com.syming.cursedcastle.entity.monster.MedusaSnakeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MedusaSnakeRenderer extends MobRenderer<MedusaSnakeEntity, MedusaSnakeModel> {

    public static final ResourceLocation MEDUSA_SNAKE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/medusa_snake.png");

    public MedusaSnakeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MedusaSnakeModel(pContext.bakeLayer(MedusaSnakeModel.MEDUSA_SNAKE_LAYER)), 0.15F);
    }

    @Override
    public ResourceLocation getTextureLocation(MedusaSnakeEntity pEntity) {
        return MEDUSA_SNAKE_TEXTURE;
    }
}
