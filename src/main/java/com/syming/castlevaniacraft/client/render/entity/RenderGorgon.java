package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelGorgon;
import com.syming.castlevaniacraft.entity.mobs.EntityGorgon;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderGorgon extends RenderLiving<EntityGorgon> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/gorgon.png");

    public RenderGorgon(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGorgon(), 0.7F);
    }

    @Override
    protected float getDeathMaxRotation(EntityGorgon entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityGorgon entity) {
        return TEXTURE;
    }
}
