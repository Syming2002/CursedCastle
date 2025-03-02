package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelDracula;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

@SideOnly(Side.CLIENT)
public class RenderDracula extends RenderLiving<EntityDracula> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/dracula.png");

    public RenderDracula(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDracula(), 0.5F);
    }

    @Override
    protected float getDeathMaxRotation(EntityDracula entityLivingBaseIn) {
        return 0.0F;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDracula entity) {
        return TEXTURE;
    }
}
