package fr.deltadesnoc.asgaria.init;

import fr.deltadesnoc.asgaria.Constants;
import fr.deltadesnoc.asgaria.armor.FarmArmor;
import fr.deltadesnoc.asgaria.items.ItemBasic;
import fr.deltadesnoc.asgaria.items.runes.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class ItemsInitialisation {

    public static List<Item> items = new ArrayList<>();

    /**
     * ARMOR
     */

    //FARM
    public static Item farm_helmet;
    public static Item farm_body;
    public static Item farm_legs;
    public static Item farm_boots;

    //STEEL
    public static Item steel_helmet;
    public static Item steel_body;
    public static Item steel_legs;
    public static Item steel_boots;

    //MYTHRIL
    public static Item mythril_helmet;
    public static Item mythril_body;
    public static Item mythril_legs;
    public static Item mythril_boots;

    //SHELL
    public static Item shell_helmet;
    public static Item shell_body;
    public static Item shell_legs;
    public static Item shell_boots;

    //FUR
    public static Item fur_helmet;
    public static Item fur_body;
    public static Item fur_legs;
    public static Item fur_boots;

    //DIVINE
    public static Item divine_helmet;
    public static Item divine_body;
    public static Item divine_legs;
    public static Item divine_boots;

    /**
     * ARMOR
     */

    /**
     * TOOLS
     */

    //STEEL
    public static Item steel_sword;
    public static Item steel_pickaxe;
    public static Item steel_axe;
    public static Item steel_shovel;
    public static Item steel_hoe;

    //MYTHRIL
    public static Item mythril_sword;
    public static Item mythril_pickaxe;
    public static Item mythril_axe;
    public static Item mythril_shovel;
    public static Item mythril_hoe;

    //DIVINE
    public static Item divine_sword;
    public static Item divine_pickaxe;
    public static Item divine_axe;
    public static Item divine_shovel;
    public static Item divine_hoe;

    /**
     * TOOLS
     */

    /**
     * WEAPONS
     */

    public static Item battleaxe_viking;
    public static Item sword_viking;
    public static Item shell_sword;
    public static Item mjolnir;
    public static Item fenrir_fang_dagger;
    public static Item odin_spear;
    public static Item loky_dagger;
    public static Item heimdall_sword;

    /**
     * WEAPONS
     */

    /**
     * RUNES
     */

    public static Item rune_fehu;
    public static Item rune_uruz;
    public static Item rune_thurisaz;
    public static Item rune_raido;
    public static Item rune_kaunan;
    public static Item rune_gebo;
    public static Item rune_jeran;
    public static Item rune_pertho;
    public static Item rune_sowilo;
    public static Item rune_berkanan;
    public static Item rune_ingwaz;
    public static Item rune_dagaz;

    /**
     * RUNES
     */

    public static Item divine_fragment;
    public static Item rune_fragment;
    public static Item mythril_ingot;
    public static Item steel_ingot;

    public static void init(){
        items.add(divine_fragment = new ItemBasic("divine_fragment"));
        items.add(rune_fragment = new ItemBasic("rune_fragment"));
        items.add(mythril_ingot = new ItemBasic("mythril_ingot"));
        items.add(steel_ingot = new ItemBasic("steel_ingot"));

        items.add(rune_fehu = new RuneFehu());
        items.add(rune_uruz = new RuneUruz());
        items.add(rune_thurisaz = new RuneThurisaz());
        items.add(rune_raido = new RuneRaido());
        items.add(rune_kaunan = new RuneKaunan());
        items.add(rune_gebo = new RuneGebo());
        items.add(rune_jeran = new RuneJeran());
        items.add(rune_pertho = new RunePertho());
        items.add(rune_sowilo = new RuneSowilo());
        items.add(rune_berkanan = new RuneBerkanan());
        items.add(rune_ingwaz = new RuneIngwaz());
        items.add(rune_dagaz = new RuneDagaz());

        items.add(farm_helmet = new FarmArmor("farm_helmet", 20, EntityEquipmentSlot.HEAD));
        items.add(farm_body = new FarmArmor("farm_body", 20, EntityEquipmentSlot.CHEST));
        items.add(farm_legs = new FarmArmor("farm_legs", 20, EntityEquipmentSlot.LEGS));
        items.add(farm_boots = new FarmArmor("farm_boots", 20, EntityEquipmentSlot.FEET));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        for(Item item : items) {
            e.getRegistry().registerAll(item);
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent e) {
        for(Item item : items) {
            registerRender(item);
        }
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
