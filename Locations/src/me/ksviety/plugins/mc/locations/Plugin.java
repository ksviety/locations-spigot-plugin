package me.ksviety.plugins.mc.locations;

import me.ksviety.plugins.mc.locations.commands.FastTravel;
import me.ksviety.plugins.mc.locations.commands.Locations;
import me.ksviety.plugins.mc.locations.data.LocationsData;
import me.ksviety.plugins.mc.locations.data.PlayersData;
import me.ksviety.plugins.mc.locations.listeners.PlayerJoinEventListener;
import me.ksviety.plugins.mc.locations.listeners.PlayerMotionEventListener;
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

        //  Commands
        this.getCommand("adminlocations").setExecutor(new Locations());
        this.getCommand("fasttravel").setExecutor(new FastTravel());

        //  Event listeners
        getServer().getPluginManager().registerEvents(new PlayerMotionEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);

    }

    @Override
    public void onDisable() {

        //  Saving the data
        locationsData.save();
        playersData.save();

    }

}
