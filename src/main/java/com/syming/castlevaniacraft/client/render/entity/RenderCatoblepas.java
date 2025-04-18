package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelCatoblepas;
import com.syming.castlevaniacraft.entity.mobs.EntityCatoblepas;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderCatoblepas extends RenderLiving<EntityCatoblepas> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/catoblepas.png");

    public RenderCatoblepas(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCatoblepas(), 0.7F);
    }

    @Override
    protected float getDeathMaxRotation(EntityCatoblepas entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityCatoblepas entity) {
        return TEXTURE;
    }
}
