package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMotionEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMoved(PlayerMoveEvent e) {
        Vector3 playerPosition = Vector3.zero;

        //  Do not do anything if the event has been cancelled
        if (e.isCancelled())
            return;

        //  Setting current player position in Vector3
        playerPosition.setX( (float)e.getPlayer().getLocation().getX() );
        playerPosition.setZ( (float)e.getPlayer().getLocation().getZ() );
        playerPosition.setY( (float)e.getPlayer().getLocation().getY() );

        //  Checking player position relatively to each location
        for (Location location: Plugin.locationsData.getLocations()) {

            if (location.hasIn(playerPosition))
                e.getPlayer().sendMessage("in");
            else
                e.getPlayer().sendMessage("out");

        }

    }

}
