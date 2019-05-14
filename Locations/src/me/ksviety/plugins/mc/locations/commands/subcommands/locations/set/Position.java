package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Position extends SubCommand {

    private static final String FIRST = "first";
    private static final String SECOND = "second";

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getCommand() {
        return "position";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set position <location-name> <first|second> [X, Y, Z]";
    }

    //  First argument: Location name
    //  Second argument: Position [first|second]
    //  Third argument: X
    //  Fourth argument: Z
    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location;
        Vector3 position = Vector3.zero;
        Player player;
        boolean isByCoord = false;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length > 0) {
            location = Plugin.locationsData.getLocation(args[0].toLowerCase());

            switch (args.length) {
                case 1:
                    //  No position been given
                    errorMessage = "Position has not been specified.";
                    return false;
                case 3:
                    //  The X coordinate has been given
                    //  But no Y coordinate set
                    errorMessage = "Y coordinate is not specified.";
                    return false;
                case 4:
                    //  The Y coordinate has been given
                    //  But no Z coordinate set
                    errorMessage = "Z coordinate is not specified.";
                    return false;
                case 5:
                    //  Coordinates are specified
                    isByCoord = true;
                    break;
            }

        } else {

            //  No arguments been given at all
            errorMessage = "Location name has not been specified.";

            return false;
        }

        //  ERROR CHECK
        //  Checking if the location exists
        if (location == null) {

            errorMessage = "Cannot find location " + args[0] + ". Maybe it doesn't exist.";

            return false;
        }

        //  DOING THE STUFF

        //  Checking if to set the position by the give as arguments coordinates
        //  Or to set it by the player's position
        if (isByCoord) {

            try {

                position.setX(Integer.parseInt(args[2]));
                position.setY(Integer.parseInt(args[3]));
                position.setZ(Integer.parseInt(args[4]));

            } catch (NumberFormatException e) {

                errorMessage = "Unable to parse the given coordinates. They must be integers.";

                return false;
            }

        } else {
            player = (Player) sender;

            //  Setting the current position point
            position.setX((int) player.getLocation().getX());
            position.setY((int) player.getLocation().getY());
            position.setZ((int) player.getLocation().getZ());

        }

        //  Matching the typed position with existing ones
        //  First one
        if (args[1].equalsIgnoreCase(FIRST)) {

            //  Setting second position
            location.setFirstPosition(position);

            successMessage = "The first position has been successfully set to " + args[0]  + ".";

            return true;
        }

        //  Second one
        if (args[1].equalsIgnoreCase(SECOND)) {

            //  Setting second position
            location.setSecondPosition(position);


            successMessage = "The second position has been successfully set to " + args[0]  + ".";

            return true;
        }

        //  Throwing an error message
        //  If none of possible positions has been typed
        errorMessage = "Unknown position given.";

        return false;
    }

}
