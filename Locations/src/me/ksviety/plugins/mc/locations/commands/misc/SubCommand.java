package me.ksviety.plugins.mc.locations.commands.misc;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {

    protected String errorMessage;
    protected String successMessage;

    public abstract String getCommand();

    public abstract String getHelp();

    public abstract List<String> getTabCompletion(CommandSender sender, String[] args);

    public abstract boolean run(CommandSender sender, String[] args);

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
