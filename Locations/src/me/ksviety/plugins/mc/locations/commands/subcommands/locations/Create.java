package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.util.LocationType;
import me.ksviety.plugins.mc.locations.util.Vector2xz;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Create extends SubCommand {

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getCommand() {
        return "create";
    }

    @Override
    public String getHelp() {
        return "/adminlocations create <location-name> [world]";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        World world;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length > 0) {

            if (args.length == 1) {

                if (!(sender instanceof Player)) {

                    errorMessage = "World required.";

                    return false;
                } else
                    world = ((Player)sender).getWorld();

            } else
                world = Bukkit.getWorld(args[1]);


        } else {

            errorMessage = "Location name required.";

            return false;
        }

        //  Creating the new location
        Location newLocation = new Location(
                args[0].toLowerCase(),
                args[0].toLowerCase(),
                Vector3.zero,
                Vector3.zero,
                Vector3.zero,
                world,
                0,
                LocationType.OTHER);

        //  Adding the location into the DataSave
        if (!Plugin.locationsData.addLocation(newLocation)) {

            errorMessage = "Cannot add the location. Maybe the location " + args[0] + " already exists.";

            return false;
        }

        successMessage = "The location " + args[0] + " has been successfully created.";

        return true;
    }

}
