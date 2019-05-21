package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations;

import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.CheckConnection;
import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.DatabaseInfo;
import me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.DatabaseSet;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.commands.util.SubCommandsExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class Database extends SubCommand {

    private SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

    @Override
    public String getCommand() {
        return "database";
    }

    @Override
    public String getHelp() {
        return "/adminlocations database <sub-command> [param1, param2, ...]";
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

    public Database() {

        subCommandsExecutor.registerSubCommand(new DatabaseInfo());
        subCommandsExecutor.registerSubCommand(new DatabaseSet());
        subCommandsExecutor.registerSubCommand(new CheckConnection());

    }

}
