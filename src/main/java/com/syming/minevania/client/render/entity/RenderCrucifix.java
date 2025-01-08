package com.syming.minevania.client.render.entity;

import com.syming.minevania.client.models.entity.ModelCrucifix;
import com.syming.minevania.entity.EntityCrucifix;
import com.syming.minevania.util.MVReferences;
import net.minecraft.block.BlockPlanks;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

@SideOnly(Side.CLIENT)
public class RenderCrucifix extends RenderLiving<EntityCrucifix> {

    private static final ResourceLocation OAK_TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/crucifix_oak.png");
    private static final ResourceLocation SPRUCE_TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/crucifix_spruce.png");
    private static final ResourceLocation BIRCH_TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/crucifix_birch.png");
    private static final ResourceLocation JUNGLE_TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/crucifix_jungle.png");
    private static final ResourceLocation ACACIA_TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/crucifix_acacia.png");
    private static final ResourceLocation DARK_OAK_TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/crucifix_dark_oak.png");


    public RenderCrucifix(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelCrucifix(), 0.0F);
    }

    @Override
    protected void applyRotations(EntityCrucifix entityLiving, float ageInTicks, float rotationYaw, float partialTicks)
    {
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        float f = (float)(entityLiving.world.getTotalWorldTime() - entityLiving.punchCooldown) + partialTicks;

        if (f < 5.0F)
        {
            GlStateManager.rotate(MathHelper.sin(f / 1.5F * (float)Math.PI) * 3.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    @Override
    protected boolean canRenderName(EntityCrucifix entity)
    {
        return entity.getAlwaysRenderNameTag();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityCrucifix entity) {
        BlockPlanks.EnumType woodType = entity.getWoodType();
        switch (woodType) {
            case OAK:
                return OAK_TEXTURE;
            case SPRUCE:
                return SPRUCE_TEXTURE;
            case BIRCH:
                return BIRCH_TEXTURE;
            case JUNGLE:
                return JUNGLE_TEXTURE;
            case ACACIA:
                return ACACIA_TEXTURE;
            case DARK_OAK:
                return DARK_OAK_TEXTURE;
            default:
                return OAK_TEXTURE;
        }
    }
}
