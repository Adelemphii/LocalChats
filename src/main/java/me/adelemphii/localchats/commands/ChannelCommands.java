package me.adelemphii.localchats.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.adelemphii.localchats.LocalChats;
import me.adelemphii.localchats.util.ChatRanges;
import me.adelemphii.localchats.util.ChatUtils;
import me.adelemphii.localchats.util.SimpleUser;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("ch|chat|channel")
public class ChannelCommands extends BaseCommand {

    private final LocalChats plugin;

    public ChannelCommands(LocalChats plugin) {
        this.plugin = plugin;
    }

    @HelpCommand
    @CommandPermission("localchats.ch")
    @Description("Shows the help message")
    public void onHelp(CommandSender sender) {

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&8&m---------------------------------------"
                        + "\n&7LocalChats &8&l>> &7Channel Commands"
                        + "\n&8&m---------------------------------------"
                        + "\n&7/ch list &8- &7Lists all channels"
                        + "\n&7/ch focus <channel> &8- &7Focuses a channel"
                        + "\n&8&m---------------------------------------"
        ));
    }

    @Subcommand("list")
    @CommandPermission("localchats.ch")
    @Description("Lists all channels")
    public void onList(CommandSender sender) {

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&8&m---------------------------------------"
                        + "\n&7LocalChats &8&l>> &7Channel List"
                        + "\n"
                        + "\n&7&lChannel &8- &7Description"
                        + "\n&8&m---------------------------------------"
        ));

        for(ChatRanges channel : ChatRanges.values()) {
            if(channel == ChatRanges.GLOBAL) {
                sender.sendMessage(
                        // [GLOBAL] - Range: 48
                        ChatColor.DARK_GRAY + "[" + channel.getPrefixColor() + channel.getAlias().toUpperCase() + ChatColor.DARK_GRAY + "] "
                                + ChatColor.GRAY + "- Range: Global"
                );
                continue;
            }

            sender.sendMessage(
                    // [SHOUT] - Range: 48
                    ChatColor.DARK_GRAY + "[" + channel.getPrefixColor() + channel.getAlias().toUpperCase() + ChatColor.DARK_GRAY + "] "
                    + ChatColor.GRAY + "- Range: " + channel.getRange()
            );
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&8&m---------------------------------------"
                ));
    }

    @Subcommand("focus")
    @CommandPermission("localchats.ch")
    @CommandCompletion("global|shout|local|quiet|whisper")
    @Description("Focuses a specific channel")
    public void onFocusChannel(Player player, @Default("global") String channel) {

        SimpleUser user = plugin.getUser(player.getUniqueId());
        ChatRanges newChannel;
        try {
            newChannel = ChatRanges.valueOf(channel.toUpperCase());
        } catch(IllegalArgumentException exception) {
            newChannel = ChatRanges.GLOBAL;
        }

        user.setRange(newChannel);

        plugin.updateUser(player.getUniqueId(), user);

        ChatUtils.success(player,
                "You are now chatting in " + newChannel.getPrefixColor() + StringUtils.capitalize(newChannel.getAlias()));

    }

}
