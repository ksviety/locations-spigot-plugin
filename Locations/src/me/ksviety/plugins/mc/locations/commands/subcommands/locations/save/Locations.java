package me.ksviety.plugins.mc.locations.commands.subcommands.locations.save;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.data.Locale;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Locations extends SubCommand {

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getCommand() {
        return "locations";
    }

    @Override
    public String getHelp() {
        return "/adminlocations save locations";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        Plugin.locationsData.save();

        successMessage = Plugin.locale.getText(sender, Locale.Keys.SAVED);

        return true;
    }

}
