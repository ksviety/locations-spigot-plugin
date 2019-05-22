package me.ksviety.plugins.mc.locations.data;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.util.FileManagement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

//  TODO add multi-language support
public class Locale implements ILoadable {

    private Map<List<String>, Properties> locales = new HashMap<>();
    private Properties defaultLocale = new Properties();

    //  THIS IS THE MOST BORING PART OF ALL THE PLUGIN
    public static class Keys {
        public static final String LANGUAGE = "language";
        public static final String LOCATION_NOT_OPENED = "location-not-opened";
        public static final String PERMISSION_FAIL = "permission-fail";
        public static final String CANNOT_FIND_PLAYER = "cannot-find-player";
        public static final String CANNOT_FIND_LOCATION = "cannot-find-location";
        public static final String LOCATION_IS_INACTIVE = "location-is-inactive";
        public static final String ACTION_CANCELLED = "action-cancelled";
        public static final String COMMAND_RAN_UNSUCCESSFULLY = "command-ran-unsuccessfully";
        public static final String NO_ARGUMENTS = "no-arguments";
        public static final String NO_LOCATION = "no-location";
        public static final String LOCATION_REMOVED = "location-removed";
        public static final String CANNOT_REMOVE_LOCATION = "cannot-remove-location";
        public static final String NO_WORLD = "no-world";
        public static final String CANNOT_CREATE_LOCATION = "cannot-create-location";
        public static final String LOCATION_CREATED = "location-created";
        public static final String SAVED = "saved";
        public static final String MADE_LOCATION_ACTIVE = "made-location-active";
        public static final String MADE_LOCATION_INACTIVE = "made-location-inactive";
        public static final String UNKNOWN_ACTIVITY_STATE = "unknown-activity-state";
        public static final String LABEL_APPLIED = "label-applied";
        public static final String LABEL_EMPTY = "label-empty";
        public static final String NO_POSITION = "no-position";
        public static final String NO_COORDINATE_Y = "no-coordinate-y";
        public static final String NO_COORDINATE_Z = "no-coordinate-z";
        public static final String COORDINATES_NOT_INT = "coordinates-not-int";
        public static final String FIRST_POSITION_SET = "first-position-set";
        public static final String SECOND_POSITION_SET = "second-position-set";
        public static final String UNKNOWN_POSITION = "unknown-position";
        public static final String PRIORITY_NOT_INT = "priority-not-int";
        public static final String PRIORITY_APPLIED = "priority-applied";
        public static final String CHANGED_LOCATION_TYPE = "changed-location-type";
        public static final String NO_COORDINATES = "no-coordinates";
        public static final String WARP_SET = "warp-set";
        public static final String LOCATION_OPENED = "location-opened";
        public static final String UNKNOWN_LOCATION_TYPE = "unknown-location-type";
        public static final String DB_USERNAME_SET = "db-username-set";
        public static final String DB_PASSWORD_SET = "db-password-set";
        public static final String DB_URL_SET = "db-url-set";
        public static final String DB_MYSQL_SET = "db-mysql-set";
        public static final String DB_SQLITE_SET = "db-sqlite-set";
        public static final String DB_UNSUPPORTED_DATABASE = "db-unsupported-database";
        public static final String ILLEGAL_ARGUMENT = "illegal-argument";
        public static final String DB_CONNECTED = "db-connected";
        public static final String DB_USE = "db-use";
        public static final String DB_NOT_USE = "db-not-use";
        public static final String DB_CANNOT_CONNECT = "db-cannot-connect";
        public static final String LOADED = "loaded";
        public static final String CANNOT_SAVE = "cannot-save";
        public static final String CANNOT_LOAD = "cannot-load";
    }

    public String getText(Player player, String key) {

        for (Properties locale: locales.values()) {

            if (locale.getProperty(Keys.LANGUAGE).contains(getLocale(player))) {
                String text = locale.getProperty(key);

                return text == null? defaultLocale.getProperty(key): text;
            }

        }

        return defaultLocale.getProperty(key);
    }

    public String getText(String key) {
        return defaultLocale.getProperty(key);
    }

    public String getText(CommandSender sender, String key) {

        if (sender instanceof Player)
            return getText((Player)sender, key);
        else
            return getText(key);

    }

    private String getLocale(Player p) {
        String language = "";

        try {
            Object ep = getMethod("getHandle", p.getClass()).invoke(p, (Object[]) null);
            Field f = ep.getClass().getDeclaredField("locale");
            f.setAccessible(true);
            language = (String) f.get(ep);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return language.toLowerCase();
    }
    private Method getMethod(String name, Class<?> clazz) {

        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().equals(name))
                return m;
        }

        return null;
    }

    private static class Filter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".loc");
        }

    }

    @Override
    public boolean load() {
        String data;
        List<Properties> locales = new ArrayList<>();
        String[] paths;
        Properties prop;

        try {

            paths = FileManagement.LOCALES_DIR.list(new Filter());
            paths = paths == null? new String[0]: paths; // Avoiding nullPointer

            //  Add default locale to the locales list
            data = FileManagement.readResourceFile(FileManagement.Resources.DEFAULT_LOCALE_FILE);
            prop = new Properties();
            prop.load(new StringReader(data));
            locales.add(prop);

            //  Load locales array
            for (String path: paths) {
                File file = new File(FileManagement.LOCALES_DIR, path);

                data = FileManagement.readFile(file);
                prop = new Properties(); // Create new object in memory
                prop.load(new StringReader(data));
                locales.add(prop);

            }

            //  Set up locales map
            for (Properties locale: locales) {
                List<String> languages = Arrays.asList(locale.getProperty(Keys.LANGUAGE).replaceAll(" ", "").split(","));
                this.locales.put(languages, locale);

                if (languages.contains("default"))
                    this.defaultLocale = locale;

                Plugin.getPlugin(Plugin.class).getLogger().warning(String.join(", ", languages));
            }

            Plugin.getPlugin(Plugin.class).getLogger().warning("Fallback(default) locale: " + defaultLocale.getProperty(Keys.LANGUAGE));
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

}
