package me.ksviety.plugins.mc.locations.commands.util;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {

    protected String errorMessage;
    protected String successMessage;
    protected boolean showHelp = true;

    public abstract List<String> getTabCompletion(CommandSender sender, String[] args);

    public abstract String getCommand();

    public abstract String getHelp();

    public abstract boolean run(CommandSender sender, String[] args);

}
