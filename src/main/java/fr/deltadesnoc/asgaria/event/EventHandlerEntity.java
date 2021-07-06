package fr.deltadesnoc.asgaria.event;

import fr.deltadesnoc.asgaria.capabilities.*;
import fr.deltadesnoc.asgaria.packets.PacketHandler;
import fr.deltadesnoc.asgaria.packets.PacketSync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.*;

public class EventHandlerEntity {
    private HashMap<UUID, ItemStack[]> runeSync = (HashMap)new HashMap<>();

    @SubscribeEvent
    public void cloneCapabilitiesEvent(PlayerEvent.Clone event) {
        try {
            RuneContainer bco = (RuneContainer) AsgariaAPI.getRuneHandler(event.getOriginal());
            NBTTagCompound nbt = bco.serializeNBT();
            RuneContainer bcn = (RuneContainer)AsgariaAPI.getRuneHandler(event.getEntityPlayer());
            bcn.deserializeNBT(nbt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer)
            event.addCapability(new ResourceLocation("asgaria", "container"), (ICapabilityProvider)new RuneContainerProvider(new RuneContainer()));
    }

    @SubscribeEvent
    public void playerJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            syncSlots((EntityPlayer)player, (Collection)Collections.singletonList(player));
        }
    }

    @SubscribeEvent
    public void onStartTracking(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();
        if (target instanceof EntityPlayerMP)
            syncSlots((EntityPlayer)target, Collections.singletonList(event.getEntityPlayer()));
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event) {
        this.runeSync.remove(event.player.getUniqueID());
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            IAsgariaItemHandler runes = AsgariaAPI.getRuneHandler(player);
            for (int i = 0; i < runes.getSlots(); i++) {
                ItemStack stack = runes.getStackInSlot(i);
                IAsgaria rune = (IAsgaria)stack.getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null);
                if (rune != null)
                    rune.onWornTick(stack, (EntityLivingBase)player);
            }
            if (!player.world.isRemote)
                syncBaubles(player, runes);
        }
    }

    private void syncBaubles(EntityPlayer player, IAsgariaItemHandler runes) {
        ItemStack[] items = this.runeSync.get(player.getUniqueID());
        if (items == null) {
            items = new ItemStack[runes.getSlots()];
            Arrays.fill((Object[])items, ItemStack.EMPTY);
            this.runeSync.put(player.getUniqueID(), items);
        }
        if (items.length != runes.getSlots()) {
            ItemStack[] old = items;
            items = new ItemStack[runes.getSlots()];
            System.arraycopy(old, 0, items, 0, Math.min(old.length, items.length));
            this.runeSync.put(player.getUniqueID(), items);
        }
        Set<EntityPlayer> receivers = null;
        for (int i = 0; i < runes.getSlots(); i++) {
            ItemStack stack = runes.getStackInSlot(i);
            IAsgaria rune = (IAsgaria)stack.getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null);
            if (runes.isChanged(i) || (rune != null && rune.willAutoSync(stack, (EntityLivingBase)player) && !ItemStack.areItemStacksEqual(stack, items[i]))) {
                if (receivers == null) {
                    receivers = new HashSet<>(((WorldServer)player.world).getEntityTracker().getTrackingPlayers((Entity)player));
                    receivers.add(player);
                }
                syncSlot(player, i, stack, receivers);
                runes.setChanged(i, false);
                items[i] = (stack == null) ? ItemStack.EMPTY : stack.copy();
            }
        }
    }

    private void syncSlots(EntityPlayer player, Collection<? extends EntityPlayer> receivers) {
        IAsgariaItemHandler baubles = AsgariaAPI.getRuneHandler(player);
        for (int i = 0; i < baubles.getSlots(); i++)
            syncSlot(player, i, baubles.getStackInSlot(i), receivers);
    }

    private void syncSlot(EntityPlayer player, int slot, ItemStack stack, Collection<? extends EntityPlayer> receivers) {
        PacketSync pkt = new PacketSync(player, slot, stack);
        for (EntityPlayer receiver : receivers)
            PacketHandler.INSTANCE.sendTo((IMessage)pkt, (EntityPlayerMP)receiver);
    }

    @SubscribeEvent
    public void playerDeath(PlayerDropsEvent event) {
        if (event.getEntity() instanceof EntityPlayer &&
                !(event.getEntity()).world.isRemote &&
                !(event.getEntity()).world.getGameRules().getBoolean("keepInventory"))
            dropItemsAt(event.getEntityPlayer(), event.getDrops(), (Entity)event.getEntityPlayer());
    }

    public void dropItemsAt(EntityPlayer player, List<EntityItem> drops, Entity e) {
        IAsgariaItemHandler baubles = AsgariaAPI.getRuneHandler(player);
        for (int i = 0; i < baubles.getSlots(); i++) {
            if (baubles.getStackInSlot(i) != null && !baubles.getStackInSlot(i).isEmpty()) {
                EntityItem ei = new EntityItem(e.world, e.posX, e.posY + e.getEyeHeight(), e.posZ, baubles.getStackInSlot(i).copy());
                ei.setPickupDelay(40);
                float f1 = e.world.rand.nextFloat() * 0.5F;
                float f2 = e.world.rand.nextFloat() * 3.1415927F * 2.0F;
                ei.motionX = (-MathHelper.sin(f2) * f1);
                ei.motionZ = (MathHelper.cos(f2) * f1);
                ei.motionY = 0.20000000298023224D;
                drops.add(ei);
                baubles.setStackInSlot(i, ItemStack.EMPTY);
            }
        }
    }
}
