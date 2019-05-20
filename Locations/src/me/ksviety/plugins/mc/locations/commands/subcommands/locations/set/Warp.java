package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.data.Locale;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Warp extends SubCommand {

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> locations = new ArrayList<>();

        for (Location location: Plugin.locationsData.getLocations())
            locations.add(location.getName());

        return StringUtil.clarificateIgnoreCase(args[0], locations);
    }

    @Override
    public String getCommand() {
        return "warp";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set warp <location-name> [X Y Z]";
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
                        errorMessage = Plugin.locale.getText(sender, Keys.NO_COORDINATE_Y);
                        return false;
                    case 3:
                        //  The X and Y coordinates has been given
                        //  But no Z coordinate set
                        errorMessage = Plugin.locale.getText(sender, Keys.NO_COORDINATE_Z);
                        return false;
                    case 4:
                        //  Everything is fine
                        isByCoord = true;
                        break;
                }

            } else if (!(sender instanceof Player)) {

                errorMessage = Plugin.locale.getText(sender, Keys.NO_COORDINATES);

                return false;
            }

        } else {

            //  No arguments been given at all
            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        //  ERROR CHECK
        //  The location does not exist
        if (location == null) {

            errorMessage = Plugin.locale.getText(sender, Keys.CANNOT_FIND_LOCATION);

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

                errorMessage = Plugin.locale.getText(sender, Keys.COORDINATES_NOT_INT);

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

        successMessage = Plugin.locale.getText(sender, Keys.WARP_SET);

        return true;
    }

}
