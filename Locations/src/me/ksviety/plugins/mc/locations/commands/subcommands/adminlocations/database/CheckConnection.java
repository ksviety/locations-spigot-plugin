package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.data.Locale;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class CheckConnection extends SubCommand {

    @Override
    public String getCommand() {
        return "check";
    }

    @Override
    public String getHelp() {
        return "/adminlocations database check";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        if (Plugin.dbManager.getDatabase().connect())
            successMessage = Plugin.locale.getText(sender, Locale.Keys.DB_CONNECTED);
        else {

            errorMessage = Plugin.locale.getText(sender, Locale.Keys.DB_CANNOT_CONNECT);
            showHelp = false;

            return false;
        }

        Plugin.dbManager.getDatabase().disconnect();

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

}
