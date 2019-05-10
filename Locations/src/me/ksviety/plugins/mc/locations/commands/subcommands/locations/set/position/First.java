package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set.position;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.misc.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import org.bukkit.command.CommandSender;

public class First extends SubCommand {

    @Override
    public String getCommand() {
        return "first";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set position <location-name> first";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        String locationName = args[0];
        Location location = Plugin.locationsData.getLocation(locationName);

        //  Checking if the location exists
        if (location == null) {

            errorMessage = "Cannot find location " + args[0] + ". Maybe it doesn't exist.";

            return false;
        }

        //  Setting the first position for

        return true;
    }

}
