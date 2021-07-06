package fr.deltadesnoc.asgaria.capabilities;

import fr.deltadesnoc.asgaria.inventory.AsgariaInventoryWrapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;

public class AsgariaAPI {
    public static IAsgariaItemHandler getRuneHandler(EntityPlayer player) {
        IAsgariaItemHandler handler = (IAsgariaItemHandler)player.getCapability(AsgariaCapabilities.CAPABILITY_RUNE, null);
        handler.setPlayer((EntityLivingBase)player);
        return handler;
    }

    @Deprecated
    public static IInventory getRune(EntityPlayer player) {
        IAsgariaItemHandler handler = (IAsgariaItemHandler)player.getCapability(AsgariaCapabilities.CAPABILITY_RUNE, null);
        handler.setPlayer((EntityLivingBase)player);
        return (IInventory)new AsgariaInventoryWrapper(handler, player);
    }

    public static int isRuneEquipped(EntityPlayer player, Item bauble) {
        IAsgariaItemHandler handler = getRuneHandler(player);
        for (int a = 0; a < handler.getSlots(); a++) {
            if (!handler.getStackInSlot(a).isEmpty() && handler.getStackInSlot(a).getItem() == bauble)
                return a;
        }
        return -1;
    }
}