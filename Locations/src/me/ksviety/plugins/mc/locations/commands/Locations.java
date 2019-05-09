package me.ksviety.plugins.mc.locations.commands;

import me.ksviety.plugins.mc.locations.commands.subcommands.locations.Create;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.Remove;
import me.ksviety.plugins.mc.locations.commands.misc.SubCommand;
import me.ksviety.plugins.mc.locations.commands.misc.SubCommandsExecutor;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.Save;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.Set;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class Locations implements CommandExecutor, TabCompleter {

    final SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String subCommand;
        String[] subCommandArgs = new String[(args.length > 0)? args.length-1: 0];
        boolean isExecutionSuccessful;

        //  Check if there is no any sub-command at all
        if (args.length < 1)
            return false;

        //  Getting out the sub-commands arguments list
        for (int i = 1; i < args.length; i++)
            subCommandArgs[i-1] = args[i];

        //  Initializing the sub-command name
        subCommand = args[0];

        //  Executing the sub-command
        isExecutionSuccessful = subCommandsExecutor.executeSubCommand(subCommand, sender, subCommandArgs);

        //  Showing the list on registered sub-commands if the execution is not successful
        if (!isExecutionSuccessful) {

            if (!(sender instanceof CommandBlock)) {

                for (SubCommand currentCommand: subCommandsExecutor.getRegisteredSubCommands())
                    sender.sendMessage(currentCommand.getCommand());

            }

        }

        return true;
    }



    public Locations() {

        subCommandsExecutor.registerSubCommand(new Create());
        subCommandsExecutor.registerSubCommand(new Remove());
        subCommandsExecutor.registerSubCommand(new Save());
        subCommandsExecutor.registerSubCommand(new Set());

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return subCommandsExecutor.getTabCompletion(sender, args);
    }

}
