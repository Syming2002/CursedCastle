package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelRusalka;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityRusalka;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderRusalka extends RenderLiving<EntityRusalka> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/rusalka.png");
    public RenderRusalka(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRusalka(), 0.5F);
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityRusalka entity) {
        return TEXTURE;
    }
}
