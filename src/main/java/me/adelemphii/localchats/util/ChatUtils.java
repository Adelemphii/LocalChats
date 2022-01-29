package me.adelemphii.localchats.util;

import me.adelemphii.localchats.LocalChats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ChatUtils {

    /**
     * formats error message to be sent to a user
     *
     * @param player player to send error message to
     * @param error error message to send to user
     *
     */
    public static void errorMessage(Player player, String error) {
        player.sendMessage(ChatColor.DARK_RED + "[!] " + ChatColor.RED + ChatColor.ITALIC + ChatColor.translateAlternateColorCodes('&', error));
        player.playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1F, 1F);
    }

    public static void success(Player player, String message) {
        player.sendMessage(ChatColor.DARK_GREEN + "[!] " + ChatColor.GREEN + ChatColor.ITALIC + ChatColor.translateAlternateColorCodes('&',
                message));
    }

    /**
     * formats correct syntax for user to use
     *
     * @param player user to send syntax error to
     * @param correctSyntax the right syntax to use
     *
     */
    public static void syntaxError(Player player, String correctSyntax) {
        player.sendMessage(ChatColor.DARK_RED + "[!] " + ChatColor.RED + "Syntax Error: " + ChatColor.ITALIC + ChatColor.translateAlternateColorCodes('&', correctSyntax));
        player.playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1F, 1F);
    }

    /**
     * Send a message in whatever channel is selected
     * @param player Player who is sending the message
     * @param message The message to send
     * @param range The channel to send to
     */
    public static void sendMessageInChannel(Player player, String message, ChatRanges range) {
        int distanceSQRT = range.getRange() / 2;
        if(range == ChatRanges.GLOBAL) {
            sendGlobalMessage(player, message, range);
            return;
        }

        String format = String.format("%s[%s%s%s] %s%s%s: %s%s", ChatColor.DARK_GRAY, range.getPrefixColor(), range.getAlias().toUpperCase(), ChatColor.DARK_GRAY,
                ChatColor.RESET, player.getDisplayName(), ChatColor.WHITE, ChatColor.GRAY, message
        );

        player.sendMessage(format);
        LocalChats.instance.getLogger().info(format);
        for(Entity entity : player.getNearbyEntities(distanceSQRT, distanceSQRT, distanceSQRT)) {
            if(entity instanceof Player) {
                Player nearbyPlayer = (Player) entity;
                nearbyPlayer.sendMessage(format);
            }
        }
    }

    /**
     * Send a message in global chat
     * @param player Player who is sending the message
     * @param message The message to send
     * @param range The channel to send to
     */
    public static void sendGlobalMessage(Player player, String message, ChatRanges range) {
        String format = String.format("%s[%s%s%s] %s%s%s: %s%s", ChatColor.DARK_GRAY, range.getPrefixColor(), range.getAlias().toUpperCase(), ChatColor.DARK_GRAY,
                ChatColor.RESET, player.getDisplayName(), ChatColor.WHITE, ChatColor.GRAY, message
        );

        LocalChats.instance.getLogger().info(format);
        for(Player globalPlayer : Bukkit.getOnlinePlayers()) {
            globalPlayer.sendMessage(format);
        }
    }
}
