package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DatabaseInfo extends SubCommand {

    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public String getHelp() {
        return "/adminlocations database info";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        sender.sendMessage(Plugin.dbManager.getInfo());

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

}
