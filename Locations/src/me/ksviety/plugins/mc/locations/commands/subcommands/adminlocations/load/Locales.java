package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.load;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Locales extends SubCommand {

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getCommand() {
        return "locales";
    }

    @Override
    public String getHelp() {
        return "/adminlocations load locales";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        boolean success;

        success = Plugin.locale.load();

        if (success)
            successMessage = getCommand() + ": " + Plugin.locale.getText(sender, me.ksviety.plugins.mc.locations.data.Locale.Keys.LOADED);
        else
            errorMessage = getCommand() + ": " + Plugin.locale.getText(sender, me.ksviety.plugins.mc.locations.data.Locale.Keys.CANNOT_LOAD);

        return true;
    }

}
