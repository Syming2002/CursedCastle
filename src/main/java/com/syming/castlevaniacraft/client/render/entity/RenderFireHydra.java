package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelFireHydra;
import com.syming.castlevaniacraft.entity.projectiles.EntityFireHydra;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderFireHydra extends Render<EntityFireHydra> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/fire_hydra.png");
    public ModelFireHydra modelFireHydra;

    public RenderFireHydra(RenderManager rendermanagerIn) {
        super(rendermanagerIn);
        this.modelFireHydra = new ModelFireHydra();
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityFireHydra entity) {
        return TEXTURE;
    }
}
