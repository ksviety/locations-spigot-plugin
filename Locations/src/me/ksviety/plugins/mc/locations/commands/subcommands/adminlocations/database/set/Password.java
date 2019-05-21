package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

public class Password extends SubCommand {

    @Override
    public String getCommand() {
        return "password";
    }

    @Override
    public String getHelp() {
        return "/adminlocations database set password <password>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        String password;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length == 0) {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        } else
            password = args[0];

        Plugin.databaseConfig.getConfig().setPassword(password);

        successMessage = Plugin.locale.getText(sender, Keys.DB_PASSWORD_SET);

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

}
