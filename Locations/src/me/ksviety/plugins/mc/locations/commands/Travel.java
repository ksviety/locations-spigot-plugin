package me.ksviety.plugins.mc.locations.commands;

import static me.ksviety.plugins.mc.locations.data.Locale.Keys.*;
import static me.ksviety.plugins.mc.locations.commands.util.Permissions.*;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.events.PlayerTraveledEvent;
import me.ksviety.plugins.mc.locations.pojo.Location;
import me.ksviety.plugins.mc.locations.util.ChatWriter;
import me.ksviety.plugins.mc.locations.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Travel implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Location location;
        //  Player to be traveled
        Player player;
        List<String> locations;
        boolean isByPlayer = false;
        PlayerTraveledEvent playerTraveledEvent;

        //  PERMISSION CHECK
        if (!sender.hasPermission(command.getPermission()) && !sender.isOp()) {

            ChatWriter.writeError(sender, Plugin.locale.getText(sender, PERMISSION_FAIL));

            return true;
        }

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

            ChatWriter.writeError(sender, Plugin.locale.getText(sender, CANNOT_FIND_LOCATION));

            return false;
        }

        //  ERROR CHECK
        //  Checking if the location is inactive
        if (!location.isActive()) {

            ChatWriter.writeError(sender, Plugin.locale.getText(sender, LOCATION_IS_INACTIVE));

            return false;
        }

        //  DOING THE STUFF
        //  Moving player to the location by their nickname
        //  If the the mode has been set to do like that
        if (isByPlayer) {
            player = Bukkit.getPlayer(args[1]);

            //  Check if the sender has permission to do that
            if (!sender.hasPermission(TRAVEL_OTHERS) && !sender.isOp()) {

                ChatWriter.writeError(sender, Plugin.locale.getText(sender, PERMISSION_FAIL));

                return true;
            }

            //  Check if the player is online
            if (player == null) {

                ChatWriter.writeError(sender, Plugin.locale.getText(sender, CANNOT_FIND_PLAYER));

                return false;
            }

        } else {
            //  Moving the sender to the typed location
            player = (Player) sender;

            //  Checking if the player has opened the location already
            if (!Plugin.playersData.getPlayer(player.getUniqueId()).getLocations().contains(location.getName())) {

                if (!player.isOp()) {

                    ChatWriter.writeError(sender, Plugin.locale.getText(sender, LOCATION_NOT_OPENED));

                    return true;
                }

            }

        }

        //  Creating and calling the event
        //  Then checking if no listeners have cancelled it
        //  And moving the player if so
        playerTraveledEvent = new PlayerTraveledEvent(player, location);

        Bukkit.getPluginManager().callEvent(playerTraveledEvent);

        if (playerTraveledEvent.isCancelled()) {

            ChatWriter.writeError(sender, Plugin.locale.getText(sender, ACTION_CANCELLED));

            return true;
        } else
            player.teleport(location.getWarpLocation());

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> availableLocations = new ArrayList<>();

        //  PERMISSION CHECK
        if (sender instanceof Player && !sender.hasPermission(command.getPermission()))
            return null;

        //  Show all locations if the sender is console or OP
        //  Else only show unlocked by the player locations
        if (sender instanceof ConsoleCommandSender || sender.isOp() || sender.hasPermission(LOCATIONS_LIST)) {

            //  Choose what list of completions to show
            //  First argument: Location name
            //  Second argument: Player
            switch (args.length) {
                case 1:
                    //  Fill with all locations
                    for (Location location : Plugin.locationsData.getLocations()) {

                        if (location.isActive())
                            availableLocations.add(location.getName());

                    }

                    //  Clarification
                    availableLocations = StringUtil.clarificateIgnoreCase(args[0], availableLocations);
                    break;
                case 2:
                    return null;
            }

        } else if (sender instanceof Player)
            availableLocations = Plugin.playersData.getPlayer(((Player) sender).getUniqueId()).getLocations();


        return availableLocations;
    }

}
