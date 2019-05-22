package me.ksviety.plugins.mc.locations.util;

import me.ksviety.plugins.mc.locations.Plugin;

import java.io.*;
import java.nio.charset.Charset;

public class FileManagement {

    private static final String SAVES_DIR_NAME = "saves";
    private static final String LOCALES_DIR_NAME = "locales";

    private static final String DATABASE_CONFIG_FILE_NAME = "dbconf.json";
    private static final String LOCATIONS_SAVE_FILE_NAME = "locations.json";
    private static final String PLAYERS_SAVE_FILE_NAME = "players.json";

    public static final File PLUGIN_DIR = Plugin.getPlugin(Plugin.class).getDataFolder();
    public static final File SAVES_DIR = new File(PLUGIN_DIR, SAVES_DIR_NAME);
    public static final File LOCALES_DIR = new File(PLUGIN_DIR, LOCALES_DIR_NAME);

    public static final File PLAYERS_SAVE_FILE = new File(SAVES_DIR, PLAYERS_SAVE_FILE_NAME);
    public static final File LOCATIONS_SAVE_FILE = new File(SAVES_DIR, LOCATIONS_SAVE_FILE_NAME);
    public static final File DATABASE_CONFIG_FILE = new File(PLUGIN_DIR, DATABASE_CONFIG_FILE_NAME);

    public static class Resources {

        public static final String DEFAULT_LOCALE_FILE = "/default.loc";

    }

    public static String readFile(File file) throws IOException {
        String currentLine;
        StringBuilder finalString = new StringBuilder();
        FileInputStream input = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));

        while ( (currentLine = bufferedReader.readLine()) != null )
            finalString.append(currentLine).append("\n");

        bufferedReader.close();

        return finalString.toString();
    }

    public static void writeFile(File file, String content) throws IOException {
        FileWriter writer;

        //  Creating missing dirs
        PLUGIN_DIR.mkdirs();
        SAVES_DIR.mkdirs();
        LOCALES_DIR.mkdirs();

        writer = new FileWriter(file);

        writer.write(content);
        writer.flush();

        writer.close();
    }

    public static String readResourceFile(String resource) throws IOException {
        String currentLine;
        StringBuilder finalString = new StringBuilder();
        InputStream inputStream = Plugin.class.getResourceAsStream(resource);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        while ( (currentLine = bufferedReader.readLine()) != null )
            finalString.append(currentLine).append("\n");

        bufferedReader.close();

        return finalString.toString();
    }

}
