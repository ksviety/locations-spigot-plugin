package me.ksviety.plugins.mc.locations.data;

import me.ksviety.plugins.mc.locations.util.FileManagement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;

//  TODO add multi-language support
public class Locale implements ILoadable {

    //private Map<String, Properties> locales = new TreeMap<>();
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
    }

    public String getText(Player player, String key) {
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

    private class filter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.isFile() && pathname.toString().endsWith(".loc");
        }

    }

    @Override
    public boolean load() {

        try {

            defaultLocale.load(new StringReader(FileManagement.readResourceFile(FileManagement.Resources.DEFAULT_LOCALE_FILE)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
