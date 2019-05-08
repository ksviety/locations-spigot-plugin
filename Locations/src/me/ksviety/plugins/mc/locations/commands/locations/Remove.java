package me.ksviety.plugins.mc.locations.commands.locations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.ISubCommand;
import org.bukkit.command.CommandSender;

public class Remove implements ISubCommand {

    @Override
    public String getCommand() {
        return "remove";
    }

    @Override
    public String getHelp() {
        return "/locations remove <name>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        //  Checking if there is the name
        if (args.length < 1)
            return false;

        Plugin.locationsData.removeLocation(args[0].toLowerCase());

        sender.sendMessage("Successfully removed the location " + args[0].toLowerCase());

        return true;
    }

}
