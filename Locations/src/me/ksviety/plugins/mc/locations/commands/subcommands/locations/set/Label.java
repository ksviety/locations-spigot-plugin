package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Label extends SubCommand {

    @Override
    public String getCommand() {
        return "label";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set label <location-name> <label>";
    }

    //  First argument: location name
    //  Second+ arguments: the label

    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location;
        String label;

        //  ERROR CHECK
        //  Arguments validation
        switch (args.length) {
            case 0:
                //  No location been given
                errorMessage = Plugin.locale.getText(sender, Keys.NO_LOCATION);
                return false;
            case 1:
                //  No label been given
                errorMessage = Plugin.locale.getText(sender, Keys.LABEL_EMPTY);
                return false;
            default:
                //  Everything is fine
                //  Setting the location
                location = Plugin.locationsData.getLocation(args[0].toLowerCase());

                //  Setting the label
                label = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                break;
        }

        //  ERROR CHECK
        //  If the location exists
        if (location == null) {

            errorMessage = Plugin.locale.getText(sender, Keys.CANNOT_FIND_LOCATION);

            return false;
        }

        //  DOING THE STUFF

        location.setLabel(label);

        successMessage = Plugin.locale.getText(sender, Keys.LABEL_APPLIED);

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> availableSuggestion = new ArrayList<>();

        switch (args.length) {
            //  Return list of all available locations
            case 1:
                for (Location location: Plugin.locationsData.getLocations())
                    availableSuggestion.add(location.getName());

                return StringUtil.clarificateIgnoreCase(args[0], availableSuggestion);
            //  Return the being edited location name
            case 2:

                for (Location location: Plugin.locationsData.getLocations()) {

                    if (location.equals(args[0]))
                        availableSuggestion.add(location.getName());

                }

                return availableSuggestion;
        }

        return null;
    }

}
