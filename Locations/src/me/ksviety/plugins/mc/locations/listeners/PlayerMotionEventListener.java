package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.misc.Vector2xz;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class PlayerMotionEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMoved(PlayerMoveEvent e) {
        List<Location> locations = Plugin.locationsData.getLocations();

        for (Location location: locations) {
            //  Positions of teh first and second points of the location
            Vector2xz pos1 = location.getFirstPosition();
            Vector2xz pos2 = location.getSecondPosition();

            //  Player current position
            Vector2xz player = Vector2xz.zero;

            //  Setting up player current position
            player.x = e.getPlayer().getLocation().getBlockX();
            player.z = e.getPlayer().getLocation().getBlockY();

            //  Building the triangle
            float sideA = (float)Math.sqrt(Math.pow((player.x - pos1.x), 2) + Math.pow((player.z - pos1.z), 2));
            float sideB = (float)Math.sqrt(Math.pow((player.x - pos2.x), 2) + Math.pow((player.z - pos2.z), 2));
            float sideC = (float)Math.sqrt(Math.pow((pos1.x - pos2.x), 2) + Math.pow((pos1.z - pos2.z), 2));

            //e.getPlayer().sendMessage(String.valueOf(sideC));
        }

    }

}
