package me.ksviety.plugins.mc.locations;

import me.ksviety.plugins.mc.locations.commands.AdminLocations;
import me.ksviety.plugins.mc.locations.commands.FastTravel;
import me.ksviety.plugins.mc.locations.data.LocationsData;
import me.ksviety.plugins.mc.locations.data.PlayersData;
import me.ksviety.plugins.mc.locations.listeners.*;
import me.ksviety.plugins.mc.locations.util.NMS.NMS;
import me.ksviety.plugins.mc.locations.util.NMS.v1_13_R1;
import me.ksviety.plugins.mc.locations.util.NMS.v1_13_R2;
import me.ksviety.plugins.mc.locations.util.NMS.v1_14_R1;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    public final static LocationsData locationsData = new LocationsData();
    public final static PlayersData playersData = new PlayersData();

    public static NMS nms;

    private String getVersion() {
        String p = getServer().getClass().getPackage().getName();
        return p.substring(p.lastIndexOf('.') + 1);
    }

    @Override
    public void onLoad() {

        //  Loading the data
        locationsData.load();
        playersData.load();

    }

    @Override
    public void onEnable() {

        //  Setup NMS
        switch (getVersion()) {
            case NMS.v1_13_R1:
                nms = new v1_13_R1();
                break;
            case NMS.v1_13_R2:
                nms = new v1_13_R2();
                break;
            case NMS.v1_14_R1:
                nms = new v1_14_R1();
                break;
            default:
                Bukkit.getPluginManager().disablePlugin(this);
                break;
        }

        //  Commands
        this.getCommand("adminlocations").setExecutor(new AdminLocations());
        this.getCommand("fasttravel").setExecutor(new FastTravel());

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

    }

}
