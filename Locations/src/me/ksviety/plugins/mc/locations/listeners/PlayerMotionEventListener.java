package me.ksviety.plugins.mc.locations.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMotionEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMoved(PlayerMoveEvent e) {
        //  TODO
    }

}
