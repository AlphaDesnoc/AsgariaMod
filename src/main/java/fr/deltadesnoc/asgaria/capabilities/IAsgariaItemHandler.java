package fr.deltadesnoc.asgaria.capabilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

public interface IAsgariaItemHandler extends IItemHandlerModifiable {
    boolean isItemValidForSlot(int paramInt, ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase);

    boolean isEventBlocked();

    void setEventBlock(boolean paramBoolean);

    boolean isChanged(int paramInt);

    void setChanged(int paramInt, boolean paramBoolean);

    void setPlayer(EntityLivingBase paramEntityLivingBase);
}
