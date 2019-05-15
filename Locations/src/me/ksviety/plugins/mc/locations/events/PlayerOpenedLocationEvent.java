package me.ksviety.plugins.mc.locations.events;

import me.ksviety.plugins.mc.locations.pojo.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerOpenedLocationEvent extends PlayerEvent {
    private final Location location;

    //  **  STATIC  **

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    //  **  GETTERS **

    public Location getLocation() {
        return location;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    //  **  CONSTRUCTORS **

    public PlayerOpenedLocationEvent(Player player, Location location) {
        super(player);
        this.location = location;
    }

}
