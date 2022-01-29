package me.adelemphii.localchats.util;

import org.bukkit.ChatColor;

public enum ChatRanges {

    WHISPER(2, ChatColor.BLUE, "2", "whisper"),
    QUIET(8, ChatColor.AQUA, "8", "quiet"),
    LOCAL(20, ChatColor.GREEN, "20", "local"),
    SHOUT(48, ChatColor.RED, "48", "shout"),
    GLOBAL(Integer.MAX_VALUE, ChatColor.GRAY, "GLOBAL", "global");

    private final int range;
    private final ChatColor prefixColor;
    private final String prefix;
    private final String alias;

    ChatRanges(int range, ChatColor prefixColor, String prefix, String alias) {
        this.range = range;
        this.prefixColor = prefixColor;
        this.prefix = prefix;
        this.alias = alias;
    }

    public int getRange() {
        return range;
    }

    public ChatColor getPrefixColor() {
        return prefixColor;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return "ChatRanges{" +
                "range=" + range +
                ", prefixColor=" + prefixColor +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
