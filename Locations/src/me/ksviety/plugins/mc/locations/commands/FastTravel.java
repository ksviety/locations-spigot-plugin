package me.ksviety.plugins.mc.locations.commands;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.events.PlayerTraveledEvent;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.ChatWriter;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FastTravel implements CommandExecutor, TabCompleter {

    private final static String PERMISSION_NICKNAME = "ksviety.locations.use.fasttravel.nickname";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Location location;
        Player player;
        List<String> locations;
        boolean isByPlayer = false;
        PlayerTraveledEvent playerTraveledEvent;

        //  PERMISSION CHECK
        if (!sender.hasPermission(command.getPermission()) && !sender.isOp())
            return false;

        //  ERROR CHECK
        //  Arguments validation
        if (args.length > 0) {
            location = Plugin.locationsData.getLocation(args[0].toLowerCase());

            if (args.length >= 2)
                isByPlayer = true;

        } else
            return false;

        //  ERROR CHECK
        //  Does the location exist
        if (location == null) {

            ChatWriter.writeError(sender, "Cannot find location " + args[0].toLowerCase() + ".");

            return false;
        }

        //  DOING THE STUFF
        //  Moving player to the location by their nickname
        //  If the the mode has been set to do like that
        if (isByPlayer) {
            player = Bukkit.getPlayer(args[1]);

            //  Check if the sender has permission to do that
            if (!sender.hasPermission(PERMISSION_NICKNAME) && !sender.isOp()) {

                ChatWriter.writeError(sender, "Permission fail.");

                return false;
            }

            //  Check if the player is online
            if (player == null) {

                ChatWriter.writeError(sender, "Cannot find the player.");

                return false;
            }

            return player.teleport(location.getWarpLocation());
        }

        //  Moving the sender to the typed location
        player = (Player)sender;

        //  Check if the player is OP
        if (player.isOp())
            return player.teleport(location.getWarpLocation());

        //  Checking if the player has unlocked the location already
        for (String cLocation: Plugin.playersData.getPlayer(player.getUniqueId()).getLocations()) {

            if (location.equals(cLocation))
                return player.teleport(location.getWarpLocation());

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> availableLocations = new ArrayList<>();

        //  PERMISSION CHECK
        if (sender instanceof Player && !sender.hasPermission(command.getPermission()))
            return null;

        //  Show all locations if the sender is console or OP
        //  Else only show unlocked by the player locations
        if (sender instanceof ConsoleCommandSender || sender.isOp() || sender.hasPermission(PERMISSION_NICKNAME)) {

            //  Choose what list of completions to show
            //  First argument: Location name
            //  Second argument: Player
            switch (args.length) {
                case 1:
                    //  Fill with all locations
                    for (Location location : Plugin.locationsData.getLocations())
                        availableLocations.add(location.getName());

                    break;
                case 2:
                    return null;
            }

        } else if (sender instanceof Player)
            availableLocations = Plugin.playersData.getPlayer(((Player) sender).getUniqueId()).getLocations();


        return availableLocations;
    }

}
