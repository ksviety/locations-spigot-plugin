package me.ksviety.plugins.mc.locations.commands;

import org.bukkit.command.CommandSender;

public interface ISubCommand {

    String getCommand();

    String getHelp();

    boolean run(CommandSender sender, String[] args);
}
