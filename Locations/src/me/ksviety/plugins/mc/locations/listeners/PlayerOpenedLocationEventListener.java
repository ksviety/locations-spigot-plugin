package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.data.Locale;
import me.ksviety.plugins.mc.locations.events.PlayerOpenedLocationEvent;
import me.ksviety.plugins.mc.locations.util.NMS.objects.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerOpenedLocationEventListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLocationOpened(PlayerOpenedLocationEvent e) {
        Text location = new Text().setText(e.getLocation().getLabel());
        Text message = new Text().setText(Plugin.locale.getText(e.getPlayer(), Locale.Keys.LOCATION_OPENED));

        Plugin.nms.getNMS().sendTitle(e.getPlayer(), location, message, 20, 80, 20);

    }

}
