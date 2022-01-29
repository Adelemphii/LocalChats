package me.adelemphii.localchats.events;

import me.adelemphii.localchats.LocalChats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final LocalChats plugin;

    public PlayerJoinListener(LocalChats plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.addUserIfNotExists(event.getPlayer());
    }
}
