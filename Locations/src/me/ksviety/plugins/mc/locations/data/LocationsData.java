package me.ksviety.plugins.mc.locations.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.data.database.IDatabase;
import me.ksviety.plugins.mc.locations.util.FileManagement;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.Files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationsData implements ILoadable, ISavable {

    private List<Location> locations = new ArrayList<>();

    //  Get a location by the name
    public Location getLocation(String name) {

        for (Location location: locations) {

            if (location.getName().equalsIgnoreCase(name))
                return location;

        }

        return null;
    }

    //  Get whole locations list
    public List<Location> getLocations() {
        return locations;
    }

    //  Add a new location
    public boolean addLocation(Location newLocation) {

        //  Checking if the location does not exist yet
        for (Location location: locations) {

            if (location.equals(newLocation))
                return false;

        }

        return locations.add(newLocation);
    }

    //  Remove a location
    public boolean removeLocation(String name) {

        for (Location location: locations) {

            if (location.equals(name))
                return locations.remove(location);

        }

        return false;
    }

    private boolean loadLocal() {
        String data;

        try {

            //  Loading JSON from the save file
            data = FileManagement.readFile(Files.LOCATIONS_SAVE_FILE);

            //  Parsing the data and initializing the data array with them
            locations = new ArrayList<>(Arrays.asList(new Gson().fromJson(data, Location[].class)));

        } catch (IOException e) {

            return false;
        }

        return true;
    }

    private boolean saveLocal() {
        String data;
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        try {

            //  Serializing the data into JSON
            data = gson.toJson(locations.toArray());

            //  Saving the data
            FileManagement.writeFile(Files.LOCATIONS_SAVE_FILE, data);

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    private boolean loadDatabase() {
        boolean success;
        IDatabase database = Plugin.dbManager.getDatabase();

        success = database.connect();

        if (success) {

            locations = database.getLocations();

            database.disconnect();
        }

        return success;
    }

    private boolean saveDatabase() {
        boolean success;
        IDatabase database = Plugin.dbManager.getDatabase();

        success = database.connect();

        if (success) {

            database.setLocations(locations);

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
