package fr.deltadesnoc.asgaria.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class AsgariaCapabilities {

    @CapabilityInject(IAsgariaItemHandler.class)
    public static final Capability<IAsgariaItemHandler> CAPABILITY_RUNE = null;

    @CapabilityInject(IAsgaria.class)
    public static final Capability<IAsgaria> CAPABILITY_ITEM_RUNE = null;

    public static class CapabilitiesRunes<T extends IAsgariaItemHandler> implements Capability.IStorage<IAsgariaItemHandler> {
        public NBTBase writeNBT(Capability<IAsgariaItemHandler> capability, IAsgariaItemHandler instance, EnumFacing side) {
            return null;
        }

        public void readNBT(Capability<IAsgariaItemHandler> capability, IAsgariaItemHandler instance, EnumFacing side, NBTBase nbt) {}
    }

    public static class CapabilityItemBaubleStorage implements Capability.IStorage<IAsgaria> {
        public NBTBase writeNBT(Capability<IAsgaria> capability, IAsgaria instance, EnumFacing side) {
            return null;
        }

        public void readNBT(Capability<IAsgaria> capability, IAsgaria instance, EnumFacing side, NBTBase nbt) {}
    }

}
