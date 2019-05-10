package me.ksviety.plugins.mc.locations.commands.util;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    protected String errorMessage;
    protected String successMessage;

    public abstract String getCommand();

    public abstract String getHelp();

    public abstract boolean run(CommandSender sender, String[] args);

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
