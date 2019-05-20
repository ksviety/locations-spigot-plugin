package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Info extends SubCommand {

    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public String getHelp() {
        return "/adminlocations info <location-name>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        Location location;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length >= 1)
            location = Plugin.locationsData.getLocation(args[0]);
        else {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        //  ERROR CHECK
        //  Checking if the location exists
        if (location == null) {

            errorMessage = Plugin.locale.getText(sender, Keys.CANNOT_FIND_LOCATION);

            return false;
        }

        //  DOING THE STUFF

        sender.sendMessage(location.toString());

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> locations = new ArrayList<>();

        for (Location location: Plugin.locationsData.getLocations())
            locations.add(location.getName());

        return StringUtil.clarificateIgnoreCase(args[0], locations);
    }

}
