package me.ksviety.plugins.mc.locations.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ksviety.plugins.mc.locations.util.FileManagement;
import me.ksviety.plugins.mc.locations.pojo.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayersData implements IDataSave {

    private ArrayList<Player> players = new ArrayList<>();

    public Player getPlayer(UUID uuid) {

        for (Player player: players) {

            if (player.getUUID().equals(uuid))
                return player;

        }

        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean addPlayer(Player newPlayer) {

        //  Checking if the player has already been added
        for (Player player: players) {

            if (player.equals(newPlayer))
                return false;

        }

        return players.add(newPlayer);
    }

    @Override
    public boolean load() {
        String data;

        try {

            //  Loading JSON from the save file
            data = FileManagement.readFile(FileManagement.PATHS.PLAYERS_DATA);

            //  Parsing the data and initializing the data array with them
            players = new ArrayList<>(Arrays.asList(new Gson().fromJson(data, Player[].class)));

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
