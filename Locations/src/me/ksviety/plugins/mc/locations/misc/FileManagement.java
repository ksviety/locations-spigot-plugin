package me.ksviety.plugins.mc.locations.misc;

import me.ksviety.plugins.mc.locations.Plugin;

import java.io.*;

public class FileManagement {

    public final static class PATHS {

        public final static String PLUGIN_DIR = Plugin.getPlugin(Plugin.class).getDataFolder().toString();

        public final static String LOCATIONS_DATA = "locations_save.json";
        public final static String PLAYERS_DATA = "players_save.json";
        public final static String SETTING_DATA = "settings_save.json";

    }

    public static String readFile(String path) throws IOException {
        String finalOut;
        File file = new File(PATHS.PLUGIN_DIR, path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] content = new byte[inputStream.available()];

        //  Reading the file and putting the data into content
        inputStream.read(content);

        //  Initializing finalOut with the file data
        finalOut = new String(content);

        inputStream.close();

        return finalOut;
    }

    public static void writeFile(String path, String content) throws IOException {
        File pluginDir = new File(PATHS.PLUGIN_DIR);
        File file = new File(PATHS.PLUGIN_DIR, path);

        //  Checking if the plugin folder exists and making it if it doesn't
        if (!pluginDir.exists())
            pluginDir.mkdirs();

        FileOutputStream outputStream = new FileOutputStream(file);

        //  Writing into the file
        outputStream.write(content.getBytes());

        outputStream.close();
    }

}
