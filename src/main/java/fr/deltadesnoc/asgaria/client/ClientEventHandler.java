package fr.deltadesnoc.asgaria.client;

import fr.deltadesnoc.asgaria.packets.PacketHandler;
import fr.deltadesnoc.asgaria.packets.PacketOpenPlayerRuneInventory;
import fr.deltadesnoc.asgaria.proxy.ClientProxy;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientEventHandler {

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.START &&
                ClientProxy.KEY_RUNES.isPressed() && (FMLClientHandler.instance().getClient()).inGameHasFocus)
            PacketHandler.INSTANCE.sendToServer(new PacketOpenPlayerRuneInventory());
    }

}
