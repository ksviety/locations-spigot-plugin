package me.ksviety.plugins.mc.locations.commands.util;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.util.ChatWriter;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class SubCommandsExecutor {

    final List<SubCommand> subCommands = new ArrayList<>();

    //  New command registration
    public boolean registerSubCommand(SubCommand subCommand) {
        return subCommands.add(subCommand);
    }

    //  Return the commands list if the 'command' is null
    //  Else pass the 'args' to the command found by 'command'
    //  'args' must not contain the sub command
    public List<String> getTabCompletion(CommandSender sender, String command, String[] args) {
        List<String> availableSuggestions = new ArrayList<>();

        //  Return list of all registered sub-commands
        //  TODO add sub-commands permissions validation
        if (args.length == 0) {

            for (SubCommand subCommand: subCommands)
                availableSuggestions.add(subCommand.getCommand());

            return StringUtil.clarificateIgnoreCase(command, availableSuggestions);
        }

        //  Return list of received from the sub-command suggestions
        for (SubCommand subCommand: subCommands) {

            if (subCommand.getCommand().equalsIgnoreCase(command))
                return subCommand.getTabCompletion(sender, args);

        }

        //  Return just nothing if something went wrong
        return null;
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
                if (!success && subCommand.showHelp) {

                    //  Checking if the error message for the sub command set
                    //  Else display the default error message
                    if (subCommand.errorMessage != null)
                        ChatWriter.writeError(sender, subCommand.errorMessage);
                    else
                        ChatWriter.writeError(sender, Plugin.locale.getText(sender, Keys.COMMAND_RAN_UNSUCCESSFULLY));

                    ChatWriter.writeHelp(sender, subCommand.getHelp());

                } else {
                    //  Display success message

                    if (subCommand.successMessage != null)
                        ChatWriter.writeSuccess(sender, subCommand.successMessage);

                }

                return true;
            }

        }

        return false;
    }

    //  Executing a command by the instance
    public boolean executeSubCommand(SubCommand subCommand, CommandSender sender, String[] args) {
        boolean success = subCommand.run(sender, args);

        //  Print help and error messages if the command ran unsuccessfully
        if (!success && subCommand.showHelp) {

            //  Checking if the error message for the sub command set
            //  Else display the default error message
            if (subCommand.errorMessage != null)
                ChatWriter.writeError(sender, subCommand.errorMessage);
            else
                ChatWriter.writeError(sender, Plugin.locale.getText(sender, Keys.COMMAND_RAN_UNSUCCESSFULLY));

            ChatWriter.writeHelp(sender, subCommand.getHelp());

        } else {
            //  Display success message

            if (subCommand.successMessage != null)
                ChatWriter.writeSuccess(sender, subCommand.successMessage);

        }

        return true;
    }

}
