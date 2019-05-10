package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

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
                errorMessage = "Location has not been specified.";
                return false;
            case 1:
                //  No label been given
                errorMessage = "Label cannot be empty.";
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

            errorMessage = "Cannot find location " + args[0].toLowerCase() + ". Maybe it doesn't exist.";

            return false;
        }

        //  DOING THE STUFF

        location.setLabel(label);

        successMessage = "Label \"" + label + "\" has been successfully applied to location " + args[0].toLowerCase() + ".";

        return true;
    }

}
