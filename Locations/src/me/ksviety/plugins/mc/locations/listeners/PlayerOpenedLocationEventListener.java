package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.data.Locale;
import me.ksviety.plugins.mc.locations.events.PlayerOpenedLocationEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerOpenedLocationEventListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLocationOpened(PlayerOpenedLocationEvent e) {

        e.getPlayer().sendTitle(e.getLocation().getLabel(), Plugin.locale.getText(e.getPlayer(), Locale.Keys.LOCATION_OPENED), 20, 100, 20);
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 1f, 1.7f);

    }

}
