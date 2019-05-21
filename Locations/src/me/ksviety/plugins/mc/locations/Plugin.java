package me.ksviety.plugins.mc.locations;

import me.ksviety.plugins.mc.locations.commands.AdminLocations;
import me.ksviety.plugins.mc.locations.commands.Travel;
import me.ksviety.plugins.mc.locations.data.DatabaseConfig;
import me.ksviety.plugins.mc.locations.data.Locale;
import me.ksviety.plugins.mc.locations.data.LocationsData;
import me.ksviety.plugins.mc.locations.data.PlayersData;
import me.ksviety.plugins.mc.locations.data.database.DatabaseManager;
import me.ksviety.plugins.mc.locations.listeners.*;
import me.ksviety.plugins.mc.locations.util.NMS.NMSManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    public final static LocationsData locationsData = new LocationsData();
    public final static PlayersData playersData = new PlayersData();
    public final static Locale locale = new Locale();
    public final static NMSManager nms = new NMSManager();
    public final static DatabaseConfig databaseConfig = new DatabaseConfig();
    public final static DatabaseManager dbManager = new DatabaseManager();

    @Override
    public void onLoad() {

        //  Loading the data
        locationsData.load();
        playersData.load();
        locale.load();
        databaseConfig.load();

    }

    @Override
    public void onEnable() {

        //  NMS
        nms.enable(this);

        //  Commands
        this.getCommand("adminlocations").setExecutor(new AdminLocations());
        this.getCommand("travel").setExecutor(new Travel());

        //  Event listeners
        getServer().getPluginManager().registerEvents(new PlayerMotionEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTraveledEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerEnteredLocationEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerOpenedLocationEventListener(), this);

    }

    @Override
    public void onDisable() {

        //  Saving the data
        locationsData.save();
        playersData.save();
        databaseConfig.save();

    }

}
