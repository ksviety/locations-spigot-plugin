package me.ksviety.plugins.mc.locations.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.data.database.IDatabase;
import me.ksviety.plugins.mc.locations.util.FileManagement;
import me.ksviety.plugins.mc.locations.pojo.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayersData implements ILoadable, ISavable {

    private List<Player> players = new ArrayList<>();

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

    private boolean loadLocal() {
        String data;

        try {

            //  Loading JSON from the save file
            data = FileManagement.readFile(FileManagement.PLAYERS_SAVE_FILE);

            //  Parsing the data and initializing the data array with them
            players = new ArrayList<>(Arrays.asList(new Gson().fromJson(data, Player[].class)));

            return true;
        } catch (IOException e) {

            return false;
        }

    }

    private boolean saveLocal() {
        String data;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            //  Serializing data
            data = gson.toJson(players);

            //  Saving data
            FileManagement.writeFile(FileManagement.PLAYERS_SAVE_FILE, data);

            return true;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

    }

    private boolean loadDatabase() {
        boolean success;
        IDatabase database = Plugin.dbManager.getDatabase();

        success = database.connect();

        if (success) {

            players = database.getPlayers();

            database.disconnect();
        }

        return success;
    }

    private boolean saveDatabase() {
        boolean success;
        IDatabase database = Plugin.dbManager.getDatabase();

        success = database.connect();

        if (success) {

            database.setPlayers(players);

            database.disconnect();
        }

        return success;
    }

    @Override
    public boolean load() {
        boolean success;

        if (Plugin.databaseConfig.getConfig().use())
            success = loadDatabase();
        else
            success = loadLocal();


        return success;
    }

    @Override
    public boolean save() {
        boolean success;

        if (Plugin.databaseConfig.getConfig().use())
            success = saveDatabase();
        else
            success = saveLocal();


        return success;
    }

}
