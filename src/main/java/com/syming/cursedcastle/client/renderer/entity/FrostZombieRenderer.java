package com.syming.cursedcastle.client.renderer.entity;

import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.FrostZombieModel;
import com.syming.cursedcastle.entity.mob.zombie.FrostZombieEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.List;

public class FrostZombieRenderer extends MobRenderer<FrostZombieEntity, FrostZombieModel> {

    public static final ResourceLocation FROST_ZOMBIE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/zombie/frost_zombie.png");


    public FrostZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FrostZombieModel(), 0.5F);
    }

    @Override
    public void render(FrostZombieEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FrostZombieEntity pEntity) {
        return FROST_ZOMBIE_TEXTURE;
    }


}
