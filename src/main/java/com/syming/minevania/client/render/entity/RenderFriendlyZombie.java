package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelFriendlyZombie;
import com.syming.minevania.entity.mobs.friendly.EntityFriendlyZombie;
import com.syming.minevania.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFriendlyZombie extends RenderBiped<EntityFriendlyZombie> {

    private static final ResourceLocation FRIENDLY_ZOMBIE_TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/friendly_zombie.png");

    public RenderFriendlyZombie(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelFriendlyZombie(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFriendlyZombie friendlyZombie)
    {
        return FRIENDLY_ZOMBIE_TEXTURE;
    }
}
