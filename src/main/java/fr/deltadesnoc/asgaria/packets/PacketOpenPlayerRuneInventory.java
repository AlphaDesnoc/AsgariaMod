package fr.deltadesnoc.asgaria.packets;

import fr.deltadesnoc.asgaria.Asgaria;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenPlayerRuneInventory implements IMessage, IMessageHandler<PacketOpenPlayerRuneInventory, IMessage> {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public IMessage onMessage(PacketOpenPlayerRuneInventory message, MessageContext ctx) {

        WorldServer server = (WorldServer) ctx.getServerHandler().player.world;
        server.addScheduledTask(() -> {
            (ctx.getServerHandler()).player.openContainer.onContainerClosed((ctx.getServerHandler()).player);
            (ctx.getServerHandler()).player.openGui(Asgaria.instance, 0, (ctx.getServerHandler()).player.world, 0, 0, 0);
        });

        return null;
    }
}
