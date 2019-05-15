package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity extends SubCommand {

    private static final String ACTIVE = "active";
    private static final String INACTIVE = "inactive";

    @Override
    public String getCommand() {
        return "activity";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set activity <location-name> <active|inactive>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location;
        boolean activity;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length == 2) {

            location = Plugin.locationsData.getLocation(args[0]);

            switch (args[1]) {
                case ACTIVE:
                    activity = true;
                    break;
                case INACTIVE:
                    activity = false;
                    break;
                default:

                    errorMessage = "Unknown activity state.";

                    return false;
            }

        } else {

            errorMessage = "Not enough arguments.";

            return false;
        }

        //  ERROR CHECK
        //  Checking if the location cannot be found
        if (location == null) {

            errorMessage = "Cannot find location " + args[0] + ". Maybe it doesn't exist.";

            return false;
        }

        //  DOING THE STUFF

        location.setActive(activity);

        successMessage = "Successfully made " + location.getName() + " " + args[1].toLowerCase() + ".";

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> suggestions = new ArrayList<>();
        String key = args[0];

        switch (args.length) {
            case 1:

                for (Location location: Plugin.locationsData.getLocations())
                    suggestions.add(location.getName());

                break;
            case 2:
                key = args[1];

                suggestions = Arrays.asList(ACTIVE, INACTIVE);

                break;
        }

        return StringUtil.clarificateIgnoreCase(key, suggestions);
    }

}
