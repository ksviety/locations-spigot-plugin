package me.ksviety.plugins.mc.locations.pojo.saves;

import java.util.UUID;

public class Player {

    private final UUID uuid;
    private String[] locations;

    public UUID getUUID() {
        return uuid;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public Player(UUID uuid) {

        this.uuid = uuid;

        locations = new String[0];

    }

}
