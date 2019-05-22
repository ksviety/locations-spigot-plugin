package me.ksviety.plugins.mc.locations.util;

import me.ksviety.plugins.mc.locations.Plugin;

import java.io.File;

public class Files {
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
}
