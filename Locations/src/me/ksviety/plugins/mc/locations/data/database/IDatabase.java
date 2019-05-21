package me.ksviety.plugins.mc.locations.data.database;

import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.pojo.Player;

import java.util.List;

public interface IDatabase {

    enum Database {
        MYSQL,
        SQLITE
    }

    List<Player> getPlayers();

    List<Location> getLocations();

    void setPlayers(List<Player> players);

    void setLocations(List<Location> locations);

    boolean connect();

    void disconnect();

}
