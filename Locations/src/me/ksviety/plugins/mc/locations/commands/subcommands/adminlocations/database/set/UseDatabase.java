package me.ksviety.plugins.mc.locations.commands.subcommands.adminlocations.database.set;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.commands.util.SubCommand;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys;

public class UseDatabase extends SubCommand {

    @Override
    public String getCommand() {
        return "use";
    }

    @Override
    public String getHelp() {
        return "/adminlocations database set use <yes|no>";
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        boolean use;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length == 0) {

            errorMessage = Plugin.locale.getText(sender, Keys.NO_ARGUMENTS);

            return false;
        }

        switch (args[0].toLowerCase()) {
            case "yes":
                use = true;
                successMessage = Plugin.locale.getText(sender, Keys.DB_USE);
                break;
            case "no":
                use = false;
                successMessage = Plugin.locale.getText(sender, Keys.DB_NOT_USE);
                break;
            default:

                errorMessage = Plugin.locale.getText(sender, Keys.ILLEGAL_ARGUMENT);

                return false;
        }

        Plugin.databaseConfig.getConfig().use(use);

        return true;
    }

    @Override
    public List<String> getTabCompletion(CommandSender sender, String[] args) {

        return StringUtil.clarificateIgnoreCase(args[0], Arrays.asList("yes", "no"));
    }

}
