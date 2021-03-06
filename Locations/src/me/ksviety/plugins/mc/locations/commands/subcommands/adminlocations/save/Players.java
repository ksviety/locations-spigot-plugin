package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.save;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.data.Locale;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Players extends SubCommand {

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getCommand() {
        return "players";
    }

    @Override
    public String getHelp() {
        return "/adminlocations save players";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        boolean success;

        success = Plugin.playersData.save();

        if (success)
            successMessage = getCommand() + ": " + Plugin.locale.getText(sender, Locale.Keys.SAVED);
        else {
            errorMessage = getCommand() + ": " + Plugin.locale.getText(sender, Locale.Keys.CANNOT_SAVE);
            showHelp = false;
        }

        return success;
    }

}