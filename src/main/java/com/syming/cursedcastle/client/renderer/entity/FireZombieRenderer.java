package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.FireZombieModel;
import com.syming.cursedcastle.client.model.FrostZombieModel;
import com.syming.cursedcastle.entity.mob.zombie.FireZombieEntity;
import com.syming.cursedcastle.entity.mob.zombie.FrostZombieEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FireZombieRenderer extends MobRenderer<FireZombieEntity, FireZombieModel> {

    public static final ResourceLocation FIRE_ZOMBIE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/zombie/fire_zombie.png");


    public FireZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FireZombieModel(), 0.5F);
    }

    @Override
    public void render(FireZombieEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FireZombieEntity pEntity) {
        return FIRE_ZOMBIE_TEXTURE;
    }


}
