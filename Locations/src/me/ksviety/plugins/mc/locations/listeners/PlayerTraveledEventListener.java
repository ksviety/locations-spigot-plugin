package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.events.PlayerTraveledEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerTraveledEventListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerTraveled(PlayerTraveledEvent e) {

        e.getPlayer().sendMessage(e.getPlayer().getLocation().toString());

    }

}
