package fr.deltadesnoc.asgaria.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RuneContainerProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider {
    private final RuneContainer runeContainer;

    public RuneContainerProvider(RuneContainer runeContainer) {
        this.runeContainer = runeContainer;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return (capability == AsgariaCapabilities.CAPABILITY_RUNE);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == AsgariaCapabilities.CAPABILITY_RUNE)
            return (T)this.runeContainer;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return this.runeContainer.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.runeContainer.deserializeNBT(nbt);
    }
}
