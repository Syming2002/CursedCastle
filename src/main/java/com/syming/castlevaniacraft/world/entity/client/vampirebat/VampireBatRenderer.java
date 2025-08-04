package com.syming.castlevaniacraft.world.entity.client.vampirebat;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.world.entity.mob.VampireBatEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class VampireBatRenderer extends LivingEntityRenderer<VampireBatEntity, VampireBatModel> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(CastlevaniaCraftMod.MOD_ID, "textures/entity/vampire_bat.png");

    public VampireBatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new VampireBatModel(pContext.bakeLayer(ModelLayers.BAT)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(VampireBatEntity vampireBatEntity) {
        return TEXTURE;
    }
}
