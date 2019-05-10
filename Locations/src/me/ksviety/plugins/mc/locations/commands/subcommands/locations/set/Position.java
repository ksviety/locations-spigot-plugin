package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.misc.SubCommand;
import me.ksviety.plugins.mc.locations.misc.Vector2xz;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Position extends SubCommand {

    public static final String FIRST = "first";
    public static final String SECOND = "second";

    @Override
    public String getCommand() {
        return "position";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set position <location-name> <first|second>";
    }

    //  First argument: Location name
    //  Second argument: Position [first|second]
    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location;

        //  ERROR CHECK
        //  Checking if the sender is a player
        //  If so initialize playerPosition
        if (!(sender instanceof Player)) {

            errorMessage = "The operation is only available for in-game players.";

            return false;
        }

        //  ERROR CHECK
        //  Checking if it has enough arguments
        //  Initialize location if everything's fine
        if (args.length < 2) {

            //  Specifying the error message
            if (args.length < 1)
                errorMessage = "The location name must be specified.";
            else
                errorMessage = "The position must be specified.";

            return false;
        } else
            location = Plugin.locationsData.getLocation(args[0]);

        //  ERROR CHECK
        //  Checking if the location exists
        if (location == null) {

            errorMessage = "Cannot find location " + args[0] + ". Maybe it doesn't exist.";

            return false;
        }

        sender.sendMessage(location.toString());

        return true;
    }

}
