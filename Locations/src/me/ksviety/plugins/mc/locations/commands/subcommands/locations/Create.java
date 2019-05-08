package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.misc.SubCommand;
import me.ksviety.plugins.mc.locations.misc.Vector3;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import org.bukkit.command.CommandSender;

public class Create extends SubCommand {

    @Override
    public String getCommand() {
        return "create";
    }

    @Override
    public String getHelp() {
        return "/adminlocations create <location-name>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        //  Checking if args has enough values
        if (args.length < 1) {

            errorMessage = "Missing location name.";

            return false;
        }

        //  Creating the new location
        Location newLocation = new Location(args[0].toLowerCase(), args[0].toLowerCase(), Vector3.zero, Vector3.zero, Vector3.zero);

        //  Adding the location into the DataSave
        if (!Plugin.locationsData.addLocation(newLocation)) {

            errorMessage = "Cannot add the location. Maybe the location " + args[0] + " already exists.";

            return false;
        }

        successMessage = "The location " + args[0] + " has been successfully created.";

        return true;
    }

}
