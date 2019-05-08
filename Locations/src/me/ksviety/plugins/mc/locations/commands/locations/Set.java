package me.ksviety.plugins.mc.locations.commands.locations;

import me.ksviety.plugins.mc.locations.commands.ISubCommand;
import org.bukkit.command.CommandSender;

public class Set implements ISubCommand {

    @Override
    public String getCommand() {
        return "set";
    }

    @Override
    public String getHelp() {
        return "/locations set <location-name> <pos|label|warp-position> [param1, param2, ...]";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        return false;
    }
}
