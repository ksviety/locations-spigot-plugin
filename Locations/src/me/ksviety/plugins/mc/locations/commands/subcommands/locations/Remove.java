package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Remove extends SubCommand {

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getCommand() {
        return "remove";
    }

    @Override
    public String getHelp() {
        return "/adminlocations remove <name>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        //  Checking if there is the name
        if (args.length < 1) {

            errorMessage = "Missing location name.";

            return false;
        }

        if (Plugin.locationsData.removeLocation(args[0].toLowerCase()))
            successMessage = "Location " + args[0].toLowerCase() + " has been successfully removed.";
        else {

            errorMessage = "Cannot remove location " + args[0].toLowerCase() + ". Maybe the location doesn't exist.";

            return false;
        }

        return true;
    }

}
