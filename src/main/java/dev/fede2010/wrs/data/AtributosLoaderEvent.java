package dev.fede2010.wrs.data;

import dev.fede2010.wrs.Wrs;
import dev.fede2010.wrs.network.DataCategory;
import dev.fede2010.wrs.network.DataSyncPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

public class AtributosLoaderEvent {
    public static final AtributosDataLoader GROUPS = new AtributosDataLoader("groups");
    public static final AtributosDataLoader ENTITIES = new AtributosDataLoader("entities");
    public static final AtributosDataLoader ITEMS = new AtributosDataLoader("items");

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(GROUPS);
        event.addListener(ENTITIES);
        event.addListener(ITEMS);
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Wrs.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                    new DataSyncPacket(DataCategory.GROUPS, GROUPS.getData()));

            Wrs.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                    new DataSyncPacket(DataCategory.ENTITIES, ENTITIES.getData()));

            Wrs.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                    new DataSyncPacket(DataCategory.ITEMS, ITEMS.getData()));
        }
    }

}