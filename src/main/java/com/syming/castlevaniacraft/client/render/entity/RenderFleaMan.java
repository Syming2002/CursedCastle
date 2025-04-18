package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelFleaMan;
import com.syming.castlevaniacraft.entity.mobs.EntityFleaMan;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderFleaMan extends RenderLiving<EntityFleaMan> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/flea_man.png");

    public RenderFleaMan(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelFleaMan(), 0.25F);
    }

    @Override
    protected void preRenderCallback(EntityFleaMan entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.15F, 1.15F, 1.15F);
    }

    @Override
    protected float getDeathMaxRotation(EntityFleaMan entityLivingBaseIn) {
        return 0.0F;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFleaMan entity) {
        return TEXTURE;
    }
}
