package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelDagger;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityDagger;
import com.syming.castlevaniacraft.item.projectiles.subweapons.ItemDagger;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.syming.castlevaniacraft.util.handler.RegistryHandler.NETHERITE;

@SideOnly(Side.CLIENT)
public class RenderDagger<T extends EntityDagger> extends Render<T> {

    private ModelDagger modelDagger;
    private int modifier;

    public static final ResourceLocation WOODEN_TEXTURE = new ResourceLocation(CCReferences.MODID,"textures/entity/wooden_dagger.png");
    public static final ResourceLocation STONE_TEXTURE = new ResourceLocation(CCReferences.MODID,"textures/entity/stone_dagger.png");
    public static final ResourceLocation IRON_TEXTURE = new ResourceLocation(CCReferences.MODID,"textures/entity/iron_dagger.png");
    public static final ResourceLocation GOLDEN_TEXTURE = new ResourceLocation(CCReferences.MODID,"textures/entity/golden_dagger.png");
    public static final ResourceLocation DIAMOND_TEXTURE = new ResourceLocation(CCReferences.MODID,"textures/entity/diamond_dagger.png");
    public static final ResourceLocation NETHERITE_TEXTURE = new ResourceLocation(CCReferences.MODID,"textures/entity/netherite_dagger.png");

    public RenderDagger(RenderManager manager) {
        this(manager, 0);
        this.modelDagger = new ModelDagger();
    }

    public RenderDagger(RenderManager renderManager, int rotationModifier) {
        super(renderManager);
        this.modifier = rotationModifier;
    }

    @Override
    public void doRender(T bullet, double x, double y, double z, float yaw, float partialTicks) {
        super.doRender(bullet, x, y, z, yaw, partialTicks);
        GL11.glPushMatrix();
        bindTexture(getEntityTexture(bullet));
        GL11.glTranslated(x, y, z);
        GL11.glRotated(bullet.prevRotationYaw + (bullet.rotationYaw - bullet.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotated(bullet.prevRotationPitch + (bullet.rotationPitch - bullet.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        modelDagger.render(bullet, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(T entityDagger) {
        ItemStack stack = entityDagger.getItemStack();
        if (stack.getItem() instanceof ItemDagger) {
            Item.ToolMaterial material = ((ItemDagger) stack.getItem()).getMaterial();
            if (Objects.requireNonNull(material) == Item.ToolMaterial.WOOD) {
                return WOODEN_TEXTURE;
            } else if (material == Item.ToolMaterial.STONE) {
                return STONE_TEXTURE;
            } else if (material == Item.ToolMaterial.IRON) {
                return IRON_TEXTURE;
            } else if (material == Item.ToolMaterial.GOLD) {
                return GOLDEN_TEXTURE;
            } else if (material == Item.ToolMaterial.DIAMOND) {
                return DIAMOND_TEXTURE;
            } else if (material == NETHERITE) {
                return NETHERITE_TEXTURE;
            }
        }
        return WOODEN_TEXTURE;
    }

}
