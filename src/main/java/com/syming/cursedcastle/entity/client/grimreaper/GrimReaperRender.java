package com.syming.cursedcastle.entity.client.grimreaper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.client.dracula.DraculaModel;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class GrimReaperRender extends MobRenderer<GrimReaperEntity, GrimReaperModel> {
    public static final ResourceLocation GRIM_REAPER_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/grim_reaper.png");

    public GrimReaperRender(EntityRendererProvider.Context pContext) {
        super(pContext, new GrimReaperModel(), 0.5F);

        addLayer(new DeathScytheInHandLayer(this, pContext.getItemInHandRenderer()));
    }

    @Override
    protected float getFlipDegrees(GrimReaperEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(GrimReaperEntity pEntity) {
        return GRIM_REAPER_TEXTURE;
    }
}
