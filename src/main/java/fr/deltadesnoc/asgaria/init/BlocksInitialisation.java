package fr.deltadesnoc.asgaria.init;

import fr.deltadesnoc.asgaria.Constants;
import fr.deltadesnoc.asgaria.blocks.BlockBasic;
import fr.deltadesnoc.asgaria.blocks.ore.DivineOreBlock;
import fr.deltadesnoc.asgaria.blocks.ore.MythrilOreBlock;
import fr.deltadesnoc.asgaria.blocks.ore.RuneOreBlock;
import fr.deltadesnoc.asgaria.blocks.ore.SteelOreBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class BlocksInitialisation {

    public static List<Block> blocks = new ArrayList<>();

    public static Block rune_ore;
    public static Block steel_ore;
    public static Block steel_block;
    public static Block mythril_ore;
    public static Block mythril_block;
    public static Block divine_ore;
    public static Block divine_block;

    public static void init(){
        blocks.add(rune_ore = new RuneOreBlock());
        blocks.add(steel_ore = new SteelOreBlock());
        blocks.add(steel_block = new BlockBasic("steel_block", Material.IRON, 10.0f, 5.0f, "pickaxe", 2));
        blocks.add(mythril_ore = new MythrilOreBlock());
        blocks.add(mythril_block = new BlockBasic("mythril_block", Material.IRON, 10.0f, 5.0f, "pickaxe", 2));
        blocks.add(divine_ore = new DivineOreBlock());
        blocks.add(divine_block = new BlockBasic("divine_block", Material.IRON, 10.0f, 5.0f, "pickaxe", 2));
    }

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> e) {
        for(Block block : blocks) {
            e.getRegistry().registerAll(block);
        }
    }

    @SubscribeEvent
    public static void registerItemBlock(RegistryEvent.Register<Item> e) {
        for(Block block : blocks) {
            e.getRegistry().registerAll(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent e) {
        for(Block block : blocks) {
            registerRender(Item.getItemFromBlock(block));
        }
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
