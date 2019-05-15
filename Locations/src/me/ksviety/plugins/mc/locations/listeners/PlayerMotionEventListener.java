package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.events.PlayerEnteredLocationEvent;
import me.ksviety.plugins.mc.locations.events.PlayerLeftLocationEvent;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMotionEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMoved(PlayerMoveEvent e) {
        Vector3 playerPosition = Vector3.zero;
        PlayerEnteredLocationEvent playerEnteredLocationEvent;
        PlayerLeftLocationEvent playerLeftLocationEvent;

        //  Do not do anything if the event has been cancelled
        if (e.isCancelled())
            return;

        //  Setting current player position in Vector3
        playerPosition.setX( (float)e.getPlayer().getLocation().getX() );
        playerPosition.setZ( (float)e.getPlayer().getLocation().getZ() );
        playerPosition.setY( (float)e.getPlayer().getLocation().getY() );

        //  Checking player position relatively to each location
        for (Location location: Plugin.locationsData.getLocations()) {

            if (!location.getWorld().equalsIgnoreCase(e.getPlayer().getLocation().getWorld().getName()))
                continue;

            //  The player is in the location
            //  Entered location event call
            if (location.hasIn(playerPosition)) {

                if (!location.getPlayers().contains(e.getPlayer().getUniqueId())) {
                    playerEnteredLocationEvent = new PlayerEnteredLocationEvent(e.getPlayer(), location);


                    Bukkit.getPluginManager().callEvent(playerEnteredLocationEvent);

                    if (!playerEnteredLocationEvent.isCancelled())
                        location.addPlayer(e.getPlayer().getUniqueId());

                }

            //  The player is out of the location
            //  Left location event call
            } else {

                if (location.getPlayers().contains(e.getPlayer().getUniqueId())) {
                    playerLeftLocationEvent = new PlayerLeftLocationEvent(e.getPlayer(), location);

                    Bukkit.getPluginManager().callEvent(playerLeftLocationEvent);

                    if (!playerLeftLocationEvent.isCancelled())
                        location.removePlayer(e.getPlayer().getUniqueId());

                }

            }

        }

    }

}
