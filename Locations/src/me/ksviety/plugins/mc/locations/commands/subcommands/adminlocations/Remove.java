package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Remove extends SubCommand {

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

            errorMessage = Plugin.locale.getText(sender, Keys.NO_LOCATION);

            return false;
        }

        if (Plugin.locationsData.removeLocation(args[0].toLowerCase()))
            successMessage = Plugin.locale.getText(sender, Keys.LOCATION_REMOVED);
        else {

            errorMessage = Plugin.locale.getText(sender, Keys.CANNOT_REMOVE_LOCATION);

            return false;
        }

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> availableLocations = new ArrayList<>();

        for (Location location: Plugin.locationsData.getLocations())
            availableLocations.add(location.getName());

        return args.length > 0? StringUtil.clarificateIgnoreCase(args[0], availableLocations): availableLocations;
    }

}
