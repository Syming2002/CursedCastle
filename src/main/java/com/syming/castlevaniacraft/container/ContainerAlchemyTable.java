package com.syming.castlevaniacraft.container;

import com.syming.castlevaniacraft.tile.TileEntityAlchemyTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAlchemyTable extends Container {
    private final TileEntityAlchemyTable tile;

    public ContainerAlchemyTable(InventoryPlayer playerInv, TileEntityAlchemyTable tile) {
        this.tile = tile;

        this.addSlotToContainer(new Slot(tile, 0, 81, 7));

        this.addSlotToContainer(new Slot(tile, 1, 116, 15));

        this.addSlotToContainer(new Slot(tile, 2, 126, 51));

        this.addSlotToContainer(new Slot(tile, 3, 116, 88));

        this.addSlotToContainer(new Slot(tile, 4, 81, 97));

        this.addSlotToContainer(new Slot(tile, 5, 45, 88));

        this.addSlotToContainer(new Slot(tile, 6, 36, 51));

        this.addSlotToContainer(new Slot(tile, 7, 45, 15));



        this.addSlotToContainer(new Slot(tile, 8, 81, 51) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlotToContainer(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 128 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(playerInv, col, 8 + col * 18, 186));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tile.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            ItemStack stackCopy = stackInSlot.copy();

            final int INPUT_START = 0, INPUT_END = 7;
            final int OUTPUT_SLOT = 8;
            final int PLAYER_INV_START = 9, PLAYER_INV_END = 35;
            final int PLAYER_HOTBAR_START = 36, PLAYER_HOTBAR_END = 44;

            if (index == OUTPUT_SLOT) {
                if (!this.mergeItemStack(stackInSlot, PLAYER_INV_START, PLAYER_HOTBAR_END + 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stackInSlot, stackCopy);
            }
            else if (index >= PLAYER_INV_START) {
//                if (isValidInput(stackInSlot)) {
                    if (!this.mergeItemStack(stackInSlot, INPUT_START, INPUT_END + 1, false)) {
                        return ItemStack.EMPTY;
                    }
//                }
            }
            else if (index >= INPUT_START && index <= INPUT_END) {
                if (!this.mergeItemStack(stackInSlot, PLAYER_INV_START, PLAYER_HOTBAR_END + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stackInSlot.getCount() == stackCopy.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stackInSlot);
            return stackCopy;
        }

        return ItemStack.EMPTY;
    }
}
