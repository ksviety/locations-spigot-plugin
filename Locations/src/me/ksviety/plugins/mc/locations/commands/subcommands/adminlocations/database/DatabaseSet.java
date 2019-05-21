package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database;

import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.set.*;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.commands.util.SubCommandsExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class DatabaseSet extends SubCommand {

    private SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

    @Override
    public String getCommand() {
        return "set";
    }

    @Override
    public String getHelp() {
        return "/adminlocations database set <sub-command> [params]";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        String[] subCommandArgs = args.length > 1? Arrays.copyOfRange(args, 1, args.length): new String[0];
        String subCommand = args.length > 0? args[0]: "";

        return subCommandsExecutor.executeSubCommand(subCommand, sender, subCommandArgs);
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        String[] subCommandArgs = Arrays.copyOfRange(args, 1, args.length);

        return subCommandsExecutor.getTabCompletion(sender, args[0], subCommandArgs);
    }

    public DatabaseSet() {

        subCommandsExecutor.registerSubCommand(new Password());
        subCommandsExecutor.registerSubCommand(new Username());
        subCommandsExecutor.registerSubCommand(new DatabaseType());
        subCommandsExecutor.registerSubCommand(new DatabaseURL());
        subCommandsExecutor.registerSubCommand(new UseDatabase());

    }

}
