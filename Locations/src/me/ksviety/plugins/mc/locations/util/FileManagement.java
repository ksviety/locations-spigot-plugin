package me.ksviety.plugins.mc.locations.util;

import me.ksviety.plugins.mc.locations.Plugin;

import java.io.*;

public class FileManagement {

    private static final String SAVES_DIR_NAME = "saves";
    private static final String SETTINGS_FILE_NAME = "settings.json";
    private static final String LOCATIONS_SAVE_FILE_NAME = "locations.json";
    private static final String PLAYERS_SAVE_FILE_NAME = "players.json";

    public static final File PLUGIN_DIR = Plugin.getPlugin(Plugin.class).getDataFolder();
    public static final File SAVES_DIR = new File(PLUGIN_DIR, SAVES_DIR_NAME);

    public static final File SETTINGS_FILE = new File(PLUGIN_DIR, SETTINGS_FILE_NAME);
    public static final File PLAYERS_SAVE_FILE = new File(SAVES_DIR, PLAYERS_SAVE_FILE_NAME);
    public static final File LOCATIONS_SAVE_FILE = new File(SAVES_DIR, LOCATIONS_SAVE_FILE_NAME);

    public static class Resources {

        public static final String ENGLISH_LOCALE_FILE = "english.loc";
        public static final String RUSSIAN_LOCALE_FILE = "russian.loc";
        public static final String DEFAULT_SETTIGNS_FILE = "settings.json";

    }

    public static String readFile(File file) throws IOException {
        String currentLine;
        StringBuilder finalString = new StringBuilder();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        while ( (currentLine = bufferedReader.readLine()) != null )
            finalString.append(currentLine);

        bufferedReader.close();

        return finalString.toString();
    }

    public static void writeFile(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file);

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
            finalString.append(currentLine);

        bufferedReader.close();

        return finalString.toString();
    }

}
