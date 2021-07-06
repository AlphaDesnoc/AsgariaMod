package fr.deltadesnoc.asgaria.event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import fr.deltadesnoc.asgaria.capabilities.AsgariaCapabilities;
import fr.deltadesnoc.asgaria.capabilities.IAsgaria;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerItem {

    private static ResourceLocation capabilityResourceLocation = new ResourceLocation("asgaria", "rune_cap");

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void itemCapabilityAttach(AttachCapabilitiesEvent<ItemStack> event) {
        final ItemStack stack = (ItemStack)event.getObject();
        if (stack.isEmpty() || !(stack.getItem() instanceof IAsgaria) || stack.hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null) || event
                .getCapabilities().values().stream().anyMatch(c -> c.hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null)))
            return;
        event.addCapability(capabilityResourceLocation, new ICapabilityProvider() {
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                return (capability == AsgariaCapabilities.CAPABILITY_ITEM_RUNE);
            }

            @Nullable
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                return (capability == AsgariaCapabilities.CAPABILITY_ITEM_RUNE) ? (T)AsgariaCapabilities.CAPABILITY_ITEM_RUNE
                        .cast((IAsgaria) stack.getItem()) : null;
            }
        });
    }

}
