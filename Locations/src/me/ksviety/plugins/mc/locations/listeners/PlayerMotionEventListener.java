package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.misc.Vector2;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMotionEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMoved(PlayerMoveEvent e) {
        Location[] locations = Plugin.locationsData.getLocations();

        for (Location location: locations) {
            //  Positions of teh first and second points of the location
            Vector2 pos1 = location.getPos1();
            Vector2 pos2 = location.getPos2();

            //  Player current position
            Vector2 player = Vector2.zero;

            //  Setting up player current position
            player.x = e.getPlayer().getLocation().getBlockX();
            player.y = e.getPlayer().getLocation().getBlockY();

            //  Building the triangle
            float sideA = (float)Math.sqrt(Math.pow((player.x - pos1.x), 2) + Math.pow((player.y - pos1.y), 2));
            float sideB = (float)Math.sqrt(Math.pow((player.x - pos2.x), 2) + Math.pow((player.y - pos2.y), 2));
            float sideC = (float)Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));

        }

    }

}
