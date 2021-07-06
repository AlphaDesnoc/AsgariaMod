package fr.deltadesnoc.asgaria.proxy;

import fr.deltadesnoc.asgaria.client.ClientEventHandler;
import fr.deltadesnoc.asgaria.client.gui.GuiPlayerExpanded;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public static final KeyBinding KEY_RUNES = new KeyBinding("keybind.runesinventory", 48, "key.categories.inventory");

    public void registerEventHandlers() {
        super.registerEventHandlers();
        ClientRegistry.registerKeyBinding(KEY_RUNES);
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof net.minecraft.client.multiplayer.WorldClient)
            switch (ID) {
                case 0:
                    return new GuiPlayerExpanded(player);
            }
        return null;
    }

    public World getClientWorld() {
        return (World)(FMLClientHandler.instance().getClient()).world;
    }

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }

}
