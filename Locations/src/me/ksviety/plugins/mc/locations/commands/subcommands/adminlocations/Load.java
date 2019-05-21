package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.load.All;
import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.load.DatabaseConfig;
import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.load.Locations;
import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.load.Players;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.commands.util.SubCommandsExecutor;
import me.ksviety.plugins.mc.locations.data.Locale;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Load extends SubCommand {

    private SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

    @Override
    public String getCommand() {
        return "load";
    }

    @Override
    public String getHelp() {
        return "/adminlocations load <data>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        String subCommand;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length == 0) {

            errorMessage = Plugin.locale.getText(sender, Locale.Keys.NO_ARGUMENTS);

            return false;
        } else
            subCommand = args[0];

        return subCommandsExecutor.executeSubCommand(subCommand, sender, null);
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return subCommandsExecutor.getTabCompletion(sender, args[0], null);
    }

    public Load() {

        subCommandsExecutor.registerSubCommand(new All());
        subCommandsExecutor.registerSubCommand(new DatabaseConfig());
        subCommandsExecutor.registerSubCommand(new Locations());
        subCommandsExecutor.registerSubCommand(new Players());

    }

}
