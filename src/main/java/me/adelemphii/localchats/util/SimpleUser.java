package me.adelemphii.localchats.util;

public class SimpleUser {

    ChatRanges range = ChatRanges.GLOBAL;

    public SimpleUser(ChatRanges range) {
        this.range = range;
    }

    public SimpleUser() {
    }

    public ChatRanges getRange() {
        return range;
    }

    public void setRange(ChatRanges range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "SimpleUser{" +
                "range=" + range +
                '}';
    }
}
