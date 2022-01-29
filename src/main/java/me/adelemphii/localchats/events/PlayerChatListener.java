package me.adelemphii.localchats.events;

import me.adelemphii.localchats.LocalChats;
import me.adelemphii.localchats.util.ChatRanges;
import me.adelemphii.localchats.util.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatListener implements Listener {

    private final LocalChats plugin;

    public PlayerChatListener(LocalChats plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        ChatRanges channel = plugin.getUser(player.getUniqueId()).getRange();

        ChatUtils.sendMessageInChannel(player, message, channel);
        event.setCancelled(true);
    }
}
