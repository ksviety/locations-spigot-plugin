package me.ksviety.plugins.mc.locations.events;

import me.ksviety.plugins.mc.locations.pojo.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerLeftLocationEvent extends PlayerEvent implements Cancellable {
    private final Location location;
    private boolean cancelled;

    //  **  STATIC  **

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    //  **  GETTERS **

    public Location getLocation() {
        return location;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    //  **  CONSTRUCTORS **

    public PlayerLeftLocationEvent(Player player, Location location) {
        super(player);
        this.location = location;
    }

}