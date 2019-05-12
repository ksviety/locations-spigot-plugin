package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class Save extends SubCommand {

    private static final String PLAYERS = "players";
    private static final String LOCATIONS = "locations";
    private static final String ALL = "all";

    @Override
    public String getCommand() {
        return "save";
    }

    @Override
    public String getHelp() {
        return "/adminlocations save <players|locations|all>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        //  Checking if there are any arguments else return with error
        if (args.length < 1) {

            errorMessage = "No arguments have been given.";

            return false;
        }

        switch (args[0].toLowerCase()) {
            case PLAYERS:
                Plugin.playersData.save();
                successMessage = "Players have been successfully saved.";
                break;
            case LOCATIONS:
                Plugin.locationsData.save();
                successMessage = "Locations have been successfully saved.";
                break;
            case ALL:
                Plugin.playersData.save();
                Plugin.locationsData.save();
                successMessage = "Players and locations have been successfully saved.";
                break;
            default:
                errorMessage = "Cannot save anything by the argument " + args[0] + ".";
                return false;
        }

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {

        return Arrays.asList(PLAYERS, LOCATIONS, ALL);
    }

}
