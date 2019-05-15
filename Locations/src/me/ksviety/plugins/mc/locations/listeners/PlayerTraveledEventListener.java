package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.events.PlayerEnteredLocationEvent;
import me.ksviety.plugins.mc.locations.events.PlayerTraveledEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerTraveledEventListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerTraveled(PlayerTraveledEvent e) {
        PlayerEnteredLocationEvent playerEnteredLocationEvent;

        //  Do not do anything if the event is cancelled
        if (e.isCancelled())
            return;

        playerEnteredLocationEvent = new PlayerEnteredLocationEvent(e.getPlayer(), e.getLocation());

        Bukkit.getPluginManager().callEvent(playerEnteredLocationEvent);

    }

}
