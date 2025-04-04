package com.syming.castlevaniacraft.client.gui;

import com.syming.castlevaniacraft.container.ContainerAlchemyTable;
import com.syming.castlevaniacraft.tile.TileEntityAlchemyTable;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAlchemyTable extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/gui/alchemy_table.png");
    private final InventoryPlayer playerInv;
    private final TileEntityAlchemyTable tile;

    public GuiAlchemyTable(InventoryPlayer playerInv, TileEntityAlchemyTable tileEntityAlchemyTable) {
        super(new ContainerAlchemyTable(playerInv, tileEntityAlchemyTable));

        this.playerInv = playerInv;
        this.tile = tileEntityAlchemyTable;
        this.xSize = 176;
        this.ySize = 211;

    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.renderHoveredToolTip(mouseX, mouseY);

    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.tile.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
