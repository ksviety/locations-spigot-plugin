package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.events.PlayerEnteredLocationEvent;
import me.ksviety.plugins.mc.locations.events.PlayerOpenedLocationEvent;
import me.ksviety.plugins.mc.locations.pojo.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.List;

public class PlayerEnteredLocationEventListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerEnteredLocation(PlayerEnteredLocationEvent e) {
        List<String> playerLocations = Plugin.playersData.getPlayer(e.getPlayer().getUniqueId()).getLocations();
        Player player = Plugin.playersData.getPlayer(e.getPlayer().getUniqueId());
        PlayerOpenedLocationEvent playerOpenedLocationEvent;

        if (!e.getLocation().isActive()) {

            e.setCancelled(true);

            return;
        }

        //  The location is already opened
        if (playerLocations.contains(e.getLocation().getName())) {

            Plugin.nms.getNMS().sendGameInfo(e.getPlayer(), e.getLocation().getLabel());

        //  The location is not opened yet
        } else {
            player.addLocation(e.getLocation().getName());

            playerOpenedLocationEvent = new PlayerOpenedLocationEvent(e.getPlayer(), e.getLocation());

            Bukkit.getPluginManager().callEvent(playerOpenedLocationEvent);
        }

    }

}
