package me.ksviety.plugins.mc.locations.pojo;

import me.ksviety.plugins.mc.locations.Plugin;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {

    private final UUID uuid;
    private List<String> locations = new ArrayList<>();

    public UUID getUUID() {
        return uuid;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public boolean addLocation(String name) {

        //  Checking if the player hasn't unlocked the location yet
        for (String location: locations) {

            if (location.equalsIgnoreCase(name))
                return false;

        }

        //  Checking if the location exists at all
        if (Plugin.locationsData.getLocation(name) == null)
            return false;

        return locations.add(name);
    }

    @Override
    public String toString() {
        return Bukkit.getPlayer(this.uuid).getName();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Player) && (((Player) obj).uuid.equals(this.uuid));
    }

    public Player(UUID uuid) {

        this.uuid = uuid;

    }

}
