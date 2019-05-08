package me.ksviety.plugins.mc.locations.data;

import com.google.gson.Gson;
import me.ksviety.plugins.mc.locations.misc.FileManagement;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationsData implements IDataSave {

    private Location[] locations = new Location[0];

    //  Get a location by the name
    public Location getLocation(String name) {

        for (Location location: locations) {

            if (location.getName().equals(name))
                return location;

        }

        return null;
    }

    //  Add a new location
    public boolean addLocation(Location newLocation) {
        Location[] newArray = new Location[locations.length + 1];

        for (int i = 0; i < locations.length; i++) {

            //  Checking if the location already exists
            if (locations[i].getName().equals(newLocation.getName()))
                return false;

            //  Copying data from the old array to the new one
            newArray[i] = locations[i];
        }

        //  Adding newLocation to the end of the new array
        newArray[locations.length] = newLocation;

        //  Reinitializing the data array
        locations = newArray;

        return true;
    }

    //  Remove a location
    public boolean removeLocation(String name) {
        List<Location> newArray = new ArrayList();
        boolean success = false;

        for (int i = 0; i < locations.length; i++) {

            if (locations[i].getName().equals(name))
                success = true;
            else
                newArray.add(locations[i]);

        }

        locations = newArray.toArray(new Location[locations.length-1]);

        return success;
    }

    @Override
    public boolean load() {
        String data;

        try {

            //  Loading JSON from the save file
            data = FileManagement.readFile(FileManagement.PATHS.LOCATIONS_DATA);

            //  Parsing the data and initializing the data array with them
            locations = new Gson().fromJson(data, Location[].class);

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

        try {

            //  Serializing the data into JSON
            data = new Gson().toJson(locations);

            //  Saving the data
            FileManagement.writeFile(FileManagement.PATHS.LOCATIONS_DATA, data);

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }
}
