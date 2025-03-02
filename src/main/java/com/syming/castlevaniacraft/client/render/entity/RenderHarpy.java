package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelHarpy;
import com.syming.castlevaniacraft.entity.mobs.EntityHarpy;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderHarpy extends RenderLiving<EntityHarpy> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/harpy.png");

    public RenderHarpy(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHarpy(), 0.75F);
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityHarpy entity) {
        return TEXTURE;
    }
}
