package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelDraculaSecondForm;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDraculaSecondForm;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

@SideOnly(Side.CLIENT)
public class RenderDraculaSecondForm extends RenderLiving<EntityDraculaSecondForm> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/dracula_second_form.png");

    public RenderDraculaSecondForm(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDraculaSecondForm(), 1.5F);
    }

    @Override
    protected float getDeathMaxRotation(EntityDraculaSecondForm entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    protected void preRenderCallback(EntityDraculaSecondForm entitylivingbaseIn, float partialTickTime) {
//        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

//    @Override
//    public void doRender(EntityDraculaSecondForm entity, double x, double y, double z, float entityYaw, float partialTicks) {
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
//    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDraculaSecondForm entity) {
        return TEXTURE;
    }
}
