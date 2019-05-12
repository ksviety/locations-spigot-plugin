package me.ksviety.plugins.mc.locations.events;

import me.ksviety.plugins.mc.locations.pojo.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerTraveledEvent extends PlayerMoveEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Location location;

    public Location getLocation() {
        return location;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public PlayerTraveledEvent(Player player, Location location) {
        super(player, location.getWarpLocation(), player.getLocation());
        this.location = location;
    }

}
