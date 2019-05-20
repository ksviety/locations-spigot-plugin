package me.ksviety.plugins.mc.locations.util;

import org.bukkit.command.CommandSender;

public class ChatWriter {

    public static void writeError(CommandSender sender, String errorMessage) {
        //  TODO
        sender.sendMessage("Error: " + errorMessage);
    }

    public static void writeSuccess(CommandSender sender, String successMessage) {
        //  TODO
        sender.sendMessage("Success: " + successMessage);
    }

    public static void writeHelp(CommandSender sender, String helpMessage) {
        //  TODO
        sender.sendMessage("Help: " + helpMessage);
    }

}
