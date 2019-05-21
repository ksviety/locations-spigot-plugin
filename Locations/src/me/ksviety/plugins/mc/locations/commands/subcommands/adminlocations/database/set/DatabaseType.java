package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.data.database.IDatabase;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

public class DatabaseType extends SubCommand {

    @Override
    public String getCommand() {
        return "database";
    }

    @Override
    public String getHelp() {
        String[] names = new String[IDatabase.Database.values().length];
        String helpList;

        for (int i = 0; i < names.length; i++)
            names[i] = IDatabase.Database.values().clone()[i].name();

        helpList = "<" + String.join("|", names) + ">";

        return "/adminlocations database set database " + helpList;
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        IDatabase.Database database;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length == 0) {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        try {
            database = IDatabase.Database.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {

            errorMessage = Plugin.locale.getText(sender, Keys.DB_UNSUPPORTED_DATABASE);

            return false;
        }

        Plugin.databaseConfig.getConfig().setDatabase(database);

        switch (database) {
            case MYSQL:
                successMessage = Plugin.locale.getText(sender, Keys.DB_MYSQL_SET);
                break;
            case SQLITE:
                successMessage = Plugin.locale.getText(sender, Keys.DB_SQLITE_SET);
        }

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {
        List<String> suggestions = new ArrayList<>();

        for (IDatabase.Database database: IDatabase.Database.values())
            suggestions.add(database.name());

        return StringUtil.clarificateIgnoreCase(args[0], suggestions);
    }

}
