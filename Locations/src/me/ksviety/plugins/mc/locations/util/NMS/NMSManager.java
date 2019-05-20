package me.ksviety.plugins.mc.locations.util.NMS;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class NMSManager {

    private NMS nms;
    private Plugin plugin;

    public NMS getNMS() {
        return nms;
    }

    private String getVersion() {
        String p = getServer().getClass().getPackage().getName();
        return p.substring(p.lastIndexOf('.') + 1);
    }

    public void enable(Plugin plugin) {

        this.plugin = plugin;

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
            case NMS.v1_12_R1:
                nms = new v1_12_R1();
                break;
            default:
                Bukkit.getPluginManager().disablePlugin(plugin);
                break;
        }

    }

}
