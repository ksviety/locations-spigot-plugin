package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

public class Save extends SubCommand {

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
        if (args.length < 1)
            return false;

        switch (args[0].toLowerCase()) {
            case "players":
                Plugin.playersData.save();
                break;
            case "locations":
                Plugin.locationsData.save();
                break;
            case "all":
                Plugin.playersData.save();
                Plugin.locationsData.save();
                break;
        }

        return true;
    }

}
