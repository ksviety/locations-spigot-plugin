package me.ksviety.plugins.mc.locations.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class SubCommandsExecutor {

    final List<ISubCommand> subCommands = new ArrayList();

    //  New command registration
    public boolean registerSubCommand(ISubCommand subCommand) {

        return subCommands.add(subCommand);
    }

    //  Get the list of the registered sub-commands
    public ISubCommand[] getRegisteredSubCommands() {
        return subCommands.toArray(new ISubCommand[subCommands.size()]);
    }

    //  Executing a command by its name
    public boolean executeSubCommand(String command, CommandSender sender, String[] args) {

        for (ISubCommand subCommand: subCommands) {

            if (subCommand.getCommand().equals(command))
                return subCommand.run(sender, args);

        }

        return false;
    }

    //  Executing a command by the instance
    public boolean executeSubCommand(ISubCommand subCommand, CommandSender sender, String[] args) {

        return subCommand.run(sender, args);
    }

}
