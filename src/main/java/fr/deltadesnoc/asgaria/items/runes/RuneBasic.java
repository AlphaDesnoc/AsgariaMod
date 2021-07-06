package fr.deltadesnoc.asgaria.items.runes;

import fr.deltadesnoc.asgaria.capabilities.AsgariaType;
import fr.deltadesnoc.asgaria.capabilities.IAsgaria;
import fr.deltadesnoc.asgaria.items.ItemBasic;
import net.minecraft.item.ItemStack;

public class RuneBasic extends ItemBasic implements IAsgaria {

    public RuneBasic(String name) {
        super(name);
    }

    @Override
    public AsgariaType getAsgariaType(ItemStack paramItemStack) {
        return AsgariaType.RUNE;
    }
}
