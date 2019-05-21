package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.load;

import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.commands.util.SubCommandsExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class All extends SubCommand {

    private SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public String getCommand() {
        return "all";
    }

    @Override
    public String getHelp() {
        return "/adminlocations load all";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        subCommandsExecutor.executeSubCommand(new Locations(), sender, new String[0]);
        subCommandsExecutor.executeSubCommand(new Players(), sender, new String[0]);
        subCommandsExecutor.executeSubCommand(new DatabaseConfig(), sender, new String[0]);

        return true;
    }

}
