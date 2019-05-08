package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import me.ksviety.plugins.mc.locations.commands.misc.SubCommand;
import org.bukkit.command.CommandSender;

public class Set extends SubCommand {

    @Override
    public String getCommand() {
        return "set";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set <location-name> <pos|label|warp-position> [param1, param2, ...]";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        return false;
    }
}
