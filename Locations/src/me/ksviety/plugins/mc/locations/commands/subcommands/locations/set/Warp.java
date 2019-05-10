package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp extends SubCommand {

    @Override
    public String getCommand() {
        return "warp";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set warp <location-name> [X, Y, Z]";
    }

    //  First argument: location name
    //  Second argument: X
    //  Third argument: Y
    //  Fourth argument: Z
    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location;
        Player player;
        Vector3 position = Vector3.zero;
        boolean isByCoord = false;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length > 0) {
            location = Plugin.locationsData.getLocation(args[0].toLowerCase());

            //  If supposed to be ran with specified coordinates
            if (args.length > 1) {

                switch (args.length) {
                    case 2:
                        //  The X coordinate has been given
                        //  But no Y coordinate set
                        errorMessage = "Y coordinate is not specified.";
                        return false;
                    case 3:
                        //  The X and Y coordinates has been given
                        //  But no Z coordinate set
                        errorMessage = "Z coordinate is not specified.";
                        return false;
                    case 4:
                        //  Everything is fine
                        isByCoord = true;
                        break;
                }

            } else if (!(sender instanceof Player)) {

                errorMessage = "Cannot set the warp via console without specified coordinates.";

                return false;
            }

        } else {

            //  No arguments been given at all
            errorMessage = "Location name has not been specified.";

            return false;
        }

        //  ERROR CHECK
        //  The location does not exist
        if (location == null) {

            errorMessage = "Cannot find location " + args[0] + ". Maybe it does not exist.";

            return false;
        }

        //  DOING THE STUFF

        //  Checking if to set the position by the give as arguments coordinates
        //  Or to set it by the player's position
        if (isByCoord) {

            try {

                position.setX(Integer.parseInt(args[1]));
                position.setY(Integer.parseInt(args[2]));
                position.setZ(Integer.parseInt(args[3]));

            } catch (NumberFormatException e) {

                errorMessage = "Unable to parse the given coordinates. They must be integers.";

                return false;
            }

        } else {
            player = (Player) sender;

            //  Setting the current position point
            position.setX((int) Math.floor(player.getLocation().getX()));
            position.setY((int) Math.floor(player.getLocation().getY()));
            position.setZ((int) Math.floor(player.getLocation().getZ()));

        }

        //  Setting the warp
        location.setWarpPosition(position);

        successMessage = "Warp has been successfully set to " + args[0] + ".";

        return true;
    }

}
