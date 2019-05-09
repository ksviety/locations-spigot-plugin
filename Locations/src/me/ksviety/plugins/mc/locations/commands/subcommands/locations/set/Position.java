package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.misc.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import org.bukkit.command.CommandSender;

public class Position extends SubCommand {
    @Override
    public String getCommand() {
        return "position";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set position <location-name> <first|second>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location = null;


        //  Checking if the location has been found
        //  If not throw an error
        if (location == null) {

            errorMessage = "Cannot find location " + args[0] + ". Maybe it doesn't exist.";

            return false;
        }

        return true;
    }
}
