package me.ksviety.plugins.mc.locations.commands.locations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.ISubCommand;
import me.ksviety.plugins.mc.locations.misc.Vector3;
import me.ksviety.plugins.mc.locations.pojo.saves.Location;
import org.bukkit.command.CommandSender;

public class Create implements ISubCommand {

    @Override
    public String getCommand() {
        return "create";
    }

    @Override
    public String getHelp() {
        return "/locations create <location-name>\n" +
                "/location create big_city";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        //  Creating the new location
        Location newLocation = new Location(args[0].toLowerCase(), args[0].toLowerCase(), Vector3.zero, Vector3.zero, Vector3.zero);

        //  Adding the location into the DataSave
        if (!Plugin.locationsData.addLocation(newLocation)) {
            sender.sendMessage("Unable to create the location.");
            return false;
        }

        sender.sendMessage("The location " + args[0] + "has been successfully created.");

        return true;
    }

}
