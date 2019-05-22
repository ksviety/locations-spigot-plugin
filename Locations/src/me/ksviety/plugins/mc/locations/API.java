package me.ksviety.plugins.mc.locations;

import com.sun.istack.internal.NotNull;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.pojo.Player;

import javax.annotation.Nullable;
import java.util.List;

public class API {

    @Nullable
    public static Player getLocationsPlayer(org.bukkit.entity.Player player) {
        return Plugin.playersData.getPlayer(player.getUniqueId());
    }

    @NotNull
    public static List<Location> getAllLocations() {
        return Plugin.locationsData.getLocations();
    }

}
