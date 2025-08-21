package com.syming.castlevaniacraft.util.handler;

import com.syming.castlevaniacraft.client.gui.GuiAlchemyTable;
import com.syming.castlevaniacraft.container.ContainerAlchemyTable;
import com.syming.castlevaniacraft.tile.TileEntityAlchemyTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.jetbrains.annotations.Nullable;

public class GuiHandler implements IGuiHandler {
    @Override
    public @Nullable Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        if (ID == 2) {
            if (tile instanceof TileEntityAlchemyTable alchemyTable) {
                return new ContainerAlchemyTable(player.inventory, alchemyTable);
            }
        }
        return null;
    }

    @Override
    public @Nullable Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        if (ID == 2) {
            if (tile instanceof TileEntityAlchemyTable alchemyTable) {
                return new GuiAlchemyTable(player.inventory, alchemyTable);
            }
        }
        return null;
    }
}
