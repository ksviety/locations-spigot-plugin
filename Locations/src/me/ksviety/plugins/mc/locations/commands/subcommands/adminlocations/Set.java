package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations;

import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.set.*;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.commands.util.SubCommandsExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class Set extends SubCommand {

    private final SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

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

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        String[] subCommandArgs = (args.length == 0)? args: Arrays.copyOfRange(args, 1, args.length);

        if (args.length > 0)
            return subCommandsExecutor.getTabCompletion(sender, args[0], subCommandArgs);

        return null;
    }

    public Set() {

        subCommandsExecutor.registerSubCommand(new Position());
        subCommandsExecutor.registerSubCommand(new Warp());
        subCommandsExecutor.registerSubCommand(new Label());
        subCommandsExecutor.registerSubCommand(new Priority());
        subCommandsExecutor.registerSubCommand(new Type());
        subCommandsExecutor.registerSubCommand(new Activity());

    }

}
