package fr.deltadesnoc.asgaria.container;

import fr.deltadesnoc.asgaria.capabilities.AsgariaCapabilities;
import fr.deltadesnoc.asgaria.capabilities.IAsgaria;
import fr.deltadesnoc.asgaria.capabilities.IAsgariaItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotRune extends SlotItemHandler {

    int runeSlot;
    EntityPlayer player;

    public SlotRune(EntityPlayer player, IAsgariaItemHandler itemHandler, int slot, int xPosition, int yPosition) {
        super(itemHandler, slot, xPosition, yPosition);
        this.runeSlot = slot;
        this.player = player;
    }

    public boolean isItemValid(ItemStack stack) {
        return ((IAsgariaItemHandler)getItemHandler()).isItemValidForSlot(this.runeSlot, stack, (EntityLivingBase)this.player);
    }

    public boolean canTakeStack(EntityPlayer player) {
        ItemStack stack = getStack();
        if (stack.isEmpty())
            return false;
        IAsgaria bauble = (IAsgaria)stack.getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null);
        return bauble.canUnequip(stack, (EntityLivingBase)player);
    }

    public ItemStack onTake(EntityPlayer playerIn, ItemStack stack) {
        if (!getHasStack() && !((IAsgariaItemHandler)getItemHandler()).isEventBlocked() && stack
                .hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null))
            ((IAsgaria)stack.getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null)).onUnequipped(stack, (EntityLivingBase)playerIn);
        super.onTake(playerIn, stack);
        return stack;
    }

    public void putStack(ItemStack stack) {
        if (getHasStack() && !ItemStack.areItemStacksEqual(stack, getStack()) &&
                !((IAsgariaItemHandler)getItemHandler()).isEventBlocked() &&
                getStack().hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null))
            ((IAsgaria)getStack().getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null)).onUnequipped(getStack(), (EntityLivingBase)this.player);
        ItemStack oldstack = getStack().copy();
        super.putStack(stack);
        if (getHasStack() && !ItemStack.areItemStacksEqual(oldstack, getStack()) &&
                !((IAsgariaItemHandler)getItemHandler()).isEventBlocked() &&
                getStack().hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null))
            ((IAsgaria)getStack().getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null)).onEquipped(getStack(), (EntityLivingBase)this.player);
    }

    public int getSlotStackLimit() {
        return 1;
    }

}
