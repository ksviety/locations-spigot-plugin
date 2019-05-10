package me.ksviety.plugins.mc.locations.commands.subcommands.locations.set;

import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

public class Warp extends SubCommand {

    @Override
    public String getCommand() {
        return "warp";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        return false;
    }

}
