package fr.deltadesnoc.asgaria.items;

import fr.deltadesnoc.asgaria.Asgaria;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ItemBasic extends Item {

    public ItemBasic(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Asgaria.asgariaItems);
    }

}
