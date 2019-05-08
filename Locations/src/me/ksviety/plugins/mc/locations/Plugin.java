package me.ksviety.plugins.mc.locations;

import me.ksviety.plugins.mc.locations.commands.Locations;
import me.ksviety.plugins.mc.locations.data.LocationsData;
import me.ksviety.plugins.mc.locations.data.PlayersData;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    public final static LocationsData locationsData = new LocationsData();
    public final static PlayersData playersData = new PlayersData();

    @Override
    public void onLoad() {

        //  Loading the data
        locationsData.load();
        playersData.load();

    }

    @Override
    public void onEnable() {

        this.getCommand("locations").setExecutor(new Locations());

    }

    @Override
    public void onDisable() {

        //  Saving the data
        locationsData.save();
        playersData.save();

    }

}
