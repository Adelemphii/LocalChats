package me.adelemphii.localchats;

import co.aikar.commands.BukkitCommandManager;
import me.adelemphii.localchats.commands.ChannelCommands;
import me.adelemphii.localchats.events.PlayerChatListener;
import me.adelemphii.localchats.events.PlayerJoinListener;
import me.adelemphii.localchats.util.SimpleUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class LocalChats extends JavaPlugin {

    private BukkitCommandManager commandManager;

    private HashMap<UUID, SimpleUser> users = new HashMap<>();

    public static LocalChats instance;

    @Override
    public void onEnable() {

        instance = this;

        commandManager = new BukkitCommandManager(this);

        registerEvents();
        registerCommands();

        for(Player player : Bukkit.getOnlinePlayers()) {
            users.put(player.getUniqueId(), new SimpleUser());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
    }

    private void registerCommands() {
        commandManager.registerCommand(new ChannelCommands(this));
    }

    public HashMap<UUID, SimpleUser> getUsers() {
        return users;
    }

    public void addUserIfNotExists(Player player) {
        users.putIfAbsent(player.getUniqueId(), new SimpleUser());
    }

    public void updateUser(UUID uuid, SimpleUser simpleUser) {
        users.put(uuid, simpleUser);
    }

    public SimpleUser getUser(UUID uuid) {
        return users.getOrDefault(uuid, new SimpleUser());
    }

    public void setUsers(HashMap<UUID, SimpleUser> users) {
        this.users = users;
    }
}
