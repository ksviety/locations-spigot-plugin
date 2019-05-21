package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.set;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class Username extends SubCommand {

    @Override
    public String getCommand() {
        return "username";
    }

    @Override
    public String getHelp() {
        return "/adminlocations database set username <username>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        String username;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length == 0) {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        } else
            username = args[0];

        Plugin.databaseConfig.getConfig().setUsername(username);

        successMessage = Plugin.locale.getText(sender, Keys.DB_USERNAME_SET);

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

}
