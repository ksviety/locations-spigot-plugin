package me.ksviety.plugins.mc.locations.data.database;

import com.google.gson.Gson;
import me.ksviety.plugins.mc.locations.Plugin;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.pojo.Player;
import me.ksviety.plugins.mc.locations.util.LocationType;
import me.ksviety.plugins.mc.locations.util.Vector3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MySQL implements IDatabase {

    private Connection connection = null;

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("select * from ksvlocations_players");

            while (resultSet.next()) {
                Player player;
                UUID uuid;
                String[] locations;

                uuid = UUID.fromString(resultSet.getString("uuid"));
                locations = new Gson().fromJson(resultSet.getString("locations"), String[].class);

                player = new Player(uuid);

                player.setLocations(Arrays.asList(locations));

                players.add(player);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

    @Override
    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        Gson gson = new Gson();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("select * from ksv_locations");

            while (resultSet.next()) {
                Location location = new Location();

                location.setName(resultSet.getString("name"));
                location.setLabel(resultSet.getString("label"));
                location.setActive(resultSet.getBoolean("active"));
                location.setPriority(resultSet.getInt("priority"));
                location.setWorld(resultSet.getString("world"));
                location.setType(LocationType.valueOf(resultSet.getString("type")));
                location.setFirstPosition(gson.fromJson(resultSet.getString("first_position"), Vector3.class));
                location.setSecondPosition(gson.fromJson(resultSet.getString("second_position"), Vector3.class));
                location.setWarpPosition(gson.fromJson(resultSet.getString("warp_position"), Vector3.class));

                locations.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locations;
    }

    @Override
    public void setPlayers(List<Player> players) {

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("drop table if exists ksv_locations_players");
            statement.executeUpdate("create table ksv_locations_players(uuid VARCHAR(36) not null, locations TEXT not null)");

            for (Player player: players) {
                PreparedStatement ps = connection.prepareStatement("insert into ksv_locations_players(uuid, locations) values(?, ?)");

                ps.setString(1, player.getUUID().toString());
                ps.setString(2, new Gson().toJson(player.getLocations().toArray()));

                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setLocations(List<Location> locations) {
        Gson gson = new Gson();

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("drop table if exists ksv_locations");
            statement.executeUpdate("create table ksv_locations(name VARCHAR(32), label VARCHAR(32), world VARCHAR(32), type VARCHAR(32), active BOOL, priority INT, first_position VARCHAR(255), second_position VARCHAR(255), warp_position VARCHAR(255))");

            for (Location location: locations) {
                PreparedStatement ps = connection.prepareStatement("insert into ksv_locations(" +
                        "name, label, world, type, active, priority, first_position, second_position, warp_position)" +
                        " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, location.getName());
                ps.setString(2, location.getLabel());
                ps.setString(3, location.getWorld());
                ps.setString(4, location.getType().name());
                ps.setBoolean(5, location.isActive());
                ps.setInt(6, location.getPriority());
                ps.setString(7, gson.toJson(location.getFirstPosition()));
                ps.setString(8, gson.toJson(location.getSecondPosition()));
                ps.setString(9, gson.toJson(location.getWarpPosition()));

                ps.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean connect() {
        me.ksviety.plugins.mc.locations.pojo.Database config = Plugin.databaseConfig.clone();

        String username = config.getUsername();
        String password = config.getPassword();
        Driver driver;

        try {

            driver = new FabricMySQLDriver();

            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(config.getUrl()+"?useSSL=false", username, password);

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public void disconnect() {

        try {

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
