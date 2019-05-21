package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.set;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.LocationType;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Type extends SubCommand {

    @Override
    public String getCommand() {
        return "type";
    }

    @Override
    public String getHelp() {
        String[] names = new String[LocationType.values().length];
        String helpList;

        for (int i = 0; i < names.length; i++)
            names[i] = LocationType.values().clone()[i].name();

        helpList = "<" + String.join("|", names) + ">";

        return "/adminlocations set type <location-name> " + helpList;
    }

    //  args[0:location name
    //  args[1:location type
    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location;
        LocationType type;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length == 2) {

            location = Plugin.locationsData.getLocation(args[0]);

        } else {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        //  ERROR CHECK
        //  Checking if the location exists
        if (location == null) {

            errorMessage = Plugin.locale.getText(sender, Keys.CANNOT_FIND_LOCATION);

            return false;
        }

        //  DOING THE STUFF
        try {
            type = LocationType.valueOf(args[1]);

            location.setType(type);

            successMessage = Plugin.locale.getText(sender, Keys.CHANGED_LOCATION_TYPE);

        } catch (IllegalArgumentException e) {

            errorMessage = Plugin.locale.getText(sender, Keys.UNKNOWN_LOCATION_TYPE);

            return false;
        }

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> suggestions = new ArrayList<>();
        String key = args[0];

        //  Return adminlocations list as first parameter suggestion
        //  And available location types as second
        switch (args.length) {
            case 1:

                for (Location location: Plugin.locationsData.getLocations())
                    suggestions.add(location.getName());

                break;
            case 2:
                key = args[1];

                for (LocationType type: LocationType.values())
                    suggestions.add(type.name());

                break;
        }

        return StringUtil.clarificateIgnoreCase(key, suggestions);
    }

}
