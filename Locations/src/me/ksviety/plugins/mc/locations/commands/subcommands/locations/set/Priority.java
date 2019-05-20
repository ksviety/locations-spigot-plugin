package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Priority extends SubCommand {

    @Override
    public String getCommand() {
        return "priority";
    }

    @Override
    public String getHelp() {
        return "/adminlocation set priority <location-name> <priority>";
    }

    //  args[0]: location name
    //  args[1]: priority
    @Override
    public boolean run(CommandSender sender, String[] args) {
        int priority;
        Location location;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length >= 2) {

            location = Plugin.locationsData.getLocation(args[0]);

        } else {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        //  ERROR CHECK
        //  Location not found
        if (location == null) {

            errorMessage = Plugin.locale.getText(sender, Keys.CANNOT_FIND_LOCATION);

            return false;
        }

        //  DOING THE STUFF

        try {
            priority = Integer.parseInt(args[1]);

            location.setPriority(priority);

            successMessage = Plugin.locale.getText(sender, Keys.PRIORITY_APPLIED);

            return true;
        } catch (NumberFormatException e) {

            errorMessage = Plugin.locale.getText(sender, Keys.PRIORITY_NOT_INT);

            return false;
        }

    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> locations = new ArrayList<>();

        if (args.length == 1) {

            for (Location location: Plugin.locationsData.getLocations())
                locations.add(location.getName());

        }

        return StringUtil.clarificateIgnoreCase(args[0], locations);
    }

}
