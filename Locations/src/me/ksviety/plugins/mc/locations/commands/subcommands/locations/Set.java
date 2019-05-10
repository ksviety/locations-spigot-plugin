package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import me.ksviety.plugins.mc.locations.commands.subcommands.locations.set.Label;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.set.Warp;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.commands.util.SubCommandsExecutor;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.set.Position;
import org.bukkit.command.CommandSender;

public class Set extends SubCommand {

    final SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

    @Override
    public String getCommand() {
        return "set";
    }

    @Override
    public String getHelp() {
        return "/adminlocations set <position|label|warp> <location-name> [param1, param2, ...]";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        String subCommand;
        String[] subCommandArgs = new String[(args.length > 0)? args.length-1: 0];

        //  Check if there is no any sub-command at all
        if (args.length < 1)
            return false;

        //  Getting out the sub-commands arguments list
        for (int i = 1; i < args.length; i++)
            subCommandArgs[i-1] = args[i];

        //  Initializing the sub-command name
        subCommand = args[0];

        subCommandsExecutor.executeSubCommand(subCommand, sender, subCommandArgs);

        return true;
    }

    public Set() {

        subCommandsExecutor.registerSubCommand(new Position());
        subCommandsExecutor.registerSubCommand(new Warp());
        subCommandsExecutor.registerSubCommand(new Label());

    }

}
