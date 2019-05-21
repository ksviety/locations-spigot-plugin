package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.set;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Position extends SubCommand {

    private static final String FIRST = "first";
    private static final String SECOND = "second";

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> suggestions = new ArrayList<>();
        String key = args[0];

        switch (args.length) {
            case 1:

                for (Location location : Plugin.locationsData.getLocations())
                    suggestions.add(location.getName());

                break;
            case 2:

                suggestions = Arrays.asList(FIRST, SECOND);

                key = args[1];

                break;
        }

        return StringUtil.clarificateIgnoreCase(key, suggestions);
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
                    errorMessage = Plugin.locale.getText(sender, Keys.NO_POSITION);
                    return false;
                case 3:
                    //  The X coordinate has been given
                    //  But no Y coordinate set
                    errorMessage = Plugin.locale.getText(sender, Keys.NO_COORDINATE_Y);
                    return false;
                case 4:
                    //  The Y coordinate has been given
                    //  But no Z coordinate set
                    errorMessage = Plugin.locale.getText(sender, Keys.NO_COORDINATE_Z);
                    return false;
                case 5:
                    //  Coordinates are specified
                    isByCoord = true;
                    break;
            }

        } else {

            //  No arguments been given at all
            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        //  ERROR CHECK
        //  Checking if the sender is not console if setting not by coordinates
        if (!(sender instanceof Player) && !isByCoord) {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_COORDINATES);

            return false;
        }

        //  ERROR CHECK
        //  Checking if the location exists
        if (location == null) {

            errorMessage = Plugin.locale.getText(sender, Keys.CANNOT_FIND_LOCATION);

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

                errorMessage = Plugin.locale.getText(sender, Keys.COORDINATES_NOT_INT);

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

            successMessage = Plugin.locale.getText(sender, Keys.FIRST_POSITION_SET);

            return true;
        }

        //  Second one
        if (args[1].equalsIgnoreCase(SECOND)) {

            //  Setting second position
            location.setSecondPosition(position);


            successMessage = Plugin.locale.getText(sender, Keys.SECOND_POSITION_SET);

            return true;
        }

        //  Throwing an error message
        //  If none of possible positions has been typed
        errorMessage = Plugin.locale.getText(sender, Keys.UNKNOWN_POSITION);

        return false;
    }

}
