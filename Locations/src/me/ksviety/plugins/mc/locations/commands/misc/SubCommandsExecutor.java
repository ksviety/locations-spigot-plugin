package me.ksviety.plugins.mc.locations.commands.misc;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class SubCommandsExecutor {

    final List<SubCommand> subCommands = new ArrayList<>();

    //  New command registration
    public boolean registerSubCommand(SubCommand subCommand) {
        return subCommands.add(subCommand);
    }

    //  Get the list of the registered sub-commands
    public List<SubCommand> getRegisteredSubCommands() {
        return subCommands;
    }

    //  Executing a command by its name
    //  Return true if the command has ran
    //  Success of the sub commands should not affect
    public boolean executeSubCommand(String command, CommandSender sender, String[] args) {

        for (SubCommand subCommand: subCommands) {

            if (subCommand.getCommand().equals(command)) {
                boolean success = subCommand.run(sender, args);

                //  Print help and error messages if the command ran unsuccessfully
                if (!success) {

                    //  Checking if the error message for the sub command set
                    //  Else display the default error message
                    if (subCommand.getErrorMessage() != null)
                        sender.sendMessage(subCommand.getErrorMessage());
                    else
                        sender.sendMessage(command + " has ran unsuccessfully.");

                    sender.sendMessage(subCommand.getHelp());

                } else {
                    //  Display success message

                    //  Checking if the success message for the sub command set
                    //  If it hasn't been set display default success message
                    if (subCommand.getSuccessMessage() != null)
                        sender.sendMessage(subCommand.getSuccessMessage());
                    else
                        sender.sendMessage(command + " has ran successfully.");

                }

                return true;
            }

        }

        return false;
    }

    //  Executing a command by the instance
    public boolean executeSubCommand(SubCommand subCommand, CommandSender sender, String[] args) {

        return subCommand.run(sender, args);
    }

}
