package me.ksviety.plugins.mc.locations.commands.subcommands.locations.save;

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

        Plugin.playersData.save();

        successMessage = Plugin.locale.getText(sender, Locale.Keys.SAVED);

        return true;
    }

}