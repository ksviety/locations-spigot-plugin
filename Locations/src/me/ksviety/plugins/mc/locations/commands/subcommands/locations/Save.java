package me.ksviety.plugins.mc.locations.commands.subcommands.locations;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.save.All;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.save.Locations;
import me.ksviety.plugins.mc.locations.commands.subcommands.locations.save.Players;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.commands.util.SubCommandsExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Save extends SubCommand {

    private SubCommandsExecutor subCommandsExecutor = new SubCommandsExecutor();

    @Override
    public String getCommand() {
        return "save";
    }

    @Override
    public String getHelp() {
        return "/adminlocations save <players|locations|all>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {

        //  Checking if there are any arguments else return with error
        if (args.length < 1) {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        subCommandsExecutor.executeSubCommand(args[0], sender, null);

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {

        return subCommandsExecutor.getTabCompletion(sender, args[0], new String[0]);
    }

    public Save() {

        subCommandsExecutor.registerSubCommand(new Players());
        subCommandsExecutor.registerSubCommand(new Locations());
        subCommandsExecutor.registerSubCommand(new All());

    }

}
