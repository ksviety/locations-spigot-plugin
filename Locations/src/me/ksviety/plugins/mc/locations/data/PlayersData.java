package me.ksviety.plugins.mc.locations.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.misc.FileManagement;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import me.ksviety.plugins.mc.locations.pojo.saves.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class PlayersData implements IDataSave {

    Player[] players = new Player[0];

    public Player getPlayer(UUID uuid) {

        for (Player player: players) {

            if (player.getUUID().equals(uuid))
                return player;

        }

        return null;
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean addPlayer(Player newPlayer) {
        Player[] newArray = new Player[players.length + 1];

        //  Checking if the new player hasn't been saved yet
        for (Player player: players) {

            if (player.getUUID().equals(newPlayer.getUUID()))
                return false;

        }

        //  Filling out the new array
        for (int i = 0; i < players.length; i++)
            newArray[i] = players[i];

        //  Adding the new player into the end of newArray
        newArray[players.length] = newPlayer;

        players = newArray;

        return true;
    }

    public boolean addLocation(Player player, Location location) {

        //  Checking if the location exists
        if (Plugin.locationsData.getLocation(location.getName()) == null)
            return false;

        for (int i = 0; i < players.length; i++) {

            //  The player has been found
            if (players[i].getUUID().equals(player.getUUID())) {
                //  Association
                Player p = players[i];

                String[] locations = new String[p.getLocations().length+1];

                for (int a = 0; a < locations.length-1; a++)
                    locations[a] = p.getLocations()[a];

                //  Adding the new location
                locations[locations.length-1] = location.getName();

                break;
            }

        }

        return true;
    }

    @Override
    public boolean load() {
        String data;

        try {

            //  Loading JSON from the save file
            data = FileManagement.readFile(FileManagement.PATHS.PLAYERS_DATA);

            //  Parsing the data and initializing the data array with them
            players = new Gson().fromJson(data, Player[].class);

        } catch (IOException e) {

            //  Saving empty save file if the file does not exist yet
            if (e instanceof FileNotFoundException)
                save();
            else
                return false;

        }

        return true;
    }

    @Override
    public boolean save() {
        String data;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            //  Serializing data
            data = gson.toJson(players);

            //  Saving data
            FileManagement.writeFile(FileManagement.PATHS.PLAYERS_DATA, data);

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

}
