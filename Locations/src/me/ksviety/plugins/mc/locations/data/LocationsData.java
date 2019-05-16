package me.ksviety.plugins.mc.locations.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ksviety.plugins.mc.locations.util.FileManagement;
import me.ksviety.plugins.mc.locations.pojo.Location;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationsData implements ILoadable, ISavable {

    private ArrayList<Location> locations = new ArrayList<>();

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

    @Override
    public boolean load() {
        String data;

        try {

            //  Loading JSON from the save file
            data = FileManagement.readFile(FileManagement.PATHS.LOCATIONS_DATA);

            //  Parsing the data and initializing the data array with them
            locations = new ArrayList<>(Arrays.asList(new Gson().fromJson(data, Location[].class)));

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
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        try {

            //  Serializing the data into JSON
            data = gson.toJson(locations.toArray());

            //  Saving the data
            FileManagement.writeFile(FileManagement.PATHS.LOCATIONS_DATA, data);

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }
}
