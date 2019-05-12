package me.ksviety.plugins.mc.locations.util;

import org.bukkit.command.CommandSender;

public class ChatWriter {

    public static void writeError(CommandSender sender, String errorMessage) {
        //  TODO
        sender.sendMessage("Error: " + errorMessage);
    }

}
