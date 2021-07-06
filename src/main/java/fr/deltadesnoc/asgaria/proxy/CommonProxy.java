package fr.deltadesnoc.asgaria.proxy;

import fr.deltadesnoc.asgaria.container.ContainerPlayerExpanded;
import fr.deltadesnoc.asgaria.event.EventHandlerEntity;
import fr.deltadesnoc.asgaria.event.EventHandlerItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy  implements IGuiHandler {

    public void preInit(){

    }
    public void init(){

    }
    public void postInit(){

    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new ContainerPlayerExpanded(player.inventory, !world.isRemote, player);
        }
        return null;
    }

    public World getClientWorld() {
        return null;
    }

    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(new EventHandlerEntity());
        MinecraftForge.EVENT_BUS.register(new EventHandlerItem());
    }
}
